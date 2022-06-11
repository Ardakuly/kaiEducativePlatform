package spring.educativeprojects.kaieducativeplatform.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.educativeprojects.kaieducativeplatform.service.InstructorService;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService serviceInstructors;

    public InstructorController(InstructorService serviceInstructors) {
        this.serviceInstructors = serviceInstructors;
    }

    @RequestMapping("/allAuthors")
    public String findAllInstructors(Model model) {

        model.addAttribute("instructors", serviceInstructors.findAll());

        return "allInstructorsIndex";

    }
}
