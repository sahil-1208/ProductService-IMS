package com.learning.globalException;

public class ProductResponseException extends RuntimeException {

	public ProductResponseException(String message) {
		super(message);
	}
}
