package output;

import entities.phonetics.Consonant;
import entities.phonetics.Vowel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import statistics.Statistics;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OutputFile {

    private String filePath;
    private static final String INPUT_DIRECTORY = "D:\\JavaProjects2019\\word\\src\\main\\java\\output\\";

    private Workbook wb;
    private static CellStyle headerCellStyle;

    private static final String SHEET_VOW = "Vowels";
    private static final String SHEET_CONS_MANNER = "Cons manner";
    private static final String SHEET_CONS_PLACE = "Cons place";

    public OutputFile() {
        this.filePath = INPUT_DIRECTORY + "OutputFile.xlsx";
        this.wb = new XSSFWorkbook();
        createOutputFile();


    }

    public void createOutputFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.filePath);

            // Create an Excel file draft
            ArrayList<Sheet> sheets = new ArrayList<>();
            sheets.add(wb.createSheet(SHEET_VOW));
            sheets.add(wb.createSheet(SHEET_CONS_MANNER));
            sheets.add(wb.createSheet(SHEET_CONS_PLACE));

            Row row;
            Cell cell;

            // Specify a style for headers
            headerCellStyle = wb.createCellStyle();
            headerCellStyle.setBorderRight(BorderStyle.THIN);
            headerCellStyle.setBorderBottom(BorderStyle.THIN);
            headerCellStyle.setBorderLeft(BorderStyle.THIN);
            headerCellStyle.setBorderTop(BorderStyle.THIN);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // Add headers for every sheet
            for (Sheet sh : sheets) {
                Header.addCommonHeader(sh);
            }
            Header.addVowelsHeader(sheets.get(0));
            Header.addMannerHeader(sheets.get(1));
            Header.addPlaceHeader(sheets.get(2));

            wb.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            System.out.println("IOException caught");
        }
    }

    public void fillWithRealData(HashMap<Object, Integer> map, String kindOfCalculation) {

        try {
            FileOutputStream fileOut = new FileOutputStream(this.filePath);

            int column = 2;
            int row = 0;

            switch (kindOfCalculation) {
                case Statistics.WORDS_WITH_PHTYPE_PER_LIST : { row = 3; break; }
                case Statistics.PHTYPES_PER_LIST : { row = 4; break; }
                case Statistics.PHTYPES_AVERAGE_PER_WORD : { row = 5; break; }
            }

            Sheet sh = this.wb.getSheet(SHEET_CONS_MANNER);

            sh.getRow(row).createCell(4).setCellValue(map.get(Consonant.MannerPricise.STOP));
            //TODO: замапить column = class

            sh = this.wb.getSheet(SHEET_VOW);
            for (Map.Entry<Object, Header> entry : Header.vowSh.entrySet()) {
                column = entry.getValue().getColumn();
                sh.getRow(row).createCell(column).setCellValue(map.get(entry.getKey()));
            }

            wb.write(fileOut);
            fileOut.close();

        } catch (IOException e) {
            System.out.println("IOException caught");
        }

    }



    // GETTERS AND SETTERS
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public Workbook getWb() {
        return wb;
    }
    public void setWb(Workbook wb) {
        this.wb = wb;
    }
    public static CellStyle getHeaderCellStyle() {
        return headerCellStyle;
    }
    public static void setHeaderCellStyle(CellStyle headerCellStyle) {
        OutputFile.headerCellStyle = headerCellStyle;
    }
}
