package spring.educativeprojects.kaieducativeplatform.services;

import org.springframework.stereotype.Service;
import spring.educativeprojects.kaieducativeplatform.dto.ModuleDTO;

public interface ModuleService extends CrudService<ModuleDTO, Integer> {
    public ModuleDTO findByName(String name);

    ModuleDTO updateModule(ModuleDTO moduleDTO, Integer id);

    ModuleDTO addLessons(ModuleDTO moduleDTO, String name);
}
