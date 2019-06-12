package knowledgeBase;


import entities.phonetics.Consonant;
import entities.phonetics.Phoneme;
import entities.phonetics.Vowel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/************ Singleton class
 *
 * Provides a table with all the vowels and consonants and its parameters
 * Every vowel is an object of Vowel class
 * Every consonant is an object of Consonant class
 *
 * *********************/
public class SoundsBank {

    private HashMap<String, Phoneme> allPhonemesTable;

    public HashMap<String, Phoneme> getAllPhonemesTable() {
        return allPhonemesTable;
    }

    private static HashMap<Object, Integer> allPhTypesMap = null;

    /**
     *  **************************  ENUMS
     */
    public enum Height {
        OPEN, NEAR_OPEN, OPEN_MID, MID, CLOSE_MID, NEAR_CLOSE, CLOSE
    }

    public enum Backness {
        BACK, CENTRAL, FRONT
    }

    public enum Roundness {
        ROUNDED, UNROUNDED
    }

    public enum Nasalization {
        NASAL, NON_NASAL
    }

    /**
     * *************************  SINGLETON
     * **/
    private static SoundsBank instance;

    public static SoundsBank getInstance() {
        if (instance == null) {
            instance = new SoundsBank();
        }
        return instance;
    }

    /**
     * ************************* CONSTRUCTOR
     * **/
    public SoundsBank() {
        this.allPhonemesTable = new HashMap<>();
        addConsonants();
        addVowels();
        // TODO all the affricates
        // TODO all the diacritics
    }

    public static HashMap<Object, Integer> getAllPhonotypes() {

        if (allPhTypesMap != null) {
            return allPhTypesMap;

        } else {
            HashMap<Object, Integer> map = new HashMap<>();

            // Height
            Object[] hArr = Height.values();
            for (Object ob : hArr) {
                map.put(ob, 0);
            }

            // Backness
            hArr = Backness.values();
            for (int i = 0; i < hArr.length; i++) {
                map.put(hArr[i], 0);
            }

            // Roundness
            hArr = Roundness.values();
            for (Object ob : hArr) {
                map.put(ob, 0);
            }

            // Nasalization
            hArr = Nasalization.values();
            for (Object ob : hArr) {
                map.put(ob, 0);
            }

            // Cons.MannerApprox
            hArr = Consonant.MannerApproximate.values();
            for (Object ob : hArr) {
                map.put(ob, 0);
            }

            // Cons.MannerPrecise
            hArr = Consonant.MannerPricise.values();
            for (Object ob : hArr) {
                map.put(ob, 0);
            }

            // Cons.PlaceApprox
            hArr = Consonant.PlaceApproximate.values();
            for (Object ob : hArr) {
                map.put(ob, 0);
            }

            // Cons.PlacePrecise
            hArr = Consonant.PlacePrecise.values();
            for (Object ob : hArr) {
                map.put(ob, 0);
            }

            return map;
        }
    }

    private void addConsonants() {
        HashMap<String, Phoneme> table = this.allPhonemesTable;

        // consonants
        // STOPS
        table.put("p", new Consonant("p", Consonant.PlacePrecise.BILABIAL, Consonant.MannerPricise.STOP));
        table.put("b", new Consonant("b", Consonant.PlacePrecise.BILABIAL, Consonant.MannerPricise.STOP, true));
        table.put("p̪", new Consonant("p̪", Consonant.PlacePrecise.LABIODENTAL, Consonant.MannerPricise.STOP));
        table.put("b̪", new Consonant("b̪", Consonant.PlacePrecise.LABIODENTAL, Consonant.MannerPricise.STOP, true));
        //table.put("t̼̪", new Consonant("t̼", Consonant.PlacePrecise.LABIODENTAL, Consonant.MannerPricise.STOP));
        //table.put("d̼̪", new Consonant("d̼̪", Consonant.PlacePrecise.LABIODENTAL, Consonant.MannerPricise.STOP, true));
        table.put("t", new Consonant("t", Consonant.PlacePrecise.ALVEOLAR, Consonant.MannerPricise.STOP));
        table.put("d", new Consonant("d", Consonant.PlacePrecise.ALVEOLAR, Consonant.MannerPricise.STOP, true));
        table.put("ʈ", new Consonant("ʈ", Consonant.PlacePrecise.RETROFLEX, Consonant.MannerPricise.STOP));
        table.put("ɖ", new Consonant("ɖ", Consonant.PlacePrecise.RETROFLEX, Consonant.MannerPricise.STOP, true));
        table.put("c", new Consonant("c", Consonant.PlacePrecise.PALATAL, Consonant.MannerPricise.STOP));
        table.put("ɟ", new Consonant("ɟ", Consonant.PlacePrecise.PALATAL, Consonant.MannerPricise.STOP, true));
        table.put("k", new Consonant("k", Consonant.PlacePrecise.VELAR, Consonant.MannerPricise.STOP));
        table.put("g", new Consonant("g", Consonant.PlacePrecise.VELAR, Consonant.MannerPricise.STOP, true));
        table.put("q", new Consonant("q", Consonant.PlacePrecise.UVULAR, Consonant.MannerPricise.STOP));
        table.put("ɢ", new Consonant("ɢ", Consonant.PlacePrecise.UVULAR, Consonant.MannerPricise.STOP, true));
        table.put("ʡ", new Consonant("ʡ", Consonant.PlacePrecise.EPIGLOTTAL, Consonant.MannerPricise.STOP));
        table.put("ʔ", new Consonant("ʔ", Consonant.PlacePrecise.GLOTTAL, Consonant.MannerPricise.STOP));

        // FRICATIVES
        // SIBILANTS
        table.put("s", new Consonant("s", Consonant.PlacePrecise.ALVEOLAR, Consonant.MannerPricise.SIBILANT));
        table.put("z", new Consonant("z", Consonant.PlacePrecise.ALVEOLAR, Consonant.MannerPricise.SIBILANT, true));
        table.put("ʃ", new Consonant("ʃ", Consonant.PlacePrecise.POSTALVEOLAR, Consonant.MannerPricise.SIBILANT));
        // TODO подтвердить характериситки š
        table.put("š", new Consonant("š", Consonant.PlacePrecise.POSTALVEOLAR, Consonant.MannerPricise.SIBILANT));
        table.put("ʒ", new Consonant("ʒ", Consonant.PlacePrecise.POSTALVEOLAR, Consonant.MannerPricise.SIBILANT, true));
        table.put("ʂ", new Consonant("ʂ", Consonant.PlacePrecise.RETROFLEX, Consonant.MannerPricise.SIBILANT));
        table.put("ʐ", new Consonant("ʐ", Consonant.PlacePrecise.RETROFLEX, Consonant.MannerPricise.SIBILANT, true));
        table.put("ɕ", new Consonant("ɕ", Consonant.PlacePrecise.PALATAL, Consonant.MannerPricise.SIBILANT));
        table.put("ʑ", new Consonant("ʑ", Consonant.PlacePrecise.PALATAL, Consonant.MannerPricise.SIBILANT, true));

        // NON_SIBILANTS
        table.put("ɸ", new Consonant("ɸ", Consonant.PlacePrecise.BILABIAL, Consonant.MannerPricise.FRICATIVE));
        table.put("β", new Consonant("β", Consonant.PlacePrecise.BILABIAL, Consonant.MannerPricise.FRICATIVE, true));
        table.put("f", new Consonant("f", Consonant.PlacePrecise.LABIODENTAL, Consonant.MannerPricise.FRICATIVE));
        table.put("v", new Consonant("v", Consonant.PlacePrecise.LABIODENTAL, Consonant.MannerPricise.FRICATIVE, true));
        table.put("θ", new Consonant("θ", Consonant.PlacePrecise.DENTAL, Consonant.MannerPricise.FRICATIVE));
        table.put("ð", new Consonant("ð", Consonant.PlacePrecise.DENTAL, Consonant.MannerPricise.FRICATIVE, true));
        table.put("ç", new Consonant("ç", Consonant.PlacePrecise.PALATAL, Consonant.MannerPricise.FRICATIVE));
        table.put("ʝ", new Consonant("ʝ", Consonant.PlacePrecise.PALATAL, Consonant.MannerPricise.FRICATIVE, true));
        table.put("x", new Consonant("x", Consonant.PlacePrecise.VELAR, Consonant.MannerPricise.FRICATIVE));
        table.put("ɣ", new Consonant("ɣ", Consonant.PlacePrecise.VELAR, Consonant.MannerPricise.FRICATIVE, true));
        table.put("χ", new Consonant("χ", Consonant.PlacePrecise.UVULAR, Consonant.MannerPricise.FRICATIVE));
        table.put("ʁ", new Consonant("ʁ", Consonant.PlacePrecise.UVULAR, Consonant.MannerPricise.FRICATIVE, true));
        table.put("ħ", new Consonant("ħ", Consonant.PlacePrecise.EPIGLOTTAL, Consonant.MannerPricise.FRICATIVE));
        table.put("ʕ", new Consonant("ʕ", Consonant.PlacePrecise.EPIGLOTTAL, Consonant.MannerPricise.FRICATIVE, true));
        table.put("h", new Consonant("h", Consonant.PlacePrecise.GLOTTAL, Consonant.MannerPricise.FRICATIVE));
        table.put("ɦ", new Consonant("ɦ", Consonant.PlacePrecise.GLOTTAL, Consonant.MannerPricise.FRICATIVE, true));

        // SONORANT
        // NASAL
        table.put("m", new Consonant("m", Consonant.PlacePrecise.BILABIAL, Consonant.MannerPricise.NASAL, true));
        table.put("n", new Consonant("n", Consonant.PlacePrecise.ALVEOLAR, Consonant.MannerPricise.NASAL, true));
        table.put("ɳ", new Consonant("ɳ", Consonant.PlacePrecise.RETROFLEX, Consonant.MannerPricise.NASAL, true));
        table.put("ɲ", new Consonant("ɲ", Consonant.PlacePrecise.PALATAL, Consonant.MannerPricise.NASAL, true));
        table.put("ŋ", new Consonant("ŋ", Consonant.PlacePrecise.VELAR, Consonant.MannerPricise.NASAL, true));
        table.put("ɴ", new Consonant("ɴ", Consonant.PlacePrecise.UVULAR, Consonant.MannerPricise.NASAL, true));

        // TRILL
        table.put("r", new Consonant("r", Consonant.PlacePrecise.ALVEOLAR, Consonant.MannerPricise.TRILL, true));
        table.put("ʀ", new Consonant("ʀ", Consonant.PlacePrecise.UVULAR, Consonant.MannerPricise.TRILL, true));
        table.put("ʢ", new Consonant("ʢ", Consonant.PlacePrecise.EPIGLOTTAL, Consonant.MannerPricise.TRILL, true));

        // APPROXIMANT
    }

    private void addVowels() {
        // HOW TO GET UNICODE IF NEEDED
        /*String e = "ẽ";
        for (int i = 0; i < e.length(); i++) {
            System.out.print( "\\u" + Integer.toHexString(e.charAt(i) | 0x10000).substring(1));
            System.out.print(" ");
        }*/

        HashMap<String, Phoneme> table = this.allPhonemesTable;

        //vowels
        //front
        table.put("i", new Vowel("i", Height.CLOSE, Backness.FRONT, Roundness.UNROUNDED,Nasalization.NON_NASAL));
        table.put("ĩ", new Vowel("ĩ", Height.CLOSE, Backness.FRONT, Roundness.UNROUNDED,Nasalization.NASAL));
        table.put("y", new Vowel("y", Height.CLOSE, Backness.FRONT, Roundness.ROUNDED, Nasalization.NON_NASAL));
        table.put("e", new Vowel("e", Height.CLOSE_MID, Backness.FRONT, Roundness.UNROUNDED, Nasalization.NON_NASAL));
        table.put("ẽ", new Vowel("ẽ", Height.CLOSE_MID, Backness.FRONT, Roundness.UNROUNDED, Nasalization.NASAL));

        table.put("ø", new Vowel("ø", Height.CLOSE_MID, Backness.FRONT, Roundness.ROUNDED, Nasalization.NON_NASAL));
        table.put("ɛ", new Vowel("ɛ", Height.OPEN_MID, Backness.FRONT, Roundness.UNROUNDED, Nasalization.NON_NASAL));
        table.put("œ", new Vowel("œ", Height.OPEN_MID, Backness.FRONT, Roundness.ROUNDED, Nasalization.NON_NASAL));
        table.put("æ", new Vowel("æ", Height.NEAR_OPEN, Backness.FRONT, Roundness.UNROUNDED, Nasalization.NON_NASAL));
        table.put("a", new Vowel("a", Height.OPEN, Backness.FRONT, Roundness.UNROUNDED, Nasalization.NON_NASAL));
        table.put("ɶ", new Vowel("ɶ", Height.OPEN, Backness.FRONT, Roundness.ROUNDED, Nasalization.NON_NASAL));

        // central
        table.put("ɨ", new Vowel("ɨ", Height.CLOSE, Backness.CENTRAL, Roundness.UNROUNDED, Nasalization.NON_NASAL));
        table.put("ʉ", new Vowel("ʉ", Height.CLOSE, Backness.CENTRAL, Roundness.ROUNDED, Nasalization.NON_NASAL));
        table.put("ɘ", new Vowel("ɘ", Height.CLOSE_MID, Backness.CENTRAL, Roundness.UNROUNDED, Nasalization.NON_NASAL));
        table.put("ɵ", new Vowel("ɵ", Height.CLOSE_MID, Backness.CENTRAL, Roundness.ROUNDED, Nasalization.NON_NASAL));
        table.put("ə", new Vowel("ə", Height.MID, Backness.CENTRAL, Roundness.UNROUNDED, Nasalization.NON_NASAL)); // \u0259
        table.put("ǝ", new Vowel("ǝ", Height.MID, Backness.CENTRAL, Roundness.UNROUNDED, Nasalization.NON_NASAL)); // \u01DD

        table.put("ɜ", new Vowel("ɜ", Height.OPEN_MID, Backness.CENTRAL, Roundness.UNROUNDED, Nasalization.NON_NASAL));
        table.put("ɞ", new Vowel("ɞ", Height.OPEN_MID, Backness.CENTRAL, Roundness.ROUNDED, Nasalization.NON_NASAL));
        table.put("ä", new Vowel("ä", Height.OPEN, Backness.CENTRAL, Roundness.UNROUNDED, Nasalization.NON_NASAL));

        // back
        table.put("ɯ", new Vowel("ɯ", Height.CLOSE, Backness.BACK, Roundness.UNROUNDED, Nasalization.NON_NASAL));
        table.put("u", new Vowel("u", Height.CLOSE, Backness.BACK, Roundness.ROUNDED, Nasalization.NON_NASAL));
        table.put("ʊ", new Vowel("ʊ", Height.NEAR_CLOSE, Backness.BACK, Roundness.ROUNDED, Nasalization.NON_NASAL));
        table.put("ɤ", new Vowel("ɤ", Height.CLOSE_MID, Backness.BACK, Roundness.UNROUNDED, Nasalization.NON_NASAL));
        table.put("o", new Vowel("o", Height.CLOSE_MID, Backness.BACK, Roundness.ROUNDED, Nasalization.NON_NASAL));
        table.put("õ", new Vowel("õ", Height.CLOSE_MID, Backness.BACK, Roundness.ROUNDED, Nasalization.NASAL));
        table.put("ʌ", new Vowel("ʌ", Height.OPEN_MID, Backness.BACK, Roundness.UNROUNDED, Nasalization.NON_NASAL));
        table.put("ɔ", new Vowel("ɔ", Height.OPEN_MID, Backness.BACK, Roundness.ROUNDED, Nasalization.NON_NASAL));
        table.put("ɑ", new Vowel("ɑ", Height.OPEN, Backness.BACK, Roundness.UNROUNDED, Nasalization.NON_NASAL));
        table.put("ɒ", new Vowel("ɒ", Height.OPEN, Backness.BACK, Roundness.ROUNDED, Nasalization.NON_NASAL));
    }

    private void addAffricates() {
        HashMap<String, Phoneme> table = this.allPhonemesTable;

        //vowels
        //front
        table.put("ts", new Consonant("ts", Consonant.PlacePrecise.ALVEOLAR, Consonant.MannerPricise.SIBILANT_AFFRICATE, false));
        table.put("dz", new Consonant("dz", Consonant.PlacePrecise.ALVEOLAR, Consonant.MannerPricise.SIBILANT_AFFRICATE, true));
        table.put("ʈʂ", new Consonant("ʈʂ", Consonant.PlacePrecise.RETROFLEX, Consonant.MannerPricise.SIBILANT_AFFRICATE, false));
        table.put("ɖʐ", new Consonant("ɖʐ", Consonant.PlacePrecise.RETROFLEX, Consonant.MannerPricise.SIBILANT_AFFRICATE, true));
        table.put("tɕ", new Consonant("tɕ", Consonant.PlacePrecise.PALATAL, Consonant.MannerPricise.SIBILANT_AFFRICATE, false));
        table.put("dʑ", new Consonant("dʑ", Consonant.PlacePrecise.PALATAL, Consonant.MannerPricise.SIBILANT_AFFRICATE, true));




    }

    public Phoneme find(String requestedSymbol) {
        return allPhonemesTable.get(requestedSymbol);
    }

    public static boolean isExtraSign(String symbol) {
        String[] symbols = {
                ".", ",", "-", "="
        };
        boolean result = false;

        for (String s :symbols) {
            if (s.equals(symbol)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
