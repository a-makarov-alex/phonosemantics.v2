package output;


import knowledgeBase.SoundsBank;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * После заполнения библиотеки фонем, распознаваемых программой,
 * необходимо открыть файл с таблицами всех фонем (по IPA) и выделить те фонемы, которые распознаются.
 * Таким образом, будет понятно, насколько велико покрытие и какие фонемы нужно ещё ввести.
 * **/
public class PhonemesCoverageFile {
    static final Logger userLogger = LogManager.getLogger(PhonemesCoverageFile.class);

    private String title;
    private Workbook wb;
    private CoverageSheet vowelsSheet;
    private CoverageSheet consonantsSheet;
    private CellStyle foundPhonemeStyle;

    // Шаблон с таблицами всех фонем перезаписывается в отредактированном варианте
    // Сам шаблон остается в оригинальном виде
    private final String OUTPUT_DIRECTORY = "D:\\JavaProjects2019\\word\\src\\main\\java\\output\\";
    private final String INPUT_FILE_PATH = OUTPUT_DIRECTORY + "PhonemesCoverageExample.xlsx";


    private class CoverageSheet {
        Sheet sheet;
        // Границы таблицы звуков для осмотра
        int firstRow;
        int lastRow;
        int firstCol;
        int lastCol;

        public CoverageSheet(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
            this.sheet = sheet;
            this.firstRow = firstRow;
            this.lastRow = lastRow;
            this.firstCol = firstCol;
            this.lastCol = lastCol;
        }
    }

    /***************************  SINGLETON ************************/
    private static PhonemesCoverageFile instance;

    public static PhonemesCoverageFile getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new PhonemesCoverageFile();
            return instance;
        }
    }

    public PhonemesCoverageFile() {
        this.title =  "PhonemesCoverage.xlsx";
        try {
            InputStream inputStream = new FileInputStream(INPUT_FILE_PATH);
            this.wb = WorkbookFactory.create(inputStream);
            userLogger.info("start defining phonemes coverage...");
        } catch (IOException e) {
            userLogger.error(e.toString());
        }

        // defining style for cells with found phonemes
        foundPhonemeStyle = wb.createCellStyle();
        short color = IndexedColors.LIGHT_GREEN.getIndex();
        foundPhonemeStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        foundPhonemeStyle.setFillForegroundColor(color);

        vowelsSheet = new CoverageSheet(this.wb.getSheetAt(0), 2, 8, 1, 6);
        consonantsSheet = new CoverageSheet(this.wb.getSheetAt(1), 2, 14, 1, 24);
    }


    /************** ОПРЕДЕЛЯЕМ КАКИЕ ФОНЕМЫ РАСПОЗНАЁТ ПРОГРАММА И ОТМЕЧАЕМ ИХ В ФАЙЛЕ **/
    public void definePhonemesCoverage() {
        SoundsBank soundsBank = SoundsBank.getInstance();
        Row row;
        ArrayList<CoverageSheet> shList = new ArrayList<>();
        shList.add(this.vowelsSheet);
        shList.add(this.consonantsSheet);

        for (CoverageSheet sh : shList) {
            for (int i = sh.firstRow; i <= sh.lastRow; i++) {
                row = vowelsSheet.sheet.getRow(i);
                for (int j = sh.firstCol; j <= sh.lastCol; j++) {
                    // ячейка может быть пустой! см. таблицу IPA
                    Cell cell = null;
                    try {
                        cell = row.getCell(j);
                    } catch (Error e) {
                        userLogger.error(e.toString() + "\n ROW: " + i + "\n COLUMN: " + j);
                    }
                    if (cell != null) {
                        if (soundsBank.find(cell.getStringCellValue()) != null) {
                            cell.setCellStyle(this.foundPhonemeStyle);
                        }
                    }
                }
            }
        }

        // Записываем результаты в файл
        try {
            FileOutputStream fileOut = new FileOutputStream(this.OUTPUT_DIRECTORY + this.title);
            wb.write(fileOut);
            fileOut.close();
            userLogger.info("phonemes coverage is done and can be seen in file: " + this.title);

        } catch (IOException e) {
            userLogger.error(e.toString());
        }
    }

}
