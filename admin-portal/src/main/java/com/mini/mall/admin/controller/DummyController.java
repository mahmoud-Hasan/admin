package com.mini.mall.admin.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.mall.admin.model.ProductModel;
import com.mini.mall.admin.model.SubImagesUrls;
import com.mini.mall.admin.repo.ProductsRepo;

@RestController
public class DummyController {

	@Autowired
	private ProductsRepo productsRepo;
	
	@GetMapping("/get")
	public String get() {
		ProductModel pm = new ProductModel();
		SubImagesUrls subIm= new SubImagesUrls();
		subIm.setUrl("https://stackoverflow.com/questions/56526131/why-unique-true-is-not-working-in-hibernate-entity");
		
		
		Set<SubImagesUrls> subSet= new HashSet<SubImagesUrls>();
		subSet.add(subIm);
		
		SubImagesUrls subIm1= new SubImagesUrls();
		subIm1.setUrl("loooooooool");
		subSet.add(subIm1);
		
		SubImagesUrls subIm2= new SubImagesUrls();
		subIm2.setUrl("iooooooooooooooo");	
		subSet.add(subIm2);

		pm.setBrand("test Brand");
		pm.setCategory("test cat");
		pm.setType("test type");
		pm.setProductCode("codeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		pm.setUrls(subSet);
		productsRepo.save(pm);
		
		return "good";
	}
	
}
