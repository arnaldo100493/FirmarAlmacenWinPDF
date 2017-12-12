// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcAsymmetricKeyUnwrapper.java

package co.org.bouncy.operator.bc;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.AsymmetricBlockCipher;
import co.org.bouncy.crypto.InvalidCipherTextException;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.operator.*;

public abstract class BcAsymmetricKeyUnwrapper extends AsymmetricKeyUnwrapper
{

    public BcAsymmetricKeyUnwrapper(AlgorithmIdentifier encAlgId, AsymmetricKeyParameter privateKey)
    {
        super(encAlgId);
        this.privateKey = privateKey;
    }

    public GenericKey generateUnwrappedKey(AlgorithmIdentifier encryptedKeyAlgorithm, byte encryptedKey[])
        throws OperatorException
    {
        AsymmetricBlockCipher keyCipher = createAsymmetricUnwrapper(getAlgorithmIdentifier().getAlgorithm());
        keyCipher.init(false, privateKey);
        byte key[];
        try
        {
            key = keyCipher.processBlock(encryptedKey, 0, encryptedKey.length);
            if(encryptedKeyAlgorithm.getAlgorithm().equals(PKCSObjectIdentifiers.des_EDE3_CBC))
                return new GenericKey(encryptedKeyAlgorithm, key);
        }
        catch(InvalidCipherTextException e)
        {
            throw new OperatorException((new StringBuilder()).append("unable to recover secret key: ").append(e.getMessage()).toString(), e);
        }
        return new GenericKey(encryptedKeyAlgorithm, key);
    }

    protected abstract AsymmetricBlockCipher createAsymmetricUnwrapper(ASN1ObjectIdentifier asn1objectidentifier);

    private AsymmetricKeyParameter privateKey;
}
