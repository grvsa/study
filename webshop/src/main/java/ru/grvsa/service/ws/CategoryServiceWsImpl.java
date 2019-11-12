package ru.grvsa.service.ws;

import ru.grvsa.persistence.Category;
import ru.grvsa.persistence.WebShopRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.List;

@Stateless
@WebService(endpointInterface = "ru.grvsa.service.ws.CategoryServiceWs", serviceName = "CategoryService")
public class CategoryServiceWsImpl {

    @EJB
    private WebShopRepository webShopRepository;


    public List<Category> findAllCategories(){
        return webShopRepository.findAllCategories();
    }


    public void insert(Category category){
        webShopRepository.insert(category);
    }


    public Category findCategoryById(long id){
        return webShopRepository.findCategoryById(id);
    }


    public void delete(Category category){
        webShopRepository.delete(category);
    }
}
