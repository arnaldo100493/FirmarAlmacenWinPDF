// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonParserImpl.java

package org.glassfish.json;

import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParsingException;

// Referenced classes of package org.glassfish.json:
//            JsonParserImpl, JsonTokenizer, JsonMessages

private class JsonParserImpl$StateIterator
    implements Iterator
{

    public boolean hasNext()
    {
        if(JsonParserImpl.Stack.access$400(JsonParserImpl.access$300(JsonParserImpl.this)) && (JsonParserImpl.access$500(JsonParserImpl.this) == javax.json.stream.JsonParser.Event.END_ARRAY || JsonParserImpl.access$500(JsonParserImpl.this) == javax.json.stream.JsonParser.Event.END_OBJECT))
        {
            JsonTokenizer.JsonToken token = JsonParserImpl.access$600(JsonParserImpl.this).nextToken();
            if(token != JsonTokenizer.JsonToken.EOF)
                throw new JsonParsingException(JsonMessages.PARSER_EXPECTED_EOF(token), getLastCharLocation());
            else
                return false;
        } else
        {
            return true;
        }
    }

    public javax.json.stream.JsonParser.Event next()
    {
        if(!hasNext())
            throw new NoSuchElementException();
        else
            return JsonParserImpl.access$502(JsonParserImpl.this, JsonParserImpl.access$700(JsonParserImpl.this).getNextEvent());
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

    public volatile Object next()
    {
        return next();
    }

    final JsonParserImpl this$0;

    private JsonParserImpl$StateIterator()
    {
        this$0 = JsonParserImpl.this;
        super();
    }

    JsonParserImpl$StateIterator(JsonParserImpl._cls1 x1)
    {
        this();
    }
}
