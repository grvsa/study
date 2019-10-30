package ru.grvsa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grvsa.persistence.Product;
import ru.grvsa.persistence.WebShopRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class ProductBean implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(ProductBean.class);

    @Inject
    private WebShopRepository webShopRepository;
    private Product product;

    private List<Product> productList;

    public void loadProduct(ComponentSystemEvent componentSystemEvent) {
        this.productList = webShopRepository.findAllProducts();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public List<Product> getAllProduct(){
        return productList;
    }

    public String createProduct(){
        this.product = new Product();
        return "/product.xhtml?faces-redirect=true";
    }

    public String saveProduct(){
        if (product.getProductid() == null){
            webShopRepository.insert(product);
        }else{
            webShopRepository.update(product);
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public void deleteProduct(Product product){
        webShopRepository.delete(product);
    }

    public String editProduct(Product product){
        this.product = product;
        return "/product.xhtml?faces-redirect=true";
    }
}
