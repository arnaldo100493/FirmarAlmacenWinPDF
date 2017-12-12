// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DHKeyPairGenerator.java

package co.org.bouncy.crypto.generators;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.*;

// Referenced classes of package co.org.bouncy.crypto.generators:
//            DHKeyGeneratorHelper

public class DHKeyPairGenerator
    implements AsymmetricCipherKeyPairGenerator
{

    public DHKeyPairGenerator()
    {
    }

    public void init(KeyGenerationParameters param)
    {
        this.param = (DHKeyGenerationParameters)param;
    }

    public AsymmetricCipherKeyPair generateKeyPair()
    {
        DHKeyGeneratorHelper helper = DHKeyGeneratorHelper.INSTANCE;
        co.org.bouncy.crypto.params.DHParameters dhp = param.getParameters();
        java.math.BigInteger x = helper.calculatePrivate(dhp, param.getRandom());
        java.math.BigInteger y = helper.calculatePublic(dhp, x);
        return new AsymmetricCipherKeyPair(new DHPublicKeyParameters(y, dhp), new DHPrivateKeyParameters(x, dhp));
    }

    private DHKeyGenerationParameters param;
}
