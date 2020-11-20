package dev.offlical.macro;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

    private static BeeMovieM beeMovieM;

    private static double seconds;
    private static int charsMsgs;
    private static boolean downloadAlways;
    private static MWindow window;
    /*
        If you're using a jar decompiler to look at my shit code, fuck you
        - Offlical
     */

    public static void main(String[] args) {
        downloadAlways = false;
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //Windows Look and feel
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        window = new MWindow();
        window.setMaximumSize(new Dimension(750,500));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setTitle("Bee Movie Script Spammer | Made by Offlical");
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        beeMovieM = new BeeMovieM(window.getProgressBar1());
    }

    public static BeeMovieM getBeeMovieM() {
        return beeMovieM;
    }

    public static void setBeeMovieM(BeeMovieM beeMovieM) {
        Main.beeMovieM = beeMovieM;
    }

    public static double getSeconds() {
        return seconds;
    }

    public static void setSeconds(double seconds) {
        Main.seconds = seconds;
        beeMovieM.setSeconds(seconds);
    }

    public static int getCharsMsgs() {
        return charsMsgs;
    }

    public static void setCharsMsgs(int charsMsgs) {
        Main.charsMsgs = charsMsgs;
        beeMovieM.setCharsMsg(charsMsgs);
    }

    public static MWindow getWindow() {
        return window;
    }

    public static void setWindow(MWindow window) {
        Main.window = window;
    }

    public static boolean isDownloadAlways() {
        return downloadAlways;
    }

    public static void setDownloadAlways(boolean downloadAlways) {
        Main.downloadAlways = downloadAlways;
    }
}
