package ru.grvsa.service.rs;

import ru.grvsa.persistence.Category;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

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

@Local
@Path("categories")
public interface CategoryServiceRest {
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    List<Category> findAllCategories();

    @GET
    @Path("{id}/id")
    @Produces(MediaType.APPLICATION_JSON)
    Category findCategoryById(@PathParam("id")long id);

    @POST
    @Path("{name}/add")
    void insert(@PathParam("name") String name);

    @DELETE
    @Path("{id}/delete")
    void delete(@PathParam("id") long id);
}
