package br.com.webmotors.util;

public class Sleep {
    public static void seconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception err) {}
    }
}
