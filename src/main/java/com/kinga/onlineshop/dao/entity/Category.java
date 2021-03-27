package com.kinga.onlineshop.dao.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category", nullable = false, unique = true)
    private Long idCategory;

    @Column(name = "category")
    private String category;

    @OneToMany(mappedBy = "category", targetEntity = Product.class)
    private Set<Product> products;

    public Category() {
    }

    public Category(Long idCategory, String category, Set<Product> products) {
        this.idCategory = idCategory;
        this.category = category;
        this.products = products;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
