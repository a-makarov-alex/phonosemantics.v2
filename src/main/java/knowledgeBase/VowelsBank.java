package knowledgeBase;

import entities.phonetics.Vowel;

import java.util.HashMap;

/************ Singleton class
 *
 * Provides a table with all the vowels and its parameters
 * Every vowel is an object of Vowel class
 *
 * *********************/
public class VowelsBank {

    private static VowelsBank instance;

    public static VowelsBank getInstance() {
        if (instance == null) {
            instance = new VowelsBank();
        }
        return instance;
    }

    // TODO: maybe change String/Char to unique enum type
    private HashMap<String, Vowel> allVowelsTable = new HashMap<String, Vowel>();

    private VowelsBank() {
        getInstance().fillVowelsTable();
    }


    private void fillVowelsTable() {
        HashMap<String, Vowel> table = instance.allVowelsTable;
        //vowels
        //front
        table.put("i", new Vowel("i", Vowel.Height.CLOSE, Vowel.Backness.FRONT, false,false));
        table.put("y", new Vowel("y", Vowel.Height.CLOSE, Vowel.Backness.FRONT, true, false));
        table.put("e", new Vowel("e", Vowel.Height.CLOSE_MID, Vowel.Backness.FRONT, false, false));
        table.put("ø", new Vowel("ø", Vowel.Height.CLOSE_MID, Vowel.Backness.FRONT, true, false));
        table.put("ɛ", new Vowel("ɛ", Vowel.Height.OPEN_MID, Vowel.Backness.FRONT, false, false));
        table.put("œ", new Vowel("œ", Vowel.Height.OPEN_MID, Vowel.Backness.FRONT, true, false));
        table.put("æ", new Vowel("æ", Vowel.Height.NEAR_OPEN, Vowel.Backness.FRONT, false, false));
        table.put("a", new Vowel("a", Vowel.Height.OPEN, Vowel.Backness.FRONT, false, false));
        table.put("ɶ", new Vowel("ɶ", Vowel.Height.OPEN, Vowel.Backness.FRONT, true, false));

        // central
        table.put("ɨ", new Vowel("ɨ", Vowel.Height.CLOSE, Vowel.Backness.CENTRAL, false, false));
        table.put("ʉ", new Vowel("ʉ", Vowel.Height.CLOSE, Vowel.Backness.CENTRAL, true, false));
        table.put("ɘ", new Vowel("ɘ", Vowel.Height.CLOSE_MID, Vowel.Backness.CENTRAL, false, false));
        table.put("ɵ", new Vowel("ɵ", Vowel.Height.CLOSE_MID, Vowel.Backness.CENTRAL, true, false));
        table.put("ə", new Vowel("ə", Vowel.Height.MID, Vowel.Backness.CENTRAL, false, false));
        table.put("ɜ", new Vowel("ɜ", Vowel.Height.OPEN_MID, Vowel.Backness.CENTRAL, false, false));
        table.put("ɞ", new Vowel("ɞ", Vowel.Height.OPEN_MID, Vowel.Backness.CENTRAL, true, false));
        table.put("ä", new Vowel("ä", Vowel.Height.OPEN, Vowel.Backness.CENTRAL, false, false));

        // back
        table.put("ɯ", new Vowel("ɯ", Vowel.Height.CLOSE, Vowel.Backness.BACK, false, false));
        table.put("u", new Vowel("u", Vowel.Height.CLOSE, Vowel.Backness.BACK, true, false));
        table.put("ʊ", new Vowel("ʊ", Vowel.Height.NEAR_CLOSE, Vowel.Backness.BACK, true, false));
        table.put("ɤ", new Vowel("ɤ", Vowel.Height.CLOSE_MID, Vowel.Backness.BACK, false, false));
        table.put("o", new Vowel("o", Vowel.Height.CLOSE_MID, Vowel.Backness.BACK, true, false));
        table.put("ʌ", new Vowel("ʌ", Vowel.Height.OPEN_MID, Vowel.Backness.BACK, false, false));
        table.put("ɔ", new Vowel("ɔ", Vowel.Height.OPEN_MID, Vowel.Backness.BACK, true, false));
        table.put("ɑ", new Vowel("ɑ", Vowel.Height.OPEN, Vowel.Backness.BACK, false, false));
        table.put("ɒ", new Vowel("ɒ", Vowel.Height.OPEN, Vowel.Backness.BACK, true, false));

    }


    public HashMap<String, Vowel> getAllVowelsTable() {
        return allVowelsTable;
    }
}
