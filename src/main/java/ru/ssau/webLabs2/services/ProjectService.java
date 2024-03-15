package ru.ssau.webLabs2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.webLabs2.dto.ProjectPojo;
import ru.ssau.webLabs2.models.Project;
import ru.ssau.webLabs2.repositories.ProjectRepository;

import java.util.*;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    public ProjectPojo getProjectById(int pr_id){
        Optional<Project> pr = projectRepository.findById(pr_id);
        if(pr.isPresent()){
            return ProjectPojo.fromEntity(pr.get());
        }
        else return null;
    }

    public ProjectPojo createProject(ProjectPojo projectDTO){
        Project p = ProjectPojo.toEntity(projectDTO);
        projectRepository.save(p);
        return ProjectPojo.fromEntity(p);
    }

    public ProjectPojo updateProject(int id, ProjectPojo dto){
        Optional<Project> pr = projectRepository.findById(id);
        if(pr.isPresent()){
            Project p = ProjectPojo.toEntity(dto);
            pr.get().setName(p.getName());
            pr.get().setDescription(p.getDescription());
            pr.get().setEnd_data(p.getEnd_data());
            pr.get().setStart_data(p.getStart_data());
            return ProjectPojo.fromEntity(projectRepository.save(pr.get()));
        }
        else return null;
    }
    public void deleteProject(int id){
        Optional<Project> pr = projectRepository.findById(id);
        pr.ifPresent(projectRepository::delete);
    }

    public Map<Integer,Integer> getNumUncompletedTasks(){
        Map<Integer, Integer> res = new LinkedHashMap<>();
        for(Integer[] i : projectRepository.getNumUncompletedTasks()){
            res.put(i[0],i[1]);
        }
        return res;

    }

    public List<ProjectPojo> getFilterProjects(String text){
        List<ProjectPojo> res = new ArrayList<>();
        for (Project p: projectRepository.selectByText(text)){
            res.add(ProjectPojo.fromEntity(p));
        }
        return res;
    }



}
