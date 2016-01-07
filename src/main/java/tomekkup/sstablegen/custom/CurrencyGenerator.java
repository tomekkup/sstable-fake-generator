package tomekkup.sstablegen.custom;

import java.util.Random;

/**
 * ********************************************************
 * Copyright: 2012 Tomek Kuprowski
 *
 * License: GPLv2: http://www.gnu.org/licences/gpl.html
 *
 * @author Tomek Kuprowski (tomekkuprowski at gmail dot com)
 * *******************************************************
 */
public class CurrencyGenerator {

    private static Random rand = new Random();

    public static String get() {
        return rand.nextBoolean() == false ? "PLN" : "EUR";
    }
}
