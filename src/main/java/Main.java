import entities.Word;
import entities.WordList;
import input.InputFile;

public class Main {

    public static void main(String args[]) {

        InputFile inputFile = new InputFile("Input.xlsx");
        //inputFile.getAllWordLists();

        // test: get wordlist for some meaning
        WordList wordlist = new WordList(inputFile.getWordList("big"));
        System.out.println(wordlist.serialize());

        Word word = wordlist.getWord("shoshone");
        System.out.println("i phonemes in word: " + word.getNumOfPhonemes('i'));


        // bug list
        // TODO: fix the problem with /u0027 etc codes instead of symbols
        // TODO: unit tests
        // TODO: do not read empty cells in inputFile and do not count them
        // TODO: write java 1.8 in pom.xml

    }

}
