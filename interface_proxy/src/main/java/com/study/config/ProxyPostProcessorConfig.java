package com.study.config;

import com.study.proxy.ProxyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2023/6/4 20:21
 */
@Component
@DependsOn(value = {"springUtils","proxyProperties"})
public class ProxyPostProcessorConfig implements BeanDefinitionRegistryPostProcessor, ResourceLoaderAware {

    private final static Logger log = LoggerFactory.getLogger(ProxyPostProcessorConfig.class);

    @Autowired
    ProxyProperties proxyProperties;

    private ResourcePatternResolver resourcePatternResolver;

    private CachingMetadataReaderFactory metadataReaderFactory;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        // TODO 由于生命周期缘故，这里还没有执行autowired加载，会报错空指针，需要修改为手动读取配置，待完善
        ScanProperties[] scanProperties = proxyProperties.getScanProperties();
        for (ScanProperties proxyType : scanProperties) {
            // 按路径扫描所有的接口类
            List<Class<?>> classes = scanProxyInterface(proxyType);
            List<String> proxyClass = new ArrayList<>();
            proxyType.setProxyClass(proxyClass);
            if (!CollectionUtils.isEmpty(classes)) {
                for (Class<?> aClass : classes) {
                    // 判断是否为接口
                    if (aClass.isInterface()) {
                        String name = aClass.getName();
                        proxyClass.add(name);
                        // 名称驼峰转换后注册到Spring容器中
                        String finalName = name.substring(0, 1).toLowerCase() + name.substring(1);
                        GenericBeanDefinition definition = (GenericBeanDefinition) BeanDefinitionBuilder.genericBeanDefinition(aClass).getRawBeanDefinition();
                        definition.getConstructorArgumentValues().addGenericArgumentValue(aClass);
                        definition.setBeanClass(ProxyFactory.class);
                        definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_NAME);
                        beanDefinitionRegistry.registerBeanDefinition(finalName, definition);
                    }
                }
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    /**
     * 获取某个路径下所有的类信息
     *
     * @param scanProperty {@link ScanProperties 扫描配置信息}
     * @return
     */
    private List<Class<?>> scanProxyInterface(ScanProperties scanProperty) {
        // 第一个class类的集合
        List<Class<?>> classes = new ArrayList<Class<?>>();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + resolveBasePackage(scanProperty.getScanPath()) + "/" + scanProperty.getEndWith();
        try {
            Resource[] resources = this.resourcePatternResolver.getResources(packageSearchPath);
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
                    String className = metadataReader.getClassMetadata().getClassName();
                    log.info("proxy className : {}", className);
                    try {
                        Class<?> clazz = Class.forName(className, true, this.resourcePatternResolver.getClassLoader());
                        if (clazz.isInterface()) {
                            classes.add(clazz);
                        }
                    } catch (ClassNotFoundException e) {
                        log.error("proxy class name exception :", e);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return classes;
    }

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
        resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
    }

}
