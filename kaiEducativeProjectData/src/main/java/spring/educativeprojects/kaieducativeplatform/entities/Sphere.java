package spring.educativeprojects.kaieducativeplatform.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "spheres")
public class Sphere extends BaseEntity{


    @OneToMany(mappedBy = "sphere", fetch = FetchType.EAGER)
    private Set<Course> courses = new HashSet<>();

    //------------------ Getters and Setters------------------//

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    //----------------------END------------------------------//
}
