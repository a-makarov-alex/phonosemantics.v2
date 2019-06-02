package entities;

import entities.phonetics.Phoneme;
import knowledgeBase.SoundsBank;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

// TODO: idea is for future. Should be realized later
public class Language {

    public static final String INPUT_LANGUAGES_PATH =
            "D:\\JavaProjects2019\\word\\src\\main\\java\\knowledgeBase\\AllLanguages.xlsx";
    private static HashMap<String, Language> allLanguages = new HashMap<>();

    private String title;
    private String family;
    private String group;  // typology etc.

    private HashMap<String, Phoneme> phonology;
    private HashMap<Object, Boolean> phTypeCoverage;

    public Language(String title) {
        this.title = title;
        this.phonology = getLangPhonology();

        allLanguages.put(this.title, this);
    }


    public HashMap<String, Phoneme> getLangPhonology() {
        // open file for reading
        InputStream inputStream = null;
        HashMap<String, Phoneme> allPhonemes = new HashMap<>();

        try {
            inputStream = new FileInputStream(INPUT_LANGUAGES_PATH);
            Workbook wb = WorkbookFactory.create(inputStream);
            Sheet sheet = wb.getSheetAt(0);

            int rowNum = 1;
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(0);
            SoundsBank cBank = SoundsBank.getInstance();

            // LOOKING FOR LANGUAGE
            while (cell.getCellType() != CellType.BLANK) {
                String s = cell.getStringCellValue();
                //System.out.println(rowNum + " LANG " + s);

                if (this.title.toLowerCase().equals(s.toLowerCase())) {

                    // CREATING A PHONEMES BANK FOR THE LANGUAGE
                    for (Map.Entry<String, Phoneme> entry : cBank.getAllConsonantsTable().entrySet()) {
                        if (sheet.getRow(rowNum).getCell(1).getStringCellValue().contains(entry.getKey())) {
                            // System.out.println("KEY " + entry.getKey()); //check all phonemes in console

                            allPhonemes.put(entry.getKey(), entry.getValue());
                        }
                    }
                    break;
                }

                rowNum++;
                cell = sheet.getRow(rowNum).getCell(0);
            }

            inputStream.close();
            return allPhonemes;

        } catch (IOException e) {
            System.out.println("exception");    //TODO: logs
            return null;
        }
    }


    /** GETTERS AND SETTERS **/
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public HashMap<String, Phoneme> getPhonology() {
        return phonology;
    }

    public void setPhonology(HashMap<String, Phoneme> phonology) {
        this.phonology = phonology;
    }

    public static HashMap<String, Language> getAllLanguages() {
        return allLanguages;
    }

    public static void setAllLanguages(HashMap<String, Language> allLanguages) {
        Language.allLanguages = allLanguages;
    }

    public static Language getLanguage(String title) {
        if (allLanguages.containsKey(title)) {
            return allLanguages.get(title);
        } else {
            Language l = new Language(title);
            return l;
        }
    }
}
