package nz.net.osnz.train.provider;

import nz.net.osnz.train.domain.User;

import java.util.Comparator;

/**
 * Created by kdeng on 1/04/14.
 */
public class ComparatorProvider implements Comparator<User> {

    @Override
    public int compare(User o1, User o2) {
        return o1.getAge() - o2.getAge();
    }

}
