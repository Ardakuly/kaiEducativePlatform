package spring.educativeprojects.kaieducativeplatform.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Instructor extends BaseEntity {

    @OneToMany(mappedBy = "instructor", fetch = FetchType.EAGER)
    private Set<Course> courses;

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
