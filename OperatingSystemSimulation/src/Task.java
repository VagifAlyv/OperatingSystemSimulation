 import java.time.LocalDateTime;
public class Task implements Comparable<Task>{

	private String taskType;
	private int burstTime;
	private LocalDateTime arrivalDateTime;
	private int priority;
	
	public Task(String taskType, int burstTime, LocalDateTime arrivalDateTime) {
		this.taskType = taskType;
		this.burstTime = burstTime;
		this.arrivalDateTime = arrivalDateTime;
		priority = findPriority();
		
	}
	
	public String getTaskType() {
		return taskType;
	}

	public int getBurstTime() {
		return burstTime;
	}
	
	public LocalDateTime getArrivalDateTime() {
		return arrivalDateTime;
	}
	
	public int getPriority() {
		return priority;
	}
	
	private int findPriority() {
		int priority = 0;
		switch(taskType) {
		case "security management":
			priority = 6;
			break;
		case "process management":
			priority = 5;
			break;
		case "memory management":
			priority = 4;
			break;
		case "user management":
			priority = 3;
			break;
		case "device management":
			priority = 2;
			break;
		case "file management":
			priority = 1;
			break;
		}
		return priority;
	}
	
	@Override
	public int compareTo(Task other) {
        return this.arrivalDateTime.compareTo(other.arrivalDateTime);
    }
	
	public String toString() {
		return (taskType + ", " + burstTime + ", " + SimulationProcess.formatDateTime(arrivalDateTime));
	}
	
}
