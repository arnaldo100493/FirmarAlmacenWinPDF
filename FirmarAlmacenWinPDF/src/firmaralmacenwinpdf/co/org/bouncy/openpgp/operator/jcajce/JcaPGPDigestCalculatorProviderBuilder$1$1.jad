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

class JcaPGPDigestCalculatorProviderBuilder$1$1
    implements PGPDigestCalculator
{

    public int getAlgorithm()
    {
        return val$algorithm;
    }

    public OutputStream getOutputStream()
    {
        return val$stream;
    }

    public byte[] getDigest()
    {
        return val$stream.getDigest();
    }

    public void reset()
    {
        val$dig.reset();
    }

    final int val$algorithm;
    final stOutputStream val$stream;
    final MessageDigest val$dig;
    final JcaPGPDigestCalculatorProviderBuilder._cls1 this$1;

    JcaPGPDigestCalculatorProviderBuilder$1$1()
    {
        this$1 = final__pcls1;
        val$algorithm = i;
        val$stream = stoutputstream;
        val$dig = MessageDigest.this;
        super();
    }

    // Unreferenced inner class co/org/bouncy/openpgp/operator/jcajce/JcaPGPDigestCalculatorProviderBuilder$1

/* anonymous class */
    class JcaPGPDigestCalculatorProviderBuilder._cls1
        implements PGPDigestCalculatorProvider
    {

        public PGPDigestCalculator get(final int algorithm)
            throws PGPException
        {
            final JcaPGPDigestCalculatorProviderBuilder.DigestOutputStream stream;
            MessageDigest dig;
            try
            {
                dig = JcaPGPDigestCalculatorProviderBuilder.access$000(JcaPGPDigestCalculatorProviderBuilder.this).createDigest(algorithm);
                stream = new JcaPGPDigestCalculatorProviderBuilder.DigestOutputStream(JcaPGPDigestCalculatorProviderBuilder.this, dig);
            }
            catch(GeneralSecurityException e)
            {
                throw new PGPException((new StringBuilder()).append("exception on setup: ").append(e).toString(), e);
            }
            return dig. new JcaPGPDigestCalculatorProviderBuilder._cls1._cls1();
        }

        final JcaPGPDigestCalculatorProviderBuilder this$0;

            
            {
                this$0 = JcaPGPDigestCalculatorProviderBuilder.this;
                super();
            }
    }

}
