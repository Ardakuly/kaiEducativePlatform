package spring.educativeprojects.kaieducativeplatform.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import spring.educativeprojects.kaieducativeplatform.dto.InstructorDTO;

import java.util.Set;


public interface InstructorService extends CrudService<InstructorDTO, Integer> {

    public InstructorDTO findByName(String name);

    InstructorDTO updateInstructor(InstructorDTO instructorDTO, Integer id);

    InstructorDTO getInstructorByCourse(String name);
}
