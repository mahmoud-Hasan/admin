package com.mini.mall.admin.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
//@Table(uniqueConstraints = @UniqueConstraint(columnNames= {"product_code"}))
public class ProductModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String brand;
	private String category;
	private String type;
	@OneToMany(mappedBy = "productModel", cascade = CascadeType.ALL)
	private Set<SubImagesUrls> urls;
//	private List<Integer> colors;
	@Column(name = "product_code",length = 225 , unique = true)
	private String productCode;
	private String main_url;

}