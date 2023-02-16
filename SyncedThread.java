public class SyncedThread extends Thread {
    Task task;

    public SyncedThread (Task task){
        this.task = task;
    }
}
