package nz.net.osnz.demos.spi.impl;

import nz.net.osnz.demos.spi.domain.User;
import nz.net.osnz.demos.spi.helper.UserHelper;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public class BUserHelper implements UserHelper {
    @Override
    public String display(User user) {
        if (user.getAge() < 16) {
            return "B kid";
        } else if (user.getAge() < 30) {
            return "B young";
        } else {
            return "B man";
        }
    }
}
