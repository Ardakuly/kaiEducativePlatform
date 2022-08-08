package spring.educativeprojects.kaieducativeplatform.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import spring.educativeprojects.kaieducativeplatform.dto.ModuleDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Module;

@Component
public class ModuleToModuleDTOConverter implements Converter<Module, ModuleDTO> {

    public final LessonToLessonDTOConvert convertLesson;

    @Autowired
    public ModuleToModuleDTOConverter(LessonToLessonDTOConvert convertLesson) {

        this.convertLesson = convertLesson;
    }

    @Override
    public ModuleDTO convert(Module source) {

        final ModuleDTO moduleCommand = new ModuleDTO();
        //moduleCommand.setId(source.getId());
        moduleCommand.setName(source.getName());
        moduleCommand.setDescription(source.getDescription());
        if (source.getLessons() != null && source.getLessons().size() > 0) {
            source.getLessons().forEach(lesson -> moduleCommand.getLessonsDTO().add(convertLesson.convert(lesson)));
        }

        return moduleCommand;
    }
}
