package com.aopapp.implementation;



import com.aopapp.entity.Product;
import com.aopapp.repository.ProductRepository;
import com.aopapp.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {


    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {

        return this.productRepository.findAll();
    }

    @Override
    public List<Product> delete(Long id) {
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    @Override
    public List<Product> updateProduct(Long id, Product product) {
        Product pd = productRepository.findById(id).get();
        pd.setName(product.getName());
        productRepository.save(pd);
        return productRepository.findAll();
    }

    @Override
    public List<Product> createProduct(Product product) {
        productRepository.save(product);
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).get();
    }
}
