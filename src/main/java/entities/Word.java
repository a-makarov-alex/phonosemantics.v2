package entities;

import com.google.gson.Gson;
import entities.phonetics.Consonant;
import entities.phonetics.Phoneme;
import knowledgeBase.SoundsBank;

import java.util.ArrayList;
import java.util.function.Predicate;

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
        setTranscriptionFromWord(); // length is added here also
        this.language = language;
        this.partOfSpeech = partOfSpeech;
    }

    public Word(String word) {
        this.word = word;
        setTranscriptionFromWord(); // length is added here also
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

    public void printTranscription() {
        // Print transcription to console
        System.out.print("Transcription: ");
        for (Phoneme p : this.transcription) {
            if (p != null) {
                System.out.print(p.getSymbol() + " ");
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println("Length: " + this.length);
        System.out.println("");
        System.out.println("");
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

    public void setTranscriptionFromWord() {
        this.transcription = new ArrayList<>();
        String word = this.getWord();
        System.out.println("Word: " + word);

        if (word != null) {
            String[] phonemes = word.split("");
            SoundsBank cBank = SoundsBank.getInstance();

            // Phoneme might be a set of 2 symbols.
            // So we need to check the symbol after the current on every step.
            for (int i = 0; i < word.length(); i++) {

                // For last symbol
                if (i == word.length() - 1) {
                    Phoneme ph = cBank.find(phonemes[i]);
                    this.transcription.add(ph);

                } else {

                    // For 2-graph phoneme
                    Phoneme ph = cBank.find(phonemes[i] + phonemes[i+1]);
                    if (ph != null) {
                        this.transcription.add(ph);
                        i++;
                    } else {

                        // For 1-graph phoneme
                        ph = cBank.find(phonemes[i]);
                        if (ph != null) {
                            this.transcription.add(ph);
                        } else {

                            // Empty phoneme
                            this.transcription.add(null);
                            System.out.println("  Phoneme " + phonemes[i] + " is not found in sounds bank");
                        }
                    }
                }
            }

            this.length = transcription.size();
            printTranscription();

        }
    }


    /**
     * BUNCH OF METHODS WITH POLYMORPHYSM
     * **/
    public int countPhonotype(Consonant.MannerApproximate mannerApproximate) {
        return countPhonotypeBy(cons -> cons.getMannerApproximate().equals(mannerApproximate));
    }

    public int countPhonotype(Consonant.MannerPricise mannerPricise) {
        if (mannerPricise.equals(Consonant.MannerPricise.FRICATIVE)) {
            return countPhonotypeBy(cons -> cons.isFricative());
        } else {
            return countPhonotypeBy(cons -> cons.getMannerPricise().equals(mannerPricise));
        }
    }

    public int countPhonotype(Consonant.PlaceApproximate placeApproximate) {
        return countPhonotypeBy(cons -> cons.getPlaceApproximate().equals(placeApproximate));
    }

    public int countPhonotype(Consonant.PlacePrecise placePrecise) {
        return countPhonotypeBy(cons -> cons.getPlacePrecise().equals(placePrecise));
    }

    /**
     *  The main method for counting phonotype examples in the word
     *  **/
    private int countPhonotypeBy(Predicate<Consonant> p) {
        int count = 0;
        for(Phoneme ph : this.transcription) {
            if (ph != null) {
                if (ph.getClass().equals(Consonant.class)) {
                    Consonant cons = (Consonant) ph;
                    if (p.test(cons)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
