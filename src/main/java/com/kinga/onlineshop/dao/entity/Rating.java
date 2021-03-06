package com.kinga.onlineshop.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_rating")
    private Long idRating;

    @Column(name = "rating")
    private int rating;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="id_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;

    public Rating() {
    }

    public Rating(Long idRating, int rating, String description, Product product, User user) {
        this.idRating = idRating;
        this.rating = rating;
        this.description = description;
        this.product = product;
        this.user = user;
    }

    public Long getIdRating() {
        return idRating;
    }

    public void setIdRating(Long idRating) {
        this.idRating = idRating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
