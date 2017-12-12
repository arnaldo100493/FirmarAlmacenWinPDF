// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyPairGeneratorSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.elgamal;

import co.org.bouncy.crypto.AsymmetricCipherKeyPair;
import co.org.bouncy.crypto.generators.ElGamalKeyPairGenerator;
import co.org.bouncy.crypto.generators.ElGamalParametersGenerator;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.jcajce.provider.config.ProviderConfiguration;
import co.org.bouncy.jce.provider.BouncyCastleProvider;
import co.org.bouncy.jce.spec.ElGamalParameterSpec;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.DHParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.elgamal:
//            BCElGamalPublicKey, BCElGamalPrivateKey

public class KeyPairGeneratorSpi extends KeyPairGenerator
{

    public KeyPairGeneratorSpi()
    {
        super("ElGamal");
        engine = new ElGamalKeyPairGenerator();
        strength = 1024;
        certainty = 20;
        random = new SecureRandom();
        initialised = false;
    }

    public void initialize(int strength, SecureRandom random)
    {
        this.strength = strength;
        this.random = random;
    }

    public void initialize(AlgorithmParameterSpec params, SecureRandom random)
        throws InvalidAlgorithmParameterException
    {
        if(!(params instanceof ElGamalParameterSpec) && !(params instanceof DHParameterSpec))
            throw new InvalidAlgorithmParameterException("parameter object not a DHParameterSpec or an ElGamalParameterSpec");
        if(params instanceof ElGamalParameterSpec)
        {
            ElGamalParameterSpec elParams = (ElGamalParameterSpec)params;
            param = new ElGamalKeyGenerationParameters(random, new ElGamalParameters(elParams.getP(), elParams.getG()));
        } else
        {
            DHParameterSpec dhParams = (DHParameterSpec)params;
            param = new ElGamalKeyGenerationParameters(random, new ElGamalParameters(dhParams.getP(), dhParams.getG(), dhParams.getL()));
        }
        engine.init(param);
        initialised = true;
    }

    public KeyPair generateKeyPair()
    {
        if(!initialised)
        {
            DHParameterSpec dhParams = BouncyCastleProvider.CONFIGURATION.getDHDefaultParameters(strength);
            if(dhParams != null)
            {
                param = new ElGamalKeyGenerationParameters(random, new ElGamalParameters(dhParams.getP(), dhParams.getG(), dhParams.getL()));
            } else
            {
                ElGamalParametersGenerator pGen = new ElGamalParametersGenerator();
                pGen.init(strength, certainty, random);
                param = new ElGamalKeyGenerationParameters(random, pGen.generateParameters());
            }
            engine.init(param);
            initialised = true;
        }
        AsymmetricCipherKeyPair pair = engine.generateKeyPair();
        ElGamalPublicKeyParameters pub = (ElGamalPublicKeyParameters)pair.getPublic();
        ElGamalPrivateKeyParameters priv = (ElGamalPrivateKeyParameters)pair.getPrivate();
        return new KeyPair(new BCElGamalPublicKey(pub), new BCElGamalPrivateKey(priv));
    }

    ElGamalKeyGenerationParameters param;
    ElGamalKeyPairGenerator engine;
    int strength;
    int certainty;
    SecureRandom random;
    boolean initialised;
}
