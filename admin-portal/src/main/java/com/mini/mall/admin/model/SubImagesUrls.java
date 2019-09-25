package com.mini.mall.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity(name = "imagesUrls")
@Table(uniqueConstraints = @UniqueConstraint(columnNames= {"url"}))
public class SubImagesUrls {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "url",length = 225)
	private String url;
	@ManyToOne
	@JoinColumn(name = "book_category_id")
	private ProductModel productModel;

}
