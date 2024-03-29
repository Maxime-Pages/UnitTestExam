package exam;

import java.util.ArrayList;

public class TaskManager {
    private enum Mode {
        START,
        LIST,
        DETAILS,
        ADD,
    }
    
    private ArrayList<Task> tasks;
    
    private Mode currentmode;
    
    private int currentPage;
    private int selectedTask;
    
    public void addTask(Task newtask) {
        for (Task task : tasks) {
            if (task.getName().equals(newtask.getName())) {
                return;
            }
        }
        tasks.add(newtask);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public TaskManager(String path) {
        currentPage = 0;
        currentmode = Mode.START;
        if (path == null) {
            tasks = new ArrayList<>();
        } else {
            try {
                tasks = SaveLoader.loadTasksFromFile(path);
            } catch (Exception e) {
                tasks = new ArrayList<>();
            }
        }
    }

    public Boolean handleInput(String input) {
        switch (currentmode) {
            case ADD:
                return handleAdd(input);
        
                case DETAILS:
                return handleDetails(input);
                
                case LIST:
                return handleList(input);
                default:
                return false;
            }
        }
        
        public Boolean handleDetails(String input) {
            switch (input) {
                case "D":
                case "d":
                    tasks.get(selectedTask).dough();
                    return false;
                case "R":
                case "r":
                    removeTask(tasks.get(selectedTask));
                    currentmode = Mode.LIST;
                    return false;
                default:
                    currentmode = Mode.LIST;
                    return false;
            }
        }
        
        public Boolean handleList(String input) {
            switch (input) {
                case "A":
                case "a":
                    currentmode = Mode.ADD;
                    return false;
                case "X":
                case "x":
                    return true;
                case "U":
                case "u":
                    if (currentPage == 0) {
                        currentPage = tasks.size() == 0 ?  0 : (tasks.size() / 10);
                    } else {
                        currentPage--;
                    }
                    return false;
                case "D":
                case "d":
                    currentPage = (currentPage + 1) % ((tasks.size() == 0 ?  0 : (tasks.size() / 10)) + 1) ;
                    return false;
                default:
                    try {
                        int index = Integer.parseInt(input);
                        if (index < 0 || index >= tasks.size()) {
                            return false;
                        }
                        selectedTask = index+currentPage*10;
                        currentmode = Mode.DETAILS;
                        return false;
                    } 
                    catch (NumberFormatException e) {
                        return false;
                    }
                }
            }
        
        public Boolean handleAdd(String input) throws IllegalArgumentException {
            if (input == null) {currentmode= Mode.LIST; return false;}
            
            input = input.trim();
            String[] parts = input.split("\\|");
            if (parts.length != 2) {currentmode= Mode.LIST; return false;}
            
            String name = parts[0].trim();
            String description = parts[1].trim();
            addTask(new Task(name, description));
            
            currentmode = Mode.DETAILS;
            selectedTask = tasks.size() - 1;
            return false;
        }
        
    public String display() {
            switch (currentmode) {
            case START:
                currentmode = Mode.LIST;
                return "Welcome to Task Manager \n Press 'Enter' to begin";

            case ADD:
                return displayAdd();
        
            case DETAILS:
                return displayDetails();
        
            case LIST:
                return displayList();
            default:
                return "Invalid mode";
        }
    }

    public String displayList() {
        StringBuilder sb = new StringBuilder(" Here are your tasks: \n Enter the number of any task to view it \n\n <u> scroll up \n");
        for (int i = 0; i < 10 && i + currentPage * 10 < tasks.size(); i++) {
            sb.append(" <" + i + "> " + tasks.get(i + currentPage * 10).getName() + (tasks.get(i + currentPage * 10).isDone() ? " --- ✓\n" : " --- X\n"));
        }
        sb.append(" <d> scroll down \n <a> add a task \n <x> Exit \n");
        return sb.toString();
    }


    public String displayDetails() {
        StringBuilder sb = new StringBuilder("Task Details: \n");
        sb.append(tasks.get(selectedTask).getName() + "\n" + tasks.get(selectedTask).getDesc() + "\n");
        sb.append("Press D to mark as done \n");
        sb.append("Press R to remove task \n");
        sb.append("Press 'Enter' to return to the list");
        return sb.toString();
    }

    public String displayAdd() {
        return "Enter the name and description of the task separated by '|'\n Any non valid input will cancel instead";
    }

}
