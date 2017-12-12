// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SHA1PGPDigestCalculator.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import java.io.IOException;
import java.io.OutputStream;

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
            dig.update(bytes, 0, bytes.length);
        }

        public void write(int b)
            throws IOException
        {
            dig.update((byte)b);
        }

        private Digest dig;
        final SHA1PGPDigestCalculator this$0;

        DigestOutputStream(Digest dig)
        {
            this$0 = SHA1PGPDigestCalculator.this;
            super();
            this.dig = dig;
        }
    }


    SHA1PGPDigestCalculator()
    {
        digest = new SHA1Digest();
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
        byte d[] = new byte[digest.getDigestSize()];
        digest.doFinal(d, 0);
        return d;
    }

    public void reset()
    {
        digest.reset();
    }

    private Digest digest;
}
