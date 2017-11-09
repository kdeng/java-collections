package nz.net.osnz.demos;

import nz.net.osnz.demos.domain.User;
import nz.net.osnz.demos.helper.UserHelper;
import org.junit.Test;

import java.util.ServiceLoader;

/**
 * Created by kdeng on 2/04/14.
 */
public class AgeTest {

    private static ServiceLoader<UserHelper> serviceLoader = ServiceLoader.load(nz.net.osnz.demos.helper.UserHelper.class);

    @Test
    public void testPersonAgeCompare() {

        User oldMan = new User(32);

        User youngMan = new User(23);

        User kid = new User(3);


        assert "man".equals(getHelper().display(oldMan));
        assert "young".equals(getHelper().display(youngMan));
        assert "kid".equals(getHelper().display(kid));

    }

    @SuppressWarnings("unchecked")
    protected static UserHelper getHelper() {
        for(UserHelper service : serviceLoader)
        {
            return (UserHelper) service;
        }
        return null;
    }

}
