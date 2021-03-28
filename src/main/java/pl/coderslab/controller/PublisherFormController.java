package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
public class PublisherFormController {
    private final PublisherDao publisherDao;
    private final BookDao bookDao;
    private final AuthorDao authorDao;

    public PublisherFormController(PublisherDao publisherDao, BookDao bookDao, AuthorDao authorDao) {
        this.publisherDao = publisherDao;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }
    @GetMapping(path = "form/publisher/add", produces = "text/html;charset=UTF-8")
    String showAddForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "publisher/add";
    }
    @PostMapping(path = "form/publisher/add", produces = "text/html;charset=UTF-8")
    String processAddForm(@Valid Publisher publisher, BindingResult result) {
        if(result.hasErrors()){
            return "publisher/add";
        }
        publisherDao.create(publisher);
        return "redirect:/publisher/aa";
//        return "redirect:books";

    }
    @GetMapping(path = "form/publishers", produces = "text/html;charset=UTF-8")
    String showAllPublishers(Model model) {
        List<Publisher> publishers = publisherDao.findAll();
        model.addAttribute("publisher", publishers);
        return "publisher/all";
    }
    @PostMapping(path = "form/publisher/edit",  produces = "text/html;charset=UTF-8")
    String editPublisher(Publisher publisher) {
        publisherDao.update(publisher);
        return "redirect:/publisher/edit";
    }
    @ModelAttribute("books")
    List<Book> findAllBooks() {
        return bookDao.findAll();
    }

    @ModelAttribute("authors")
    Collection<Author> findAllAuthors() {
        return authorDao.findAllAuthors();
    }
}
