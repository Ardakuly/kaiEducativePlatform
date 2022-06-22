package spring.educativeprojects.kaieducativeplatform.dto;

import java.util.HashSet;
import java.util.Set;

public class SphereDTO {

    //private Integer id;
    private String name;
    private Set<CourseDTO> coursesDTO = new HashSet<>();

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CourseDTO> getCoursesDTO() {
        return coursesDTO;
    }

    public void setCoursesDTO(Set<CourseDTO> coursesDTO) {
        this.coursesDTO = coursesDTO;
    }
}
