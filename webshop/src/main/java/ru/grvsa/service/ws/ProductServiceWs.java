package ru.grvsa.service.ws;

import ru.grvsa.persistence.Category;
import ru.grvsa.persistence.Product;

import javax.ejb.Local;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@Local
@WebService

public interface ProductServiceWs {
    @WebMethod
    List<Product> findAllProducts();

    @WebMethod
    void insert(Product product);

    @WebMethod
    Product findProductById(long id);

    @WebMethod
    Product findProductByName(String name);

    @WebMethod
    List<Product> findProductByCategory(Category category);

    @WebMethod
    void deleteById(long id);

    @WebMethod
    void delete(Product product);

}
