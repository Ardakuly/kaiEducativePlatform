package spring.educativeprojects.kaieducativeplatform.dto;

import java.util.HashSet;
import java.util.Set;

public class SphereDTO {

    private String name;
    private String description;
    private Set<CourseDTO> coursesDTO = new HashSet<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<CourseDTO> getCoursesDTO() {
        return coursesDTO;
    }

    public void setCoursesDTO(Set<CourseDTO> coursesDTO) {
        this.coursesDTO = coursesDTO;
    }
}
