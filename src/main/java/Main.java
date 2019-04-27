import entities.Word;
import entities.WordList;
import entities.phonetics.Consonant;
import input.InputFile;
import knowledgeBase.SoundsBank;

public class Main {

    public static void main(String args[]) {

        InputFile inputFile = new InputFile("Input.xlsx");
        //inputFile.getAllWordLists();

        // test: get wordlist for some meaning
        WordList wordlist = new WordList(inputFile.getWordList("big"));
        //System.out.println(wordlist.serialize());

        Word word = wordlist.getWord("shoshone");
        //System.out.println("i phonemes in word: " + word.getNumOfPhonemes("i"));

        //word.getTranscriptionFromWord();
        SoundsBank cBank = SoundsBank.getInstance();
        System.out.println("WORD: " + word.getWord());
        System.out.println("STOPS number: " + word.countPhonotype(Consonant.MannerPricise.STOP));
        System.out.println("BILABIAL number: " + word.countPhonotype(Consonant.PlacePrecise.BILABIAL));

        //System.out.println(word.getTranscription().get(0).getSymbol());
        //System.out.println(word.getTranscriptionAsString());


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

    }

}
