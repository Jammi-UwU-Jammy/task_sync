import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Reader implements Runnable{

    private BufferedReader fileReader;
    private LinkedList<String> queue = new LinkedList<>();

    public Reader(String path, LinkedList<String> queue){
        try {
            this.queue = queue;
            this.fileReader = new BufferedReader(new FileReader(path));

        } catch (FileNotFoundException e) {
            System.out.println("File not found, program exits.");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void run() {
        try {
            String readString = fileReader.readLine();

            while (readString != null){
                String[] splitString = readString.strip().split(" ");

                for (String item : splitString){
                    this.queue.addLast(item);
                    System.out.println("Added an item " + item);
                }

                /* try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } */

                readString = fileReader.readLine();
            }

            queue.addLast("\u001A");
            System.out.println("Reader - done.");

        } catch (IOException e) {
            System.out.println("Jesus Christ");
            e.printStackTrace();
        }
        
    }

    public void printTest(){
        for (String item : queue){
            System.out.println(item);
        }
    }
    
}
