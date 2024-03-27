package com.kibernumacademy.crud.infrastructure.web.controller;

import com.kibernumacademy.crud.application.dto.ProductDTO;
import com.kibernumacademy.crud.application.service.ProductApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path="/products")
public class ProductController {
  private final ProductApplicationService productApplicationService;

  @GetMapping
  public ResponseEntity<List<ProductDTO>> getAllProducts() {
    List<ProductDTO> products = productApplicationService.listAllProducts();
    return new ResponseEntity<>(products, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
    return productApplicationService.getProductById(id)
            .map(productDTO -> new ResponseEntity<>(productDTO, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
    ProductDTO savedProductDTO = productApplicationService.createProduct(productDTO);
    return new ResponseEntity<>(savedProductDTO, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
    ProductDTO updatedProductDTO = productApplicationService.updateProduct(id, productDTO);
    return ResponseEntity.ok(updatedProductDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
    productApplicationService.deleteProductById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
