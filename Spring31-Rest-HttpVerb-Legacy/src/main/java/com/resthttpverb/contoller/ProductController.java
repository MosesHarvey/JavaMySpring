package com.resthttpverb.contoller;

import com.resthttpverb.entity.Product;
import com.resthttpverb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value="/products/{id}", method = RequestMethod.GET)
    public @ResponseBody Product getProduct(@PathVariable("id") long id){
        return productService.getProduct(id);

    }

    @RequestMapping(value="/products")
    public @ResponseBody List<Product> getProducts(){
        return productService.getProducts();
    }

}
