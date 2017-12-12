// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyPairGeneratorSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.dsa;

import co.org.bouncy.crypto.AsymmetricCipherKeyPair;
import co.org.bouncy.crypto.generators.DSAKeyPairGenerator;
import co.org.bouncy.crypto.generators.DSAParametersGenerator;
import co.org.bouncy.crypto.params.*;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.DSAParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.dsa:
//            BCDSAPublicKey, BCDSAPrivateKey

public class KeyPairGeneratorSpi extends KeyPairGenerator
{

    public KeyPairGeneratorSpi()
    {
        super("DSA");
        engine = new DSAKeyPairGenerator();
        strength = 1024;
        certainty = 20;
        random = new SecureRandom();
        initialised = false;
    }

    public void initialize(int strength, SecureRandom random)
    {
        if(strength < 512 || strength > 1024 || strength % 64 != 0)
        {
            throw new InvalidParameterException("strength must be from 512 - 1024 and a multiple of 64");
        } else
        {
            this.strength = strength;
            this.random = random;
            return;
        }
    }

    public void initialize(AlgorithmParameterSpec params, SecureRandom random)
        throws InvalidAlgorithmParameterException
    {
        if(!(params instanceof DSAParameterSpec))
        {
            throw new InvalidAlgorithmParameterException("parameter object not a DSAParameterSpec");
        } else
        {
            DSAParameterSpec dsaParams = (DSAParameterSpec)params;
            param = new DSAKeyGenerationParameters(random, new DSAParameters(dsaParams.getP(), dsaParams.getQ(), dsaParams.getG()));
            engine.init(param);
            initialised = true;
            return;
        }
    }

    public KeyPair generateKeyPair()
    {
        if(!initialised)
        {
            DSAParametersGenerator pGen = new DSAParametersGenerator();
            pGen.init(strength, certainty, random);
            param = new DSAKeyGenerationParameters(random, pGen.generateParameters());
            engine.init(param);
            initialised = true;
        }
        AsymmetricCipherKeyPair pair = engine.generateKeyPair();
        DSAPublicKeyParameters pub = (DSAPublicKeyParameters)pair.getPublic();
        DSAPrivateKeyParameters priv = (DSAPrivateKeyParameters)pair.getPrivate();
        return new KeyPair(new BCDSAPublicKey(pub), new BCDSAPrivateKey(priv));
    }

    DSAKeyGenerationParameters param;
    DSAKeyPairGenerator engine;
    int strength;
    int certainty;
    SecureRandom random;
    boolean initialised;
}
