package spring.educativeprojects.kaieducativeplatform.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.educativeprojects.kaieducativeplatform.service.ModuleService;

@Controller
@RequestMapping("/modules")
public class ModuleController {

     private final ModuleService serviceModules;

     @Autowired
    public ModuleController(ModuleService serviceModules) {
        this.serviceModules = serviceModules;
    }

    @RequestMapping("/allModules")
    public String findAllModules(Model model) {

        model.addAttribute("modules", serviceModules.findAll());

        return "allModulesIndex";

    }
}
