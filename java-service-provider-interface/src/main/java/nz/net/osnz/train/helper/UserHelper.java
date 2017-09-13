package nz.net.osnz.train.helper;

import nz.net.osnz.train.domain.User;

/**
 * Created by kdeng on 1/04/14.
 */
public class UserHelper {

    public String display(User user) {
        if (user.getAge() < 16) {
            return "kid";
        } else if (user.getAge() < 30) {
            return "young";
        } else {
            return "man";
        }
    }

}
