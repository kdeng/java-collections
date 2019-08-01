package io.osnz.demos.jersey.feature;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Kefeng Deng (kefeng.deng@clearpoint.co.nz)
 */
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface Injectable {
}
