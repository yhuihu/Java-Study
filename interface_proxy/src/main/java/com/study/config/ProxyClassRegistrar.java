package com.study.config;

import com.study.proxy.DefaultProxyPersistent;
import com.study.proxy.ProxyFactory;
import com.study.proxy.ProxyFactoryHandle;
import com.study.proxy.ProxyPersistent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2023/6/4 20:21
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(ProxyThreadPoolConfig.class)
public class ProxyClassRegistrar implements BeanDefinitionRegistryPostProcessor, ResourceLoaderAware, EnvironmentAware, ApplicationContextAware {

    private final static Logger log = LoggerFactory.getLogger(ProxyClassRegistrar.class);
    private ResourcePatternResolver resourcePatternResolver;

    private ResourceLoader resourceLoader;

    private Environment environment;

    private ApplicationContext applicationContext;


    /**
     * 包名替换，从.替换为文件符
     *
     * @param scanPath
     * @return
     */
    private String resolveBasePackage(String scanPath) {
        return ClassUtils.convertClassNameToResourcePath(scanPath);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
    }

    private void persistentInterFace() {
        ProxyPersistent proxyPersistent = null;
        Map<String, ProxyPersistent> beansOfType = applicationContext.getBeansOfType(ProxyPersistent.class);
        if (beansOfType.size() > 1) {
            Set<Map.Entry<String, ProxyPersistent>> entries = beansOfType.entrySet();
            for (Map.Entry<String, ProxyPersistent> entry : entries) {
                if (!(entry.getValue() instanceof DefaultProxyPersistent)) {
                    proxyPersistent = entry.getValue();
                }
            }
        } else {
            proxyPersistent = beansOfType.values().iterator().next();
        }
        assert proxyPersistent != null;
        Map<String, ScanProperty> read = proxyPersistent.read();
        if (read == null) {
            read = new HashMap<>();
        }
        Map<String, ScanProperty> finalRead = read;
        ProxyFactory.scanPropertiesMap.forEach((key, value) -> {
            if (finalRead.containsKey(key)) {
                value.setDefaultImpl(finalRead.get(key).getDefaultImpl());
            } else {
                finalRead.put(key, value);
            }
            log.info("proxy class {} , impl {}", value.getClassInfo().getName(), value.getDefaultImpl());
        });
        proxyPersistent.write(read);
    }

    private void registerProxyConfiguration(AnnotationMetadata metadata, BeanDefinitionRegistry registry) throws ClassNotFoundException {
        Map<String, Object> defaultAttrs = metadata.getAnnotationAttributes(EnableProxyConfig.class.getName(), true);
        if (defaultAttrs.containsKey("basePackages")) {
            registerProxyBean(registry, (String[]) defaultAttrs.get("basePackages"));
        }
    }

    protected ClassPathScanningCandidateComponentProvider getScanner() {
        return new ClassPathScanningCandidateComponentProvider(false, this.environment) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                boolean isCandidate = false;
                if (beanDefinition.getMetadata().isIndependent()) {
                    if (!beanDefinition.getMetadata().isAnnotation()) {
                        isCandidate = true;
                    }
                }
                return isCandidate;
            }
        };
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, Object> beansWithAnnotation = beanFactory.getBeansWithAnnotation(EnableProxyConfig.class);
        Set<String> beanNames = beansWithAnnotation.keySet();
        beanNames.forEach(beanName -> {
            EnableProxyConfig annotationOnBean = beanFactory.findAnnotationOnBean(beanName, EnableProxyConfig.class);
            String[] basedPackages = annotationOnBean.basePackages();
            try {
                registerProxyBean((BeanDefinitionRegistry) beanFactory, basedPackages);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void registerProxyBean(BeanDefinitionRegistry registry, String[] basePackages) throws ClassNotFoundException {
        ClassPathScanningCandidateComponentProvider scanner = getScanner();
        scanner.setResourceLoader(this.resourceLoader);
        scanner.addIncludeFilter(new AnnotationTypeFilter(ProxyComponent.class));
        LinkedHashSet<BeanDefinition> candidateComponents = new LinkedHashSet<>();
        for (String basePackage : basePackages) {
            candidateComponents.addAll(scanner.findCandidateComponents(basePackage));
        }

        for (BeanDefinition candidateComponent : candidateComponents) {
            if (candidateComponent instanceof AnnotatedBeanDefinition) {
                AnnotationMetadata annotationMetadata = ((AnnotatedBeanDefinition) candidateComponent).getMetadata();
                Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(ProxyComponent.class.getCanonicalName());
                String defaultImpl = "";
                if (annotationAttributes != null) {
                    defaultImpl = (String) annotationAttributes.get("impl");
                }
                Class<?> clazz = Class.forName(candidateComponent.getBeanClassName(), true, this.resourcePatternResolver.getClassLoader());
                if (clazz.isInterface()) {
                    String name = clazz.getSimpleName();
                    log.info("proxy class : {}", name);
                    ScanProperty scanProperty = new ScanProperty();
                    scanProperty.setClassInfo(clazz);
                    String className = name.substring(0, 1).toLowerCase() + name.substring(1);
                    scanProperty.setClassName(className);
                    scanProperty.setDefaultImpl(defaultImpl);
                    ProxyFactory.scanPropertiesMap.put(className, scanProperty);
                    Method[] declaredMethods = clazz.getDeclaredMethods();
                    Map<String,String> methodMap = new HashMap<>();
                    for (Method declaredMethod : declaredMethods) {
                        ProxyMethod annotation = declaredMethod.getAnnotation(ProxyMethod.class);
                        if (annotation != null) {
                            methodMap.put(declaredMethod.getName(), annotation.impl());
                        }
                    }
                    scanProperty.setMethodsImpl(methodMap);
                    // 名称驼峰转换后注册到Spring容器中
                    GenericBeanDefinition definition = (GenericBeanDefinition) BeanDefinitionBuilder.genericBeanDefinition(clazz).getRawBeanDefinition();
                    definition.getConstructorArgumentValues().addGenericArgumentValue(clazz);
                    definition.setBeanClass(ProxyFactoryHandle.class);
                    definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_NAME);
                    definition.setPrimary(true);
                    registry.registerBeanDefinition(className, definition);
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
