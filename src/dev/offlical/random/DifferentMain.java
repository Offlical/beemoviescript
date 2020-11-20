package dev.offlical.random;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DifferentMain {
    public static Random r = new Random();
    public static long typingTime = 0;
    public static int generated = 0;
    private static int codes;

    public static void main(String[] args) throws IOException, InterruptedException, AWTException, IllegalAccessException {
        File file = new File("C:\\Users\\Daniel\\Desktop\\codes.txt");
        if (!file.exists()) file.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        Long typeTime;

        System.out.println(convertToFormat(26) + "aaa");

        codes = 250;

        NitroThread nitroThread = new NitroThread();
        nitroThread.run();

/*
        Thread.sleep(3000);
        System.out.println("Starting!");
        int estimatedTime =  (codes * 2250) / 1000;

        pressString("Attempting to generate " + codes + " codes, Estimated time til finish: " + convertToFormat(estimatedTime));

        Thread.sleep(5000);
        Long time = System.currentTimeMillis();

        String loop = "";
        for (int l = 0; l < codes; l++) {

            for (int i = 0; i < 16; i++) {
                int random = r.nextInt(2);
                if (random == 0) loop += uppercasepass();
                if (random == 1) loop += lowercasepass();
            }
            generated++;
            typeTime = System.currentTimeMillis();
            pressString(generated + " https://discord.gift/" +loop);
            typingTime += (System.currentTimeMillis() - typeTime);
            System.out.println("Code #" + generated + " typed in " + (System.currentTimeMillis() - typeTime) + " ms!");
            Thread.sleep(1500);
            loop = "";

        }
        time = System.currentTimeMillis() - time;
        int secs = (int) (time / 1000);
        pressString("Generated " + codes + " codes, Took " + (time) + "ms to generate and write, or in basic human terms: " + convertToFormat(secs) + ".");
        System.out.println(secs);
        pressString("Average of " + (typingTime / codes) + "ms to type each code. Made by Offlical" );
*/
    }


    public static String uppercasepass() {
        String[] abc = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        String passletter = "";
        int rN3 = 0;
        rN3 = r.nextInt(26);
        passletter = abc[rN3];


        return passletter;
        // uppercase() end
    }

    public static String lowercasepass() {
        String passletter = "";
        int rN3 = 0;
        rN3 = r.nextInt(26);
        String[] abc = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        passletter = abc[rN3];
        return passletter;
    }

    public static void pressString(String string) throws AWTException, InterruptedException {
        Long time = System.currentTimeMillis();
        Robot r = new Robot();
        char[] chars = string.toCharArray();
        r.setAutoDelay(10);
        // Numbers
        char[] ns = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        java.util.List<Character> nums = (java.util.List) Collections.singletonList(ns);
        // Capital Letters
        char[] c2 = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        c2 = c2.toString().toUpperCase().toCharArray();
        java.util.List<Character> capABC = (List) Collections.singletonList(c2);

        for (Character ch : chars) {
            if(ch == ' ') {
                r.keyPress(KeyEvent.VK_SPACE);
                r.keyRelease(KeyEvent.VK_SPACE);
                continue;
            }
            if(ch == ':') {
                r.keyPress(KeyEvent.VK_SHIFT);
                r.keyPress(KeyEvent.VK_SEMICOLON);
                r.keyRelease(KeyEvent.VK_SHIFT);
                continue;
            }
            if(ch.toString().equalsIgnoreCase("/")) {
                r.keyPress(KeyEvent.VK_SLASH);
                continue;
            }

            if (ch.toString().toUpperCase().equals(ch.toString())) {
                r.keyPress(KeyEvent.VK_CAPS_LOCK);
                r.keyPress(KeyEvent.getExtendedKeyCodeForChar(ch));
                r.keyRelease(KeyEvent.VK_CAPS_LOCK);
                r.keyPress(KeyEvent.VK_CAPS_LOCK);
                r.keyRelease(KeyEvent.VK_CAPS_LOCK);

            } else {
                r.keyPress(KeyEvent.getExtendedKeyCodeForChar(ch));
            }

        }


        r.keyPress(KeyEvent.VK_ENTER);

    }
    public static String convertToFormat(int secs) {
        String s = "";
        int hours = 0,mins = 0,secs2 = 0,days = 0;
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
        System.out.println(s);
        return s.replace(".","");
    }
}
