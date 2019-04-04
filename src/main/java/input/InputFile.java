package input;

import entities.Meaning;
import entities.Transcription;
import entities.Word;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Every file can be specified manually
 */
public class InputFile {

    private String filePath;

    //TODO: add wordlists files to input directory
    public InputFile(String filePath) {
        this.filePath = filePath;

    }


    public void parseInputFile() {

        // открытие файла для чтения
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("D:\\JavaProjects2019\\word\\src\\main\\java\\input\\" + filePath);
            Workbook wb = WorkbookFactory.create(inputStream);
            Sheet sheet;
            Row row;
            Cell cell;
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

                    System.out.println(word.serialize());
                }
            }
            System.out.println();








        } catch (IOException e) {
            System.out.println("exception");    //TODO: logs
        }


        // for every row in file
        // take a word and create new Word()


    }
}
