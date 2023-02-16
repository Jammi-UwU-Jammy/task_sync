
import java.util.LinkedList;
import java.util.Stack;

//Threads including reader > buffer-or > Processor

public class App{

    public static void main(String[] args) throws InterruptedException {

        LinkedList<String> queue = new LinkedList<>();
        Thread threads[] = new Thread[args.length];

        //parallel
        for (int i = 0; i < args.length ; ++i){
            System.out.println(args[i]);
            threads[i] = new Thread(new Reader(args[i], queue));
            threads[i].start();
        }
        
        //Sleep for 0.5s to make sure all data is loaded in queue before mapping them.
        Thread.sleep(500);

        //syncing
        Processor processor = new Processor(queue);
        Thread thread = new Thread(processor);
        thread.start();

        // while (true){
        //     if (!thread.isAlive()){
        //         processor.printMap();
        //         break;
        //     }  
        // }
            
            

    }
}
