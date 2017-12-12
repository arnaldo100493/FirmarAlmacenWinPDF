// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RainbowKeysToParams.java

package co.org.bouncy.pqc.jcajce.provider.rainbow;

import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.pqc.crypto.rainbow.RainbowPrivateKeyParameters;
import co.org.bouncy.pqc.crypto.rainbow.RainbowPublicKeyParameters;
import java.security.*;

// Referenced classes of package co.org.bouncy.pqc.jcajce.provider.rainbow:
//            BCRainbowPublicKey, BCRainbowPrivateKey

public class RainbowKeysToParams
{

    public RainbowKeysToParams()
    {
    }

    public static AsymmetricKeyParameter generatePublicKeyParameter(PublicKey key)
        throws InvalidKeyException
    {
        if(key instanceof BCRainbowPublicKey)
        {
            BCRainbowPublicKey k = (BCRainbowPublicKey)key;
            return new RainbowPublicKeyParameters(k.getDocLength(), k.getCoeffQuadratic(), k.getCoeffSingular(), k.getCoeffScalar());
        } else
        {
            throw new InvalidKeyException((new StringBuilder()).append("can't identify Rainbow public key: ").append(key.getClass().getName()).toString());
        }
    }

    public static AsymmetricKeyParameter generatePrivateKeyParameter(PrivateKey key)
        throws InvalidKeyException
    {
        if(key instanceof BCRainbowPrivateKey)
        {
            BCRainbowPrivateKey k = (BCRainbowPrivateKey)key;
            return new RainbowPrivateKeyParameters(k.getInvA1(), k.getB1(), k.getInvA2(), k.getB2(), k.getVi(), k.getLayers());
        } else
        {
            throw new InvalidKeyException("can't identify Rainbow private key.");
        }
    }
}
