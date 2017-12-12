// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonReaderFactoryImpl.java

package org.glassfish.json;

import co.org.glassfish.json.api.BufferPool;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;

// Referenced classes of package org.glassfish.json:
//            JsonReaderImpl

class JsonReaderFactoryImpl
    implements JsonReaderFactory
{

    JsonReaderFactoryImpl(BufferPool bufferPool)
    {
        this.bufferPool = bufferPool;
    }

    public JsonReader createReader(Reader reader)
    {
        return new JsonReaderImpl(reader, bufferPool);
    }

    public JsonReader createReader(InputStream in)
    {
        return new JsonReaderImpl(in, bufferPool);
    }

    public JsonReader createReader(InputStream in, Charset charset)
    {
        return new JsonReaderImpl(in, charset, bufferPool);
    }

    public Map getConfigInUse()
    {
        return config;
    }

    private final Map config = Collections.emptyMap();
    private final BufferPool bufferPool;
}
