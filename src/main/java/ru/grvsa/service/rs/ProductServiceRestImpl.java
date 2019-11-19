package ru.grvsa.service.rs;

import ru.grvsa.persistence.Category;
import ru.grvsa.persistence.Product;
import ru.grvsa.persistence.WebShopRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
public class ProductServiceRestImpl implements ProductServiceRest {
    @EJB
    WebShopRepository webShopRepository;

    @Override
    public List<Product> findAllProducts() {
        List<Product> l = webShopRepository.findAllProducts();
        if (!l.isEmpty()){
            return l;
        }
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @Override
    public Product findProductById(long id) {
        Product p = webShopRepository.findProductById(id);
        if (p != null){
            return p;
        }
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @Override
    public Product findProductByName(String name) {
        return findAllProducts().stream()
                .filter(p -> p.getProductName().equals(name))
                .findFirst()
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

    @Override
    public Product findProductByCategory(long id) {
        return findAllProducts().stream()
                .filter(p -> p.getProductCategory().getCategoryid() == id)
                .findFirst()
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

    @Override
    public void delete(long id) {
        Product p = webShopRepository.findProductById(id);
        if (p != null){
            webShopRepository.delete(p);
        }else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    @Override
    public void insert(String name, long id, String description, double price, long qty) {
        Category c = webShopRepository.findCategoryById(id);
        if (c != null){
            webShopRepository.insert(new Product(null,name,c,description,price,qty));
        }else{
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
}
