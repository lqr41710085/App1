package com.example.javaproject;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class MyClass {
    public static void main(String[] args) {
        Document doc= null;
        try {
            String url="https://www.usd-cny.com/bankofchina.htm";
            doc = Jsoup.connect(url).get();
            System.out.println("hhhhhdoc:"+doc.title());
            Elements tables= doc.getElementsByTag("table");
            Element table=tables.get(0);
            Elements tds=table.getElementsByTag("td");
            for(int i=0;i<tds.size();i+=6){
                Element td1=tds.get(i);
                Element td2=tds.get(i+5);
                String name=td1.text();
                String rate=td2.text();
                if(name.equals("美元")||name.equals("日元")||name.equals("欧元")||name.equals("港币")){
                    float v=100f/Float.parseFloat(rate);
                    System.out.println(name+"hhhhh: "+v+"\n");

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
