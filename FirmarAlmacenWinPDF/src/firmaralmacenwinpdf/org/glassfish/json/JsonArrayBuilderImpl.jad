// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonArrayBuilderImpl.java

package org.glassfish.json;

import co.org.glassfish.json.api.BufferPool;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import javax.json.*;

// Referenced classes of package org.glassfish.json:
//            JsonStringImpl, JsonNumberImpl, JsonMessages, JsonWriterImpl

class JsonArrayBuilderImpl
    implements JsonArrayBuilder
{
    private static final class JsonArrayImpl extends AbstractList
        implements JsonArray
    {

        public int size()
        {
            return valueList.size();
        }

        public JsonObject getJsonObject(int index)
        {
            return (JsonObject)valueList.get(index);
        }

        public JsonArray getJsonArray(int index)
        {
            return (JsonArray)valueList.get(index);
        }

        public JsonNumber getJsonNumber(int index)
        {
            return (JsonNumber)valueList.get(index);
        }

        public JsonString getJsonString(int index)
        {
            return (JsonString)valueList.get(index);
        }

        public List getValuesAs(Class clazz)
        {
            return valueList;
        }

        public String getString(int index)
        {
            return getJsonString(index).getString();
        }

        public String getString(int index, String defaultValue)
        {
            try
            {
                return getString(index);
            }
            catch(Exception e)
            {
                return defaultValue;
            }
        }

        public int getInt(int index)
        {
            return getJsonNumber(index).intValue();
        }

        public int getInt(int index, int defaultValue)
        {
            try
            {
                return getInt(index);
            }
            catch(Exception e)
            {
                return defaultValue;
            }
        }

        public boolean getBoolean(int index)
        {
            JsonValue jsonValue = get(index);
            if(jsonValue == JsonValue.TRUE)
                return true;
            if(jsonValue == JsonValue.FALSE)
                return false;
            else
                throw new ClassCastException();
        }

        public boolean getBoolean(int index, boolean defaultValue)
        {
            try
            {
                return getBoolean(index);
            }
            catch(Exception e)
            {
                return defaultValue;
            }
        }

        public boolean isNull(int index)
        {
            return ((JsonValue)valueList.get(index)).equals(JsonValue.NULL);
        }

        public javax.json.JsonValue.ValueType getValueType()
        {
            return javax.json.JsonValue.ValueType.ARRAY;
        }

        public JsonValue get(int index)
        {
            return (JsonValue)valueList.get(index);
        }

        public String toString()
        {
            StringWriter sw = new StringWriter();
            JsonWriter jw = new JsonWriterImpl(sw, bufferPool);
            jw.write(this);
            jw.close();
            return sw.toString();
        }

        public volatile Object get(int x0)
        {
            return get(x0);
        }

        private final List valueList;
        private final BufferPool bufferPool;

        JsonArrayImpl(List valueList, BufferPool bufferPool)
        {
            this.valueList = valueList;
            this.bufferPool = bufferPool;
        }
    }


    JsonArrayBuilderImpl(BufferPool bufferPool)
    {
        this.bufferPool = bufferPool;
    }

    public JsonArrayBuilder add(JsonValue value)
    {
        validateValue(value);
        addValueList(value);
        return this;
    }

    public JsonArrayBuilder add(String value)
    {
        validateValue(value);
        addValueList(new JsonStringImpl(value));
        return this;
    }

    public JsonArrayBuilder add(BigDecimal value)
    {
        validateValue(value);
        addValueList(JsonNumberImpl.getJsonNumber(value));
        return this;
    }

    public JsonArrayBuilder add(BigInteger value)
    {
        validateValue(value);
        addValueList(JsonNumberImpl.getJsonNumber(value));
        return this;
    }

    public JsonArrayBuilder add(int value)
    {
        addValueList(JsonNumberImpl.getJsonNumber(value));
        return this;
    }

    public JsonArrayBuilder add(long value)
    {
        addValueList(JsonNumberImpl.getJsonNumber(value));
        return this;
    }

    public JsonArrayBuilder add(double value)
    {
        addValueList(JsonNumberImpl.getJsonNumber(value));
        return this;
    }

    public JsonArrayBuilder add(boolean value)
    {
        addValueList(value ? JsonValue.TRUE : JsonValue.FALSE);
        return this;
    }

    public JsonArrayBuilder addNull()
    {
        addValueList(JsonValue.NULL);
        return this;
    }

    public JsonArrayBuilder add(JsonObjectBuilder builder)
    {
        if(builder == null)
        {
            throw new NullPointerException(JsonMessages.ARRBUILDER_OBJECT_BUILDER_NULL());
        } else
        {
            addValueList(builder.build());
            return this;
        }
    }

    public JsonArrayBuilder add(JsonArrayBuilder builder)
    {
        if(builder == null)
        {
            throw new NullPointerException(JsonMessages.ARRBUILDER_ARRAY_BUILDER_NULL());
        } else
        {
            addValueList(builder.build());
            return this;
        }
    }

    public JsonArray build()
    {
        List snapshot;
        if(valueList == null)
            snapshot = Collections.emptyList();
        else
            snapshot = Collections.unmodifiableList(valueList);
        valueList = null;
        return new JsonArrayImpl(snapshot, bufferPool);
    }

    private void addValueList(JsonValue value)
    {
        if(valueList == null)
            valueList = new ArrayList();
        valueList.add(value);
    }

    private void validateValue(Object value)
    {
        if(value == null)
            throw new NullPointerException(JsonMessages.ARRBUILDER_VALUE_NULL());
        else
            return;
    }

    private ArrayList valueList;
    private final BufferPool bufferPool;
}
