package ca.papercrane.api.service.impl;

import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.project.Project;
import ca.papercrane.api.repository.ProjectRepository;
import ca.papercrane.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project findById(Integer projectId) {
        return projectRepository.findById(projectId).orElseThrow(() -> new ResourceNotFoundException("Project not found!"));
    }

    @Override
    public Integer createProject(Integer clientId, Integer projectLeadId, String description) {
        final Project createdProject = projectRepository.save(new Project(clientId, projectLeadId, description));
        return createdProject.getProjectId();
    }

    @Override
    public Integer createProject(Project project) {
        final Project createdProject = projectRepository.save(project);
        return createdProject.getProjectId();
    }

    @Override
    public void updateProject(Project project) {
        final Project existingProject = findById(project.getProjectId());
        existingProject.setClientId(project.getClientId());
        existingProject.setProjectLeadId(project.getProjectLeadId());
        existingProject.setProjectDescription(project.getProjectDescription());
        projectRepository.save(existingProject);
    }

    @Override
    public void save(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void delete(Project project) {
        projectRepository.delete(project);
    }

    @Override
    public void deleteById(Integer projectId) {
        final Project project = findById(projectId);
        projectRepository.delete(project);
    }

    @Override
    public List<Project> findByClientId(Integer clientId) {
        return projectRepository.findAllById(clientId).orElseThrow(() -> new ResourceNotFoundException("Projects not found with clientId"));
    }

    @Override
    public Long totalCount() {
        return projectRepository.count();
    }

}