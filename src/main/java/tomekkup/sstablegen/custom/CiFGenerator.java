package tomekkup.sstablegen.custom;

/**
 *
 * @author tomek
 */
public class CiFGenerator {
    
    private static long cif = 100000;
    
    public static String get() {
        cif++;
        
        return String.format("%09d", cif);
    }
}
