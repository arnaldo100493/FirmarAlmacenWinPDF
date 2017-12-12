// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ElGamalUtil.java

package co.org.bouncy.jcajce.provider.asymmetric.elgamal;

import co.org.bouncy.crypto.params.*;
import co.org.bouncy.jce.interfaces.ElGamalPrivateKey;
import co.org.bouncy.jce.interfaces.ElGamalPublicKey;
import co.org.bouncy.jce.spec.ElGamalParameterSpec;
import java.security.*;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

public class ElGamalUtil
{

    public ElGamalUtil()
    {
    }

    public static AsymmetricKeyParameter generatePublicKeyParameter(PublicKey key)
        throws InvalidKeyException
    {
        if(key instanceof ElGamalPublicKey)
        {
            ElGamalPublicKey k = (ElGamalPublicKey)key;
            return new ElGamalPublicKeyParameters(k.getY(), new ElGamalParameters(k.getParameters().getP(), k.getParameters().getG()));
        }
        if(key instanceof DHPublicKey)
        {
            DHPublicKey k = (DHPublicKey)key;
            return new ElGamalPublicKeyParameters(k.getY(), new ElGamalParameters(k.getParams().getP(), k.getParams().getG()));
        } else
        {
            throw new InvalidKeyException("can't identify public key for El Gamal.");
        }
    }

    public static AsymmetricKeyParameter generatePrivateKeyParameter(PrivateKey key)
        throws InvalidKeyException
    {
        if(key instanceof ElGamalPrivateKey)
        {
            ElGamalPrivateKey k = (ElGamalPrivateKey)key;
            return new ElGamalPrivateKeyParameters(k.getX(), new ElGamalParameters(k.getParameters().getP(), k.getParameters().getG()));
        }
        if(key instanceof DHPrivateKey)
        {
            DHPrivateKey k = (DHPrivateKey)key;
            return new ElGamalPrivateKeyParameters(k.getX(), new ElGamalParameters(k.getParams().getP(), k.getParams().getG()));
        } else
        {
            throw new InvalidKeyException("can't identify private key for El Gamal.");
        }
    }
}
