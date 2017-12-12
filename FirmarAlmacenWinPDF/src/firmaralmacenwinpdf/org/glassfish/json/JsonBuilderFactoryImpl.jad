// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonBuilderFactoryImpl.java

package org.glassfish.json;

import co.org.glassfish.json.api.BufferPool;
import java.util.Collections;
import java.util.Map;
import javax.json.*;

// Referenced classes of package org.glassfish.json:
//            JsonObjectBuilderImpl, JsonArrayBuilderImpl

class JsonBuilderFactoryImpl
    implements JsonBuilderFactory
{

    JsonBuilderFactoryImpl(BufferPool bufferPool)
    {
        this.bufferPool = bufferPool;
    }

    public JsonObjectBuilder createObjectBuilder()
    {
        return new JsonObjectBuilderImpl(bufferPool);
    }

    public JsonArrayBuilder createArrayBuilder()
    {
        return new JsonArrayBuilderImpl(bufferPool);
    }

    public Map getConfigInUse()
    {
        return config;
    }

    private final Map config = Collections.emptyMap();
    private final BufferPool bufferPool;
}
