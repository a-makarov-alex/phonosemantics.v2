package output;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * После заполнения библиотеки фонем, распознаваемых программой,
 * необходимо открыть файл с таблицами всех фонем и выделить те фонемы, которые распознаются.
 * Таким образом, будет понятно, насколько велико покрытие и какие фонемы нужно ещё ввести.
 * **/
public class PhonemesCoverageFile {
    static final Logger userLogger = LogManager.getLogger(PhonemesCoverageFile.class);

    private static String title = "PhonemesCoverage.xlsx";
    private static final String OUTPUT_DIRECTORY = "D:\\JavaProjects2019\\word\\src\\main\\java\\output\\";
    // Шаблон с таблицами всех фонем
    private static final String INPUT_FILE = OUTPUT_DIRECTORY + "PhonemesCoverageExample.xlsx";

}
