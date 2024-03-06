package ru.ssau.webLabs2.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssau.webLabs2.dto.TaskDTO;
import ru.ssau.webLabs2.services.TaskService;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class TaskController {
    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    //получение списка всех задач
    @GetMapping("/{pr_id}/tasks")
    public ResponseEntity<?> getAllTasks(@PathVariable("pr_id") int id){
        List<TaskDTO> result = taskService.getListTasks(id);
        if(!result.equals(Collections.emptyList())) return new ResponseEntity<>(result, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //создание задачи
    @PostMapping("/{pr_id}/tasks")
    public ResponseEntity<?> create(@PathVariable int pr_id, @RequestBody TaskDTO task){
        TaskDTO result = taskService.add(task, pr_id);
        if (result!=null) return new ResponseEntity<>(result, HttpStatus.CREATED);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //получение задачи по id
    @GetMapping("/{pr_id}/tasks/{task_id}")
    public ResponseEntity<?>  getTaskById(@PathVariable("pr_id") int pr_id, @PathVariable("task_id") int task_id){
        TaskDTO result = taskService.getTaskById(pr_id, task_id);
        if(result!=null) return new ResponseEntity<>(result, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    //изменение задачи
    @PutMapping("/{pr_id}/tasks/{task_id}")
    public ResponseEntity<?> updateTask(@PathVariable("pr_id") int pr_id, @PathVariable("task_id") int task_id,
                          @RequestBody TaskDTO task){
        TaskDTO result = taskService.update(task, pr_id, task_id);
        if(result!=null) return new ResponseEntity<>(result, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    //удаление задачи по id
    @DeleteMapping("/{pr_id}/tasks/{task_id}")
    public ResponseEntity<?> deleteTask(@PathVariable("pr_id") int pr_id, @PathVariable("task_id") int task_id){
        taskService.deleteTask(pr_id, task_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //удаление завершенных задач
    @DeleteMapping("/{pr_id}/tasks")
    public ResponseEntity<?> deleteCompletedTasks(@PathVariable int pr_id){
        taskService.deleteCompletedTasks(pr_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
