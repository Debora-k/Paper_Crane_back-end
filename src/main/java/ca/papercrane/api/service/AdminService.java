package ca.papercrane.api.service;

import ca.papercrane.api.entity.Client;
import ca.papercrane.api.entity.Employee;
import ca.papercrane.api.project.Project;
import ca.papercrane.api.project.task.Task;
import ca.papercrane.api.repository.ClientRepository;
import ca.papercrane.api.repository.EmployeeRepository;
import ca.papercrane.api.repository.ProjectRepository;
import ca.papercrane.api.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
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

	@Autowired
	private EntityService entityService;

	public AdminService(EmployeeRepository employeeRepository, ClientRepository clientRepository,
			EntityService entityService) {
		this.employeeRepository = employeeRepository;
		this.clientRepository = clientRepository;
		this.entityService = entityService;
	}

	// Method to create a new project and its associated client account, task list,
	// and repository
	public void createProject(Project project) {
		// Create the project
		Project newProject = projectRepository.save(project);

		// Create a new client account associated with the project
		Client newClient = new Client();
		newClient.setClientName(project.getProjectId() + " Client"); // might need to add getName to project class and
																		// db
		newClient.setProjects(List.of(newProject));
		clientRepository.save(newClient);

		// Create a new task list associated with the project
		Task newTaskList = new Task();
		newTaskList.setName(project.getProjectId() + " Task List"); // might need to add getName to project class and db
		newTaskList.setProject(newProject);
		taskRepository.save(newTaskList);
	}

	// Method to update a project and its associated client account, task list, and
	// repository
	public void updateProject(Project project) {
		Optional<Project> optionalUpdateProject = projectRepository.findById(project.getProjectId());
		if (optionalUpdateProject.isPresent()) {
			Project existingProject = optionalUpdateProject.get();

			// Update the project
			existingProject.setProjectId(project.getProjectId());
			// we might need to add a column for a brief description for the project. lemme
			// know what you think
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

	// Method to delete a project and its associated client account, task list, and
	// repository
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

	// Method to create an employee account
		public void createEmployee(String employeeName, String employeeRole) {
	        Employee employee = new Employee();
	        employeeRepository.save(employee);
	    }
	
		// Method to update an employee account

	    public void updateEmployee(int userId, String employeeName, String employeeRole) {
	        Optional<Employee> optionalEmployee = employeeRepository.findById(userId);
	        if (optionalEmployee.isPresent()) {
	            Employee employee = optionalEmployee.get();
	            employee.setEmployeeName(employeeName);
	            employee.setRole(employeeRole);
	            employeeRepository.save(employee);
	        } else {
	            throw new EntityNotFoundException("Employee not found with user ID: " + userId);
	        }
	    }
	
	 // Method to delete an employee account
	    public void deleteEmployee(int userId) {
	        Optional<Employee> optionalEmployee = employeeRepository.findById(userId);
	        optionalEmployee.ifPresent(employee -> employeeRepository.delete(employee));
	    }
	    
	 // Method to create a client account
	    public void createClientAccount(Client client) {
	        clientRepository.save(client);
	    }

	    // Method to update a client account
	    public void updateClientAccount(Client client) {
	        Optional<Client> existingClient = clientRepository.findById(client.getUserId());
	        if (existingClient.isPresent()) {
	            Client updatedClient = existingClient.get();
	            updatedClient.setClientName(client.getClientName());
	            updatedClient.setWebsite(client.getWebsite());
	            clientRepository.save(updatedClient);
	        } else {
	            throw new EntityNotFoundException("Client not found with user ID: " + client.getUserId());
	        }
	    }

	    // Method to delete a client account
	    public void deleteClientAccount(int userId) {
	        Optional<Client> existingClient = clientRepository.findById(userId);
	        if (existingClient.isPresent()) {
	            clientRepository.delete(existingClient.get());
	        } else {
	            throw new EntityNotFoundException("Client not found with user ID: " + userId);
	        }
	    }

	 // Method to create a task list for the project

	    public void createTaskList(int projectId, int taskId, String taskName, String description, Date deadline, Date startDate, Double expectedWorkHours, Double progressInWorkHours) throws NotFoundException {
	        Project project = projectRepository.findByProjectId(projectId).orElseThrow(() -> new NotFoundException());
	        Task task = new Task();
	        task.setProject(project);
	        taskRepository.save(task);
	    }
	 // Method to modify a task list for the project
	    public void modifyTaskList(int projectId, int taskId, String taskName, String description, Date deadline, Date startDate, Double expectedWorkHours, Double progressInWorkHours) throws NotFoundException {
	        Task task = taskRepository.findByTaskId(taskId).orElseThrow(() -> new NotFoundException());
	        task.setName(taskName);
	        task.setDescription(description);
	        task.setDeadline(deadline);
	        task.setStartDate(startDate);
	        task.setExpectedWorkHours(expectedWorkHours);
	        task.setProgressInWorkHours(progressInWorkHours);
	        taskRepository.save(task);
	    }

	 // Method to delete a task list for the project
	    public void deleteTaskList(int taskId) throws NotFoundException {
	        Task task = taskRepository.findByTaskId(taskId).orElseThrow(() -> new NotFoundException());
	        taskRepository.delete(task);
	    }

	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

}
