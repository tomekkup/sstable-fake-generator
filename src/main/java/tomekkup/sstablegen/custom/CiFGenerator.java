package tomekkup.sstablegen.custom;

/**
 * ********************************************************
 * Copyright: 2012 Tomek Kuprowski
 *
 * License: GPLv2: http://www.gnu.org/licences/gpl.html
 *
 * @author Tomek Kuprowski (tomekkuprowski at gmail dot com)
 * *******************************************************
 */
public class CiFGenerator {

    private static long cif = 100000;

    public static String get() {
        cif++;

        return String.format("%09d", cif);
    }
}
