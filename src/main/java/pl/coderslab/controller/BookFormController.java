package pl.coderslab.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;
import java.util.Collection;
import java.util.List;

@Controller
public class BookFormController {
    private final PublisherDao publisherDao;
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    public BookFormController(PublisherDao publisherDao, BookDao bookDao, AuthorDao authorDao) {
        this.publisherDao = publisherDao;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }
    @GetMapping(path = "form/book", produces = "text/html;charset=UTF-8")
    String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/add";
    }
    @PostMapping(path = "form/book", produces = "text/html;charset=UTF-8")
    String processAddForm(Book book) {
        bookDao.create(book);
        return "redirect:/form/books";
//        return "redirect:books";

    }
    @GetMapping(path = "form/books", produces = "text/html;charset=UTF-8")
    String showAllBooks(Model model) {
        List<Book> books = bookDao.findAll();
        model.addAttribute("books", books);
        return "book/all";
    }
    @PostMapping(path = "form/book/edit",  produces = "text/html;charset=UTF-8")
    String editBook(Book book) {
   bookDao.update(book);
        return "redirect:book/edit";
    }
    @ModelAttribute("publishers")
    Collection<Publisher> findAllPublishers() {
        return publisherDao.findAllPublishers();
    }

    @ModelAttribute("authors")
    Collection<Author> findAllAuthors() {
        return authorDao.findAllAuthors();
    }
}
//Zadanie 1 - rozwiązywane z wykładowcą
//W projekcie Spring01hibernate utwórz kontroler BookFormController, umieścimy w nim akcje odpowiedzialne za operacje na obiektach typu Book z wykorzystaniem formularzy.
//Utwórz formularz, który pozwoli dodać obiekt klasy Book.
//Utwórz akcję wyświetlającą formularz.
//Dodaj akcję obsługującą dane z formularza,
//Dodaj możliwość wyboru wydawcy z listy rozwijalnej.
//Zapisz obiekt do bazy danych.