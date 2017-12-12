// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePEMEncryptorBuilder.java

package co.org.bouncy.openssl.jcajce;

import co.org.bouncy.openssl.PEMEncryptor;
import co.org.bouncy.openssl.PEMException;

// Referenced classes of package co.org.bouncy.openssl.jcajce:
//            JcePEMEncryptorBuilder, PEMUtilities

class JcePEMEncryptorBuilder$1
    implements PEMEncryptor
{

    public String getAlgorithm()
    {
        return JcePEMEncryptorBuilder.access$000(JcePEMEncryptorBuilder.this);
    }

    public byte[] getIV()
    {
        return val$iv;
    }

    public byte[] encrypt(byte encoding[])
        throws PEMException
    {
        return PEMUtilities.crypt(true, JcePEMEncryptorBuilder.access$100(JcePEMEncryptorBuilder.this), encoding, val$password, JcePEMEncryptorBuilder.access$000(JcePEMEncryptorBuilder.this), val$iv);
    }

    final byte val$iv[];
    final char val$password[];
    final JcePEMEncryptorBuilder this$0;

    JcePEMEncryptorBuilder$1()
    {
        this$0 = final_jcepemencryptorbuilder;
        val$iv = abyte0;
        val$password = _5B_C.this;
        super();
    }
}
