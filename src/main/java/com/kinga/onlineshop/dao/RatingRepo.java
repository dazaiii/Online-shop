package com.kinga.onlineshop.dao;

import com.kinga.onlineshop.dao.entity.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepo extends CrudRepository<Rating, Long> {
}
