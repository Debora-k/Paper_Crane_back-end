package ca.papercrane.api.service;

import ca.papercrane.api.project.Project;
import ca.papercrane.api.repository.ProjectRepository;
import ca.papercrane.api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * A Service class for retrieving data from the {@link ProjectRepository}
 */
@Service
@Transactional
public class ProjectService {

    @Autowired
    private final ProjectRepository projectRepository;

    @Autowired
    private final TaskRepository taskRepository;

    /**
     * Creates a new ProjectService.
     *
     * @param projectRepository The project repository.
     * @param taskRepository    The task repository.
     */
    public ProjectService(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    /**
     * Creates a new Project and saves it into the database.
     *
     * @param clientId      The id of the client the project is for.
     * @param projectLeadId The id of the project leader employee.
     * @return the newly created project.
     */
    public Project createProject(Integer clientId, Integer projectLeadId) {
        //TODO: Validate clientId and projectLeadId
        return save(new Project(clientId, projectLeadId));
    }

    /**
     * Updates a Project.
     * <p>
     * TODO: we might need to add a column for a brief description for the project.
     *
     * @param project The project being updated.
     */
    public void updateProject(Project project) {
        final Optional<Project> existingProjectOptional = projectRepository.findById(project.getProjectId());
        if (existingProjectOptional.isPresent()) {
            final Project existingProject = existingProjectOptional.get();
            existingProject.setClientId(project.getClientId());
            existingProject.setProjectLeadId(project.getProjectLeadId());
            projectRepository.save(existingProject);
        } else {
            //TODO: Exception.
        }
    }

    /**
     * Deletes a Project from the database.
     *
     * @param project The project being deleted.
     */
    public void deleteProject(Project project) {
        final Optional<Project> optionalProject = projectRepository.findByProjectId(project.getProjectId());
        if (optionalProject.isPresent()) {
            final Project existingProject = optionalProject.get();
            // Delete all tasks associated with the project.
            taskRepository.deleteAllByProjectId(existingProject.getProjectId());
            // Delete the project.
            projectRepository.delete(existingProject);
        }
    }

    /**
     * Returns an Optional containing a Project that exists with the specified projectId.
     *
     * @param projectId The projectId being searched for.
     * @return An Optional containing the found Project, or an empty Optional if no Project is found with the specified projectId.
     */
    public Optional<Project> getProjectById(Integer projectId) {
        return projectRepository.findByProjectId(projectId);
    }

    /**
     * Returns a list of Project objects that belong to the same clientId.
     *
     * @param clientId The clientId that these projects are for.
     * @return The list of Project objects.
     */
    public List<Project> getAllByClientId(Integer clientId) {
        return projectRepository.findByClientId(clientId);
    }

    /**
     * Saves a Project to the database.
     *
     * @param project The project being saved into the database.
     * @return the project.
     */
    public Project save(Project project) {
        //TODO: Check for the following before adding a project to the database.
        //TODO: Validate the project was created correctly.
        //TODO: clientId must not be null, clientId must point to a valid client saved within the database.
        //TODO: projectLeadId must not be null, projectLeadId must point to a valid employee within the database.
        return projectRepository.save(project);
    }

    /**
     * Deletes a project from the database.
     *
     * @param project the project being deleted.
     */
    public void delete(Project project) {
        //TODO: Checks or validation before deleting.
        projectRepository.delete(project);
    }

    /**
     * Deletes a project by the specified projectId.
     *
     * @param projectId the id of the project being deleted.
     */
    public void deleteById(Integer projectId) {
        //TODO: Checks or validation before deleting.
        projectRepository.deleteById(projectId);
    }

    /**
     * Returns the total amount of projects stored within the repository.
     *
     * @return the count.
     */
    public Long totalProjectCount() {
        return projectRepository.count();
    }

}