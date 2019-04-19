package knowledgeBase;

import entities.Phoneme;
import entities.Vowel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/** Singleton class **/
public class AllSounds {

    private static AllSounds instance;

    public static AllSounds getInstance() {
        if (instance == null) {
            instance = new AllSounds();
        }
        return instance;
    }

    // TODO: maybe change String/Char to unique enum type
    private HashMap<Character, Phoneme> allSoundsTable = new HashMap<Character, Phoneme>();

    // TODO: add file from current directory
    private File soundFile = new File("address_here");

    private AllSounds() {
        // Fill the hashmap with all the vowels and consonants

        instance.fillTheSoundsTable();
    }


    private void fillTheSoundsTable() {
        // THE RESULT IS A FULL TABLE WITH ALL THE PHONEMES FOR PARSING WORDLISTS
        HashMap<Character, Phoneme> table = instance.allSoundsTable;
        //vowels
        //front
        table.put('i', new Vowel('i', Vowel.Height.CLOSE, Vowel.Backness.FRONT, false,false));
        table.put('y', new Vowel('y', Vowel.Height.CLOSE, Vowel.Backness.FRONT, true, false));
        table.put('e', new Vowel('e', Vowel.Height.CLOSE_MID, Vowel.Backness.FRONT, false, false));
        table.put('ø', new Vowel('ø', Vowel.Height.CLOSE_MID, Vowel.Backness.FRONT, true, false));
        table.put('ɛ', new Vowel('ɛ', Vowel.Height.OPEN_MID, Vowel.Backness.FRONT, false, false));
        table.put('œ', new Vowel('œ', Vowel.Height.OPEN_MID, Vowel.Backness.FRONT, true, false));
        table.put('æ', new Vowel('æ', Vowel.Height.NEAR_OPEN, Vowel.Backness.FRONT, false, false));
        table.put('a', new Vowel('a', Vowel.Height.OPEN, Vowel.Backness.FRONT, false, false));
        table.put('ɶ', new Vowel('ɶ', Vowel.Height.OPEN, Vowel.Backness.FRONT, true, false));

        // central
        table.put('ɨ', new Vowel('ɨ', Vowel.Height.CLOSE, Vowel.Backness.CENTRAL, false, false));
        table.put('ʉ', new Vowel('ʉ', Vowel.Height.CLOSE, Vowel.Backness.CENTRAL, true, false));
        table.put('ɘ', new Vowel('ɘ', Vowel.Height.CLOSE_MID, Vowel.Backness.CENTRAL, false, false));
        table.put('ɵ', new Vowel('ɵ', Vowel.Height.CLOSE_MID, Vowel.Backness.CENTRAL, true, false));
        table.put('ə', new Vowel('ə', Vowel.Height.MID, Vowel.Backness.CENTRAL, false, false));
        table.put('ɜ', new Vowel('ɜ', Vowel.Height.OPEN_MID, Vowel.Backness.CENTRAL, false, false));
        table.put('ɞ', new Vowel('ɞ', Vowel.Height.OPEN_MID, Vowel.Backness.CENTRAL, true, false));
        table.put('ä', new Vowel('ä', Vowel.Height.OPEN, Vowel.Backness.CENTRAL, false, false));

        // back
        table.put('ɯ', new Vowel('ɯ', Vowel.Height.CLOSE, Vowel.Backness.BACK, false, false));
        table.put('u', new Vowel('u', Vowel.Height.CLOSE, Vowel.Backness.BACK, true, false));
        table.put('ʊ', new Vowel('ʊ', Vowel.Height.NEAR_CLOSE, Vowel.Backness.BACK, true, false));
        table.put('ɤ', new Vowel('ɤ', Vowel.Height.CLOSE_MID, Vowel.Backness.BACK, false, false));
        table.put('o', new Vowel('o', Vowel.Height.CLOSE_MID, Vowel.Backness.BACK, true, false));
        table.put('ʌ', new Vowel('ʌ', Vowel.Height.OPEN_MID, Vowel.Backness.BACK, false, false));
        table.put('ɔ', new Vowel('ɔ', Vowel.Height.OPEN_MID, Vowel.Backness.BACK, true, false));
        table.put('ɑ', new Vowel('ɑ', Vowel.Height.OPEN, Vowel.Backness.BACK, false, false));
        table.put('ɒ', new Vowel('ɒ', Vowel.Height.OPEN, Vowel.Backness.BACK, true, false));

        // consonants
        // TODO add all consonants


    }


    public HashMap<Character, Phoneme> getAllSoundsTable() {
        return allSoundsTable;
    }
}
