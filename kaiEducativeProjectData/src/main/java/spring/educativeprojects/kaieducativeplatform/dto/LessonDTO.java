package spring.educativeprojects.kaieducativeplatform.dto;

public class LessonDTO {

    //private Integer id;

    private String name;

    //need to add video

    private ModuleDTO moduleDTO;

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ModuleDTO getModuleDTO() {
        return moduleDTO;
    }

    public void setModuleDTO(ModuleDTO moduleDTO) {
        this.moduleDTO = moduleDTO;
    }
}
