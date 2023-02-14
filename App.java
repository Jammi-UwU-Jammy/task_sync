import java.util.LinkedList;

//Threads including reader > buffer-or > Processor

public class App{

    public static void main(String[] args) {

        LinkedList<String> bufferedQueue = new LinkedList<>();
        LinkedList<String> readyQueue = new LinkedList<>();

        //System.out.print("\u001A");

        Thread thread1 = new Thread(new Reader("./text.txt", bufferedQueue));
        Thread thread2 = new Thread(new Buffer(bufferedQueue, readyQueue));
        Thread thread3 = new Thread(new Processor(readyQueue));

        thread1.start();
        thread2.start();
        thread3.start();


    }


}
