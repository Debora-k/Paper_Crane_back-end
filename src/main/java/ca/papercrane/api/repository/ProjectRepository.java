package ca.papercrane.api.repository;

import ca.papercrane.api.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Optional<Project> findByProjectId(Integer projectId);

    Optional<List<Project>> findAllByClientId(Integer clientId);

}