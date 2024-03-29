package exam;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        
        if (args.length != 1) {
            System.out.println("Usage: java -jar exam.jar");
            return;
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        TaskManager taskManager = new TaskManager(args[0]);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                SaveLoader.saveTasksToFile(args[0], taskManager.getTasks());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        System.out.println(taskManager.display());
        while (true) {
            //Read Stdin
            String input = System.console().readLine();
            if (input == null) continue;
            if (taskManager.handleInput(input)) break; //handleinput returns true if the program should exit
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(taskManager.display());
            
        }

    }
}