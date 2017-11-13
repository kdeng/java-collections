package nz.net.osnz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Kefeng Deng (deng@51any.com)
 */

public class HelloItemWriter implements ItemWriter<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloItemWriter.class);

    @Override
    public void write(List<? extends Object> items) throws Exception {
        if (items != null) {
            items.forEach(item -> LOGGER.info(item.toString()));
        }
    }

}
