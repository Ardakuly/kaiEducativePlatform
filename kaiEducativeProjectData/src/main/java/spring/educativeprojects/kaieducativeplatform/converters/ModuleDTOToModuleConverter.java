package spring.educativeprojects.kaieducativeplatform.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.educativeprojects.kaieducativeplatform.dto.ModuleDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Module;

@Component
public class ModuleDTOToModuleConverter implements Converter<ModuleDTO, Module> {

    private final LessonDTOToLessonConverter convertLesson;

    public ModuleDTOToModuleConverter(LessonDTOToLessonConverter convertLesson) {
        this.convertLesson = convertLesson;
    }

    @Override
    public Module convert(ModuleDTO source) {

        final Module module = new Module();
        //module.setId(source.getId());
        module.setName(source.getName());
        module.setDescription(source.getDescription());
        if(source.getLessonsDTO() != null && source.getLessonsDTO().size() > 0) {
            source.getLessonsDTO().forEach(lessonDTO -> module.getLessons().add(convertLesson.convert(lessonDTO)));
        }
        return module;
    }
}
