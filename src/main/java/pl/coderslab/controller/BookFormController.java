package pl.coderslab.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;
import pl.coderslab.repository.BookRepository;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor// Lombok powala, że nie trzeba używać konstruktora, trzeba usunąć kontruktor
@Controller
public class BookFormController {
    private final PublisherDao publisherDao;
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final BookRepository bookRepository;

    //    public BookFormController(PublisherDao publisherDao, BookDao bookDao, AuthorDao authorDao,BookRepository bookRepository) {
//        this.publisherDao = publisherDao;
//        this.bookDao = bookDao;
//        this.authorDao = authorDao;
//        this.bookRepository=bookRepository;
//    }
    @GetMapping(path = "form/book/search", produces = "text/html;charset=UTF-8")
//np. form/book/search?title=Thinking i Java
    String findAllBooksByTitle(@RequestParam("title") String title, Model model) {
//        List<Book> books = bookRepository.findAllByTitle(title);
        List<Book> books = bookRepository.findAllByTitleLike(title);//%25O%25 trzeba % szukany_znak % enkodować, bo % nie jest prawidłowym znakiem w url, użyć np strony: https://www.urlencoder.org/
        model.addAttribute("books", books);
        return "book/all";
    }

    //Listę książek dla zadanego autora.
    @GetMapping(path = "form/book/searchAuthor", produces = "text/html;charset=UTF-8")
    String indAllByAuthors(@RequestParam("authorID") Long id, Model model) {
        Author author=new Author();
        author.setId(id);
        List<Book> books = bookRepository.findAllByAuthors(author);
        model.addAttribute("books",books);
        return "book/all";
    }
    //Listę książek dla zadanego wydawcy.
    @GetMapping(path = "form/book/searchPublisher", produces = "text/html;charset=UTF-8")
    String findAllByPublisher(@RequestParam("publisherID") Long id, Model model) {
        Publisher publisher=new Publisher();
        publisher.setId(id);
        List<Book> books = bookRepository.findAllByPublisher(publisher);
        model.addAttribute("books",books);
        return "book/all";
    }
    //Listę książek dla danego ratingu.
    @GetMapping(path = "form/book/searchRating", produces = "text/html;charset=UTF-8")
    String findAllByRating(@RequestParam("rating") int rat, Model model) {
        List<Book> books = bookRepository.findAllByRating(rat);
        model.addAttribute("books",books);
        return "book/all";
    }

    @GetMapping(path = "form/book", produces = "text/html;charset=UTF-8")
    String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/add";
    }

    @PostMapping(path = "form/book", produces = "text/html;charset=UTF-8")
    String processAddForm(@Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book/add";
        }
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

    @GetMapping(path = "form/book/edit", produces = "text/html;charset=UTF-8")
    String showEditForm(Model model, @RequestParam long id) {
        Book book = bookDao.read(id);
        model.addAttribute("book", book);
        return "book/edit";
    }

    @PostMapping(path = "form/book/edit", produces = "text/html;charset=UTF-8")
    String processEditForm(@Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "book/edit";
        }
        bookDao.update(book);
        return "redirect:/form/books";
    }

    @GetMapping(path = "form/book/delete", produces = "text/html;charset=UTF-8")
    String deleteBook(@RequestParam long id) {
        Book book = bookDao.read(id);
        bookDao.delete(book);
        return "redirect:/form/books";
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