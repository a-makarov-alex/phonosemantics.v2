package entities;

public class Consonant extends Phoneme {

    public enum Place {
        OPEN, NEAR_OPEN, OPEN_MID, MID, CLOSE_MID, NEAR_CLOSE, CLOSE
    }

    // approximate_place - зависит от precise_place
    // т.е. если bilabial, то автоматически labial
    // наверное, стоит это встроить в конструктор
}
