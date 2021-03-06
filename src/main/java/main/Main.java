package main;

import entities.WordList;
import input.InputFile;
import knowledgeBase.SoundsBank;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import output.OutputFile;
import statistics.Statistics;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    //static final Logger rootLogger = org.apache.logging.log4j.LogManager.getRootLogger();
    static final Logger userLogger = LogManager.getLogger(Main.class);

    // CONFIG --> should be transformed to logs later
    public static final boolean CONSOLE_SHOW_TRASCRIPTION = false;
    public static final Object CONSOLE_SHOW_WORDS_OF_CLASS = null; //SoundsBank.MannerPricise.STOP;
    public static final boolean CONSOLE_SHOW_ALL_UNKNOWN_PHONEMES = false;
    public static final boolean CONSOLE_SHOW_NUM_OF_WORDS_AND_PHONEMES = false;
    public static final boolean CONSOLE_SHOW_FOUND_MEANINGS_IN_INPUT_FILE = false;
    public static final boolean CONSOLE_SHOW_NOT_FOUND_MEANINGS_IN_INPUT_FILE = true;
    public static final boolean CONSOLE_LANG_PHONOLOGY = false;
    public static final boolean CONSOLE_LANG_PHONOTYPES = false;
    public static final boolean CONSOLE_EXTRA_SYMBOLS = false;

    public static final boolean DEBUG_STATS = false;


    private static InputFile inputFile;

    public static void main(String args[]) {
        userLogger.info("start point");
        newFullCycle();

        showAllUnknownPhonemes();

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

        // Создаем объекты всех фонем
        SoundsBank cBank = SoundsBank.getInstance();

        // Считываем из входного файла названия языков,
        // Метод не обязательный. Если его убрать, все будет работать.
        inputFile.prepareLanguagesMap();

        // Получаем все списки слов из входного файла
        // В полях wordlist уже будут записаны базовые статистические результаты. См. документацию
        ArrayList<WordList> allWordlists = inputFile.getAllWordLists();

        // данные для 1 вордлиста. используются в качестве дебага
        WordList wl = allWordlists.get(0);
        for (Map.Entry<Object, WordList.PhTypeStats> stats : wl.getPhTypeStatsMap().entrySet()) {
            userLogger.error(stats.getKey() + " : " + stats.getValue().getPercentOfWordsWithPhType() + " : " + stats.getValue().getAveragePhTypePerWord());
        }
        //*************************

        // Запись результатов в файл
        OutputFile normalityFile = new OutputFile("Normality", OutputFile.Type.NORMALITY);
        normalityFile.fillWith(allWordlists);


        /* ПОЗЖЕ РАСКОММЕНТИТЬ
        HashMap<Object, Double[]> map = normalityFile.readAllSamplesAsArray(Statistics.KindOfStats.WORDS_WITH_PHTYPE_PER_LIST);


        for (int i=0; i < map.get(Vowel.Backness.FRONT).length; i++) {
            System.out.println(map.get(Vowel.Backness.FRONT)[i]);
        }

        double pvalue = ShapiroWilk.ShapiroWilkW(map.get(Vowel.Backness.FRONT));
        System.out.println("p-value:" + pvalue);
        System.out.println("Prob:" + (1 - pvalue));*/

        normalityFile.finalDesign();
    }

    // Выполнять только после FullCycle()
    private static void showAllUnknownPhonemes() {
        if (CONSOLE_SHOW_ALL_UNKNOWN_PHONEMES) {
            System.out.println();
            double div_1 = Statistics.getNumOfUnknownPhonemes() * 1.0;
            int div_2 = Statistics.getNumOfAllPhonemes();
            DecimalFormat df = new DecimalFormat("#.#");

            System.out.println("ALL THE UNKNOWN PHONEMES = " + df.format((div_1/div_2) * 100) + "% (of all phonemes)");
            for (Map.Entry<String, Integer> unknownSymbol : Statistics.getUnknownPhonemes().entrySet()) {
                System.out.println(unknownSymbol.getKey() + " : " + unknownSymbol.getValue());
            }
        }
    }
}
