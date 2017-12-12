// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonGeneratorFactoryImpl.java

package org.glassfish.json;

import co.org.glassfish.json.api.BufferPool;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

// Referenced classes of package org.glassfish.json:
//            JsonPrettyGeneratorImpl, JsonGeneratorImpl

class JsonGeneratorFactoryImpl
    implements JsonGeneratorFactory
{

    JsonGeneratorFactoryImpl(Map config, boolean prettyPrinting, BufferPool bufferPool)
    {
        this.config = config;
        this.prettyPrinting = prettyPrinting;
        this.bufferPool = bufferPool;
    }

    public JsonGenerator createGenerator(Writer writer)
    {
        return ((JsonGenerator) (prettyPrinting ? new JsonPrettyGeneratorImpl(writer, bufferPool) : new JsonGeneratorImpl(writer, bufferPool)));
    }

    public JsonGenerator createGenerator(OutputStream out)
    {
        return ((JsonGenerator) (prettyPrinting ? new JsonPrettyGeneratorImpl(out, bufferPool) : new JsonGeneratorImpl(out, bufferPool)));
    }

    public JsonGenerator createGenerator(OutputStream out, Charset charset)
    {
        return ((JsonGenerator) (prettyPrinting ? new JsonPrettyGeneratorImpl(out, charset, bufferPool) : new JsonGeneratorImpl(out, charset, bufferPool)));
    }

    public Map getConfigInUse()
    {
        return config;
    }

    private final boolean prettyPrinting;
    private final Map config;
    private final BufferPool bufferPool;
}
