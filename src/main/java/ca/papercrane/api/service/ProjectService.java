package ca.papercrane.api.service;

import ca.papercrane.api.project.Project;

import java.util.List;

public interface ProjectService {

    Project findById(Integer projectId);

    Integer createProject(Integer clientId, Integer projectLeadId, String description);

    Integer createProject(Project project);

    void updateProject(Project project);

    void save(Project project);

    void delete(Project project);

    void deleteById(Integer projectId);

    List<Project> findByClientId(Integer clientId);

    Long totalCount();

}