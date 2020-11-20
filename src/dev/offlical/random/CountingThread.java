package dev.offlical.random;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.List;

public class CountingThread {

    public static void main(String[] args) throws InterruptedException, AWTException {

        int num = 1222;

        System.out.println("Starting in 3 seconds");
        Thread.sleep(3000);
        System.out.println("Starting");

        while(true) {
            num++;
            pressString(num + "");
            Thread.sleep(1000);

        }

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
}
