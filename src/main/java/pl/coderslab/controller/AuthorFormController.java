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
public class AuthorFormController {
    private final PublisherDao publisherDao;
    private final BookDao bookDao;
    private final AuthorDao authorDao;

    public AuthorFormController(PublisherDao publisherDao, BookDao bookDao, AuthorDao authorDao) {
        this.publisherDao = publisherDao;
        this.bookDao = bookDao;
        this.authorDao = authorDao;
    }

    @GetMapping(path = "form/author", produces = "text/html;charset=UTF-8")
    String showAddForm(Model model) {
        model.addAttribute("author", new Author());
        return "author/add";
    }

    @PostMapping(path = "form/author", produces = "text/html;charset=UTF-8")
    String processAddForm(@Valid Author author, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "author/add";
        }
        authorDao.create(author);
        return "redirect:/author/add";
//        return "redirect:author";

    }

    @GetMapping(path = "form/authors", produces = "text/html;charset=UTF-8")
    String showAllAuthors(Model model) {
        List<Author> authors = authorDao.findAll();
        model.addAttribute("authors", authors);
        return "authors/all";
    }

    @PostMapping(path = "form/author/edit", produces = "text/html;charset=UTF-8")
    String editAuthor(Author author) {
        authorDao.update(author);
        return "redirect:author/edit";
    }

    @ModelAttribute("publishers")
    Collection<Publisher> findAllPublishers() {
        return publisherDao.findAllPublishers();
    }

    @ModelAttribute("books")
    List<Book> findAllBooks() {
        return bookDao.findAll();
    }
}