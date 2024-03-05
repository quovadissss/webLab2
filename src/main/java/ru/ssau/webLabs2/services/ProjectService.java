package ru.ssau.webLabs2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.webLabs2.dto.ProjectDTO;
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

    public ProjectDTO getProjectById(int pr_id){
        Optional<Project> pr = projectRepository.findById(pr_id);
        if(pr.isPresent()){
            return ProjectDTO.fromEntity(pr.get());
        }
        else return null;
    }

    public ProjectDTO createProject(ProjectDTO projectDTO){
        projectRepository.save(ProjectDTO.toEntity(projectDTO));
        return projectDTO;
    }

    public ProjectDTO updateProject(int id, ProjectDTO dto){
        Optional<Project> pr = projectRepository.findById(id);
        if(pr.isPresent()){
            Project p = ProjectDTO.toEntity(dto);
            pr.get().setName(p.getName());
            pr.get().setDescription(p.getDescription());
            pr.get().setEnd_data(p.getEnd_data());
            pr.get().setStart_data(p.getStart_data());
            return ProjectDTO.fromEntity(projectRepository.save(pr.get()));
        }
        else return null;
    }
    public void deleteProject(int id){
        Optional<Project> pr = projectRepository.findById(id);
        pr.ifPresent(projectRepository::delete);
    }

    public Map<Integer,Integer> getNumUncompletedTasks(){
        List<Integer[]> l = projectRepository.getNumUncompletedTasks();
        Map<Integer, Integer> res = new LinkedHashMap<>();
        for(Integer[] i : l){
            res.put(i[0],i[1]);
        }
        return res;

    }

    public List<ProjectDTO> getFilterProjects(String text){
        List<Project> l = projectRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(text, text);
        List<ProjectDTO> res = new ArrayList<>();
        for (Project p: l){
            res.add(ProjectDTO.fromEntity(p));
        }
        return res;
    }



}
