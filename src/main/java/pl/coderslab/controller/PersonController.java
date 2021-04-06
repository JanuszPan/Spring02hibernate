package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.PersonDao;
import pl.coderslab.entity.Person;


@Controller
@RequestMapping(produces = "application/json; charset=UTF-8")
public class PersonController {
    PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping(path = "/showPersonForm", produces = "text/plain;charset=UTF-8")
    String showPersonForm() {

        return "person/personForm";
    }
    @PostMapping(path = "/savePersonForm", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    String savePersonForm(Person person) {
        personDao.create(person);
        return "Dodano osobę " + person;
    }
//    @PostMapping(path = "/savePersonForm", produces = "text/plain;charset=UTF-8")
//    @ResponseBody
 //robi automatycznie
//    String savePersonForm(@RequestParam("login") String login,
//                          @RequestParam("password") String password,
//                          @RequestParam("email") String email) {
//        Person person = new Person();
//        person.setLogin(login);
//        person.setPassword(password);
//        person.setEmail(email);
//        personDao.create(person);
//        return "Dodano osobę " + person;
//    }

    @RequestMapping("/person/add")
    @ResponseBody
    public String savePerson() {
        Person person =new Person();
        person.setLogin("admin1");
        person.setPassword("pass1");
        person.setEmail("email1");
        return "Id nowego osoby to: " + person.getId() + " Login nowej osoby to: " + person.getLogin();
    }
    @RequestMapping("/person/get/{id}")
    @ResponseBody
    public String getPerson(@PathVariable long id){
        Person person = personDao.read(id);
        return person.toString();
    }
    @RequestMapping("/person/{id}")
    public String updatePerson(@PathVariable long id){
        Person person= personDao.read(id);
        person.setLogin("login1");
        person.setEmail("email1");
        personDao.update(person);
        return person.toString();
    }
    @RequestMapping("/person/delete/{id}")
    public String deletePerson(@PathVariable long id){
        Person author = personDao.read(id);
        personDao.delete(author);
        return "Person deleted";
    }
}
//Zadanie 1 - rozwiązywane z wykładowcą
//Utwórz akcję wyświetlającą formularz w kontrolerze PersonController.
//Dodaj widok formularza zawierający pola login oraz password, email.
//Wykorzystaj w tym celu encję Person z poprzednich zajęć.
//Dodaj akcję przetwarzająca formularz (akcja ma zakończyć się zapisem danych do bazy)- pobieraj dane za pomocą @RequestParam.