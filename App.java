
import java.util.LinkedList;
import java.util.Stack;

//Threads including reader > buffer-or > Processor

public class App{

    public static void main(String[] args) throws InterruptedException {

        LinkedList<String> queue = new LinkedList<>();
        //LinkedList<String> readyQueue = new LinkedList<>();

        // for (String arg : args){
        //     System.out.println(arg);
        //     Runnable r = new Reader(arg, queue);
        //     r.run();
        // }

        Runnable r = new Reader(args[0], queue);
        r.run();
        Runnable r2 = new Reader(args[1], queue);
        r2.run();

        Runnable processor = new Processor(queue, args.length);
        // for (String item : queue)
        //     System.out.println(item);

        Thread thread = new Thread(processor);
        thread.start();
            

    }
}
