package main;

import entities.Language;
import entities.WordList;
import entities.phonetics.Phoneme;
import entities.phonetics.Vowel;
import input.InputFile;
import knowledgeBase.SoundsBank;
import output.OutputFile;
import statistics.ShapiroWilk;
import statistics.Statistics;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    // CONFIG --> should be transformed to logs later
    public static final boolean CONSOLE_SHOW_TRASCRIPTION = false;
    public static final Object CONSOLE_SHOW_WORDS_OF_CLASS = null; //SoundsBank.MannerPricise.STOP;
    public static final boolean CONSOLE_UNKNOWN_PHONEMES = false;
    public static final boolean CONSOLE_SHOW_NUM_OF_WORDS_AND_PHONEMES = false;
    public static final boolean CONSOLE_SHOW_FOUND_MEANINGS_IN_INPUT_FILE = false;
    public static final boolean CONSOLE_SHOW_NOT_FOUND_MEANINGS_IN_INPUT_FILE = true;
    public static final boolean CONSOLE_LANG_PHONOLOGY = true;
    public static final boolean CONSOLE_LANG_PHONOTYPES = false;

    public static final boolean DEBUG_STATS = false;


    private static InputFile inputFile;

    public static void main(String args[]) {

        newFullCycle();

        // test for one lang phon inventory
        //Language l = Language.getAllLanguages().get("Ket");
        /*System.out.println("Lang: " + l.getTitle());
        for (Phoneme ph : l.getPhCoverage()) {
            System.out.print(ph.getSymbol() + " ");
        }
        System.out.println();*/

        /*for (Phoneme ph : l.getPhNotDescribed()) {
            if (ph != null) {
                System.out.print(ph.getSymbol() + " ");
            }
        }*/

    }

    public static InputFile getInputFile() {
        return inputFile;
    }

    public static void newFullCycle() {
        inputFile = new InputFile("Input.xlsx");

        SoundsBank cBank = SoundsBank.getInstance();

        ArrayList<WordList> allWordlists = inputFile.getAllWordLists();

        // Запись результатов в файл
        OutputFile normalityFile = new OutputFile("Normality", OutputFile.Type.NORMALITY);
        normalityFile.fillWith(allWordlists);


        /* ПОЗЖЕ РАСКОММЕНТИТЬ
        HashMap<Object, Double[]> map = normalityFile.readAllSamples(Statistics.KindOfStats.WORDS_WITH_PHTYPE_PER_LIST);


        for (int i=0; i < map.get(Vowel.Backness.FRONT).length; i++) {
            System.out.println(map.get(Vowel.Backness.FRONT)[i]);
        }

        double pvalue = ShapiroWilk.ShapiroWilkW(map.get(Vowel.Backness.FRONT));
        System.out.println("p-value:" + pvalue);
        System.out.println("Prob:" + (1 - pvalue));*/

        normalityFile.finalDesign();
    }
}
