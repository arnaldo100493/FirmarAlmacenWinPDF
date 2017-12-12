// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BrokenKDF2BytesGenerator.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.KDFParameters;

public class BrokenKDF2BytesGenerator
    implements DerivationFunction
{

    public BrokenKDF2BytesGenerator(Digest digest)
    {
        this.digest = digest;
    }

    public void init(DerivationParameters param)
    {
        if(!(param instanceof KDFParameters))
        {
            throw new IllegalArgumentException("KDF parameters required for KDF2Generator");
        } else
        {
            KDFParameters p = (KDFParameters)param;
            shared = p.getSharedSecret();
            iv = p.getIV();
            return;
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
        long oBits = len * 8;
        if(oBits > (long)(digest.getDigestSize() * 8) * 29L)
            new IllegalArgumentException("Output length to large");
        int cThreshold = (int)(oBits / (long)digest.getDigestSize());
        byte dig[] = null;
        dig = new byte[digest.getDigestSize()];
        for(int counter = 1; counter <= cThreshold; counter++)
        {
            digest.update(shared, 0, shared.length);
            digest.update((byte)(counter & 0xff));
            digest.update((byte)(counter >> 8 & 0xff));
            digest.update((byte)(counter >> 16 & 0xff));
            digest.update((byte)(counter >> 24 & 0xff));
            digest.update(iv, 0, iv.length);
            digest.doFinal(dig, 0);
            if(len - outOff > dig.length)
            {
                System.arraycopy(dig, 0, out, outOff, dig.length);
                outOff += dig.length;
            } else
            {
                System.arraycopy(dig, 0, out, outOff, len - outOff);
            }
        }

        digest.reset();
        return len;
    }

    private Digest digest;
    private byte shared[];
    private byte iv[];
}
