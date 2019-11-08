package output;

import org.junit.Test;

import static org.junit.Assert.*;

public class PhonemesCoverageFileTest {

    @Test
    public void definePhonemesCoverage() {
        PhonemesCoverageFile.getInstance().definePhonemesCoverage();
    }
}