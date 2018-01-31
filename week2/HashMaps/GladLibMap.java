import edu.duke.*;
import java.util.*;
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GladLibMap {
    private HashMap<String,ArrayList<String>> myMap;
    /* adjective, noun, color, country, name, animal, timeframe, verb, fruit */
    private ArrayList<String> usedList;
    private ArrayList<String> usedCategoriesList;
    
    private int numSubs;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String,ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        usedList = new ArrayList<String>();
        usedCategoriesList = new ArrayList<String>();
        
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String,ArrayList<String>>();
        initializeFromSource(source);
        usedList = new ArrayList<String>();
        usedCategoriesList = new ArrayList<String>();
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color","country", "name", "animal", "timeframe", "verb", "fruit"};
        for (int i=0;i<categories.length;i++){
            myMap.put(categories[i],readIt(source+"/"+categories[i]+".txt"));
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if (myMap.containsKey(label)) {
            return randomFrom(myMap.get(label));
        }        
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        
        // add label to usedCategoriesList if it is not there already
        if (!usedCategoriesList.contains(w.substring(first+1,last))){
            usedCategoriesList.add(w.substring(first+1,last));
        }

        // Get a random word
        String sub = getSubstitute(w.substring(first+1,last));
        // Check if we used that word already
        while (usedList.contains(sub)) { // keep trying so we don't repeat
            sub = getSubstitute(w.substring(first+1,last));
        }
        usedList.add(sub);
        numSubs++;
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        System.out.println("\n");
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        usedList.clear();
        usedCategoriesList.clear();
        numSubs = 0; // reset the count of number of words replaced

        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap() {
        int total = 0;
        for (String s : myMap.keySet()) {
            // process each key in turn 
            total += myMap.get(s).size();
        } 
        return total;
    }
    
    private int totalWordsConsidered() {
        int total = 0;
        for (String s : usedCategoriesList) {
            // process each key in turn 
            total += myMap.get(s).size();
        }
        return total;
    }
    
    public void makeStory(){
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\ntotal number of words that were replaced: " + numSubs);
        System.out.println("total number of possible words: "+totalWordsInMap());
        System.out.println("total number of Words Considered: "+totalWordsConsidered());
    }
}
