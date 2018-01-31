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
        //System.out.println(f.getName());
        FileResource fr = new FileResource(f.getName());
        
        for(String word : fr.words()){
            //word = word.toLowerCase();
               
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
    
    private int maxNumber(){
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
    
    private ArrayList wordsInNumFiles(int number) {
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

    private void printFilesIn(String word) {
        if(map.containsKey(word)) {
            ArrayList<String> filenames = new ArrayList<String>();
            filenames = map.get(word);
            System.out.print(word+" appears in the files: ");
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
        /*for(String word : words) {
            System.out.print(word);
            System.out.print(" ");
            printFilesIn(word);
            System.out.println("");
        }*/
        System.out.println("#: " +words.size());
        
        System.out.println("in four files:" );
        words = wordsInNumFiles(4);
        /*for(String word : words) {
            System.out.print(word);
            System.out.print(" ");
            printFilesIn(word);
        }*/
        System.out.println("");
        System.out.println("#: " +words.size());
        
        String word = "sad";
        System.out.println("Files containing "+word);
        printFilesIn(word);
        
        word = "red";
        System.out.println("Files containing "+word);
        printFilesIn(word);

        word = "laid";
        System.out.println("Files containing "+word);
        printFilesIn(word);

        word = "tree";
        System.out.println("Files containing "+word);
        printFilesIn(word);
}
}
