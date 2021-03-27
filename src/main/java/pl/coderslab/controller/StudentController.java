package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import pl.coderslab.other.Student;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(produces = "application/json; charset=UTF-8")
public class StudentController {
    @GetMapping(path = "/showStudent", produces = "text/plain;charset=UTF-8")
    String showStudentForm(Model model) {
        model.addAttribute("student",new Student());
        return "studentForm";
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
