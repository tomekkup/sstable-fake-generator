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
public class CiFGenerator {

    private static long cif = 100000;
    private static final char zero = '0';

    public static String get() {
        cif++;

        return StringUtils.leftPad(String.valueOf(cif), 9, zero);
    }
}
