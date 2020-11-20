package dev.offlical.macro;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MWindow extends JFrame {
    private JPanel contentPane;
    private JProgressBar progressBar1;
    private JTextField charsBox;
    private JTextField secondsBox;
    private JLabel LogText;
    private JButton genMsgs;
    private JButton macroButton;
    private JCheckBox downloadAlways;
    private JTextArea consoleLog;
    private JScrollPane scroll;


    public MWindow() {
        setContentPane(contentPane);
        setMaximumSize(new Dimension(750,500));
        setResizable(false);
    }



    private void createUIComponents() {
        progressBar1 = new JProgressBar();
        charsBox = new JTextField();
        secondsBox = new JTextField();
        LogText = new JLabel();
        genMsgs = new JButton();
        macroButton = new JButton();
        consoleLog = new JTextArea();
        scroll = new JScrollPane();
        Font font = consoleLog.getFont();


        scroll.setVerticalScrollBarPolicy (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        genMsgs.addActionListener(ev -> {
            int chars = 0;
            try {
                chars = Integer.parseInt(charsBox.getText());
                System.out.println(chars);
                if(chars <= 0) {
                    JOptionPane pane =  new JOptionPane();
                    JOptionPane.showMessageDialog(pane,"You can't make a message out of negative amount of characters can you?");
                    charsBox.setText("1000");
                    return;
                }
                Main.getBeeMovieM().divideScript(chars);
                Main.setCharsMsgs(chars);
            }catch (NumberFormatException | IOException e) {
                JOptionPane pane =  new JOptionPane();
                pane.setMessage("Error");
                JOptionPane.showMessageDialog(pane,"Invalid Number! Use only whole numbers!");
                charsBox.setText("1000");
                return;
            }
        });
        macroButton.addActionListener(ev -> {
            double seconds = 0;
            int chars = 0;
            Main.setBeeMovieM(new BeeMovieM(progressBar1));

            try {
                chars = Integer.parseInt(charsBox.getText());
                System.out.println(chars);
                if(chars <= 0) {
                    JOptionPane pane =  new JOptionPane();
                    JOptionPane.showMessageDialog(pane,"You can't make a message out of negative amount of characters can you?");
                    charsBox.setText("1000");
                }
                Main.getBeeMovieM().divideScript(chars);
                Main.setCharsMsgs(chars);
            }catch (NumberFormatException | IOException e) {
                JOptionPane pane =  new JOptionPane();
                pane.setMessage("Error");
                JOptionPane.showMessageDialog(pane,"Invalid Number! Use only whole numbers!");
                charsBox.setText("1000");
                return;
            }

            try {
                seconds = Double.parseDouble(secondsBox.getText());
                System.out.println(seconds);
                if(seconds <= 0) {
                    JOptionPane pane =  new JOptionPane();
                    JOptionPane.showMessageDialog(pane,"You can't wait a negative amount of seconds between messages can you?");
                    secondsBox.setText("3");
                }
                Main.setSeconds(seconds);
            }catch (NumberFormatException e) {
                JOptionPane pane =  new JOptionPane();
                pane.setMessage("Error");
                JOptionPane.showMessageDialog(pane,"Invalid Number!");
                secondsBox.setText("3");
                return;
            }


            Main.getBeeMovieM().log("Starting in 3 seconds!");
            try {
                Main.getBeeMovieM().start();
            }catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }


        });
        downloadAlways = new JCheckBox();
        downloadAlways.addActionListener(e -> {
            System.out.println(downloadAlways.isSelected());
            if(downloadAlways.isSelected()) Main.setDownloadAlways(false);
            else Main.setDownloadAlways(true);
            System.out.println(Main.isDownloadAlways());
        });

    }

    public JProgressBar getProgressBar1() {
        return progressBar1;
    }

    public JLabel getLogText() {
        return LogText;
    }

    public JTextArea getConsoleLog() {
        return consoleLog;
    }

}
