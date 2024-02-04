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

    private static void printDate(Elements list) {
        String dateMainFormat = "%s %s (%s) %s, температура %s %s";

        for (Element el : list) {

            String dayNumber = el.select(".date ").text();
            String dayMonth = el.select(".month ").text();
            String dayWeek;

            if (el.select("p.date.dateFree").hasText())
                dayWeek = ANSI_RED + el.select(".day-link").text() + ANSI_RESET;
            else
                dayWeek = el.select(".day-link").text();

            String weather = el.select(".weatherIco ").attr("title").toString();

            Element temp = el.select(".temperature").first();
            Element min = temp.select(".min").first();
            Element max = temp.select(".max").first();
            String minTemp = min.select("span").text();
            String maxTemp = max.select("span").text();

            String line = String.format(dateMainFormat, dayNumber, dayMonth, dayWeek, weather, minTemp, maxTemp);
            System.out.println(line);
        }

    }

}