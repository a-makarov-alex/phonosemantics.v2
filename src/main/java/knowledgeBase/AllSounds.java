package knowledgeBase;

import entities.Sound;

import java.io.File;
import java.util.HashMap;

public class AllSounds {

    // TODO: maybe change String to unique enum type
    private static HashMap<String, Sound> allSoundsTable;

    // TODO: add file from current directory
    private File soundFile = new File("address_here");

    private void fillTheSoundsTable() {
        // TODO: fill the table here by parsing Excel file
        // for every row in file
        // if class is vowel create new Vowel() else create Consonant()
        // then fill all the fields for entity created like Height/Backness etc.
        // and put every sound into Map like
        // "a" : Vowel vowel_A
        // "b" : Consonant consonant_B etc.

        // THE RESULT IS A FULL TABLE WITH ALL THE SOUNDS FROM FILE AND SOUND OBJECTS FOR THEM
    }


}
