// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Base64Encoder.java

package co.org.bouncy.util.encoders;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.util.encoders:
//            Encoder

public class Base64Encoder
    implements Encoder
{

    protected void initialiseDecodingTable()
    {
        for(int i = 0; i < decodingTable.length; i++)
            decodingTable[i] = -1;

        for(int i = 0; i < encodingTable.length; i++)
            decodingTable[encodingTable[i]] = (byte)i;

    }

    public Base64Encoder()
    {
        padding = 61;
        initialiseDecodingTable();
    }

    public int encode(byte data[], int off, int length, OutputStream out)
        throws IOException
    {
        int modulus = length % 3;
        int dataLength = length - modulus;
        for(int i = off; i < off + dataLength; i += 3)
        {
            int a1 = data[i] & 0xff;
            int a2 = data[i + 1] & 0xff;
            int a3 = data[i + 2] & 0xff;
            out.write(encodingTable[a1 >>> 2 & 0x3f]);
            out.write(encodingTable[(a1 << 4 | a2 >>> 4) & 0x3f]);
            out.write(encodingTable[(a2 << 2 | a3 >>> 6) & 0x3f]);
            out.write(encodingTable[a3 & 0x3f]);
        }

        switch(modulus)
        {
        case 1: // '\001'
        {
            int d1 = data[off + dataLength] & 0xff;
            int b1 = d1 >>> 2 & 0x3f;
            int b2 = d1 << 4 & 0x3f;
            out.write(encodingTable[b1]);
            out.write(encodingTable[b2]);
            out.write(padding);
            out.write(padding);
            break;
        }

        case 2: // '\002'
        {
            int d1 = data[off + dataLength] & 0xff;
            int d2 = data[off + dataLength + 1] & 0xff;
            int b1 = d1 >>> 2 & 0x3f;
            int b2 = (d1 << 4 | d2 >>> 4) & 0x3f;
            int b3 = d2 << 2 & 0x3f;
            out.write(encodingTable[b1]);
            out.write(encodingTable[b2]);
            out.write(encodingTable[b3]);
            out.write(padding);
            break;
        }
        }
        return (dataLength / 3) * 4 + (modulus != 0 ? 4 : 0);
    }

    private boolean ignore(char c)
    {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    public int decode(byte data[], int off, int length, OutputStream out)
        throws IOException
    {
        int outLen = 0;
        int end;
        for(end = off + length; end > off && ignore((char)data[end - 1]); end--);
        int i = off;
        int finish = end - 4;
        for(i = nextI(data, i, finish); i < finish; i = nextI(data, i, finish))
        {
            byte b1 = decodingTable[data[i++]];
            i = nextI(data, i, finish);
            byte b2 = decodingTable[data[i++]];
            i = nextI(data, i, finish);
            byte b3 = decodingTable[data[i++]];
            i = nextI(data, i, finish);
            byte b4 = decodingTable[data[i++]];
            if((b1 | b2 | b3 | b4) < 0)
                throw new IOException("invalid characters encountered in base64 data");
            out.write(b1 << 2 | b2 >> 4);
            out.write(b2 << 4 | b3 >> 2);
            out.write(b3 << 6 | b4);
            outLen += 3;
        }

        outLen += decodeLastBlock(out, (char)data[end - 4], (char)data[end - 3], (char)data[end - 2], (char)data[end - 1]);
        return outLen;
    }

    private int nextI(byte data[], int i, int finish)
    {
        for(; i < finish && ignore((char)data[i]); i++);
        return i;
    }

    public int decode(String data, OutputStream out)
        throws IOException
    {
        int length = 0;
        int end;
        for(end = data.length(); end > 0 && ignore(data.charAt(end - 1)); end--);
        int i = 0;
        int finish = end - 4;
        for(i = nextI(data, i, finish); i < finish; i = nextI(data, i, finish))
        {
            byte b1 = decodingTable[data.charAt(i++)];
            i = nextI(data, i, finish);
            byte b2 = decodingTable[data.charAt(i++)];
            i = nextI(data, i, finish);
            byte b3 = decodingTable[data.charAt(i++)];
            i = nextI(data, i, finish);
            byte b4 = decodingTable[data.charAt(i++)];
            if((b1 | b2 | b3 | b4) < 0)
                throw new IOException("invalid characters encountered in base64 data");
            out.write(b1 << 2 | b2 >> 4);
            out.write(b2 << 4 | b3 >> 2);
            out.write(b3 << 6 | b4);
            length += 3;
        }

        length += decodeLastBlock(out, data.charAt(end - 4), data.charAt(end - 3), data.charAt(end - 2), data.charAt(end - 1));
        return length;
    }

    private int decodeLastBlock(OutputStream out, char c1, char c2, char c3, char c4)
        throws IOException
    {
        byte b1;
        byte b2;
        if(c3 == padding)
        {
            b1 = decodingTable[c1];
            b2 = decodingTable[c2];
            if((b1 | b2) < 0)
            {
                throw new IOException("invalid characters encountered at end of base64 data");
            } else
            {
                out.write(b1 << 2 | b2 >> 4);
                return 1;
            }
        }
        byte b3;
        if(c4 == padding)
        {
            b1 = decodingTable[c1];
            b2 = decodingTable[c2];
            b3 = decodingTable[c3];
            if((b1 | b2 | b3) < 0)
            {
                throw new IOException("invalid characters encountered at end of base64 data");
            } else
            {
                out.write(b1 << 2 | b2 >> 4);
                out.write(b2 << 4 | b3 >> 2);
                return 2;
            }
        }
        b1 = decodingTable[c1];
        b2 = decodingTable[c2];
        b3 = decodingTable[c3];
        byte b4 = decodingTable[c4];
        if((b1 | b2 | b3 | b4) < 0)
        {
            throw new IOException("invalid characters encountered at end of base64 data");
        } else
        {
            out.write(b1 << 2 | b2 >> 4);
            out.write(b2 << 4 | b3 >> 2);
            out.write(b3 << 6 | b4);
            return 3;
        }
    }

    private int nextI(String data, int i, int finish)
    {
        for(; i < finish && ignore(data.charAt(i)); i++);
        return i;
    }

    protected final byte encodingTable[] = {
        65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
        75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
        85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
        101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
        111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
        121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
        56, 57, 43, 47
    };
    protected byte padding;
    protected final byte decodingTable[] = new byte[128];
}
