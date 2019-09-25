package com.mini.mall.admin.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductRequestModel {

	@NotNull
	@NotBlank
	private String brand;

	@NotNull
	@NotBlank
	private String type;

	@NotNull
	@NotBlank
	private String catagory;

	@NotNull
	@NotEmpty
	private List<Integer> colors;

	@NotNull
	@NotEmpty
	private List<Integer> size;

}
