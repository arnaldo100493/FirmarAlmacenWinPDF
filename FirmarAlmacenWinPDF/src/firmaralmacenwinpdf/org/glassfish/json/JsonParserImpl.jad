// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonParserImpl.java

package org.glassfish.json;

import co.org.glassfish.json.api.BufferPool;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.json.JsonException;
import javax.json.stream.*;

// Referenced classes of package org.glassfish.json:
//            JsonTokenizer, UnicodeDetectingInputStream, JsonMessages

public class JsonParserImpl
    implements JsonParser
{
    private final class ArrayContext extends Context
    {

        public javax.json.stream.JsonParser.Event getNextEvent()
        {
            JsonTokenizer.JsonToken token = tokenizer.nextToken();
            if(token == JsonTokenizer.JsonToken.SQUARECLOSE)
            {
                currentContext = stack.pop();
                return javax.json.stream.JsonParser.Event.END_ARRAY;
            }
            if(firstValue)
            {
                firstValue = false;
            } else
            {
                if(token != JsonTokenizer.JsonToken.COMMA)
                    throw parsingException(token, "[COMMA]");
                token = tokenizer.nextToken();
            }
            if(token.isValue())
                return token.getEvent();
            if(token == JsonTokenizer.JsonToken.CURLYOPEN)
            {
                stack.push(currentContext);
                currentContext = new ObjectContext();
                return javax.json.stream.JsonParser.Event.START_OBJECT;
            }
            if(token == JsonTokenizer.JsonToken.SQUAREOPEN)
            {
                stack.push(currentContext);
                currentContext = new ArrayContext();
                return javax.json.stream.JsonParser.Event.START_ARRAY;
            } else
            {
                throw parsingException(token, "[CURLYOPEN, SQUAREOPEN, STRING, NUMBER, TRUE, FALSE, NULL]");
            }
        }

        private boolean firstValue;
        final JsonParserImpl this$0;

        private ArrayContext()
        {
            this$0 = JsonParserImpl.this;
            super();
            firstValue = true;
        }

    }

    private final class ObjectContext extends Context
    {

        public javax.json.stream.JsonParser.Event getNextEvent()
        {
            JsonTokenizer.JsonToken token = tokenizer.nextToken();
            if(currentEvent == javax.json.stream.JsonParser.Event.KEY_NAME)
            {
                if(token != JsonTokenizer.JsonToken.COLON)
                    throw parsingException(token, "[COLON]");
                token = tokenizer.nextToken();
                if(token.isValue())
                    return token.getEvent();
                if(token == JsonTokenizer.JsonToken.CURLYOPEN)
                {
                    stack.push(currentContext);
                    currentContext = new ObjectContext();
                    return javax.json.stream.JsonParser.Event.START_OBJECT;
                }
                if(token == JsonTokenizer.JsonToken.SQUAREOPEN)
                {
                    stack.push(currentContext);
                    currentContext = new ArrayContext();
                    return javax.json.stream.JsonParser.Event.START_ARRAY;
                } else
                {
                    throw parsingException(token, "[CURLYOPEN, SQUAREOPEN, STRING, NUMBER, TRUE, FALSE, NULL]");
                }
            }
            if(token == JsonTokenizer.JsonToken.CURLYCLOSE)
            {
                currentContext = stack.pop();
                return javax.json.stream.JsonParser.Event.END_OBJECT;
            }
            if(firstValue)
            {
                firstValue = false;
            } else
            {
                if(token != JsonTokenizer.JsonToken.COMMA)
                    throw parsingException(token, "[COMMA]");
                token = tokenizer.nextToken();
            }
            if(token == JsonTokenizer.JsonToken.STRING)
                return javax.json.stream.JsonParser.Event.KEY_NAME;
            else
                throw parsingException(token, "[STRING]");
        }

        private boolean firstValue;
        final JsonParserImpl this$0;

        private ObjectContext()
        {
            this$0 = JsonParserImpl.this;
            super();
            firstValue = true;
        }

    }

    private final class NoneContext extends Context
    {

        public javax.json.stream.JsonParser.Event getNextEvent()
        {
            JsonTokenizer.JsonToken token = tokenizer.nextToken();
            if(token == JsonTokenizer.JsonToken.CURLYOPEN)
            {
                stack.push(currentContext);
                currentContext = new ObjectContext();
                return javax.json.stream.JsonParser.Event.START_OBJECT;
            }
            if(token == JsonTokenizer.JsonToken.SQUAREOPEN)
            {
                stack.push(currentContext);
                currentContext = new ArrayContext();
                return javax.json.stream.JsonParser.Event.START_ARRAY;
            } else
            {
                throw parsingException(token, "[CURLYOPEN, SQUAREOPEN]");
            }
        }

        final JsonParserImpl this$0;

        private NoneContext()
        {
            this$0 = JsonParserImpl.this;
            super();
        }

    }

    private abstract class Context
    {

        abstract javax.json.stream.JsonParser.Event getNextEvent();

        Context next;
        final JsonParserImpl this$0;

        private Context()
        {
            this$0 = JsonParserImpl.this;
            super();
        }

    }

    private static final class Stack
    {

        private void push(Context context)
        {
            context.next = head;
            head = context;
        }

        private Context pop()
        {
            if(head == null)
            {
                throw new NoSuchElementException();
            } else
            {
                Context temp = head;
                head = head.next;
                return temp;
            }
        }

        private boolean isEmpty()
        {
            return head == null;
        }

        private Context head;




        private Stack()
        {
        }

    }

    private class StateIterator
        implements Iterator
    {

        public boolean hasNext()
        {
            if(stack.isEmpty() && (currentEvent == javax.json.stream.JsonParser.Event.END_ARRAY || currentEvent == javax.json.stream.JsonParser.Event.END_OBJECT))
            {
                JsonTokenizer.JsonToken token = tokenizer.nextToken();
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
                return currentEvent = currentContext.getNextEvent();
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

        private StateIterator()
        {
            this$0 = JsonParserImpl.this;
            super();
        }

    }


    public JsonParserImpl(Reader reader, BufferPool bufferPool)
    {
        currentContext = new NoneContext();
        stack = new Stack();
        tokenizer = new JsonTokenizer(reader, bufferPool);
        stateIterator = new StateIterator();
    }

    public JsonParserImpl(InputStream in, BufferPool bufferPool)
    {
        currentContext = new NoneContext();
        stack = new Stack();
        UnicodeDetectingInputStream uin = new UnicodeDetectingInputStream(in);
        tokenizer = new JsonTokenizer(new InputStreamReader(uin, uin.getCharset()), bufferPool);
        stateIterator = new StateIterator();
    }

    public JsonParserImpl(InputStream in, Charset encoding, BufferPool bufferPool)
    {
        currentContext = new NoneContext();
        stack = new Stack();
        tokenizer = new JsonTokenizer(new InputStreamReader(in, encoding), bufferPool);
        stateIterator = new StateIterator();
    }

    public String getString()
    {
        if(currentEvent == javax.json.stream.JsonParser.Event.KEY_NAME || currentEvent == javax.json.stream.JsonParser.Event.VALUE_STRING || currentEvent == javax.json.stream.JsonParser.Event.VALUE_NUMBER)
            return tokenizer.getValue();
        else
            throw new IllegalStateException(JsonMessages.PARSER_GETSTRING_ERR(currentEvent));
    }

    public boolean isIntegralNumber()
    {
        if(currentEvent != javax.json.stream.JsonParser.Event.VALUE_NUMBER)
            throw new IllegalStateException(JsonMessages.PARSER_ISINTEGRALNUMBER_ERR(currentEvent));
        else
            return tokenizer.isIntegral();
    }

    public int getInt()
    {
        if(currentEvent != javax.json.stream.JsonParser.Event.VALUE_NUMBER)
            throw new IllegalStateException(JsonMessages.PARSER_GETINT_ERR(currentEvent));
        else
            return tokenizer.getInt();
    }

    boolean isDefinitelyInt()
    {
        return tokenizer.isDefinitelyInt();
    }

    public long getLong()
    {
        if(currentEvent != javax.json.stream.JsonParser.Event.VALUE_NUMBER)
            throw new IllegalStateException(JsonMessages.PARSER_GETLONG_ERR(currentEvent));
        else
            return tokenizer.getBigDecimal().longValue();
    }

    public BigDecimal getBigDecimal()
    {
        if(currentEvent != javax.json.stream.JsonParser.Event.VALUE_NUMBER)
            throw new IllegalStateException(JsonMessages.PARSER_GETBIGDECIMAL_ERR(currentEvent));
        else
            return tokenizer.getBigDecimal();
    }

    public JsonLocation getLocation()
    {
        return tokenizer.getLocation();
    }

    public JsonLocation getLastCharLocation()
    {
        return tokenizer.getLastCharLocation();
    }

    public boolean hasNext()
    {
        return stateIterator.hasNext();
    }

    public javax.json.stream.JsonParser.Event next()
    {
        return stateIterator.next();
    }

    public void close()
    {
        try
        {
            tokenizer.close();
        }
        catch(IOException e)
        {
            throw new JsonException(JsonMessages.PARSER_TOKENIZER_CLOSE_IO(), e);
        }
    }

    private JsonParsingException parsingException(JsonTokenizer.JsonToken token, String expectedTokens)
    {
        JsonLocation location = getLastCharLocation();
        return new JsonParsingException(JsonMessages.PARSER_INVALID_TOKEN(token, location, expectedTokens), location);
    }

    private Context currentContext;
    private javax.json.stream.JsonParser.Event currentEvent;
    private final Stack stack;
    private final StateIterator stateIterator;
    private final JsonTokenizer tokenizer;







}
