// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaPGPDigestCalculatorProviderBuilder.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import co.org.bouncy.openpgp.operator.PGPDigestCalculatorProvider;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            JcaPGPDigestCalculatorProviderBuilder, OperatorHelper

class JcaPGPDigestCalculatorProviderBuilder$1
    implements PGPDigestCalculatorProvider
{

    public PGPDigestCalculator get(final int algorithm)
        throws PGPException
    {
        final gestOutputStream stream;
        final MessageDigest dig;
        try
        {
            dig = JcaPGPDigestCalculatorProviderBuilder.access$000(JcaPGPDigestCalculatorProviderBuilder.this).createDigest(algorithm);
            stream = new gestOutputStream(JcaPGPDigestCalculatorProviderBuilder.this, dig);
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
            final JcaPGPDigestCalculatorProviderBuilder.DigestOutputStream val$stream;
            final MessageDigest val$dig;
            final JcaPGPDigestCalculatorProviderBuilder._cls1 this$1;

            
            {
                this$1 = JcaPGPDigestCalculatorProviderBuilder._cls1.this;
                algorithm = i;
                stream = digestoutputstream;
                dig = messagedigest;
                super();
            }
        }
;
    }

    final JcaPGPDigestCalculatorProviderBuilder this$0;

    JcaPGPDigestCalculatorProviderBuilder$1()
    {
        this$0 = JcaPGPDigestCalculatorProviderBuilder.this;
        super();
    }
}
