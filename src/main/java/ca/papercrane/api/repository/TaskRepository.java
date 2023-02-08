package ca.papercrane.api.repository;

import ca.papercrane.api.project.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    Optional<Task> findByTaskId(Integer taskId);

    List<Task> findByProjectId(Integer projectId);

}