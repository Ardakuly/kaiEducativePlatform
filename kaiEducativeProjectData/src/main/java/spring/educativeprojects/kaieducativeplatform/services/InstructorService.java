package spring.educativeprojects.kaieducativeplatform.services;

import org.springframework.stereotype.Service;
import spring.educativeprojects.kaieducativeplatform.dto.InstructorDTO;


public interface InstructorService extends CrudService<InstructorDTO, Integer> {

    public InstructorDTO findByName(String name);

    InstructorDTO updateInstructor(InstructorDTO instructorDTO, Integer id);
}
