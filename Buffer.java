
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;

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
            try{
                String item = queue.getFirst(); //create a new string obj
                
                if (item.equals("\u001A")){ //terminating indicator
                    readyQueue.addLast(item);
                    System.out.println("====BUFFER - done.");
                    break;
                }
                String filtered = filteredString(item);
                readyQueue.addLast(filtered);   //add it to the new queue
                //System.out.println("..Filtered an item: " + filtered);
                queue.remove(item); //and destroy the original string obj
            }
            catch ( NoSuchElementException e){
                //System.out.println("Buffer empty.");
                continue;
            }
        }
    }
    
    public String filteredString(String string){
        
        StringBuilder muteString = new StringBuilder("");
        char array[] = string.toCharArray();
        char[] copiedArray = Arrays.copyOfRange(array, 0, array.length-1);

        char lastChar = array[array.length-1];
        //System.out.println(lastChar);

        if ( checkingTable[0].contains(Character.toString(lastChar))
            ||
             checkingTable[0].toUpperCase().contains(Character.toString(lastChar))
            ||
             checkingTable[1].contains(Character.toString(lastChar))
        ){
            copiedArray = Arrays.copyOfRange(array, 0, array.length);
        }
        else 
            copiedArray = Arrays.copyOfRange(array, 0, array.length-1);

        return muteString.append(copiedArray).toString();

    }

}


