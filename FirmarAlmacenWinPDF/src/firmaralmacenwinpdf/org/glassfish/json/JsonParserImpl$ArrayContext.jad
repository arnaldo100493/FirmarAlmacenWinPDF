// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonParserImpl.java

package org.glassfish.json;

import javax.json.stream.JsonParser;

// Referenced classes of package org.glassfish.json:
//            JsonParserImpl, JsonTokenizer

private final class JsonParserImpl$ArrayContext extends JsonParserImpl.Context
{

    public javax.json.stream.JsonParser.Event getNextEvent()
    {
        JsonTokenizer.JsonToken token = JsonParserImpl.access$600(JsonParserImpl.this).nextToken();
        if(token == JsonTokenizer.JsonToken.SQUARECLOSE)
        {
            JsonParserImpl.access$702(JsonParserImpl.this, JsonParserImpl.Stack.access$1300(JsonParserImpl.access$300(JsonParserImpl.this)));
            return javax.json.stream.JsonParser.Event.END_ARRAY;
        }
        if(firstValue)
        {
            firstValue = false;
        } else
        {
            if(token != JsonTokenizer.JsonToken.COMMA)
                throw JsonParserImpl.access$1200(JsonParserImpl.this, token, "[COMMA]");
            token = JsonParserImpl.access$600(JsonParserImpl.this).nextToken();
        }
        if(token.isValue())
            return token.getEvent();
        if(token == JsonTokenizer.JsonToken.CURLYOPEN)
        {
            JsonParserImpl.Stack.access$900(JsonParserImpl.access$300(JsonParserImpl.this), JsonParserImpl.access$700(JsonParserImpl.this));
            JsonParserImpl.access$702(JsonParserImpl.this, new (JsonParserImpl.this, null));
            return javax.json.stream.JsonParser.Event.START_OBJECT;
        }
        if(token == JsonTokenizer.JsonToken.SQUAREOPEN)
        {
            JsonParserImpl.Stack.access$900(JsonParserImpl.access$300(JsonParserImpl.this), JsonParserImpl.access$700(JsonParserImpl.this));
            JsonParserImpl.access$702(JsonParserImpl.this, new JsonParserImpl$ArrayContext());
            return javax.json.stream.JsonParser.Event.START_ARRAY;
        } else
        {
            throw JsonParserImpl.access$1200(JsonParserImpl.this, token, "[CURLYOPEN, SQUAREOPEN, STRING, NUMBER, TRUE, FALSE, NULL]");
        }
    }

    private boolean firstValue;
    final JsonParserImpl this$0;

    private JsonParserImpl$ArrayContext()
    {
        this$0 = JsonParserImpl.this;
        super(JsonParserImpl.this, null);
        firstValue = true;
    }

    JsonParserImpl$ArrayContext(JsonParserImpl._cls1 x1)
    {
        this();
    }
}
