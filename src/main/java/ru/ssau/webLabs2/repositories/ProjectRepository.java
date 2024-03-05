package ru.ssau.webLabs2.repositories;

import lombok.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.ssau.webLabs2.models.Project;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query(value = "select p.id, count(t.flag) from Project p inner join p.tasks t where t.flag = false group by p.id")
    List<Integer[]> getNumUncompletedTasks();
    List<Project> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String s, String s2);

}
