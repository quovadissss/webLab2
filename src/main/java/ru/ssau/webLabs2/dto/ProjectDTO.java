package ru.ssau.webLabs2.dto;
import lombok.Getter;
import lombok.Setter;
import ru.ssau.webLabs2.models.Project;
import ru.ssau.webLabs2.models.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProjectDTO {

    private int pr_id;
    private String name_pr;
    private String project_description;
    private LocalDate start_data;
    private LocalDate end_data;
    private List<TaskDTO> tasks;

    public static ProjectDTO fromEntity(Project pr){
        ProjectDTO p = new ProjectDTO();
        p.setPr_id(pr.getId());
        p.setName_pr(pr.getName());
        p.setProject_description(pr.getDescription());
        p.setStart_data(pr.getStart_data());
        p.setEnd_data(pr.getEnd_data());
        p.setProject_description(pr.getDescription());
        return p;

    }

    public static Project toEntity(ProjectDTO pr){
        Project p = new Project();
        p.setId(pr.getPr_id());
        p.setName(pr.getName_pr());
        p.setDescription(pr.getProject_description());
        p.setStart_data(pr.getStart_data());
        p.setEnd_data(pr.getEnd_data());
        p.setDescription(pr.getProject_description());
        List<Task>  tasks = new ArrayList<>();
        p.setTasks(tasks);
        return p;

    }
}
