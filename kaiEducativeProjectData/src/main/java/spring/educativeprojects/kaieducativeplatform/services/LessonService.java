package spring.educativeprojects.kaieducativeplatform.services;

import spring.educativeprojects.kaieducativeplatform.dto.LessonDTO;


public interface LessonService extends CrudService<LessonDTO, Integer> {
    LessonDTO updateLesson(LessonDTO lessonDTO, Integer id);

    //add getVideo method;
}
