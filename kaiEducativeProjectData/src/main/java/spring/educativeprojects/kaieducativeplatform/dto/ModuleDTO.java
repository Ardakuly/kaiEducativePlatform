package spring.educativeprojects.kaieducativeplatform.dto;

import java.util.HashSet;
import java.util.Set;

public class ModuleDTO {

    //private Integer id;

    private String name;

    private Set<LessonDTO> lessonsDTO = new HashSet<>();

    //private CourseDTO courseDTO;


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

    public Set<LessonDTO> getLessonsDTO() {
        return lessonsDTO;
    }

    public void setLessonsDTO(Set<LessonDTO> lessonsDTO) {
        this.lessonsDTO = lessonsDTO;
    }

//    public CourseDTO getCourseDTO() {
//        return courseDTO;
//    }
//
//    public void setCourseDTO(CourseDTO courseDTO) {
//        this.courseDTO = courseDTO;
//    }
}
