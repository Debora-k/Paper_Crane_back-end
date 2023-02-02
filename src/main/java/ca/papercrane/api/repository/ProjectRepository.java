package ca.papercrane.api.repository;

import ca.papercrane.api.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    //TODO: Probably use Optional here instead.
    Project findByProjectId(Integer projectId);

    List<Project> findByClientId(Integer clientId);

}