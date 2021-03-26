package com.kinga.onlineshop.dao;

import com.kinga.onlineshop.dao.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends CrudRepository<Cart, Long> {
}
