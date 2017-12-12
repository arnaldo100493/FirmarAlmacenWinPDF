// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcAsymmetricKeyWrapper.java

package co.org.bouncy.operator.bc;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.operator.*;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.operator.bc:
//            OperatorUtils

public abstract class BcAsymmetricKeyWrapper extends AsymmetricKeyWrapper
{

    public BcAsymmetricKeyWrapper(AlgorithmIdentifier encAlgId, AsymmetricKeyParameter publicKey)
    {
        super(encAlgId);
        this.publicKey = publicKey;
    }

    public BcAsymmetricKeyWrapper setSecureRandom(SecureRandom random)
    {
        this.random = random;
        return this;
    }

    public byte[] generateWrappedKey(GenericKey encryptionKey)
        throws OperatorException
    {
        AsymmetricBlockCipher keyEncryptionCipher = createAsymmetricWrapper(getAlgorithmIdentifier().getAlgorithm());
        CipherParameters params = publicKey;
        if(random != null)
            params = new ParametersWithRandom(params, random);
        try
        {
            byte keyEnc[] = OperatorUtils.getKeyBytes(encryptionKey);
            keyEncryptionCipher.init(true, publicKey);
            return keyEncryptionCipher.processBlock(keyEnc, 0, keyEnc.length);
        }
        catch(InvalidCipherTextException e)
        {
            throw new OperatorException("unable to encrypt contents key", e);
        }
    }

    protected abstract AsymmetricBlockCipher createAsymmetricWrapper(ASN1ObjectIdentifier asn1objectidentifier);

    private AsymmetricKeyParameter publicKey;
    private SecureRandom random;
}
