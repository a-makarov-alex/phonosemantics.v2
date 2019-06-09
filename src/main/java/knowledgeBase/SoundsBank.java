package knowledgeBase;


import entities.phonetics.Consonant;
import entities.phonetics.Phoneme;
import entities.phonetics.Vowel;

import java.util.HashMap;

/************ Singleton class
 *
 * Provides a table with all the vowels and consonants and its parameters
 * Every vowel is an object of Vowel class
 * Every consonant is an object of Consonant class
 *
 * *********************/
public class SoundsBank {

    private HashMap<String, Phoneme> allConsonantsTable;

    public HashMap<String, Phoneme> getAllConsonantsTable() {
        return allConsonantsTable;
    }

    private static HashMap<Object, Integer> allPhTypesMap = null;

    /**
     * SINGLETON
     * **/
    private static SoundsBank instance;

    public static SoundsBank getInstance() {
        if (instance == null) {
            instance = new SoundsBank();
        }
        return instance;
    }

    /**
     * CONSTRUCTOR
     * **/
    public SoundsBank() {
        this.allConsonantsTable = new HashMap<>();
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

            // Vowel.Height
            Vowel.Height[] hArr = Vowel.Height.values();
            for (int i = 0; i < hArr.length; i++) {
                map.put(hArr[i], 0);
            }

            // Vowel.Backness
            Vowel.Backness[] bArr = Vowel.Backness.values();
            for (int i = 0; i < bArr.length; i++) {
                map.put(bArr[i], 0);
            }

            // Cons.MannerApprox
            Consonant.MannerApproximate[] maArr = Consonant.MannerApproximate.values();
            for (int i = 0; i < maArr.length; i++) {
                map.put(maArr[i], 0);
            }

            // Cons.MannerPrecise
            Consonant.MannerPricise[] mpArr = Consonant.MannerPricise.values();
            for (int i = 0; i < mpArr.length; i++) {
                map.put(mpArr[i], 0);
            }

            // Cons.PlaceApprox
            Consonant.PlaceApproximate[] paArr = Consonant.PlaceApproximate.values();
            for (int i = 0; i < paArr.length; i++) {
                map.put(paArr[i], 0);
            }

            // Cons.PlacePrecise
            Consonant.PlacePrecise[] ppArr = Consonant.PlacePrecise.values();
            for (int i = 0; i < ppArr.length; i++) {
                map.put(ppArr[i], 0);
            }

            return map;
        }
    }

    private void addConsonants() {
        HashMap<String, Phoneme> table = this.allConsonantsTable;

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
        HashMap<String, Phoneme> table = this.allConsonantsTable;

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

    private void addAffricates() {
        HashMap<String, Phoneme> table = this.allConsonantsTable;

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
        return allConsonantsTable.get(requestedSymbol);
    }
}
