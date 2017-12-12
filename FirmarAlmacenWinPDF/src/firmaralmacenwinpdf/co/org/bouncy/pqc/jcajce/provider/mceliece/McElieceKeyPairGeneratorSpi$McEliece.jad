// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceKeyPairGeneratorSpi.java

package co.org.bouncy.pqc.jcajce.provider.mceliece;

import co.org.bouncy.crypto.AsymmetricCipherKeyPair;
import co.org.bouncy.pqc.crypto.mceliece.*;
import co.org.bouncy.pqc.jcajce.spec.ECCKeyGenParameterSpec;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;

// Referenced classes of package co.org.bouncy.pqc.jcajce.provider.mceliece:
//            McElieceKeyPairGeneratorSpi, BCMcEliecePublicKey, BCMcEliecePrivateKey

public static class McElieceKeyPairGeneratorSpi$McEliece extends McElieceKeyPairGeneratorSpi
{

    public void initialize(AlgorithmParameterSpec params)
        throws InvalidAlgorithmParameterException
    {
        kpg = new McElieceKeyPairGenerator();
        super.initialize(params);
        ECCKeyGenParameterSpec ecc = (ECCKeyGenParameterSpec)params;
        McElieceKeyGenerationParameters mccKGParams = new McElieceKeyGenerationParameters(new SecureRandom(), new McElieceParameters(ecc.getM(), ecc.getT()));
        kpg.init(mccKGParams);
    }

    public void initialize(int keySize, SecureRandom random)
    {
        ECCKeyGenParameterSpec paramSpec = new ECCKeyGenParameterSpec();
        try
        {
            initialize(((AlgorithmParameterSpec) (paramSpec)));
        }
        catch(InvalidAlgorithmParameterException ae) { }
    }

    public KeyPair generateKeyPair()
    {
        AsymmetricCipherKeyPair generateKeyPair = kpg.generateKeyPair();
        McEliecePrivateKeyParameters sk = (McEliecePrivateKeyParameters)generateKeyPair.getPrivate();
        McEliecePublicKeyParameters pk = (McEliecePublicKeyParameters)generateKeyPair.getPublic();
        return new KeyPair(new BCMcEliecePublicKey(pk), new BCMcEliecePrivateKey(sk));
    }

    McElieceKeyPairGenerator kpg;

    public McElieceKeyPairGeneratorSpi$McEliece()
    {
        super("McEliece");
    }
}
