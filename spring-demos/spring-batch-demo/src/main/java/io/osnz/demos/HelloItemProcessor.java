package io.osnz.demos;

import org.springframework.batch.item.ItemProcessor;

/**
 * @author Kefeng Deng (deng@51any.com)
 */

public class HelloItemProcessor implements ItemProcessor<String, String> {

  @Override
  public String process(String item) throws Exception {
    Thread.sleep(2 * 1000);
    return item + " processed";
  }

}
