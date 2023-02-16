import java.util.*;

public class WordList {

    private int numberOfWords; 
    private HashMap<Character, HashMap<String, Integer>> wordMap;

    public WordList(){
        this.numberOfWords = 0;
        //HashMap<String, Integer> map = new HashMap<>();
        this.wordMap = new HashMap<Character, HashMap<String, Integer>>();
    }

    public HashMap<Character, HashMap<String, Integer>> getMap(){
        return this.wordMap;
    }

    public void addWord(String word){
        this.numberOfWords++;

        pendingToList(word);
        // System.out.println("Word existed?  " +  pendingToList(word));
    }


    public boolean pendingToList(String word){
        char firstLetter = word.charAt(0);

        //Map empty or the first letter doesn't exist
        if (this.wordMap.isEmpty() || this.wordMap.get(firstLetter) == null){
            HashMap<String, Integer> innerMap = new HashMap<>();
            innerMap.put(word, 1);
            this.wordMap.put(firstLetter, innerMap);
            return false;
        }
        //Map exists, first letter exists but not the word
        else if (!this.wordMap.get(firstLetter).containsKey(word)){
            this.wordMap.get(firstLetter).put(word, 1 );       
            return false;  
        } 
        //Word already exists
        else{
            int count = this.wordMap.get(firstLetter).get(word) + 1;
            this.wordMap.get(firstLetter).put(word, count);
            return true;
        }

    }


}
