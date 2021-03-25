package com.kinga.onlineshop.dao.entity;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCart;

    @Column(name="amount")
    private int amount;

    @OneToMany(mappedBy = "cart")
    private Set<Product> products;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    public Cart(Long idCart, int amount, Set<Product> products, User user) {
        this.idCart = idCart;
        this.amount = amount;
        this.products = products;
        this.user = user;
    }

    public Long getIdCart() {
        return idCart;
    }

    public void setIdCart(Long idCart) {
        this.idCart = idCart;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
