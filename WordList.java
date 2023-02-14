import java.util.*;

public class WordList {

    private int numberOfWords; 
    private HashMap<Character, HashMap<String, Integer>> wordMap;

    public WordList(){
        this.numberOfWords = 0;
        this.wordMap = new HashMap<>();
    }


    public void addWord(String word){
        this.numberOfWords++;
        
        if (pendingToList(word))
            System.out.println("Added a new word: " + word);
        else
            System.out.println("Word already exists: " + word);
    }


    public boolean pendingToList(String word){
        char firstLetter = word.charAt(0);

        if ( !this.wordMap.get(firstLetter).containsKey(word) ){
            this.wordMap.get(firstLetter).put(word, 1);
            return false;
        }
        else {
            int count = this.wordMap.get(firstLetter).get(word) + 1;
            this.wordMap.get(firstLetter).put(word, count);
            return true;
        }  
    }


}
