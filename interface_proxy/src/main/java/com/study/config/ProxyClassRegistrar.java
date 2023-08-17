package com.study.config;

import com.study.proxy.ProxyFactory;
import com.study.proxy.ProxyFactoryHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2023/6/4 20:21
 */
@Component
@DependsOn(value = {"springUtils"})
@Order(Integer.MIN_VALUE)
public class ProxyClassRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {

    private final static Logger log = LoggerFactory.getLogger(ProxyClassRegistrar.class);
    private ResourcePatternResolver resourcePatternResolver;

    private ResourceLoader resourceLoader;

    private Environment environment;


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

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        try {
            registerProxyConfiguration(importingClassMetadata, registry);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void registerProxyConfiguration(AnnotationMetadata metadata, BeanDefinitionRegistry registry) throws ClassNotFoundException {
        Map<String, Object> defaultAttrs = metadata.getAnnotationAttributes(EnableProxyConfig.class.getName(), true);
        if (defaultAttrs.containsKey("basePackages")) {
            String[] basePackages = (String[]) defaultAttrs.get("basePackages");
            ClassPathScanningCandidateComponentProvider scanner = getScanner();
            scanner.setResourceLoader(this.resourceLoader);
            scanner.addIncludeFilter(new AnnotationTypeFilter(ProxyConfig.class));
            LinkedHashSet<BeanDefinition> candidateComponents = new LinkedHashSet<>();
            for (String basePackage : basePackages) {
                candidateComponents.addAll(scanner.findCandidateComponents(basePackage));
            }

            for (BeanDefinition candidateComponent : candidateComponents) {
                if (candidateComponent instanceof AnnotatedBeanDefinition) {
                    AnnotationMetadata annotationMetadata = ((AnnotatedBeanDefinition) candidateComponent).getMetadata();
                    Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(ProxyConfig.class.getCanonicalName());
                    Object implMethods = annotationAttributes.get("impl");
                    Class<?> clazz = Class.forName(candidateComponent.getBeanClassName(), true, this.resourcePatternResolver.getClassLoader());
                    if (clazz.isInterface()) {
                        String name = clazz.getSimpleName();
                        log.info("proxy class : {}", name);
                        ScanProperty scanProperty = new ScanProperty();
                        scanProperty.setClassInfo(clazz);
                        String className = name.substring(0, 1).toLowerCase() + name.substring(1);
                        scanProperty.setClassName(className);
                        scanProperty.setClassImpl((String) implMethods);
                        ProxyFactory.scanPropertiesMap.put(className, scanProperty);

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
}
