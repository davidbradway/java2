import edu.duke.*;
import java.util.*;
import java.io.*;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    private HashMap<String,ArrayList<String>> map;
    
    public WordsInFiles(){
        map = new HashMap<String,ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f.getName());
        
        for(String word : fr.words()){
            word = word.toLowerCase();
               
            if (map.containsKey(word)){
                ArrayList<String> filenames = new ArrayList<String>(); 
                filenames = map.get(word);
                
                if (!filenames.contains(f.getName())) {
                    filenames.add(f.getName());
                    map.put(word,filenames);
                }
                else {
                    // the filename is already mapped to that key word
                }
            }
            else { //word not in map yet, add it
                ArrayList<String> filenames = new ArrayList<String>(); 
                filenames.add(f.getName());
                map.put(word,filenames);
            }
        }
    }
    
    public void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            // process each file in turn
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int maximumNum = 0;
        for(String word : map.keySet()) {
            ArrayList<String> filenames = new ArrayList<String>();
            filenames = map.get(word);
            if (filenames.size() > maximumNum) {
                maximumNum = filenames.size();
            }
        }
        return maximumNum;
    }
    
    public ArrayList wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<String>();
        words.clear();
        for(String word : map.keySet()) {
            ArrayList<String> filenames = new ArrayList<String>();
            filenames = map.get(word);
            if (filenames.size() == number) {
                words.add(word);
            }
        }
        return words;
    }

    public void printFilesIn(String word) {
        if(map.containsKey(word)) {
            ArrayList<String> filenames = new ArrayList<String>();
            filenames = map.get(word);
            for (String filename : filenames) {
                System.out.println(filename);
            }
        }
    }

    public void tester () {
        buildWordFileMap();
        if(map.size() < 35 && map.size() > 0){
            System.out.println("printing whole map:");
            System.out.println(map);
            /*for(String word : map.keySet()) {
                System.out.print(word+": ");
                printFilesIn(word);
            }*/
        }        
        System.out.println("max number: "+maxNumber());
        System.out.println("files containing most popular word:");
        ArrayList<String> words = new ArrayList<String>();
        words = wordsInNumFiles(maxNumber());
        for(String word : words) {
            System.out.println(word);
            printFilesIn(word);
        }
    }
}
