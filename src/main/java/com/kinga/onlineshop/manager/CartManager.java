package com.kinga.onlineshop.manager;

import com.kinga.onlineshop.dao.CartRepo;
import com.kinga.onlineshop.dao.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartManager {
    private CartRepo cartRepo;

    @Autowired
    public CartManager(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    public Optional<Cart> findById(Long id){
        return cartRepo.findById(id);
    }

    public Iterable<Cart> findAll(){
        return cartRepo.findAll();
    }

    public Cart save(Cart cart){
        return cartRepo.save(cart);
    }

    public void deleteById(Long id){
        cartRepo.deleteById(id);
    }
}
