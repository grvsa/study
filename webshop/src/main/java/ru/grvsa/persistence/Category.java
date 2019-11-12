package ru.grvsa.persistence;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="categoryid", updatable=false)
    private Long categoryid;

    @Column(nullable = false, unique = true, length = 45, name = "categoryName", updatable = true)
    private String categoryName;

    public Category() {
    }

    public Category(Long categoryid, String categoryName) {
        this.categoryid = categoryid;
        this.categoryName = categoryName;
    }

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "" + categoryid;
    }
}
