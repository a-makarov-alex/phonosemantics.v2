# phonosemantics.v2
Provides statistics for linguistics data

**************** METHODS *******************
********* Reading input file ***************

ArrayList<Word> getWordList(String) - reads the whole wordlist by the header from the input file.

ArrayList<Word> getAllWordLists() - reads all the wordlists from the input file.








************************* CLASS WORD **************************

int getNumOfPhonemes(String phoneme) - returns a number of some phoneme in the word's transcription

ArrayList<Phoneme> getTranscriptionFromWord() - takes a word from corresponding field and creates a transcription from it.
The result is written into the transcription field automatically.

String serialize() - serializes word as a JSON