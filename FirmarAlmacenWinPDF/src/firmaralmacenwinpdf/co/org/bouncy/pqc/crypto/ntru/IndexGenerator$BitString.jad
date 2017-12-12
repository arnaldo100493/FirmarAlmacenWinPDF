// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IndexGenerator.java

package co.org.bouncy.pqc.crypto.ntru;

import co.org.bouncy.util.Arrays;

// Referenced classes of package co.org.bouncy.pqc.crypto.ntru:
//            IndexGenerator

public static class IndexGenerator$BitString
{

    void appendBits(byte bytes[])
    {
        for(int i = 0; i != bytes.length; i++)
            appendBits(bytes[i]);

    }

    public void appendBits(byte b)
    {
        if(numBytes == bytes.length)
            bytes = IndexGenerator.access$000(bytes, 2 * bytes.length);
        if(numBytes == 0)
        {
            numBytes = 1;
            bytes[0] = b;
            lastByteBits = 8;
        } else
        if(lastByteBits == 8)
        {
            bytes[numBytes++] = b;
        } else
        {
            int s = 8 - lastByteBits;
            bytes[numBytes - 1] |= (b & 0xff) << lastByteBits;
            bytes[numBytes++] = (byte)((b & 0xff) >> s);
        }
    }

    public IndexGenerator$BitString getTrailing(int numBits)
    {
        IndexGenerator$BitString newStr = new IndexGenerator$BitString();
        newStr.numBytes = (numBits + 7) / 8;
        newStr.bytes = new byte[newStr.numBytes];
        for(int i = 0; i < newStr.numBytes; i++)
            newStr.bytes[i] = bytes[i];

        newStr.lastByteBits = numBits % 8;
        if(newStr.lastByteBits == 0)
        {
            newStr.lastByteBits = 8;
        } else
        {
            int s = 32 - newStr.lastByteBits;
            newStr.bytes[newStr.numBytes - 1] = (byte)((newStr.bytes[newStr.numBytes - 1] << s) >>> s);
        }
        return newStr;
    }

    public int getLeadingAsInt(int numBits)
    {
        int startBit = ((numBytes - 1) * 8 + lastByteBits) - numBits;
        int startByte = startBit / 8;
        int startBitInStartByte = startBit % 8;
        int sum = (bytes[startByte] & 0xff) >>> startBitInStartByte;
        int shift = 8 - startBitInStartByte;
        for(int i = startByte + 1; i < numBytes; i++)
        {
            sum |= (bytes[i] & 0xff) << shift;
            shift += 8;
        }

        return sum;
    }

    public byte[] getBytes()
    {
        return Arrays.clone(bytes);
    }

    byte bytes[];
    int numBytes;
    int lastByteBits;

    public IndexGenerator$BitString()
    {
        bytes = new byte[4];
    }
}
