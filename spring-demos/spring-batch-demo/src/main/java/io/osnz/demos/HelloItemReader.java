package io.osnz.demos;


import org.springframework.batch.item.ItemReader;

/**
 * @author Kefeng Deng (deng@51any.com)
 */

public class HelloItemReader implements ItemReader<String> {

  private int index = 0;

  @Override
  public String read() throws Exception {

    if (index > 5000) {
      return null;
    }

    index++;
    Thread.sleep(5);
    return "Hello World! " + index;

  }
}
