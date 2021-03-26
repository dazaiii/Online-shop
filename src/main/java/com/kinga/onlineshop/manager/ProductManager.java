package com.kinga.onlineshop.manager;

import com.kinga.onlineshop.dao.ProductRepo;
import com.kinga.onlineshop.dao.entity.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductManager {
    private ProductRepo productRepo;

    public ProductManager(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Optional<Product> findById(Long id){
        return productRepo.findById(id);
    }

    public Iterable<Product> findAll(){
        return productRepo.findAll();
    }

    public Product save(Product product){
        return productRepo.save(product);
    }

    public void deleteById(Long id){
        productRepo.deleteById(id);
    }
}
