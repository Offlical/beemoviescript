package dev.offlical.macro;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class BeeMovieM extends Thread {

    private String script;
    private JProgressBar bar;
    private ArrayList<String> msgs;
    private MWindow window;
    private int charsMsg = 1000;
    private double seconds;

    public BeeMovieM(JProgressBar bar) {
        this.bar = bar;
        this.msgs = new ArrayList<String>();
        window = Main.getWindow();;
    }

    @Override
    public void run() {

        if(msgs.isEmpty()) {
            try {
                msgs = divideScript(charsMsg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        StringSelection selection;
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();


        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        System.out.println("Estimated Time til finish: " + msgs.size() * seconds + " seconds");

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(String msg : msgs.toArray(new String[msgs.size()])) {
            selection = new StringSelection(msg);
            clipboard.setContents(selection,selection);
            r.keyPress(17);
            r.keyPress(KeyEvent.VK_V);
            r.keyRelease(17);
            r.keyPress(KeyEvent.VK_ENTER);

            r.keyRelease(KeyEvent.VK_V);
            r.keyRelease(KeyEvent.VK_ENTER);

            try {
                sleep((long) (1000 * seconds));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.stop();
    }

    public void downloadScript() throws IOException {
        int barval = 0;
        bar.setValue(0);

        String path = "scripts//";
        File file = new File(path + "beemoviescript.txt");
        if(file.exists() && !Main.isDownloadAlways()) return;

        barval += 33;
        bar.setValue(barval);
        log("DOWNLOAD: Connecting to the website..");
        Document doc = Jsoup.connect("https://web.njit.edu/~cm395/theBeeMovieScript/").get();
        bar.setValue(bar.getValue() + 50);

        barval += 33;
        bar.setValue(barval);
        log("DOWNLOAD: Getting html value...");
        String s = doc.body().select("pre").html();
        bar.setValue(bar.getValue() + 50);

        barval += 34;
        bar.setValue(barval);
        log("DOWNLOAD: Got html value!");
        System.out.println("Got html value!");

        this.script = doc.body().select("pre").html();
        if(!Main.isDownloadAlways())
            writeScriptFile();


    }

    public ArrayList<String> divideScript(int charsPerMsg) throws IOException {
        int barval = 0;
        log("MESSAGE GEN: Checking if script is loaded...");

        if(script == null) {
            log("MESSAGE GEN: Script isn't avaliable! Getting script..");
            String path = "scripts//";
            File file = new File(path + "beemoviescript.txt");

            if(!file.exists())
                downloadScript();
            else
                readScriptFile();
        }
        barval += 25;
        bar.setValue(barval);
        log("MESSAGE GEN: Script Loaded!");
        msgs.clear();
        bar.setValue(0);
        msgs = new ArrayList<String>();

        int index = 0,prevIndex = 0;
        int m = 0;
        int chars = script.toCharArray().length;
        int l = 0;

        int totalMsgs = chars / charsPerMsg;
        if(chars % charsPerMsg != 0) totalMsgs += 1;



        log("MESSAGE GEN: Total Characters in script =" + chars);
        while(chars != 0) {
            prevIndex = index;
         //   System.out.println("chars %" + charsPerMsg + " != 0 - " + (chars % charsPerMsg != 0));
           // System.out.println("chars / " + charsPerMsg + " != 0 - " + (chars / charsPerMsg != 0));
            if(chars % charsPerMsg != 0 && chars / charsPerMsg != 0) {
                index += charsPerMsg;
                msgs.add(script.substring(prevIndex,index));
                chars = chars - charsPerMsg;
                l = charsPerMsg;
            } else {
                index += chars;
                msgs.add(script.substring(prevIndex,index));
                System.out.println("DEBUG: Finished Dividing!");
                l = chars;
                chars = 0;
            }
            barval += (int) (100 / totalMsgs + 100 % totalMsgs);
            bar.setValue(barval);
            m++;
            System.out.println("Characters: Left " + chars);
            System.out.println("Created the #" + m + "/" + totalMsgs + " message!");

            log("MESSAGE GEN: Created the #" + m + "/" + totalMsgs + " message!");
        }

        return msgs;
    }

    public void readScriptFile() throws IOException {
        int barval = 0;
        bar.setValue(0);
        log("READ: Checking if file exists...");
        barval += 25;
        bar.setValue(barval);
        String path = "scripts//";
        File file = new File(path + "beemoviescript.txt");
        if(!file.exists()) {
            log("READ: File doesn't exist! downloading it...");
            Main.setDownloadAlways(false);
            downloadScript();
        }
        barval += 25;
        bar.setValue(barval);
        log("READ: File loaded!");

        StringBuilder str = new StringBuilder();

        barval += 25;
        bar.setValue(barval);
        log("READ: Reading all lines..");
        ArrayList<String> list = (ArrayList<String>) Files.readAllLines(Paths.get(file.toURI()));

        log("READ: Adding lines to script... ");
        for(String s : list) {
            str.append(s).append("\n");
            barval += 25 / list.size() + 25 % list.size();
            bar.setValue(barval);
        }
        log("READ: Done!");
        script = str.toString();
    }

    public void writeScriptFile() throws IOException {
        int barval = 0;
        bar.setValue(0);
        Scanner sc = new Scanner(script);
        String path = "scripts//";
        File file = new File(path + "beemoviescript.txt");


        barval += 25;
        bar.setValue(barval);
        log("WRITE: Checking if file already exists...");
        File dir = new File(path);
        if (!file.exists()) {
            log("WRITE: File doesn't exist! Creating directory and file");
            System.out.println("Created directory: " + dir.mkdirs());
            System.out.println("Created file: " + file.createNewFile());
        };
        barval += 25;
        bar.setValue(barval);
        log("WRITE: File Loaded!");

        log("WRITE: Writer Started");
        barval += 25;
        bar.setValue(barval);
        FileOutputStream outputStream = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

        while(sc.hasNextLine()) {
            writer.write(sc.nextLine());
            writer.newLine();
        }
        writer.close();
        barval += 25;
        bar.setValue(barval);
        log("WRITE: Finished Writing.");
    }

    public void setMsgs(ArrayList<String> msgs) {
        this.msgs = msgs;
    }

    public void setCharsMsg(int charsMsg) {
        this.charsMsg = charsMsg;
    }

    public ArrayList<String> getMsgs() {
        return msgs;
    }

    public int getCharsMsg() {
        return charsMsg;
    }

    public void log(String s) {
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
        window.getLogText().setText("Latest Log | " + s);
        window.getConsoleLog().setText(window.getConsoleLog().getText() + "\n" + s + " (" + df.format(new Date(System.currentTimeMillis())) + ")");
    }

    public double getSeconds() {
        return seconds;
    }

    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }
}
