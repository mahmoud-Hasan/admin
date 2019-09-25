package com.mini.mall.admin.repo;

import org.springframework.data.repository.CrudRepository;

import com.mini.mall.admin.model.ProductModel;

public interface ProductsRepo extends CrudRepository<ProductModel, Integer> {

}
