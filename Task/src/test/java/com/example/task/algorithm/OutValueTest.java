package com.example.task.algorithm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutValueTest {
    OutValue outValue = new OutValue();
    @Test
    void procentTest1() {

        assertEquals("33,33%",outValue.outerValueAlg("губка меч енот", "меч еж"));
    }

    @Test
    void procentTest2() {
        assertEquals("0%",outValue.outerValueAlg("губка меч енот", ""));
    }
    @Test
    void procentTest3() {
        assertEquals("0%",outValue.outerValueAlg("", "нож"));
    }
    @Test
    void procentTest4() {
        assertEquals("100%",outValue.outerValueAlg("", ""));
    }
    @Test
    void procentTest5() {
        assertEquals("100%",outValue.outerValueAlg(",", ","));
    }
    @Test
    void procentTest6() {
        assertEquals("100%",outValue.outerValueAlg("карл корал украл кларнет",
                "карл корал украл кларнет карл корал украл кларнет"));
    }
    @Test
    void procentTest7() {
        assertEquals("22,22%",outValue.outerValueAlg("карл корал украл кларнет автобус мышь дельфин пес квас",
                "карл кларнет"));
    }
    @Test
    void procentTest9() {
        assertEquals("42,86%",outValue.outerValueAlg("карл корал украл кларнет автобус мышь дельфин",
                "карл кларнет украл"));
    }
    @Test
    void procentTest10() {
        assertEquals("100%",outValue.outerValueAlg("автобус автобус автобус автобус автобус",
                "автобус"));
    }



}