package ca.papercrane.api.service.impl;

import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.project.Project;
import ca.papercrane.api.repository.ProjectRepository;
import ca.papercrane.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project getByProjectId(Integer projectId) {
        return projectRepository.findByProjectId(projectId).orElseThrow(() -> new ResourceNotFoundException("Project not found!"));
    }

    @Override
    public List<Project> getAllByClientId(Integer clientId) {
        return projectRepository.findAllByClientId(clientId).orElseThrow(() -> new ResourceNotFoundException("No projects found with client id!"));
    }

    @Override
    public void addNewProject(Project project) {
        final Optional<Project> projectOptional = projectRepository.findByProjectId(project.getProjectId());
        if (projectOptional.isPresent()) {
            throw new IllegalArgumentException("Project with id already exists.");
        }
        projectRepository.save(project);
    }

    @Override
    public void update(Project project) {
        final Project existingProject = getByProjectId(project.getProjectId());
        existingProject.setClientId(project.getClientId());
        existingProject.setProjectLeadId(project.getProjectLeadId());
        existingProject.setProjectDescription(project.getProjectDescription());
        save(existingProject);
    }

    @Override
    public void save(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void delete(Integer projectId) {
        boolean exists = projectRepository.existsById(projectId);
        if (!exists) {
            throw new ResourceNotFoundException("Project with id: " + projectId + " does not exist.");
        }
        projectRepository.deleteById(projectId);
    }

    @Override
    public Long totalCount() {
        return projectRepository.count();
    }

}