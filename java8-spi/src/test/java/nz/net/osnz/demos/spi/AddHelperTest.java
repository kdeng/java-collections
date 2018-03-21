package nz.net.osnz.demos.spi;

import nz.net.osnz.demos.spi.helper.AddHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Created by kdeng on 2/04/14.
 */
public class AddHelperTest {

    @Test
    public void testTwoImplementations() {
        ServiceLoader<AddHelper> operations = ServiceLoader.load(AddHelper.class);
        Iterator<AddHelper> helperIterator = operations.iterator();
        List<AddHelper> userHelperList = new ArrayList<>();
        while (helperIterator.hasNext()) {
            AddHelper helper = helperIterator.next();
            System.out.println(helper.add(1, 2));
            userHelperList.add(helper);
        }
        Assert.assertEquals(2, userHelperList.size());
    }

}
