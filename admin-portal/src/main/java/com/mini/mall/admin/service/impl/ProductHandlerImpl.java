package com.mini.mall.admin.service.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import com.mini.mall.admin.model.ProductRequestModel;
import com.mini.mall.admin.service.ProductHandler;
import com.mini.mall.admin.service.StorageService;

@Service
@Validated
public class ProductHandlerImpl implements ProductHandler{

	@Autowired
	private StorageService storageService;
	
	@Override
	public void addProductHandler(MultipartFile mainImage,List<MultipartFile> listOfImages,@Valid ProductRequestModel productRequestModel) {

		//get the brand from the token " for security reason
		//create the main path from model brand path
		String brand = "D:";
		
		String category = productRequestModel.getCatagory();
		String bath = brand+"\\"+category;

		//call storage service
		storageService.store(mainImage, listOfImages, bath );
		
		//		if success call persistence 
		
	}

	
}
