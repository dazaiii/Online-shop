package com.kinga.onlineshop.api;

import com.kinga.onlineshop.dao.entity.Category;
import com.kinga.onlineshop.dao.entity.Product;
import com.kinga.onlineshop.dao.entity.Rating;
import com.kinga.onlineshop.dao.entity.User;
import com.kinga.onlineshop.dto.ProductDto;
import com.kinga.onlineshop.dto.RatingDto;
import com.kinga.onlineshop.manager.CategoryManager;
import com.kinga.onlineshop.manager.ProductManager;
import com.kinga.onlineshop.manager.RatingManager;
import com.kinga.onlineshop.manager.UserManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductApi {
    ProductManager productManager;
    RatingManager ratingManager;
    UserManager userManager;
    CategoryManager categoryManager;
    ModelMapper modelMapper;

    @Autowired
    public ProductApi(ProductManager productManager, RatingManager ratingManager, UserManager userManager, CategoryManager categoryManager, ModelMapper modelMapper) {
        this.productManager = productManager;
        this.ratingManager = ratingManager;
        this.userManager = userManager;
        this.categoryManager = categoryManager;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<ProductDto> getProducts(){
        Iterable<Product> iterable = productManager.findAll();
        List<Product> list = new ArrayList<>();
        iterable.forEach(list::add);
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ProductDto getById(@PathVariable Long id){
        Optional<Product> optional = productManager.findById(id);
        ProductDto productDto = convertToDto(optional.get());
        return productDto;
    }

    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto productDto){
        Product product = convertToEntity(productDto);
        Product productCreated = productManager.save(product);
        return convertToDto(productCreated);
    }

    @PutMapping("{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto){
        productDto.setIdProduct(id);
        Product product = convertToEntity(productDto);
        Product productCreated = productManager.save(product);
        return convertToDto(productCreated);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id){
        productManager.deleteById(id);
    }


    @GetMapping("{id}/ratings")
    public List<RatingDto> getRatingById(@PathVariable Long id) {
        Optional<Product> optional = productManager.findById(id);
        List<Rating> list = ratingManager.findByProduct(optional.get());
        return list.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("{id}/ratings")
    public RatingDto addRating(@PathVariable Long id, @RequestBody RatingDto ratingDto){
        Optional<Product> optional = productManager.findById(id);
        Rating rating = convertToEntity(ratingDto);
        rating.setProduct(optional.get());
        Rating ratingCreated = ratingManager.save(rating);
        return convertToDto(ratingCreated);
    }

    @PutMapping("{idProduct}/ratings/{idRating}")
    public RatingDto updateRating(@PathVariable Long idProduct, @PathVariable Long idRating, @RequestBody RatingDto ratingDto){
        Optional<Product> optional = productManager.findById(idProduct);
        ratingDto.setIdRating(idRating);
        Rating rating = convertToEntity(ratingDto);
        rating.setProduct(optional.get());
        Rating ratingCreated = ratingManager.save(rating);
        return convertToDto(ratingCreated);
    }

    @DeleteMapping("{idProduct}/ratings/{idRating}")
    public void deleteRating(@PathVariable Long idProduct, @PathVariable Long idRating){
        ratingManager.deleteById(idRating);
    }

    private ProductDto convertToDto(Product product){
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;
    }

    private Product convertToEntity(ProductDto productDto){
        Product product = modelMapper.map(productDto, Product.class);
        if(productDto.getIdProduct() == null){
            Optional<Category> category = categoryManager.findById(productDto.getIdCategory());
            product.setCategory(category.get());
        }
        return product;
    }

    private RatingDto convertToDto(Rating rating){
        RatingDto ratingDto = modelMapper.map(rating, RatingDto.class);
        return ratingDto;
    }

    private Rating convertToEntity(RatingDto ratingDto){
        Rating rating = modelMapper.map(ratingDto, Rating.class);
        Optional<User> user = userManager.findById(ratingDto.getIdUser());
        rating.setUser(user.get());
        Optional<Product> product = productManager.findById(ratingDto.getIdProduct());
        rating.setProduct(product.get());
        return rating;
    }
}
