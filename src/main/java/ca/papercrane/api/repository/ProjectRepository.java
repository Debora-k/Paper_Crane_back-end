package ca.papercrane.api.repository;

import ca.papercrane.api.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Optional<Project> findByProjectId(Integer projectId);

    List<Project> findByClientId(Integer clientId);

    void delete();

    void deleteById(Integer projectId);

}