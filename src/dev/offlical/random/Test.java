package dev.offlical.random;

public class Test {
    public static void main(String[] args) {
        String line = "";

        for (int i = 5; i >= 1; i--) {
            System.out.println(i);
            for (int j = 1; j <= i; j++) {
                line += j + " ";

            }
            System.out.println(line);
            System.out.println();
            line = "";
        }
    }
}
