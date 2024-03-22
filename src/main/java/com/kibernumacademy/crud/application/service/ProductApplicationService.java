package com.kibernumacademy.crud.application.service;

import org.springframework.stereotype.Service;

import com.kibernumacademy.crud.application.dto.ProductDTO;
import com.kibernumacademy.crud.domain.model.Product;
import com.kibernumacademy.crud.domain.service.ProductService;

@Service
public class ProductApplicationService {
  
  private final ProductService productService;

  public ProductApplicationService(ProductService productService) {
    this.productService = productService;
  }
  
  public ProductDTO createProduct(ProductDTO productDTO) {
    Product product = convertToEntity(productDTO);
    Product saveProduct = productService.saveOrUpdateProduct(product);

    return convertToDTO(saveProduct);
  }

  private Product convertToEntity(ProductDTO productDTO) {
    return new Product(productDTO.getName(), productDTO.getPrice());
  }

  private ProductDTO convertToDTO(Product product) {
    return new ProductDTO(product.getId(), product.getName(), product.getPrice());
  }



  

}
