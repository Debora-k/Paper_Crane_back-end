package ca.papercrane.api.service;

import ca.papercrane.api.project.Project;

public interface ProjectService {

    Project findById(Integer projectId);

    void save(Project project);

    Long totalCount();

}