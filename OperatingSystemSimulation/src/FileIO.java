import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileIO {
	private LList<Task> allTasks;
	
	public FileIO() {
		allTasks = new LList<Task>();
	}
	
	public LList<Task> readFile(String fileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy,HH:mm");
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String taskType = parts[0].trim();
                int burstTime = Integer.parseInt(parts[1].trim());
                LocalDateTime arrivalDateTime = LocalDateTime.parse(parts[2].trim() + "," + parts[3].trim(), formatter);
                
                
                Task task = new Task(taskType, burstTime, arrivalDateTime);
                allTasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allTasks;
    }
}

