package ru.ssau.webLabs2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ssau.webLabs2.dto.ProjectPojo;
import ru.ssau.webLabs2.services.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService pr){
        this.projectService = pr;
    }

    //получение проекта по id
    @GetMapping("/{pr_id}")
    public ResponseEntity<?> getProjectById(@PathVariable("pr_id") int id){
        ProjectPojo pr = projectService.getProjectById(id);
        if(pr!=null) return new ResponseEntity<>(pr, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //создание проекта
    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody ProjectPojo projectDTO){
        ProjectPojo pr = projectService.createProject(projectDTO);
        return new ResponseEntity<>(pr, HttpStatus.CREATED);
    }
    //обновление проекта
    @PutMapping("/{pr_id}")
    public ResponseEntity<?> updateProject(@PathVariable("pr_id") int id, @RequestBody ProjectPojo projectDTO){
        ProjectPojo pr = projectService.updateProject(id, projectDTO);
        if(pr!=null) return new ResponseEntity<>(pr, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //удаление проекта
    @DeleteMapping("/{pr_id}")
    public ResponseEntity<?> deleteProject(@PathVariable("pr_id") int id){
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //получить ассоциативный массив с id проектов и количеством незавершенных задач
    @GetMapping
    public ResponseEntity<?> getNumUncompletedTasks(){
        return new ResponseEntity<>(projectService.getNumUncompletedTasks(), HttpStatus.OK);

    }

    //получить список проектов с фильтрацией
    @GetMapping("/filter")
    public ResponseEntity<?> getProjectsFilter(@RequestParam("search") String text){
        return new ResponseEntity<>(projectService.getFilterProjects(text), HttpStatus.OK);

    }
}
