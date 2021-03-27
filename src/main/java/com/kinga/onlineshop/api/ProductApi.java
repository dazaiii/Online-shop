package com.kinga.onlineshop.api;

import com.kinga.onlineshop.dao.entity.Product;
import com.kinga.onlineshop.dao.entity.Rating;
import com.kinga.onlineshop.manager.ProductManager;
import com.kinga.onlineshop.manager.RatingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductApi {
    ProductManager productManager;
    RatingManager ratingManager;

    @Autowired
    public ProductApi(ProductManager productManager, RatingManager ratingManager) {
        this.productManager = productManager;
        this.ratingManager = ratingManager;
    }

    @GetMapping
    public Iterable<Product> getAll(){
        return productManager.findAll();
    }

    @GetMapping("{id}")
    public Optional<Product> getById(@PathVariable Long id){
        return productManager.findById(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productManager.save(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product){
        return productManager.save(product);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id){
        productManager.deleteById(id);
    }

    @GetMapping("{id}/ratings")
    public Iterable<Rating> getRatingById(@PathVariable Long id){
        Optional<Product> optional = productManager.findById(id);
        return ratingManager.findByProduct(optional.get());
    }

    @PostMapping("{id}")
    public Rating addRating(@PathVariable Long id, @RequestBody Rating rating){
        Optional<Product> optional = productManager.findById(id);
        rating.setProduct(optional.get());
        return ratingManager.save(rating);
    }

    @PutMapping("{id}")
    public Rating updateRating(@PathVariable Long id, @RequestBody Rating rating){
        Optional<Product> optional = productManager.findById(id);
        rating.setProduct(optional.get());
        return ratingManager.save(rating);
    }

    @DeleteMapping("{idProduct}/{idRating}")
    public void deleteRating(@PathVariable Long idProduct, @PathVariable Long idRating){
        ratingManager.deleteById(idRating);
    }
}
