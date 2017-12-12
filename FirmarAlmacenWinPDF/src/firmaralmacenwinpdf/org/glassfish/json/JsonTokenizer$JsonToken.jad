// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonTokenizer.java

package org.glassfish.json;

import javax.json.stream.JsonParser;

// Referenced classes of package org.glassfish.json:
//            JsonTokenizer

static final class JsonTokenizer$JsonToken extends Enum
{

    public static JsonTokenizer$JsonToken[] values()
    {
        return (JsonTokenizer$JsonToken[])$VALUES.clone();
    }

    public static JsonTokenizer$JsonToken valueOf(String name)
    {
        return (JsonTokenizer$JsonToken)Enum.valueOf(org/glassfish/json/JsonTokenizer$JsonToken, name);
    }

    javax.json.stream.JsonParser.Event getEvent()
    {
        return event;
    }

    boolean isValue()
    {
        return value;
    }

    public static final JsonTokenizer$JsonToken CURLYOPEN;
    public static final JsonTokenizer$JsonToken SQUAREOPEN;
    public static final JsonTokenizer$JsonToken COLON;
    public static final JsonTokenizer$JsonToken COMMA;
    public static final JsonTokenizer$JsonToken STRING;
    public static final JsonTokenizer$JsonToken NUMBER;
    public static final JsonTokenizer$JsonToken TRUE;
    public static final JsonTokenizer$JsonToken FALSE;
    public static final JsonTokenizer$JsonToken NULL;
    public static final JsonTokenizer$JsonToken CURLYCLOSE;
    public static final JsonTokenizer$JsonToken SQUARECLOSE;
    public static final JsonTokenizer$JsonToken EOF;
    private final javax.json.stream.JsonParser.Event event;
    private final boolean value;
    private static final JsonTokenizer$JsonToken $VALUES[];

    static 
    {
        CURLYOPEN = new JsonTokenizer$JsonToken("CURLYOPEN", 0, javax.json.stream.JsonParser.Event.START_OBJECT, false);
        SQUAREOPEN = new JsonTokenizer$JsonToken("SQUAREOPEN", 1, javax.json.stream.JsonParser.Event.START_ARRAY, false);
        COLON = new JsonTokenizer$JsonToken("COLON", 2, null, false);
        COMMA = new JsonTokenizer$JsonToken("COMMA", 3, null, false);
        STRING = new JsonTokenizer$JsonToken("STRING", 4, javax.json.stream.JsonParser.Event.VALUE_STRING, true);
        NUMBER = new JsonTokenizer$JsonToken("NUMBER", 5, javax.json.stream.JsonParser.Event.VALUE_NUMBER, true);
        TRUE = new JsonTokenizer$JsonToken("TRUE", 6, javax.json.stream.JsonParser.Event.VALUE_TRUE, true);
        FALSE = new JsonTokenizer$JsonToken("FALSE", 7, javax.json.stream.JsonParser.Event.VALUE_FALSE, true);
        NULL = new JsonTokenizer$JsonToken("NULL", 8, javax.json.stream.JsonParser.Event.VALUE_NULL, true);
        CURLYCLOSE = new JsonTokenizer$JsonToken("CURLYCLOSE", 9, javax.json.stream.JsonParser.Event.END_OBJECT, false);
        SQUARECLOSE = new JsonTokenizer$JsonToken("SQUARECLOSE", 10, javax.json.stream.JsonParser.Event.END_ARRAY, false);
        EOF = new JsonTokenizer$JsonToken("EOF", 11, null, false);
        $VALUES = (new JsonTokenizer$JsonToken[] {
            CURLYOPEN, SQUAREOPEN, COLON, COMMA, STRING, NUMBER, TRUE, FALSE, NULL, CURLYCLOSE, 
            SQUARECLOSE, EOF
        });
    }

    private JsonTokenizer$JsonToken(String s, int i, javax.json.stream.JsonParser.Event event, boolean value)
    {
        super(s, i);
        this.event = event;
        this.value = value;
    }
}
