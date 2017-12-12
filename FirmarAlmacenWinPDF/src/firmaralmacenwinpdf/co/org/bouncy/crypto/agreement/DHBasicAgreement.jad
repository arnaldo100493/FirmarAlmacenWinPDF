// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DHBasicAgreement.java

package co.org.bouncy.crypto.agreement;

import co.org.bouncy.crypto.BasicAgreement;
import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.params.*;
import java.math.BigInteger;

public class DHBasicAgreement
    implements BasicAgreement
{

    public DHBasicAgreement()
    {
    }

    public void init(CipherParameters param)
    {
        AsymmetricKeyParameter kParam;
        if(param instanceof ParametersWithRandom)
        {
            ParametersWithRandom rParam = (ParametersWithRandom)param;
            kParam = (AsymmetricKeyParameter)rParam.getParameters();
        } else
        {
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

    public int getFieldSize()
    {
        return (key.getParameters().getP().bitLength() + 7) / 8;
    }

    public BigInteger calculateAgreement(CipherParameters pubKey)
    {
        DHPublicKeyParameters pub = (DHPublicKeyParameters)pubKey;
        if(!pub.getParameters().equals(dhParams))
            throw new IllegalArgumentException("Diffie-Hellman public key has wrong parameters.");
        else
            return pub.getY().modPow(key.getX(), dhParams.getP());
    }

    private DHPrivateKeyParameters key;
    private DHParameters dhParams;
}
