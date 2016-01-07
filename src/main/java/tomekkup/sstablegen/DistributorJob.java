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
 * ********************************************************
 * Copyright: 2012 Tomek Kuprowski
 *
 * License: GPLv2: http://www.gnu.org/licences/gpl.html
 *
 * @author Tomek Kuprowski (tomekkuprowski at gmail dot com)
 * *******************************************************
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

        int progress = -1;
        Logger.getLogger(App.class.getName()).log(Level.SEVERE, "Generating : " + this.amount);
        for (int i = 0; i < this.amount; i++) {
            int currentProgress = i * 100 / amount;
            if (currentProgress > progress) {
                progress = currentProgress;
                System.out.println(progress + "%");
            }
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
