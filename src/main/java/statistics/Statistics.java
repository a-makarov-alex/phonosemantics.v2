package statistics;

import entities.Word;
import entities.WordList;
import main.Main;

public class Statistics {

    // kinds of calculations
    public enum KindOfStats {
        WORDS_WITH_PHTYPE_PER_LIST,
        PHTYPES_PER_LIST,
        PHTYPES_AVERAGE_PER_WORD
    }

    // Wordlists are added here with semantics as a key
    // TODO: create a Semantics class
    // Remember that Word has Semantics field. No need of HashMap
    private WordList globalWordlist = Main.getInputFile().composeWordList("Big"); //TODO: =getGlobalWordlist() в классе InputFile


    /**
     *  Class for calculating statistics for some sample
     *  **/
    public class Sample {
        // TODO: выборка и методы по ней
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
}
