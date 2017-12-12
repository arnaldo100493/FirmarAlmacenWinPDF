// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcRSAAsymmetricKeyUnwrapper.java

package co.org.bouncy.operator.bc;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.AsymmetricBlockCipher;
import co.org.bouncy.crypto.encodings.PKCS1Encoding;
import co.org.bouncy.crypto.engines.RSAEngine;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;

// Referenced classes of package co.org.bouncy.operator.bc:
//            BcAsymmetricKeyUnwrapper

public class BcRSAAsymmetricKeyUnwrapper extends BcAsymmetricKeyUnwrapper
{

    public BcRSAAsymmetricKeyUnwrapper(AlgorithmIdentifier encAlgId, AsymmetricKeyParameter privateKey)
    {
        super(encAlgId, privateKey);
    }

    protected AsymmetricBlockCipher createAsymmetricUnwrapper(ASN1ObjectIdentifier algorithm)
    {
        return new PKCS1Encoding(new RSAEngine());
    }
}
