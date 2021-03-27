package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Publisher;

@Controller
@RequestMapping(produces = "application/json; charset=UTF-8")
public class PublisherController {
    private final PublisherDao publisherDao;

    public PublisherController(PublisherDao publisherDao) {

        this.publisherDao = publisherDao;
    }

    @RequestMapping("/publisher/add")
    @ResponseBody
    public String newPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Publisher1");
        publisherDao.create(publisher);
        return "Id nowego wydawcy to: " + publisher.getId() + " Nazwa nowego wydawcy to: " + publisher.getName();
    }

    @RequestMapping("/publisher/{id}/{name}")
    @ResponseBody
    public String updatePublisher(@PathVariable long id, @PathVariable String name) {
        Publisher publisher = publisherDao.read(id);
        return publisher.toString();
    }

    @RequestMapping("/publisher/get/{id}")
    @ResponseBody
    public String getPublisher(@PathVariable long id) {
        Publisher publisher = publisherDao.read(id);
        return publisher.toString();
    }
    @RequestMapping("/publisher/delete/{id}")
    @ResponseBody
    public String deletePublisher(@PathVariable long id){
        Publisher publisher = publisherDao.read(id);
        publisherDao.delete(publisher);
        return "Publisher deleted";
    }
}
