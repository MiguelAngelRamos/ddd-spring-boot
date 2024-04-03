package com.kibernumacademy.crud.application.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.kibernumacademy.crud.application.dto.ProductDTO;
import com.kibernumacademy.crud.domain.model.Product;
import com.kibernumacademy.crud.domain.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

  public Optional<ProductDTO> getProductById(Long id) {
    return productService.getProductById(id).map(this::convertToDTO);
  }

  public List<ProductDTO> listAllProducts() {
    return productService.listAllProducts().stream().map(this::convertToDTO).collect(Collectors.toList());
  }

  // http://localhost:8080/api/10
  public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
    Optional<Product> existingProduct = productService.getProductById(id);

    if(!existingProduct.isPresent()) throw new EntityNotFoundException("Product not with ID: " +  id);
  
      Product productToUpdate = existingProduct.get();
      productToUpdate.setName(productDTO.getName());
      productToUpdate.setPrice(productDTO.getPrice());
      Product updatedProduct = productService.saveOrUpdateProduct(productToUpdate);
      return convertToDTO(updatedProduct);
   
  }

  public void deleteProductById(Long id) {
    productService.deleteProductById(id);
  }
  
  // Transaccion de datos entre capas
  private Product convertToEntity(ProductDTO productDTO) {
    return new Product(productDTO.getName(), productDTO.getPrice());
  }

  private ProductDTO convertToDTO(Product product) {
    return new ProductDTO(product.getId(), product.getName(), product.getPrice());
  }



  

}
