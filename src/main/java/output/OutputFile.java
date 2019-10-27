package output;

import entities.WordList;
import knowledgeBase.SoundsBank;
import main.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import statistics.Sample;
import statistics.Statistics;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class OutputFile {
    static final Logger userLogger = LogManager.getLogger(OutputFile.class);

    private String title;
    private String filePath;
    private Type type;
    private ArrayList<Sheet> sheets;
    private static final String OUTPUT_DIRECTORY = "D:\\JavaProjects2019\\word\\src\\main\\java\\output\\";
    private int recordsCounter;
    private Workbook wb;
    private static CellStyle headerCellStyle;

    private HashMap<Object, Sample> allSamplesSheet1 = null;

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

        userLogger.debug("output file created");
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
    public void fillWith(ArrayList<WordList> wordLists) {

        for (WordList wordList : wordLists) {
            switch (this.type) {
                case GENERAL: {
                    userLogger.info("writing to general file...");
                    writeToGeneralFile(wordList);
                }
                case NORMALITY: {
                    userLogger.info("writing to normality file...");
                    writeToNormalityFile(wordList);
                }
            }
        }
        addMeanAndAverage(Statistics.KindOfStats.WORDS_WITH_PHTYPE_PER_LIST);
    }

    private void writeToGeneralFile(WordList wordList) {
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
            userLogger.debug("vowels are written to general file");
            fileOut.close();

        } catch (IOException e) {
            userLogger.debug("IOException caught: " + e.getStackTrace());
        }
    }

    private void writeToNormalityFile(WordList wordList) {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.filePath);

            // SHEET WORDS_WITH_PHTYPE_PER_LIST
            userLogger.debug("writing WORDS_WITH_PHTYPE_PER_LIST...");
            writeOneKindOfStats(
                    sheets.get(0),
                    wordList.getStats(Statistics.KindOfStats.WORDS_WITH_PHTYPE_PER_LIST),
                    wordList);

            // SHEET PHTYPES_PER_LIST
            userLogger.debug("writing PHTYPES_PER_LIST...");
            writeOneKindOfStats(
                    sheets.get(1),
                    wordList.getStats(Statistics.KindOfStats.PHTYPES_PER_LIST),
                    wordList);

            // SHEET PHTYPES_AVERAGE_PER_WORD
            userLogger.debug("writing PHTYPES_AVERAGE_PER_WORD...");
            writeOneKindOfStats(
                    sheets.get(2),
                    wordList.getStats(Statistics.KindOfStats.PHTYPES_AVERAGE_PER_WORD),
                    wordList);

            // count a new record
            recordsCounter++;

            wb.write(fileOut);
            userLogger.info("writing to normality file is finished \n");
            fileOut.close();

        } catch (IOException e) {
            userLogger.debug("IOException caught: " + e.getStackTrace());
        }
    }

    private void writeOneKindOfStats(Sheet sh, HashMap<Object, Double> mapResult, WordList wordList) {
        String meaning = wordList.getMeaning();
        int size = wordList.getList().size();

        int row = 3 + recordsCounter;
        int column = 2;
        if (row != 3) {
            sh.createRow(row);
        }

        Row r = sh.getRow(row);
        r.createCell(0).setCellValue(meaning);
        r.createCell(1).setCellValue(size);
        r.createCell(2);

        // TODO add more maps here
        ArrayList<HashMap<Object, Header>> maps = new ArrayList<>();
        maps.add(Header.vowSh);
        maps.add(Header.consMannerSh);

        for (HashMap<Object, Header> map : maps) {
            for (Map.Entry<Object, Header> entry : map.entrySet()) {
                column = entry.getValue().getColumn();
                Cell c = sh.getRow(row).createCell(column);

                // Make cells format (percents etc.)
                DecimalFormat df = new DecimalFormat("#.#");

                String s1;
                if (sh == sheets.get(2)) {
                    s1 = df.format(mapResult.get(entry.getKey())) + " ";
                } else {
                    s1 = df.format(mapResult.get(entry.getKey()) * 100) + "% ";
                }
                String s2 = String.valueOf(wordList.getMapOfDividers().get(entry.getKey()));
                Font font = wb.createFont();
                font.setTypeOffset(Font.SS_SUPER);
                RichTextString richString = new XSSFRichTextString(s1 + s2);
                richString.applyFont(s1.length(), s1.length() + s2.length(), font);

                c.setCellValue(richString);
            }
        }
    }


    // Добавляем медиану и среднее арифметическое
    private void addMeanAndAverage(Statistics.KindOfStats kindOfStats) {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.filePath);
            userLogger.info("adding mean and average...");

            Sheet sh = sheets.get(0);

            // MEAN
            // 3 - высота хедеров
            Row rowMean = sh.createRow(3 + recordsCounter + 2);
            Row rowAver = sh.createRow(3 + recordsCounter + 3);

            HashMap<Object, Sample> samples = readAllSamples(kindOfStats);
            HashMap<Object, Header> headers = Header.vowSh;

            DecimalFormat df = new DecimalFormat("#.#");
            // VOWELS MANNER
            for (Map.Entry<Object, Header> entry : headers.entrySet()) {
                Cell cell = rowMean.createCell(entry.getValue().getColumn());
                cell.setCellValue(df.format(samples.get(entry.getKey()).getMean()) + "%");

                cell = rowAver.createCell(entry.getValue().getColumn());
                cell.setCellValue(df.format(samples.get(entry.getKey()).getAverage()) + "%");
            }
            userLogger.debug("vowels manner stats added");

            // CONSONANT MANNER
            headers = Header.consMannerSh;
            for (Map.Entry<Object, Header> entry : headers.entrySet()) {
                Cell cell = rowMean.createCell(entry.getValue().getColumn());
                cell.setCellValue(df.format(samples.get(entry.getKey()).getMean()) + "%");

                cell = rowAver.createCell(entry.getValue().getColumn());
                cell.setCellValue(df.format(samples.get(entry.getKey()).getAverage()) + "%");
            }
            userLogger.debug("consonants manner stats added");

            wb.write(fileOut);
            userLogger.debug("mean and average are written to file");
            fileOut.close();

        } catch (IOException e) {
            userLogger.info(e.toString());
        }
    }


    // TODO: удалить в GENERAL файле
    private CellStyle createCellStyleWithDataFormat(String format) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(wb.createDataFormat().getFormat(format));
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        return cellStyle;
    }


    // Final design implementation: styles, borders, colors
    public void finalDesign() {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.filePath);
            userLogger.info("designing and coloring...");
            CellStyle style;

            /** ВСТАВИТЬ ЗДЕСЬ ОКРАШИВАНИЕ В ЗАВИСИМОСТИ ОТ КВАРТИЛЯ
             * ПЕРЕБОР ПО Sample.getAllSamples
             * **/
            /*HashMap<Object, Sample> allSamples = Sample.getAllSamples();
            for (Map.Entry<Object, Sample> entry : allSamples.entrySet()) {

            }*/

            Set<Integer> rightBorderCellNum = new HashSet<>();
            rightBorderCellNum.add(0);
            rightBorderCellNum.add(1);
            rightBorderCellNum.add(2);
            rightBorderCellNum.add(Header.vowSh.get(SoundsBank.Height.CLOSE).getColumn());
            rightBorderCellNum.add(Header.vowSh.get(SoundsBank.Backness.BACK).getColumn());
            rightBorderCellNum.add(Header.vowSh.get(SoundsBank.Roundness.UNROUNDED).getColumn());
            rightBorderCellNum.add(Header.vowSh.get(SoundsBank.Nasalization.NON_NASAL).getColumn());
            rightBorderCellNum.add(Header.consMannerSh.get(SoundsBank.MannerApproximate.OBSTRUENT).getColumn());
            rightBorderCellNum.add(Header.consMannerSh.get(SoundsBank.MannerPricise.STOP).getColumn());

            //colorTheColumn(sheets.get(0), SoundsBank.Height.CLOSE);
            Sheet sheet = sheets.get(0);

            for (Map.Entry<Object, Header> entry : Header.vowSh.entrySet()) {
                colorTheColumn(sheet, entry.getKey(), Header.vowSh);
                //System.out.println(entry.getKey());
            }
            userLogger.debug("vowels manner colored");

            for (Map.Entry<Object, Header> entry : Header.consMannerSh.entrySet()) {
                try {
                    colorTheColumn(sheet, entry.getKey(), Header.consMannerSh);
                    //System.out.println(entry.getKey());
                } catch (NullPointerException e) {
                    userLogger.error(e.toString());
                }
            }

            // draw borders
            for (Sheet sh : sheets) {
                for (int i = 3; i < 3 + recordsCounter; i++) {
                    int vowFinCol = Header.vowSh.size();
                    int mannnerFinCol = vowFinCol + Header.consMannerSh.size();

                    for (int col = 0; col < 3 + vowFinCol; col++) {
                        Cell c = sh.getRow(i).getCell(col);
                        style = c.getCellStyle();

                        style.setBorderBottom(BorderStyle.DASHED);
                        if (rightBorderCellNum.contains(col)) {
                            style.setBorderRight(BorderStyle.THIN);
                        }
                        c.setCellStyle(style);
                    }

                    for (int col = vowFinCol; col < mannnerFinCol; col++) {
                        Cell c = sh.getRow(i).getCell(col);
                        style = c.getCellStyle();

                        style.setBorderBottom(BorderStyle.DASHED);
                        if (rightBorderCellNum.contains(col)) {
                            style.setBorderRight(BorderStyle.THIN);
                        }
                        c.setCellStyle(style);
                    }
                }
            }
            userLogger.debug("borders are drawn");
            wb.write(fileOut);
            userLogger.info("design is finished");
            fileOut.close();

        } catch (IOException e) {
            System.out.println("IOException caught");
        }
    }

    // Считывает столбик каждого фонотипа как выборку для расчета статистики
    public HashMap<Object, Sample> readAllSamples(Statistics.KindOfStats kindOfStats) {
        if (allSamplesSheet1 != null) {
            return allSamplesSheet1;
        }

        HashMap<Object, Sample> result = new HashMap<>();
        Sheet sh = null;
        Cell cell;
        if (kindOfStats == Statistics.KindOfStats.WORDS_WITH_PHTYPE_PER_LIST) {
            sh = sheets.get(0);
        }

        ArrayList<Double> dList;
        //VOWELS
        for (Map.Entry<Object, Header> entry : Header.vowSh.entrySet()) {
            dList = new ArrayList<>();

            for (int i = 3; i < this.recordsCounter + 3; i++) {
                // TODO: костыли запилены за неимением времени
                cell = sh.getRow(i).getCell(entry.getValue().getColumn());
                dList.add(parseNormalityCell(cell));
            }

            Sample sample = new Sample(dList, entry.getKey());
            result.put(entry.getKey(), sample);
        }

        // CONSONANTS MANNER
        for (Map.Entry<Object, Header> entry : Header.consMannerSh.entrySet()) {
            dList = new ArrayList<>();

            for (int i = 3; i < this.recordsCounter + 3; i++) {
                // TODO: костыли запилены за неимением времени
                cell = sh.getRow(i).getCell(entry.getValue().getColumn());
                dList.add(parseNormalityCell(cell));
            }

            Sample sample = new Sample(dList, entry.getKey());
            result.put(entry.getKey(), sample);
        }

        allSamplesSheet1 = result;
        return result;
    }


    // Возвращает первое число (процент, среднее...) из ячейки файла Normality
    // второе число - это делитель
    private double parseNormalityCell(Cell cell) {
        try {
            String s = cell.getStringCellValue();
            String[] str = s.split("%")[0].split(",");
            if (str.length == 1) {
                s = str[0];
            } else {
                s = str[0] + "." + str[1];
            }
            return Double.valueOf(s);
        } catch (NumberFormatException e) {
            userLogger.debug(e.toString());
            return 0.0;
        }
    }


    // Считывает столбик каждого фонотипа как выборку для расчета статистики
    public HashMap<Object, Double[]> readAllSamplesAsArray(Statistics.KindOfStats kindOfStats) {

        HashMap<Object, Double[]> allSamples = new HashMap<>();
        HashMap<Object, Sample> dLists = readAllSamples(kindOfStats);
        for (Map.Entry<Object, Sample> entry : dLists.entrySet()) {
            ArrayList<Double> dList = entry.getValue().getSample();
            Double[] dArr = new Double[dList.size()];
            for (int i = 0; i < dList.size(); i++) {
                dArr[i] = dList.get(i);
            }
            allSamples.put(entry.getKey(), dArr);
        }
        return allSamples;
    }


    // Раскрашиваем значения сэмпла (столбика) по квартилям
    public void colorTheColumn(Sheet sh, Object phType, HashMap<Object, Header> typeSheet) {
        int startRow = 3;
        Row row;
        int col = 0;
        Sample sample = Sample.getAllSamples().get(phType);
        CellStyle style;

        for (int i = startRow; i < startRow + recordsCounter; i++) {
            col = typeSheet.get(phType).getColumn();
            row = sh.getRow(i);
            Cell c = row.getCell(col);
            style = wb.createCellStyle();

            short color = 0;

            if (parseNormalityCell(c) >= sample.getQuartile_25()) {
                color = IndexedColors.GREEN.getIndex();
            } else if (parseNormalityCell(c) >= sample.getMean()) {
                color = IndexedColors.LIGHT_GREEN.getIndex();
            } else if (parseNormalityCell(c) >= sample.getQuartile_75()) {
                color = IndexedColors.CORAL.getIndex();
            } else {
                color = IndexedColors.RED.getIndex();
            }

            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setFillForegroundColor(color);
            c.setCellStyle(style);
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
