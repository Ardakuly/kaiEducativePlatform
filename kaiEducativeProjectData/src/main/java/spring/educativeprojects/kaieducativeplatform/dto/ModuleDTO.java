package spring.educativeprojects.kaieducativeplatform.dto;

import java.util.HashSet;
import java.util.Set;

public class ModuleDTO {

    private String name;

    private String description;

    private Set<LessonDTO> lessonsDTO = new HashSet<>();

    private CourseDTO courseDTO;



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

    public Set<LessonDTO> getLessonsDTO() {
        return lessonsDTO;
    }

    public void setLessonsDTO(Set<LessonDTO> lessonsDTO) {
        this.lessonsDTO = lessonsDTO;
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }
}
