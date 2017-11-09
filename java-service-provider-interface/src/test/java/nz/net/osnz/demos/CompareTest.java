package nz.net.osnz.demos;

import nz.net.osnz.demos.domain.User;
import org.junit.Test;

import java.util.*;

/**
 * Created by kdeng on 1/04/14.
 */
public class CompareTest {

    private static ServiceLoader<Comparator> serviceLoader = ServiceLoader.load(Comparator.class);

    User oldMan = new User(32);

    User youngMan = new User(23);

    User kid = new User(3);

    @Test
    public void testPersonAgeCompare() {

        List<User> userList = new ArrayList<User>();
        userList.add(oldMan);
        userList.add(kid);
        userList.add(youngMan);

        assert userList.get(0).getAge() == 32;
        assert userList.get(1).getAge() == 3;
        assert userList.get(2).getAge() == 23;

        Collections.sort(userList, getComparator());

        assert userList.get(0).getAge() == 3;
        assert userList.get(1).getAge() == 23;
        assert userList.get(2).getAge() == 32;

    }

    @SuppressWarnings("unchecked")
    protected static Comparator<User> getComparator() {
        for(Comparator service : serviceLoader)
        {
            return (Comparator<User>) service;
        }
        return null;
    }




}
