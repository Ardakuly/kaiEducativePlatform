package spring.educativeprojects.kaieducativeplatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.educativeprojects.kaieducativeplatform.service.LessonService;

@Controller
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService serviceLessons;

    @Autowired
    public LessonController(LessonService serviceLessons) {
        this.serviceLessons = serviceLessons;
    }

    @RequestMapping("/allLessons")
    public String findAllLessons(Model model) {

        model.addAttribute("lessons", serviceLessons.findAll());

        return "allLessonsIndex";

    }
}
