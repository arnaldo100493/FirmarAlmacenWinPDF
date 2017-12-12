// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPGPContentVerifierBuilderProvider.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.crypto.Signer;
import co.org.bouncy.openpgp.PGPPublicKey;
import co.org.bouncy.openpgp.operator.PGPContentVerifier;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.openpgp.operator.bc:
//            SignerOutputStream, BcPGPContentVerifierBuilderProvider

class BcPGPContentVerifierBuilderProvider$BcPGPContentVerifierBuilder$1
    implements PGPContentVerifier
{

    public int getHashAlgorithm()
    {
        return BcPGPContentVerifierBuilderProvider.BcPGPContentVerifierBuilder.access$100(BcPGPContentVerifierBuilderProvider.BcPGPContentVerifierBuilder.this);
    }

    public int getKeyAlgorithm()
    {
        return BcPGPContentVerifierBuilderProvider.BcPGPContentVerifierBuilder.access$200(BcPGPContentVerifierBuilderProvider.BcPGPContentVerifierBuilder.this);
    }

    public long getKeyID()
    {
        return val$publicKey.getKeyID();
    }

    public boolean verify(byte expected[])
    {
        return val$signer.verifySignature(expected);
    }

    public OutputStream getOutputStream()
    {
        return new SignerOutputStream(val$signer);
    }

    final PGPPublicKey val$publicKey;
    final Signer val$signer;
    final BcPGPContentVerifierBuilderProvider.BcPGPContentVerifierBuilder this$1;

    BcPGPContentVerifierBuilderProvider$BcPGPContentVerifierBuilder$1()
    {
        this$1 = final_bcpgpcontentverifierbuilder;
        val$publicKey = pgppublickey;
        val$signer = Signer.this;
        super();
    }
}
