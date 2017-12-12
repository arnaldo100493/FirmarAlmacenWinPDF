// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonStructureParser.java

package org.glassfish.json;

import java.math.BigDecimal;
import java.util.*;
import javax.json.*;
import javax.json.stream.JsonLocation;
import javax.json.stream.JsonParser;

// Referenced classes of package org.glassfish.json:
//            JsonMessages, JsonLocationImpl

class JsonStructureParser
    implements JsonParser
{
    private static class ObjectScope extends Scope
    {

        public boolean hasNext()
        {
            return it.hasNext();
        }

        public java.util.Map.Entry next()
        {
            java.util.Map.Entry next = (java.util.Map.Entry)it.next();
            key = (String)next.getKey();
            value = (JsonValue)next.getValue();
            return next;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        JsonValue getJsonValue()
        {
            return value;
        }

        public volatile Object next()
        {
            return next();
        }

        private final Iterator it;
        private JsonValue value;
        private String key;


        ObjectScope(JsonObject object)
        {
            it = object.entrySet().iterator();
        }
    }

    private static class ArrayScope extends Scope
    {

        public boolean hasNext()
        {
            return it.hasNext();
        }

        public JsonValue next()
        {
            value = (JsonValue)it.next();
            return value;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        JsonValue getJsonValue()
        {
            return value;
        }

        public volatile Object next()
        {
            return next();
        }

        private final Iterator it;
        private JsonValue value;

        ArrayScope(JsonArray array)
        {
            it = array.iterator();
        }
    }

    private static abstract class Scope
        implements Iterator
    {

        abstract JsonValue getJsonValue();

        static Scope createScope(JsonValue value)
        {
            if(value instanceof JsonArray)
                return new ArrayScope((JsonArray)value);
            if(value instanceof JsonObject)
                return new ObjectScope((JsonObject)value);
            else
                throw new JsonException((new StringBuilder()).append("Cannot be called for value=").append(value).toString());
        }

        private Scope()
        {
        }

    }


    JsonStructureParser(JsonArray array)
    {
        scopeStack = new ArrayDeque();
        current = new ArrayScope(array);
    }

    JsonStructureParser(JsonObject object)
    {
        scopeStack = new ArrayDeque();
        current = new ObjectScope(object);
    }

    public String getString()
    {
        if(state == javax.json.stream.JsonParser.Event.KEY_NAME)
            return ((ObjectScope)current).key;
        if(state == javax.json.stream.JsonParser.Event.VALUE_STRING)
            return ((JsonString)current.getJsonValue()).getString();
        else
            throw new IllegalStateException(JsonMessages.PARSER_GETSTRING_ERR(state));
    }

    public boolean isIntegralNumber()
    {
        if(state == javax.json.stream.JsonParser.Event.VALUE_NUMBER)
            return ((JsonNumber)current.getJsonValue()).isIntegral();
        else
            throw new IllegalStateException(JsonMessages.PARSER_ISINTEGRALNUMBER_ERR(state));
    }

    public int getInt()
    {
        if(state == javax.json.stream.JsonParser.Event.VALUE_NUMBER)
            return ((JsonNumber)current.getJsonValue()).intValue();
        else
            throw new IllegalStateException(JsonMessages.PARSER_GETINT_ERR(state));
    }

    public long getLong()
    {
        if(state == javax.json.stream.JsonParser.Event.VALUE_NUMBER)
            return ((JsonNumber)current.getJsonValue()).longValue();
        else
            throw new IllegalStateException(JsonMessages.PARSER_GETLONG_ERR(state));
    }

    public BigDecimal getBigDecimal()
    {
        if(state == javax.json.stream.JsonParser.Event.VALUE_NUMBER)
            return ((JsonNumber)current.getJsonValue()).bigDecimalValue();
        else
            throw new IllegalStateException(JsonMessages.PARSER_GETBIGDECIMAL_ERR(state));
    }

    public JsonLocation getLocation()
    {
        return JsonLocationImpl.UNKNOWN;
    }

    public boolean hasNext()
    {
        return state != javax.json.stream.JsonParser.Event.END_OBJECT && state != javax.json.stream.JsonParser.Event.END_ARRAY || !scopeStack.isEmpty();
    }

    public javax.json.stream.JsonParser.Event next()
    {
        if(!hasNext())
        {
            throw new NoSuchElementException();
        } else
        {
            transition();
            return state;
        }
    }

    private void transition()
    {
        if(state == null)
        {
            state = (current instanceof ArrayScope) ? javax.json.stream.JsonParser.Event.START_ARRAY : javax.json.stream.JsonParser.Event.START_OBJECT;
        } else
        {
            if(state == javax.json.stream.JsonParser.Event.END_OBJECT || state == javax.json.stream.JsonParser.Event.END_ARRAY)
                current = (Scope)scopeStack.pop();
            if(current instanceof ArrayScope)
            {
                if(current.hasNext())
                {
                    current.next();
                    state = getState(current.getJsonValue());
                    if(state == javax.json.stream.JsonParser.Event.START_ARRAY || state == javax.json.stream.JsonParser.Event.START_OBJECT)
                    {
                        scopeStack.push(current);
                        current = Scope.createScope(current.getJsonValue());
                    }
                } else
                {
                    state = javax.json.stream.JsonParser.Event.END_ARRAY;
                }
            } else
            if(state == javax.json.stream.JsonParser.Event.KEY_NAME)
            {
                state = getState(current.getJsonValue());
                if(state == javax.json.stream.JsonParser.Event.START_ARRAY || state == javax.json.stream.JsonParser.Event.START_OBJECT)
                {
                    scopeStack.push(current);
                    current = Scope.createScope(current.getJsonValue());
                }
            } else
            if(current.hasNext())
            {
                current.next();
                state = javax.json.stream.JsonParser.Event.KEY_NAME;
            } else
            {
                state = javax.json.stream.JsonParser.Event.END_OBJECT;
            }
        }
    }

    public void close()
    {
    }

    private static javax.json.stream.JsonParser.Event getState(JsonValue value)
    {
        static class _cls1
        {

            static final int $SwitchMap$javax$json$JsonValue$ValueType[];

            static 
            {
                $SwitchMap$javax$json$JsonValue$ValueType = new int[javax.json.JsonValue.ValueType.values().length];
                try
                {
                    $SwitchMap$javax$json$JsonValue$ValueType[javax.json.JsonValue.ValueType.ARRAY.ordinal()] = 1;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$javax$json$JsonValue$ValueType[javax.json.JsonValue.ValueType.OBJECT.ordinal()] = 2;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$javax$json$JsonValue$ValueType[javax.json.JsonValue.ValueType.STRING.ordinal()] = 3;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$javax$json$JsonValue$ValueType[javax.json.JsonValue.ValueType.NUMBER.ordinal()] = 4;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$javax$json$JsonValue$ValueType[javax.json.JsonValue.ValueType.TRUE.ordinal()] = 5;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$javax$json$JsonValue$ValueType[javax.json.JsonValue.ValueType.FALSE.ordinal()] = 6;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$javax$json$JsonValue$ValueType[javax.json.JsonValue.ValueType.NULL.ordinal()] = 7;
                }
                catch(NoSuchFieldError ex) { }
            }
        }

        switch(_cls1..SwitchMap.javax.json.JsonValue.ValueType[value.getValueType().ordinal()])
        {
        case 1: // '\001'
            return javax.json.stream.JsonParser.Event.START_ARRAY;

        case 2: // '\002'
            return javax.json.stream.JsonParser.Event.START_OBJECT;

        case 3: // '\003'
            return javax.json.stream.JsonParser.Event.VALUE_STRING;

        case 4: // '\004'
            return javax.json.stream.JsonParser.Event.VALUE_NUMBER;

        case 5: // '\005'
            return javax.json.stream.JsonParser.Event.VALUE_TRUE;

        case 6: // '\006'
            return javax.json.stream.JsonParser.Event.VALUE_FALSE;

        case 7: // '\007'
            return javax.json.stream.JsonParser.Event.VALUE_NULL;
        }
        throw new JsonException((new StringBuilder()).append("Unknown value type=").append(value.getValueType()).toString());
    }

    private Scope current;
    private javax.json.stream.JsonParser.Event state;
    private final Deque scopeStack;
}
