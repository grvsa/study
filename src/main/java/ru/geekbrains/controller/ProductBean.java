package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ShopRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@Named
public class ProductBean implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(ProductBean.class);

    @Inject
    private ShopRepository shopRepository;
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getAllProduct() throws SQLException {
        return shopRepository.findAllProducts();
    }

    public String createProduct(){
        this.product = new Product();
        return "/product.xhtml?faces-redirect=true";
    }

    public String saveProduct() throws SQLException {
        if (product.getId() == null){
            shopRepository.insert(product);
        }else{
            shopRepository.update(product);
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public void deleteProduct(Product product) throws SQLException {
        shopRepository.delete(product);
    }

    public String editProduct(Product product){
        this.product = product;
        return "/product.xhtml?faces-redirect=true";
    }
}
