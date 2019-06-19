package entities;

import com.google.gson.Gson;
import knowledgeBase.SoundsBank;
import main.Main;
import statistics.Statistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordList {

    private String meaning;
    private Language language;
    private ArrayList<Word> list;
    private HashMap<Object, Integer> mapPhTypesPerList = new HashMap<>();
    private HashMap<Object, Integer> mapWordsPerList = new HashMap<>();
    private HashMap<Object, Integer> mapOfDividers = new HashMap<>();

    public WordList(ArrayList<Word> list) {
        this.meaning = list.get(0).getMeaning().getDefinition();
        this.list = list;
        this.language = list.get(0).getLanguage();

        countAllPhonotypes();
    }

    public String serialize() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public Word getWord(String language) {
        for (Word word : this.list) {
            if (word.getLanguage().getTitle().toLowerCase().equals(language.toLowerCase())) {
                System.out.println("SUCCESS: Word of language " + language +
                        " with meaning " + this.meaning + " is: " + word.getWord());
                return word;
            }
        }
        System.out.println("FAILURE: Word of language " + language +
                " with meaning " + this.meaning + " is not found");
        return null;
    }


    // Counts all ph-types for further statistics and write result to the Statistics object
    public void countAllPhonotypes() {
        mapPhTypesPerList = SoundsBank.getAllPhonotypes();


        // Все фонотипы каждой фонемы из каждого слова в вордлисте суммируем в хашмапе
        for (Map.Entry<Object, Integer> entry : mapPhTypesPerList.entrySet()) {
            Object phType = entry.getKey();

            //TODO    System.out.println("");
            //TODO    System.out.println(phType);

            int counterPh = 0;
            int counterW = 0;
            for (Word w : this.getList()) {
                int incr = w.countPhonotype(phType);

                // TODO
                if (phType.equals(Main.CONSOLE_SHOW_WORDS_OF_CLASS)) {
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
        }
    }


    public HashMap<Object, Double> getStats(Statistics.KindOfStats kindOfStats) {
        HashMap<Object, Double> resultMap = new HashMap<>();
        HashMap<Object, Integer> inputMap = mapPhTypesPerList;
        boolean allPho = false;

        // Рассчитываем делитель для каждого Meaning в зависимости от того, в скольких языках встречен фонотип
        mapOfDividers = this.calculateDividers();

        double divider = 0.0;

        switch (kindOfStats) {
            case WORDS_WITH_PHTYPE_PER_LIST : { inputMap = mapWordsPerList; break; }
            case PHTYPES_PER_LIST : { allPho = true; break; }
            case PHTYPES_AVERAGE_PER_WORD : { break; }
        }

        // TODO short "if"
        if (Main.CONSOLE_SHOW_NUM_OF_WORDS_AND_PHONEMES) {
            System.out.println(kindOfStats);
        }

        for (Map.Entry<Object, Integer> entry : inputMap.entrySet()) {
            //TODO
            if (Main.CONSOLE_SHOW_NUM_OF_WORDS_AND_PHONEMES) {
                System.out.println(entry.getKey() + " --- " + entry.getValue());
            }

            // костыли. возможно, позже будет заменено на что-то более элегантное
            if (!allPho) {
                divider = mapOfDividers.get(entry.getKey());
            } else {
                divider = Statistics.getNumOfAllPhonemes();
            }
            resultMap.put(entry.getKey(), entry.getValue()/divider);

            if (Main.DEBUG_STATS) {
                System.out.println(entry.getKey() + " " + entry.getValue() + " " + divider + " " + allPho + " WL class line 128");
            }
        }

        return resultMap;
    }


    public HashMap<Object, Integer> calculateDividers() {
        HashMap<Object, Integer> mapOfDividers = SoundsBank.getAllPhonotypes();

        for (Map.Entry<Object, Integer> entry : mapOfDividers.entrySet()) {
            int count = 0;

            for (Word w : this.getList()) {
                HashMap<Object, Integer> phTypeCov = w.getLanguage().getPhTypeCoverage();

                if (phTypeCov.get(entry.getKey()) > 0) {
                    count++;
                }
            }

            entry.setValue(count);
        }
        return mapOfDividers;
    }


    // GETTERS AND SETTERS
    public HashMap<Object, Integer> getMapPhTypesPerList() {
        return mapPhTypesPerList;
    }

    public HashMap<Object, Integer> getMapWordsPerList() {
        return mapWordsPerList;
    }

    public ArrayList<Word> getList() {
        return list;
    }

    public void setList(ArrayList<Word> list) {
        this.list = list;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public HashMap<Object, Integer> getMapOfDividers() {
        return mapOfDividers;
    }

    public void setMapOfDividers(HashMap<Object, Integer> mapOfDividers) {
        this.mapOfDividers = mapOfDividers;
    }
}
