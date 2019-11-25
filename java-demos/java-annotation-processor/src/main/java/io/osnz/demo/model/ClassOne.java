package io.osnz.demo.model;

import io.osnz.demo.annotation.Apple;
import io.osnz.demo.annotation.Banana;
import io.osnz.demo.annotation.Cherry;

/**
 * @author Kefeng Deng (https://bit.ly/2JFoCO1)
 */
@Apple("green")
@Banana(value = "yellow", provider = "China")
@Cherry(provider = "NZ")
public class ClassOne {
}
