package com.kinga.onlineshop.manager;

import com.kinga.onlineshop.dao.ProductRepo;
import com.kinga.onlineshop.dao.RatingRepo;
import com.kinga.onlineshop.dao.entity.Product;
import com.kinga.onlineshop.dao.entity.Rating;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Optional;

@Service
public class RatingManager {
    private RatingRepo ratingRepo;

    public RatingManager(RatingRepo ratingRepo) {
        this.ratingRepo = ratingRepo;
    }

    public Optional<Rating> findById(Long id){
        return ratingRepo.findById(id);
    }

    public Iterable<Rating> findAll(){
        return ratingRepo.findAll();
    }

    public List<Rating> findByProduct(Product product){
        return ratingRepo.findByProduct(product);
    }

    public Rating save(Rating rating){
        return ratingRepo.save(rating);
    }

    public void deleteById(Long id){
        ratingRepo.deleteById(id);
    }
}
