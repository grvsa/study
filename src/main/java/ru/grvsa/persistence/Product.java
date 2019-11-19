package ru.grvsa.persistence;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="productid", updatable=false)
    private Long productid;

    @Column(nullable = false, length = 45, name = "productName", updatable = true)
    private String productName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoryid")
//    @OneToOne
    private Category productCategory;

    @Column(nullable = false, length = 45, name = "productDescription", updatable = true)
    private String productDescription;

    @Column(name = "productPrice")
    private Double productPrice;

    @Column(name = "productQty")
    private Long productQty;

    public Product() {
    }

    public Product(Long productid, String productName, Category productCategory, String productDescription, Double productPrice, Long productQty) {
        this.productid = productid;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQty = productQty;
    }

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Long getProductQty() {
        return productQty;
    }

    public void setProductQty(Long productQty) {
        this.productQty = productQty;
    }
}
