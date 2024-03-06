package ru.ssau.webLabs2.repositories;

import lombok.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ssau.webLabs2.models.Project;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    @Query(value = "select p.id, count(t.flag) from Project p inner join p.tasks t where t.flag = false group by p.id")
    List<Integer[]> getNumUncompletedTasks();
    @Query(value = "select* from project p where p.name_pr ilike '%' || :text ||'%' " +
            "or p.project_description ilike '%' || :text ||'%'", nativeQuery = true)
    List<Project> selectByText(@Param("text") String text);


}
