# Connect.java #

Open file `wikipedia.sql.Connect.java`.

Add lines:
```
// English Wiktionary database
public final static String ENWIKT_DB = "enwikt20100824?useUnicode=false&characterEncoding=ISO8859_1&autoReconnect=true&useUnbufferedInput=false";

// English Wiktionary parsed database
public final static String ENWIKT_PARSED_DB = "enwikt20100824_parsed?useUnicode=false&characterEncoding=ISO8859_1&autoReconnect=true&useUnbufferedInput=false";
```

# PageTableAll.java #

Check the file [PageTableAll.java](http://code.google.com/p/wikokit/source/browse/trunk/wikt_parser/src/wikt/parser/PageTableAll.java).

This line:
```
DEBUG_PAGES = true;
```
should be changed to:
```
DEBUG_PAGES = false;
```

# wikt\_parser #

  * Set **wikt\_parser** as a main project in NetBeans.
  * Compile it.
  * Open project properties / Run. Set:
    * Main class: **`wikt.parser.Main`**
    * Arguments: **`en 0`**
    * VM options variant 1: `-Xms1212m -Xmx1212m -Xmn16m -XX:+DisableExplicitGC -verbose:gc`
    * VM options variant 2: `-Xms1212m -Xmx1212m -Xmn16m -XX:+DisableExplicitGC  -XX:PermSize=256m`
  * Run main project (test that parser works without error).

# Run parser #

## Running parser in first time ##

Create directory for the parser, copy project files, run .bat file:
```
$mkdir /enwikt20100824/wikt_parser

wikt_parser/run_wikt_parser.bat
wikt_parser/dist/wikt_parser.jar
wikt_parser/dist/lib/common_wiki.jar
wikt_parser/dist/lib/common_wiki_jdbc.jar
wikt_parser/dist/lib/mysql-connector-java-5.1.25-bin.jar

mysql$ DROP DATABASE enwikt20100824_parsed;
mysql$ CREATE DATABASE enwikt20100824_parsed;
mysql$ USE enwikt20100824_parsed
mysql$ SOURCE D:\all\projects\java\synonyms\wikokit\wikt_parser\doc\wikt_parsed_empty.sql
```

Read twice very attentively parameters of your MySQL config file (tune your server to make it as fast as possible):
```
NET STOP MySQL
gvim "C:\w\MySQL\MySQL Server 5.1\my.ini"
NET START MySQL
```

Check character set by MySQL command "status" (change file `my.ini`):
```
Server characterset:    latin1
Db     characterset:    latin1
```

OK. Run it:
```
$cd /enwikt20100824/wikt_parser
$run_wikt_parser.bat en 0
```

## Running parser in ... time ##

If/When the parser got an error and failed, you can continue from the last treated page of the source Wiktionary database. You should open log-file located in the same directory as `run_wikt_parser.bat`.

Go to the end of the generated log-file, find the line starting from the figure, e.g.:
```
424000: some_word_from_Wiktionary, duration: 199 min, remain: 27 min
```

Ok, you can continue parsing from the record 42400 in the table 'page'.
  1. Increment the name of the log-file in `run_wikt_parser.bat`, e.g. enwikt20100824\_parsed\_02.log -> enwikt20100824\_parsed\_03.log
  1. run .bat-file with new parameter
```
$run_wikt_parser.bat en 424000
```

Attention: if you want to run parser from zero (from the very beginning) then recreate Wiktionary parsed database (see "DROP DATABASE" above).

Attention: if you are running the wikt\_parser as a main project in NetBeans, then (1) see NetBeans output buffer instead of log-file, (2) change "Arguments: **`en 0`**" to "Arguments: **`en 424000`".**

# Analyze MySQL slow requests log file #

Dump result Wiktionary parsed database, then:
```
NET STOP mysql     % stop MySQL server
cd "D:\w\MySQL\MySQL Server 5.1\log\mysqld.slow.log" 
rename mysqld.slow.log mysqld.slow.log_enwikt20111008.log
gvim mysqld.slow.log_enwikt20111008.log
```

Sort lines in VIM:
```
gvim$:sort u
```

Analyze requests and try to find the most common and useless repetitive requests, then change Java source code in order to avoid such requests.

# Previous step #
  * [Load empty Wiktionary parsed database into MySQL](File_wikt_parsed_empty_sql.md)

# Next step #
  * [Convert the Wiktionary parsed database (MySQL) into SQLite file](SQLite.md)