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
3. Prove the distribution normality.
4. Caluclate statistics.
5. Create some user-friendly output format for results.

### Details

#### 1. INPUT
  `.xlsx` file
  Headers - word meanings like "big", "run", "stone".
  Rows - language names.
  
#### 2. READING
  There are 2 options:
    1. Getting list for a specific meaning. It may be useful for comparing lists "1 to 1".
    2. Getting every word of every language from the input file and places them to `globalWordList`.
    Raw data processing.
    We can count ph-Types `per word` or `per list`. Also we can count `num of words containing ph-Type`. This allows us to count different types of stats: 
  *% of words with ph-Type (per list);
  *% of ph with ph-Type (per list);
  *average ph-Type per word.

#### 3. NORMALITY
  In order to use statistics we must prove the distribution normality. One way is to compare samples (arrays) of phoneme distribution in the wordlists for different meanings and use some statistics creiteria afterwards, eg. `Shapiro-Wilk test`.
  
#### 3. STATISTICS
  Looking up for anomaly in the ph-Types disribution or, rarely, phonemes distribution.
  When normality is proved (really it can not be proved, but can be _not disproved_), we can use statistic tools.
  
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
PUBLIC METHODS
**OutputFile constructor** calls *createOutputFile()* method and depends on *Type* creates General or Normality result file.

void fillWith(WordList wordList) - works both types of OutputFiles. It fills up a file with the proper results of Wordlist processing.

PRIVATE METHODS
void `createOutputFile()` - creates an output file in `.xlsx` format with a default name in an output directory. Method is called by constructor automatically.

void writeToNormalityFile/writeToGeneralFile(WordList wordList) - methods are called by *fillWith* method depends on file *Type*. These methods contain a real logic of writing data to files.

void writeOneKindOfStats(Sheet sh, String dataFormat, HashMap<Object, Double> mapResult) - is called by *writeToNormalityFile* and writes to file results for one kind of statistics.

CellStyle createCellStyleWithDataFormat(String format) - this method solves one of Apache POI problems with adding different styles to cells in a loop or using conditional operator.



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

Word `getWord(String language)` - searches through wordlist word of a certain language. Presume that one list contains only one word for every meaning. Result also is printed in console.
>^^^ //TODO: it's better to return a wordlist cause we can have a `globalWordList` with many words for every language.

void countAllPhonotypes(), int countAllPhonemes() - provides two important results for further usage:
      - Number of all ph-Type/phoneme instances in all the words in list. 
      - Number of all the words with a certain ph-Type.
      Results are written into HashMaps (*mapPhTypesPerList* and *mapWordsPerList*) that are fields of a Wordlist object.
      These results are required for countind basic statistic features.
      
HashMap<Object, Double> getStats(Statistics.KindOfStats kindOfStats) - takes a certain *HashMap* field of Wordlist object depends on *KindOfStats* argument and returns map of pairs "*phType*:number/percent". These results are written into *OutputFile*.

String `serialize()` - serializes wordlist to JSON. Can be used for a quick checking in [JSON formatter](https://jsonformatter.curiousconcept.com/)

***


Class SoundsBank
-----
> SoundBank is a singleton class so it creates an instance by calling `getter`
> All the phonemes in bank are hardcoded.

Phoneme `find(String requestedSymbol)` - returns a reference on requested Vowel/Consonant object in a Phoneme type.
        Inverse transformation implements by (Vowel)ph or (Consonant)ph.
        If phoneme is not found in table method returns null.
        
HashMap<Object, Integer> `getAllPhonotypes` - returns a list of all ph-types as a key. 
        Value will be filled later with a number of every ph-type instances per list.


Class Statistics
-----





        ПЛАНЫ
        
        Мелкие правки
        - Сделать методы createCellStyleWithDataFormat, writeToGeneralFile и ...Normality.. private
        
        
        добавления
        - в inputFile добавить метод, который читает все списки слов из входного файла. Возможный вариант объекта на выходе - ArrayList<Wordlist>. В цикле читаем заголовки, для каждого заголовка вызываем сборщик в Wordlist и складываем их все в ArrayList.
        - в выходной файл может быть внесено условно неограниченное число записей. каждая следующая запись должна идти со смещением вниз, а значит надо запоминать порядковый номер каждой записи - надо завести счетчик. Далее рассчитать смещение для General как три строчки на запись, а для Normality как одна строчка на запись.
        - столбец каждого фонотипа в файле Normality - это выборка размера N, где N соответствует числу meanings (=кол-во записей/строк(исключая заголовки)). По такой выборке можно провести проверку на нормальность. Для таких выборок можно завести класс Sample, где в качестве методов использовать расчеты разных статистических критериев.
        В каждом из таких методов можно ставить проверку на доказательство нормальности, и если нормальность опровергается, проверку не запускать (либо запускать с сообщением о невалидности результатов).
        Что мы ожидаем? Что на фоне нормального распределения для выборки значения для конкретных фонотипов для конкретного Meaning будут выпадать за пределы 1/2/3 сигм.
        
        Крупные правки
        - обезопасить конструктор Wordlist, чтобы нельзя было запихнуть в него слова с разными meaning (подумать про global WL)

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
