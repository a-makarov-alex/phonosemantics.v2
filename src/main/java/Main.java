import entities.Word;
import entities.WordList;
import input.InputFile;

public class Main {

    public static void main(String args[]) {

        InputFile inputFile = new InputFile("Input.xlsx");
        //inputFile.getAllWordLists();

        WordList wordlist = new WordList(inputFile.getWordList("big"));
        System.out.println(wordlist.serialize());



        // bug list
        // TODO: fix the problem with /u0027 etc codes instead of symbols
        // TODO: unit tests

    }

}
