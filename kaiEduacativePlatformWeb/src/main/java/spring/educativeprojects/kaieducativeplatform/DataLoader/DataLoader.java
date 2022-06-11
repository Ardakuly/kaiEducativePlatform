package spring.educativeprojects.kaieducativeplatform.DataLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spring.educativeprojects.kaieducativeplatform.entities.Sphere;
import spring.educativeprojects.kaieducativeplatform.service.SphereService;

@Component
public class DataLoader implements CommandLineRunner {

    public final SphereService sphereService;

    @Autowired
    public DataLoader(SphereService sphereService) {
        this.sphereService = sphereService;
    }


    @Override
    public void run(String... args) throws Exception {

//        System.out.println("Saving ICT");
//        Sphere sphere1 = new Sphere(1, "ICT");
//        sphereService.save(sphere1);
//
//        System.out.println("Saving Security");
//        Sphere sphere2 = new Sphere(2, "Security");
//        sphereService.save(sphere2);
//
//        System.out.println("Saving Programming");
//        Sphere sphere3 = new Sphere(3, "Programming");
//        sphereService.save(sphere3);


    }
}
