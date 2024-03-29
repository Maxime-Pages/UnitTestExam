package exam;

import java.util.function.Function;

public class Task {
    
    private String name;
    
    private String desc;

    private Boolean done;

    public Task(String name, String desc) throws IllegalArgumentException {
        Function<String,String> validate = x -> { 
                if (x == null) return null;
                else if (x.contains("=") || x.contains("|")) throw new IllegalArgumentException("Task name can't contain = or |");
                else return x.strip();};

        if (name == null) throw new IllegalArgumentException("Task name can't be null");
        this.name = validate.apply(name);
        this.desc = validate.apply(desc);
        this.done = false;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public Boolean isDone() {
        return done;
    }

    public void dough() throws UnsupportedOperationException {
        if(done) throw new UnsupportedOperationException("Can't do a task twice, the task was" + this.toString());
        done = true;
    }

    @Override
    public String toString() {
        return "Task ||name= " + name + " | desc= " + desc + " |done= " + done + " ||";
    }
    
    //Exemple is "Task ||name=Hello | desc=Read me | done=false|| ""
    public static Task fromString(String str){
        String[] parts = str.split("\\|");
        String name = parts[2].split("=")[1].strip();
        String desc = parts[3].split("=")[1].strip();
        Boolean done = Boolean.parseBoolean(parts[4].split("=")[1].strip());
        Task task = new Task(name, desc);
        task.done = done;
        return task;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj == null) return false;

        if (obj.getClass() != Task.class) return false;
        
        Task other = (Task) obj;
        
        if   ( name.equals(other.name)
             && (desc == null && other.desc == null || desc.equals(other.desc))
             && done.equals(other.done)) return true;
        return false;
    }


}
