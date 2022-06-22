package spring.educativeprojects.kaieducativeplatform.services.datajpa;

import org.springframework.stereotype.Service;
import spring.educativeprojects.kaieducativeplatform.converters.InstructorDTOToInstructorConverter;
import spring.educativeprojects.kaieducativeplatform.converters.InstructorToInstructorDTOConverter;
import spring.educativeprojects.kaieducativeplatform.dto.InstructorDTO;
import spring.educativeprojects.kaieducativeplatform.entities.Author;
import spring.educativeprojects.kaieducativeplatform.entities.Course;
import spring.educativeprojects.kaieducativeplatform.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import spring.educativeprojects.kaieducativeplatform.entities.Instructor;
import spring.educativeprojects.kaieducativeplatform.services.InstructorService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository repositoryInstructor;

    private final InstructorDTOToInstructorConverter converterInstructor;

    private final InstructorToInstructorDTOConverter converterInstructorDTO;

    @Autowired
    public InstructorServiceImpl(InstructorRepository repositoryInstructor,
                                 InstructorDTOToInstructorConverter converterInstructor,
                                 InstructorToInstructorDTOConverter converterInstructorDTO) {
        this.repositoryInstructor = repositoryInstructor;
        this.converterInstructor = converterInstructor;
        this.converterInstructorDTO = converterInstructorDTO;
    }
    //------------------ Common Methods -----------------//

    @Override
    public Set<InstructorDTO> findAll() {
        Set<InstructorDTO> instructorsDTO = new HashSet();
        repositoryInstructor.findAll().
                forEach(instructor -> instructorsDTO.add(converterInstructorDTO.convert(instructor)));
        return instructorsDTO;
    }

    @Override
    public InstructorDTO findById(Integer id) {
        Optional<Instructor> optInstructors = repositoryInstructor.findById(id);

        Instructor instructor = null;
        if(optInstructors.isPresent()) instructor = optInstructors.get();

        return converterInstructorDTO.convert(instructor);
    }

    @Override
    public InstructorDTO findByName(String name) {
        Optional<Instructor> optInstructors = repositoryInstructor.findByName(name);

        Instructor instructor = null;
        if(optInstructors.isPresent()) instructor = optInstructors.get();

        return converterInstructorDTO.convert(instructor);
    }

    @Override
    public InstructorDTO updateInstructor(InstructorDTO instructorDTO, Integer id) {
        Instructor instructor = converterInstructor.convert(instructorDTO);
        instructor.setId(id);
        Instructor instructorReturned = repositoryInstructor.save(instructor);
        return converterInstructorDTO.convert(instructorReturned);
    }

    @Override
    public InstructorDTO save(InstructorDTO instructorDTO) {
         Instructor instructor = repositoryInstructor.save(converterInstructor.convert(instructorDTO));

         return converterInstructorDTO.convert(instructor);
    }

    @Override
    public void delete(InstructorDTO instructorDTO) {
        Instructor instructor = repositoryInstructor.findByName(instructorDTO.getName()).get();
        repositoryInstructor.delete(instructor);
    }

    @Override
    public void deleteById(Integer id) {
        repositoryInstructor.deleteById(id);
    }

    //----------------------END------------------------------//
}
