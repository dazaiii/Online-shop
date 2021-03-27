package com.kinga.onlineshop.dao.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idProduct")
    private Long idProduct;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private double price;

    @Column(name="amount")
    private int amount;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="idCategory")
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<Rating> ratings;

    @OneToMany(mappedBy = "product")
    private Set<Cart> carts;

    /*
    @ManyToOne(targetEntity = Cart.class)
    @JoinColumn(name="idProduct")
    private Cart cart;
    */

    public Product() {
    }

    public Product(Long idProduct, String name, double price, int amount, String description, Category category, Set<Rating> ratings, Set<Cart> carts) {
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.ratings = ratings;
        this.carts = carts;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }
}
