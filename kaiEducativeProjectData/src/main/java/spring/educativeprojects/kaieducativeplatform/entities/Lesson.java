package spring.educativeprojects.kaieducativeplatform.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lessons")
public class Lesson extends BaseEntity {

    //need to add video

    @JoinColumn(name = "module_id")
    @ManyToOne
    private Module module;

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
