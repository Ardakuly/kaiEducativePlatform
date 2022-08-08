package spring.educativeprojects.kaieducativeplatform.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.educativeprojects.kaieducativeplatform.dto.LessonDTO;
import spring.educativeprojects.kaieducativeplatform.dto.ModuleDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Lesson;

@Component
public class LessonToLessonDTOConvert implements Converter<Lesson, LessonDTO> {


    @Override
    public LessonDTO convert(Lesson source) {
        final LessonDTO lessonCommand = new LessonDTO();

        //lessonCommand.setId(source.getId());
        lessonCommand.setName(source.getName());
        lessonCommand.setDescription(source.getDescription());

        return lessonCommand;
    }
}
