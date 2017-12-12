// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PRTokeniser.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.exceptions.InvalidPdfException;
import co.com.pdf.text.io.RandomAccessSourceFactory;
import java.io.IOException;

// Referenced classes of package co.com.pdf.text.pdf:
//            RandomAccessFileOrArray

public class PRTokeniser
{
    public static final class TokenType extends Enum
    {

        public static TokenType[] values()
        {
            return (TokenType[])$VALUES.clone();
        }

        public static TokenType valueOf(String name)
        {
            return (TokenType)Enum.valueOf(co/com/pdf/text/pdf/PRTokeniser$TokenType, name);
        }

        public static final TokenType NUMBER;
        public static final TokenType STRING;
        public static final TokenType NAME;
        public static final TokenType COMMENT;
        public static final TokenType START_ARRAY;
        public static final TokenType END_ARRAY;
        public static final TokenType START_DIC;
        public static final TokenType END_DIC;
        public static final TokenType REF;
        public static final TokenType OTHER;
        public static final TokenType ENDOFFILE;
        private static final TokenType $VALUES[];

        static 
        {
            NUMBER = new TokenType("NUMBER", 0);
            STRING = new TokenType("STRING", 1);
            NAME = new TokenType("NAME", 2);
            COMMENT = new TokenType("COMMENT", 3);
            START_ARRAY = new TokenType("START_ARRAY", 4);
            END_ARRAY = new TokenType("END_ARRAY", 5);
            START_DIC = new TokenType("START_DIC", 6);
            END_DIC = new TokenType("END_DIC", 7);
            REF = new TokenType("REF", 8);
            OTHER = new TokenType("OTHER", 9);
            ENDOFFILE = new TokenType("ENDOFFILE", 10);
            $VALUES = (new TokenType[] {
                NUMBER, STRING, NAME, COMMENT, START_ARRAY, END_ARRAY, START_DIC, END_DIC, REF, OTHER, 
                ENDOFFILE
            });
        }

        private TokenType(String s, int i)
        {
            super(s, i);
        }
    }


    public PRTokeniser(RandomAccessFileOrArray file)
    {
        this.file = file;
    }

    public void seek(long pos)
        throws IOException
    {
        file.seek(pos);
    }

    public long getFilePointer()
        throws IOException
    {
        return file.getFilePointer();
    }

    public void close()
        throws IOException
    {
        file.close();
    }

    public long length()
        throws IOException
    {
        return file.length();
    }

    public int read()
        throws IOException
    {
        return file.read();
    }

    public RandomAccessFileOrArray getSafeFile()
    {
        return new RandomAccessFileOrArray(file);
    }

    public RandomAccessFileOrArray getFile()
    {
        return file;
    }

    public String readString(int size)
        throws IOException
    {
        StringBuilder buf = new StringBuilder();
        do
        {
            if(size-- <= 0)
                break;
            int ch = read();
            if(ch == -1)
                break;
            buf.append((char)ch);
        } while(true);
        return buf.toString();
    }

    public static final boolean isWhitespace(int ch)
    {
        return ch == 0 || ch == 9 || ch == 10 || ch == 12 || ch == 13 || ch == 32;
    }

    public static final boolean isDelimiter(int ch)
    {
        return ch == 40 || ch == 41 || ch == 60 || ch == 62 || ch == 91 || ch == 93 || ch == 47 || ch == 37;
    }

    public static final boolean isDelimiterWhitespace(int ch)
    {
        return delims[ch + 1];
    }

    public TokenType getTokenType()
    {
        return type;
    }

    public String getStringValue()
    {
        return stringValue;
    }

    public int getReference()
    {
        return reference;
    }

    public int getGeneration()
    {
        return generation;
    }

    public void backOnePosition(int ch)
    {
        if(ch != -1)
            file.pushBack((byte)ch);
    }

    public void throwError(String error)
        throws IOException
    {
        throw new InvalidPdfException(MessageLocalization.getComposedMessage("1.at.file.pointer.2", new Object[] {
            error, String.valueOf(file.getFilePointer())
        }));
    }

    public int getHeaderOffset()
        throws IOException
    {
        String str = readString(1024);
        int idx = str.indexOf("%PDF-");
        if(idx < 0)
        {
            idx = str.indexOf("%FDF-");
            if(idx < 0)
                throw new InvalidPdfException(MessageLocalization.getComposedMessage("pdf.header.not.found", new Object[0]));
        }
        return idx;
    }

    public char checkPdfHeader()
        throws IOException
    {
        file.seek(0L);
        String str = readString(1024);
        int idx = str.indexOf("%PDF-");
        if(idx != 0)
            throw new InvalidPdfException(MessageLocalization.getComposedMessage("pdf.header.not.found", new Object[0]));
        else
            return str.charAt(7);
    }

    public void checkFdfHeader()
        throws IOException
    {
        file.seek(0L);
        String str = readString(1024);
        int idx = str.indexOf("%FDF-");
        if(idx != 0)
            throw new InvalidPdfException(MessageLocalization.getComposedMessage("fdf.header.not.found", new Object[0]));
        else
            return;
    }

    public long getStartxref()
        throws IOException
    {
        int arrLength = 1024;
        long fileLength = file.length();
        long pos = fileLength - (long)arrLength;
        if(pos < 1L)
            pos = 1L;
        for(; pos > 0L; pos = (pos - (long)arrLength) + 9L)
        {
            file.seek(pos);
            String str = readString(arrLength);
            int idx = str.lastIndexOf("startxref");
            if(idx >= 0)
                return pos + (long)idx;
        }

        throw new InvalidPdfException(MessageLocalization.getComposedMessage("pdf.startxref.not.found", new Object[0]));
    }

    public static int getHex(int v)
    {
        if(v >= 48 && v <= 57)
            return v - 48;
        if(v >= 65 && v <= 70)
            return (v - 65) + 10;
        if(v >= 97 && v <= 102)
            return (v - 97) + 10;
        else
            return -1;
    }

    public void nextValidToken()
        throws IOException
    {
        int level = 0;
        String n1 = null;
        String n2 = null;
        long ptr = 0L;
label0:
        do
        {
            do
                if(!nextToken())
                    break label0;
            while(type == TokenType.COMMENT);
            switch(level)
            {
            case 0: // '\0'
                if(type != TokenType.NUMBER)
                    return;
                ptr = file.getFilePointer();
                n1 = stringValue;
                level++;
                break;

            case 1: // '\001'
                if(type != TokenType.NUMBER)
                {
                    file.seek(ptr);
                    type = TokenType.NUMBER;
                    stringValue = n1;
                    return;
                }
                n2 = stringValue;
                level++;
                break;

            default:
                if(type != TokenType.OTHER || !stringValue.equals("R"))
                {
                    file.seek(ptr);
                    type = TokenType.NUMBER;
                    stringValue = n1;
                    return;
                } else
                {
                    type = TokenType.REF;
                    reference = Integer.parseInt(n1);
                    generation = Integer.parseInt(n2);
                    return;
                }
            }
        } while(true);
        if(level == 1)
            type = TokenType.NUMBER;
    }

    public boolean nextToken()
        throws IOException
    {
        int ch = 0;
        do
            ch = file.read();
        while(ch != -1 && isWhitespace(ch));
        if(ch == -1)
        {
            type = TokenType.ENDOFFILE;
            return false;
        }
        StringBuilder outBuf = new StringBuilder();
        stringValue = "";
        switch(ch)
        {
        case 91: // '['
            type = TokenType.START_ARRAY;
            break;

        case 93: // ']'
            type = TokenType.END_ARRAY;
            break;

        case 47: // '/'
            outBuf.setLength(0);
            type = TokenType.NAME;
            do
            {
                ch = file.read();
                if(delims[ch + 1])
                    break;
                if(ch == 35)
                    ch = (getHex(file.read()) << 4) + getHex(file.read());
                outBuf.append((char)ch);
            } while(true);
            backOnePosition(ch);
            break;

        case 62: // '>'
            ch = file.read();
            if(ch != 62)
                throwError(MessageLocalization.getComposedMessage("greaterthan.not.expected", new Object[0]));
            type = TokenType.END_DIC;
            break;

        case 60: // '<'
            int v1 = file.read();
            if(v1 == 60)
            {
                type = TokenType.START_DIC;
            } else
            {
                outBuf.setLength(0);
                type = TokenType.STRING;
                hexString = true;
                int v2 = 0;
                do
                {
                    for(; isWhitespace(v1); v1 = file.read());
                    if(v1 == 62)
                        break;
                    v1 = getHex(v1);
                    if(v1 < 0)
                        break;
                    for(v2 = file.read(); isWhitespace(v2); v2 = file.read());
                    if(v2 == 62)
                    {
                        ch = v1 << 4;
                        outBuf.append((char)ch);
                        break;
                    }
                    v2 = getHex(v2);
                    if(v2 < 0)
                        break;
                    ch = (v1 << 4) + v2;
                    outBuf.append((char)ch);
                    v1 = file.read();
                } while(true);
                if(v1 < 0 || v2 < 0)
                    throwError(MessageLocalization.getComposedMessage("error.reading.string", new Object[0]));
            }
            break;

        case 37: // '%'
            type = TokenType.COMMENT;
            do
                ch = file.read();
            while(ch != -1 && ch != 13 && ch != 10);
            break;

        case 40: // '('
            outBuf.setLength(0);
            type = TokenType.STRING;
            hexString = false;
            int nesting = 0;
            do
            {
                ch = file.read();
                if(ch == -1)
                    break;
                if(ch == 40)
                    nesting++;
                else
                if(ch == 41)
                    nesting--;
                else
                if(ch == 92)
                {
                    boolean lineBreak = false;
                    ch = file.read();
                    switch(ch)
                    {
                    case 110: // 'n'
                        ch = 10;
                        break;

                    case 114: // 'r'
                        ch = 13;
                        break;

                    case 116: // 't'
                        ch = 9;
                        break;

                    case 98: // 'b'
                        ch = 8;
                        break;

                    case 102: // 'f'
                        ch = 12;
                        break;

                    case 13: // '\r'
                        lineBreak = true;
                        ch = file.read();
                        if(ch != 10)
                            backOnePosition(ch);
                        break;

                    case 10: // '\n'
                        lineBreak = true;
                        break;

                    default:
                        if(ch >= 48 && ch <= 55)
                        {
                            int octal = ch - 48;
                            ch = file.read();
                            if(ch < 48 || ch > 55)
                            {
                                backOnePosition(ch);
                                ch = octal;
                            } else
                            {
                                octal = ((octal << 3) + ch) - 48;
                                ch = file.read();
                                if(ch < 48 || ch > 55)
                                {
                                    backOnePosition(ch);
                                    ch = octal;
                                } else
                                {
                                    octal = ((octal << 3) + ch) - 48;
                                    ch = octal & 0xff;
                                }
                            }
                        }
                        break;

                    case 40: // '('
                    case 41: // ')'
                    case 92: // '\\'
                        break;
                    }
                    if(lineBreak)
                        continue;
                    if(ch < 0)
                        break;
                } else
                if(ch == 13)
                {
                    ch = file.read();
                    if(ch < 0)
                        break;
                    if(ch != 10)
                    {
                        backOnePosition(ch);
                        ch = 10;
                    }
                }
                if(nesting == -1)
                    break;
                outBuf.append((char)ch);
            } while(true);
            if(ch == -1)
                throwError(MessageLocalization.getComposedMessage("error.reading.string", new Object[0]));
            break;

        default:
            outBuf.setLength(0);
            if(ch == 45 || ch == 43 || ch == 46 || ch >= 48 && ch <= 57)
            {
                type = TokenType.NUMBER;
                if(ch == 45)
                {
                    boolean minus = false;
                    do
                    {
                        minus = !minus;
                        ch = file.read();
                    } while(ch == 45);
                    if(minus)
                        outBuf.append('-');
                } else
                {
                    outBuf.append((char)ch);
                    ch = file.read();
                }
                for(; ch != -1 && (ch >= 48 && ch <= 57 || ch == 46); ch = file.read())
                    outBuf.append((char)ch);

            } else
            {
                type = TokenType.OTHER;
                do
                {
                    outBuf.append((char)ch);
                    ch = file.read();
                } while(!delims[ch + 1]);
            }
            if(ch != -1)
                backOnePosition(ch);
            break;
        }
        if(outBuf != null)
            stringValue = outBuf.toString();
        return true;
    }

    public long longValue()
    {
        return Long.parseLong(stringValue);
    }

    public int intValue()
    {
        return Integer.parseInt(stringValue);
    }

    public boolean readLineSegment(byte input[])
        throws IOException
    {
        int c = -1;
        boolean eol = false;
        int ptr = 0;
        int len = input.length;
        if(ptr < len)
            while(isWhitespace(c = read())) ;
        do
        {
            if(eol || ptr >= len)
                break;
            switch(c)
            {
            case -1: 
            case 10: // '\n'
                eol = true;
                break;

            case 13: // '\r'
                eol = true;
                long cur = getFilePointer();
                if(read() != 10)
                    seek(cur);
                break;

            default:
                input[ptr++] = (byte)c;
                break;
            }
            if(eol || len <= ptr)
                break;
            c = read();
        } while(true);
        if(ptr >= len)
        {
            eol = false;
            do
            {
                if(eol)
                    break;
                switch(c = read())
                {
                case -1: 
                case 10: // '\n'
                    eol = true;
                    break;

                case 13: // '\r'
                    eol = true;
                    long cur = getFilePointer();
                    if(read() != 10)
                        seek(cur);
                    break;
                }
            } while(true);
        }
        if(c == -1 && ptr == 0)
            return false;
        if(ptr + 2 <= len)
        {
            input[ptr++] = 32;
            input[ptr] = 88;
        }
        return true;
    }

    public static long[] checkObjectStart(byte line[])
    {
        PRTokeniser tk;
        int num;
        int gen;
        try
        {
            tk = new PRTokeniser(new RandomAccessFileOrArray((new RandomAccessSourceFactory()).createSource(line)));
            num = 0;
            gen = 0;
            if(!tk.nextToken() || tk.getTokenType() != TokenType.NUMBER)
                return null;
        }
        catch(Exception ioe)
        {
            return null;
        }
        num = tk.intValue();
        if(!tk.nextToken() || tk.getTokenType() != TokenType.NUMBER)
            return null;
        gen = tk.intValue();
        if(!tk.nextToken())
            return null;
        if(!tk.getStringValue().equals("obj"))
            return null;
        return (new long[] {
            (long)num, (long)gen
        });
    }

    public boolean isHexString()
    {
        return hexString;
    }

    public static final boolean delims[] = {
        true, true, false, false, false, false, false, false, false, false, 
        true, true, false, true, true, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, true, false, false, false, false, true, false, 
        false, true, true, false, false, false, false, false, true, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, true, false, true, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, true, false, true, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false, false, false, false, 
        false, false, false, false, false, false, false
    };
    static final String EMPTY = "";
    private final RandomAccessFileOrArray file;
    protected TokenType type;
    protected String stringValue;
    protected int reference;
    protected int generation;
    protected boolean hexString;

}
