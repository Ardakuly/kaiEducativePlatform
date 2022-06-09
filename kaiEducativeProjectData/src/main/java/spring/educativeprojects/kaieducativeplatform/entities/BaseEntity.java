package spring.educativeprojects.kaieducativeplatform.entities;

import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public abstract class BaseEntity {


    private Integer id;
    private String name;



    //------------------ Getters and Setters------------------//

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //----------------------END------------------------------//
}
