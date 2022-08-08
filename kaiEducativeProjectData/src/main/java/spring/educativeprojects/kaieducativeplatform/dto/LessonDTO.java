package spring.educativeprojects.kaieducativeplatform.dto;

public class LessonDTO {

    //private Integer id;

    private String name;

    private String description;

    private String videoLink;

    private ModuleDTO moduleDTO;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public ModuleDTO getModuleDTO() {
        return moduleDTO;
    }

    public void setModuleDTO(ModuleDTO moduleDTO) {
        this.moduleDTO = moduleDTO;
    }
}
