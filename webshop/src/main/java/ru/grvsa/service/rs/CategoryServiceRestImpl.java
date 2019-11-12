package ru.grvsa.service.rs;

import ru.grvsa.persistence.Category;
import ru.grvsa.persistence.WebShopRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

/*
    1. Разработать сервисы JAX-WS и JAX-RS, которые позволяют:
    a. Добавлять товар в БД;
    b. Удалять товар из БД;
    d. Получить товар по Id;
    e. Получить товар по имени;
    f. Получить все товары;
        g. Получить товары по Id категории.
    c. Добавлять категорию товара;



    2. К JAX-WS сервису написать клиента.
*/


@Stateless
public class CategoryServiceRestImpl implements CategoryServiceRest{
    @EJB
    WebShopRepository webShopRepository;

    @Override
    public List<Category> findAllCategories() {
        List<Category> l =  webShopRepository.findAllCategories();
        if (!l.isEmpty()){
            return l;
        }
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @Override
    public Category findCategoryById(long id) {
        Category c = webShopRepository.findCategoryById(id);
        if (c != null){
            return c;
        }
        throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @Override
    public void insert(String name) {
        webShopRepository.insert(new Category(null, name));
    }

    @Override
    public void delete(long id) {
        Category c = webShopRepository.findCategoryById(id);
        if (c == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        webShopRepository.delete(c);
    }
}
