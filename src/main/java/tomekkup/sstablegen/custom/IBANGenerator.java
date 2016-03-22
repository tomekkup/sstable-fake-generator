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
public class IBANGenerator {

    private static final String countryCode = "PL";
    private static final String bankCode = "50106588";

    public static String get(String bban) {
        String nr2 = bban + "252100";
        int modulo = 0;
        for (char znak : nr2.toCharArray()) {
            modulo = (10 * modulo + Integer.parseInt(String.valueOf(znak))) % 97;
        }
        modulo = 98 - modulo;

        StringBuilder sb = new StringBuilder(countryCode);
        sb.append(StringUtils.leftPad(String.valueOf(modulo), 2, '0'));
        sb.append(bankCode);
        sb.append(bban);
        return sb.toString();
    }
}
