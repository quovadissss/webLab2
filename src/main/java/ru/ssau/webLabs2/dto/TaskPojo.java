package ru.ssau.webLabs2.dto;

import lombok.Getter;
import lombok.Setter;
import ru.ssau.webLabs2.models.Task;
import java.time.LocalDate;


@Getter
@Setter
public class TaskPojo {
    private int task_id;
    private String task_name;
    private String task_description;
    private LocalDate plan_com_date;
    private boolean flag_t;

   public static Task toEntity(TaskPojo task){
        Task t = new Task();
        t.setTaskID(task.getTask_id());
        t.setTask_name(task.getTask_name());
        t.setTask_description(task.getTask_description());
        t.setFlag(task.isFlag_t());
        t.setPlan_com_date(task.getPlan_com_date());
        return t;
   }
    public static TaskPojo fromEntity(Task task){
        TaskPojo t= new TaskPojo();
        t.setTask_id(task.getTaskID());
        t.setTask_name(task.getTask_name());
        t.setTask_description(task.getTask_description());
        t.setFlag_t(task.isFlag());
        t.setPlan_com_date(task.getPlan_com_date());
        return t;
    }
}
