
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipher {
    
    // Methods

    // Helpers
    private int getKey(String s) {
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

    // countLetters
    private void countLetters(String encrypted, int[] counts){
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

    // maxIndex
    private int maxIndex(int[] values){
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

    public String chooseFileGetString() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        return input;
    }

    // breakCaesarcipher
    public String breakCaesarCipher(String input) {
        /*  figure out which key was used to encrypt this message 
         * (in a similar manner as the previous lesson), 
         * then create a CaesarCipher object with that key
         * and decrypt the message. */
        int key = getKey(input);
        CaesarCipher cc = new CaesarCipher(key);
        String decrypted = cc.decrypt(input);
        return decrypted;
    }
    
    // simpleTests
    public void simpleTests() {
        String input = chooseFileGetString();
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(input);
        System.out.println("Encrypted: " + encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
        String broken = breakCaesarCipher(encrypted);
        System.out.println("Broken: " + broken);
        
        input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        cc = new CaesarCipher(15);
        encrypted = cc.encrypt(input);
        System.out.println("Encrypted: " + encrypted);
        decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
        broken = breakCaesarCipher(encrypted);
        System.out.println("Broken: " + broken);
        
    }
}
