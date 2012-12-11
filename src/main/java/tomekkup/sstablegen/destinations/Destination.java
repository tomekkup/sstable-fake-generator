package tomekkup.sstablegen.destinations;

import java.io.IOException;

/**
 *
 * @author tomek
 */
public interface Destination {

    void handle(Object source) throws IOException;

    void close() throws IOException;
}
