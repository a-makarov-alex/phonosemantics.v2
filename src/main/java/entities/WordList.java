package entities;

import com.google.gson.Gson;

import java.util.ArrayList;

public class WordList {

    private String meaning;
    private ArrayList<Word> list;

    public WordList(ArrayList<Word> list) {
        this.meaning = list.get(0).getMeaning().getDefinition();
        this.list = list;
    }

    public ArrayList<Word> getList() {
        return list;
    }

    public void setList(ArrayList<Word> list) {
        this.list = list;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String serialize() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public Word getWord(String language) {
        for (Word word : this.list) {
            if (word.getLanguage().toLowerCase().equals(language.toLowerCase())) {
                System.out.println("SUCCESS: Word of language " + language +
                        " with meaning " + this.meaning + " is: " + word.getWord());
                return word;
            }
        }
        System.out.println("FAILURE: Word of language " + language +
                " with meaning " + this.meaning + " is not found");
        return null;
    }

    // counts all phonemes of all words in list
    public int countAllPhonemes() {
        int count = 0;
        for (Word w : this.getList()) {
            count += w.getLength();
        }
        return count;
    }
}
