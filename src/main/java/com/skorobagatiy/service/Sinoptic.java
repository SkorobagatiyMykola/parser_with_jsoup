package com.skorobagatiy.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Sinoptic {
    private static final String url = "https://ua.sinoptik.ua/";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {
        //System.out.println(getPage());

        // System.out.println("  Ніч    Ранок    День   Вечір");
        int COUNT = 45;
        // System.out.println("  Температура    Тиск    Вологість   Вітер");
        Document page = getPage();
        Element tab = page.select("#blockDays").first();
        Elements date = tab.select(".main ");

        Elements fullDayInfo = page.select(".tabsContent");


        printDate(date);

        System.out.println("==================================");
    }

    private static Document getPage() {
        Document page;
        try {
            page = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return page;
    }

    private static void printDate(Elements daysInfo) {
        String dateMainFormat = "%s %s, %-10s - %-45s; температура %s %s";
        String dateMainFormat2 = "%s %s, %-19s - %-45s; температура %s %s";

        for (Element el : daysInfo) {

            String dayNumber = el.select(".date ").text();
            String dayMonth = el.select(".month ").text();
            String dayWeek;

            boolean dateFree = el.select("p.date.dateFree").hasText();

            if (dateFree) {
                dayWeek = ANSI_RED + el.select(".day-link").text() + ANSI_RESET;
               // System.out.println(dayWeek.length());

            } else
                dayWeek = el.select(".day-link").text();

            String weather = el.select(".weatherIco ").attr("title").toString();

            Element temp = el.select(".temperature").first();
            Element min = temp.select(".min").first();
            Element max = temp.select(".max").first();
            String minTemp = min.select("span").text();
            String maxTemp = max.select("span").text();

            //Elements partOfDay = temp.select(".tabsContent");
            Elements partOfDay = temp.select("#bd1c");

            //String line = String.format(dateMainFormat, dayNumber, dayMonth, dayWeek, weather, minTemp, maxTemp);
            String line = dateFree ? String.format(dateMainFormat2, dayNumber, dayMonth, dayWeek, weather, minTemp, maxTemp) :
                    String.format(dateMainFormat, dayNumber, dayMonth, dayWeek, weather, minTemp, maxTemp) ;
            System.out.println(line);
        }

    }

}