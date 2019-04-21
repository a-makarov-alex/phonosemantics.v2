package entities;

import com.google.gson.Gson;
import entities.phonetics.Phoneme;

import java.util.HashMap;

public class Word {
    private String word;
    private HashMap<String, Phoneme> transcription; // TODO: String type cause we need "last" position in the transcription
    private Meaning meaning;
    private String language;
    private int length;
    private PartOfSpeech partOfSpeech;  // TODO think about shifting field to meaning

    public enum PartOfSpeech {
        NOUN, VERB, ADJECTIVE
    }

    public Word(String word, String definition, String language, PartOfSpeech partOfSpeech) {
        this.word = word;
        this.meaning = new Meaning(definition);
        this.transcription = writeAsTranscription(word);
        this.language = language;
        this.partOfSpeech = partOfSpeech;
        this.length = word.length();
    }

    public Word(String word) {
        this.word = word;
        this.transcription = writeAsTranscription(word);
        this.length = word.length();

    }


    /** GETTERS AND SETTERS**/
    public Meaning getMeaning() {
        return meaning;
    }
    public void setMeaning(Meaning meaning) {
        this.meaning = meaning;
    }
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public HashMap<String, Phoneme> getTranscription() {
        return transcription;
    }
    public void setTranscription(HashMap<String, Phoneme> transcription) {
        this.transcription = transcription;
    }
    public int getLength() {
        return length;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public PartOfSpeech getPartOfSpeech() {
        return partOfSpeech;
    }
    public void setPartOfSpeech(PartOfSpeech partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }



    public String serialize() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public int getNumOfPhonemes(String phoneme) {
        int count = 0;

        // i=1 cause map key starts with 1, not 0
        // remember: MAP key is String format, not integer.
        for (int i=1; i <= this.getTranscription().size(); i++) {
            if (this.getTranscription().get(String.valueOf(i)).getSymbol()== phoneme) {
                count ++;
            }
        }
        return count;
    }

    public HashMap<String, Phoneme> writeAsTranscription(String word) {
        HashMap<String, Phoneme> transcription = new HashMap<String, Phoneme>();

        // TODO this is a dinosaur method....
        String[] phonemes = new String[word.length()];
        char[] buffer = word.toCharArray();
        for (int i=0; i < word.length(); i++) {
             phonemes[i] = String.valueOf(buffer[i]);
        }

        for (int i=0; i < phonemes.length; i++) {
            String position = String.valueOf(i + 1);
            String symbol = phonemes[i];
            transcription.put(position, new Phoneme(symbol, i+1));
        }

        return transcription;
    }
}
