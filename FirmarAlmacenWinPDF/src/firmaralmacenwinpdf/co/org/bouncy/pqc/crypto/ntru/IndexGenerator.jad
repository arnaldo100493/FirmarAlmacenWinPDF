// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IndexGenerator.java

package co.org.bouncy.pqc.crypto.ntru;

import co.org.bouncy.crypto.Digest;
import co.org.bouncy.util.Arrays;

// Referenced classes of package co.org.bouncy.pqc.crypto.ntru:
//            NTRUEncryptionParameters

public class IndexGenerator
{
    public static class BitString
    {

        void appendBits(byte bytes[])
        {
            for(int i = 0; i != bytes.length; i++)
                appendBits(bytes[i]);

        }

        public void appendBits(byte b)
        {
            if(numBytes == bytes.length)
                bytes = IndexGenerator.copyOf(bytes, 2 * bytes.length);
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

        public BitString getTrailing(int numBits)
        {
            BitString newStr = new BitString();
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

        public BitString()
        {
            bytes = new byte[4];
        }
    }


    IndexGenerator(byte seed[], NTRUEncryptionParameters params)
    {
        this.seed = seed;
        N = params.N;
        c = params.c;
        minCallsR = params.minCallsR;
        totLen = 0;
        remLen = 0;
        counter = 0;
        hashAlg = params.hashAlg;
        hLen = hashAlg.getDigestSize();
        initialized = false;
    }

    int nextIndex()
    {
        if(!initialized)
        {
            buf = new BitString();
            byte hash[] = new byte[hashAlg.getDigestSize()];
            for(; counter < minCallsR; counter++)
                appendHash(buf, hash);

            totLen = minCallsR * 8 * hLen;
            remLen = totLen;
            initialized = true;
        }
        int i;
        do
        {
            totLen += c;
            BitString M = buf.getTrailing(remLen);
            if(remLen < c)
            {
                int tmpLen = c - remLen;
                int cThreshold = counter + ((tmpLen + hLen) - 1) / hLen;
                byte hash[] = new byte[hashAlg.getDigestSize()];
                do
                {
                    if(counter >= cThreshold)
                        break;
                    appendHash(M, hash);
                    counter++;
                    if(tmpLen > 8 * hLen)
                        tmpLen -= 8 * hLen;
                } while(true);
                remLen = 8 * hLen - tmpLen;
                buf = new BitString();
                buf.appendBits(hash);
            } else
            {
                remLen -= c;
            }
            i = M.getLeadingAsInt(c);
        } while(i >= (1 << c) - (1 << c) % N);
        return i % N;
    }

    private void appendHash(BitString m, byte hash[])
    {
        hashAlg.update(seed, 0, seed.length);
        putInt(hashAlg, counter);
        hashAlg.doFinal(hash, 0);
        m.appendBits(hash);
    }

    private void putInt(Digest hashAlg, int counter)
    {
        hashAlg.update((byte)(counter >> 24));
        hashAlg.update((byte)(counter >> 16));
        hashAlg.update((byte)(counter >> 8));
        hashAlg.update((byte)counter);
    }

    private static byte[] copyOf(byte src[], int len)
    {
        byte tmp[] = new byte[len];
        System.arraycopy(src, 0, tmp, 0, len >= src.length ? src.length : len);
        return tmp;
    }

    private byte seed[];
    private int N;
    private int c;
    private int minCallsR;
    private int totLen;
    private int remLen;
    private BitString buf;
    private int counter;
    private boolean initialized;
    private Digest hashAlg;
    private int hLen;

}
