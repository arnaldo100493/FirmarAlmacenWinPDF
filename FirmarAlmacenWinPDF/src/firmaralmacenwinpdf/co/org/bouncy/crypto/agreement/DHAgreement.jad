// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DHAgreement.java

package co.org.bouncy.crypto.agreement;

import co.org.bouncy.crypto.AsymmetricCipherKeyPair;
import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.generators.DHKeyPairGenerator;
import co.org.bouncy.crypto.params.*;
import java.math.BigInteger;
import java.security.SecureRandom;

public class DHAgreement
{

    public DHAgreement()
    {
    }

    public void init(CipherParameters param)
    {
        AsymmetricKeyParameter kParam;
        if(param instanceof ParametersWithRandom)
        {
            ParametersWithRandom rParam = (ParametersWithRandom)param;
            random = rParam.getRandom();
            kParam = (AsymmetricKeyParameter)rParam.getParameters();
        } else
        {
            random = new SecureRandom();
            kParam = (AsymmetricKeyParameter)param;
        }
        if(!(kParam instanceof DHPrivateKeyParameters))
        {
            throw new IllegalArgumentException("DHEngine expects DHPrivateKeyParameters");
        } else
        {
            key = (DHPrivateKeyParameters)kParam;
            dhParams = key.getParameters();
            return;
        }
    }

    public BigInteger calculateMessage()
    {
        DHKeyPairGenerator dhGen = new DHKeyPairGenerator();
        dhGen.init(new DHKeyGenerationParameters(random, dhParams));
        AsymmetricCipherKeyPair dhPair = dhGen.generateKeyPair();
        privateValue = ((DHPrivateKeyParameters)dhPair.getPrivate()).getX();
        return ((DHPublicKeyParameters)dhPair.getPublic()).getY();
    }

    public BigInteger calculateAgreement(DHPublicKeyParameters pub, BigInteger message)
    {
        if(!pub.getParameters().equals(dhParams))
        {
            throw new IllegalArgumentException("Diffie-Hellman public key has wrong parameters.");
        } else
        {
            BigInteger p = dhParams.getP();
            return message.modPow(key.getX(), p).multiply(pub.getY().modPow(privateValue, p)).mod(p);
        }
    }

    private DHPrivateKeyParameters key;
    private DHParameters dhParams;
    private BigInteger privateValue;
    private SecureRandom random;
}
