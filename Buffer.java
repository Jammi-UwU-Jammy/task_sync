
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;

public class Buffer implements Runnable{

    private LinkedList<String> queue;
    private LinkedList<String> readyQueue;
    private String checkingTable[] = {"abcdefghijklmnopqrstuvwxyz","0123456789"};

    public Buffer(LinkedList<String> buffer, LinkedList<String> readyBuffer){
        this.queue = buffer;
        this.readyQueue = readyBuffer;
    }

    @Override
    public void run() {

        while(true){
            if (queue.isEmpty())
                //System.out.println("Buffer empty.");
                continue;
            else{
                String fetchedItem = queue.getFirst();
                String item = new String(fetchedItem); //create a new string obj
                readyQueue.addLast(item);   //add it to the new queue
                System.out.println("Last item: " + item);
                queue.remove(item); //and destroy the original string obj


                if (fetchedItem.equals("\u001A")){
                    System.out.println("Buffer - done.");
                    break;
                }
                
                
            }
        }
    }
    
    public String filteredString(String string){
        
        char array[] = string.toCharArray();
        char[] copiedArray = Arrays.copyOfRange(array, 0, array.length-1);

        char lastChar = array[array.length-1];

        if (checkingTable[0].contains(Character.toString(lastChar))
            ||
            checkingTable[0].toUpperCase().contains(Character.toString(lastChar))
            ||
            checkingTable[1].contains(Character.toString(lastChar))
        )
            copiedArray = Arrays.copyOfRange(array, 0, array.length-2);

        return copiedArray.toString();
    }
}


