package main;

import entities.WordList;
import entities.phonetics.Vowel;
import input.InputFile;
import knowledgeBase.SoundsBank;
import output.OutputFile;
import statistics.Statistics;

import java.util.ArrayList;

public class Main {

    // CONFIG --> should be transformed to logs later
    public static final boolean CONSOLE_SHOW_TRASCRIPTION = false;
    public static final Object CONSOLE_SHOW_WORDS_OF_CLASS = null; //Vowel.Height.CLOSE;
    public static final boolean CONSOLE_UNKNOWN_PHONEMES = false;
    public static final boolean CONSOLE_SHOW_NUM_OF_WORDS_AND_PHONEMES = false;
    public static final boolean CONSOLE_SHOW_FOUND_MEANINGS_IN_INPUT_FILE = false;
    public static final boolean CONSOLE_SHOW_NOT_FOUND_MEANINGS_IN_INPUT_FILE = true;


    private static InputFile inputFile;

    public static void main(String args[]) {

        newFullCycle();

        /*SoundsBank cBank = SoundsBank.getInstance();

        OutputFile outputFile = new OutputFile("OutputFile", OutputFile.Type.GENERAL);
        OutputFile normalityFile = new OutputFile("Normality", OutputFile.Type.NORMALITY);
        WordList exampleWordlist = getInputFile().getWordList("Big");
        outputFile.fillWith(exampleWordlist);
        normalityFile.fillWith(exampleWordlist);*/

        //inputFile.getAllWordLists();






        // bug list
        // TODO: standartization of phonemes transcription: sh => ʃ
        // TODO: fix the problem with /u0027 etc codes instead of symbols
        // TODO: unit tests
        // TODO: do not read empty cells in inputFile and do not count them
        // TODO: reference to readWordList from readAllWordLists in InputFile class
        // TODO for some time a new field can be added двусмысленный знак или нет,
        // смысл в том, что многие знаки могут иметь несколько значений
        // такой шаг позволит в будущем заняться более подробным изучением отдельных знаков
        // а также уже сейчас считать процент однозначно верных знаков


        // GLOBAL STRATEGY
        // TODO make some function to calculate language phoneme bank from the swadesh list
        // so then we could count the num of languages where the sound presents
        // TODO all the affricates
        // TODO all the diacritics
        // TODO recalculate word's length after affricates and diacritics

        // Small correction and additions
        // TODO: add colors to OutputFile

    }

    public static InputFile getInputFile() {
        return inputFile;
    }

    public static void newFullCycle() {
        inputFile = new InputFile("Input.xlsx");

        SoundsBank cBank = SoundsBank.getInstance();

        ArrayList<WordList> allWordlists = inputFile.getAllWordLists();
        OutputFile normalityFile = new OutputFile("Normality", OutputFile.Type.NORMALITY);

        for(WordList wordList : allWordlists) {
            normalityFile.fillWith(wordList);
        }

        normalityFile.finalDesign();
    }
}
