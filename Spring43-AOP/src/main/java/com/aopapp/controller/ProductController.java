package com.aopapp.controller;


import com.aopapp.entity.Product;
import com.aopapp.entity.ResponseWrapper;
import com.aopapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id){
        return ResponseEntity.ok(productService.getProduct(id));

    }

    @GetMapping
    public List<Product> getProducts(){


        List<Product>list =productService.getProducts();



        return list;
    }

    @PostMapping
    public ResponseEntity<List<Product>>createProduct(@RequestBody Product product){

        List<Product> set = productService.createProduct(product);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Version", "V1.0")
                .header("Operation", "Create")
                .body(set);

    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<List<Product>>deleteProduct(@PathVariable("id") Long id){
        HttpHeaders responseHttpHeaders = new HttpHeaders();
        responseHttpHeaders.set("Version", "moses.v1");
        responseHttpHeaders.set("Operation", "Delete");
        List<Product>list = productService.delete(id);

        return new ResponseEntity<>(list, responseHttpHeaders, HttpStatus.OK);
    }

    @PutMapping (value="/{id}")
    public ResponseEntity<List<Product>>updateProduct(@PathVariable("id") Long id, @RequestBody Product product){

        MultiValueMap<String, String>map = new LinkedMultiValueMap<>();
        map.add("Version", "V1.0");
        map.add("Operation", "Update");

        List<Product>list = productService.updateProduct(id, product);
        return new ResponseEntity<>(list, map, HttpStatus.OK);
    }

    @GetMapping("/read")
    public ResponseEntity<ResponseWrapper>readAllProducts(){
        return ResponseEntity
                .ok(new ResponseWrapper("Product Successfully retrieved", productService.getProducts()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseWrapper>deleteProduct1(@PathVariable("id") Long id){
        return ResponseEntity.ok(new ResponseWrapper(("Product Successfully Deleted!")));
    }

    @DeleteMapping("/delete1/{id}")
    public ResponseEntity<ResponseWrapper>deleteProduct2(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseWrapper("Product Successfully Deleted!"));

    }

}
