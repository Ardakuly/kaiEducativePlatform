package spring.educativeprojects.kaieducativeplatform.dto;

import java.util.HashSet;
import java.util.Set;

public class InstructorDTO {

    private String name;

    private Set<CourseDTO> coursesDTO = new HashSet<>();


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
