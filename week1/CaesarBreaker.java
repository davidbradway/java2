
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
    public void countLetters(String encrypted, int[] counts){
        // given a string of characters, and an array to hold letter counts
        // loop across the string and count occurrences of each letter
        // ignore punctuation and spaces, and treat cases the same
        
        // loop across string
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        for (int k=0; k<encrypted.length(); k++){
            int index = alpha.indexOf(Character.toLowerCase(encrypted.charAt(k)));
            if (index != -1) {
                counts[index]++;
            }
        }
        // there is no 'return' value, but our output is 'counts'
    }

    public int maxIndex(int[] values){
        int index = 0;
        int largestValue = values[0];
        for (int k = 1; k< values.length; k++){
            if (values[k] > largestValue) {
                index = k;
                largestValue = values[k];
            }
        }
        return index;
    }
        
    public String decrypt(String encrypted){

        int key = getKey(encrypted);

        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt(encrypted, 26 - key);
        return message;
        
    }

    public String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        for (int k = start; k<message.length(); k+=2){
            sb.append(message.charAt(k));
        }
        return sb.toString();
    }

    public int getKey(String s) {
        int[] counts = new int[26];
        countLetters(s, counts);
        
        // use maxIndex to calculate the index of the largest letter frequency,
        // which is the location of the encrypted letter ‘e’, 
        
        int keyguess = maxIndex(counts) - 4;
        if (keyguess < 0) {
            keyguess += 26;
        }
        
        // which leads to the key, which is returned.
        return keyguess;
    }
    
    public String decryptTwoKeys(String encrypted) {
        String stringa = halfOfString(encrypted, 0);
        String stringb = halfOfString(encrypted, 1);
        
        // determine the two keys used to encrypt the message,
        // calculate the key used to encrypt each half String.
        int key1 = getKey(stringa);
        int key2 = getKey(stringb);
        
        // You should print the two keys found.
        System.out.println(key1);
        System.out.println(key2);
        
        // Calculate and return the decrypted String 
        // using the encryptTwoKeys method from your CaesarCipher class,
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);

        return message;
    }
    
    public void testDecryptTwoKeys(){
        System.out.println(decryptTwoKeys("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu"));
        
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 26 - 2, 26 - 20);
        System.out.println(message);
        
        System.out.println(decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
        
        FileResource resource = new FileResource();
        String contents = resource.asString();
        System.out.println(decryptTwoKeys(contents));
    }
    
    public void testHalfOfString() {
        System.out.println("halfOfString(“Qbkm Zgis”, 0) returns the String “Qk gs”");
        System.out.println(halfOfString("Qbkm Zgis", 0));
        
        System.out.println("halfOfString(“Qbkm Zgis”, 1) returns the String “bmZi”");
        System.out.println(halfOfString("Qbkm Zgis", 1));
    }
    
    public void testCountLetters() {
        String encrypted = "abcdabca";
        int[] counts = new int[26];
        countLetters(encrypted, counts);

        for(int k=0; k < counts.length; k++){
            System.out.println("letter " + k + "\t" + counts[k]);
        }

        System.out.println("Most commong letter: " + maxIndex(counts));
    }
    
    public void testDecrypt(){
        CaesarCipher cc = new CaesarCipher();
        int key = 15;
        String encrypted = cc.encrypt("deadbeef", key);
        System.out.println(encrypted);
        String message = decrypt(encrypted);
        System.out.println(message);
    }
}
