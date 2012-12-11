package tomekkup.sstablegen;

import tomekkup.sstablegen.custom.builders.SourceBuilder;
import tomekkup.sstablegen.destinations.Destination;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tomek
 */
public class DistributorJob {
    
    private int amount = 2;
    private Set<Destination> destinations = new HashSet<Destination>();
    private SourceBuilder sourceBuilder;
    
    public DistributorJob(int amount) throws Exception {
        this.amount = amount;
    }
    
    public void addDestination(Destination destination) {
        destinations.add(destination);
    }
    
    public void registerSourceBuilder(SourceBuilder builder) {
        this.sourceBuilder = builder;
    }
    
    public void go() throws IOException {
        if (sourceBuilder == null) {
            throw new IllegalStateException("not ready yet");
        }
        
        Logger.getLogger(App.class.getName()).log(Level.SEVERE, "Generating : " + this.amount);
        for (int i = 0; i < this.amount; i++) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, i + "");
            Object source = sourceBuilder.get();
            Iterator<Destination> destIter = destinations.iterator();
            while (destIter.hasNext()) {
                destIter.next().handle(source);
            }
        }
        
        Iterator<Destination> destIter = destinations.iterator();
        while (destIter.hasNext()) {
            destIter.next().close();
        }
    }
}
