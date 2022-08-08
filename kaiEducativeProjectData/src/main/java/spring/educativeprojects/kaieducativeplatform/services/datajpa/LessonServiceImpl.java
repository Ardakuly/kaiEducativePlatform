package spring.educativeprojects.kaieducativeplatform.services.datajpa;

import spring.educativeprojects.kaieducativeplatform.converters.LessonDTOToLessonConverter;
import spring.educativeprojects.kaieducativeplatform.converters.LessonToLessonDTOConvert;
import spring.educativeprojects.kaieducativeplatform.dto.LessonDTO;
import spring.educativeprojects.kaieducativeplatform.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.educativeprojects.kaieducativeplatform.entities.Lesson;
import spring.educativeprojects.kaieducativeplatform.repositories.ModuleRepository;
import spring.educativeprojects.kaieducativeplatform.services.LessonService;
import spring.educativeprojects.kaieducativeplatform.entities.Module;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class LessonServiceImpl implements LessonService {

    public final ModuleRepository repositoryModule;
    public final LessonRepository repositoryLesson;

    public final LessonToLessonDTOConvert converterLessonDTO;

    public final LessonDTOToLessonConverter converterLesson;

    @Autowired
    public LessonServiceImpl(ModuleRepository repositoryModule, LessonRepository repositoryLesson,
                             LessonToLessonDTOConvert converterLessonDTO,
                             LessonDTOToLessonConverter converterLesson) {
        this.repositoryModule = repositoryModule;
        this.repositoryLesson = repositoryLesson;
        this.converterLessonDTO = converterLessonDTO;
        this.converterLesson = converterLesson;
    }

    //------------------ Common Methods -----------------//

    @Override
    public Set<LessonDTO> findAll() {
        Set<LessonDTO> lessonsDTO = new HashSet<>();
        repositoryLesson.findAll().forEach(lesson -> lessonsDTO.add(converterLessonDTO.convert(lesson)));
        return lessonsDTO;
    }

    @Override
    public Set<LessonDTO> findAllByModule(String name) {

        Module module = repositoryModule.findByName(name).get();

        Set<LessonDTO> lessonsDTO = new HashSet<>();

        for (Lesson lesson : module.getLessons()) {
            lessonsDTO.add(converterLessonDTO.convert(lesson));
        }

        return lessonsDTO;
    }

    @Override
    public LessonDTO findById(Integer id) {
        Optional<Lesson> optLesson = repositoryLesson.findById(id);

        Lesson lesson = null;
        if(optLesson.isPresent()) {
            lesson = optLesson.get();
            return converterLessonDTO.convert(lesson);
        }

        return null;
    }

    @Override
    public LessonDTO findByName(String name) {

        Optional<Lesson> optLesson = repositoryLesson.findByName(name);

        Lesson lesson = null;
        if(optLesson.isPresent()) {
            lesson = optLesson.get();
            return converterLessonDTO.convert(lesson);
        }

        return null;
    }

    @Override
    public LessonDTO updateLesson(LessonDTO lessonDTO, Integer id) {
        Lesson lesson = converterLesson.convert(lessonDTO);
        lesson.setId(id);
        Lesson lessonReturned = repositoryLesson.save(lesson);
        return converterLessonDTO.convert(lessonReturned);
    }


    @Override
    public LessonDTO save(LessonDTO lessonDTO) {

        if (repositoryLesson.findByName(lessonDTO.getName()).isPresent()) return null;

        Lesson lesson = converterLesson.convert(lessonDTO);

        lesson.setModule(repositoryModule.findByName(lessonDTO.getModuleDTO().getName()).get());

        Lesson forReturn = repositoryLesson.save(lesson);

        return converterLessonDTO.convert(forReturn);
    }

    @Override
    public void delete(LessonDTO lessonDTO) {

        Lesson lesson = repositoryLesson.findByName(lessonDTO.getName()).get();
        repositoryLesson.delete(lesson);
    }

    @Override
    public void deleteById(Integer id) {
        repositoryLesson.deleteById(id);
    }


    //----------------------END------------------------------//
}
