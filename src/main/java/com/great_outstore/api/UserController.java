package com.great_outstore.api;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private Map<Integer, User> users = new HashMap<>();

    public UserController() {
        addUser("Billy", "OR",1);
        addUser("julie", "CA",2);
        addUser("drew", "NY",3);
        addUser("jack", "PA",4);
    }

    private void addUser(String name, String location, Integer id){
        User user = new User(name, location, id);
        users.put(id,user);
    }

    @GetMapping("/all")
    public Map getAllUsers(){
        return users;
    }

    @GetMapping("/{id}")
    public Object getUser(@PathVariable("id") Integer userId){
        if(users.containsKey(userId)){
            return users.get(userId);
        }
        return String.format(User.notFound, userId);
    }

    @DeleteMapping("/{id}")
    public String removeUser(@PathVariable("id") Integer userId){
        if(users.containsKey(userId)){
            User user = users.remove(userId);
            users.values().forEach(System.out::println);
            System.out.printf("The user with id:%d was removed%n", userId);
            return String.format("%s was removed from the users", user.getName());
        }
        return String.format(User.notFound, userId);
    }

    @PostMapping("/addUser")
    public User createUser(@RequestBody User user){
        users.put(user.getId(), user);
        System.out.println(users);
        return user;
    }


}










