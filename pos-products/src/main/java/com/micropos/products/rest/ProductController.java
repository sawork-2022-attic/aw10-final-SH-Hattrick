package com.micropos.products.rest;

import com.micropos.products.api.ProductsApi;
import com.micropos.products.dto.ProductDto;
import com.micropos.products.mapper.ProductMapper;
import com.micropos.products.model.Product;
import com.micropos.products.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@EnableDiscoveryClient
public class ProductController implements ProductsApi {

    private final ProductMapper productMapper;

    private final ProductService productService;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<ProductDto>> listProducts() {
        List<ProductDto> products = new ArrayList<>(productMapper.toProductsDto(this.productService.products()));
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "{productId}")
    public ResponseEntity<ProductDto> showProductById(String productId) {
        Product p = this.productService.getProduct(productId);
        if (p == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ProductDto product = productMapper.toProductDto(p);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
