import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Reader implements Runnable{

    private BufferedReader fileReader;
    private LinkedList<String> queue = new LinkedList<>();
    public String path;
    private String checkingTable[] = {"abcdefghijklmnopqrstuvwxyz","0123456789"};

    public Reader(String path, LinkedList<String> queue){
        try {
            this.queue = queue;
            this.fileReader = new BufferedReader(new FileReader(path));
            this.path = path;

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
                    String filtered = filteredString(item);
                    this.queue.addLast(filtered);
                    //System.out.println("Read an item: " + filtered);
                }
                readString = fileReader.readLine();
            }

            queue.addLast("\u001A");
            fileReader.close();
            System.out.println("====READ - done.");

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
    

    public void setPath(String path){
        try {
            this.fileReader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
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
