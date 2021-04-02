package com.kinga.onlineshop.manager;
//Manager jest warstwa posrednia pomiedzy warstwa dostepu do danych a api

import com.kinga.onlineshop.dao.UserRepo;
import com.kinga.onlineshop.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManager {
    private UserRepo userRepo;

    @Autowired
    public UserManager(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> findById(Long id){
        return userRepo.findById(id);
    }

    public Iterable<User> findAll(){
        return userRepo.findAll();
    }

    public User save(User user){
        return userRepo.save(user);
    }

    public void deleteById(Long id){
        userRepo.deleteById(id);
    }

    public List<User> findByLogin(String login){
        return userRepo.findByLogin(login);
    }

    public List<User> findByEmail(String email){
        return userRepo.findByEmail(email);
    }

}
