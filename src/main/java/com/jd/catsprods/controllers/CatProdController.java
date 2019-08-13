package com.jd.catsprods.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jd.catsprods.models.Category;
import com.jd.catsprods.models.Product;
import com.jd.catsprods.services.CatProdService;

@Controller
public class CatProdController {
	@Autowired
	private CatProdService cpService;

	@RequestMapping("/products/new")
	public String productsNew() {
		return "new-product.jsp";
	}

	@RequestMapping("/categories/new")
	public String categoriesNew() {
		return "new-category.jsp";
	}

	@PostMapping("/create-category")
	public String createCategory(@RequestParam("name") String name) {
		Category cat = new Category();
		cat.setName(name);
		cpService.create(cat);
		return "redirect:/categories/new";
	}

	@PostMapping("/create-product")
	public String createProduct(@RequestParam("name") String name, @RequestParam("description") String description,
			@RequestParam("price") float price) {
		Product pro = new Product();
		pro.setName(name);
		pro.setDescription(description);
		pro.setPrice(price);
		cpService.create(pro);
		return "redirect:/products/new";
	}
	
	@RequestMapping("/products/{id}")
	public String showProduct(@PathVariable("id") Long id, Model model) {
		Product pro = cpService.getProductById(id);
		model.addAttribute("product", pro);
		model.addAttribute("nonCategories", cpService.nonCategories(pro));
		return "product.jsp";
	}
	
	@RequestMapping("/addCategory/{id}")
	public String addCategory(@PathVariable("id") Long id, @RequestParam("cat") Long catId) {
		Product pro = cpService.findById(id);
		List<Category> cats = pro.getCategories();
		cats.add(cpService.findCatById(catId));
		pro.setCategories(cats);
		cpService.create(pro);
		return "redirect:/products/{id}";
	}
	
	@RequestMapping("/categories/{id}")
	public String showCategory(@PathVariable("id") Long id, Model model) {
		Category cat = cpService.getCategoryById(id);
		model.addAttribute("category", cat);
		model.addAttribute("products", cpService.nonProducts(cat));
		return "category.jsp";
	}
	
	@RequestMapping("/addProduct/{id}")
	public String addProduct(@PathVariable("id") Long id, @RequestParam("pro") Long prodId) {
		Category cat = cpService.findCatById(id);
		List<Product> pros = cat.getProducts();
		pros.add(cpService.findById(prodId));
		cat.setProducts(pros);
		cpService.create(cat);
		return "redirect:/categories/{id}";
	}
	
}
