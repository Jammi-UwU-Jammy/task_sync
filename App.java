
import java.util.LinkedList;
import java.util.Stack;

//Threads including reader > buffer-or > Processor

public class App{

    public static void main(String[] args) throws InterruptedException {

        LinkedList<String> bufferedQueue = new LinkedList<>();
        LinkedList<String> readyQueue = new LinkedList<>();
        //System.out.print("\u001A");

        // Thread thread1 = new Thread(new Reader("./text.txt", bufferedQueue));
        // Thread thread2 = new Thread(new Buffer(bufferedQueue, readyQueue));
        // Thread thread3 = new Thread(new Processor(readyQueue));

        // thread1.start();
        // thread2.start();
        // thread3.start();


        Task buffer = new Task(new Buffer(bufferedQueue, readyQueue));
        Task processor = new Task(new Processor(readyQueue));

        Stack<Task> taskStack = new Stack<>();

        taskStack.push(buffer);
        taskStack.push(processor);

        //populating tasks from args
        for (String arg : args){
            taskStack.push(new Task(new Reader(arg, bufferedQueue)));
            //System.out.print(taskStack.peek().getTask());
        }

        Thread prevThread = null;
        while (!taskStack.empty()){

            Task item = taskStack.pop();

            // if (taskStack.size() > 3){
            //     System.out.print("aaaaaaaaaaaaaa");
            //     Thread.sleep((int)Math.random()*100);
            // }

                if (item != null){ 
                    Thread thread = new Thread(item.getTask()); //threads cant be reused so makin new thread
                    System.out.print(thread.getName());

                    prevThread = thread;
                    thread.start();

                }  
                else continue; 
        }


        /* Replace the loop above with this code block will make the threads run randomly, not in order
         * and might finish writing before even read all of other files.
         */
        // for (Task task : taskStack){
        //     // Task item = taskStack.pop();
        //     if (task != null){  //Had to add this because the prog thrws StackEmptyException even though isEmpty() is checked before the codes below
        //         Thread thread = new Thread(task.getTask()); //threads cant be reused so makin new thread
        //         System.out.print(thread.getName());
        //         thread.start();
        //     }
        //     else continue; 
        // }
    }


}
