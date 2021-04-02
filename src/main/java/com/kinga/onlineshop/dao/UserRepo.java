package com.kinga.onlineshop.dao;

import com.kinga.onlineshop.dao.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    List<User> findByLogin(String login);
    List<User> findByEmail(String email);
}
