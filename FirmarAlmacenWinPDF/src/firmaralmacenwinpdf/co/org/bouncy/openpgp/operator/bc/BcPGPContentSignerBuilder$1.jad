// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPGPContentSignerBuilder.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.crypto.CryptoException;
import co.org.bouncy.crypto.Signer;
import co.org.bouncy.openpgp.PGPPrivateKey;
import co.org.bouncy.openpgp.operator.PGPContentSigner;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import co.org.bouncy.util.io.TeeOutputStream;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.openpgp.operator.bc:
//            SignerOutputStream, BcPGPContentSignerBuilder

class BcPGPContentSignerBuilder$1
    implements PGPContentSigner
{

    public int getType()
    {
        return val$signatureType;
    }

    public int getHashAlgorithm()
    {
        return BcPGPContentSignerBuilder.access$000(BcPGPContentSignerBuilder.this);
    }

    public int getKeyAlgorithm()
    {
        return BcPGPContentSignerBuilder.access$100(BcPGPContentSignerBuilder.this);
    }

    public long getKeyID()
    {
        return val$privateKey.getKeyID();
    }

    public OutputStream getOutputStream()
    {
        return new TeeOutputStream(new SignerOutputStream(val$signer), val$digestCalculator.getOutputStream());
    }

    public byte[] getSignature()
    {
        try
        {
            return val$signer.generateSignature();
        }
        catch(CryptoException e)
        {
            throw new IllegalStateException("unable to create signature");
        }
    }

    public byte[] getDigest()
    {
        return val$digestCalculator.getDigest();
    }

    final int val$signatureType;
    final PGPPrivateKey val$privateKey;
    final Signer val$signer;
    final PGPDigestCalculator val$digestCalculator;
    final BcPGPContentSignerBuilder this$0;

    BcPGPContentSignerBuilder$1()
    {
        this$0 = final_bcpgpcontentsignerbuilder;
        val$signatureType = i;
        val$privateKey = pgpprivatekey;
        val$signer = signer1;
        val$digestCalculator = PGPDigestCalculator.this;
        super();
    }
}
