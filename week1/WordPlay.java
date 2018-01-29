
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    /* You will write a program to transform words from a file
     * into another form, such as replacing vowels with an asterix.
     */
    public boolean isVowel(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || 
            ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U' ) {
            return true;
        }
        return false;
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder replaced = new StringBuilder(phrase);
        
        /* Be sure to call the method isVowel that you wrote and also test this method. */
        for (int i=0; i < phrase.length(); i++) {
            if (isVowel(replaced.charAt(i))) {
                replaced.setCharAt(i, ch);
            } 
        }
        return replaced.toString();
    }

    public String emphasize(String phrase, char ch) {
        StringBuilder replaced = new StringBuilder(phrase);
        
        for (int i=0; i < phrase.length(); i++) {
            if (Character.toLowerCase(replaced.charAt(i)) == Character.toLowerCase(ch)) {
                if (i % 2 == 0) {
                    replaced.setCharAt(i, '*');
                }
                else {
                    replaced.setCharAt(i, '+');
                }
            }
        }
        return replaced.toString();
    }
    
    public void testEmphasize() {
         System.out.print("emphasize(“dna ctgaaactga”, ‘a’) should return “dn* ctg+*+ctg+” ");
         System.out.println(emphasize("dna ctgaaactga", 'a'));

         System.out.print("Mary Bella Abracadabra”, ‘a’ should return “M+ry Bell+ +br*c*d*br+” ");
         System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }    
    
    public void testReplaceVowels() {
        System.out.print("replaceVowels(“Hello World”, ‘*’) ");
        System.out.print("should return returns the string “H*ll* W*rld”: ");
        System.out.println(replaceVowels("Hello World", '*'));
        
    }

    public void testIsVowel() {
        System.out.println("isVowel(‘F’) should return false: " +  isVowel('F'));

        System.out.println("isVowel(‘a’) should return true: " +  isVowel('a'));
    }
}
