package output;

import entities.WordList;
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
    private ArrayList<Sheet> sheets;
    private static final String OUTPUT_DIRECTORY = "D:\\JavaProjects2019\\word\\src\\main\\java\\output\\";
    private int recordsCounter;

    private Workbook wb;
    private static CellStyle headerCellStyle;

    public enum Type {
        GENERAL, NORMALITY
    }

    // TODO to enum
    private static final String SHEET_VOW = "Vowels";
    private static final String SHEET_CONS_MANNER = "Cons manner";
    private static final String SHEET_CONS_PLACE = "Cons place";

    public OutputFile(String title, Type type) {
        this.title = title;
        this.type = type;
        this.recordsCounter = 0;
        this.filePath = OUTPUT_DIRECTORY + title + ".xlsx";
        this.wb = new XSSFWorkbook();
        this.createOutputFile();
    }

    private void createOutputFile() {
        Type type = this.type;

        try {
            FileOutputStream fileOut = new FileOutputStream(this.filePath);

            // Create an Excel file draft
            sheets = new ArrayList<>();
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
                    // sheets
                    sheets.add(wb.createSheet(Statistics.KindOfStats.WORDS_WITH_PHTYPE_PER_LIST.toString()));
                    sheets.add(wb.createSheet(Statistics.KindOfStats.PHTYPES_PER_LIST.toString()));
                    sheets.add(wb.createSheet(Statistics.KindOfStats.PHTYPES_AVERAGE_PER_WORD.toString()));

                    //headers
                    Header.addNormalityHeader(sheets.get(0), Statistics.KindOfStats.WORDS_WITH_PHTYPE_PER_LIST);
                    Header.addNormalityHeader(sheets.get(1), Statistics.KindOfStats.PHTYPES_PER_LIST);
                    Header.addNormalityHeader(sheets.get(2), Statistics.KindOfStats.PHTYPES_AVERAGE_PER_WORD);

                    for (Sheet sh : sheets) {
                        Header.addVowelsHeader(sh);
                        Header.addMannerHeader(sh, true);
                        //Header.addPlaceHeader(sheets.get(2));
                    }

                    break;
                }
            }

            wb.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            System.out.println("IOException caught");
        }
    }

    // Writes the worlist parsing and statistics counting result into the output file
    public void fillWith(WordList wordList) {
        switch (type) {
            case GENERAL: {
                writeToGeneralFile(wordList);
            }
            case NORMALITY: {
                writeToNormalityFile(wordList);
            }
        }
    }

    public void writeToGeneralFile(WordList wordList) {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.filePath);

            // percentage or absolute
            CellStyle cellStyle = wb.createCellStyle();
            HashMap<Object, Double> mapResult = new HashMap<>();
            Sheet sh;
            int row = 0;
            int column = 2;

            // WRITE VOWELS
            sh = this.wb.getSheet(SHEET_VOW);

            sh.getRow(3).getCell(0).setCellValue(wordList.getMeaning());
            sh.getRow(3).getCell(1).setCellValue(wordList.getList().size());

            for (int i = 3; i <= 5; i++) {
                row = i;
                String styleFormat = "";
                switch (row) {
                    // WORDS_WITH_PHTYPE_PER_LIST
                    case 3 : {
                        mapResult = wordList.getStats(Statistics.KindOfStats.WORDS_WITH_PHTYPE_PER_LIST);
                        styleFormat = "0.0%";
                        break;
                    }
                    // PHTYPES_PER_LIST
                    case 4 : {
                        mapResult = wordList.getStats(Statistics.KindOfStats.PHTYPES_PER_LIST);
                        styleFormat = "0.0%";
                        break;
                    }

                    // PHTYPES_AVERAGE_PER_WORD
                    case 5 : {
                        mapResult = wordList.getStats(Statistics.KindOfStats.PHTYPES_AVERAGE_PER_WORD);
                        styleFormat = "0.0";
                        break;
                    }
                }

                // WRITE ROW BY ROW
                for (Map.Entry<Object, Header> entry : Header.vowSh.entrySet()) {
                    column = entry.getValue().getColumn();

                    Cell c = sh.getRow(row).createCell(column);
                    c.setCellValue(mapResult.get(entry.getKey()));
                    c.setCellStyle(createCellStyleWithDataFormat(styleFormat));
                }
            }

            wb.write(fileOut);
            fileOut.close();

        } catch (IOException e) {
            System.out.println("IOException caught");
        }
    }

    public void writeToNormalityFile(WordList wordList) {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.filePath);

            // SHEET WORDS_WITH_PHTYPE_PER_LIST
            writeOneKindOfStats(
                    sheets.get(0),
                    "0.0%",
                    wordList.getStats(Statistics.KindOfStats.WORDS_WITH_PHTYPE_PER_LIST));

            // SHEET PHTYPES_PER_LIST
            writeOneKindOfStats(
                    sheets.get(1),
                    "0.0%",
                    wordList.getStats(Statistics.KindOfStats.PHTYPES_PER_LIST));

            // SHEET PHTYPES_AVERAGE_PER_WORD
            writeOneKindOfStats(
                    sheets.get(2),
                    "0.0",
                    wordList.getStats(Statistics.KindOfStats.PHTYPES_AVERAGE_PER_WORD));

            // count a new record
            recordsCounter++;

            wb.write(fileOut);
            fileOut.close();

        } catch (IOException e) {
            System.out.println("IOException caught");
        }
    }

    private void writeOneKindOfStats(Sheet sh, String dataFormat, HashMap<Object, Double> mapResult) {
        int row = 3 + recordsCounter;
        int column = 2;

        for (Map.Entry<Object, Header> entry : Header.vowSh.entrySet()) {
            column = entry.getValue().getColumn();

            Cell c = sh.getRow(row).createCell(column);
            c.setCellValue(mapResult.get(entry.getKey()));
            c.setCellStyle(createCellStyleWithDataFormat(dataFormat));
        }
    }


    public CellStyle createCellStyleWithDataFormat(String format) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(wb.createDataFormat().getFormat(format));
        return cellStyle;
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
