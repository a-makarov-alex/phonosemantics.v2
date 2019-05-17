# phonosemantics.v2
Provides statistics for linguistics data

** METHODS **
===

* Reading input file *
------

ArrayList<Word> `getWordList(String)` - reads the whole list by the header from the input file.

ArrayList<Word> `getAllWordLists()` - reads all the wordlists from the input file.








************************* Class Word **************************

int getNumOfPhonemes(String phoneme) - returns a number of some phoneme in the word's transcription

ArrayList<Phoneme> getTranscriptionFromWord() - takes a word from corresponding field and creates a transcription from it.
        The result is written into the transcription field automatically.

void printTranscription() - prints transcription to console

int getNumOfPhonemes(String phoneme) - returns a number of a certain Phoneme in the word

String serialize() - serializes word as a JSON




************************* Class SoundsBank ************************

Phoneme find(String requestedSymbol) - returns a reference on requested Vowel/Consonant object in a Phoneme type.
        Inverse transformation implements by (Vowel)ph or (Consonant)ph.
        If phoneme is not found in table method returns null.


************************* Class Statistics ************************

HashMap<Object, Integer> getAllPhonotypes - returns a list of all ph-types as a key. 
        Value will be filled later with a number of every ph-type instances per list.
        
HashMap<Object, Integer> countAllPhonotypesPerList() - counts all ph-types instances per list for further stats counting. 




        ПЛАНЫ

     1.   Написать методы подсчета по характеристикам на уровне list и experimentsample
        getNumOfWordsByParameter(parameter) - возвращает число слов с 1 и более искомым параметром
        {
        for (Word word : this.list) {
        for (Phoneme phoneme: word.getTranscription) {
        switch(phoneme.getClass()){
        case consonant: {

        }
        case vowel : {

        }
        }
        }

        варианты подсчета:
        - для гласных
        - для согласных
        - для всех (а есть ли такие параметры?)

        getNumOfParameterInstances(parameter) - возвращает число экземпляров искомого параметра в списке
        сделать по аналогии


        2.
