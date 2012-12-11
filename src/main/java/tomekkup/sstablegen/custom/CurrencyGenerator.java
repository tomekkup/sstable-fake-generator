package tomekkup.sstablegen.custom;

import java.util.Random;

/**
 *
 * @author tomek
 */
public class CurrencyGenerator {
    
    private static Random rand = new Random();
    
    public static String get() {
        return rand.nextBoolean() == false ? "PLN" : "EUR";
    }
}
