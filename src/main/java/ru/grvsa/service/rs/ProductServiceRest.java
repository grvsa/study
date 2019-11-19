package ru.grvsa.service.rs;

import ru.grvsa.persistence.Product;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/*
    1. Разработать сервисы JAX-WS и JAX-RS, которые позволяют:
    a. Добавлять товар в БД;
    b. Удалять товар из БД;




    g. Получить товары по Id категории.

    2. К JAX-WS сервису написать клиента.
*/

@Local
@Path("products")
public interface ProductServiceRest {
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    List<Product> findAllProducts();

    @GET
    @Path("{id}/id")
    @Produces(MediaType.APPLICATION_JSON)
    Product findProductById(@PathParam("id") long id);

    @GET
    @Path("{name}/name")
    @Produces(MediaType.APPLICATION_JSON)
    Product findProductByName(@PathParam("name") String name);

    @GET
    @Path("{id}/category")
    @Produces(MediaType.APPLICATION_JSON)
    Product findProductByCategory(@PathParam("id") long id);

//    String productName, Category productCategory, String productDescription, Double productPrice, Long productQty
//
    @POST
    @Path("{name}/{categoryid}/{description}/{price}/{qty}/add")
    void insert(@PathParam("name") String name,
                @PathParam("categoryid") long id,
                @PathParam("description") String description,
                @PathParam("price") double price,
                @PathParam("qty") long qty);

    @DELETE
    @Path("{id}/delete")
    void delete(@PathParam("id") long id);
}
