package ca.papercrane.api.service;

import java.io.File;
import java.util.List;

import ca.papercrane.api.entity.Client;
import ca.papercrane.api.project.Project;
import ca.papercrane.api.project.task.Task;
import ca.papercrane.api.repository.ProjectRepository;
import ca.papercrane.api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class ClientServiceImpl {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    // need to add new table to DB to allow the client to
    // ■ submit tickets or requests for work and scope changes as specified in Capstone Brief doc

    //private TicketRepository ticketRepository;

    // Method to view a project
    public Project viewProject(int projectId) {
        return projectRepository.findByProjectId(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    // Method to submit changes to tasks for a project
    public void submitTaskChanges(int projectId, List<Task> tasks) {
        Project project = projectRepository.findByProjectId(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found")); 
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            task.setName(project.getProjectId() + " Task " + (i + 1));
            taskRepository.save(task);
        }

    }

    // Method to calculate completion percentage for a project
    public Double calculateCompletionPercentage(int projectId) {
        Project project = projectRepository.findByProjectId(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        List<Task> tasks = taskRepository.findAllByProjectId(project.getProjectId());

        if (tasks.isEmpty()) {
            return 0.0;
        }

        double totalProgress = 0.0;
        double totalExpectedWorkHours = 0.0;

        for (int i = 0; i < tasks.size(); i++) {
            totalProgress += tasks.get(i).getProgressInWorkHours();
            totalExpectedWorkHours += tasks.get(i).getExpectedWorkHours();
        }

        return (totalProgress / totalExpectedWorkHours) * 100;
    }

   
    
    

    // Method to submit a ticket or request for work and scope changes
    // need to create Ticket table in the DB + add Ticket class + TicketRepository
  /*
   *  public void submitTicket(int projectId, Ticket ticket) {
   *  }
   */
        
    
}