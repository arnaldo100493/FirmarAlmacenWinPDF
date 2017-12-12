// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonParserFactoryImpl.java

package org.glassfish.json;

import co.org.glassfish.json.api.BufferPool;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;

// Referenced classes of package org.glassfish.json:
//            JsonParserImpl, JsonStructureParser

class JsonParserFactoryImpl
    implements JsonParserFactory
{

    JsonParserFactoryImpl(BufferPool bufferPool)
    {
        this.bufferPool = bufferPool;
    }

    public JsonParser createParser(Reader reader)
    {
        return new JsonParserImpl(reader, bufferPool);
    }

    public JsonParser createParser(InputStream in)
    {
        return new JsonParserImpl(in, bufferPool);
    }

    public JsonParser createParser(InputStream in, Charset charset)
    {
        return new JsonParserImpl(in, charset, bufferPool);
    }

    public JsonParser createParser(JsonArray array)
    {
        return new JsonStructureParser(array);
    }

    public Map getConfigInUse()
    {
        return config;
    }

    public JsonParser createParser(JsonObject object)
    {
        return new JsonStructureParser(object);
    }

    private final Map config = Collections.emptyMap();
    private final BufferPool bufferPool;
}
