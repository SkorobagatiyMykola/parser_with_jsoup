package com.skorobagatiy;

import org.jsoup.Jsoup;

public class Main {
    private static String url = "https://leon.bet/ru-ua/";
    //private static String url = "https://sinoptik.ua/";


    public static void main(String[] args) {
        System.out.println("Hello world!");

        try {
            var document = Jsoup.connect(url).get();
            var title = document.selectFirst("title");
            //var elements = document.select("a");
            var elements = document.select(".sport-event-list-sport-headline__label_EJ3tx");

            for (var element : elements) {
                System.out.println(element.attr("href"));
            }
            System.out.println(title.text());

        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("==== THE  END ====");
    }
}