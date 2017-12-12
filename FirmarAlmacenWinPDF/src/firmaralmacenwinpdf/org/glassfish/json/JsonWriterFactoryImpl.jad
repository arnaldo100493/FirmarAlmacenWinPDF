// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonWriterFactoryImpl.java

package org.glassfish.json;

import co.org.glassfish.json.api.BufferPool;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;

// Referenced classes of package org.glassfish.json:
//            JsonWriterImpl

class JsonWriterFactoryImpl
    implements JsonWriterFactory
{

    JsonWriterFactoryImpl(Map config, boolean prettyPrinting, BufferPool bufferPool)
    {
        this.config = config;
        this.prettyPrinting = prettyPrinting;
        this.bufferPool = bufferPool;
    }

    public JsonWriter createWriter(Writer writer)
    {
        return new JsonWriterImpl(writer, prettyPrinting, bufferPool);
    }

    public JsonWriter createWriter(OutputStream out)
    {
        return new JsonWriterImpl(out, prettyPrinting, bufferPool);
    }

    public JsonWriter createWriter(OutputStream out, Charset charset)
    {
        return new JsonWriterImpl(out, charset, prettyPrinting, bufferPool);
    }

    public Map getConfigInUse()
    {
        return config;
    }

    private final Map config;
    private final boolean prettyPrinting;
    private final BufferPool bufferPool;
}
