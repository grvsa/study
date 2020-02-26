package ru.grvsa.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.grvsa.demo.entities.Product;
import ru.grvsa.demo.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> findAllByPriceBetween(double min, double max){
        return productRepository.findAllByPriceBetween(min,max);
    }

    public Product findProductMinPrice(){
        return productRepository.findAllOrderByPriceMin().get(0);
    }

    public Product findProductMaxPrice(){
        return productRepository.findAllOrderByPriceMax().get(0);
    }
}
