# Russian Wiktionary, dump 20090122 #

Source database: ruwikt20090122, see [Database dump progress](http://download.wikimedia.org/backup-index.html)

Total pages in source database: 319741

Result database:
  1. [ruwikt20090122\_parsed](http://code.google.com/p/wikokit/downloads/detail?name=ruwikt20090122_parsed.zip)
  1. [ruwikt20090122\_parsed\_with\_translation](http://code.google.com/p/wikokit/downloads/detail?name=ruwikt20090122_parsed_with_translation.zip)

## Parsing ##

### ruwikt20090122\_parsed ###

Time 5183.856 sec = 86 min

Problems: it works only with if you have enough RAM. It is memory leak, I guess, so it fails if you give less than 507 MByte:

GC 507625K->494275K(522688K), 0.1761469 secs

-Xms512m -Xmx512m -Xmn16m -XX:+DisableExplicitGC -verbose:gc

### ruwikt20090122\_parsed\_with\_translation ###

Time 18271.756 sec = 305 min

## Parsed database ##

See database layout in [File\_wikt\_parsed\_empty\_sql](File_wikt_parsed_empty_sql.md).

There are the following results (name of table, size of table, and my comment).

| **Table** | **Size1** | **Size2** | **Table description** | **Comment** |
|:----------|:----------|:----------|:----------------------|:------------|
| page | 334,306  | 391,141 |  | > 319741 pages in source database!? Thanks to red links, i.e. wikification? |
| relation | 67,250 | 67,296 | number of semantic relations, e.g. synonyms, antonyms, etc. |  |
| lang\_pos | 247,408 | 247,580 | number of pairs: language & part of speech, one Wiktionary page can contain several such pairs. | Why it is less than number of pages?! |
| wiki\_text | 85,728 | 163,570 | number of meaning definitions + number of semantic relations phrases (divided by comma, semicolon) + number of wikified translations |  |
| wiki\_text\_words | 102,107 | 186,556 | number of wikified words (in meaning definitions + in semantic relations + in translations) |  |
| meaning | 177,158 | 177,365 | number of meanings, one word can have several meanings |  |
| inflection | 13,110 | 14,906 | number of unique inflectional wordforms | It is extracted from wikified word definitions |
| translation | 0 | 33,800 | number of translation section boxes (in best case: one translation box corresponds to one meaning) | in practice: 35 009 < 177 158 |
| translation\_entry | 0 | 504,919 | number of different translations (pairs of translations) |  |

**Size1** is a statistics of the database ruwikt20090122\_parsed, and **Size2** - ruwikt20090122\_parsed\_with\_translation.

Size of the table translation\_entry is a number of different translations (from Russian into all languages). E.g. the article [спутник](http://ru.wiktionary.org/wiki/%D1%81%D0%BF%D1%83%D1%82%D0%BD%D0%B8%D0%BA) contains English translation of first meaning: "companion; fellow traveler (fellow traveller)". Then 3 records should be added into the table:
  1. companion
  1. fellow traveler
  1. fellow traveller

## Structure ##

The structure of the article in Russian Wiktionary
is more or less strictly defined and formalized, see [Викисловарь:Правила оформления статей](http://ru.wiktionary.org/wiki/%D0%92%D0%B8%D0%BA%D0%B8%D1%81%D0%BB%D0%BE%D0%B2%D0%B0%D1%80%D1%8C:%D0%9F%D1%80%D0%B0%D0%B2%D0%B8%D0%BB%D0%B0_%D0%BE%D1%84%D0%BE%D1%80%D0%BC%D0%BB%D0%B5%D0%BD%D0%B8%D1%8F_%D1%81%D1%82%D0%B0%D1%82%D0%B5%D0%B9).

There are some difference with structure of the article
in English Wiktionary, see [Wiktionary:Entry layout explained](http://en.wiktionary.org/wiki/Wiktionary:Entry_layout_explained).

### ruwikt20090122\_parsed ###

The following sections of articles from Russian Wiktionary have been parsed:
  * meaning (definition) (without quotations, labels)
  * semantic relations

### ruwikt20090122\_parsed\_with\_translation ###

An additional section "translation" has been parsed.

## See also ##
  * [SQLWiktParsedPhantasmagoria](SQLWiktParsedPhantasmagoria.md)
  * [Parameters of the created (parsed) Wiktionary database](http://en.wiktionary.org/wiki/User:AKA_MBG/Statistics:Parameters_of_the_database_created_by_the_Wiktionary_parser) // **English** Wiktionary
  * [Parameters of the created (parsed) Wiktionary database](http://ru.wiktionary.org/wiki/%D0%A3%D1%87%D0%B0%D1%81%D1%82%D0%BD%D0%B8%D0%BA:AKA_MBG/%D0%A1%D1%82%D0%B0%D1%82%D0%B8%D1%81%D1%82%D0%B8%D0%BA%D0%B0:%D0%A0%D0%B0%D0%B7%D0%BC%D0%B5%D1%80%D1%8B_%D0%B1%D0%B0%D0%B7%D1%8B_%D0%B4%D0%B0%D0%BD%D0%BD%D1%8B%D1%85,_%D1%81%D0%BE%D0%B7%D0%B4%D0%B0%D0%BD%D0%BD%D0%BE%D0%B9_%D0%BF%D0%B0%D1%80%D1%81%D0%B5%D1%80%D0%BE%D0%BC_%D0%92%D0%B8%D0%BA%D0%B8%D1%81%D0%BB%D0%BE%D0%B2%D0%B0%D1%80%D1%8F) // **Russian** Wiktionary