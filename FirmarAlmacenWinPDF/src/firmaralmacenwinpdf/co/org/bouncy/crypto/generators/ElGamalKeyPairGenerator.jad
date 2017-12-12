// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ElGamalKeyPairGenerator.java

package co.org.bouncy.crypto.generators;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.*;

// Referenced classes of package co.org.bouncy.crypto.generators:
//            DHKeyGeneratorHelper

public class ElGamalKeyPairGenerator
    implements AsymmetricCipherKeyPairGenerator
{

    public ElGamalKeyPairGenerator()
    {
    }

    public void init(KeyGenerationParameters param)
    {
        this.param = (ElGamalKeyGenerationParameters)param;
    }

    public AsymmetricCipherKeyPair generateKeyPair()
    {
        DHKeyGeneratorHelper helper = DHKeyGeneratorHelper.INSTANCE;
        ElGamalParameters egp = param.getParameters();
        DHParameters dhp = new DHParameters(egp.getP(), egp.getG(), null, egp.getL());
        java.math.BigInteger x = helper.calculatePrivate(dhp, param.getRandom());
        java.math.BigInteger y = helper.calculatePublic(dhp, x);
        return new AsymmetricCipherKeyPair(new ElGamalPublicKeyParameters(y, egp), new ElGamalPrivateKeyParameters(x, egp));
    }

    private ElGamalKeyGenerationParameters param;
}
