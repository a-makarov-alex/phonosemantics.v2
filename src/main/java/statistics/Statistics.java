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

    private HashMap<Object, Integer> mapPhTypesPerList = new HashMap<>();
    private HashMap<Object, Integer> mapWordsPerList = new HashMap<>();

    // Wordlists are added here with semantics as a key
    // TODO: create a Semantics class
    // Remember that Word has Semantics field. No need of HashMap
    private WordList globalWordlist = Main.getInputFile().getWordList("Big"); //TODO: =getGlobalWordlist() в классе InputFile


    /**
     *  Class for calculating statistics for some sample
     *  **/
    public class Sample {
        // TODO: выборка и методы по ней
    }


    // Counts all ph-types for further statistics and write result to the Statistics object
    public void countAllPhonotypesPerList() {
        mapPhTypesPerList = getAllPhonotypes();

        for (Map.Entry<Object, Integer> entry : mapPhTypesPerList.entrySet()) {
            Object phType = entry.getKey();

        //TODO    System.out.println("");
        //TODO    System.out.println(phType);

                int counterPh = 0;
                int counterW = 0;
                for (Word w : this.globalWordlist.getList()) {
                    int incr = w.countPhonotype(phType);

                    // TODO
                    if (phType.equals(Main.class_to_console)) {
                        System.out.println(phType + " : " + w.getWord() + " " + incr);
                    }

                    counterPh += incr;
                    if (incr > 0) {
                        counterW++;
                    }
            //TODO    System.out.println(w.getWord() + " " + counterPh + " " + counterW);
                }
                entry.setValue(counterPh);
                mapWordsPerList.put(entry.getKey(), counterW);

           /*for (Word w : this.globalWordlist.getList()) {
               entry.setValue(w.countPhonotype(entry.getKey()));
           }*/
        }
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



    // TODO метод был не додуман
    // подумать, как удобно смотреть данные в консоли
    public void printStatsToConsole() {

        System.out.println("ALL STATS FOR WORDLIST \"" + this.globalWordlist.getMeaning() + "\"");
        System.out.println("----- Word ----|-- words/List -|- phTypes/List -|- aver phType/Word");
        System.out.println("---------------|---------------|----------------|-------------------");
        int wordMaxSymb = 15;

        for (Word w : this.globalWordlist.getList()) {
            System.out.print(w.getWord());
            for (int i=0; i<(wordMaxSymb-w.getWord().length()); i++) {
                System.out.println( );
            }
            System.out.println("| ");
        }
    }


    /** GETTERS AND SETTERS **/
    public WordList getGlobalWordlist() {
        return globalWordlist;
    }

    public HashMap<Object, Integer> getMapPhTypesPerList() {
        return mapPhTypesPerList;
    }

    public HashMap<Object, Integer> getMapWordsPerList() {
        return mapWordsPerList;
    }

}
