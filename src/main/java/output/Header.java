package output;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.ArrayList;

public class Header {

    private int row;
    private int column;
    private String text;

    public Header(int row, int column, String text) {
        this.row = row;
        this.column = column;
        this.text = text;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * Header that is common for all excel sheets
     * **/
    public static void addCommonHeader(Sheet sheet) {
        // inicialization and style
        for (int i=0; i <= 5; i++ ) {
            sheet.createRow(i);
            for (int j=0; j <= 2; j++) {
                sheet.getRow(i).createCell(j);
                sheet.getRow(i).getCell(j).setCellStyle(OutputFile.getHeaderCellStyle());
            }
        }

        // merging cells
        sheet.addMergedRegion(new CellRangeAddress(0,2,0, 0));
        sheet.addMergedRegion(new CellRangeAddress(0,2,1, 1));
        sheet.addMergedRegion(new CellRangeAddress(0,2,2, 2));
        sheet.addMergedRegion(new CellRangeAddress(3,5,0, 0));
        sheet.addMergedRegion(new CellRangeAddress(3,5,1, 1));

        ArrayList<Header> list = new ArrayList<>();
        list.add(new Header(0,0, "Semantics"));
        list.add(new Header(0, 1, "Wordlist length"));
        list.add(new Header(3, 2, "% of words with ph-type"));
        list.add(new Header(4, 2, "% of ph with ph-type"));
        list.add(new Header(5, 2, "aver ph per word"));

        // вписываем значения хедеров
        for (Header h : list) {
            Cell cell = sheet.getRow(h.row).getCell(h.column);
            cell.setCellValue(h.text);
        }

        // устанавливаем ширину ячеек
        sheet.setColumnWidth(0, 3700);
        sheet.setColumnWidth(1, 3700);
        sheet.setColumnWidth(2, 5800);

    }


    /**
     *  Headers for Vowel excel sheet
     *  **/
    public static void addVowelsHeader(Sheet sheet) {
        // inicialization and style
        for (int i=0; i <= 2; i++ ) {
            sheet.getRow(i);
            for (int j=3; j <= 10; j++) {
                sheet.getRow(i).createCell(j);
                sheet.getRow(i).getCell(j).setCellStyle(OutputFile.getHeaderCellStyle());
            }
        }

        // merging cells
        sheet.addMergedRegion(new CellRangeAddress(0,0,3, 10));
        sheet.addMergedRegion(new CellRangeAddress(1,1,3, 7));
        sheet.addMergedRegion(new CellRangeAddress(1,1,8, 10));

        ArrayList<Header> list = new ArrayList<>();
        list.add(new Header(0,3, "VOWELS"));
        list.add(new Header(1, 3, "Height"));
        list.add(new Header(1, 8, "Backness"));
        list.add(new Header(2, 3, "Open"));
        list.add(new Header(2, 4, "Op-mid"));
        list.add(new Header(2, 5, "Mid"));
        list.add(new Header(2, 6, "Cl-mid"));
        list.add(new Header(2, 7, "Close"));
        list.add(new Header(2, 8, "Front"));
        list.add(new Header(2, 9, "Cent"));
        list.add(new Header(2, 10, "Back"));


        // вписываем значения хедеров
        for (Header h : list) {
            Cell cell = sheet.getRow(h.row).getCell(h.column);
            cell.setCellValue(h.text);
        }
    }


    /**
     *  Headers for Manner excel sheet
     *  **/
    public static void addMannerHeader(Sheet sheet) {
        // inicialization and style
        for (int i=0; i <= 2; i++ ) {
            sheet.getRow(i);
            for (int j=3; j <= 14; j++) {
                sheet.getRow(i).createCell(j);
                sheet.getRow(i).getCell(j).setCellStyle(OutputFile.getHeaderCellStyle());
            }
        }

        // merging cells
        sheet.addMergedRegion(new CellRangeAddress(0,0,3, 14));
        sheet.addMergedRegion(new CellRangeAddress(1,1,3, 8));
        sheet.addMergedRegion(new CellRangeAddress(1,1,9, 13));

        ArrayList<Header> list = new ArrayList<>();
        list.add(new Header(0,3, "MANNER"));
        list.add(new Header(1, 3, "Obstruent"));
        list.add(new Header(1, 9, "Sonorant"));
        list.add(new Header(1, 14, "Liquid"));
        list.add(new Header(2, 3, "All"));
        list.add(new Header(2, 4, "Stops"));
        list.add(new Header(2, 5, "Affric"));
        list.add(new Header(2, 6, "Fricat all"));
        list.add(new Header(2, 7, "Sibil"));
        list.add(new Header(2, 8, "Non-sibil"));
        list.add(new Header(2, 9, "All"));
        list.add(new Header(2, 10, "Nasal"));
        list.add(new Header(2, 11, "Approx"));
        list.add(new Header(2, 12, "Trill"));
        list.add(new Header(2, 13, "Flap"));
        list.add(new Header(2, 14, "Lateral"));


        // вписываем значения хедеров
        for (Header h : list) {
            Cell cell = sheet.getRow(h.row).getCell(h.column);
            cell.setCellValue(h.text);
        }
    }


    /**
     *  Headers for Place excel sheet
     *  **/
    public static void addPlaceHeader(Sheet sheet) {
        // inicialization and style
        for (int i=0; i <= 2; i++ ) {
            sheet.getRow(i);
            for (int j=3; j <= 17; j++) {
                sheet.getRow(i).createCell(j);
                sheet.getRow(i).getCell(j).setCellStyle(OutputFile.getHeaderCellStyle());
            }
        }

        // merging cells
        sheet.addMergedRegion(new CellRangeAddress(0,0,3, 17));
        sheet.addMergedRegion(new CellRangeAddress(1,1,3, 5));
        sheet.addMergedRegion(new CellRangeAddress(1,1,6, 10));
        sheet.addMergedRegion(new CellRangeAddress(1,1,11, 14));
        sheet.addMergedRegion(new CellRangeAddress(1,1,15, 17));

        ArrayList<Header> list = new ArrayList<>();
        list.add(new Header(0,3, "PLACE"));
        list.add(new Header(1, 3, "Labial"));
        list.add(new Header(1, 6, "Coronal"));
        list.add(new Header(1, 11, "Dorsal"));
        list.add(new Header(1, 15, "Laryngeal"));
        list.add(new Header(2, 3, "All"));
        list.add(new Header(2, 4, "Bilab"));
        list.add(new Header(2, 5, "Lab-dent"));
        list.add(new Header(2, 6, "All"));
        list.add(new Header(2, 7, "Dent"));
        list.add(new Header(2, 8, "Alveol"));
        list.add(new Header(2, 9, "Postalv"));
        list.add(new Header(2, 10, "Retroflex"));
        list.add(new Header(2, 11, "All"));
        list.add(new Header(2, 12, "Palat"));
        list.add(new Header(2, 13, "Velar"));
        list.add(new Header(2, 14, "Uvular"));
        list.add(new Header(2, 15, "All"));
        list.add(new Header(2, 16, "Epiglot"));
        list.add(new Header(2, 17, "Glottal"));

        // вписываем значения хедеров
        for (Header h : list) {
            Cell cell = sheet.getRow(h.row).getCell(h.column);
            cell.setCellValue(h.text);
        }
    }
}