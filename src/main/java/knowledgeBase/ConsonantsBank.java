package knowledgeBase;


import entities.phonetics.Consonant;
import entities.phonetics.Phoneme;
import entities.phonetics.Vowel;

import java.util.HashMap;

/************ Singleton class
 *
 * Provides a table with all the vowels and its parameters
 * Every vowel is an object of Vowel class
 *
 * *********************/
public class ConsonantsBank {

    private static ConsonantsBank instance;

    public static ConsonantsBank getInstance() {
        if (instance == null) {
            System.out.println("NEW ONE");
            instance = new ConsonantsBank();
        }
        return instance;
    }

    private HashMap<String, Consonant> allConsonantsTable;

    public ConsonantsBank() {
        this.allConsonantsTable = new HashMap<>();
        this.fillConsonantsTable();
    }

    private void fillConsonantsTable() {
        HashMap<String, Consonant> table = this.allConsonantsTable;

        System.out.println(table.toString());
        System.out.println(this.allConsonantsTable.toString());

        // TODO all the affricates
        // TODO all the diacritics

        // consonants
        // STOPS
        table.put("p", new Consonant("p", Consonant.PlacePrecise.BILABIAL, Consonant.MannerPricise.STOP));
        table.put("b", new Consonant("b", Consonant.PlacePrecise.BILABIAL, Consonant.MannerPricise.STOP, true));
        table.put("t", new Consonant("t", Consonant.PlacePrecise.ALVEOLAR, Consonant.MannerPricise.STOP));
        table.put("d", new Consonant("d", Consonant.PlacePrecise.ALVEOLAR, Consonant.MannerPricise.STOP, true));
        table.put("k", new Consonant("k", Consonant.PlacePrecise.VELAR, Consonant.MannerPricise.STOP));
        table.put("g", new Consonant("g", Consonant.PlacePrecise.VELAR, Consonant.MannerPricise.STOP, true));
        table.put("q", new Consonant("q", Consonant.PlacePrecise.UVULAR, Consonant.MannerPricise.STOP));
        table.put("ʔ", new Consonant("ʔ", Consonant.PlacePrecise.GLOTTAL, Consonant.MannerPricise.STOP));

        // FRICATIVES
        // SIBILANTS
        table.put("s", new Consonant("s", Consonant.PlacePrecise.ALVEOLAR, Consonant.MannerPricise.SIBILANT));
        table.put("z", new Consonant("z", Consonant.PlacePrecise.ALVEOLAR, Consonant.MannerPricise.SIBILANT, true));
        table.put("ʃ", new Consonant("ʃ", Consonant.PlacePrecise.POSTALVEOLAR, Consonant.MannerPricise.SIBILANT));
        // TODO подтвердить характериситки š
        table.put("š", new Consonant("š", Consonant.PlacePrecise.POSTALVEOLAR, Consonant.MannerPricise.SIBILANT));
        table.put("ʒ", new Consonant("ʒ", Consonant.PlacePrecise.POSTALVEOLAR, Consonant.MannerPricise.SIBILANT, true));

        // NON_SIBILANTS
        table.put("ɸ", new Consonant("ɸ", Consonant.PlacePrecise.BILABIAL, Consonant.MannerPricise.FRICATIVE));
        table.put("β", new Consonant("β", Consonant.PlacePrecise.BILABIAL, Consonant.MannerPricise.FRICATIVE, true));
        table.put("f", new Consonant("f", Consonant.PlacePrecise.LABIODENTAL, Consonant.MannerPricise.FRICATIVE));
        table.put("v", new Consonant("v", Consonant.PlacePrecise.LABIODENTAL, Consonant.MannerPricise.FRICATIVE, true));
        table.put("θ", new Consonant("θ", Consonant.PlacePrecise.DENTAL, Consonant.MannerPricise.FRICATIVE));
        table.put("ð", new Consonant("ð", Consonant.PlacePrecise.DENTAL, Consonant.MannerPricise.FRICATIVE, true));
        table.put("x", new Consonant("x", Consonant.PlacePrecise.VELAR, Consonant.MannerPricise.FRICATIVE));
        table.put("ɣ", new Consonant("ɣ", Consonant.PlacePrecise.VELAR, Consonant.MannerPricise.FRICATIVE, true));
        table.put("h", new Consonant("h", Consonant.PlacePrecise.GLOTTAL, Consonant.MannerPricise.FRICATIVE));

        // SONORANT
        // NASAL
        table.put("m", new Consonant("m", Consonant.PlacePrecise.BILABIAL, Consonant.MannerPricise.NASAL, true));
        table.put("n", new Consonant("n", Consonant.PlacePrecise.ALVEOLAR, Consonant.MannerPricise.NASAL, true));
        table.put("ɳ", new Consonant("ɳ", Consonant.PlacePrecise.RETROFLEX, Consonant.MannerPricise.NASAL, true));
        table.put("ɲ", new Consonant("ɲ", Consonant.PlacePrecise.PALATAL, Consonant.MannerPricise.NASAL, true));
        table.put("ŋ", new Consonant("ŋ", Consonant.PlacePrecise.VELAR, Consonant.MannerPricise.NASAL, true));
        table.put("ɴ", new Consonant("ɴ", Consonant.PlacePrecise.UVULAR, Consonant.MannerPricise.NASAL, true));

        // TODO test!!!
        table.put("ch", new Consonant("ch", Consonant.PlacePrecise.UVULAR, Consonant.MannerPricise.NASAL, true));

    }

    public Consonant find(String requestedSymbol) {
        return allConsonantsTable.get(requestedSymbol);
    }


    public HashMap<String, Consonant> getAllConsonantsTable() {
        return allConsonantsTable;
    }
}
