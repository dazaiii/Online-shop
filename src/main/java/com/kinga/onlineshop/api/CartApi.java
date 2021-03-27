package com.kinga.onlineshop.api;

import com.kinga.onlineshop.dao.entity.Cart;
import com.kinga.onlineshop.manager.CartManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartApi {
    CartManager cartManager;

    @Autowired  //Wstrzykiwanie obiektu cartManager jako parametr
    public CartApi(CartManager cartManager) {
        this.cartManager = cartManager;
    }

    @GetMapping
    public Iterable<Cart> getAll(){
        return cartManager.findAll();
    }

    @PostMapping
    public Cart addCart(@RequestBody Cart cart){
        return cartManager.save(cart);
    }

    @PutMapping
    public Cart updateCart(@RequestBody Cart cart){
        return cartManager.save(cart);
    }

    @DeleteMapping("{id}")
    public void deleteCart(@PathVariable("id") Long id){
        cartManager.deleteById(id);
    }
}
