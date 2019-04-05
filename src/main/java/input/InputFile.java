package input;

import entities.Meaning;
import entities.Transcription;
import entities.Word;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Every file can be specified manually
 */
public class InputFile {

    private String filePath;
    private static final String INPUT_DIRECTORY = "D:\\JavaProjects2019\\word\\src\\main\\java\\input\\";

    //TODO: add wordlists files to input directory
    public InputFile(String filePath) {
        this.filePath = filePath;

    }

    /**
     * Reads all the words from inputFile and write them to one list of Word entities
     */
    public ArrayList<Word> getAllWordLists() {

        // open file for reading
        InputStream inputStream = null;
        ArrayList<Word> allWordsList = new ArrayList<Word>();

        try {
            inputStream = new FileInputStream(INPUT_DIRECTORY + filePath);
            Workbook wb = WorkbookFactory.create(inputStream);
            Sheet sheet;
            sheet = wb.getSheetAt(0);

            System.out.println("test " + sheet.getRow(32));

            // create Word entities for every word in file
            System.out.println("--- FILE INFO ---");
            for (int col = 1; col <= sheet.getLastRowNum(); col++) {
                // stop on the first empty column
                if (sheet.getRow(0).getCell(col) == null) {
                    System.out.println("Number of columns (meanings): " + (col - 1));
                    break;
                }
                for (int i = 1; i <= sheet.getLastRowNum() + 1; i++) {
                    // stop on the first empty row
                    if (sheet.getRow(i) == null) {
                        System.out.println("Number of rows (languages) " + (i - 1));
                        break;
                    }
                    Word word = new Word();
                    word.setMeaning(new Meaning(sheet.getRow(0).getCell(col).getStringCellValue()));
                    word.setLanguage(sheet.getRow(i).getCell(0).getStringCellValue());
                    word.setTranscription(new Transcription(sheet.getRow(i).getCell(col).getStringCellValue()));

                    allWordsList.add(word);
                }
            }
            System.out.println();
            inputStream.close();
            return allWordsList;

        } catch (IOException e) {
            System.out.println("exception");    //TODO: logs
            return null;
        }
    }


    /**
     * Reads a list of words from inputFile by meaning
     */
    public ArrayList<Word> getWordList(String meaning) {

        // open file for reading
        InputStream inputStream = null;
        ArrayList<Word> wordlist = new ArrayList<Word>();

        try {
            inputStream = new FileInputStream(INPUT_DIRECTORY + filePath);
            Workbook wb = WorkbookFactory.create(inputStream);
            Sheet sheet;
            sheet = wb.getSheetAt(0);

            // Find meaning in the headers
            for (int col = 1; col <= sheet.getLastRowNum(); col++) {

                // stop on the first empty column
                if (sheet.getRow(0).getCell(col) == null) {
                    System.out.println("ERROR: There is no words for " + meaning + " in the input file");
                    break;
                } else {

                    // Meaning is found successfully
                    if (sheet.getRow(0).getCell(col).getStringCellValue().toLowerCase().equals(meaning.toLowerCase())) {
                        System.out.println("SUCCESS: Words for " + meaning +
                                " found in the " + col + " column of the input file");

                        for (int i = 1; i <= sheet.getLastRowNum() + 1; i++) {

                            // Stop on the first empty row
                            if (sheet.getRow(i) == null) {
                                System.out.println("Number of words for " + meaning + " : " + (i - 1));
                                break;
                            }
                            Word word = new Word();
                            word.setMeaning(new Meaning(sheet.getRow(0).getCell(col).getStringCellValue()));
                            word.setLanguage(sheet.getRow(i).getCell(0).getStringCellValue());
                            word.setTranscription(new Transcription(sheet.getRow(i).getCell(col).getStringCellValue()));

                            wordlist.add(word);
                        }
                        // Break after wordlist is created
                        break;
                    }
                }
            }

            System.out.println();
            inputStream.close();
            return wordlist;

        } catch (IOException e) {
            System.out.println("exception");    // TODO: logs
            return null;
        }
    }

}
