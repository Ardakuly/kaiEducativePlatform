package spring.educativeprojects.kaieducativeplatform.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "instructors")
public class Instructor extends BaseEntity {

    @OneToMany(mappedBy = "instructor", fetch = FetchType.EAGER)
    private Set<Course> courses = new HashSet<>();

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
