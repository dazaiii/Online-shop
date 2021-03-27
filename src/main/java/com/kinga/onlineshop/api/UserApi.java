package com.kinga.onlineshop.api;

import com.kinga.onlineshop.dao.entity.User;
import com.kinga.onlineshop.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserApi {
    private UserManager userManager;

    @Autowired
    public UserApi(UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping
    public Iterable<User> getAll(){
        return userManager.findAll();
    }

    @GetMapping("{id}")
    public Optional<User> getById(@PathVariable("id") Long id){
        return userManager.findById(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return userManager.save(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
        return userManager.save(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userManager.deleteById(id);
    }
}
