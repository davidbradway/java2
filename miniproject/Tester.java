import java.util.*;
import edu.duke.*;
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester {
    public void TestCaesarCipher() {
        CaesarCipher cc = new CaesarCipher(5);
        System.out.println("key: "+cc);
        
        char letter = 'a';
        char encrypted = cc.encryptLetter(letter);
        char decrypted = cc.decryptLetter(encrypted);
        System.out.println("input char: "+letter);
        System.out.println("enc char: "+encrypted);
        System.out.println("dec char: "+letter);
        
        String input = "aaeee";
        String encStr = cc.encrypt(input);
        String decStr = cc.decrypt(encStr);
        System.out.println("input str: "+input);
        System.out.println("enc str: "+encStr);
        System.out.println("dec str: "+decStr);
        
        FileResource fr = new FileResource("data/titus-small.txt");
        String contents = fr.asString();
        String encCont = cc.encrypt(contents);
        String decCont = cc.decrypt(encCont);
        System.out.println("input str: "+contents);
        System.out.println("enc str: "+encCont);
        System.out.println("dec str: "+decCont);
        fr = new FileResource("data/titus-small_key5.txt");
        String truth = fr.asString();
        System.out.println("truth: "+truth);
        System.out.println("dec truth: "+cc.decrypt(truth));
    }

    public void TestCaesarCracker() {
        CaesarCracker ccr = new CaesarCracker();        
        FileResource fr = new FileResource("data/titus-small_key5.txt");
        String encrypted = fr.asString();
        System.out.println("encrypted: "+encrypted);
        int key = ccr.getKey(encrypted);
        System.out.println("key: "+key);
        System.out.println("dec: "+ccr.decrypt(encrypted));

        ccr = new CaesarCracker('a');        
        fr = new FileResource("data/oslusiadas_key17.txt");
        encrypted = fr.asString();
        System.out.println("encrypted: "+encrypted);
        key = ccr.getKey(encrypted);
        System.out.println("key: "+key);
        System.out.println("dec: "+ccr.decrypt(encrypted));
    }

    public void testVigenereCipher() {
        int[] key = {17, 14, 12, 4};
        VigenereCipher vc = new VigenereCipher(key);
        System.out.println("key: "+vc);
        FileResource fr = new FileResource("data/titus-small.txt");
        String contents = fr.asString();
        String encCont = vc.encrypt(contents);
        String decCont = vc.decrypt(encCont);
        System.out.println("input str: "+contents);
        System.out.println("enc str: "+encCont);
        System.out.println("above should be: Tcmp-pxety mj nikhqv htee mrfhtii tyv");
        System.out.println("dec str: "+decCont);
    }
    
    public void testVigenereBreaker() {
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.sliceString("abcdefghijklm", 0, 3)+" should return adgjm");
        System.out.println(vb.sliceString("abcdefghijklm", 1, 3)+" should return behk");
        System.out.println(vb.sliceString("abcdefghijklm", 2, 3)+" should return cfil");
        System.out.println(vb.sliceString("abcdefghijklm", 0, 4)+" should return aeim");
        System.out.println(vb.sliceString("abcdefghijklm", 1, 4)+" should return bfj");
        System.out.println(vb.sliceString("abcdefghijklm", 2, 4)+" should return cgk");
        System.out.println(vb.sliceString("abcdefghijklm", 3, 4)+" should return dhl");
        System.out.println(vb.sliceString("abcdefghijklm", 0, 5)+" should return afk");
        System.out.println(vb.sliceString("abcdefghijklm", 1, 5)+" should return bgl");
        System.out.println(vb.sliceString("abcdefghijklm", 2, 5)+" should return chm");
        System.out.println(vb.sliceString("abcdefghijklm", 3, 5)+" should return di");
        System.out.println(vb.sliceString("abcdefghijklm", 4, 5)+" should return ej");
    }
    
    public void testTryKeyLength() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("data/athens_keyflute.txt");
        String encrypted = fr.asString();
        //make sure you get the key “flute”={5, 11, 20, 19, 4}.
        int[] keys = vb.tryKeyLength(encrypted, 5, 'e');
        System.out.println(Arrays.toString(keys));
    }
}
