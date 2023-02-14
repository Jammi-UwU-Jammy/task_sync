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
        
        
    }


}
