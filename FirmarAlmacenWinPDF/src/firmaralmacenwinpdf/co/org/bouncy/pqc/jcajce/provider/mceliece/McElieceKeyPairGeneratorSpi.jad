// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceKeyPairGeneratorSpi.java

package co.org.bouncy.pqc.jcajce.provider.mceliece;

import co.org.bouncy.crypto.AsymmetricCipherKeyPair;
import co.org.bouncy.pqc.crypto.mceliece.*;
import co.org.bouncy.pqc.jcajce.spec.ECCKeyGenParameterSpec;
import co.org.bouncy.pqc.jcajce.spec.McElieceCCA2ParameterSpec;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;

// Referenced classes of package co.org.bouncy.pqc.jcajce.provider.mceliece:
//            BCMcEliecePublicKey, BCMcEliecePrivateKey, BCMcElieceCCA2PublicKey, BCMcElieceCCA2PrivateKey

public abstract class McElieceKeyPairGeneratorSpi extends KeyPairGenerator
{
    public static class McEliece extends McElieceKeyPairGeneratorSpi
    {

        public void initialize(AlgorithmParameterSpec params)
            throws InvalidAlgorithmParameterException
        {
            kpg = new McElieceKeyPairGenerator();
            initialize(params);
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

        public McEliece()
        {
            super("McEliece");
        }
    }

    public static class McElieceCCA2 extends McElieceKeyPairGeneratorSpi
    {

        public void initialize(AlgorithmParameterSpec params)
            throws InvalidAlgorithmParameterException
        {
            kpg = new McElieceCCA2KeyPairGenerator();
            initialize(params);
            ECCKeyGenParameterSpec ecc = (ECCKeyGenParameterSpec)params;
            McElieceCCA2KeyGenerationParameters mccca2KGParams = new McElieceCCA2KeyGenerationParameters(new SecureRandom(), new McElieceCCA2Parameters(ecc.getM(), ecc.getT()));
            kpg.init(mccca2KGParams);
        }

        public void initialize(int keySize, SecureRandom random)
        {
            McElieceCCA2ParameterSpec paramSpec = new McElieceCCA2ParameterSpec();
            try
            {
                initialize(((AlgorithmParameterSpec) (paramSpec)));
            }
            catch(InvalidAlgorithmParameterException ae) { }
        }

        public KeyPair generateKeyPair()
        {
            AsymmetricCipherKeyPair generateKeyPair = kpg.generateKeyPair();
            McElieceCCA2PrivateKeyParameters sk = (McElieceCCA2PrivateKeyParameters)generateKeyPair.getPrivate();
            McElieceCCA2PublicKeyParameters pk = (McElieceCCA2PublicKeyParameters)generateKeyPair.getPublic();
            return new KeyPair(new BCMcElieceCCA2PublicKey(pk), new BCMcElieceCCA2PrivateKey(sk));
        }

        McElieceCCA2KeyPairGenerator kpg;

        public McElieceCCA2()
        {
            super("McElieceCCA-2");
        }

        public McElieceCCA2(String s)
        {
            super(s);
        }
    }


    public McElieceKeyPairGeneratorSpi(String algorithmName)
    {
        super(algorithmName);
    }
}
