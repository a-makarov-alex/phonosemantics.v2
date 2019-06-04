package entities;

import entities.phonetics.Phoneme;
import knowledgeBase.SoundsBank;
import main.Main;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

// TODO: idea is for future. Should be realized later
public class Language {

    public static final String INPUT_LANGUAGES_PATH =
            "D:\\JavaProjects2019\\word\\src\\main\\java\\knowledgeBase\\AllLanguages.xlsx";
    private static HashMap<String, Language> allLanguages = new HashMap<>();

    private String title;
    private String family;
    private String group;  // typology etc.

    private Set<Phoneme> phonology;

    // maps save the verdict "if the phoneme/phType were found in the Language words on practice"
    private Set<Phoneme> phCoverage;
    private HashMap<Object, Boolean> phTypeCoverage;
    private Set<Phoneme> phNotDescribed;

    public Language(String title) {
        this.title = title;
        this.phonology = getLangPhonology();
        phCoverage = new HashSet<>();
        phNotDescribed = new HashSet<>();


        allLanguages.put(this.title, this);
    }


    public HashSet<Phoneme> getLangPhonology() {
        // open file for reading
        InputStream inputStream = null;
        HashSet<Phoneme> allPhonemes = new HashSet<>();

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

                    if (Main.CONSOLE_LANG_PHONOLOGY) {
                        System.out.print("PHONOLOGY for LANG " + this.getTitle() + ": ");
                    }

                    // CREATING A PHONEMES BANK FOR THE LANGUAGE
                    for (Map.Entry<String, Phoneme> entry : cBank.getAllConsonantsTable().entrySet()) {
                        String allPh = sheet.getRow(rowNum).getCell(1).getStringCellValue();
                        String[] allPhArr = allPh.split(" ");

                        for (String ph: allPhArr) {
                            if (ph.equals(entry.getKey())) {
                                if (Main.CONSOLE_LANG_PHONOLOGY) {
                                    System.out.print(entry.getKey() + " "); //check all phonemes in console
                                }
                                allPhonemes.add(entry.getValue());
                            }
                        }
                    }
                    if (Main.CONSOLE_LANG_PHONOLOGY) {System.out.println();}
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

    public void categorizePh(Phoneme ph) {
        if (phonology.contains(ph)) {
            if (!phCoverage.contains(ph)) {
                phCoverage.add(ph);
            }
        } else {
            phNotDescribed.add(ph);
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

    public Set<Phoneme> getPhonology() {
        return phonology;
    }

    public void setPhonology(HashSet<Phoneme> phonology) {
        this.phonology = phonology;
    }

    public static HashMap<String, Language> getAllLanguages() {
        return allLanguages;
    }

    public Set<Phoneme> getPhCoverage() {
        return phCoverage;
    }

    public void setPhCoverage(Set<Phoneme> phCoverage) {
        this.phCoverage = phCoverage;
    }

    public HashMap<Object, Boolean> getPhTypeCoverage() {
        return phTypeCoverage;
    }

    public void setPhTypeCoverage(HashMap<Object, Boolean> phTypeCoverage) {
        this.phTypeCoverage = phTypeCoverage;
    }

    public Set<Phoneme> getPhNotDescribed() {
        return phNotDescribed;
    }

    public void setPhNotDescribed(Set<Phoneme> phNotDescribed) {
        this.phNotDescribed = phNotDescribed;
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
