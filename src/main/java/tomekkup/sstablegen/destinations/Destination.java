package tomekkup.sstablegen.destinations;

import java.io.IOException;

/**
 * ********************************************************
 * Copyright: 2012 Tomek Kuprowski
 *
 * License: GPLv2: http://www.gnu.org/licences/gpl.html
 *
 * @author Tomek Kuprowski (tomekkuprowski at gmail dot com)
 * *******************************************************
 */
public interface Destination {

    void handle(Object source) throws IOException;

    void close() throws IOException;
}
