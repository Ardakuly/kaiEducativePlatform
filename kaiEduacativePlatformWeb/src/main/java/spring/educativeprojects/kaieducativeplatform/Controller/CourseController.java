package spring.educativeprojects.kaieducativeplatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.educativeprojects.kaieducativeplatform.entities.Course;
import spring.educativeprojects.kaieducativeplatform.service.CourseService;

import java.util.Set;

@Controller
@RequestMapping("/courses")
public class CourseController {

    final private CourseService seriviceCourses;

    @Autowired
    public CourseController(CourseService seriviceCourses) {
        this.seriviceCourses = seriviceCourses;
    }

    @RequestMapping("/allCourses")
    public String findAllCourses(Model model) {

        model.addAttribute("courses", seriviceCourses.findAll());

        return "allCoursesIndex";
    }
}
