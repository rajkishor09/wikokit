
package wikokit.base.wikt.multi.en;

import wikokit.base.wikt.multi.en.WEtymologyEn;
import wikokit.base.wikt.multi.en.WPOSEn;
import wikokit.base.wikipedia.language.LanguageType;
import wikokit.base.wikt.constant.POS;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import wikokit.base.wikt.util.LangText;
import wikokit.base.wikt.util.POSText;

public class WPOSEnTest {

    public WPOSEnTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSplitToPOSSections() {
        System.out.println("splitToPOSSections");
        String s1, s2, s3, s4, s1_result, s2_result;

        String source_text, result1, result2;
        LangText source_lt;
        LangText[] etymology_sections;
        POSText[] result;
        String page_title;

        // case -1: empty => unknown
        //
        // ===Verb===
        source_text = "===It's not a Part Of Speech Name===\n" +
                      "text which do not describe POS";
        source_lt = new LangText(LanguageType.en);
        source_lt.text = new StringBuffer(source_text);

        page_title = "pos_en_word-1";
        etymology_sections = WEtymologyEn.splitToEtymologySections(page_title, source_lt);
        result = WPOSEn.splitToPOSSections(page_title, etymology_sections);

        assertEquals(1, result.length);
        assertEquals(POS.unknown, result[0].getPOSType());
        assertTrue(result[0].getText().toString().equalsIgnoreCase(source_text));

        // case 0: verb
        //
        // ===Verb===
        source_text = "===Verb===\n" +
                      "===It's not a Part Of Speech Name===";

        source_lt = new LangText(LanguageType.en);
        source_lt.text = new StringBuffer(source_text);

        page_title = "pos_en_word0";
        etymology_sections = WEtymologyEn.splitToEtymologySections(page_title, source_lt);
        result = WPOSEn.splitToPOSSections(page_title, etymology_sections);

        assertEquals(1, result.length);
        assertEquals(POS.verb, result[0].getPOSType());
        assertTrue(result[0].getText().toString().equalsIgnoreCase("===It's not a Part Of Speech Name==="));

        // case 1: noun and verb
        //
        // ===Noun===
        // {{en-noun}}
        // ===Verb===
        s1 = "===Noun===\n" +
             "{{en-noun}}\n";
        s2 = "===Verb===";
        source_text = s1 + s2;
        
        s1_result = "{{en-noun}}\n";
        s2_result = "";

        source_lt = new LangText(LanguageType.en);
        source_lt.text = new StringBuffer(source_text);

        page_title = "pos_en_word1";
        etymology_sections = WEtymologyEn.splitToEtymologySections(page_title, source_lt);
        result = WPOSEn.splitToPOSSections(page_title, etymology_sections);
       
        assertEquals(2, result.length);
        assertEquals(POS.noun, result[0].getPOSType());
        assertEquals(POS.verb, result[1].getPOSType());
        assertTrue(result[0].getText().toString().equalsIgnoreCase(s1_result));
        assertTrue(result[1].getText().toString().equalsIgnoreCase(s2_result));

        // todo case with two etymologies
        // case 2: noun and verb
        //
        // ===Etymology 1===
        // ====Pronunciation====
        // ====Noun====
        // {{en-noun}}
        // ====Usage notes====
        // ====Synonyms====
        //
        // ===Verb===
        // ===Etymology 2===
        // ====Pronunciation====
        // ====Noun====
        // ====Verb====
        // Conjugation

        s1 = "===Etymology 1===\n" +
             "====Pronunciation====\n" +
             "====Noun====\n";
        s2 = "{{en-noun}}\n" +
             "====Usage notes====\n" +
             "====Synonyms====\n" +
             "\n";
        s3 = "===Verb===\n" +
             "===Etymology 2===\n" +
             "====Pronunciation====\n" +
             "====Noun====\n" +
             "====Verb====\n";
        s4 = "Conjugation\n" +
             "\n";

        source_text = s1 + s2 + s3 + s4;
        
        source_lt = new LangText(LanguageType.en);
        source_lt.text = new StringBuffer(source_text);

        page_title = "pos_en_word3";
        etymology_sections = WEtymologyEn.splitToEtymologySections(page_title, source_lt);
        result = WPOSEn.splitToPOSSections(page_title, etymology_sections);

        assertEquals(4, result.length);
        assertEquals(POS.noun, result[0].getPOSType());
        assertEquals(POS.verb, result[1].getPOSType());
        assertEquals(POS.noun, result[2].getPOSType());
        assertEquals(POS.verb, result[3].getPOSType());
        
        assertTrue(result[0].getText().toString().equalsIgnoreCase(s2));
        assertTrue(result[1].getText().toString().equalsIgnoreCase(""));

        assertTrue(result[2].getText().toString().equalsIgnoreCase(""));
        assertTrue(result[3].getText().toString().equalsIgnoreCase(s4));
    }

    @Test
    public void testSplitToPOSSections_phrase() {
        System.out.println("splitToPOSSections_phrase");
        String s1, s2, s3, s4, s1_result, s2_result;

        String source_text, result1, result2;
        LangText source_lt;
        LangText[] etymology_sections;
        POSText[] result;
        String page_title;

        //==Swedish==
        //===Phrase===
        s1 = "===Phrase===\n";
        s2 = "'''[[var]] [[är]] [[toaletten]]?'''\n";
        s3 = "# [[where is the toilet]]?";
        source_text = s1 + s2 + s3;

        s1_result = s2 + s3;

        source_lt = new LangText(LanguageType.sw);
        source_lt.text = new StringBuffer(source_text);

        page_title = "pos_en_word1";
        etymology_sections = WEtymologyEn.splitToEtymologySections(page_title, source_lt);
        result = WPOSEn.splitToPOSSections(page_title, etymology_sections);

        assertEquals(1, result.length);
        assertEquals(POS.phrase, result[0].getPOSType());
        assertTrue(result[0].getText().toString().equalsIgnoreCase(s1_result));
    }

    // Adjective
    //
    // Other headers in use (http://en.wiktionary.org/wiki/Wiktionary:Entry_layout_explained/POS_headers#Other_headers_in_use)
    // Adjectival noun 	な-Adjectives 	Japanese "quasi-adjective", probably should be Adjective.
    // Quasi-adjective 	な-Adjectives 	Japanese, probably should be Adjective.
    @Test
    public void testSplitToPOSSections_adjective() {
        System.out.println("splitToPOSSections_adjective");
        String s1, s2, s3, s4, s5, s6, s1_result, s2_result, s3_result;

        String source_text, result1, result2;
        LangText source_lt;
        LangText[] etymology_sections;
        POSText[] result;
        String page_title;

        s1 = "===Adjective===\n";
        s2 = "{{infl|lv|adjective}}\n";
        s3 = "===Adjectival noun===\n";
        s4 = "some text\n";
        s5 = "===Quasi-adjective===\n";
        s6 = "some text2";
        source_text = s1 + s2 + s3 + s4 + s5 + s6;

        s1_result = s2;
        s2_result = s4;
        s3_result = s6;

        source_lt = new LangText(LanguageType.sw);
        source_lt.text = new StringBuffer(source_text);

        page_title = "pos_en_word1";
        etymology_sections = WEtymologyEn.splitToEtymologySections(page_title, source_lt);
        result = WPOSEn.splitToPOSSections(page_title, etymology_sections);

        assertEquals(3, result.length);
        assertEquals(POS.adjective, result[0].getPOSType());
        assertEquals(POS.adjective, result[1].getPOSType());
        assertEquals(POS.adjective, result[2].getPOSType());
        assertTrue(result[0].getText().toString().equalsIgnoreCase(s1_result));
        assertTrue(result[1].getText().toString().equalsIgnoreCase(s2_result));
        assertTrue(result[2].getText().toString().equalsIgnoreCase(s3_result));
    }

    // POS of foreign words in English Wiktionary.
    @Test
    public void testSplitToPOSSections_foreign() {
        System.out.println("splitToPOSSections_foreign");
        String s1, s2, s1_result, s2_result;
        
        String source_text;
        LangText source_lt;
        LangText[] etymology_sections;
        POSText[] result;
        String page_title;

        // case 1: "Verb form" and "Participle" in Russian word article
        //
        s1 = "===Verb form===\n" +
             "'''испо́льзуем''' (ispól'zujem)\n";
        s2 = "===Participle===\n" +
             "# [[is used]]";
        source_text = s1 + s2;

        s1_result = "'''испо́льзуем''' (ispól'zujem)\n";
        s2_result = "# [[is used]]";

        source_lt = new LangText(LanguageType.ru);
        source_lt.text = new StringBuffer(source_text);

        page_title = "pos_ru_word-1";
        etymology_sections = WEtymologyEn.splitToEtymologySections(page_title, source_lt);
        result = WPOSEn.splitToPOSSections(page_title, etymology_sections);

        assertEquals(2, result.length);
        assertEquals(POS.verb, result[0].getPOSType());
        assertTrue(result[0].getText().toString().equalsIgnoreCase(s1_result));
        
        assertEquals(POS.verb, result[0].getPOSType());
        assertTrue(result[1].getText().toString().equalsIgnoreCase(s2_result));
    }

    // POS of foreign words in English Wiktionary.
    @Test
    public void testSplitToPOSSections_onePOS() {
        System.out.println("splitToPOSSections_onePOS");
        String s1, s1_result;

        LangText source_lt;
        LangText[] etymology_sections;
        POSText[] result;
        String page_title;

        // case 1: "Verb form" and "Participle" in Russian word article
        //
        s1 = "===Verb form===\n" +
             "'''испо́льзуем''' (ispól'zujem)\n";

        s1_result = "'''испо́льзуем''' (ispól'zujem)\n";

        source_lt = new LangText(LanguageType.ru);
        source_lt.text = new StringBuffer(s1);

        page_title = "pos_ru_word-1";
        etymology_sections = WEtymologyEn.splitToEtymologySections(page_title, source_lt);
        result = WPOSEn.splitToPOSSections(page_title, etymology_sections);

        assertEquals(1, result.length);
        assertEquals(POS.verb, result[0].getPOSType());
        assertTrue(result[0].getText().toString().equalsIgnoreCase(s1_result));
    }
    
    // One POS. Tests sub-function cutHeaderFromAlonePOSSection.
    @Test
    public void testSplitToPOSSections_onePOS_cutHeaderFromAlonePOSSection() {
        System.out.println("splitToPOSSections_onePOS_cutHeaderFromAlonePOSSection");
        String s_header, s_noun, source_text;
        LangText source_lt;
        LangText[] etymology_sections;
        POSText[] result;
        String page_title;

        s_header = "[[Image:Ryanair.arp.750pix.jpg|thumb|right|250 px|Boeing 737 airplane.]]\n" +
                "===Etymology===\n" +
                "From {{term|aeroplane||lang=en}}\n" +
                "\n" +
                "===Pronunciation===\n" +
                "* {{audio|en-us-airplane.ogg|Audio (US)}}\n" +
                "\n" +
                "===Noun===\n";

        s_noun = "{{en-noun}}\n" +
                "# {{US}} A powered heavier-than air [[aircraft]] with fixed [[wing]]s.\n" +
                "\n" +
                "====Synonyms====\n" +
                "* [[aeroplane]].\n" +
                "\n" +
                "\n";

        source_text = s_header + s_noun;

        source_lt = new LangText(LanguageType.en);
        source_lt.text = new StringBuffer(source_text);

        page_title = "pos_word-1";
        etymology_sections = WEtymologyEn.splitToEtymologySections(page_title, source_lt);
        result = WPOSEn.splitToPOSSections(page_title, etymology_sections);

        assertEquals(1, result.length);
        assertEquals(POS.noun, result[0].getPOSType());

        System.out.println("pos_section="+result[0].getText().toString());

        assertTrue(result[0].getText().toString().equalsIgnoreCase(s_noun));
    }



}