package com.learning.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.ProductEntity;
import com.learning.enums.Status;
import com.learning.globalException.ProductResponseException;
import com.learning.models.ProductRequest;
import com.learning.models.ProductResponse;
import com.learning.repository.ProductRepository;
import com.learning.utility.Converter;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private Converter converter;

	public ProductResponse createProduct(ProductRequest productRequest) {
		ProductResponse productResponse = null;
		if (Objects.nonNull(productRequest)) {
			ProductEntity productEntity = converter.requestToEntity(productRequest);
			productEntity = productRepository.save(productEntity);
			productResponse = converter.entityToResponse(productEntity);
		}
		return productResponse;
	}

	public ProductResponse findByProductId(Long productId) {
		Optional<ProductEntity> productEntityOptional = productRepository.findByProductId(productId);
		if (productEntityOptional.isPresent()) {
			ProductEntity productEntity = productEntityOptional.get();
			return converter.entityToResponse(productEntity);
		} else {
			throw new ProductResponseException("Product not found for ID: " + productId);
		}
	}
	
//	public ProductResponse findProductById(Long id) {
//		return productRepository.findById(id).map(converter::entityToResponse)
//				.orElseThrow(() -> new ProductResponseException("Product Not Found"));
//	}

	public ProductResponse updateProduct(Long productId, ProductRequest productRequest) {
		return productRepository.findById(productId).map(productEntity -> {
			productEntity = converter.updatEntity(productRequest, productEntity);
			productEntity = productRepository.save(productEntity);
			return converter.entityToResponse(productEntity);
		}).orElseThrow(() -> new ProductResponseException("Product Not Found"));
	}

	public Status deleteById(Long productId) {
		Optional<ProductEntity> optionalEntity = productRepository.findById(productId);
		if (optionalEntity.isPresent()) {
			productRepository.deleteById(productId);
			return Status.SUCCESS;
		}
		return Status.FAILED;

	}

}
