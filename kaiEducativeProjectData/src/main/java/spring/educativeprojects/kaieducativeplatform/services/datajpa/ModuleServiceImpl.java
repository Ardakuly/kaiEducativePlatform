package spring.educativeprojects.kaieducativeplatform.services.datajpa;

import spring.educativeprojects.kaieducativeplatform.converters.ModuleDTOToModuleConverter;
import spring.educativeprojects.kaieducativeplatform.converters.ModuleToModuleDTOConverter;
import spring.educativeprojects.kaieducativeplatform.dto.LessonDTO;
import spring.educativeprojects.kaieducativeplatform.dto.ModuleDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Course;
import spring.educativeprojects.kaieducativeplatform.entities.Lesson;
import spring.educativeprojects.kaieducativeplatform.repositories.CourseRepository;
import spring.educativeprojects.kaieducativeplatform.repositories.LessonRepository;
import spring.educativeprojects.kaieducativeplatform.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.educativeprojects.kaieducativeplatform.services.ModuleService;
import spring.educativeprojects.kaieducativeplatform.entities.Module;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ModuleServiceImpl implements ModuleService {

    public final CourseRepository repositoryCourse;

    private final ModuleRepository repositoryModule;

    private final LessonRepository repositoryLesson;

    private final ModuleToModuleDTOConverter converterModuleDTO;

    private final ModuleDTOToModuleConverter converterModule;

    @Autowired
    public ModuleServiceImpl(CourseRepository repositoryCourse, ModuleRepository repositoryModule, LessonRepository repositoryLesson, ModuleToModuleDTOConverter converterModuleDTO,
                             ModuleDTOToModuleConverter converterModule) {
        this.repositoryCourse = repositoryCourse;
        this.repositoryModule = repositoryModule;
        this.repositoryLesson = repositoryLesson;
        this.converterModuleDTO = converterModuleDTO;
        this.converterModule = converterModule;
    }

    //------------------ Common Methods -----------------//

    @Override
    public Set<ModuleDTO> findAll() {
        Set<ModuleDTO> modulesDTO = new HashSet<>();
        repositoryModule.findAll().forEach(module -> modulesDTO.add(converterModuleDTO.convert(module)));
        return modulesDTO;
    }

    @Override
    public Set<ModuleDTO> findAllByCourse(String name) {
        Course course = repositoryCourse.findByName(name).get();

        Set<ModuleDTO> modulesDTO = new HashSet<>();

        for(Module module : course.getModules()) {
            modulesDTO.add(converterModuleDTO.convert(module));
        }

        return modulesDTO;
    }

    @Override
    public ModuleDTO findById(Integer id) {
        Optional<Module> optModule = repositoryModule.findById(id);

        Module module = null;
        if(optModule.isPresent()) {
            module = optModule.get();
            return converterModuleDTO.convert(module);
        }

        return null;
    }

    @Override
    public ModuleDTO findByName(String name) {
        Optional<Module> optModule = repositoryModule.findByName(name);

        Module module = null;
        if(optModule.isPresent()) {
            module = optModule.get();
            return converterModuleDTO.convert(module);
        }

        return null;
    }

    @Override
    public ModuleDTO updateModule(ModuleDTO moduleDTO, Integer id) {
        Module module = converterModule.convert(moduleDTO);
        module.setId(id);

        for (Lesson lesson : module.getLessons()) {
            if (repositoryLesson.findByName(lesson.getName()).isPresent()) {
                lesson.setId(repositoryLesson.findByName(lesson.getName()).get().getId());
            }
        }

        Module moduleReturned = repositoryModule.save(module);
        return converterModuleDTO.convert(moduleReturned);
    }

    public ModuleDTO addLessons(ModuleDTO moduleDTO, String name) {
        Optional<Module> optModule = repositoryModule.findByName(name);
        Module module = optModule.get();

        if(module.getLessons().size() == 0) {
            moduleDTO.getLessonsDTO().
                    forEach(lessonDTOEach -> module.getLessons().add(repositoryLesson.findByName(lessonDTOEach.getName()).get()));
        }else {
            for (LessonDTO lessonDTO : moduleDTO.getLessonsDTO()) {
                boolean isThere = false;
                for (Lesson lesson : module.getLessons()) {
                    if (lessonDTO.getName().equals(lesson.getName())) {
                        isThere = true;
                    }
                }
                if(!isThere) module.getLessons().add(repositoryLesson.findByName(lessonDTO.getName()).get());
            }
        }

        Module returnedModule = repositoryModule.save(module);
        return converterModuleDTO.convert(returnedModule);
    }

    @Override
    public ModuleDTO save(ModuleDTO moduleDTO) {

        Module module =  repositoryModule.save(converterModule.convert(moduleDTO));

        return converterModuleDTO.convert(module);
    }

    @Override
    public void delete(ModuleDTO moduleDTO) {
        Module module = repositoryModule.findByName(moduleDTO.getName()).get();
        repositoryModule.delete(module);
    }

    @Override
    public void deleteById(Integer id) {
        repositoryModule.deleteById(id);
    }

    //----------------------END------------------------------//
}
