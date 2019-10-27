package entities;

import entities.phonetics.Consonant;
import entities.phonetics.Phoneme;
import entities.phonetics.Vowel;
import knowledgeBase.SoundsBank;
import main.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Predicate;

// TODO: idea is for future. Should be realized later
public class Language {
    static final Logger userLogger = LogManager.getLogger(Language.class);

    public static final String INPUT_LANGUAGES_PATH =
            "D:\\JavaProjects2019\\word\\src\\main\\java\\knowledgeBase\\AllLanguages.xlsx";
    private static HashMap<String, Language> allLanguages = new HashMap<>();

    private String title;
    private String family;
    private String group;  // typology etc.

    private Set<Phoneme> phonology;

    // maps save the verdict "if the phoneme/phType were found in the Language words on practice"
    private Set<Phoneme> phCoverage;
    private HashMap<Object, Integer> phTypeCoverage;
    private Set<Phoneme> phNotDescribed;

    public Language(String title) {
        this.title = title;
        this.phonology = getLangPhonology();
        this.phTypeCoverage = calculatePhTypeCoverage();
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
            String[] allPhArr = null;

            // LOOKING FOR LANGUAGE
            while (cell.getCellType() != CellType.BLANK) {
                String s = cell.getStringCellValue();
                //System.out.println(rowNum + " LANG " + s);

                if (this.title.toLowerCase().equals(s.toLowerCase())) {

                    if (Main.CONSOLE_LANG_PHONOLOGY) {
                        System.out.print("PHONOLOGY for LANG " + this.getTitle() + ": ");
                    }


                    // CREATING A PHONEMES BANK FOR THE LANGUAGE
                    for (Map.Entry<String, Phoneme> entry : cBank.getAllPhonemesTable().entrySet()) {
                        if (allPhArr == null) {
                            int NUM_OF_PHONOLOGY_COLUMNS = 7;
                            String allPh = " ";
                            Row r = sheet.getRow(rowNum);

                            for (int i = 1; i <= NUM_OF_PHONOLOGY_COLUMNS; i++) {
                                if (r.getCell(i) != null) {
                                    allPh += r.getCell(i).getStringCellValue() + " ";
                                }
                            }
                            allPhArr = allPh.split(" ");
                        }

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

    // Count all the phonotypes present in a specific language
    public HashMap<Object, Integer> calculatePhTypeCoverage() {
        //userLogger.debug("PhType coverage calculating is started");
        HashMap<Object, Integer> mapPhType = SoundsBank.getAllPhonotypes();

        if (Main.CONSOLE_LANG_PHONOTYPES) {
            userLogger.debug(this.title);
        }

        for (Map.Entry<Object, Integer> entry : mapPhType.entrySet()) {

            if (Main.CONSOLE_LANG_PHONOTYPES) {
                userLogger.debug(entry.getKey() + " : ");
            }

            entry.setValue(entry.getValue() + findPhType(entry.getKey()));

            if (Main.CONSOLE_LANG_PHONOTYPES) {
                userLogger.debug("TOTAL : " + entry.getValue());
            }
        }
        return mapPhType;
    }


    // Буферный метод, который маппит классы для лямбды
    public Integer findPhType(Object phType) {
        int i = 0;
        Class phTypeClass = phType.getClass();

        // *************************** VOWELS
        if (phTypeClass.equals(SoundsBank.Height.class)) {
            return findVowByPredicate(vow -> vow.getHeight().equals((SoundsBank.Height)phType));
        }

        else if (phTypeClass.equals(SoundsBank.Backness.class)) {
            return findVowByPredicate(vow -> vow.getBackness().equals((SoundsBank.Backness)phType));
        }

        else if (phTypeClass.equals(SoundsBank.Roundness.class)) {
            return findVowByPredicate(vow -> vow.isRoundedness().equals((SoundsBank.Roundness)phType));
        }

        else if (phTypeClass.equals(SoundsBank.Nasalization.class)) {
            return findVowByPredicate(vow -> vow.isNasalization().equals((SoundsBank.Nasalization)phType));
        }

        // **************************CONSONANTS
        if (phTypeClass.equals(SoundsBank.Phonation.class)) {
            return findConsByPredicate(cons -> cons.isVoiced().equals((SoundsBank.Phonation)phType));
        }

        if (phTypeClass.equals(SoundsBank.MannerPricise.class)) {
            return findConsByPredicate(cons -> cons.getMannerPricise().equals((SoundsBank.MannerPricise)phType));
        }

        if (phTypeClass.equals(SoundsBank.MannerApproximate.class)) {
            return findConsByPredicate(cons -> cons.getMannerApproximate().equals((SoundsBank.MannerApproximate)phType));
        }

        else {
            return i;
        }
    }

    // Проверяем каждую фонему языка на соответствие заданному фонотипу и возвращаем число таких фонем в языке.
    private Integer findVowByPredicate(Predicate<Vowel> p) {
        int count = 0;

        for (Phoneme ph : phonology) {
            if (ph != null) {
                if (ph.getClass().equals(Vowel.class)) {
                    Vowel vow = (Vowel) ph;
                    if (p.test(vow)) {
                        count++;

                        if (Main.CONSOLE_LANG_PHONOTYPES) {
                            System.out.println(vow.getSymbol());
                        }
                    }
                }
            }
        }

        return count;
    }


    private int findConsByPredicate(Predicate<Consonant> p) {
        int count = 0;

        for (Phoneme ph : phonology) {
            if (ph != null) {
                if (ph.getClass().equals(Consonant.class)) {
                    Consonant cons = (Consonant) ph;
                    if (p.test(cons)) {
                        count++;

                        if (Main.CONSOLE_LANG_PHONOTYPES) {
                            System.out.println(cons.getSymbol());
                        }
                    }
                }
            }
        }
        return count;
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

    public HashMap<Object, Integer> getPhTypeCoverage() {
        return phTypeCoverage;
    }

    public void setPhTypeCoverage(HashMap<Object, Integer> phTypeCoverage) {
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
