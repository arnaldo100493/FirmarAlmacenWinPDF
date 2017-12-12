// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPublicKeyKeyEncryptionMethodGenerator.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.crypto.AsymmetricBlockCipher;
import co.org.bouncy.crypto.InvalidCipherTextException;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.PGPPublicKey;
import co.org.bouncy.openpgp.operator.PublicKeyKeyEncryptionMethodGenerator;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.openpgp.operator.bc:
//            BcPGPKeyConverter, BcImplProvider

public class BcPublicKeyKeyEncryptionMethodGenerator extends PublicKeyKeyEncryptionMethodGenerator
{

    public BcPublicKeyKeyEncryptionMethodGenerator(PGPPublicKey key)
    {
        super(key);
        keyConverter = new BcPGPKeyConverter();
    }

    public BcPublicKeyKeyEncryptionMethodGenerator setSecureRandom(SecureRandom random)
    {
        this.random = random;
        return this;
    }

    protected byte[] encryptSessionInfo(PGPPublicKey pubKey, byte sessionInfo[])
        throws PGPException
    {
        try
        {
            AsymmetricBlockCipher c = BcImplProvider.createPublicKeyCipher(pubKey.getAlgorithm());
            AsymmetricKeyParameter key = keyConverter.getPublicKey(pubKey);
            if(random == null)
                random = new SecureRandom();
            c.init(true, new ParametersWithRandom(key, random));
            return c.processBlock(sessionInfo, 0, sessionInfo.length);
        }
        catch(InvalidCipherTextException e)
        {
            throw new PGPException((new StringBuilder()).append("exception encrypting session info: ").append(e.getMessage()).toString(), e);
        }
    }

    private SecureRandom random;
    private BcPGPKeyConverter keyConverter;
}
