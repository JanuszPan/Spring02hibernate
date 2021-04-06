package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.entity.Book;
import pl.coderslab.other.Student;

import java.util.Arrays;
import java.util.List;

@Controller
@Transactional
@RequestMapping(produces = "application/json; charset=UTF-8")
public class StudentController {
    @GetMapping(path = "/student", produces = "text/plain;charset=UTF-8")
    String showStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/studentForm";
    }

    @PostMapping(path = "/student", produces = "text/plain;charset=UTF-8")
    String StudentForm(@ModelAttribute("student") Student student) {
        return student.toString();
    }

    @ModelAttribute("countries")
    public List<String> countries() {

        return Arrays.asList("Poland", "Germany", "France", "Russia", "Denmark");
    }

    @ModelAttribute("programmingSkills")
    public List<String> programmingSkills() {
        return Arrays.asList("Java", "Java Script", "PHP", "Scratch");
    }

    @ModelAttribute("hobbies")
    public List<String> hobbies() {

        return Arrays.asList("music", "sport", "games", "astronomy", "journeys");
    }

}
