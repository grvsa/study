package ru.grvsa.spring1lesson5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.grvsa.spring1lesson5.entities.Book;
import ru.grvsa.spring1lesson5.repositories.BooksRepository;

import java.util.List;

@Service
public class BookService {
    private BooksRepository booksRepository;

    @Autowired
    public void setBooksRepository(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public BookService() {
    }

    public List<Book> getAllBooks(){
        return (List<Book>) booksRepository.findAll();
    }

    public void addBook(Book book){
        booksRepository.save(book);
    }

    public void removeById(Long id){
        booksRepository.deleteById(id);
    }
}
