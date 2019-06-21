package statistics;

import entities.Word;
import entities.WordList;
import main.Main;

import java.util.*;

public class Statistics {

    // kinds of calculations
    public enum KindOfStats {
        WORDS_WITH_PHTYPE_PER_LIST,
        PHTYPES_PER_LIST,
        PHTYPES_AVERAGE_PER_WORD
    }


    // Медиана
    public static double calculateMean(ArrayList<Double> sample) {
        double d = 0.0;
        int size = sample.size();
        Collections.sort(sample);
        if (size % 2 == 1) {
            d = sample.get(size/2);
        } else {
            d = (sample.get((size/2) - 1) + sample.get(size/2))/2.0;
        }
        return d;
    }

    // Среднее арифметическое
    public static double calculateAverage(ArrayList<Double> sample) {
        double d = 0.0;
        double sum = 0.0;
        for (Double elem : sample) {
            sum += elem;
        }
        d = sum / sample.size();
        return d;
    }


    private static HashMap<String, Integer> unknownPhonemes = new HashMap<>();

    private static int numOfAllPhonemes = 0;

    public static HashMap<String, Integer> getUnknownPhonemes() {
        return unknownPhonemes;
    }

    public static int getNumOfAllPhonemes() {
        return numOfAllPhonemes;
    }

    // Сумма всех неизвестных символов в исследовании
    public static int getNumOfUnknownPhonemes() {
        int count = 0;
        for (Map.Entry<String, Integer> elem : unknownPhonemes.entrySet()) {
            count += elem.getValue();
        }
        return count;
    }

    // Добавляет новый неизвестный символ или инкрементит его количество, если он уже существует в hashmap
    public static void addUnknownSymbol(String symbol) {
        if (!unknownPhonemes.containsKey(symbol)) {
            unknownPhonemes.put(symbol, 1);
        } else {
            unknownPhonemes.put(symbol, unknownPhonemes.get(symbol) + 1);
        }
    }


    // Увеличивает счетчик всех фонем в исследовании.
    // Инкрементится для ФОНЕМЫ (1 или 2 графемы) и для НЕИЗВЕСТНЫХ знаков
    public static void incrementNumOfAllPhonemes() {
        numOfAllPhonemes ++;
    }

    // Wordlists are added here with semantics as a key
    // TODO: create a Semantics class
    // Remember that Word has Semantics field. No need of HashMap
    private WordList globalWordlist = Main.getInputFile().composeWordList("Big"); //TODO: =getGlobalWordlist() в классе InputFile


    /** GETTERS AND SETTERS **/
    public WordList getGlobalWordlist() {
        return globalWordlist;
    }
}
