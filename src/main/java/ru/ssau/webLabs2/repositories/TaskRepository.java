package ru.ssau.webLabs2.repositories;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ssau.webLabs2.models.Project;
import ru.ssau.webLabs2.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
        Task findAllByIdAndTaskID(Project pr_id, int task_id);
        @Transactional
        void removeByIdAndFlagIsTrue(Project pr_id);


}
