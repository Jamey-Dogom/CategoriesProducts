package com.jd.catsprods.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jd.catsprods.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

	List<Category> findByNameNotIn(List<String> names);
}
