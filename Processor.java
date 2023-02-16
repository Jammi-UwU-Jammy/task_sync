import java.util.HashMap;
import java.util.LinkedList;

public class Processor implements Runnable{

    private WordList list;
    private LinkedList<String> readyQueue;
    private int filecount;

    public Processor(LinkedList<String> queue, int filecount){
        this.list = new WordList();
        this.readyQueue = queue;
        this.filecount = filecount;
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
                    for (String i : this.readyQueue)
                        System.out.println(i);
                    System.out.println("====================");
                }else{
                    loadWord(item);
                    System.out.println("....Counted an item: " + item);
                    readyQueue.remove(item);
                }     
                //System.out.println("....Counted an item: " + item);
        }
        printMap(list);
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
