package com.kinga.onlineshop.manager;

import com.kinga.onlineshop.dao.entity.Cart;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartManager {
    private CartManager cartManager;

    public CartManager(CartManager cartManager) {
        this.cartManager = cartManager;
    }

    public Optional<Cart> findById(Long id){
        return cartManager.findById(id);
    }

    public Iterable<Cart> findAll(){
        return cartManager.findAll();
    }

    public Cart save(Cart cart){
        return cartManager.save(cart);
    }

    public void deleteById(Long id){
        cartManager.deleteById(id);
    }
}
