/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
    // Methods

    // Helpers
    // getKey
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

    // halfOfString
    private String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        for (int k = start; k<message.length(); k+=2){
            sb.append(message.charAt(k));
        }
        return sb.toString();
    }
    
    public String breakCaesarCipher(String input) {
        String input1 = halfOfString(input, 0);
        String input2 = halfOfString(input, 1);
        int key1 = getKey(input1);
        int key2 = getKey(input2);
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        String decrypted = cc.decrypt(input);
        return decrypted;
    }
    
    public void simpleTests() {
        /* This method should read in a file as a String, 
         * create a CaesarCipherTwo object with keys 17 and 3, 
         * encrypt the String using the CaesarCipherTwo object, 
         * print the encrypted String, 
         * and decrypt the encrypted String using the decrypt method. */
          
        FileResource fr = new FileResource();
        String input = fr.asString();
        //int key = getKey(input);
        CaesarCipherTwo cc = new CaesarCipherTwo(17, 3);
        String encrypted = cc.encrypt(input);
        System.out.println("Encrypted: " + encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
        String broken = breakCaesarCipher(encrypted);
        System.out.println("Broken: " + broken);
        
        
        input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        cc = new CaesarCipherTwo(21, 8);
        encrypted = cc.encrypt(input);
        System.out.println("Encrypted: " + encrypted);
        
        cc = new CaesarCipherTwo(14, 24);
        decrypted = cc.decrypt("Hfs cpwewloj loks cd Hoto kyg Cyy.");
        System.out.println("Decrypted: " + decrypted);
        
        broken = breakCaesarCipher("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
        System.out.println("Broken: " + broken);
        
        fr = new FileResource("data/mysteryTwoKeysQuiz.txt");
        input = fr.asString();
        broken = breakCaesarCipher(input);
        System.out.println("Broken: " + broken);
        
        String input1 = halfOfString(input, 0);
        String input2 = halfOfString(input, 1);
        int key1 = getKey(input1);
        int key2 = getKey(input2);
        System.out.println("key1: " + key1);
        System.out.println("key2: " + key2);
        
    }
}
