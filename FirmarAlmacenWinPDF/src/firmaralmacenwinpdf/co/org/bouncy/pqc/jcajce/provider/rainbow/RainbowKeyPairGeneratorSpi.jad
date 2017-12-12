// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RainbowKeyPairGeneratorSpi.java

package co.org.bouncy.pqc.jcajce.provider.rainbow;

import co.org.bouncy.crypto.AsymmetricCipherKeyPair;
import co.org.bouncy.pqc.crypto.rainbow.*;
import co.org.bouncy.pqc.jcajce.spec.RainbowParameterSpec;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;

// Referenced classes of package co.org.bouncy.pqc.jcajce.provider.rainbow:
//            BCRainbowPublicKey, BCRainbowPrivateKey

public class RainbowKeyPairGeneratorSpi extends KeyPairGenerator
{

    public RainbowKeyPairGeneratorSpi()
    {
        super("Rainbow");
        engine = new RainbowKeyPairGenerator();
        strength = 1024;
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
        if(!(params instanceof RainbowParameterSpec))
        {
            throw new InvalidAlgorithmParameterException("parameter object not a RainbowParameterSpec");
        } else
        {
            RainbowParameterSpec rainbowParams = (RainbowParameterSpec)params;
            param = new RainbowKeyGenerationParameters(random, new RainbowParameters(rainbowParams.getVi()));
            engine.init(param);
            initialised = true;
            return;
        }
    }

    public KeyPair generateKeyPair()
    {
        if(!initialised)
        {
            param = new RainbowKeyGenerationParameters(random, new RainbowParameters((new RainbowParameterSpec()).getVi()));
            engine.init(param);
            initialised = true;
        }
        AsymmetricCipherKeyPair pair = engine.generateKeyPair();
        RainbowPublicKeyParameters pub = (RainbowPublicKeyParameters)pair.getPublic();
        RainbowPrivateKeyParameters priv = (RainbowPrivateKeyParameters)pair.getPrivate();
        return new KeyPair(new BCRainbowPublicKey(pub), new BCRainbowPrivateKey(priv));
    }

    RainbowKeyGenerationParameters param;
    RainbowKeyPairGenerator engine;
    int strength;
    SecureRandom random;
    boolean initialised;
}
