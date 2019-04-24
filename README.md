# phonosemantics.v2
Provides statistics for linguistics data

**************** METHODS *******************
********* Reading input file ***************
ArrayList<Word> getWordList(String) - reads the whole wordlist by the header from the input file.

ArrayList<Word> getAllWordLists() - reads all the wordlists from the input file.








************************* Class Word **************************
int getNumOfPhonemes(String phoneme) - returns a number of some phoneme in the word's transcription

ArrayList<Phoneme> getTranscriptionFromWord() - takes a word from corresponding field and creates a transcription from it.
        The result is written into the transcription field automatically.

String serialize() - serializes word as a JSON




************************* Class SoundsBank ************************
Phoneme find(String requestedSymbol) - returns a reference on requested Vowel/Consonant object in a Phoneme type.
        Inverse transformation implements by (Vowel)ph or (Consonant)ph.
        If phoneme is not found in table method returns null.