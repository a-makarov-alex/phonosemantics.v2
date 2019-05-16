package statistics;

import entities.Word;
import entities.WordList;
import entities.phonetics.Consonant;
import entities.phonetics.Vowel;
import input.InputFile;
import main.Main;
import output.Header;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Statistics {

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
        HashMap<Object, Integer> map = getAllPhonotypes();

        for (Map.Entry<Object, Integer> entry : map.entrySet()) {
            Class c = entry.getKey().getClass();

           /*if (c == Vowel.Height.class) {
               System.out.println(c);
                Vowel.Height phType = (Vowel.Height) entry.getKey();
                for (Word w : this.globalWordlist.getList()) {
                    int counter = w.countPhonotype(phType);
                    System.out.println(w.getWord() + " " + counter);
                    // entry.setValue(w.countPhonotype(phType));
                }
            }*/

           // TODO: работает. расширить на все phTypes
            if (c == Consonant.PlacePrecise.class) {
                Consonant.PlacePrecise phType = (Consonant.PlacePrecise) entry.getKey();
                System.out.println(phType);
                for (Word w : this.globalWordlist.getList()) {
                    int counter = w.countPhonotype(phType);
                    System.out.println(w.getWord() + " " + counter);
                    // entry.setValue(w.countPhonotype(phType));
                }
            }

           for (Word w : this.globalWordlist.getList()) {
               entry.setValue(w.countPhonotype(entry.getKey()));
           }
        }

        return map;
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
