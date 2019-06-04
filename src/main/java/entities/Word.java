package entities;

import com.google.gson.Gson;
import entities.phonetics.Consonant;
import entities.phonetics.Phoneme;
import entities.phonetics.Vowel;
import knowledgeBase.SoundsBank;
import main.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Predicate;

public class Word {
    private String word;
    private ArrayList<Phoneme> transcription;
    private Meaning meaning;
    private Language language;
    private int length;
    private PartOfSpeech partOfSpeech;  // TODO think about shifting field to meaning

    public enum PartOfSpeech {
        NOUN, VERB, ADJECTIVE
    }

    public Word(String word, String definition, String language, PartOfSpeech partOfSpeech) {
        this.word = word;
        this.meaning = new Meaning(definition);
        setTranscriptionFromWord(); // length is added here also
        this.language = Language.getLanguage(language);
        this.partOfSpeech = partOfSpeech;
    }

    public Word(String word, Language language) {
        this.word = word;
        this.language = language;
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
    public Language getLanguage() {
        return language;
    }
    public void setLanguage(Language language) {
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
        if (Main.CONSOLE_SHOW_TRASCRIPTION) {
            System.out.println("Word: " + word);
        }

        if (word != null) {
            String[] phonemes = word.split("");
            HashMap<String, Phoneme> allPhonemes = SoundsBank.getInstance().getAllConsonantsTable();

            // Phoneme might be a set of 2 symbols.
            // So we need to check the symbol after the current on every step.
            for (int i = 0; i < word.length(); i++) {

                // For last symbol
                if (i == word.length() - 1) {
                    Phoneme ph = allPhonemes.get(phonemes[i]);
                    this.transcription.add(ph);
                    this.getLanguage().categorizePh(ph);
                } else {

                    // For 2-graph phoneme
                    Phoneme ph = allPhonemes.get(phonemes[i] + phonemes[i+1]);
                    if (ph != null) {
                        this.transcription.add(ph);
                        this.getLanguage().categorizePh(ph);
                        i++;
                    } else {

                        // For 1-graph phoneme
                        ph = allPhonemes.get(phonemes[i]);
                        if (ph != null) {
                            this.transcription.add(ph);
                            this.getLanguage().categorizePh(ph);
                        } else {

                            // Empty phoneme
                            this.transcription.add(null);
                            if (Main.CONSOLE_UNKNOWN_PHONEMES) {
                                System.out.println("  Phoneme " + phonemes[i] + " is not found in sounds bank");
                            }
                        }
                    }
                }
            }

            this.length = transcription.size();
            if (Main.CONSOLE_SHOW_TRASCRIPTION) {
                printTranscription();
            }

        }
    }


    /**
     * BUNCH OF METHODS TO COUNT PHONOTYPES
     * **/
    /*public int countPhonotype(Consonant.MannerApproximate mannerApproximate) {
        return countConsPhonotypeBy(cons -> cons.getMannerApproximate().equals(mannerApproximate));
    }

    public int countPhonotype(Consonant.MannerPricise mannerPricise) {
        if (mannerPricise.equals(Consonant.MannerPricise.FRICATIVE)) {
            return countConsPhonotypeBy(cons -> cons.isFricative());
        } else {
            return countConsPhonotypeBy(cons -> cons.getMannerPricise().equals(mannerPricise));
        }
    }

    public int countPhonotype(Consonant.PlaceApproximate placeApproximate) {
        return countConsPhonotypeBy(cons -> cons.getPlaceApproximate().equals(placeApproximate));
    }

    public int countPhonotype(Consonant.PlacePrecise placePrecise) {
        return countConsPhonotypeBy(cons -> cons.getPlacePrecise().equals(placePrecise));
    }*/

    public int countPhonotype(Object object) {
        if (object.getClass().equals(Consonant.PlacePrecise.class)) {
            return countConsPhonotypeBy(cons -> cons.getPlacePrecise().equals((Consonant.PlacePrecise)object));
        }

        else if (object.getClass().equals(Consonant.MannerPricise.class)){
            return countConsPhonotypeBy(cons -> cons.getMannerPricise().equals((Consonant.MannerPricise)object));
        }

        // VOWELS
        else if (object.getClass().equals(Vowel.Height.class)){
            if (object.equals(Main.CONSOLE_SHOW_WORDS_OF_CLASS)) {
                System.out.print("Height :   ");
            }
            return countVowPhonotypeBy(vow -> vow.getHeight().equals((Vowel.Height)object));
        }

        else if (object.getClass().equals(Vowel.Backness.class)){
            return countVowPhonotypeBy(vow -> vow.getBackness().equals((Vowel.Backness)object));
        }

        else {
            return 0;
        }
    }

    /**
     *  The main methods for counting phonotype instances in the word
     *  **/
    private int countConsPhonotypeBy(Predicate<Consonant> p) {
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

    private int countVowPhonotypeBy(Predicate<Vowel> p) {
        int count = 0;
        for(Phoneme ph : this.transcription) {
            if (ph != null) {
                if (ph.getClass().equals(Vowel.class)) {
                    Vowel vow = (Vowel) ph;
                    if (p.test(vow)) {
                        count++;
                    }
                }
            } else {
                //System.out.println(this.getWord());
            }
        }
        return count;
    }
}
