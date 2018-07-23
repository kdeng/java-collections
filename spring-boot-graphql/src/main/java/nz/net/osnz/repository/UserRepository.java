package nz.net.osnz.repository;


import nz.net.osnz.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Component
public class UserRepository {

    private static final Map<Long, User> USER_LIST = new HashMap<>();

    @PostConstruct
    public void mockUsers() {
        for (int i = 1; i <= 500; i++) {
            USER_LIST.put(
                Integer.toUnsignedLong(i),
                User.builder().email("email_" + i + "@gmail.com").name("Robot #" + i).id(i).build()
            );
        }
    }

    public User getUserById(Long id) {
        return USER_LIST.get(id);
    }

    public List<User> getUsers() {
        return new ArrayList<>(USER_LIST.values());
    }

    public User addUser(User newUser) {
        int nextId = USER_LIST.size() + 1;
        newUser.setId(nextId);
        USER_LIST.put(Integer.toUnsignedLong(nextId), newUser);
        return newUser;
    }

}
