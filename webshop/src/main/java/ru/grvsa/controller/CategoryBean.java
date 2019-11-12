package ru.grvsa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grvsa.persistence.Category;
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
public class CategoryBean implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(CategoryBean.class);

    @Inject
    private WebShopRepository webShopRepository;
    private Category category;
    private List<Category> categoryList;

    public void loadCategory(ComponentSystemEvent componentSystemEvent) {
        this.categoryList = webShopRepository.findAllCategories();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public List<Category> getAllCategories(){
        return categoryList;
    }

    public String createCategory(){
        this.category = new Category();
        return "/category.xhtml?faces-redirect=true";
    }

    public String saveCategory(){
        if (category.getCategoryid() == null){
            webShopRepository.insert(category);
        }else{
            webShopRepository.update(category);
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public void deleteCategory(Category category){
        webShopRepository.delete(category);
    }

    public String editCategory(Category category){
        this.category = category;
        return "/category.xhtml?faces-redirect=true";
    }
}
