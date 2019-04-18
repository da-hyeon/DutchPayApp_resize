package com.dutch.hdh.dutchpayapp.util;


import android.util.Log;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormatUtil {
    public static String getTime(int second, TimeFormatType formatType) {
        int minute = second / 60;
        int hour = minute / 60;
        String time = "";

        if (formatType == TimeFormatType.HOUR_MINUTE5) {
            if (hour > 0) {
                time = String.format(TimeFormatType.HOUR_MINUTE5.getFormat1(), hour, minute % 60);
            } else {
                time = String.format(TimeFormatType.MINUTE.getFormat1(), minute % 60);
            }
        } else {
            time = String.format(TimeFormatType.MINUTE.getFormat1(), minute % 60);
        }

        return time;
    }

    public static String getCurrentTime(long time, TimeFormatType formatType) {
        Date newDate = new Date(time);
        String format = formatType.getFormat2();

        SimpleDateFormat sdf = new SimpleDateFormat(format);

        return sdf.format(newDate);
    }

    public static int getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String now = year + getLongNumberFormat(month, 2) + getLongNumberFormat(day, 2);

        return Integer.parseInt(now);
    }

    public static String getMoney(int value) {
        return setComma(value) + "원";
    }

    public static String getDistanceKillometers(int value) {
        return setComma(value);
    }

    public static String getDistanceKillometers(double value) {
        return setComma(value);
    }

    public static String getDistanceMetersToKillometers(int value) {
        double killo = value / (double) 1000;
        return getDistanceKillometers(killo);
    }

    public static String setBetweenWholeWord(String word, String bet) {
        if (word == null) {
            word = "";
        }

        String[] words = new String[word.length()];

        for (int i = 0; i < words.length; i++) {
            words[i] = String.valueOf(word.charAt(i));
        }

        return setBetweenWord(words, bet);
    }

    public static String setBetweenWord(String[] word, String bet) {
        if (word == null || word.length == 0) {
            return "";
        }

        if (bet == null) {
            bet = "";
        }

        String newWord = "";
        for (int i = 0; i < word.length; i++) {
            String w = word[i];

            if (newWord.isEmpty()) {
                newWord = w;
            } else {
                newWord += bet + w;
            }
        }

        return newWord;
    }

    public static String setComma(int value) {
        DecimalFormat df = new DecimalFormat("#,##0");
        return df.format(value);
    }

    public static String setComma(double value) {
        DecimalFormat df = new DecimalFormat("#,##0.0");
        return df.format(value);
    }

    //숫자, 자릿 수 (ex - 3 ==> 003)
    public static String getLongNumberFormat(int num, int digit) {
        if (digit < 1) {
            return String.valueOf(num);
        }

        String format = "";
        for (int i = 0; i < digit; i++) {
            format += "0";
        }

        DecimalFormat df = new DecimalFormat(format);
        return df.format(num);
    }

    public static String getHyphenCardMasking(String card) {
        if (card.length() == 16) {
            card = card.substring(0, 4) + "-" + card.substring(4, 8).replace(card.substring(4, 8),"****") + "-" + card.substring(8, 12).replace(card.substring(8, 12),"****") + "-" + card.substring(12);
        }
        return card;
    }

    public enum TimeFormatType {
        HOUR(0, "%d시", "HH시"), HOUR2(1, "%d시", "HH"), MINUTE(2, "%d분", "mm분"), HOUR_MINUTE1(3, "%d시 %d분", "HH시 mm분"), HOUR_MINUTE2(4, "%d:%d", "HH:mm"), HOUR_MINUTE3(5, "%d:%d", "a hh:mm"), HOUR_MINUTE4(6, "%d:%d", "MM-dd a hh:mm"), HOUR_MINUTE5(7, "%d시간 %d분", "hh시간 m분"), HOUR_MINUTE6(8, "%d:%d", "dd일 HH:mm"), HOUR_MINUTE7(9, "%d:%d", "M월 d일 HH시 mm분"), DATE1(10, "%d:%d", "yyyyMMdd"), DATE2(11, "%d:%d", "yyyy.MM.dd"), DATE3(12, "%d:%d", "yyyyMM"), DATE4(13, "%d:%d", "yyyy-MM-dd");

        private int mIndex;
        private String mFormat1;
        private String mFormat2;

        TimeFormatType(int index, String format1, String format2) {
            this.mIndex = index;
            this.mFormat1 = format1;
            this.mFormat2 = format2;
        }

        public int getIndex() {
            return mIndex;
        }

        public String getFormat1() {
            return mFormat1;
        }

        public String getFormat2() {
            return mFormat2;
        }
    }
}
