package spring.educativeprojects.kaieducativeplatform.entities;

import javax.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson extends BaseEntity {

    String videoLink;
    @JoinColumn(name = "module_id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Module module;

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
