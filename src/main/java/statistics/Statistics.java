package statistics;

import entities.Word;
import entities.WordList;
import entities.phonetics.Consonant;
import entities.phonetics.Vowel;
import main.Main;

import java.util.HashMap;
import java.util.Map;

public class Statistics {
    // kinds of calculations
    public static final String WORDS_WITH_PHTYPE_PER_LIST = "w per list";
    public static final String PHTYPES_PER_LIST = "ph per list";
    public static final String PHTYPES_AVERAGE_PER_WORD = "ph per word";

    // Wordlists are added here with semantics as a key
    // TODO: create a Semantics class
    // Remember that Word has Semantics field. No need of HashMap
    WordList globalWordlist = Main.getInputFile().getWordList("Big"); //TODO: =getGlobalWordlist() в классе InputFile


    /**
     *  Class for calculating statistics for some sample
     *  **/
    public class Sample {
        // TODO: выборка и методы по ней
    }


    // Counts all ph-types for further statistics
    public HashMap<Object, Integer> countAllPhonotypesPerList() {
        HashMap<Object, Integer> mapAllPh = getAllPhonotypes();
        HashMap<Object, Integer> mapWordsWithPh = new HashMap<>();

        for (Map.Entry<Object, Integer> entry : mapAllPh.entrySet()) {

            Object phType = entry.getKey();

        //TODO    System.out.println("");
        //TODO    System.out.println(phType);

                int counterPh = 0;
                int counterW = 0;
                for (Word w : this.globalWordlist.getList()) {
                    System.out.print(phType + " : ");
                    counterPh += w.countPhonotype(phType);
                    if (counterPh > 0) {
                        counterW++;
                    }
            //TODO    System.out.println(w.getWord() + " " + counterPh + " " + counterW);
                }
                entry.setValue(counterPh);
                mapWordsWithPh.put(entry.getKey(), counterW);

           /*for (Word w : this.globalWordlist.getList()) {
               entry.setValue(w.countPhonotype(entry.getKey()));
           }*/
        }

        // TODO: mapWords!!!!!
        return mapAllPh;
    }



    public static HashMap<Object, Integer> getAllPhonotypes() {
        HashMap<Object, Integer> map = new HashMap<>();

        // Vowel.Height
        Vowel.Height[] hArr = Vowel.Height.values();
        for (int i=0; i < hArr.length; i++) {
            map.put(hArr[i], 0);
        }

        // Vowel.Backness
        Vowel.Backness[] bArr = Vowel.Backness.values();
        for (int i=0; i < bArr.length; i++) {
            map.put(bArr[i], 0);
        }

        // Cons.MannerApprox
        Consonant.MannerApproximate[] maArr = Consonant.MannerApproximate.values();
        for (int i=0; i < maArr.length; i++) {
            map.put(maArr[i], 0);
        }

        // Cons.MannerPrecise
        Consonant.MannerPricise[] mpArr = Consonant.MannerPricise.values();
        for (int i=0; i < mpArr.length; i++) {
            map.put(mpArr[i], 0);
        }

        // Cons.PlaceApprox
        Consonant.PlaceApproximate[] paArr = Consonant.PlaceApproximate.values();
        for (int i=0; i < paArr.length; i++) {
            map.put(paArr[i], 0);
        }

        // Cons.PlacePrecise
        Consonant.PlacePrecise[] ppArr = Consonant.PlacePrecise.values();
        for (int i=0; i < ppArr.length; i++) {
            map.put(ppArr[i], 0);
        }

        return map;
    }
}
