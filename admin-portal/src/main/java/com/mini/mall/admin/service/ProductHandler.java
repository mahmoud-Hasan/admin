package com.mini.mall.admin.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.mini.mall.admin.model.ProductRequestModel;

public interface ProductHandler {
	
	public void addProductHandler(MultipartFile mainImage,List<MultipartFile> listOfImages,@Valid ProductRequestModel productRequestModel);

}
