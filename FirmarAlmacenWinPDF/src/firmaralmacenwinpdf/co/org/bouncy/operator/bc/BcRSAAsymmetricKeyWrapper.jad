// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcRSAAsymmetricKeyWrapper.java

package co.org.bouncy.operator.bc;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.crypto.AsymmetricBlockCipher;
import co.org.bouncy.crypto.encodings.PKCS1Encoding;
import co.org.bouncy.crypto.engines.RSAEngine;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.crypto.util.PublicKeyFactory;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.operator.bc:
//            BcAsymmetricKeyWrapper

public class BcRSAAsymmetricKeyWrapper extends BcAsymmetricKeyWrapper
{

    public BcRSAAsymmetricKeyWrapper(AlgorithmIdentifier encAlgId, AsymmetricKeyParameter publicKey)
    {
        super(encAlgId, publicKey);
    }

    public BcRSAAsymmetricKeyWrapper(AlgorithmIdentifier encAlgId, SubjectPublicKeyInfo publicKeyInfo)
        throws IOException
    {
        super(encAlgId, PublicKeyFactory.createKey(publicKeyInfo));
    }

    protected AsymmetricBlockCipher createAsymmetricWrapper(ASN1ObjectIdentifier algorithm)
    {
        return new PKCS1Encoding(new RSAEngine());
    }
}
