package tomekkup.sstablegen.custom.builders;

import java.util.Date;
import java.util.Random;
import org.fluttercode.datafactory.impl.DataFactory;

/**
 *
 * @author tomek
 */
public abstract class AbstractSourceBuilder implements SourceBuilder {

    protected DataFactory df = new DataFactory();
    protected Random rand = new Random();
    protected Date minDate = new Date(2004, 3, 2, 0, 0, 0);
    protected Date maxDate = new Date(2011, 9, 8, 0, 0, 0);
}
