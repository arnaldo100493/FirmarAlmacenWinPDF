// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonObjectBuilderImpl.java

package org.glassfish.json;

import co.org.glassfish.json.api.BufferPool;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import javax.json.*;

// Referenced classes of package org.glassfish.json:
//            JsonStringImpl, JsonNumberImpl, JsonMessages, JsonWriterImpl

class JsonObjectBuilderImpl
    implements JsonObjectBuilder
{
    private static final class JsonObjectImpl extends AbstractMap
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

        JsonObjectImpl(Map valueMap, BufferPool bufferPool)
        {
            this.valueMap = valueMap;
            this.bufferPool = bufferPool;
        }
    }


    JsonObjectBuilderImpl(BufferPool bufferPool)
    {
        this.bufferPool = bufferPool;
    }

    public JsonObjectBuilder add(String name, JsonValue value)
    {
        validateName(name);
        validateValue(value);
        putValueMap(name, value);
        return this;
    }

    public JsonObjectBuilder add(String name, String value)
    {
        validateName(name);
        validateValue(value);
        putValueMap(name, new JsonStringImpl(value));
        return this;
    }

    public JsonObjectBuilder add(String name, BigInteger value)
    {
        validateName(name);
        validateValue(value);
        putValueMap(name, JsonNumberImpl.getJsonNumber(value));
        return this;
    }

    public JsonObjectBuilder add(String name, BigDecimal value)
    {
        validateName(name);
        validateValue(value);
        putValueMap(name, JsonNumberImpl.getJsonNumber(value));
        return this;
    }

    public JsonObjectBuilder add(String name, int value)
    {
        validateName(name);
        putValueMap(name, JsonNumberImpl.getJsonNumber(value));
        return this;
    }

    public JsonObjectBuilder add(String name, long value)
    {
        validateName(name);
        putValueMap(name, JsonNumberImpl.getJsonNumber(value));
        return this;
    }

    public JsonObjectBuilder add(String name, double value)
    {
        validateName(name);
        putValueMap(name, JsonNumberImpl.getJsonNumber(value));
        return this;
    }

    public JsonObjectBuilder add(String name, boolean value)
    {
        validateName(name);
        putValueMap(name, value ? JsonValue.TRUE : JsonValue.FALSE);
        return this;
    }

    public JsonObjectBuilder addNull(String name)
    {
        validateName(name);
        putValueMap(name, JsonValue.NULL);
        return this;
    }

    public JsonObjectBuilder add(String name, JsonObjectBuilder builder)
    {
        validateName(name);
        if(builder == null)
        {
            throw new NullPointerException(JsonMessages.OBJBUILDER_OBJECT_BUILDER_NULL());
        } else
        {
            putValueMap(name, builder.build());
            return this;
        }
    }

    public JsonObjectBuilder add(String name, JsonArrayBuilder builder)
    {
        validateName(name);
        if(builder == null)
        {
            throw new NullPointerException(JsonMessages.OBJBUILDER_ARRAY_BUILDER_NULL());
        } else
        {
            putValueMap(name, builder.build());
            return this;
        }
    }

    public JsonObject build()
    {
        Map snapshot = valueMap != null ? Collections.unmodifiableMap(valueMap) : Collections.emptyMap();
        valueMap = null;
        return new JsonObjectImpl(snapshot, bufferPool);
    }

    private void putValueMap(String name, JsonValue value)
    {
        if(valueMap == null)
            valueMap = new LinkedHashMap();
        valueMap.put(name, value);
    }

    private void validateName(String name)
    {
        if(name == null)
            throw new NullPointerException(JsonMessages.OBJBUILDER_NAME_NULL());
        else
            return;
    }

    private void validateValue(Object value)
    {
        if(value == null)
            throw new NullPointerException(JsonMessages.OBJBUILDER_VALUE_NULL());
        else
            return;
    }

    private Map valueMap;
    private final BufferPool bufferPool;
}
