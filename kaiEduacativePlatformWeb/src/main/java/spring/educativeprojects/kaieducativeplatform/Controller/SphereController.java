package spring.educativeprojects.kaieducativeplatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.educativeprojects.kaieducativeplatform.service.Map.SphereServiceMapImpl;
import spring.educativeprojects.kaieducativeplatform.service.SphereService;

@Controller
public class SphereController {

    private final SphereService serviceSphere;

    @Autowired
    public SphereController(SphereService sphereService) {
        this.serviceSphere = sphereService;
    }

    @RequestMapping("/spheres")
    public String findAllSphere(Model model) {

        model.addAttribute("spheres", serviceSphere.findAll());

        return "sphereIndex";

    }
}
