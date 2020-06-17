package com.keeppeng.regex;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regexDemo {
    public static void main(String[] args) {
        //testOne();
        Pattern pattern = Pattern.compile("([012][0-9])\\:(\\d{2})\\:(\\d{2})");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                    String format = simpleDateFormat.format(date);
                    Matcher matcher = pattern.matcher(format);
                    if (matcher.matches()) {
                        System.out.print("Hour:"+matcher.group(1));
                        System.out.print("Mintue:"+matcher.group(2));
                        System.out.println("Secound:"+matcher.group(3));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else System.err.println("error");

                }
            }
        });
        thread.start();
        try {
            thread.sleep(1000);//小写的对象/大写的为静态类
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void testOne() {
        Pattern pattern = Pattern.compile("(\\d{3,4})\\-(\\d{7,8})");
        Matcher matcher = pattern.matcher("010-11224455");
        if (matcher.matches()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        } else System.out.println("error");
    }
}
