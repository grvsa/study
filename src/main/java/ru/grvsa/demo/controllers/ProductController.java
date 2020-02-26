package ru.grvsa.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.grvsa.demo.entities.Product;
import ru.grvsa.demo.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String shopPage(Model model) {
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("products", allProducts);
        return "index";
    }

    @GetMapping("/between")
    public String minPrice(Model model, @RequestParam("min") double min, @RequestParam("max") double max) {
        List<Product> products = productService.findAllByPriceBetween(min, max);
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/min")
    public String minPrice(Model model) {
        List<Product> minProduct = new ArrayList<>();
        minProduct.add(productService.findProductMinPrice());
        model.addAttribute("products", minProduct);
        return "index";
    }

    @GetMapping("/max")
    public String maxPrice(Model model) {
        List<Product> minProduct = new ArrayList<>();
        minProduct.add(productService.findProductMaxPrice());
        model.addAttribute("products", minProduct);
        return "index";
    }
}
