package io.osnz.demo.service;

import com.google.common.reflect.ClassPath;
import io.osnz.demo.annotation.Apple;
import io.osnz.demo.annotation.Banana;
import io.osnz.demo.annotation.Cherry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author Kefeng Deng (https://bit.ly/2JFoCO1)
 */
@Service
@Slf4j
public class GuavaClassPathScanService {

  @PostConstruct
  public void scanClasses() throws IOException {
    log.info("====================================");
    long startTime = System.currentTimeMillis();
    final ClassLoader loader = Thread.currentThread().getContextClassLoader();
    for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
      if (info.getName().startsWith("io.osnz.")) {
        final Class<?> clazz = info.load();
        log.info("Class : {}", clazz.getSimpleName());
        log.info("Class package : {}", clazz.getPackageName());
        log.info("Class Annotations: {}", clazz.getAnnotations());
        if (hasAnnotated(clazz)) {
          log.info("Class : {}", clazz.getSimpleName());
        }
      }
    }
    long endTime = System.currentTimeMillis();
    log.info("Guava took {} seconds", (endTime - startTime));
    log.info("====================================");
  }

  private boolean hasAnnotated(Class clazz) {
    return clazz.isAnnotationPresent(Apple.class) ||
      clazz.isAnnotationPresent(Banana.class) ||
      clazz.isAnnotationPresent(Cherry.class);
  }

}
