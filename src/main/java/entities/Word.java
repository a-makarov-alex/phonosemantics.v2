package entities;

import com.google.gson.Gson;

public class Word {
    private Transcription transcription;
    private Meaning meaning;
    private String language;
    private int length;
    private PartOfSpeech partOfSpeech;  // TODO think about shifting field to meaning

    public enum PartOfSpeech {
        NOUN, VERB, ADJECTIVE
    }

    public Word(Meaning meaning, Transcription transcription, String language, PartOfSpeech partOfSpeech) {
        this.meaning = meaning;
        this.transcription = transcription;
        this.language = language;
        this.partOfSpeech = partOfSpeech;
        this.length = transcription.getFullForm().length();
    }

    public Word() {
    }

    public Meaning getMeaning() {
        return meaning;
    }

    public void setMeaning(Meaning meaning) {
        this.meaning = meaning;
    }

    public Transcription getTranscription() {
        return transcription;
    }

    public void setTranscription(Transcription transcription) {
        this.transcription = transcription;
        this.length = transcription.getFullForm().length();
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
}
