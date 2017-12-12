// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPGPDigestCalculatorProvider.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.crypto.Digest;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.openpgp.operator.bc:
//            BcPGPDigestCalculatorProvider

class BcPGPDigestCalculatorProvider$1
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
    final gestOutputStream val$stream;
    final Digest val$dig;
    final BcPGPDigestCalculatorProvider this$0;

    BcPGPDigestCalculatorProvider$1()
    {
        this$0 = final_bcpgpdigestcalculatorprovider;
        val$algorithm = i;
        val$stream = gestoutputstream;
        val$dig = Digest.this;
        super();
    }
}
