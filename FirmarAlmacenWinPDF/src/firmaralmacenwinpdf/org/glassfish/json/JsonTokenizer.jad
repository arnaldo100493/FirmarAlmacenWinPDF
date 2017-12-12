// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonTokenizer.java

package org.glassfish.json;

import co.org.glassfish.json.api.BufferPool;
import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;
import javax.json.JsonException;
import javax.json.stream.*;

// Referenced classes of package org.glassfish.json:
//            JsonLocationImpl, JsonMessages

final class JsonTokenizer
    implements Closeable
{
    static final class JsonToken extends Enum
    {

        public static JsonToken[] values()
        {
            return (JsonToken[])$VALUES.clone();
        }

        public static JsonToken valueOf(String name)
        {
            return (JsonToken)Enum.valueOf(org/glassfish/json/JsonTokenizer$JsonToken, name);
        }

        javax.json.stream.JsonParser.Event getEvent()
        {
            return event;
        }

        boolean isValue()
        {
            return value;
        }

        public static final JsonToken CURLYOPEN;
        public static final JsonToken SQUAREOPEN;
        public static final JsonToken COLON;
        public static final JsonToken COMMA;
        public static final JsonToken STRING;
        public static final JsonToken NUMBER;
        public static final JsonToken TRUE;
        public static final JsonToken FALSE;
        public static final JsonToken NULL;
        public static final JsonToken CURLYCLOSE;
        public static final JsonToken SQUARECLOSE;
        public static final JsonToken EOF;
        private final javax.json.stream.JsonParser.Event event;
        private final boolean value;
        private static final JsonToken $VALUES[];

        static 
        {
            CURLYOPEN = new JsonToken("CURLYOPEN", 0, javax.json.stream.JsonParser.Event.START_OBJECT, false);
            SQUAREOPEN = new JsonToken("SQUAREOPEN", 1, javax.json.stream.JsonParser.Event.START_ARRAY, false);
            COLON = new JsonToken("COLON", 2, null, false);
            COMMA = new JsonToken("COMMA", 3, null, false);
            STRING = new JsonToken("STRING", 4, javax.json.stream.JsonParser.Event.VALUE_STRING, true);
            NUMBER = new JsonToken("NUMBER", 5, javax.json.stream.JsonParser.Event.VALUE_NUMBER, true);
            TRUE = new JsonToken("TRUE", 6, javax.json.stream.JsonParser.Event.VALUE_TRUE, true);
            FALSE = new JsonToken("FALSE", 7, javax.json.stream.JsonParser.Event.VALUE_FALSE, true);
            NULL = new JsonToken("NULL", 8, javax.json.stream.JsonParser.Event.VALUE_NULL, true);
            CURLYCLOSE = new JsonToken("CURLYCLOSE", 9, javax.json.stream.JsonParser.Event.END_OBJECT, false);
            SQUARECLOSE = new JsonToken("SQUARECLOSE", 10, javax.json.stream.JsonParser.Event.END_ARRAY, false);
            EOF = new JsonToken("EOF", 11, null, false);
            $VALUES = (new JsonToken[] {
                CURLYOPEN, SQUAREOPEN, COLON, COMMA, STRING, NUMBER, TRUE, FALSE, NULL, CURLYCLOSE, 
                SQUARECLOSE, EOF
            });
        }

        private JsonToken(String s, int i, javax.json.stream.JsonParser.Event event, boolean value)
        {
            super(s, i);
            this.event = event;
            this.value = value;
        }
    }


    JsonTokenizer(Reader reader, BufferPool bufferPool)
    {
        lineNo = 1L;
        lastLineOffset = 0L;
        bufferOffset = 0L;
        this.reader = reader;
        this.bufferPool = bufferPool;
        buf = bufferPool.take();
    }

    private void readString()
    {
        boolean inPlace = true;
        storeBegin = storeEnd = readBegin;
        do
        {
            int ch;
            do
            {
                if(inPlace)
                {
                    for(; readBegin < readEnd && (ch = buf[readBegin]) >= ' ' && ch != 92; readBegin++)
                        if(ch == 34)
                        {
                            storeEnd = readBegin++;
                            return;
                        }

                    storeEnd = readBegin;
                }
                ch = read();
                if(ch < 32 || ch == 34 || ch == 92)
                    break;
                if(!inPlace)
                    buf[storeEnd] = (char)ch;
                storeEnd++;
            } while(true);
            switch(ch)
            {
            case 92: // '\\'
                inPlace = false;
                unescape();
                break;

            case 34: // '"'
                return;

            default:
                throw unexpectedChar(ch);
            }
        } while(true);
    }

    private void unescape()
    {
        int ch = read();
        switch(ch)
        {
        case 98: // 'b'
            buf[storeEnd++] = '\b';
            break;

        case 116: // 't'
            buf[storeEnd++] = '\t';
            break;

        case 110: // 'n'
            buf[storeEnd++] = '\n';
            break;

        case 102: // 'f'
            buf[storeEnd++] = '\f';
            break;

        case 114: // 'r'
            buf[storeEnd++] = '\r';
            break;

        case 34: // '"'
        case 47: // '/'
        case 92: // '\\'
            buf[storeEnd++] = (char)ch;
            break;

        case 117: // 'u'
            int unicode = 0;
            for(int i = 0; i < 4; i++)
            {
                int ch3 = read();
                int digit = ch3 < 0 || ch3 >= HEX_LENGTH ? -1 : HEX[ch3];
                if(digit < 0)
                    throw unexpectedChar(ch3);
                unicode = unicode << 4 | digit;
            }

            buf[storeEnd++] = (char)unicode;
            break;

        default:
            throw unexpectedChar(ch);
        }
    }

    private int readNumberChar()
    {
        if(readBegin < readEnd)
        {
            return buf[readBegin++];
        } else
        {
            storeEnd = readBegin;
            return read();
        }
    }

    private void readNumber(int ch)
    {
        storeBegin = storeEnd = readBegin - 1;
        if(ch == 45)
        {
            minus = true;
            ch = readNumberChar();
            if(ch < 48 || ch > 57)
                throw unexpectedChar(ch);
        }
        if(ch == 48)
            ch = readNumberChar();
        else
            do
                ch = readNumberChar();
            while(ch >= 48 && ch <= 57);
        if(ch == 46)
        {
            fracOrExp = true;
            int count = 0;
            do
            {
                ch = readNumberChar();
                count++;
            } while(ch >= 48 && ch <= 57);
            if(count == 1)
                throw unexpectedChar(ch);
        }
        if(ch == 101 || ch == 69)
        {
            fracOrExp = true;
            ch = readNumberChar();
            if(ch == 43 || ch == 45)
                ch = readNumberChar();
            int count;
            for(count = 0; ch >= 48 && ch <= 57; count++)
                ch = readNumberChar();

            if(count == 0)
                throw unexpectedChar(ch);
        }
        readBegin--;
        storeEnd = readBegin;
    }

    private void readTrue()
    {
        int ch1 = read();
        if(ch1 != 114)
            throw expectedChar(ch1, 'r');
        int ch2 = read();
        if(ch2 != 117)
            throw expectedChar(ch2, 'u');
        int ch3 = read();
        if(ch3 != 101)
            throw expectedChar(ch3, 'e');
        else
            return;
    }

    private void readFalse()
    {
        int ch1 = read();
        if(ch1 != 97)
            throw expectedChar(ch1, 'a');
        int ch2 = read();
        if(ch2 != 108)
            throw expectedChar(ch2, 'l');
        int ch3 = read();
        if(ch3 != 115)
            throw expectedChar(ch3, 's');
        int ch4 = read();
        if(ch4 != 101)
            throw expectedChar(ch4, 'e');
        else
            return;
    }

    private void readNull()
    {
        int ch1 = read();
        if(ch1 != 117)
            throw expectedChar(ch1, 'u');
        int ch2 = read();
        if(ch2 != 108)
            throw expectedChar(ch2, 'l');
        int ch3 = read();
        if(ch3 != 108)
            throw expectedChar(ch3, 'l');
        else
            return;
    }

    JsonToken nextToken()
    {
        reset();
        int ch = read();
        do
        {
            if(ch != 32 && ch != 9 && ch != 10 && ch != 13)
                break;
            if(ch == 13)
            {
                lineNo++;
                ch = read();
                if(ch == 10)
                {
                    lastLineOffset = bufferOffset + (long)readBegin;
                } else
                {
                    lastLineOffset = (bufferOffset + (long)readBegin) - 1L;
                    continue;
                }
            } else
            if(ch == 10)
            {
                lineNo++;
                lastLineOffset = bufferOffset + (long)readBegin;
            }
            ch = read();
        } while(true);
        switch(ch)
        {
        case 34: // '"'
            readString();
            return JsonToken.STRING;

        case 123: // '{'
            return JsonToken.CURLYOPEN;

        case 91: // '['
            return JsonToken.SQUAREOPEN;

        case 58: // ':'
            return JsonToken.COLON;

        case 44: // ','
            return JsonToken.COMMA;

        case 116: // 't'
            readTrue();
            return JsonToken.TRUE;

        case 102: // 'f'
            readFalse();
            return JsonToken.FALSE;

        case 110: // 'n'
            readNull();
            return JsonToken.NULL;

        case 93: // ']'
            return JsonToken.SQUARECLOSE;

        case 125: // '}'
            return JsonToken.CURLYCLOSE;

        case 45: // '-'
        case 48: // '0'
        case 49: // '1'
        case 50: // '2'
        case 51: // '3'
        case 52: // '4'
        case 53: // '5'
        case 54: // '6'
        case 55: // '7'
        case 56: // '8'
        case 57: // '9'
            readNumber(ch);
            return JsonToken.NUMBER;

        case -1: 
            return JsonToken.EOF;
        }
        throw unexpectedChar(ch);
    }

    JsonLocation getLastCharLocation()
    {
        return new JsonLocationImpl(lineNo, (bufferOffset + (long)readBegin) - lastLineOffset, (bufferOffset + (long)readBegin) - 1L);
    }

    JsonLocation getLocation()
    {
        return new JsonLocationImpl(lineNo, ((bufferOffset + (long)readBegin) - lastLineOffset) + 1L, bufferOffset + (long)readBegin);
    }

    private int read()
    {
        int len;
        try
        {
            if(readBegin != readEnd)
                break MISSING_BLOCK_LABEL_59;
            len = fillBuf();
            if(len == -1)
                return -1;
        }
        catch(IOException ioe)
        {
            throw new JsonException(JsonMessages.TOKENIZER_IO_ERR(), ioe);
        }
        if(!$assertionsDisabled && len == 0)
            throw new AssertionError();
        readBegin = storeEnd;
        readEnd = readBegin + len;
        return buf[readBegin++];
    }

    private int fillBuf()
        throws IOException
    {
        if(storeEnd != 0)
        {
            int storeLen = storeEnd - storeBegin;
            if(storeLen > 0)
            {
                if(storeLen == buf.length)
                {
                    char doubleBuf[] = Arrays.copyOf(buf, 2 * buf.length);
                    bufferPool.recycle(buf);
                    buf = doubleBuf;
                } else
                {
                    System.arraycopy(buf, storeBegin, buf, 0, storeLen);
                    storeEnd = storeLen;
                    storeBegin = 0;
                    bufferOffset += readBegin - storeEnd;
                }
            } else
            {
                storeBegin = storeEnd = 0;
                bufferOffset += readBegin;
            }
        } else
        {
            bufferOffset += readBegin;
        }
        return reader.read(buf, storeEnd, buf.length - storeEnd);
    }

    private void reset()
    {
        if(storeEnd != 0)
        {
            storeBegin = 0;
            storeEnd = 0;
            bd = null;
            minus = false;
            fracOrExp = false;
        }
    }

    String getValue()
    {
        return new String(buf, storeBegin, storeEnd - storeBegin);
    }

    BigDecimal getBigDecimal()
    {
        if(bd == null)
            bd = new BigDecimal(buf, storeBegin, storeEnd - storeBegin);
        return bd;
    }

    int getInt()
    {
        int storeLen = storeEnd - storeBegin;
        if(!fracOrExp && (storeLen <= 9 || minus && storeLen == 10))
        {
            int num = 0;
            for(int i = minus ? 1 : 0; i < storeLen; i++)
                num = num * 10 + (buf[storeBegin + i] - 48);

            return minus ? -num : num;
        } else
        {
            return getBigDecimal().intValue();
        }
    }

    boolean isDefinitelyInt()
    {
        int storeLen = storeEnd - storeBegin;
        return !fracOrExp && (storeLen <= 9 || minus && storeLen == 10);
    }

    boolean isIntegral()
    {
        return !fracOrExp || getBigDecimal().scale() == 0;
    }

    public void close()
        throws IOException
    {
        reader.close();
        bufferPool.recycle(buf);
    }

    private JsonParsingException unexpectedChar(int ch)
    {
        JsonLocation location = getLastCharLocation();
        return new JsonParsingException(JsonMessages.TOKENIZER_UNEXPECTED_CHAR(ch, location), location);
    }

    private JsonParsingException expectedChar(int unexpected, char expected)
    {
        JsonLocation location = getLastCharLocation();
        return new JsonParsingException(JsonMessages.TOKENIZER_EXPECTED_CHAR(unexpected, location, expected), location);
    }

    private static final int HEX[];
    private static final int HEX_LENGTH;
    private final BufferPool bufferPool;
    private final Reader reader;
    private char buf[];
    private int readBegin;
    private int readEnd;
    private int storeBegin;
    private int storeEnd;
    private long lineNo;
    private long lastLineOffset;
    private long bufferOffset;
    private boolean minus;
    private boolean fracOrExp;
    private BigDecimal bd;
    static final boolean $assertionsDisabled = !org/glassfish/json/JsonTokenizer.desiredAssertionStatus();

    static 
    {
        HEX = new int[128];
        Arrays.fill(HEX, -1);
        for(int i = 48; i <= 57; i++)
            HEX[i] = i - 48;

        for(int i = 65; i <= 70; i++)
            HEX[i] = (10 + i) - 65;

        for(int i = 97; i <= 102; i++)
            HEX[i] = (10 + i) - 97;

        HEX_LENGTH = HEX.length;
    }
}
