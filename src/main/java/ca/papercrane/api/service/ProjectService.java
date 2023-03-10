package ca.papercrane.api.service;

import ca.papercrane.api.project.Project;

import java.util.List;

public interface ProjectService {

    Project getByProjectId(Integer projectId);

    List<Project> getAllByClientId(Integer clientId);

    Integer create(Integer clientId, Integer projectLeadId, String description);

    Integer create(Project project);

    void update(Project project);

    void save(Project project);

    void delete(Project project);

    void deleteById(Integer projectId);

    Long totalCount();

}