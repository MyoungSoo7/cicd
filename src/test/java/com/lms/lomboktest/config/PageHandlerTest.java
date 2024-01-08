package com.lms.lomboktest.config;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class PageHandlerTest {

    @Test
    void DecimalFormatNumberTest(){
        DecimalFormat df  = new DecimalFormat("#,###.##");
        DecimalFormat df2 = new DecimalFormat("#.###E0");

        try {
            Number num = df.parse("1,234,567.89");
            System.out.print("1,234,567.89" + " -> ");

            double d = num.doubleValue();
            System.out.print(d + " -> ");

            System.out.println(df2.format(num));
        } catch(Exception e) {}

    }

 /*   @Test
    void ThreadTest(){


        for(int i=10; i > 0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);  // 1초 동안 잠을 잔다.
            } catch(Exception e ) {}
        }
    }*/



    @Test
    void DateFromatTest(){
        DateFormat df  = new SimpleDateFormat("yyyy년 MM월 dd일");
        DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");

        try {
            Date d = df.parse("2019년 11월 23일");
            System.out.println(df2.format(d));
            System.out.println(df.format(d));
        } catch(Exception e) {}
    }

    @Test
    void patternTest(){
        double number  = 1234567.89;
        String[] pattern = {
                "0",
                "#",
                "0.0",
                "#.#",
                "0000000000.0000",
                "##########.####",
                "#.#-",
                "-#.#",
                "#,###.##",
                "#,####.##",
                "#E0",
                "0E0",
                "##E0",
                "00E0",
                "####E0",
                "0000E0",
                "#.#E0",
                "0.0E0",
                "0.000000000E0",
                "00.00000000E0",
                "000.0000000E0",
                "#.#########E0",
                "##.########E0",
                "###.#######E0",
                "#,###.##+;#,###.##-",
                "#.#%",
                "#.#\u2030",
                "\u00A4 #,###",
                "'#'#,###",
                "''#,###",
        };

        for(int i=0; i < pattern.length; i++) {
            DecimalFormat df = new DecimalFormat(pattern[i]);
            System.out.printf("%19s : %s\n",pattern[i], df.format(number));
        }
    }

}