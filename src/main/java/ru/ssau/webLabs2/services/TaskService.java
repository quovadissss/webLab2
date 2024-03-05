package ru.ssau.webLabs2.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.webLabs2.dto.TaskDTO;
import ru.ssau.webLabs2.models.Project;
import ru.ssau.webLabs2.models.Task;
import ru.ssau.webLabs2.repositories.ProjectRepository;
import ru.ssau.webLabs2.repositories.TaskRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository){
        this.taskRepository = taskRepository;
        this.projectRepository=projectRepository;
    }

    public List<TaskDTO> getListTasks(int id){
        Optional<Project> project = projectRepository.findById(id);
        if(project.isPresent()){
            List<TaskDTO> result = new ArrayList<>();
            project.get().getTasks().forEach(task -> {
                result.add(TaskDTO.fromEntity(task));
            });
            return result;
        }
        else{
            return Collections.emptyList();
        }

    }
    public TaskDTO getTaskById(int pr_id, int task_id){
        Optional<Project> project = projectRepository.findById(pr_id);
        TaskDTO result = null;
        if(project.isPresent()) {
            Task task = taskRepository.findAllByIdAndTaskID(project.get(), task_id);
            if(task!=null){
                result = TaskDTO.fromEntity(task);
            }
        }
        return result;
    }

    public TaskDTO add(TaskDTO task_dto, int id){
        Optional<Project> project = projectRepository.findById(id);
        TaskDTO result = null;
        if(project.isPresent()){
            Task t = TaskDTO.toEntity(task_dto);
            t.setId(project.get());
            taskRepository.save(t);
            result = TaskDTO.fromEntity(t);
        }
        return result;
    }


    public TaskDTO update(TaskDTO task, int pr_id, int task_id){
        Optional<Project> project = projectRepository.findById(pr_id);
        TaskDTO result = null;
        if(project.isPresent()) {
            Task t = taskRepository.findAllByIdAndTaskID(project.get(), task_id);
            if (t != null) {
                t.setTask_name(task.getTask_name());
                t.setTask_description(task.getTask_description());
                t.setPlan_com_date(task.getPlan_com_date());
                t.setFlag(task.isFlag_t());
                taskRepository.save(t);
                result = TaskDTO.fromEntity(t);
            }
        }
        return result;
    }
    public void deleteTask(int pr_id, int task_id) {
        Optional<Project> project = projectRepository.findById(pr_id);
        if(project.isPresent()) {
            Task t = taskRepository.findAllByIdAndTaskID(project.get(), task_id);
            if(t!=null) {
                taskRepository.delete(t);
            }
        }
    }
    public void deleteCompletedTasks(int pr_id){
        Optional<Project> project = projectRepository.findById(pr_id);
        if(project.isPresent()) {
            taskRepository.removeByIdAndFlagIsTrue(project.get());
        }
    }


}
