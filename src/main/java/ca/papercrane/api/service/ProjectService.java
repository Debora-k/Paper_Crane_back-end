package ca.papercrane.api.service;

import ca.papercrane.api.project.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getAll();

    Project getByProjectId(Integer projectId);

    List<Project> getAllByClientId(Integer clientId);

    void addNewProject(Project project);

    void update(Project project);

    void save(Project project);

    void delete(Integer projectId);

    Long totalCount();

}