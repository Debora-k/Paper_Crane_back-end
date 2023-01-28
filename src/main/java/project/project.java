package project;


public class project {
	
	private String projName;
	private String clientName;
	private String clientEmail;
	private String taskListName;
	private String task;
	private String projReportName;
	
	project project1 = new project();
	
	public project() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private project manage_project(project x) {
		//take project as an input
		//do some stuff to the project
		//return the updated project
		return x;
		
	}
	
	private void create_client_account(String name, String address,
			String email, int phoneNumber, String username, String password) {
		//take in name, address, email, etc
		//make the account and maybe return it?
		//otherwise just store it in the database
	}
	
	private void create_task_list(String listName) {
		//take in task list name
		//make task list
		//return and or store in database
	}
	
	private void add_to_tasklist(String listName, String task) {
		//take in task list name
		//add the task to it
		//return or update the database
	}
	
	private void generate_proj_report() {
		//tbh dont know what to do here yet
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getTaskListName() {
		return taskListName;
	}

	public void setTaskListName(String taskListName) {
		this.taskListName = taskListName;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getProjReportName() {
		return projReportName;
	}

	public void setProjReportName(String projReportName) {
		this.projReportName = projReportName;
	}

}