package entities.phonetics;

public class Consonant extends Phoneme {

    private PlaceApproximate placeApproximate;
    private PlacePrecise placePrecise;
    private MannerApproximate mannerApproximate;
    private MannerPricise mannerPricise;
    private boolean voiced = false;
    private boolean longCons = false;

    private boolean stop = false;
    private boolean affricate = false;
    private boolean fricative = false;
    private boolean sibilant = false;
    private boolean nasal = false;
    private boolean semivowel = false;
    private boolean trill = false;
    private boolean flap = false;
    private boolean lateral = false;


    /**
     * ENUMS
     * **/
    public enum PlaceApproximate {
        LABIAL, CORONAL, DORSAL, LARYNGEAL
    }

    public enum PlacePrecise {
        BILABIAL, LABIODENTAL,                      // LABIAL
        DENTAL, ALVEOLAR, POSTALVEOLAR, RETROFLEX,  // CORONAL
        PALATAL, VELAR, UVULAR,                     // DORSAL
        EPIGLOTTAL, GLOTTAL
    }

    public enum MannerApproximate {
        OBSTRUENT, SONORANT, LIQUID
    }

    public enum MannerPricise {
        STOP, AFFRICATE, FRICATIVE, SIBILANT,            // OBSTRUENT
        NASAL, SEMIVOWEL, TRILL, FLAP,  // SONORANT
        LATERAL                         // LIQUID
    }

    /**
     * CONSTRUCTORS
     * **/
    public Consonant(String symbol, PlacePrecise placePrecise, MannerPricise mannerPricise) {
        super(symbol, SoundClass.CONSONANT);
        this.placePrecise = placePrecise;
        this.mannerPricise = mannerPricise;
        this.placeApproximate = placeApproximateFromPrecise(placePrecise);
        this.mannerApproximate = mannerApproximateFromPrecise(mannerPricise);
        this.voiced = false;
        // by this point all the flags are marked

    }

    public Consonant(String symbol, PlacePrecise placePrecise, MannerPricise mannerPricise, boolean voiced) {
        super(symbol, SoundClass.CONSONANT);
        this.placePrecise = placePrecise;
        this.mannerPricise = mannerPricise;
        this.placeApproximate = placeApproximateFromPrecise(placePrecise);
        this.mannerApproximate = mannerApproximateFromPrecise(mannerPricise);
        this.voiced = voiced;
        // by this point all the flags are marked
    }

    /** Needed for constructor**/
    private PlaceApproximate placeApproximateFromPrecise(PlacePrecise placePrecise) {
        switch (placePrecise) {
            case BILABIAL:
            case LABIODENTAL: { return PlaceApproximate.LABIAL; }
            case DENTAL:
            case ALVEOLAR:
            case POSTALVEOLAR:
            case RETROFLEX: { return PlaceApproximate.CORONAL; }
            case PALATAL:
            case VELAR:
            case UVULAR: { return PlaceApproximate.DORSAL; }
            case EPIGLOTTAL:
            case GLOTTAL: { return PlaceApproximate.LARYNGEAL; }
            default: { return null; }
        }
    }

    /** Needed for constructor**/
    private MannerApproximate mannerApproximateFromPrecise(MannerPricise mannerPricise) {
        switch (mannerPricise) {
            case STOP: { this.stop = true; return MannerApproximate.OBSTRUENT; }
            case AFFRICATE: { this.affricate = true; return MannerApproximate.OBSTRUENT; }
            case FRICATIVE: { this.fricative = true; return MannerApproximate.OBSTRUENT; }
            case SIBILANT: { this.fricative = true; this.sibilant = true; return MannerApproximate.OBSTRUENT; }
            case NASAL: { this.nasal = true; return MannerApproximate.SONORANT; }
            case SEMIVOWEL: { this.semivowel = true; return MannerApproximate.SONORANT; }
            case TRILL: { this.trill = true; return MannerApproximate.SONORANT; }
            case FLAP: { this.flap = true; return MannerApproximate.SONORANT; }
            case LATERAL: { this.lateral = true; return MannerApproximate.LIQUID; }
            default: {return null; }
        }
    }

    /**
     * GETTERS FOR MAIN FIELDS
     * **/
    public PlaceApproximate getPlaceApproximate() {
        return placeApproximate;
    }
    public PlacePrecise getPlacePrecise() {
        return placePrecise;
    }
    public MannerApproximate getMannerApproximate() {
        return mannerApproximate;
    }
    public MannerPricise getMannerPricise() {
        return mannerPricise;
    }
    public boolean isVoiced() {
        return voiced;
    }
    public boolean isLongCons() {
        return longCons;
    }

    /**
     * GETTERS FOR FLAGS
     * **/
    public boolean isStop() {
        return stop;
    }
    public boolean isAffricate() {
        return affricate;
    }
    public boolean isFricative() {
        return fricative;
    }
    public boolean isSibilant() {
        return sibilant;
    }
    public boolean isNasal() {
        return nasal;
    }
    public boolean isSemivowel() {
        return semivowel;
    }
    public boolean isTrill() {
        return trill;
    }
    public boolean isFlap() {
        return flap;
    }
    public boolean isLateral() {
        return lateral;
    }

}
