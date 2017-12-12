// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyPairGeneratorSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.dh;

import co.org.bouncy.crypto.AsymmetricCipherKeyPair;
import co.org.bouncy.crypto.generators.DHBasicKeyPairGenerator;
import co.org.bouncy.crypto.generators.DHParametersGenerator;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.jcajce.provider.config.ProviderConfiguration;
import co.org.bouncy.jce.provider.BouncyCastleProvider;
import co.org.bouncy.util.Integers;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Hashtable;
import javax.crypto.spec.DHParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.dh:
//            BCDHPublicKey, BCDHPrivateKey

public class KeyPairGeneratorSpi extends KeyPairGenerator
{

    public KeyPairGeneratorSpi()
    {
        super("DH");
        engine = new DHBasicKeyPairGenerator();
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
        if(!(params instanceof DHParameterSpec))
        {
            throw new InvalidAlgorithmParameterException("parameter object not a DHParameterSpec");
        } else
        {
            DHParameterSpec dhParams = (DHParameterSpec)params;
            param = new DHKeyGenerationParameters(random, new DHParameters(dhParams.getP(), dhParams.getG(), null, dhParams.getL()));
            engine.init(param);
            initialised = true;
            return;
        }
    }

    public KeyPair generateKeyPair()
    {
        if(!initialised)
        {
            Integer paramStrength = Integers.valueOf(strength);
            if(params.containsKey(paramStrength))
            {
                param = (DHKeyGenerationParameters)params.get(paramStrength);
            } else
            {
                DHParameterSpec dhParams = BouncyCastleProvider.CONFIGURATION.getDHDefaultParameters(strength);
                if(dhParams != null)
                    param = new DHKeyGenerationParameters(random, new DHParameters(dhParams.getP(), dhParams.getG(), null, dhParams.getL()));
                else
                    synchronized(lock)
                    {
                        if(params.containsKey(paramStrength))
                        {
                            param = (DHKeyGenerationParameters)params.get(paramStrength);
                        } else
                        {
                            DHParametersGenerator pGen = new DHParametersGenerator();
                            pGen.init(strength, certainty, random);
                            param = new DHKeyGenerationParameters(random, pGen.generateParameters());
                            params.put(paramStrength, param);
                        }
                    }
            }
            engine.init(param);
            initialised = true;
        }
        AsymmetricCipherKeyPair pair = engine.generateKeyPair();
        DHPublicKeyParameters pub = (DHPublicKeyParameters)pair.getPublic();
        DHPrivateKeyParameters priv = (DHPrivateKeyParameters)pair.getPrivate();
        return new KeyPair(new BCDHPublicKey(pub), new BCDHPrivateKey(priv));
    }

    private static Hashtable params = new Hashtable();
    private static Object lock = new Object();
    DHKeyGenerationParameters param;
    DHBasicKeyPairGenerator engine;
    int strength;
    int certainty;
    SecureRandom random;
    boolean initialised;

}
