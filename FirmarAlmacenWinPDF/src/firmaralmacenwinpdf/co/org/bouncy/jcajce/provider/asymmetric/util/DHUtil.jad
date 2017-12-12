// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DHUtil.java

package co.org.bouncy.jcajce.provider.asymmetric.util;

import co.org.bouncy.crypto.params.*;
import java.security.*;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

public class DHUtil
{

    public DHUtil()
    {
    }

    public static AsymmetricKeyParameter generatePublicKeyParameter(PublicKey key)
        throws InvalidKeyException
    {
        if(key instanceof DHPublicKey)
        {
            DHPublicKey k = (DHPublicKey)key;
            return new DHPublicKeyParameters(k.getY(), new DHParameters(k.getParams().getP(), k.getParams().getG(), null, k.getParams().getL()));
        } else
        {
            throw new InvalidKeyException("can't identify DH public key.");
        }
    }

    public static AsymmetricKeyParameter generatePrivateKeyParameter(PrivateKey key)
        throws InvalidKeyException
    {
        if(key instanceof DHPrivateKey)
        {
            DHPrivateKey k = (DHPrivateKey)key;
            return new DHPrivateKeyParameters(k.getX(), new DHParameters(k.getParams().getP(), k.getParams().getG(), null, k.getParams().getL()));
        } else
        {
            throw new InvalidKeyException("can't identify DH private key.");
        }
    }
}
