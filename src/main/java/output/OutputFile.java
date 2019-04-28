package output;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class OutputFile {

    private String filePath;
    private static final String INPUT_DIRECTORY = "D:\\JavaProjects2019\\word\\src\\main\\java\\output\\";

    private Workbook wb;
    private static CellStyle headerCellStyle;

    public OutputFile() {
        this.filePath = INPUT_DIRECTORY + "OutputFile.xlsx";
        this.wb = new XSSFWorkbook();
        createOutputFile();


    }

    public void createOutputFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.filePath);

            // Создаем шаблон Excel файла
            ArrayList<Sheet> sheets = new ArrayList<>();
            sheets.add(wb.createSheet("Vowels"));
            sheets.add(wb.createSheet("Cons manner"));
            sheets.add(wb.createSheet("Cons place"));
            Row row;
            Cell cell;

            // Задаем стиль, который применим к хедерам
            headerCellStyle = wb.createCellStyle();
            headerCellStyle.setBorderRight(BorderStyle.THIN);
            headerCellStyle.setBorderBottom(BorderStyle.THIN);
            headerCellStyle.setBorderLeft(BorderStyle.THIN);
            headerCellStyle.setBorderTop(BorderStyle.THIN);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // Добавляем общие заголовки на все листы
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
