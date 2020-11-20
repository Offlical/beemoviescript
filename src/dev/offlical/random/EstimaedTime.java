package dev.offlical.random;

import java.util.Scanner;

public class EstimaedTime
{

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter amount of codes");
        long codes = s.nextLong();

        long estimatedTime =  (codes * 3250) / 1000;
        System.out.println("Estimaed Time: " + convertToFormat(estimatedTime));
    }


    public static String convertToFormat(long secs) {
        String s = "";
        long hours = 0,mins = 0,secs2 = 0,days = 0;
        if(secs >= 60 && secs < 3600) {
            mins = secs/60;
            secs2 = secs % 60;
        } else if(secs >= 3600 && secs < 86400) {
            secs2 = (secs% 3600)%60;
            mins = (secs%3600)/60;
            hours = secs/3600;
        }else if(secs >= 86400) {
            secs2 = (secs% 3600)%60;
            mins = (secs%3600)/60;
            hours = secs/3600;
            days = hours / 24;
        } else {
            secs2 = secs;
        }

        if(!(days <= 0)) {
            hours = hours - (24 * days);
            s = s.replace(".",", ") + days + " days.";
        }
        if(!(hours <= 0)) {
            s = s.replace(".",", ") + hours + " hours.";
        }
        if(!(mins <= 0)) {
            s = s.replace(".",", ") + mins + " minutes.";
        }
        if(secs2 >= 0)  {
            s = s.replace(".",", ") + secs2 + " seconds.";
        }
        return s.replace(".","");
    }
}
