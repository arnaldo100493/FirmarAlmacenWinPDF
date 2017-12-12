// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaPGPContentVerifierBuilderProvider.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.openpgp.PGPPublicKey;
import co.org.bouncy.openpgp.operator.PGPContentVerifier;
import java.io.OutputStream;
import java.security.Signature;
import java.security.SignatureException;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            SignatureOutputStream, JcaPGPContentVerifierBuilderProvider

class JcaPGPContentVerifierBuilderProvider$JcaPGPContentVerifierBuilder$1
    implements PGPContentVerifier
{

    public int getHashAlgorithm()
    {
        return JcaPGPContentVerifierBuilderProvider.JcaPGPContentVerifierBuilder.access$200(JcaPGPContentVerifierBuilderProvider.JcaPGPContentVerifierBuilder.this);
    }

    public int getKeyAlgorithm()
    {
        return JcaPGPContentVerifierBuilderProvider.JcaPGPContentVerifierBuilder.access$300(JcaPGPContentVerifierBuilderProvider.JcaPGPContentVerifierBuilder.this);
    }

    public long getKeyID()
    {
        return val$publicKey.getKeyID();
    }

    public boolean verify(byte expected[])
    {
        try
        {
            return val$signature.verify(expected);
        }
        catch(SignatureException e)
        {
            throw new IllegalStateException("unable to verify signature");
        }
    }

    public OutputStream getOutputStream()
    {
        return new SignatureOutputStream(val$signature);
    }

    final PGPPublicKey val$publicKey;
    final Signature val$signature;
    final JcaPGPContentVerifierBuilderProvider.JcaPGPContentVerifierBuilder this$1;

    JcaPGPContentVerifierBuilderProvider$JcaPGPContentVerifierBuilder$1()
    {
        this$1 = final_jcapgpcontentverifierbuilder;
        val$publicKey = pgppublickey;
        val$signature = Signature.this;
        super();
    }
}
