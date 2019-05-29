package input;

import entities.Meaning;
import entities.Word;
import entities.WordList;
import main.Main;
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
    public ArrayList<WordList> getAllWordLists() {

        // open file for reading
        InputStream inputStream = null;
        ArrayList<WordList> allWordlists = new ArrayList<>();

        try {
            inputStream = new FileInputStream(INPUT_DIRECTORY + filePath);
            Workbook wb = WorkbookFactory.create(inputStream);
            Sheet sheet = wb.getSheetAt(0);

            int rowNum = 0;
            int colNum = 1;

            // read all the headers with words' Meanings
            while (sheet.getRow(rowNum).getCell(colNum) != null) {
                if (Main.CONSOLE_SHOW_FOUND_MEANINGS_IN_INPUT_FILE) {
                    System.out.println(sheet.getRow(rowNum).getCell(colNum).getStringCellValue());
                }
                allWordlists.add(this.getWordList(sheet.getRow(rowNum).getCell(colNum).getStringCellValue()));
                colNum++;
            }

            System.out.println();
            inputStream.close();
            return allWordlists;

        } catch (IOException e) {
            System.out.println("exception");    //TODO: logs
            return null;
        }
    }


    /**
     * Reads a list of words from inputFile by meaning
     */
    public WordList getWordList(String meaning) {

        // open file for reading
        InputStream inputStream = null;
        ArrayList<Word> list = new ArrayList<Word>();

        try {
            inputStream = new FileInputStream(INPUT_DIRECTORY + filePath);
            Workbook wb = WorkbookFactory.create(inputStream);
            Sheet sheet;
            sheet = wb.getSheetAt(0);
            int count = 0;

            // Find meaning in the headers
            for (int col = 1; col <= sheet.getLastRowNum(); col++) {

                // stop on the first empty column
                if (sheet.getRow(0).getCell(col) == null) {
                    if (Main.CONSOLE_SHOW_NOT_FOUND_MEANINGS_IN_INPUT_FILE) {
                        System.out.println("ERROR: There is no words for " + meaning + " in the input file");
                    }
                    break;
                } else {

                    // Meaning is found successfully
                    if (sheet.getRow(0).getCell(col).getStringCellValue().toLowerCase().equals(meaning.toLowerCase())) {
                        if (Main.CONSOLE_SHOW_FOUND_MEANINGS_IN_INPUT_FILE) {
                            System.out.println("SUCCESS: Words for " + meaning +
                                    " found in the " + col + " column of the input file");
                        }

                        for (int i = 1; i <= sheet.getLastRowNum() + 1; i++) {

                            // Stop on the first empty row
                            if (sheet.getRow(i) == null) {
                                if (Main.CONSOLE_SHOW_FOUND_MEANINGS_IN_INPUT_FILE) {
                                    System.out.println("Number of words for " + meaning + " : " + (count));
                                }
                                break;
                            }

                            Cell cell = sheet.getRow(i).getCell(col);
                            if (cell != null && cell.getCellType() != CellType.BLANK) {
                                Word word = new Word(sheet.getRow(i).getCell(col).getStringCellValue());
                                word.setMeaning(new Meaning(sheet.getRow(0).getCell(col).getStringCellValue()));
                                word.setLanguage(sheet.getRow(i).getCell(0).getStringCellValue());

                                list.add(word);
                                count++;
                            } else {
                                System.out.println("No value for word \"" + sheet.getRow(0).getCell(col).getStringCellValue() +
                                        "\" of language " + sheet.getRow(i).getCell(0).getStringCellValue());
                            }
                        }
                        // Break after wordlist is created
                        break;
                    }
                }
            }
            if (Main.CONSOLE_SHOW_FOUND_MEANINGS_IN_INPUT_FILE) {
                System.out.println();
            }
            inputStream.close();

            return new WordList(list);

        } catch (IOException e) {
            System.out.println("exception");    // TODO: logs
            return null;
        }
    }

}
