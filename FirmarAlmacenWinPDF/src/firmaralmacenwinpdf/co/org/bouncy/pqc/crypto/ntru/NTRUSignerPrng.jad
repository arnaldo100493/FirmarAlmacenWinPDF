// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NTRUSignerPrng.java

package co.org.bouncy.pqc.crypto.ntru;

import co.org.bouncy.crypto.Digest;
import java.nio.ByteBuffer;

public class NTRUSignerPrng
{

    NTRUSignerPrng(byte seed[], Digest hashAlg)
    {
        counter = 0;
        this.seed = seed;
        this.hashAlg = hashAlg;
    }

    byte[] nextBytes(int n)
    {
        ByteBuffer buf;
        for(buf = ByteBuffer.allocate(n); buf.hasRemaining();)
        {
            ByteBuffer cbuf = ByteBuffer.allocate(seed.length + 4);
            cbuf.put(seed);
            cbuf.putInt(counter);
            byte array[] = cbuf.array();
            byte hash[] = new byte[hashAlg.getDigestSize()];
            hashAlg.update(array, 0, array.length);
            hashAlg.doFinal(hash, 0);
            if(buf.remaining() < hash.length)
                buf.put(hash, 0, buf.remaining());
            else
                buf.put(hash);
            counter++;
        }

        return buf.array();
    }

    private int counter;
    private byte seed[];
    private Digest hashAlg;
}
