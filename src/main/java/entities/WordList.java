package entities;

import com.google.gson.Gson;

import java.util.ArrayList;

public class WordList {

    private String meaning;
    private ArrayList<Word> wordlist;

    public WordList(ArrayList<Word> wordlist) {
        this.meaning = wordlist.get(0).getMeaning().getDefinition();
        this.wordlist = wordlist;
    }

    public ArrayList<Word> getWordlist() {
        return wordlist;
    }

    public void setWordlist(ArrayList<Word> wordlist) {
        this.wordlist = wordlist;
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
}
