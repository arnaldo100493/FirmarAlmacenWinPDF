// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorHelper.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.openpgp.operator.PGPDataDecryptor;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import java.io.InputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            SHA1PGPDigestCalculator, OperatorHelper

class OperatorHelper$1
    implements PGPDataDecryptor
{

    public InputStream getInputStream(InputStream in)
    {
        return new CipherInputStream(in, val$c);
    }

    public int getBlockSize()
    {
        return val$c.getBlockSize();
    }

    public PGPDigestCalculator getIntegrityCalculator()
    {
        return new SHA1PGPDigestCalculator();
    }

    final Cipher val$c;
    final OperatorHelper this$0;

    OperatorHelper$1()
    {
        this$0 = final_operatorhelper;
        val$c = Cipher.this;
        super();
    }
}
