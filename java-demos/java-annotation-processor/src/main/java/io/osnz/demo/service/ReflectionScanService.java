package io.osnz.demo.service;

import io.osnz.demo.annotation.Apple;
import io.osnz.demo.annotation.Banana;
import io.osnz.demo.annotation.Cherry;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * @author Kefeng Deng (https://bit.ly/2JFoCO1)
 */
@Service
@Slf4j
public class ReflectionScanService {

  @PostConstruct
  public void scan() {
    log.info("====================================");
    log.info("Reflection scan");
    long startTime = System.currentTimeMillis();

    Reflections reflections = new Reflections("io.osnz.demo");
    Set<Class<?>> appleAnnotated = reflections.getTypesAnnotatedWith(Apple.class);
    Set<Class<?>> bananaAnnotated = reflections.getTypesAnnotatedWith(Banana.class);
    Set<Class<?>> cherryAnnotated = reflections.getTypesAnnotatedWith(Cherry.class);
    long endTime = System.currentTimeMillis();

    log.info("Classes with Apple");
    appleAnnotated.forEach(this::display);

    log.info("Classes with Banana");
    bananaAnnotated.forEach(this::display);

    log.info("Classes with Cherry");
    cherryAnnotated.forEach(this::display);

    log.info("Reflections took {} seconds", (endTime-startTime));
    log.info("====================================");
  }

  public void display(Class clazz) {
    log.info("Class name: {}", clazz.getSimpleName());
    log.info("Class Package name: {}", clazz.getPackageName());
    log.info("Class annotations: {}", clazz.getAnnotations());
  }

}
