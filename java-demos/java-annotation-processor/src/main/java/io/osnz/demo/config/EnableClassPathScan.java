package io.osnz.demo.config;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

/**
 * @author Kefeng Deng (https://bit.ly/2JFoCO1)
 */
@Slf4j
public class EnableClassPathScan {

  @PostConstruct
  public void postConstruct() {
    log.info("++++++++++++++++++++++++++++++++++++++++");
  }

}
