package ru.grvsa.service.ws;

import ru.grvsa.persistence.Category;
import ru.grvsa.persistence.Product;
import ru.grvsa.persistence.WebShopRepository;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@Local
@WebService
public interface CategoryServiceWs {


    @WebMethod
    List<Category> findAllCategories();

    @WebMethod
    void insert(Category category);

    @WebMethod
    Category findCategoryById(long id);

    @WebMethod
    void delete(Category category);
}
