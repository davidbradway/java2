import edu.duke.*;

/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //ring alphabet = "XYZABCDEFGHIJKLMNOPQRSTUVW";
        String alphabetShifted = alphabet.substring(key) + alphabet.substring(0, key);
        
        for (int i = 0; i < input.length(); i++) {
            char letter = encrypted.charAt(i);
            int index = alphabet.indexOf(Character.toUpperCase(letter));
            if (index != -1) {
                
                if (Character.isUpperCase(letter)) {
                    encrypted.setCharAt(i, alphabetShifted.charAt(index));
                }
                else {
                    encrypted.setCharAt(i, Character.toLowerCase(alphabetShifted.charAt(index)));
                }
            }
        }
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        
        for (int i=0; i < input.length(); i++) {
            int key;
            if (i % 2 == 0) {
                key = key1;
            }
            else {
                key = key2;
            }
            String stringchar = encrypt(input.substring(i,i+1), key);
            encrypted.setCharAt(i, stringchar.charAt(0));
        }
        return encrypted.toString();
    }

    public void testEncryptTwoKeys() {
        System.out.println("encryptTwoKeys(“First Legion”, 23, 17) should return ");
        System.out.println("Czojq Ivdzle");
        System.out.println(encryptTwoKeys("First Legion", 23, 17));

        System.out.println("encryptTwoKeys(\"At noon be in the conference room with your hat on for a surprise party. YELL LOUD!\", 8, 21)");
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
    }

    public void testEncrypt () {
        System.out.println("encrypt(“FIRST LEGION ATTACK EAST FLANK!”, 23) should return the string");
        System.out.println("CFOPQ IBDFLK XQQXZH BXPQ CIXKH!: ");
        System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!", 23));
        
        System.out.println("encrypt(“First Legion”, 23) should return the string");
        System.out.println("Cfopq Ibdflk: ");
        System.out.println(encrypt("First Legion", 23));
        
        System.out.println("encrypt(“First Legion”, 17) should return the string");
        System.out.println("Wzijk Cvxzfe: ");
        System.out.println(encrypt("First Legion", 17));
        
        System.out.println("encrypt(\"At noon be in the conference room with your hat on for a surprise party. YELL LOUD!\", 15)");
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
    }
    
    public  void testCaesar() {
        /* This method should read a file and 
         * encrypt the complete file using the Caesar Cipher algorithm, 
         * printing the encrypted message. 
         * */
         int key = 23;
         FileResource fr = new FileResource("message1.txt");
         String message = fr.asString();
         String encrypted = encrypt(message, key);
         System.out.println("key is " + key + "\n" + encrypted);

    }
}
