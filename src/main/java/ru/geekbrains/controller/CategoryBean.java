package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ShopRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@SessionScoped
public class CategoryBean implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(CategoryBean.class);

    @Inject
    private ShopRepository shopRepository;
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getAllCategories() throws SQLException {
        return shopRepository.findAllCategories();
    }

    public String createCategory(){
        this.category = new Category();
        return "/category.xhtml?faces-redirect=true";
    }

    public String saveCategory() throws SQLException {
        if (category.getId() == null){
            shopRepository.insert(category);
        }else{
            shopRepository.update(category);
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public void deleteCategory(Category category) throws SQLException {
        for (Product product :
                shopRepository.findAllProductsByCategory(category)) {
            System.out.println(product);
            shopRepository.delete(product);
        }
        shopRepository.delete(category);
    }

    public String editCategory(Category category){
        this.category = category;
        return "/category.xhtml?faces-redirect=true";
    }
}
