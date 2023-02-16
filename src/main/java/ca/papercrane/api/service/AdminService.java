package ca.papercrane.api.service;

import ca.papercrane.api.entity.Client;
import ca.papercrane.api.entity.Employee;
import ca.papercrane.api.project.Project;
import ca.papercrane.api.project.task.Task;
import ca.papercrane.api.repository.ClientRepository;
import ca.papercrane.api.repository.EmployeeRepository;
import ca.papercrane.api.repository.ProjectRepository;
import ca.papercrane.api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminService {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private TaskRepository taskRepository;
    
    // Method to create a new project and its associated client account, task list, and repository
    public void createProject(Project project) {
        // Create the project
        Project newProject = projectRepository.save(project);
        
        // Create a new client account associated with the project
        Client newClient = new Client();
        newClient.setClientName(project.getProjectId() + " Client"); // might need to add getName to project class and db
        newClient.setProjects(List.of(newProject));
        clientRepository.save(newClient);
        
        // Create a new task list associated with the project
        Task newTaskList = new Task();
        newTaskList.setName(project.getProjectId() + " Task List"); // might need to add getName to project class and db
        newTaskList.setProject(newProject);
        taskRepository.save(newTaskList);
    }
    
    // Method to update a project and its associated client account, task list, and repository
    public void updateProject(Project project) {
        Optional<Project> optionalUpdateProject = projectRepository.findById(project.getProjectId());
        if (optionalUpdateProject.isPresent()) {
            Project existingProject = optionalUpdateProject.get();
            
            // Update the project
            existingProject.setProjectId(project.getProjectId());
            //we might need to add a column for a brief description for the project. lemme know what you think
           // existingProject.setDescription(project.getDescription()); 
            projectRepository.save(existingProject);
            
            // Update the client account
            Optional<Client> optionalClient = clientRepository.findById(existingProject.getProjectId());
            if (optionalClient.isPresent()) {
                Client existingClient = optionalClient.get();
                existingClient.setClientName(project.getProjectId() + " Client");
                clientRepository.save(existingClient);
            }
            
            // Update the task list
            Optional<Task> optionalTaskList = taskRepository.findByTaskId(existingProject.getProjectId());
            if (optionalTaskList.isPresent()) {
                Task existingTaskList = optionalTaskList.get();
                existingTaskList.setName(project.getProjectId() + " Task List");
                taskRepository.save(existingTaskList);
            }
        }
    }
    
    // Method to delete a project and its associated client account, task list, and repository
    public void deleteProject(Project project) {
        Optional<Project> optionalProject = projectRepository.findByProjectId(project.getProjectId());
        if (optionalProject.isPresent()) {
            Project existingProject = optionalProject.get();
            
            // Delete the project
            projectRepository.delete(existingProject);
            
            // Delete the client account
            Optional<Client> optionalClient = clientRepository.findById(existingProject.getProjectId());
            if (optionalClient.isPresent()) {
                Client existingClient = optionalClient.get();
                clientRepository.delete(existingClient);
            }
            
            // Delete the task list
            Optional<Task> optionalTaskList = taskRepository.findByTaskId(existingProject.getProjectId());
            if (optionalTaskList.isPresent()) {
                Task existingTaskList = optionalTaskList.get();
                taskRepository.delete(existingTaskList);
            }
        }
    }
    
 // Method to assign employees to a project and its associated task list
    public void assignEmployeesToProject(Project project, List<Employee> employees) {
        // Get the task list associated with the project
        List<Task> taskList = taskRepository.findByProjectId(project.getProjectId());

        // If the task list is not found, throw a TaskNotFoundException
        if (taskList.isEmpty()) {
            throw new RuntimeException("No task list found for project " + project.getProjectId());
        }

        // Assign the employees to the task list
        for (Task task : taskList) {
            task.setEmployees(employees);
            taskRepository.save(task);
        }
    }
    
    
    

    
    
    
    
}
