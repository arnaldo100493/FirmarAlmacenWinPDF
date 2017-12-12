// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaPGPContentSignerBuilder.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.openpgp.operator.PGPContentSigner;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import co.org.bouncy.util.io.TeeOutputStream;
import java.io.OutputStream;
import java.security.Signature;
import java.security.SignatureException;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            SignatureOutputStream, JcaPGPContentSignerBuilder

class JcaPGPContentSignerBuilder$1
    implements PGPContentSigner
{

    public int getType()
    {
        return val$signatureType;
    }

    public int getHashAlgorithm()
    {
        return JcaPGPContentSignerBuilder.access$000(JcaPGPContentSignerBuilder.this);
    }

    public int getKeyAlgorithm()
    {
        return JcaPGPContentSignerBuilder.access$100(JcaPGPContentSignerBuilder.this);
    }

    public long getKeyID()
    {
        return val$keyID;
    }

    public OutputStream getOutputStream()
    {
        return new TeeOutputStream(new SignatureOutputStream(val$signature), val$digestCalculator.getOutputStream());
    }

    public byte[] getSignature()
    {
        try
        {
            return val$signature.sign();
        }
        catch(SignatureException e)
        {
            throw new IllegalStateException("unable to create signature");
        }
    }

    public byte[] getDigest()
    {
        return val$digestCalculator.getDigest();
    }

    final int val$signatureType;
    final long val$keyID;
    final Signature val$signature;
    final PGPDigestCalculator val$digestCalculator;
    final JcaPGPContentSignerBuilder this$0;

    JcaPGPContentSignerBuilder$1()
    {
        this$0 = final_jcapgpcontentsignerbuilder;
        val$signatureType = i;
        val$keyID = l;
        val$signature = signature1;
        val$digestCalculator = PGPDigestCalculator.this;
        super();
    }
}
