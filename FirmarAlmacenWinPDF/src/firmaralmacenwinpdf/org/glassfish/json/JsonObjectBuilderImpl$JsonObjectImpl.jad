// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonObjectBuilderImpl.java

package org.glassfish.json;

import co.org.glassfish.json.api.BufferPool;
import java.io.StringWriter;
import java.util.*;
import javax.json.*;

// Referenced classes of package org.glassfish.json:
//            JsonWriterImpl, JsonObjectBuilderImpl

private static final class JsonObjectBuilderImpl$JsonObjectImpl extends AbstractMap
    implements JsonObject
{

    public JsonArray getJsonArray(String name)
    {
        return (JsonArray)get(name);
    }

    public JsonObject getJsonObject(String name)
    {
        return (JsonObject)get(name);
    }

    public JsonNumber getJsonNumber(String name)
    {
        return (JsonNumber)get(name);
    }

    public JsonString getJsonString(String name)
    {
        return (JsonString)get(name);
    }

    public String getString(String name)
    {
        return getJsonString(name).getString();
    }

    public String getString(String name, String defaultValue)
    {
        try
        {
            return getString(name);
        }
        catch(Exception e)
        {
            return defaultValue;
        }
    }

    public int getInt(String name)
    {
        return getJsonNumber(name).intValue();
    }

    public int getInt(String name, int defaultValue)
    {
        try
        {
            return getInt(name);
        }
        catch(Exception e)
        {
            return defaultValue;
        }
    }

    public boolean getBoolean(String name)
    {
        JsonValue value = (JsonValue)get(name);
        if(value == null)
            throw new NullPointerException();
        if(value == JsonValue.TRUE)
            return true;
        if(value == JsonValue.FALSE)
            return false;
        else
            throw new ClassCastException();
    }

    public boolean getBoolean(String name, boolean defaultValue)
    {
        try
        {
            return getBoolean(name);
        }
        catch(Exception e)
        {
            return defaultValue;
        }
    }

    public boolean isNull(String name)
    {
        return ((JsonValue)get(name)).equals(JsonValue.NULL);
    }

    public javax.json.JsonValue.ValueType getValueType()
    {
        return javax.json.JsonValue.ValueType.OBJECT;
    }

    public Set entrySet()
    {
        return valueMap.entrySet();
    }

    public String toString()
    {
        StringWriter sw = new StringWriter();
        JsonWriter jw = new JsonWriterImpl(sw, bufferPool);
        jw.write(this);
        jw.close();
        return sw.toString();
    }

    private final Map valueMap;
    private final BufferPool bufferPool;

    JsonObjectBuilderImpl$JsonObjectImpl(Map valueMap, BufferPool bufferPool)
    {
        this.valueMap = valueMap;
        this.bufferPool = bufferPool;
    }
}
