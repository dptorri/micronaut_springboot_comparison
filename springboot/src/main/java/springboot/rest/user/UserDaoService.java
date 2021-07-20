package springboot.rest.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    // Add a static list
    static {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Eve", new Date()));
        users.add(new User(3, "Jack", new Date()));
    }

    /**
     * findAll: Retrieve all users
     * @return
     */
    public List<User> findAll() {
        return users;
    }

    /**
     * save: Create new user
     * @param user
     * @return
     */
    public User save(User user) {
        // if not created acumulate id
        if(user.getId()==null) {
            user.setId(++usersCount);
        }

        // save user
        users.add(user);

        //return user
        return user;
    }

    /**
     * findOne: Retrieve user by id
     * @param userId
     * @return
     */
    public User findOne(int userId){
        for(User user: users) {
            if(user.getId() == userId){
                return user;
            }
        }
        return null;
    }

    public User deleteById(int userId){
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == userId) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }

}
