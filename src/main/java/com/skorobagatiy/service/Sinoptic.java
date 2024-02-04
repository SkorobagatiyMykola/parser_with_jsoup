package com.skorobagatiy.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Sinoptic {
    private static final String url = "https://ua.sinoptik.ua/";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        //System.out.println(getPage());

        // System.out.println("  Ніч    Ранок    День   Вечір");
        int COUNT =45;
       // System.out.println("  Температура    Тиск    Вологість   Вітер");
        Document page = getPage();
        //Element tabl = page.select("div.tabs").first();
        Element tabl = page.select("#blockDays").first();

        //Elements date = tabl.select(".date ");

        Elements date = tabl.select(".main ");

        printDate(date);


        Elements months = tabl.select(".month");
        Elements months2 = tabl.select("p[month]");
        Elements temperature = tabl.select(".temperature");
//        Elements temperature2 = tabl.select("div[temperature]");

       // System.out.println(date.size());
        int z=2;

//        for (int i = 1; i <=7 ; i++) {
//            String str = "bd"+i;
//            tabl.
//
//        }

        // css query language

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

    private static void printDate(Elements list){

        for (Element el: list) {
            String day_week = el.select(".day-link").text();
            System.out.println(day_week);
            String date_number = el.select(".date ").text();
            //System.out.println(date_number);

        }

    }

}