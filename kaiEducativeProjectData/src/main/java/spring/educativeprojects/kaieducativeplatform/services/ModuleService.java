package spring.educativeprojects.kaieducativeplatform.services;

import org.springframework.stereotype.Service;
import spring.educativeprojects.kaieducativeplatform.dto.ModuleDTO;

import java.util.Set;

public interface ModuleService extends CrudService<ModuleDTO, Integer> {

    public Set<ModuleDTO> findAllByCourse(String name);

    public ModuleDTO findByName(String name);

    ModuleDTO updateModule(ModuleDTO moduleDTO, Integer id);

    ModuleDTO addLessons(ModuleDTO moduleDTO, String name);
}
