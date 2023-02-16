import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.LinkedList;

public class Processor implements Runnable{

    private WordList list;
    private LinkedList<String> readyQueue;

    public Processor(LinkedList<String> queue){
        this.list = new WordList();
        this.readyQueue = queue;
    }

    public void loadWord(String word){
        this.list.addWord(word);
    }

    @Override
    public void run() {

        while (! readyQueue.isEmpty()){
                String item = readyQueue.getFirst();

                if (item.equals("\u001A")){
                    readyQueue.remove(item);
                    // for (String i : this.readyQueue)
                    //     System.out.println(i);
                    System.out.println("###");
                }else{
                    loadWord(item);
                    // System.out.println("....Counted an item: " + item);
                    readyQueue.remove(item);
                }     
        }
        System.out.println("====STORE - done.");
        exportMap();
    }

    public void printMap(){
        HashMap<Character, HashMap<String, Integer>> map = this.list.getMap();

        for (HashMap.Entry<Character, HashMap<String, Integer>> mapEntry : map.entrySet()){
            HashMap<String, Integer> innerMap = mapEntry.getValue();

            for (HashMap.Entry<String, Integer> wordEntry : innerMap.entrySet()){
                System.out.println("```" + wordEntry.getKey() +"--"+ wordEntry.getValue());
            }
        }
    }

    public void exportMap(){
        BufferedWriter writer;
        HashMap<Character, HashMap<String, Integer>> map = this.list.getMap();

        try {
            writer = new BufferedWriter(new FileWriter(new File("./generated.txt")));

            for (HashMap.Entry<Character, HashMap<String, Integer>> mapEntry : map.entrySet()){
                HashMap<String, Integer> innerMap = mapEntry.getValue();
    
                for (HashMap.Entry<String, Integer> wordEntry : innerMap.entrySet()){
                    writer.write(wordEntry.getKey() + ": " + wordEntry.getValue().toString() + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Create file failed.");
            e.printStackTrace();
        }
    }

}
