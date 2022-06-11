package spring.educativeprojects.kaieducativeplatform.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.educativeprojects.kaieducativeplatform.service.AuthorService;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService serviceAuthors;

    public AuthorController(AuthorService serviceAuthors) {
        this.serviceAuthors = serviceAuthors;
    }

    @RequestMapping("/allAuthors")
    public String findAllAuthors(Model model) {

        model.addAttribute("authors", serviceAuthors.findAll());

        return "allAuthorsIndex";

    }
}
