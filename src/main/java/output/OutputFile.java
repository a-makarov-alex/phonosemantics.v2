package output;

import entities.WordList;
import entities.phonetics.Consonant;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import statistics.Statistics;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OutputFile {

    private String title;
    private String filePath;
    private Type type;
    private static final String OUTPUT_DIRECTORY = "D:\\JavaProjects2019\\word\\src\\main\\java\\output\\";

    private Workbook wb;
    private static CellStyle headerCellStyle;

    public enum Type {
        GENERAL, NORMALITY
    }

    private static final String SHEET_VOW = "Vowels";
    private static final String SHEET_CONS_MANNER = "Cons manner";
    private static final String SHEET_CONS_PLACE = "Cons place";

    public OutputFile(String title, Type type) {
        this.title = title;
        this.filePath = OUTPUT_DIRECTORY + title + ".xlsx";
        this.wb = new XSSFWorkbook();
        this.createOutputFile(type);
    }

    public void createOutputFile(Type type) {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.filePath);

            // Create an Excel file draft
            ArrayList<Sheet> sheets = new ArrayList<>();
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

            // Create sheets with headers
            switch (type) {
                case GENERAL: {
                    // sheets
                    sheets.add(wb.createSheet(SHEET_VOW));
                    sheets.add(wb.createSheet(SHEET_CONS_MANNER));
                    sheets.add(wb.createSheet(SHEET_CONS_PLACE));

                    //headers
                    for (Sheet sh : sheets) {
                        Header.addCommonHeader(sh);
                    }
                    Header.addVowelsHeader(sheets.get(0));
                    Header.addMannerHeader(sheets.get(1));
                    Header.addPlaceHeader(sheets.get(2));

                    break;
                }

                case NORMALITY: {
                    // TODO

                    break;
                }
            }

            wb.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            System.out.println("IOException caught");
        }
    }

    public void fillGeneralOutputFile(WordList wordList) {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.filePath);

            // percentage or absolute
            CellStyle cellStyle = wb.createCellStyle();
            HashMap<Object, Double> mapResult = new HashMap<>();
            Sheet sh;
            int row;
            int column = 2;

            // WRITE VOWELS
            sh = this.wb.getSheet(SHEET_VOW);

            for (int i = 3; i <= 5; i++) {
                row = i;
                switch (row) {
                    // WORDS_WITH_PHTYPE_PER_LIST
                    case 3 : {
                        mapResult = wordList.getStats(Statistics.KindOfStats.WORDS_WITH_PHTYPE_PER_LIST);
                        cellStyle.setDataFormat(wb.createDataFormat().getFormat("0.0%"));
                        break;
                    }

                    // PHTYPES_PER_LIST
                    case 4 : {
                        mapResult = wordList.getStats(Statistics.KindOfStats.PHTYPES_PER_LIST);
                        cellStyle.setDataFormat(wb.createDataFormat().getFormat("0.0%"));
                        break;
                    }

                    // PHTYPES_AVERAGE_PER_WORD
                    case 5 : {
                        mapResult = wordList.getStats(Statistics.KindOfStats.PHTYPES_AVERAGE_PER_WORD);
                        cellStyle.setDataFormat(wb.createDataFormat().getFormat("0.0"));
                        break;
                    }
                }

                // WRITE ROW BY ROW
                for (Map.Entry<Object, Header> entry : Header.vowSh.entrySet()) {
                    column = entry.getValue().getColumn();

                    Cell c = sh.getRow(row).createCell(column);
                    //System.out.println(entry.getKey());
                    //System.out.println(mapResult.get(entry.getKey()));
                    c.setCellValue(mapResult.get(entry.getKey()));
                    c.setCellStyle(cellStyle);
                }
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
