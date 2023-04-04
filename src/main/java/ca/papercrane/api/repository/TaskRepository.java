package ca.papercrane.api.repository;

import ca.papercrane.api.project.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Optional<Task> findByTaskId(Integer taskId);

    Optional<List<Task>> findAllByProjectId(Integer projectId);

}