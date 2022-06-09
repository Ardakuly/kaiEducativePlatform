package spring.educativeprojects.kaieducativeplatform.entities;

import javax.persistence.Entity;


public class Sphere extends BaseEntity{

    public Sphere() {
        super();
    }

    public Sphere(int id, String name) {
        super.setId(id);
        super.setName(name);

    }
}
