package entities;

import java.util.HashMap;

public class Transcription {
    private String fullForm;
    private HashMap<Integer, Sound> transcription;


    public Transcription(String fullForm) {
        this.fullForm = fullForm;
        this.transcription = writeAsTranscription(fullForm);

    }

    public String getFullForm() {
        return fullForm;
    }

    public void setFullForm(String fullForm) {
        this.fullForm = fullForm;
    }

    public HashMap<Integer, Sound> getTranscription() {
        return transcription;
    }

    public void setTranscription(HashMap<Integer, Sound> transcription) {
        this.transcription = transcription;
    }

    public HashMap<Integer, Sound> writeAsTranscription(String fullForm) {
        // TODO: required method
        return new HashMap<Integer, Sound>();
    }

    public void parseTranscription() {

    }

}
