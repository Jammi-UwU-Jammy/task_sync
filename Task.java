
public class Task {
    Runnable task;
    Boolean isActive = false;
    
    public Task (Runnable r){
        this.task = r;
    }

    public Runnable getTask(){
        return this.task;
    }

}
