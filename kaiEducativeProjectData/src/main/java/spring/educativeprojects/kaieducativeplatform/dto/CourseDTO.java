package spring.educativeprojects.kaieducativeplatform.dto;


import java.util.HashSet;
import java.util.Set;

public class CourseDTO {

    //private Integer id;
    private String name;
    private AuthorDTO authorDTO;
    private InstructorDTO instructorDTO;

    private String description;

    private SphereDTO sphereDTO;

    private Set<ModuleDTO> modulesDTO = new HashSet<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuthorDTO getAuthorDTO() {
        return authorDTO;
    }

    public void setAuthorDTO(AuthorDTO authorDTO) {
        this.authorDTO = authorDTO;
    }

    public InstructorDTO getInstructorDTO() {
        return instructorDTO;
    }

    public void setInstructorDTO(InstructorDTO instructorDTO) {
        this.instructorDTO = instructorDTO;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SphereDTO getSphereDTO() {
        return sphereDTO;
    }

    public void setSphereDTO(SphereDTO sphereDTO) {
        this.sphereDTO = sphereDTO;
    }

    public Set<ModuleDTO> getModulesDTO() {
        return modulesDTO;
    }

    public void setModulesDTO(Set<ModuleDTO> modulesDTO) {
        this.modulesDTO = modulesDTO;
    }
}
