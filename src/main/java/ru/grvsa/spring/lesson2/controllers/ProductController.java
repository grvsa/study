package ru.grvsa.spring.lesson2.controllers;

import org.springframework.web.bind.annotation.*;
import ru.grvsa.spring.lesson2.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.grvsa.spring.lesson2.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService){
        this.productService = productService;
    }

    @RequestMapping("/showall")
    public String showAllProducts(Model uiModel){
        List<Product> productList = productService.getAllProducts();
        uiModel.addAttribute("products", productList);
        return "products";
    }

    @RequestMapping("/product/{id}")
    @ResponseBody
    public Product showProductById(@PathVariable("id") Long id){
        Product product = productService.getProductById(id);
        if (product == null){
            product = new Product();
        }
        return product;
    }

    @RequestMapping("/productform")
    public String productForm(Model uiModel){
        Product product = new Product();
        uiModel.addAttribute("product", product);
        return "addproductform";
    }

    @RequestMapping(path = "/addproduct")
    public String addProduct(@ModelAttribute("product") Product product){
        productService.addProduct(product);
        return "forward:/showall";
    }
}
