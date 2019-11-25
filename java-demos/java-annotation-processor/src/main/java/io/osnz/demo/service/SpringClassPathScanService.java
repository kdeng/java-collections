package io.osnz.demo.service;

import io.osnz.demo.annotation.Apple;
import io.osnz.demo.annotation.Banana;
import io.osnz.demo.annotation.Cherry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author Kefeng Deng (https://bit.ly/2JFoCO1)
 */
@Service
@Slf4j
public class SpringClassPathScanService {

  @PostConstruct
  public void scanAnnotations() {
    scanPackages();
  }

  protected void scanPackages() {
    log.info("====================================");
    long startTime = System.currentTimeMillis();
    final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
    provider.addIncludeFilter(new AnnotationTypeFilter(Apple.class));
    provider.addIncludeFilter(new AnnotationTypeFilter(Banana.class));
    provider.addIncludeFilter(new AnnotationTypeFilter(Cherry.class));
    final Set<BeanDefinition> classes = provider.findCandidateComponents("io.osnz");
    long endTime = System.currentTimeMillis();
    classes.stream().forEach((BeanDefinition bean) -> {
      try {
        Class<?> clazz = Class.forName(bean.getBeanClassName());
        log.info("Bean Class : {}", clazz.getSimpleName());
        log.info("Bean Package: {}", clazz.getPackageName());
        log.info("Bean Annotations : {}", String.join(", ", Arrays.stream(clazz.getAnnotations()).map(Annotation::toString).collect(Collectors.toList())));
      } catch (ClassNotFoundException ex) {
        log.error("Cannot find class", ex);
      }
    });
    log.info("Spring took {} seconds", (endTime-startTime));
    log.info("====================================");
  }

}
