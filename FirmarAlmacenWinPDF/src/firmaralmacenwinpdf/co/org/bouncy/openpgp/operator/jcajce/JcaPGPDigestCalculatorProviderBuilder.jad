// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaPGPDigestCalculatorProviderBuilder.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.jcajce.*;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import co.org.bouncy.openpgp.operator.PGPDigestCalculatorProvider;
import java.io.IOException;
import java.io.OutputStream;
import java.security.*;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            OperatorHelper

public class JcaPGPDigestCalculatorProviderBuilder
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
        final JcaPGPDigestCalculatorProviderBuilder this$0;

        DigestOutputStream(MessageDigest dig)
        {
            this$0 = JcaPGPDigestCalculatorProviderBuilder.this;
            super();
            this.dig = dig;
        }
    }


    public JcaPGPDigestCalculatorProviderBuilder()
    {
        helper = new OperatorHelper(new DefaultJcaJceHelper());
    }

    public JcaPGPDigestCalculatorProviderBuilder setProvider(Provider provider)
    {
        helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JcaPGPDigestCalculatorProviderBuilder setProvider(String providerName)
    {
        helper = new OperatorHelper(new NamedJcaJceHelper(providerName));
        return this;
    }

    public PGPDigestCalculatorProvider build()
        throws PGPException
    {
        return new PGPDigestCalculatorProvider() {

            public PGPDigestCalculator get(final int algorithm)
                throws PGPException
            {
                final DigestOutputStream stream;
                final MessageDigest dig;
                try
                {
                    dig = helper.createDigest(algorithm);
                    stream = new DigestOutputStream(dig);
                }
                catch(GeneralSecurityException e)
                {
                    throw new PGPException((new StringBuilder()).append("exception on setup: ").append(e).toString(), e);
                }
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
                    final MessageDigest val$dig;
                    final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        algorithm = i;
                        stream = digestoutputstream;
                        dig = messagedigest;
                        super();
                    }
                }
;
            }

            final JcaPGPDigestCalculatorProviderBuilder this$0;

            
            {
                this$0 = JcaPGPDigestCalculatorProviderBuilder.this;
                super();
            }
        }
;
    }

    private OperatorHelper helper;

}
