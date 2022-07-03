package spring.educativeprojects.kaieducativeplatform.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "courses")
public class Course extends BaseEntity{

    @JoinColumn(name = "author_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Author author;

    @JoinColumn(name = "instructor_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Instructor instructor;

    @OneToMany(cascade = CascadeType.ALL, /*mappedBy = "course",*/ fetch = FetchType.EAGER)
    private Set<Module> modules = new HashSet<>();

    //@JoinColumn(name = "sphere_id")
    //@ManyToOne
    //private String sphere;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

//    public Sphere getSphere() {
//        return sphere;
//    }
//
//    public void setSphere(Sphere sphere) {
//        this.sphere = sphere;
//    }
}
