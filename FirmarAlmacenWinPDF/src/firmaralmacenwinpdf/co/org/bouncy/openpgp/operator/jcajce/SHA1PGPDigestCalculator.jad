// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA1PGPDigestCalculator.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class SHA1PGPDigestCalculator
    implements PGPDigestCalculator
{
    private class DigestOutputStream extends OutputStream
    {

        public void write(byte bytes[], int off, int len)
            throws IOException
        {
            dig.update(bytes, off, len);
        }

        public void write(byte bytes[])
            throws IOException
        {
            dig.update(bytes);
        }

        public void write(int b)
            throws IOException
        {
            dig.update((byte)b);
        }

        byte[] getDigest()
        {
            return dig.digest();
        }

        private MessageDigest dig;
        final SHA1PGPDigestCalculator this$0;

        DigestOutputStream(MessageDigest dig)
        {
            this$0 = SHA1PGPDigestCalculator.this;
            super();
            this.dig = dig;
        }
    }


    SHA1PGPDigestCalculator()
    {
        try
        {
            digest = MessageDigest.getInstance("SHA1");
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new IllegalStateException((new StringBuilder()).append("cannot find SHA-1: ").append(e.getMessage()).toString());
        }
    }

    public int getAlgorithm()
    {
        return 2;
    }

    public OutputStream getOutputStream()
    {
        return new DigestOutputStream(digest);
    }

    public byte[] getDigest()
    {
        return digest.digest();
    }

    public void reset()
    {
        digest.reset();
    }

    private MessageDigest digest;
}
