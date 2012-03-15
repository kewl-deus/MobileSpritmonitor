package de.dengot.spritmonitor.io;


import de.dengot.spritmonitor.model.Fueling;

import java.io.IOException;
import java.io.Reader;

public abstract class FuelingParser {

    private FuelingWriter writer;

    public FuelingParser(FuelingWriter writer){
        this.writer = writer;
    }

    public abstract void parseFuelings(Reader reader) throws IOException;

    protected void writeFueling(Fueling fueling){
        this.writer.writeFueling(fueling);
    }
}
