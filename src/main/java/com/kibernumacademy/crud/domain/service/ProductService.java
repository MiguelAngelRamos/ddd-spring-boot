package com.kibernumacademy.crud.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kibernumacademy.crud.domain.model.Product;
import com.kibernumacademy.crud.domain.repository.ProductRepository;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product saveOrUpdateProduct(Product product) {
    return productRepository.save(product);
  }

  public Optional<Product> getProductById(Long id) {
    return productRepository.findById(id);
  }

  public void deleteProductById(Long id) {
    productRepository.deleteById(id);
  }

  public List<Product> listAllProducts() {
    return productRepository.findAll();
  }


}
