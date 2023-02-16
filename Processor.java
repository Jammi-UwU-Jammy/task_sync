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
        while (true){
            if (! readyQueue.isEmpty()){
                String item = readyQueue.getFirst();

                if (item.equals("\u001A")){
                    printMap(list);
                    System.out.println("====Processor - done.");
                    break;
                }
                
                loadWord(item);
                readyQueue.remove(item);
                //System.out.println("....Counted an item: " + item);
            }   
        }
    }

    public void printMap(WordList list){
        HashMap<Character, HashMap<String, Integer>> map = list.getMap();

        for (HashMap.Entry<Character, HashMap<String, Integer>> mapEntry : map.entrySet()){
            HashMap<String, Integer> innerMap = mapEntry.getValue();

            for (HashMap.Entry<String, Integer> wordEntry : innerMap.entrySet()){
                System.out.println("```" + wordEntry.getKey() +"--"+ wordEntry.getValue());
            }
        }
    }

}
