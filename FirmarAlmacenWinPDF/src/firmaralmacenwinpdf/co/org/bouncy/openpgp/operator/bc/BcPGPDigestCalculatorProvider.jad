// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPGPDigestCalculatorProvider.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.crypto.Digest;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import co.org.bouncy.openpgp.operator.PGPDigestCalculatorProvider;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.openpgp.operator.bc:
//            BcImplProvider

public class BcPGPDigestCalculatorProvider
    implements PGPDigestCalculatorProvider
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

        byte[] getDigest()
        {
            byte d[] = new byte[dig.getDigestSize()];
            dig.doFinal(d, 0);
            return d;
        }

        private Digest dig;
        final BcPGPDigestCalculatorProvider this$0;

        DigestOutputStream(Digest dig)
        {
            this$0 = BcPGPDigestCalculatorProvider.this;
            super();
            this.dig = dig;
        }
    }


    public BcPGPDigestCalculatorProvider()
    {
    }

    public PGPDigestCalculator get(final int algorithm)
        throws PGPException
    {
        final Digest dig = BcImplProvider.createDigest(algorithm);
        final DigestOutputStream stream = new DigestOutputStream(dig);
        return new PGPDigestCalculator() {

            public int getAlgorithm()
            {
                return algorithm;
            }

            public OutputStream getOutputStream()
            {
                return stream;
            }

            public byte[] getDigest()
            {
                return stream.getDigest();
            }

            public void reset()
            {
                dig.reset();
            }

            final int val$algorithm;
            final DigestOutputStream val$stream;
            final Digest val$dig;
            final BcPGPDigestCalculatorProvider this$0;

            
            {
                this$0 = BcPGPDigestCalculatorProvider.this;
                algorithm = i;
                stream = digestoutputstream;
                dig = digest;
                super();
            }
        }
;
    }
}
