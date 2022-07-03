package spring.educativeprojects.kaieducativeplatform.services;

import spring.educativeprojects.kaieducativeplatform.dto.LessonDTO;

import java.util.Set;


public interface LessonService extends CrudService<LessonDTO, Integer> {

    LessonDTO findByName(String name);
    LessonDTO updateLesson(LessonDTO lessonDTO, Integer id);
    Set<LessonDTO> findAllByModule(String name);

    //add getVideo method;
}
