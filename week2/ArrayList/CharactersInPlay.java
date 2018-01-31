
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    
    // Fields
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    // Methods
    
    // Contructor
    public CharactersInPlay() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void update(String person) {
        person = person.toLowerCase();
        int index = myWords.indexOf(person);
        if (index == -1){
            myWords.add(person);
            myFreqs.add(1);
        }
        else {
            int freq = myFreqs.get(index);
            myFreqs.set(index,freq+1);
        }
    }
    
    public void findAllCharacters() {
        myWords.clear();
        myFreqs.clear();
        
        FileResource resource = new FileResource();
        for(String line : resource.lines()){
            int index = line.indexOf(".");
            if (index != -1) {
                String possibleName = line.substring(0,index);
                update(possibleName);
            }
        }
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        for(int k=0; k < myWords.size(); k++){
            if ((myFreqs.get(k) >= num1) && (myFreqs.get(k) < num2)) {
                System.out.println(myWords.get(k)+ " "+myFreqs.get(k));
            }
        }
    }
    
    public double getAverageSpeakingParts() {
        double totalSpeakingParts = 0.0;
        for(int k=0; k < myWords.size(); k++){
            totalSpeakingParts += myFreqs.get(k);
        }
        return totalSpeakingParts /= myWords.size();
    }

    public int findIndexOfMax(){
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for(int k=0; k < myFreqs.size(); k++){
            if (myFreqs.get(k) > max){
                max = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
        
    public void tester() {
        findAllCharacters();

        //double averageSpeakingParts = getAverageSpeakingParts();
        //System.out.println("average"+averageSpeakingParts);
        //charactersWithNumParts((int) averageSpeakingParts * 2, (int) (averageSpeakingParts * myFreqs.size()) + 1);
    
        int index = findIndexOfMax();
        System.out.println("max word/freq: "+myWords.get(index)+" "+myFreqs.get(index));
        System.out.println("# unique: "+myWords.size());
        
        System.out.println("between 10 and 15, inclusive: ");
        charactersWithNumParts(10, 16);

        System.out.println("between 75 and 100000, inclusive: ");
        charactersWithNumParts(75, 100000);
    }
}
