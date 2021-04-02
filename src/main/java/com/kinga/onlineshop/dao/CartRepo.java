package com.kinga.onlineshop.dao;

import com.kinga.onlineshop.dao.entity.Cart;
import com.kinga.onlineshop.dao.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends CrudRepository<Cart, Long> {
    List<Cart> findByUser(User user);
}
