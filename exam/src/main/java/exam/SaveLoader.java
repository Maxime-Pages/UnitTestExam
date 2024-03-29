package exam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveLoader {
    
    public static ArrayList<Task> loadTasksFromFile(String path) throws IOException{
        return parseToTasks(readFile(path));
    }

    public static void saveTasksToFile(String path, ArrayList<Task> tasks) throws IOException{
        saveFile(path, parseToStrings(tasks));
    }

    public static ArrayList<String> readFile(String path) throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            ArrayList<String> lines = new ArrayList<>();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                lines.add(line);
            }
            return lines;
        }
    }

    public static ArrayList<Task> parseToTasks(ArrayList<String> lines){
        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            tasks.add(Task.fromString(line));
        }
        return tasks;
    }


    public static ArrayList<String> parseToStrings(ArrayList<Task> tasks){
        ArrayList<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            lines.add(task.toString());
        }
        return lines;
    }
    
    public static void saveFile(String path, ArrayList<String> tasks) throws IOException{
        try (FileWriter writer = new FileWriter(path)) {
            for (String task : tasks) {
                writer.write(task + "\n");
            }
        }
    }

}
