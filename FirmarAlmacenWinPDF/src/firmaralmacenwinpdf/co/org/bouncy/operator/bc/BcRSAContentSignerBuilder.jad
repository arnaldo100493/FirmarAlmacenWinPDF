// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcRSAContentSignerBuilder.java

package co.org.bouncy.operator.bc;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.Signer;
import co.org.bouncy.crypto.signers.RSADigestSigner;
import co.org.bouncy.operator.OperatorCreationException;

// Referenced classes of package co.org.bouncy.operator.bc:
//            BcContentSignerBuilder, BcDigestProvider

public class BcRSAContentSignerBuilder extends BcContentSignerBuilder
{

    public BcRSAContentSignerBuilder(AlgorithmIdentifier sigAlgId, AlgorithmIdentifier digAlgId)
    {
        super(sigAlgId, digAlgId);
    }

    protected Signer createSigner(AlgorithmIdentifier sigAlgId, AlgorithmIdentifier digAlgId)
        throws OperatorCreationException
    {
        co.org.bouncy.crypto.Digest dig = digestProvider.get(digAlgId);
        return new RSADigestSigner(dig);
    }
}
