// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PSSSignatureSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.rsa;

import co.org.bouncy.crypto.Digest;
import java.io.ByteArrayOutputStream;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.rsa:
//            PSSSignatureSpi

private class PSSSignatureSpi$NullPssDigest
    implements Digest
{

    public String getAlgorithmName()
    {
        return "NULL";
    }

    public int getDigestSize()
    {
        return baseDigest.getDigestSize();
    }

    public void update(byte in)
    {
        bOut.write(in);
    }

    public void update(byte in[], int inOff, int len)
    {
        bOut.write(in, inOff, len);
    }

    public int doFinal(byte out[], int outOff)
    {
        byte res[] = bOut.toByteArray();
        if(oddTime)
        {
            System.arraycopy(res, 0, out, outOff, res.length);
        } else
        {
            baseDigest.update(res, 0, res.length);
            baseDigest.doFinal(out, outOff);
        }
        reset();
        oddTime = !oddTime;
        return res.length;
    }

    public void reset()
    {
        bOut.reset();
        baseDigest.reset();
    }

    public int getByteLength()
    {
        return 0;
    }

    private ByteArrayOutputStream bOut;
    private Digest baseDigest;
    private boolean oddTime;
    final PSSSignatureSpi this$0;

    public PSSSignatureSpi$NullPssDigest(Digest mgfDigest)
    {
        this$0 = PSSSignatureSpi.this;
        super();
        bOut = new ByteArrayOutputStream();
        oddTime = true;
        baseDigest = mgfDigest;
    }
}
