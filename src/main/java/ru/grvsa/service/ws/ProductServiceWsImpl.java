package ru.grvsa.service.ws;

import ru.grvsa.persistence.Category;
import ru.grvsa.persistence.Product;
import ru.grvsa.persistence.WebShopRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;
import java.util.List;
import java.util.stream.Collectors;

/*
    1. Разработать сервисы JAX-WS и JAX-RS, которые позволяют:
    a. Добавлять товар в БД;
    b. Удалять товар из БД;
    c. Добавлять категорию товара;
    d. Получить товар по Id;
    e. Получить товар по имени;
    f. Получить все товары;
    g. Получить товары по Id категории.

    2. К JAX-WS сервису написать клиента.
*/

@Stateless
@WebService(endpointInterface = "ru.grvsa.service.ws.ProductServiceWs", serviceName = "ProductService")
public class ProductServiceWsImpl {
    @EJB
    private WebShopRepository webShopRepository;

    public List<Product> findAllProducts(){
        return webShopRepository.findAllProducts();
    }

    public void insert(Product product){
        webShopRepository.insert(product);
    }

    public Product findProductById(long id){
        return webShopRepository.findProductById(id);
    }

    public Product findProductByName(String name){
        return webShopRepository.findAllProducts()
                .stream()
                .filter(p -> p.getProductName().equals(name))
                .findFirst()
                .orElseThrow(() -> new WebServiceException());
    }

    public List<Product> findProductByCategory(Category category){
        return webShopRepository.findAllProducts()
                .stream()
                .filter(p -> p.getProductCategory().getCategoryid() == category.getCategoryid())
                .collect(Collectors.toList());
    }

    public void deleteById(long id){
        webShopRepository.delete(webShopRepository.findAllProducts()
                .stream()
                .filter(p -> p.getProductid() == id)
                .findFirst()
                .orElseThrow(() -> new WebServiceException()));
    }

    public void delete(Product product){
        webShopRepository.delete(product);
    }

}
