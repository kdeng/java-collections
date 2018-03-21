package nz.net.osnz.demos.spi.impl;

import nz.net.osnz.demos.spi.domain.User;
import nz.net.osnz.demos.spi.helper.UserHelper;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public class AUserHelper implements UserHelper {

    @Override
    public String display(User user) {
        if (user.getAge() < 16) {
            return "A kid";
        } else if (user.getAge() < 30) {
            return "A young";
        } else {
            return "A man";
        }
    }
}
