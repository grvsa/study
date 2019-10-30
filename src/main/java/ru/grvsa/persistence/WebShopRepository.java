package ru.grvsa.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Named
public class WebShopRepository {
    private static final Logger logger = LoggerFactory.getLogger(WebShopRepository.class);

    /*
           CREATE SCHEMA `webshop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `products` (
  `productid` int(11) NOT NULL AUTO_INCREMENT,
  `productName` varchar(45) NOT NULL,
  `productDescription` varchar(45) NOT NULL,
  `productPrice` double NOT NULL,
  `productQty` int(11) NOT NULL,
  `categoryid` int(11) NOT NULL,
  PRIMARY KEY (`productid`),
  KEY `fk_products_1_idx` (`categoryid`),
  CONSTRAINT `fk_products_1` FOREIGN KEY (`categoryid`) REFERENCES `categories` (`categoryid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8

CREATE TABLE `categories` (
  `categoryid` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(45) NOT NULL,
  PRIMARY KEY (`categoryid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8

    */


    @PersistenceContext(unitName = "ds")
    private EntityManager em;


    @PostConstruct
    public void init(){

    }

    @Transactional
    public void insert(Product product){
        em.persist(product);
    }

    @Transactional
    public void insert(Category category){
        em.persist(category);
    }


    @Transactional
    public void update(Product product){
        em.merge(product);
    }

    @Transactional
    public void update(Category category){
        em.merge(category);
    }

    @Transactional
    public void delete(Product product){
        Product p = findProductById(product.getProductid());
        if (p != null) {
            em.remove(p);
        }
    }

    @Transactional
    public void delete(Category category){
        Category c = findCategoryById(category.getCategoryid());
        if (c != null) {
            em.remove(c);
        }
    }

    public Product findProductById(long id){
        return em.find(Product.class,id);
    }

    public Category findCategoryById(long id){
        return em.find(Category.class,id);
    }

    public List<Product> findAllProducts(){
        return em.createQuery("from Product", Product.class).getResultList();
    }

    public List<Category> findAllCategories(){
        return em.createQuery("from Category", Category.class).getResultList();
    }
}
