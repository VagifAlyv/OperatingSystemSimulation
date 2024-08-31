 import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SimulationProcess {
	private FileIO file;
	private LList<Task> allTasks;
	private LinkedSortedList<Task> taskList;
	private LinkedPriorityQueue<Task> waitingPriorityLine;
	private LinkedStack<Task> waitingBurstTime;
	
	public SimulationProcess() {
		file = new FileIO();
		//reading tasks from text and adding them to allTasks
		allTasks = file.readFile("tasks.txt");
		
		taskList = new LinkedSortedList<>();
		waitingPriorityLine = new LinkedPriorityQueue<>();
		waitingBurstTime = new LinkedStack<>();
	}
	
	public void simulation() {
		
		//adding all tasks to Sorted List
		for(int i = 1; i <= allTasks.getLength(); i++) {
			taskList.add(allTasks.getEntry(i));
		}
		
		//Listed Tasks
		System.out.println("List of Tasks(2):");
		taskList.display();
		
		//Adding tasks to 'Waiting Burst Time' and 'Waiting Priority Line'
		for(int i = 1; i <= taskList.getLength(); i++) {
        	Task task = taskList.getEntry(i);
        	waitingBurstTime.push(task);
        	waitingPriorityLine.enqueue(task);
        }
		
		//Waiting Priority Line
		System.out.println();
		System.out.println("Waiting Priority Line(4.a.): ");
		waitingPriorityLine.display();
		System.out.println();
	    executionOfWaitingPriorityLine(waitingPriorityLine);
	    
	    //Waiting Burst Time
	    System.out.println();
	    System.out.println("Waiting Burst Time(4.b.)");
	    waitingBurstTime.display();
	    System.out.println();
	    executionOfWaitingBurstTime(waitingBurstTime);
	}
	
	 
	 private void executionOfWaitingPriorityLine(LinkedPriorityQueue<Task> tasks) {
		 System.out.println("Remaning Tasks after 5 execution n in the waiting priority line (6): ");
		 while(!tasks.isEmpty()) {
			 if(tasks.getSize() >= 5) {
				 for(int i = 0; i < 5; i++) {
					 tasks.dequeue();
				 }
			 }
			 else {
				 while(!tasks.isEmpty()) {
					tasks.dequeue(); 
				 }
			 }
			 
			 if(!tasks.isEmpty()) {
				 tasks.display();
				 System.out.println("Remaning Tasks: ");
			 }
		 }
		 System.out.println("No task is left. All tasks have been executed.");
	 }
	 
	 private void executionOfWaitingBurstTime(LinkedStack<Task> tasks) {
		 System.out.println("Remaning Tasks after 5 execution in the pile of waiting burst time (6): ");
		 while(!tasks.isEmpty()) {
			 if(tasks.size() >= 5) {
				 for(int i = 0; i < 5; i++) {
					 tasks.pop();
				 }
			 }
			 else {
				 while(!tasks.isEmpty()) {
					tasks.pop(); 
				 }
			 }
			 
			 if(!tasks.isEmpty()) {
				 tasks.display();
				 System.out.println("Remaning Tasks: ");
			 }
		 }
		 System.out.println("No task is left. All tasks have been executed.");
	 }
	 
	 public static String formatDateTime(LocalDateTime dateTime) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	        return dateTime.format(formatter);
	 }
	
	
}