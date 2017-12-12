// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCMessageDigest.java

package co.org.bouncy.jcajce.provider.digest;

import co.org.bouncy.crypto.Digest;
import java.security.MessageDigest;

public class BCMessageDigest extends MessageDigest
{

    protected BCMessageDigest(Digest digest)
    {
        super(digest.getAlgorithmName());
        this.digest = digest;
    }

    public void engineReset()
    {
        digest.reset();
    }

    public void engineUpdate(byte input)
    {
        digest.update(input);
    }

    public void engineUpdate(byte input[], int offset, int len)
    {
        digest.update(input, offset, len);
    }

    public byte[] engineDigest()
    {
        byte digestBytes[] = new byte[digest.getDigestSize()];
        digest.doFinal(digestBytes, 0);
        return digestBytes;
    }

    protected Digest digest;
}
