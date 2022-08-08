package spring.educativeprojects.kaieducativeplatform.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.educativeprojects.kaieducativeplatform.dto.LessonDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Lesson;

@Component
public class LessonDTOToLessonConverter implements Converter<LessonDTO, Lesson> {

    @Override
    public Lesson convert(LessonDTO source) {

        final Lesson lesson = new Lesson();

        //lesson.setId(source.getId());
        lesson.setName(source.getName());
        lesson.setDescription(source.getDescription());
        return lesson;
    }
}
