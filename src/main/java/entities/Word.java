package entities;

public class Word {
    private String meaning;
    private Transcription transcription;
    private int lenght; //TODO: maybe better include into transcription class
    private String language;
    private PartOfSpeech partOfSpeech;

    public enum PartOfSpeech {
        NOUN, VERB, ADJECTIVE
    }

    public Word(String meaning, Transcription transcription, String language, PartOfSpeech partOfSpeech) {
        this.meaning = meaning;
        this.transcription = transcription;
        this.language = language;
        this.partOfSpeech = partOfSpeech;
    }

    public Word() {
    }
}
