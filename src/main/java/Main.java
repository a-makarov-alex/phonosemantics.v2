import entities.Sound;
import entities.Transcription;
import entities.Vowel;
import entities.Word;
import input.InputFile;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String args[]) {

        InputFile inputFile = new InputFile("Input.xlsx");
        inputFile.parseInputFile();


        // bug list
        // TODO: fix the problem with /u0027 etc codes instead of symbols

    }

}
