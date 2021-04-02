package com.kinga.onlineshop.api;

import com.kinga.onlineshop.dao.entity.Cart;
import com.kinga.onlineshop.dao.entity.User;
import com.kinga.onlineshop.dto.CartDto;
import com.kinga.onlineshop.dto.UserDto;
import com.kinga.onlineshop.manager.CartManager;
import com.kinga.onlineshop.manager.UserManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
public class CartApi {
    CartManager cartManager;
    ModelMapper modelMapper;
    UserManager userManager;

    @Autowired  //Wstrzykiwanie obiektu cartManager jako parametr
    public CartApi(CartManager cartManager, ModelMapper modelMapper, UserManager userManager) {
        this.cartManager = cartManager;
        this.modelMapper = modelMapper;
        this.userManager = userManager;
    }

    @GetMapping
    public List<CartDto> getAll(){
        Iterable<Cart> iterable = cartManager.findAll();
        List<Cart> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public List<CartDto> findByUser(@PathVariable Long id){
        Optional<User> optional = userManager.findById(id);
        List<Cart> list = cartManager.findByUser(optional.get());
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CartDto addCart(@RequestBody CartDto cartDto){
        Cart cart = convertToEntity(cartDto);
        Cart createdCart = cartManager.save(cart);
        return convertToDto(createdCart);
    }

    @PutMapping("{id}")
    public CartDto updateCart(@PathVariable Long id, @RequestBody CartDto cartDto){
        cartDto.setIdCart(id);
        Cart cart = convertToEntity(cartDto);
        Cart createdCart = cartManager.save(cart);
        return convertToDto(createdCart);
    }

    @DeleteMapping("{id}")
    public void deleteCart(@PathVariable("id") Long id){
        cartManager.deleteById(id);
    }

    private CartDto convertToDto(Cart cart){
        CartDto cartDto = modelMapper.map(cart, CartDto.class);
        return cartDto;
    }

    private Cart convertToEntity(CartDto cartDto){
        Cart cart = modelMapper.map(cartDto, Cart.class);
        return cart;
    }

    private UserDto convertToDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    private User convertToEntity(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
        return user;
    }
}
