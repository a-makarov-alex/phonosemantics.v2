package entities;

import entities.phonetics.Phoneme;
import knowledgeBase.SoundsBank;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordTest {
    private static SoundsBank soundBank = SoundsBank.getInstance();

    //TODO: впоследствии сделать для диграфаа и триграфа как функциональность, так и тесты

    @Test
    public void getNumOfPhonemes() {
        Word word = new Word("lalala");
        Assert.assertEquals(word.getNumOfPhonemes("l"), 3);

        word = new Word("tsatsatsa");
        Assert.assertEquals(word.getNumOfPhonemes("ts"), 3);
    }

    @Test
    public void vowel_AllPositions() {
        String sound = "a";
        Phoneme testPhoneme = soundBank.find(sound);
        Word wordFirstPosition = new Word("asd");
        Assert.assertEquals(wordFirstPosition.getTranscription().get(0), testPhoneme);

        Word wordMiddlePosition = new Word("sad");
        Assert.assertEquals(wordMiddlePosition.getTranscription().get(1), testPhoneme);

        Word wordLastPosition = new Word("dsa");
        Assert.assertEquals(wordLastPosition.getTranscription().get(wordLastPosition.getLength() - 1), testPhoneme);
    }

    @Test
    public void consonant_AllPositions() {
        String sound = "s";
        Phoneme testPhoneme = soundBank.find(sound);
        Word wordFirstPosition = new Word("sad");
        Assert.assertEquals(wordFirstPosition.getTranscription().get(0), testPhoneme);

        Word wordMiddlePosition = new Word("asd");
        Assert.assertEquals(wordMiddlePosition.getTranscription().get(1), testPhoneme);

        Word wordLastPosition = new Word("das");
        Assert.assertEquals(wordLastPosition.getTranscription().get(wordLastPosition.getLength() - 1), testPhoneme);
    }

    @Test
    public void digraphAffricate_FirstPosition() {
        String sound = "ts";
        Phoneme testPhoneme = soundBank.find(sound);
        Word wordFirstPosition = new Word("tsad");
        Assert.assertEquals(wordFirstPosition.getTranscription().get(0), testPhoneme);
        Assert.assertEquals(wordFirstPosition.getTranscription().size(), 3);
        Assert.assertEquals(wordFirstPosition.getLength(), 3);
    }

    @Test
    public void digraphAffricate_MiddlePosition() {
        String sound = "ts";
        Phoneme testPhoneme = soundBank.find(sound);
        Word wordFirstPosition = new Word("atsd");
        Assert.assertEquals(wordFirstPosition.getTranscription().get(1), testPhoneme);
        Assert.assertEquals(wordFirstPosition.getTranscription().size(), 3);
        Assert.assertEquals(wordFirstPosition.getLength(), 3);
    }

    @Test
    public void digraphAffricate_LastPosition() {
        String sound = "ts";
        Phoneme testPhoneme = soundBank.find(sound);
        Word wordFirstPosition = new Word("dats");
        Assert.assertEquals(wordFirstPosition.getTranscription().get(wordFirstPosition.getLength() - 1), testPhoneme);
        Assert.assertEquals(wordFirstPosition.getTranscription().size(), 3);
        Assert.assertEquals(wordFirstPosition.getLength(), 3);
    }
}