package io.osnz;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Audit {

  @AliasFor("message")
  String value() default "";

  @AliasFor("value")
  String message() default "";

  Level level() default Level.INFO;

  public static enum Level {
    DEBUG,
    INFO,
    WARN,
    ERROR
  }

}
