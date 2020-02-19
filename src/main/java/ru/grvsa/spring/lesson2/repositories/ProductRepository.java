package ru.grvsa.spring.lesson2.repositories;

import ru.grvsa.spring.lesson2.entities.Product;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductRepository {
    private List<Product> list;

    @PostConstruct
    public void postConstruct(){
        this.list = new ArrayList<Product>();
        list.add(new Product(1L, "Молоко", 79.5));
        list.add(new Product(2L, "Хлеб", 29.55));
        list.add(new Product(3L, "Сыр", 179.59));
        list.add(new Product(4L, "Пиво", 79.5));
        list.add(new Product(5L, "Сигареты", 209.5));
    }

    public List<Product> findAllProducts(){
        return list;
    }

    public Product findProductById(Long id){
        Product result = null;
        for (Product product :
                list) {
            if (product.getId() == id) {
                result = product;
                break;
            }
        }
        return result;
    }

    public void addProduct(Product product){
        Long maxID = 0L;
        for (Product p :
                list) {
            if (p.getId() > maxID){
                maxID = p.getId();
            }
        }
        product.setId(++maxID);
        list.add(product);
    }
}
