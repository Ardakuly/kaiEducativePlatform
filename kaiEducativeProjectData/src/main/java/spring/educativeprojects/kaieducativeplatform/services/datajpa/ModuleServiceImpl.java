package spring.educativeprojects.kaieducativeplatform.services.datajpa;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import spring.educativeprojects.kaieducativeplatform.converters.ModuleDTOToModuleConverter;
import spring.educativeprojects.kaieducativeplatform.converters.ModuleToModuleDTOConverter;
import spring.educativeprojects.kaieducativeplatform.currentLogUser.IAuthenticationPrincipal;
import spring.educativeprojects.kaieducativeplatform.dto.ModuleDTO;
import spring.educativeprojects.kaieducativeplatform.entities.*;
import spring.educativeprojects.kaieducativeplatform.entities.Module;
import spring.educativeprojects.kaieducativeplatform.repositories.CourseRepository;
import spring.educativeprojects.kaieducativeplatform.repositories.LessonRepository;
import spring.educativeprojects.kaieducativeplatform.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.educativeprojects.kaieducativeplatform.repositories.UserRepository;
import spring.educativeprojects.kaieducativeplatform.services.ModuleService;

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

    private final UserRepository userRepository;

    private IAuthenticationPrincipal authenticationPrincipal;

    @Autowired
    public ModuleServiceImpl(CourseRepository repositoryCourse, ModuleRepository repositoryModule,
                             LessonRepository repositoryLesson, ModuleToModuleDTOConverter converterModuleDTO,
                             ModuleDTOToModuleConverter converterModule, UserRepository userRepository,
                             IAuthenticationPrincipal authenticationPrincipal) {
        this.repositoryCourse = repositoryCourse;
        this.repositoryModule = repositoryModule;
        this.repositoryLesson = repositoryLesson;
        this.converterModuleDTO = converterModuleDTO;
        this.converterModule = converterModule;
        this.userRepository = userRepository;
        this.authenticationPrincipal = authenticationPrincipal;
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

        Authentication user = authenticationPrincipal.getAuthentication();

        Course course = repositoryCourse.findByName(name).get();

        Set<ModuleDTO> modulesDTO = new HashSet<>();

        for(Module module : course.getModules()) {
            modulesDTO.add(converterModuleDTO.convert(module));
        }

        boolean isModulesBelongToCourse = isModulesBelongToCourse(modulesDTO, user);

        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.toString().equals("ROLE_" + UserRoles.ADMIN.name())||
                    authority.toString().equals(UserAuthorities.READ_WRITE.name())) {
                isModulesBelongToCourse = true;
            }
        }

         if (!isModulesBelongToCourse) {
            return new HashSet<>();
        }

        return modulesDTO;
    }

    @Override
    public ModuleDTO findById(Integer id) {

        Authentication user = authenticationPrincipal.getAuthentication();

        boolean isModuleBelongsToCourse = isModuleBelongsCourseId(id, user);

        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.toString().equals("ROLE_" + UserRoles.ADMIN.name())||
                    authority.toString().equals(UserAuthorities.READ_WRITE.name())) {
                isModuleBelongsToCourse = true;
            }
        }

        if (!isModuleBelongsToCourse) return null;

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

        Authentication user = authenticationPrincipal.getAuthentication();

        boolean isModuleBelongsToCourse = isModuleBelongsCourseName(name, user);

        for (GrantedAuthority authority : user.getAuthorities()) {
            if (authority.toString().equals("ROLE_" + UserRoles.ADMIN.name())||
                    authority.toString().equals(UserAuthorities.READ_WRITE.name())) {
                isModuleBelongsToCourse = true;
            }
        }

        if (!isModuleBelongsToCourse) return null;

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

    @Override
    public ModuleDTO save(ModuleDTO moduleDTO) {

        if (repositoryModule.findByName(moduleDTO.getName()).isPresent()) return null;

        Course course = repositoryCourse.findByName(moduleDTO.getCourseDTO().getName()).get();

        Module module = converterModule.convert(moduleDTO);

        module.setCourse(course);

        Module finalModule =  repositoryModule.save(module);

        return converterModuleDTO.convert(finalModule);
    }

    @Override
    public void delete(ModuleDTO moduleDTO) {
        System.out.println(moduleDTO.getName());
        Module module = repositoryModule.findByName(moduleDTO.getName()).get();
        repositoryModule.delete(module);
    }

    @Override
    public void deleteById(Integer id) {
        repositoryModule.deleteById(id);
    }

    private boolean isModuleBelongsCourseId(Integer id, Authentication user) {

        User cur = userRepository.findByEmail(user.getPrincipal().toString()).get();

        for (Course course : cur.getEnrolledCourses()) {
            for (Module module : course.getModules()) {
                if (module.getId().equals(id)) return true;
            }
        }

        return false;
    }

    private boolean isModuleBelongsCourseName(String name, Authentication user) {

        User cur = userRepository.findByEmail(user.getPrincipal().toString()).get();

        for (Course course : cur.getEnrolledCourses()) {
            for (Module module : course.getModules()) {
                if (module.getName().equals(name)) return true;
            }
        }

        return false;
    }

    private boolean isModulesBelongToCourse(Set<ModuleDTO> modulesDTO, Authentication user) {

        User cur = userRepository.findByEmail(user.getPrincipal().toString()).get();
        int count = 0;

        for (Course course : cur.getEnrolledCourses()) {
            for (Module module : course.getModules()) {
                for (ModuleDTO userModule : modulesDTO) {
                    if (module.getName().equals(userModule.getName())) {
                        count++;
                    }
                }
            }
        }


        return (modulesDTO.size() == count ) ? true : false;

    }


    //----------------------END------------------------------//
}
