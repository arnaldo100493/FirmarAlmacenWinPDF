// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonGeneratorImpl.java

package org.glassfish.json;

import co.org.glassfish.json.api.BufferPool;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.*;
import javax.json.*;
import javax.json.stream.JsonGenerationException;
import javax.json.stream.JsonGenerator;

// Referenced classes of package org.glassfish.json:
//            JsonMessages

class JsonGeneratorImpl
    implements JsonGenerator
{
    private static class Context
    {

        boolean first;
        final Scope scope;

        Context(Scope scope)
        {
            first = true;
            this.scope = scope;
        }
    }

    private static final class Scope extends Enum
    {

        public static Scope[] values()
        {
            return (Scope[])$VALUES.clone();
        }

        public static Scope valueOf(String name)
        {
            return (Scope)Enum.valueOf(org/glassfish/json/JsonGeneratorImpl$Scope, name);
        }

        public static final Scope IN_NONE;
        public static final Scope IN_OBJECT;
        public static final Scope IN_ARRAY;
        private static final Scope $VALUES[];

        static 
        {
            IN_NONE = new Scope("IN_NONE", 0);
            IN_OBJECT = new Scope("IN_OBJECT", 1);
            IN_ARRAY = new Scope("IN_ARRAY", 2);
            $VALUES = (new Scope[] {
                IN_NONE, IN_OBJECT, IN_ARRAY
            });
        }

        private Scope(String s, int i)
        {
            super(s, i);
        }
    }


    JsonGeneratorImpl(Writer writer, BufferPool bufferPool)
    {
        currentContext = new Context(Scope.IN_NONE);
        stack = new ArrayDeque();
        len = 0;
        this.writer = writer;
        this.bufferPool = bufferPool;
        buf = bufferPool.take();
    }

    JsonGeneratorImpl(OutputStream out, BufferPool bufferPool)
    {
        this(out, UTF_8, bufferPool);
    }

    JsonGeneratorImpl(OutputStream out, Charset encoding, BufferPool bufferPool)
    {
        this(((Writer) (new OutputStreamWriter(out, encoding))), bufferPool);
    }

    public void flush()
    {
        flushBuffer();
        try
        {
            writer.flush();
        }
        catch(IOException ioe)
        {
            throw new JsonException(JsonMessages.GENERATOR_FLUSH_IO_ERR(), ioe);
        }
    }

    public JsonGenerator writeStartObject()
    {
        if(currentContext.scope == Scope.IN_OBJECT)
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        if(currentContext.scope == Scope.IN_NONE && !currentContext.first)
        {
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_MULTIPLE_TEXT());
        } else
        {
            writeComma();
            writeChar('{');
            stack.push(currentContext);
            currentContext = new Context(Scope.IN_OBJECT);
            return this;
        }
    }

    public JsonGenerator writeStartObject(String name)
    {
        if(currentContext.scope != Scope.IN_OBJECT)
        {
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        } else
        {
            writeName(name);
            writeChar('{');
            stack.push(currentContext);
            currentContext = new Context(Scope.IN_OBJECT);
            return this;
        }
    }

    private JsonGenerator writeName(String name)
    {
        writeComma();
        writeEscapedString(name);
        writeChar(':');
        return this;
    }

    public JsonGenerator write(String name, String fieldValue)
    {
        if(currentContext.scope != Scope.IN_OBJECT)
        {
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        } else
        {
            writeName(name);
            writeEscapedString(fieldValue);
            return this;
        }
    }

    public JsonGenerator write(String name, int value)
    {
        if(currentContext.scope != Scope.IN_OBJECT)
        {
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        } else
        {
            writeName(name);
            writeInt(value);
            return this;
        }
    }

    public JsonGenerator write(String name, long value)
    {
        if(currentContext.scope != Scope.IN_OBJECT)
        {
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        } else
        {
            writeName(name);
            writeString(String.valueOf(value));
            return this;
        }
    }

    public JsonGenerator write(String name, double value)
    {
        if(currentContext.scope != Scope.IN_OBJECT)
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        if(Double.isInfinite(value) || Double.isNaN(value))
        {
            throw new NumberFormatException(JsonMessages.GENERATOR_DOUBLE_INFINITE_NAN());
        } else
        {
            writeName(name);
            writeString(String.valueOf(value));
            return this;
        }
    }

    public JsonGenerator write(String name, BigInteger value)
    {
        if(currentContext.scope != Scope.IN_OBJECT)
        {
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        } else
        {
            writeName(name);
            writeString(String.valueOf(value));
            return this;
        }
    }

    public JsonGenerator write(String name, BigDecimal value)
    {
        if(currentContext.scope != Scope.IN_OBJECT)
        {
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        } else
        {
            writeName(name);
            writeString(String.valueOf(value));
            return this;
        }
    }

    public JsonGenerator write(String name, boolean value)
    {
        if(currentContext.scope != Scope.IN_OBJECT)
        {
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        } else
        {
            writeName(name);
            writeString(value ? "true" : "false");
            return this;
        }
    }

    public JsonGenerator writeNull(String name)
    {
        if(currentContext.scope != Scope.IN_OBJECT)
        {
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        } else
        {
            writeName(name);
            writeString("null");
            return this;
        }
    }

    public JsonGenerator write(JsonValue value)
    {
        if(currentContext.scope != Scope.IN_ARRAY)
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
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
        default:
            break;

        case 1: // '\001'
            JsonArray array = (JsonArray)value;
            writeStartArray();
            JsonValue child;
            for(Iterator i$ = array.iterator(); i$.hasNext(); write(child))
                child = (JsonValue)i$.next();

            writeEnd();
            break;

        case 2: // '\002'
            JsonObject object = (JsonObject)value;
            writeStartObject();
            java.util.Map.Entry member;
            for(Iterator i$ = object.entrySet().iterator(); i$.hasNext(); write((String)member.getKey(), (JsonValue)member.getValue()))
                member = (java.util.Map.Entry)i$.next();

            writeEnd();
            break;

        case 3: // '\003'
            JsonString str = (JsonString)value;
            write(str.getString());
            break;

        case 4: // '\004'
            JsonNumber number = (JsonNumber)value;
            writeValue(number.toString());
            break;

        case 5: // '\005'
            write(true);
            break;

        case 6: // '\006'
            write(false);
            break;

        case 7: // '\007'
            writeNull();
            break;
        }
        return this;
    }

    public JsonGenerator writeStartArray()
    {
        if(currentContext.scope == Scope.IN_OBJECT)
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        if(currentContext.scope == Scope.IN_NONE && !currentContext.first)
        {
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_MULTIPLE_TEXT());
        } else
        {
            writeComma();
            writeChar('[');
            stack.push(currentContext);
            currentContext = new Context(Scope.IN_ARRAY);
            return this;
        }
    }

    public JsonGenerator writeStartArray(String name)
    {
        if(currentContext.scope != Scope.IN_OBJECT)
        {
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        } else
        {
            writeName(name);
            writeChar('[');
            stack.push(currentContext);
            currentContext = new Context(Scope.IN_ARRAY);
            return this;
        }
    }

    public JsonGenerator write(String name, JsonValue value)
    {
        if(currentContext.scope != Scope.IN_OBJECT)
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        switch(_cls1..SwitchMap.javax.json.JsonValue.ValueType[value.getValueType().ordinal()])
        {
        default:
            break;

        case 1: // '\001'
            JsonArray array = (JsonArray)value;
            writeStartArray(name);
            JsonValue child;
            for(Iterator i$ = array.iterator(); i$.hasNext(); write(child))
                child = (JsonValue)i$.next();

            writeEnd();
            break;

        case 2: // '\002'
            JsonObject object = (JsonObject)value;
            writeStartObject(name);
            java.util.Map.Entry member;
            for(Iterator i$ = object.entrySet().iterator(); i$.hasNext(); write((String)member.getKey(), (JsonValue)member.getValue()))
                member = (java.util.Map.Entry)i$.next();

            writeEnd();
            break;

        case 3: // '\003'
            JsonString str = (JsonString)value;
            write(name, str.getString());
            break;

        case 4: // '\004'
            JsonNumber number = (JsonNumber)value;
            writeValue(name, number.toString());
            break;

        case 5: // '\005'
            write(name, true);
            break;

        case 6: // '\006'
            write(name, false);
            break;

        case 7: // '\007'
            writeNull(name);
            break;
        }
        return this;
    }

    public JsonGenerator write(String value)
    {
        if(currentContext.scope != Scope.IN_ARRAY)
        {
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        } else
        {
            writeComma();
            writeEscapedString(value);
            return this;
        }
    }

    public JsonGenerator write(int value)
    {
        if(currentContext.scope != Scope.IN_ARRAY)
        {
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        } else
        {
            writeComma();
            writeInt(value);
            return this;
        }
    }

    public JsonGenerator write(long value)
    {
        if(currentContext.scope != Scope.IN_ARRAY)
        {
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        } else
        {
            writeValue(String.valueOf(value));
            return this;
        }
    }

    public JsonGenerator write(double value)
    {
        if(currentContext.scope != Scope.IN_ARRAY)
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        if(Double.isInfinite(value) || Double.isNaN(value))
        {
            throw new NumberFormatException(JsonMessages.GENERATOR_DOUBLE_INFINITE_NAN());
        } else
        {
            writeValue(String.valueOf(value));
            return this;
        }
    }

    public JsonGenerator write(BigInteger value)
    {
        if(currentContext.scope != Scope.IN_ARRAY)
        {
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        } else
        {
            writeValue(value.toString());
            return this;
        }
    }

    public JsonGenerator write(BigDecimal value)
    {
        if(currentContext.scope != Scope.IN_ARRAY)
        {
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        } else
        {
            writeValue(value.toString());
            return this;
        }
    }

    public JsonGenerator write(boolean value)
    {
        if(currentContext.scope != Scope.IN_ARRAY)
        {
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        } else
        {
            writeComma();
            writeString(value ? "true" : "false");
            return this;
        }
    }

    public JsonGenerator writeNull()
    {
        if(currentContext.scope != Scope.IN_ARRAY)
        {
            throw new JsonGenerationException(JsonMessages.GENERATOR_ILLEGAL_METHOD(currentContext.scope));
        } else
        {
            writeComma();
            writeString("null");
            return this;
        }
    }

    private void writeValue(String value)
    {
        writeComma();
        writeString(value);
    }

    private void writeValue(String name, String value)
    {
        writeComma();
        writeEscapedString(name);
        writeChar(':');
        writeString(value);
    }

    public JsonGenerator writeEnd()
    {
        if(currentContext.scope == Scope.IN_NONE)
        {
            throw new JsonGenerationException("writeEnd() cannot be called in no context");
        } else
        {
            writeChar(currentContext.scope != Scope.IN_ARRAY ? '}' : ']');
            currentContext = (Context)stack.pop();
            return this;
        }
    }

    protected void writeComma()
    {
        if(!currentContext.first)
            writeChar(',');
        currentContext.first = false;
    }

    public void close()
    {
        if(currentContext.scope != Scope.IN_NONE || currentContext.first)
            throw new JsonGenerationException(JsonMessages.GENERATOR_INCOMPLETE_JSON());
        flushBuffer();
        try
        {
            writer.close();
        }
        catch(IOException ioe)
        {
            throw new JsonException(JsonMessages.GENERATOR_CLOSE_IO_ERR(), ioe);
        }
        bufferPool.recycle(buf);
    }

    void writeEscapedString(String string)
    {
        writeChar('"');
        int len = string.length();
        for(int i = 0; i < len; i++)
        {
            int begin = i;
            int end = i;
            char c = string.charAt(i);
            do
            {
                if(c < ' ' || c > '\0' || c == '"' || c == '\\')
                    break;
                end = ++i;
                if(i >= len)
                    break;
                c = string.charAt(i);
            } while(true);
            if(begin < end)
            {
                writeString(string, begin, end);
                if(i == len)
                    break;
            }
            switch(c)
            {
            case 34: // '"'
            case 92: // '\\'
                writeChar('\\');
                writeChar(c);
                break;

            case 8: // '\b'
                writeChar('\\');
                writeChar('b');
                break;

            case 12: // '\f'
                writeChar('\\');
                writeChar('f');
                break;

            case 10: // '\n'
                writeChar('\\');
                writeChar('n');
                break;

            case 13: // '\r'
                writeChar('\\');
                writeChar('r');
                break;

            case 9: // '\t'
                writeChar('\\');
                writeChar('t');
                break;

            default:
                String hex = (new StringBuilder()).append("000").append(Integer.toHexString(c)).toString();
                writeString((new StringBuilder()).append("\\u").append(hex.substring(hex.length() - 4)).toString());
                break;
            }
        }

        writeChar('"');
    }

    void writeString(String str, int begin, int end)
    {
        do
        {
            if(begin >= end)
                break;
            int no = Math.min(buf.length - len, end - begin);
            str.getChars(begin, begin + no, buf, len);
            begin += no;
            len += no;
            if(len >= buf.length)
                flushBuffer();
        } while(true);
    }

    void writeString(String str)
    {
        writeString(str, 0, str.length());
    }

    void writeChar(char c)
    {
        if(len >= buf.length)
            flushBuffer();
        buf[len++] = c;
    }

    void writeInt(int num)
    {
        int size;
        if(num == 0x80000000)
            size = INT_MIN_VALUE_CHARS.length;
        else
            size = num >= 0 ? stringSize(num) : stringSize(-num) + 1;
        if(len + size >= buf.length)
            flushBuffer();
        if(num == 0x80000000)
            System.arraycopy(INT_MIN_VALUE_CHARS, 0, buf, len, size);
        else
            fillIntChars(num, buf, len + size);
        len += size;
    }

    void flushBuffer()
    {
        try
        {
            if(len > 0)
            {
                writer.write(buf, 0, len);
                len = 0;
            }
        }
        catch(IOException ioe)
        {
            throw new JsonException(JsonMessages.GENERATOR_WRITE_IO_ERR(), ioe);
        }
    }

    private static int stringSize(int x)
    {
        int i = 0;
        do
        {
            if(x <= INT_CHARS_SIZE_TABLE[i])
                return i + 1;
            i++;
        } while(true);
    }

    private static void fillIntChars(int i, char buf[], int index)
    {
        int charPos = index;
        char sign = '\0';
        if(i < 0)
        {
            sign = '-';
            i = -i;
        }
        while(i >= 0x10000) 
        {
            int q = i / 100;
            int r = i - ((q << 6) + (q << 5) + (q << 2));
            i = q;
            buf[--charPos] = DIGIT_ONES[r];
            buf[--charPos] = DIGIT_TENS[r];
        }
        do
        {
            int q = i * 52429 >>> 19;
            int r = i - ((q << 3) + (q << 1));
            buf[--charPos] = DIGITS[r];
            i = q;
        } while(i != 0);
        if(sign != 0)
            buf[--charPos] = sign;
    }

    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final char INT_MIN_VALUE_CHARS[] = "-2147483648".toCharArray();
    private static final int INT_CHARS_SIZE_TABLE[] = {
        9, 99, 999, 9999, 0x1869f, 0xf423f, 0x98967f, 0x5f5e0ff, 0x3b9ac9ff, 0x7fffffff
    };
    private static final char DIGIT_TENS[] = {
        '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 
        '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', 
        '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', 
        '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', 
        '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', 
        '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', 
        '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', 
        '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', 
        '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', 
        '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'
    };
    private static final char DIGIT_ONES[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
    private static final char DIGITS[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
    private final BufferPool bufferPool;
    private final Writer writer;
    private Context currentContext;
    private final Deque stack;
    private final char buf[];
    private int len;

}
