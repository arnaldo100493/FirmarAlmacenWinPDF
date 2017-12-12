// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Latin1Converter.java

package co.com.pdf.xmp.impl;

import java.io.UnsupportedEncodingException;

// Referenced classes of package co.com.pdf.xmp.impl:
//            ByteBuffer

public class Latin1Converter
{

    private Latin1Converter()
    {
    }

    public static ByteBuffer convert(ByteBuffer buffer)
    {
        if("UTF-8".equals(buffer.getEncoding()))
        {
            byte readAheadBuffer[] = new byte[8];
            int readAhead = 0;
            int expectedBytes = 0;
            ByteBuffer out = new ByteBuffer((buffer.length() * 4) / 3);
            int state = 0;
            for(int i = 0; i < buffer.length(); i++)
            {
                int b = buffer.charAt(i);
                switch(state)
                {
                case 0: // '\0'
                default:
                    if(b < 127)
                    {
                        out.append((byte)b);
                        break;
                    }
                    if(b >= 192)
                    {
                        expectedBytes = -1;
                        for(int test = b; expectedBytes < 8 && (test & 0x80) == 128; test <<= 1)
                            expectedBytes++;

                        readAheadBuffer[readAhead++] = (byte)b;
                        state = 11;
                    } else
                    {
                        byte utf8[] = convertToUTF8((byte)b);
                        out.append(utf8);
                    }
                    break;

                case 11: // '\013'
                    if(expectedBytes > 0 && (b & 0xc0) == 128)
                    {
                        readAheadBuffer[readAhead++] = (byte)b;
                        if(--expectedBytes == 0)
                        {
                            out.append(readAheadBuffer, 0, readAhead);
                            readAhead = 0;
                            state = 0;
                        }
                    } else
                    {
                        byte utf8[] = convertToUTF8(readAheadBuffer[0]);
                        out.append(utf8);
                        i -= readAhead;
                        readAhead = 0;
                        state = 0;
                    }
                    break;
                }
            }

            if(state == 11)
            {
                for(int j = 0; j < readAhead; j++)
                {
                    byte b = readAheadBuffer[j];
                    byte utf8[] = convertToUTF8(b);
                    out.append(utf8);
                }

            }
            return out;
        } else
        {
            return buffer;
        }
    }

    private static byte[] convertToUTF8(byte ch)
    {
        int c = ch & 0xff;
        if(c < 128)
            break MISSING_BLOCK_LABEL_83;
        if(c == 129 || c == 141 || c == 143 || c == 144 || c == 157)
            return (new byte[] {
                32
            });
        try
        {
            return (new String(new byte[] {
                ch
            }, "cp1252")).getBytes("UTF-8");
        }
        catch(UnsupportedEncodingException e) { }
        return (new byte[] {
            ch
        });
    }

    private static final int STATE_START = 0;
    private static final int STATE_UTF8CHAR = 11;
}
