package ru.grvsa.spring.lesson2.service;

import ru.grvsa.spring.lesson2.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.grvsa.spring.lesson2.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public ProductService() {
    }

    public List<Product> getAllProducts(){
        return productRepository.findAllProducts();
    }

    public Product getProductById(Long id){
        return productRepository.findProductById(id);
    }

    public void addProduct(Product product){
        productRepository.addProduct(product);
    }
}
