package com.mini.mall.admin.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mini.mall.admin.model.ProductRequestModel;
import com.mini.mall.admin.service.ProductHandler;
import com.mini.mall.admin.service.StorageService;


@RestController
@RequestMapping("/image")
public class ImageUploadController {

	@Autowired
	private StorageService storgaeService;
	
	@Autowired
	private ProductHandler productHandler;
	
	@PostMapping("/upload")
	public String uploadImage(@RequestParam("mainImage") MultipartFile mainImage,
			 @RequestParam(name ="imagesList") List<MultipartFile> listOfImages,
			 @RequestParam(name ="brand") String brand,
			 @RequestParam(name ="type") String type, 
			 @RequestParam(name ="catagory") String catagory, 
			 @RequestParam(name ="colors") List<Integer> colors, 
			 @RequestParam(name ="sizes") List<Integer> size) {
		
		productHandler.addProductHandler(mainImage, listOfImages, getProductRequest(brand,type,catagory,colors,size));


    return "lol";
	}
	
	private ProductRequestModel getProductRequest(String brand, String type, String catagory, List<Integer> colors,
			List<Integer> size) {
		ProductRequestModel model = new ProductRequestModel();
		model.setBrand(brand);
		model.setType(type);
		model.setCatagory(catagory);
		model.setColors(colors);
		model.setSize(size);
		
		return model;
	}

	@GetMapping
	public Stream<Path> get(){
		
		return storgaeService.loadAll();
		
	}
	
	
	@DeleteMapping("/del")
	public String delete(@RequestParam("imageName") String imageName) throws IOException {
		storgaeService.deleteAll(imageName);
		return "done";
	}
	
	
}
