package com.jd.catsprods.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.catsprods.models.Category;
import com.jd.catsprods.models.Product;
import com.jd.catsprods.repositories.CategoryRepository;
import com.jd.catsprods.repositories.ProductRepository;

@Service
public class CatProdService {

	@Autowired
	private ProductRepository proRepo;

	@Autowired
	private CategoryRepository catRepo;

	public Product create(Product p) {
		return proRepo.save(p);
	}

	public Category create(Category c) {
		return catRepo.save(c);
	}

	public Product getProductById(Long id) {
		Optional<Product> pro = proRepo.findById(id);
		return pro.get();

	}

	public List<Category> getAllCategories() {
		return (List<Category>) catRepo.findAll();

	}

	public Product findById(Long id) {
		return proRepo.findById(id).get();
	}

	public Category findCatById(Long catId) {
		return catRepo.findById(catId).get();
	}

	public Category getCategoryById(Long id) {
		return catRepo.findById(id).get();
	}

	public List<Product> getAllProducts() {
		return (List<Product>) proRepo.findAll();
	}

	public List<Category> nonCategories(Product p) {
		List<Category> currentCats = p.getCategories();
		List<String> currentCatsNames = new ArrayList<String>();

		if (currentCats.size() == 0) {
			currentCatsNames.add("");
		} else {

			for (Category cat : currentCats) {
				currentCatsNames.add(cat.getName());
			}

		}
		return catRepo.findByNameNotIn(currentCatsNames);

	}
	
	public List<Product> nonProducts(Category c) {
		List<Product> currentProds = c.getProducts();
		List<String> currentProdsNames = new ArrayList<String>();

		if (currentProds.size() == 0) {
			currentProdsNames.add("");
		} else {

			for (Product pro : currentProds) {
				currentProdsNames.add(pro.getName());
			}

		}
		return proRepo.findByNameNotIn(currentProdsNames);

	}
}
