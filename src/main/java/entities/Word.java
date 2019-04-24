package entities;

import com.google.gson.Gson;
import entities.phonetics.Phoneme;
import knowledgeBase.ConsonantsBank;

import java.util.ArrayList;

public class Word {
    private String word;
    private ArrayList<Phoneme> transcription;
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
        this.transcription = this.getTranscriptionFromWord();
        this.language = language;
        this.partOfSpeech = partOfSpeech;
        this.length = word.length();
    }

    public Word(String word) {
        this.word = word;
        this.transcription = this.getTranscriptionFromWord();
        this.length = word.length();    // TODO this is incorrect

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
    public ArrayList<Phoneme> getTranscription() {
        return transcription;
    }
    public void setTranscription(ArrayList<Phoneme> transcription) {
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

    public String getTranscriptionAsString() {
        String result = transcription.get(0).getSymbol();
        if (transcription != null) {
            for (int i = 1; i < transcription.size(); i++) {
                result += " - ";
                result += transcription.get(i).getSymbol();
                System.out.println(result);
            }
        }
        return result;
    }

    public int getNumOfPhonemes(String phoneme) {
        int count = 0;
        for (int i=0; i < this.getTranscription().size(); i++) {
            if (this.getTranscription().get(i).getSymbol().equals(phoneme)) {
                count ++;
            }
        }
        return count;
    }

    public ArrayList<Phoneme> getTranscriptionFromWord() {
        ArrayList<Phoneme> transcription = new ArrayList<>();
        String word = this.getWord();

        if (word != null) {

            // TODO this is a dinosaur method....
            String[] phonemes = word.split("");
            ConsonantsBank cBank = ConsonantsBank.getInstance();

            // Phoneme might be a set of 2 symbols.
            // So we need to check the symbol after the current on every step.
            for (int i = 0; i < word.length(); i++) {

                // If the symbol is last
                if (i == word.length() - 1) {
                    transcription.add(new Phoneme("Last"));
                } else {
                    if (phonemes[i+1].equals("h")) {
                        transcription.add(cBank.find(phonemes[i] + phonemes[i+1]));
                        i++;
                    } else {
                        transcription.add(cBank.find(phonemes[i]));
                    }
                }
            }

            /*for (int i = 0; i < phonemes.length; i++) {
                String symbol = phonemes[i];
                transcription.add(new Phoneme(symbol, i + 1));
            }*/

            return transcription;
        } else {
            return null;
        }
    }
}
