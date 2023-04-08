package ca.papercrane.api.service.impl;

import ca.papercrane.api.exception.ResourceNotFoundException;
import ca.papercrane.api.project.Project;
import ca.papercrane.api.repository.ProjectRepository;
import ca.papercrane.api.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public List<Project> getAll() throws ResourceNotFoundException {
        final List<Project> projectList = projectRepository.findAll();
        if (projectList.isEmpty()) {
            throw new ResourceNotFoundException("No projects found!");
        }
        return projectList;
    }

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