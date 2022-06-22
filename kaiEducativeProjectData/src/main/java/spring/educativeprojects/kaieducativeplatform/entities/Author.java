package spring.educativeprojects.kaieducativeplatform.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author extends BaseEntity{


    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Course> courses = new HashSet<>();

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
