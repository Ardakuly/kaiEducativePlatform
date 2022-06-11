package spring.educativeprojects.kaieducativeplatform.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Lesson extends BaseEntity {

    @ManyToOne
    private Module module;

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
