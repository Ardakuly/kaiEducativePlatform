package spring.educativeprojects.kaieducativeplatform.DataLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.educativeprojects.kaieducativeplatform.entities.Author;
import spring.educativeprojects.kaieducativeplatform.entities.Course;
import spring.educativeprojects.kaieducativeplatform.entities.Instructor;
import spring.educativeprojects.kaieducativeplatform.entities.Sphere;
import spring.educativeprojects.kaieducativeplatform.repositories.AuthorRepository;
import spring.educativeprojects.kaieducativeplatform.repositories.CourseRepository;
import spring.educativeprojects.kaieducativeplatform.repositories.InstructorRepository;
import spring.educativeprojects.kaieducativeplatform.repositories.SphereRepository;

//@Component
public class DataLoader implements CommandLineRunner {

    public final SphereRepository repositorySphere;
    public final CourseRepository repositoryCourses;

    public final InstructorRepository repositoryInstructor;

    public final AuthorRepository repositoryAuthor;

    //@Autowired
    public DataLoader(SphereRepository repositorySphere, CourseRepository repositoryCourses, InstructorRepository repositoryInstructor, AuthorRepository repositoryAuthor) {
        this.repositorySphere = repositorySphere;
        this.repositoryCourses = repositoryCourses;
        this.repositoryInstructor = repositoryInstructor;
        this.repositoryAuthor = repositoryAuthor;
    }

    @Override
    public void run(String... args) throws Exception {

//        Author cisco = new Author();
//        cisco.setName("Cisco");
//        Author huawei = new Author();
//        huawei.setName("Huawei");
//
//        repositoryAuthor.save(huawei);
//        repositoryAuthor.save(cisco);
//
//
//        Instructor instructor1 = new Instructor();
//        instructor1.setName("Akniyet Nurzhaubayev");
//
//        Instructor instructor2 = new Instructor();
//        instructor2.setName("Alisher Anuarbekov");
//
//        repositoryInstructor.save(instructor1);
//        repositoryInstructor.save(instructor2);
//
//        Course CCNA = new Course();
//        CCNA.setName("CCNA");
//        CCNA.setAuthor(cisco);
//        CCNA.setInstructor(instructor1);
//
//        Course HCNA = new Course();
//        HCNA.setName("HCNA");
//        HCNA.setAuthor(huawei);
//        HCNA.setInstructor(instructor2);
//
//        Sphere ICT = new Sphere();
//        ICT.setName("ICT");
//
////        CCNA.setSphere(ICT);
////         HCNA.setSphere(ICT);
//        ICT.getCourses().add(HCNA);
//        ICT.getCourses().add(CCNA);
//
//        repositorySphere.save(ICT);


















    }
}
