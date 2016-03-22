package tomekkup.sstablegen.custom;

import org.apache.commons.lang3.StringUtils;

/**
 * ********************************************************
 * Copyright: 2012 Tomek Kuprowski
 *
 * License: GPLv2: http://www.gnu.org/licences/gpl.html
 *
 * @author Tomek Kuprowski (tomekkuprowski at gmail dot com)
 * *******************************************************
 */
public class DowodGenerator {

    private static final char zero = '0';
    
    public static String get() {
        int wagi[] = new int[]{7, 3, 1, 7, 3, 1, 7, 3};

        int lit1 = (int) (Math.random() * 25 + 10);
        int lit2 = (int) (Math.random() * 25 + 10);
        int lit3 = (int) (Math.random() * 25 + 10);
        int nbr = (int) (Math.random() * 99999 + 1);

        char[] nbrAsch = StringUtils.leftPad(String.valueOf(nbr), 5, zero).toCharArray();
        int sumaKontrolna = wagi[0] * lit1 + wagi[1] * lit2 + wagi[2] * lit3;
        for (int i = 3; i < wagi.length; i++) {
            final int parseInt = Integer.parseInt("" + nbrAsch[i - 3]);
            sumaKontrolna += wagi[i] * parseInt;
        }
        return (char) (lit1 + 55) + "" + (char) (lit2 + 55) + "" + (char) (lit3 + 55) + "" + (sumaKontrolna % 10) + "" + nbr;

    }
}
