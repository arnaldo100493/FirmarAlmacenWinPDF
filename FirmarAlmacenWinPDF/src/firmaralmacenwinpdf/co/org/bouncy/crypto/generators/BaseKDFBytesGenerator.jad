// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseKDFBytesGenerator.java

package co.org.bouncy.crypto.generators;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.ISO18033KDFParameters;
import co.org.bouncy.crypto.params.KDFParameters;
import co.org.bouncy.crypto.util.Pack;

public class BaseKDFBytesGenerator
    implements DerivationFunction
{

    protected BaseKDFBytesGenerator(int counterStart, Digest digest)
    {
        this.counterStart = counterStart;
        this.digest = digest;
    }

    public void init(DerivationParameters param)
    {
        if(param instanceof KDFParameters)
        {
            KDFParameters p = (KDFParameters)param;
            shared = p.getSharedSecret();
            iv = p.getIV();
        } else
        if(param instanceof ISO18033KDFParameters)
        {
            ISO18033KDFParameters p = (ISO18033KDFParameters)param;
            shared = p.getSeed();
            iv = null;
        } else
        {
            throw new IllegalArgumentException("KDF parameters required for KDF2Generator");
        }
    }

    public Digest getDigest()
    {
        return digest;
    }

    public int generateBytes(byte out[], int outOff, int len)
        throws DataLengthException, IllegalArgumentException
    {
        if(out.length - len < outOff)
            throw new DataLengthException("output buffer too small");
        long oBytes = len;
        int outLen = digest.getDigestSize();
        if(oBytes > 0x1ffffffffL)
            throw new IllegalArgumentException("Output length too large");
        int cThreshold = (int)(((oBytes + (long)outLen) - 1L) / (long)outLen);
        byte dig[] = new byte[digest.getDigestSize()];
        byte C[] = new byte[4];
        Pack.intToBigEndian(counterStart, C, 0);
        int counterBase = counterStart & 0xffffff00;
        for(int i = 0; i < cThreshold; i++)
        {
            digest.update(shared, 0, shared.length);
            digest.update(C, 0, C.length);
            if(iv != null)
                digest.update(iv, 0, iv.length);
            digest.doFinal(dig, 0);
            if(len > outLen)
            {
                System.arraycopy(dig, 0, out, outOff, outLen);
                outOff += outLen;
                len -= outLen;
            } else
            {
                System.arraycopy(dig, 0, out, outOff, len);
            }
            if(++C[3] == 0)
                Pack.intToBigEndian(counterBase += 256, C, 0);
        }

        digest.reset();
        return (int)oBytes;
    }

    private int counterStart;
    private Digest digest;
    private byte shared[];
    private byte iv[];
}
