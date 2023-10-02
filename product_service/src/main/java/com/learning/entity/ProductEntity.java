package com.learning.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.learning.enums.Category;
import com.learning.enums.ProductStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long productId;
	public String productName;
	public String description;
	public Long productPrice;
	public Long quantity;
	@Enumerated(EnumType.STRING)
	public ProductStatus productStatus;
	public Long supplierId;
	@Enumerated(EnumType.STRING)
	public Category category;

}
