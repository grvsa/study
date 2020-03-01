package ru.grvsa.spring1lesson5.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.grvsa.spring1lesson5.entities.Book;

@Repository
public interface BooksRepository extends PagingAndSortingRepository<Book,Long> {
}
