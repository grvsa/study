package ru.grvsa.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.grvsa.demo.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findAll();
    List<Product> findAllByPriceBetween(double min, double max);
    @Query(value = "select * from products order by price asc", nativeQuery = true)
    List<Product> findAllOrderByPriceMin();
    @Query(value = "select * from products order by price desc", nativeQuery = true)
    List<Product> findAllOrderByPriceMax();
}
