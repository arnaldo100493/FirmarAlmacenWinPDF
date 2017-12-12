// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonReaderImpl.java

package org.glassfish.json;

import co.org.glassfish.json.api.BufferPool;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import javax.json.*;
import javax.json.stream.JsonParser;

// Referenced classes of package org.glassfish.json:
//            JsonParserImpl, JsonArrayBuilderImpl, JsonObjectBuilderImpl, JsonMessages

class JsonReaderImpl
    implements JsonReader
{

    JsonReaderImpl(Reader reader, BufferPool bufferPool)
    {
        parser = new JsonParserImpl(reader, bufferPool);
        this.bufferPool = bufferPool;
    }

    JsonReaderImpl(InputStream in, BufferPool bufferPool)
    {
        parser = new JsonParserImpl(in, bufferPool);
        this.bufferPool = bufferPool;
    }

    JsonReaderImpl(InputStream in, Charset charset, BufferPool bufferPool)
    {
        parser = new JsonParserImpl(in, charset, bufferPool);
        this.bufferPool = bufferPool;
    }

    public JsonStructure read()
    {
        if(readDone)
            throw new IllegalStateException(JsonMessages.READER_READ_ALREADY_CALLED());
        readDone = true;
        if(parser.hasNext())
        {
            javax.json.stream.JsonParser.Event e = parser.next();
            if(e == javax.json.stream.JsonParser.Event.START_ARRAY)
                return readArray(new JsonArrayBuilderImpl(bufferPool));
            if(e == javax.json.stream.JsonParser.Event.START_OBJECT)
                return readObject(new JsonObjectBuilderImpl(bufferPool));
        }
        throw new JsonException("Internal Error");
    }

    public JsonObject readObject()
    {
        if(readDone)
            throw new IllegalStateException(JsonMessages.READER_READ_ALREADY_CALLED());
        readDone = true;
        if(parser.hasNext())
        {
            javax.json.stream.JsonParser.Event e = parser.next();
            if(e == javax.json.stream.JsonParser.Event.START_OBJECT)
                return readObject(((JsonObjectBuilder) (new JsonObjectBuilderImpl(bufferPool))));
            if(e == javax.json.stream.JsonParser.Event.START_ARRAY)
                throw new JsonException(JsonMessages.READER_EXPECTED_OBJECT_GOT_ARRAY());
        }
        throw new JsonException("Internal Error");
    }

    public JsonArray readArray()
    {
        if(readDone)
            throw new IllegalStateException(JsonMessages.READER_READ_ALREADY_CALLED());
        readDone = true;
        if(parser.hasNext())
        {
            javax.json.stream.JsonParser.Event e = parser.next();
            if(e == javax.json.stream.JsonParser.Event.START_ARRAY)
                return readArray(((JsonArrayBuilder) (new JsonArrayBuilderImpl(bufferPool))));
            if(e == javax.json.stream.JsonParser.Event.START_OBJECT)
                throw new JsonException(JsonMessages.READER_EXPECTED_ARRAY_GOT_OBJECT());
        }
        throw new JsonException("Internal Error");
    }

    public void close()
    {
        readDone = true;
        parser.close();
    }

    private JsonArray readArray(JsonArrayBuilder builder)
    {
        do
        {
            if(!parser.hasNext())
                break;
            javax.json.stream.JsonParser.Event e = parser.next();
            static class _cls1
            {

                static final int $SwitchMap$javax$json$stream$JsonParser$Event[];

                static 
                {
                    $SwitchMap$javax$json$stream$JsonParser$Event = new int[javax.json.stream.JsonParser.Event.values().length];
                    try
                    {
                        $SwitchMap$javax$json$stream$JsonParser$Event[javax.json.stream.JsonParser.Event.START_ARRAY.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError ex) { }
                    try
                    {
                        $SwitchMap$javax$json$stream$JsonParser$Event[javax.json.stream.JsonParser.Event.START_OBJECT.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError ex) { }
                    try
                    {
                        $SwitchMap$javax$json$stream$JsonParser$Event[javax.json.stream.JsonParser.Event.VALUE_STRING.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError ex) { }
                    try
                    {
                        $SwitchMap$javax$json$stream$JsonParser$Event[javax.json.stream.JsonParser.Event.VALUE_NUMBER.ordinal()] = 4;
                    }
                    catch(NoSuchFieldError ex) { }
                    try
                    {
                        $SwitchMap$javax$json$stream$JsonParser$Event[javax.json.stream.JsonParser.Event.VALUE_TRUE.ordinal()] = 5;
                    }
                    catch(NoSuchFieldError ex) { }
                    try
                    {
                        $SwitchMap$javax$json$stream$JsonParser$Event[javax.json.stream.JsonParser.Event.VALUE_FALSE.ordinal()] = 6;
                    }
                    catch(NoSuchFieldError ex) { }
                    try
                    {
                        $SwitchMap$javax$json$stream$JsonParser$Event[javax.json.stream.JsonParser.Event.VALUE_NULL.ordinal()] = 7;
                    }
                    catch(NoSuchFieldError ex) { }
                    try
                    {
                        $SwitchMap$javax$json$stream$JsonParser$Event[javax.json.stream.JsonParser.Event.END_ARRAY.ordinal()] = 8;
                    }
                    catch(NoSuchFieldError ex) { }
                    try
                    {
                        $SwitchMap$javax$json$stream$JsonParser$Event[javax.json.stream.JsonParser.Event.KEY_NAME.ordinal()] = 9;
                    }
                    catch(NoSuchFieldError ex) { }
                    try
                    {
                        $SwitchMap$javax$json$stream$JsonParser$Event[javax.json.stream.JsonParser.Event.END_OBJECT.ordinal()] = 10;
                    }
                    catch(NoSuchFieldError ex) { }
                }
            }

            switch(_cls1..SwitchMap.javax.json.stream.JsonParser.Event[e.ordinal()])
            {
            case 1: // '\001'
                JsonArray array = readArray(((JsonArrayBuilder) (new JsonArrayBuilderImpl(bufferPool))));
                builder.add(array);
                break;

            case 2: // '\002'
                JsonObject object = readObject(new JsonObjectBuilderImpl(bufferPool));
                builder.add(object);
                break;

            case 3: // '\003'
                builder.add(parser.getString());
                break;

            case 4: // '\004'
                if(parser.isDefinitelyInt())
                    builder.add(parser.getInt());
                else
                    builder.add(parser.getBigDecimal());
                break;

            case 5: // '\005'
                builder.add(JsonValue.TRUE);
                break;

            case 6: // '\006'
                builder.add(JsonValue.FALSE);
                break;

            case 7: // '\007'
                builder.addNull();
                break;

            case 8: // '\b'
                return builder.build();

            default:
                throw new JsonException("Internal Error");
            }
        } while(true);
        throw new JsonException("Internal Error");
    }

    private JsonObject readObject(JsonObjectBuilder builder)
    {
        String key = null;
        do
        {
            if(!parser.hasNext())
                break;
            javax.json.stream.JsonParser.Event e = parser.next();
            switch(_cls1..SwitchMap.javax.json.stream.JsonParser.Event[e.ordinal()])
            {
            case 1: // '\001'
                JsonArray array = readArray(new JsonArrayBuilderImpl(bufferPool));
                builder.add(key, array);
                break;

            case 2: // '\002'
                JsonObject object = readObject(((JsonObjectBuilder) (new JsonObjectBuilderImpl(bufferPool))));
                builder.add(key, object);
                break;

            case 9: // '\t'
                key = parser.getString();
                break;

            case 3: // '\003'
                builder.add(key, parser.getString());
                break;

            case 4: // '\004'
                if(parser.isDefinitelyInt())
                    builder.add(key, parser.getInt());
                else
                    builder.add(key, parser.getBigDecimal());
                break;

            case 5: // '\005'
                builder.add(key, JsonValue.TRUE);
                break;

            case 6: // '\006'
                builder.add(key, JsonValue.FALSE);
                break;

            case 7: // '\007'
                builder.addNull(key);
                break;

            case 10: // '\n'
                return builder.build();

            case 8: // '\b'
            default:
                throw new JsonException("Internal Error");
            }
        } while(true);
        throw new JsonException("Internal Error");
    }

    private final JsonParserImpl parser;
    private boolean readDone;
    private final BufferPool bufferPool;
}
