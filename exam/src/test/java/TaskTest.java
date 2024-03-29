import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exam.Task;

public class TaskTest {

    @Test
    public void CreateTask(){
        String name = "I";
        String desc = "I task therefore I am";
        Task task = null;
        assertNull(task);
        task = new Task(name,desc);
        assertNotNull(task);
        assertEquals(name, task.getName());
        assertEquals(desc, task.getDesc());
    }

    @Test
    public void SpacesAreStrippedInNames(){
        String name = "  I  ";
        String desc = " am task ";
        Task task = new Task(name,desc);
        assertEquals("I", task.getName());
        assertEquals("am task", task.getDesc());
    }

    @Test(expected = IllegalArgumentException.class)
    public void TaskNameCantBeNull(){
        String name = null;
        String desc = "My name is null";

        //This should throw so the warning doesnt matter, we can't use it anyway
        @SuppressWarnings("unused")
        Task task = new Task(name, desc);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void TaskNameCantContainEqual(){
        String name = "Hello =";
        String desc = "My name is illegal";

        //This should throw so the warning doesnt matter, we can't use it anyway
        @SuppressWarnings("unused")
        Task task = new Task(name, desc);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TaskNameCantContainPipe(){
        String name = ":|";
        String desc = "My name is illegal";

        //This should throw so the warning doesnt matter, we can't use it anyway
        @SuppressWarnings("unused")
        Task task = new Task(name, desc);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void TaskDescCantContainEqual(){
        String name = "My desc is illegal";
        String desc = "I'm not allowed to have =";

        //This should throw so the warning doesnt matter, we can't use it anyway
        @SuppressWarnings("unused")
        Task task = new Task(name, desc);
    }

    @Test(expected = IllegalArgumentException.class)
    public void TaskDescCantContainPipe(){
        String name = "My desc is illegal";
        String desc = "I'm dead | inside";

        //This should throw so the warning doesnt matter, we can't use it anyway
        @SuppressWarnings("unused")
        Task task = new Task(name, desc);
    }
    
    @Test
    public void TaskDescCanBeNull(){
        String name = "I have no desc and I must Task";
        String desc = null;
        Task task = new Task(name,desc);
        assertNull(task.getDesc());
    }

    @Test
    public void TaskStartAsNotDone(){
        Task task = new Task("hello", "I'm not done yet");
        assertFalse(task.isDone());
    }


    @Test
    public void TaskDoneIsTrulyDone(){
        Task task = new Task("hello","I will be done");
        task.dough();
        assertTrue(task.isDone());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void CantDoaTaskTwice(){
        Task task = new Task("hello","I will be done, twice");
        task.dough();
        task.dough();
    }

    @Test
    public void TaskisEqualtoitself(){
        Task task = new Task("hello", "I am myself");
        assertTrue(task.equals(task));
    }

    @Test
    public void TaskIsEqualToSameTask(){
        Task john = new Task("John", null);
        Task john2 = new Task("John", null);
        assertTrue(john.equals(john2));
    }

    @Test
    public void DifferentTasksAreNotEqual(){
        Task john = new Task("John", null);
        Task joe = new Task("Joe", null);
        assertFalse(john.equals(joe)); 
        
    }

    @Test
    public void TaskIsNotEqualToNull(){
        Task task = new Task("Hellaw", "I'm not null");
        assertFalse(task.equals(null));
    }

    @Test
    @SuppressWarnings("unlikely-arg-type")
    public void ANumberIsNotATask(){
        Task task = new Task("NaN", "I am not a number");
        int notatask = 42;

        assertFalse(task.equals(notatask));
    }

    @Test
    public void TaskToStringDisplaysEverything(){
        String expected = "Task ||name= Hello | desc= Display me |done= false ||";
        String name = "Hello";
        String desc = "Display me";
        Task task = new Task(name, desc);
        assertEquals(expected, task.toString());
    }

    @Test
    public void TaskFromString(){
        String input = "Task ||name= Hello | desc= Read me | done= false |";
        Task task = Task.fromString(input);
        Task expected = new Task("Hello", "Read me");
        assertTrue(expected.equals(task));

        
    }
    
}
