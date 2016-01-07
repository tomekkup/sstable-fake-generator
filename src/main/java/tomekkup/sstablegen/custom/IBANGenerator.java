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
public class IBANGenerator {

    private static final String countryCode = "PL";
    private static final String bankCode = "50106588";

    public static String get(String bban) {
        String nr2 = bban + "252100";
        int modulo = 0;
        for (char znak : nr2.toCharArray()) {
            modulo = (10 * modulo + Integer.parseInt("" + znak)) % 97;
        }
        modulo = 98 - modulo;

        return countryCode + String.format("%02d", modulo) + bankCode + bban;
    }
}
