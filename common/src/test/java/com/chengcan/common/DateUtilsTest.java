package com.chengcan.common;

import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilsTest {

    @org.junit.Test
    public void format() {
        String dataString = DateUtils.format(new Date(1602384902591L),"yyyy-MM-dd");
        assertEquals(dataString, "2020-10-11");
    }

    @org.junit.Test
    public void parse() {
        try {
            Date date = DateUtils.parse("2020-10-11 00:00:00","yyyy-MM-dd HH:mm:ss");
            assertEquals(date.getTime(), 1602345600000L);
        }catch (Exception e){}

    }
}