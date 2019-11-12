package ru.grvsa;

import ru.grvsa.service.ws.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

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

public class WsClient {
    public static BufferedReader reader;

    public static void main(String[] args) throws IOException {
        URL url = new URL("http://localhost:8080/webshop/ProductService/ProductServiceWsImpl?WSDL");
        URL urlSe = new URL("http://localhost:8080/webshop/CategoryService/CategoryServiceWsImpl?WSDL");

        CategoryService categoryService = new CategoryService(urlSe);

        CategoryServiceWs categoryServiceWs = categoryService.getCategoryServiceWsImplPort();

        ProductService productService = new ProductService(url);

        ProductServiceWs productServiceWs = productService.getProductServiceWsImplPort();

        reader = new BufferedReader(new InputStreamReader(System.in));

        String str = "";

        while (!str.equals("exit")){
            str = reader.readLine();
            if (str.startsWith("product")){
                product(productServiceWs, categoryServiceWs);
            }else if (str.startsWith("category")){
                category(productServiceWs, categoryServiceWs);
            }
        }
    }

    public static void product(ProductServiceWs productServiceWs, CategoryServiceWs categoryServiceWs) throws IOException {
        String str = reader.readLine();
        if (str.equals("all")){
            productServiceWs.findAllProducts().forEach(p -> printProduct(p));
        }else if (str.startsWith("id")){
            String[] tokens = str.split(" ");
            printProduct(productServiceWs.findProductById(Long.parseLong(tokens[1])));
        }else if(str.startsWith("name")){
            String[] tokens = str.split(" ");
            printProduct(productServiceWs.findProductByName(tokens[1]));
        }else if (str.startsWith("category")){
            String[] tokens = str.split(" ");
            Category category = categoryServiceWs.findCategoryById(Long.parseLong(tokens[1]));
            productServiceWs.findProductByCategory(category).forEach(p -> printProduct(p));
        }else if (str.startsWith("delete")){
            String[] tokens = str.split(" ");
            productServiceWs.deleteById(Long.parseLong(tokens[1]));
        }else if (str.startsWith("add")){
            String[] tokens = str.split(" ");
            Category category = categoryServiceWs.findCategoryById(Long.parseLong(tokens[2]));
            Product p = new Product();
            p.setProductCategory(category);
            p.setProductName(tokens[1]);
            p.setProductDescription(tokens[3]);
            p.setProductPrice(Double.parseDouble(tokens[4]));
            p.setProductQty(Long.parseLong(tokens[5]));

            productServiceWs.insert(p);
        }
    }

    public static void category(ProductServiceWs productServiceWs, CategoryServiceWs categoryServiceWs) throws IOException {
        String str = reader.readLine();
        if (str.equals("all")){
            categoryServiceWs.findAllCategories().forEach(p -> printCategory(p));
        }else if (str.startsWith("id")){
            String[] tokens = str.split(" ");
            printCategory(categoryServiceWs.findCategoryById(Long.parseLong(tokens[1])));
        }else if (str.startsWith("delete")){
            String[] tokens = str.split(" ");
            Category c = categoryServiceWs.findCategoryById(Long.parseLong(tokens[1]));
            categoryServiceWs.delete(c);
        }else if (str.startsWith("add")){
            String[] tokens = str.split(" ");
            Category c = new Category();
            c.setCategoryName(tokens[1]);
            categoryServiceWs.insert(c);
        }
    }

    public static void printProduct(Product p){
        System.out.print("Id:" + p.getProductid());
        System.out.print(" Name:" + p.getProductName());
        System.out.print(" Category: " + p.getProductCategory().getCategoryid() + " Name:" + p.getProductCategory().getCategoryName());
        System.out.print(" Descr:" + p.getProductDescription());
        System.out.print(" Price:" + p.getProductPrice());
        System.out.println(" Qty:" + p.getProductQty());
    }

    public static void printCategory(Category p){
        System.out.print("Id:" + p.getCategoryid());
        System.out.println(" Name:" + p.getCategoryName());
    }
}
