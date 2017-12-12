// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonWriterImpl.java

package org.glassfish.json;

import co.org.glassfish.json.api.BufferPool;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import javax.json.*;

// Referenced classes of package org.glassfish.json:
//            JsonPrettyGeneratorImpl, JsonGeneratorImpl, JsonMessages

class JsonWriterImpl
    implements JsonWriter
{
    private static final class NoFlushOutputStream extends FilterOutputStream
    {

        public void write(byte b[], int off, int len)
            throws IOException
        {
            out.write(b, off, len);
        }

        public void flush()
        {
        }

        public NoFlushOutputStream(OutputStream out)
        {
            super(out);
        }
    }


    JsonWriterImpl(Writer writer, BufferPool bufferPool)
    {
        this(writer, false, bufferPool);
    }

    JsonWriterImpl(Writer writer, boolean prettyPrinting, BufferPool bufferPool)
    {
        generator = ((JsonGeneratorImpl) (prettyPrinting ? ((JsonGeneratorImpl) (new JsonPrettyGeneratorImpl(writer, bufferPool))) : new JsonGeneratorImpl(writer, bufferPool)));
        os = null;
    }

    JsonWriterImpl(OutputStream out, BufferPool bufferPool)
    {
        this(out, UTF_8, false, bufferPool);
    }

    JsonWriterImpl(OutputStream out, boolean prettyPrinting, BufferPool bufferPool)
    {
        this(out, UTF_8, prettyPrinting, bufferPool);
    }

    JsonWriterImpl(OutputStream out, Charset charset, boolean prettyPrinting, BufferPool bufferPool)
    {
        os = new NoFlushOutputStream(out);
        generator = ((JsonGeneratorImpl) (prettyPrinting ? ((JsonGeneratorImpl) (new JsonPrettyGeneratorImpl(os, charset, bufferPool))) : new JsonGeneratorImpl(os, charset, bufferPool)));
    }

    public void writeArray(JsonArray array)
    {
        if(writeDone)
            throw new IllegalStateException(JsonMessages.WRITER_WRITE_ALREADY_CALLED());
        writeDone = true;
        generator.writeStartArray();
        JsonValue value;
        for(Iterator i$ = array.iterator(); i$.hasNext(); generator.write(value))
            value = (JsonValue)i$.next();

        generator.writeEnd();
        generator.flushBuffer();
        if(os != null)
            generator.flush();
    }

    public void writeObject(JsonObject object)
    {
        if(writeDone)
            throw new IllegalStateException(JsonMessages.WRITER_WRITE_ALREADY_CALLED());
        writeDone = true;
        generator.writeStartObject();
        java.util.Map.Entry e;
        for(Iterator i$ = object.entrySet().iterator(); i$.hasNext(); generator.write((String)e.getKey(), (JsonValue)e.getValue()))
            e = (java.util.Map.Entry)i$.next();

        generator.writeEnd();
        generator.flushBuffer();
        if(os != null)
            generator.flush();
    }

    public void write(JsonStructure value)
    {
        if(value instanceof JsonArray)
            writeArray((JsonArray)value);
        else
            writeObject((JsonObject)value);
    }

    public void close()
    {
        writeDone = true;
        generator.close();
    }

    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final JsonGeneratorImpl generator;
    private boolean writeDone;
    private final NoFlushOutputStream os;

}
