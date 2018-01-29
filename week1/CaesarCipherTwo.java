import edu.duke.*;

/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class CaesarCipherTwo {
    
    // Fields
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    
    // Methods
    // Contructor
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        mainKey1 = key1;
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey2 = key2;
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        
        for (int i=0; i < input.length(); i++) {
            char letter = encrypted.charAt(i);
            int index = alphabet.indexOf(Character.toUpperCase(letter));
            if (index != -1) {
                if (i % 2 == 0) { // even indexes or odd letter numbers
                    if (Character.isUpperCase(letter)) {
                        encrypted.setCharAt(i, shiftedAlphabet1.charAt(index));
                    }
                    else {
                        encrypted.setCharAt(i, Character.toLowerCase(shiftedAlphabet1.charAt(index)));
                    }
                }
                else { // odd indexes or even letter numbers
                    if (Character.isUpperCase(letter)) {
                        encrypted.setCharAt(i, shiftedAlphabet2.charAt(index));
                    }
                    else {
                        encrypted.setCharAt(i, Character.toLowerCase(shiftedAlphabet2.charAt(index)));
                    }
                }
            }
        }
        return encrypted.toString();
    }

    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cc.encrypt(input);
    }
}


