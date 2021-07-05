package springboot.rest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/user/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if(user==null) {
            throw new UserNotFoundException("id-"+ id);
        }
        return user;
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        User savedUser = service.save(user);
    }

}
