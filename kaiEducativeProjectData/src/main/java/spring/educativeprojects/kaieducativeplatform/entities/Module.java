package spring.educativeprojects.kaieducativeplatform.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Module extends BaseEntity{

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
    private Set<Lesson> lessons = new HashSet<>();

    @ManyToOne
    private Course course;

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
