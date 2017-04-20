package com.infoshareacademy.jjdd1.teamerror.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Startup
public class DiscoverBeans {

    private static Logger log = LoggerFactory.getLogger(DiscoverBeans.class);

    @Inject
    BeanManager beanManager;

    @PostConstruct
    public void afterDeployment() {
        List<String> names = beanManager
                .getBeans(Object.class, new AnnotationLiteral<Any>() {
                })
                .stream()
                .map(b -> b.getBeanClass().getName()).collect(Collectors.toList());

        log.debug("Discovered beans: {}", names);
    }

}
