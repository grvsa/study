package ru.grvsa.spring1lesson6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.grvsa.spring1lesson6.entities.Book;
import ru.grvsa.spring1lesson6.services.BookService;

@Controller
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/all")
    public String showAllBooks(Model model){
        model.addAttribute("books", bookService.getAllBooks());
        return "books";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String showAddForm(Model model){
        Book book = new Book();
        book.setTitle("NONE");
        book.setDescription("NONE");
        Short year = 2020;
        book.setYear(year);
        model.addAttribute("book", book);
        return "add-book-form";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String showAddForm(Book book){
        bookService.addBook(book);
        return "redirect:/books/all";
    }

    @RequestMapping(path = "/remove/{id}", method = RequestMethod.GET)
    public String removeById(@PathVariable(value = "id") Long id){
        bookService.removeById(id);
        return "redirect:/books/all";
    }
}
