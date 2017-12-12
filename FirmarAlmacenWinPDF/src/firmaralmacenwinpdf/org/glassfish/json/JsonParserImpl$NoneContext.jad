// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonParserImpl.java

package org.glassfish.json;

import javax.json.stream.JsonParser;

// Referenced classes of package org.glassfish.json:
//            JsonParserImpl, JsonTokenizer

private final class JsonParserImpl$NoneContext extends JsonParserImpl.Context
{

    public javax.json.stream.JsonParser.Event getNextEvent()
    {
        JsonTokenizer.JsonToken token = JsonParserImpl.access$600(JsonParserImpl.this).nextToken();
        if(token == JsonTokenizer.JsonToken.CURLYOPEN)
        {
            JsonParserImpl.Stack.access$900(JsonParserImpl.access$300(JsonParserImpl.this), JsonParserImpl.access$700(JsonParserImpl.this));
            JsonParserImpl.access$702(JsonParserImpl.this, new t(JsonParserImpl.this, null));
            return javax.json.stream.JsonParser.Event.START_OBJECT;
        }
        if(token == JsonTokenizer.JsonToken.SQUAREOPEN)
        {
            JsonParserImpl.Stack.access$900(JsonParserImpl.access$300(JsonParserImpl.this), JsonParserImpl.access$700(JsonParserImpl.this));
            JsonParserImpl.access$702(JsonParserImpl.this, new (JsonParserImpl.this, null));
            return javax.json.stream.JsonParser.Event.START_ARRAY;
        } else
        {
            throw JsonParserImpl.access$1200(JsonParserImpl.this, token, "[CURLYOPEN, SQUAREOPEN]");
        }
    }

    final JsonParserImpl this$0;

    private JsonParserImpl$NoneContext()
    {
        this$0 = JsonParserImpl.this;
        super(JsonParserImpl.this, null);
    }

    JsonParserImpl$NoneContext(JsonParserImpl._cls1 x1)
    {
        this();
    }
}
