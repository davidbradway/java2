import java.util.*;
import java.io.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        // Slice the message
        StringBuilder submessage = new StringBuilder();
        for (int i=whichSlice;i<message.length();i+=totalSlices){
            submessage.append(message.charAt(i));
        }
        return submessage.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        
        //WRITE YOUR CODE HERE
        CaesarCracker ccr = new CaesarCracker();
        for (int i=0;i<key.length;i++){
            // Get the slice of the encrypted string message
            String sub = sliceString(encrypted, i, key.length);
            // fill in the key array and return it
            key[i] = ccr.getKey(sub);
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionary = new HashSet<String>();
        // read each line in fr (exactly one word per line)
        for (String line : fr.lines()) {
            // process each line in turn
            // convert that line to lowercase
            // and put that line into the HashSet that you created
            dictionary.add(line.toLowerCase());
        }
        return dictionary;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        // split the message into words (use .split(“\\W+”),
        // which returns a String array), and iterate over those words, 
        int count = 0;
        for (String word : message.split("\\W")) {
            // see how many of them are “real words”
            // that is, how many appear in the dictionary
            // Recall that the words in dictionary are lowercase
            if (dictionary.contains(word.toLowerCase())){
                count++;
            }
        }
        // This method should return the integer count
        // of how many valid words it found.
        return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        // find the most common character in the language
        char mostCommon = mostCommonCharIn(dictionary);
        
        // try all key lengths from 1 to 100 (use your tryKeyLength method
        // to try one particular key length) to obtain the best
        // decryption for each key length in that range.
        int maxNumRealWords = 0;
        String bestDecryption = "";
        int correctKeyLength = 0;
        for (int keyLength = 1;keyLength <= 100; keyLength++){
            int[] keys = tryKeyLength(encrypted, keyLength, mostCommon);
            // For each key length, your method should decrypt the message
            // (using VigenereCipher’s decrypt method as before)

            // You should create a new VigenereCipher, passing in the key 
            // that tryKeyLength found for you.
            VigenereCipher vc = new VigenereCipher(keys);
            //System.out.println(Arrays.toString(keys));
            
            // You should use the VigenereCipher’s decrypt method to decrypt
            // the encrypted message.
            String decrypted = vc.decrypt(encrypted);

            // count how many of the “words” in it are real words in 
            // English, based on the dictionary passed in
            // (use the countWords method you just wrote).
            int numRealWords = countWords(decrypted, dictionary);

            // This method should figure out which decryption gives the
            // largest count of real words, and return that String decryption
            if (maxNumRealWords == 0 || numRealWords > maxNumRealWords){
                bestDecryption = decrypted;
                correctKeyLength = keyLength;
                maxNumRealWords = numRealWords;
            }
        }
        System.out.println("maxNumRealWords: "+maxNumRealWords);
        //System.out.println("correctKeyLength: "+correctKeyLength);
        return bestDecryption;
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // make a counter array of ints
        int[] counts = new int[26];
        // find the most common letter in this hashset of words
        // go through each word in the current dictionary
        for (String word : dictionary) {
            // force to CAPS
            String wordUp = word.toUpperCase();
            // look at each letter
            for (int i=0;i<wordUp.length();i++){
                int index = alphabet.indexOf(wordUp.charAt(i));
                if (index != -1){
                    counts[index]++;
                }
            }
        }
        // look at the counts and find the max index
        int maxIndex = -1;
        int maxCount = -1;
        for (int i=0; i<26; i++){
            if (counts[i] > maxCount) {
                maxIndex = i;
                maxCount = counts[i];
            }
        }
        return alphabet.charAt(maxIndex);
    }
    
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        int numRealWordsBestLanguage = 0;
        String bestLanguage = "";
        String bestDecryption = "";
        for (String language : languages.keySet()) {
            // process each value in turn
            System.out.println("\n"+language);
            HashSet<String> dictionary = languages.get(language);
            String currentDecryption = breakForLanguage(encrypted, dictionary);
            int numRealWords = countWords(currentDecryption, dictionary);
            if (numRealWords > numRealWordsBestLanguage) {
                numRealWordsBestLanguage = numRealWords;
                bestLanguage = language;
                bestDecryption = currentDecryption;
            }
        }
        // You will want to print out the decrypted message as well as the language that you identified for the message.
        System.out.println("decrypted: "+bestDecryption.substring(0,100));
        System.out.println("language: "+bestLanguage);
    }
    
    public void breakVigenere() {

        // Create a new FileResource using its default constructor
        // (which displays a dialog for you to select a file to decrypt).
        FileResource fr = new FileResource();

        // Use the asString method to read the entire contents of the file
        // into a String.
        String encrypted = fr.asString();

        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            // process each file in turn
            System.out.println("reading file: "+f.getName());

            // You should make a new FileResource to read from current
            // dictionary file that we have provided.
            FileResource dict_fr = new FileResource("dictionaries/"+f.getName());

            // You should use your readDictionary method to read the contents of
            // that file into a HashSet of Strings.
            HashSet<String> dictionary = readDictionary(dict_fr);
            languages.put(f.getName(),dictionary);
        }

        breakForAllLangs(encrypted, languages);
    }
    
    public void breakFirstLine() {
        String encrypted= "Er obsfsfq à tcgwzxi py Clzuylmv.";
        FileResource dict_fr = new FileResource("dictionaries/French");
        HashSet<String> dictionary = readDictionary(dict_fr);
        String decrypted = breakForLanguage(encrypted, dictionary);
        System.out.println(decrypted);
    }
}
