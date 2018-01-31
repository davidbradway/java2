import edu.duke.*;
import java.util.*;
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCount {
    private HashMap<String,Integer> map;
    
    public CodonCount(){
        map = new HashMap<String,Integer>();
    }
    
    public void buildCodonMap(int start, String dna) {
        map.clear();
        for(int i = start; i<dna.length()-2; i+=3){
            String codon = dna.substring(i,i+3);
            if (!map.containsKey(codon)){
                map.put(codon,1);
            }
            else {
                map.put(codon,map.get(codon)+1);
            }
        }
    }
    
    public String getMostCommonCodon() {
        String mostCommonCodon = "";
        int count = -1;
        for(String codon : map.keySet()){
            // initialize with first value and key
            if (count == -1) {
                mostCommonCodon = codon;
                count = map.get(codon);
            }
            if (map.get(codon) > count) {
                mostCommonCodon = codon;
                count = map.get(codon);
            }
        }
        return mostCommonCodon.toUpperCase() + " with count " + count;
    }
    
    public void printCodonCounts(int start, int end) {
        for(String codon : map.keySet()) {
            int count = map.get(codon);
            if ((count >= start) && (count <= end)) {
                System.out.println(codon+" "+count);
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString().trim().toUpperCase();
        for(int i=0;i<3;i++){
            buildCodonMap(i, dna);
            System.out.println("Reading frame starting with "+i+" results in "+map.size()+" unique codons");
            System.out.println("and most common codon is "+getMostCommonCodon());
            
            int num1 = 6;
            int num2 = 6;
            System.out.println("Counts of codons between "+num1+" and "+num2+" inclusive are:");
            printCodonCounts(num1, num2);
            
            num1 = 7;
            num2 = 7;
            System.out.println("Counts of codons between "+num1+" and "+num2+" inclusive are:");
            printCodonCounts(num1, num2);
            
            num1 = 4;
            num2 = 4;
            System.out.println("Counts of codons between "+num1+" and "+num2+" inclusive are:");
            printCodonCounts(num1, num2);
        }
    }
    /*f occurrences in this reading frame are between two numbers inclusive.*/
}
