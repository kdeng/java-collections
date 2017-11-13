package nz.net.osnz;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

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
