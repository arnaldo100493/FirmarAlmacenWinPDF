// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonProviderImpl.java

package org.glassfish.json;

import co.org.glassfish.json.api.BufferPool;
import java.io.*;
import java.util.*;
import javax.json.*;
import javax.json.spi.JsonProvider;
import javax.json.stream.*;

// Referenced classes of package org.glassfish.json:
//            BufferPoolImpl, JsonGeneratorImpl, JsonParserImpl, JsonParserFactoryImpl, 
//            JsonGeneratorFactoryImpl, JsonReaderImpl, JsonWriterImpl, JsonWriterFactoryImpl, 
//            JsonReaderFactoryImpl, JsonObjectBuilderImpl, JsonArrayBuilderImpl, JsonBuilderFactoryImpl

public class JsonProviderImpl extends JsonProvider
{

    public JsonProviderImpl()
    {
    }

    public JsonGenerator createGenerator(Writer writer)
    {
        return new JsonGeneratorImpl(writer, bufferPool);
    }

    public JsonGenerator createGenerator(OutputStream out)
    {
        return new JsonGeneratorImpl(out, bufferPool);
    }

    public JsonParser createParser(Reader reader)
    {
        return new JsonParserImpl(reader, bufferPool);
    }

    public JsonParser createParser(InputStream in)
    {
        return new JsonParserImpl(in, bufferPool);
    }

    public JsonParserFactory createParserFactory(Map config)
    {
        BufferPool pool = null;
        if(config != null && config.containsKey(co/org/glassfish/json/api/BufferPool.getName()))
            pool = (BufferPool)config.get(co/org/glassfish/json/api/BufferPool.getName());
        if(pool == null)
            pool = bufferPool;
        return new JsonParserFactoryImpl(pool);
    }

    public JsonGeneratorFactory createGeneratorFactory(Map config)
    {
        Map providerConfig;
        boolean prettyPrinting;
        BufferPool pool;
        if(config == null)
        {
            providerConfig = Collections.emptyMap();
            prettyPrinting = false;
            pool = bufferPool;
        } else
        {
            providerConfig = new HashMap();
            if(prettyPrinting = isPrettyPrintingEnabled(config))
                providerConfig.put("javax.json.stream.JsonGenerator.prettyPrinting", Boolean.valueOf(true));
            pool = (BufferPool)config.get(co/org/glassfish/json/api/BufferPool.getName());
            if(pool != null)
                providerConfig.put(co/org/glassfish/json/api/BufferPool.getName(), pool);
            else
                pool = bufferPool;
            providerConfig = Collections.unmodifiableMap(providerConfig);
        }
        return new JsonGeneratorFactoryImpl(providerConfig, prettyPrinting, pool);
    }

    public JsonReader createReader(Reader reader)
    {
        return new JsonReaderImpl(reader, bufferPool);
    }

    public JsonReader createReader(InputStream in)
    {
        return new JsonReaderImpl(in, bufferPool);
    }

    public JsonWriter createWriter(Writer writer)
    {
        return new JsonWriterImpl(writer, bufferPool);
    }

    public JsonWriter createWriter(OutputStream out)
    {
        return new JsonWriterImpl(out, bufferPool);
    }

    public JsonWriterFactory createWriterFactory(Map config)
    {
        Map providerConfig;
        boolean prettyPrinting;
        BufferPool pool;
        if(config == null)
        {
            providerConfig = Collections.emptyMap();
            prettyPrinting = false;
            pool = bufferPool;
        } else
        {
            providerConfig = new HashMap();
            if(prettyPrinting = isPrettyPrintingEnabled(config))
                providerConfig.put("javax.json.stream.JsonGenerator.prettyPrinting", Boolean.valueOf(true));
            pool = (BufferPool)config.get(co/org/glassfish/json/api/BufferPool.getName());
            if(pool != null)
                providerConfig.put(co/org/glassfish/json/api/BufferPool.getName(), pool);
            else
                pool = bufferPool;
            providerConfig = Collections.unmodifiableMap(providerConfig);
        }
        return new JsonWriterFactoryImpl(providerConfig, prettyPrinting, pool);
    }

    public JsonReaderFactory createReaderFactory(Map config)
    {
        BufferPool pool = null;
        if(config != null && config.containsKey(co/org/glassfish/json/api/BufferPool.getName()))
            pool = (BufferPool)config.get(co/org/glassfish/json/api/BufferPool.getName());
        if(pool == null)
            pool = bufferPool;
        return new JsonReaderFactoryImpl(pool);
    }

    public JsonObjectBuilder createObjectBuilder()
    {
        return new JsonObjectBuilderImpl(bufferPool);
    }

    public JsonArrayBuilder createArrayBuilder()
    {
        return new JsonArrayBuilderImpl(bufferPool);
    }

    public JsonBuilderFactory createBuilderFactory(Map config)
    {
        BufferPool pool = null;
        if(config != null && config.containsKey(co/org/glassfish/json/api/BufferPool.getName()))
            pool = (BufferPool)config.get(co/org/glassfish/json/api/BufferPool.getName());
        if(pool == null)
            pool = bufferPool;
        return new JsonBuilderFactoryImpl(pool);
    }

    static boolean isPrettyPrintingEnabled(Map config)
    {
        return config.containsKey("javax.json.stream.JsonGenerator.prettyPrinting");
    }

    private final BufferPool bufferPool = new BufferPoolImpl();
}
