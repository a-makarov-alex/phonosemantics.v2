# phonosemantics.v2
Provides statistics for linguistics data

GLOSSARY
===

* ***ph-Type*** - phonotype. Some characteristic that unifies a group of phonemes, e.g. roundness, height for vowels, manner for consonants.

***

WORKFLOW
===

1. Gather linguistic data to `.xlsx` file (manually).
2. Read data from `.xlsx`.
3. Caluclate statistics.
4. Create some user-friendly output format for results.

### Details

#### 1. INPUT
  `.xlsx` file
  Headers - word meanings like "big", "run", "stone".
  Rows - language names.
#### 2. READING
  There are 2 options:
    1. Getting list for a specific meaning. It may be useful for comparing lists "1 to 1".
    2. Getting every word of every language from the input file and places them to `globalWordList`.
#### 3. STATISTICS
  3.1 Process raw data.
  3.2 Prove the normality.
  3.3 Look up for anomaly in the ph-Types disribution or, rarely, phonemes distribution.
  
  3.1 We can count ph-Types `per word` or `per list`. Also we can count `num of words containing ph-Type`. This allows us to count different types of stats: 
  *% of words with ph-Type (per list);
  *% of ph with ph-Type (per list);
  *average ph-Type per word.
  
  3.2 In order to use statistics we must prove the distribution normality. One way is to compare samples (arrays) of phoneme distribution in the wordlists for different meanings and use some statistics creiteria afterwards, eg. `Shapiro-Wilk test`.
  
  3.3 When normality is proved (really it can not be proved, but can be _not disproved_), we can use statistic tools.
  
#### 4. OUTPUT
  4.1 Firstly, we should see some results (_relative_ rather than _absolute_ indicators). See point 3.1.
  4.2 Secondly, it'd be great for user to see graphics.
  4.3 Finally, the most complicated option is to create a UI using `Java FX` (or `Swing` but it seems to be too old). This UI can combine the `see calculation results` option, `save as` option, `create graphic` on the fly and show it in a modal window.


METHODS
===
***
Class InputFile
------

ArrayList<Word> `getWordList(String)` - reads the whole list by the header from the input `.xlsx` file.

ArrayList<Word> `getAllWordLists()` - reads all the wordlists from the input `.xlsx` file and returns them as one `globalWordList`

Class OutputFile
-----

void `createOutputFile()` - creates an output file in `.xlsx` format with a default name in an output directory. ATTENTION: constructor for OutputFile calls this method automatically.

***


Class Word
------

int `getNumOfPhonemes(String phoneme)` - returns a number of a certain phoneme in the word's transcription

int `countPhonotype(Object object)` - counts and returns a number of a certain ph-type in the word's transcription

ArrayList<Phoneme> `setTranscriptionFromWord()` - takes a word from corresponding field of Word object and creates a transcription from it.
        The result is written into the transcription field automatically.

void `printTranscription()` - prints transcription to console

String `serialize()` - serializes word as a JSON. Can be used for a quick checking in [JSON formatter](https://jsonformatter.curiousconcept.com/)



Class Wordlist
-----

Word `getWord(String language)` - searches through wordlist word of a certain languages. Presume that on list contains only one word for every meaning. Result also is printed in console.
>^^^ //TODO: it's better to return a wordlist cause we can have a `globalWordList` with many words for every language.

String `serialize()` - serializes wordlist to JSON. Can be used for a quick checking in [JSON formatter](https://jsonformatter.curiousconcept.com/)

***


Class SoundsBank
-----
> SoundBank is a singleton class so it creates an instance by calling `getter`
> All the phonemes in bank are hardcoded.

Phoneme `find(String requestedSymbol)` - returns a reference on requested Vowel/Consonant object in a Phoneme type.
        Inverse transformation implements by (Vowel)ph or (Consonant)ph.
        If phoneme is not found in table method returns null.


Class Statistics
-----

HashMap<Object, Integer> `getAllPhonotypes` - returns a list of all ph-types as a key. 
        Value will be filled later with a number of every ph-type instances per list.
        
HashMap<Object, Integer> `countAllPhonotypesPerList()` - counts all ph-types instances per list for further stats counting. 




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
