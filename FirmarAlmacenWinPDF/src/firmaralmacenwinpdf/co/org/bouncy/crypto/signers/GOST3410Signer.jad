// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GOST3410Signer.java

package co.org.bouncy.crypto.signers;

import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.DSA;
import co.org.bouncy.crypto.params.*;
import java.math.BigInteger;
import java.security.SecureRandom;

public class GOST3410Signer
    implements DSA
{

    public GOST3410Signer()
    {
    }

    public void init(boolean forSigning, CipherParameters param)
    {
        if(forSigning)
        {
            if(param instanceof ParametersWithRandom)
            {
                ParametersWithRandom rParam = (ParametersWithRandom)param;
                random = rParam.getRandom();
                key = (GOST3410PrivateKeyParameters)rParam.getParameters();
            } else
            {
                random = new SecureRandom();
                key = (GOST3410PrivateKeyParameters)param;
            }
        } else
        {
            key = (GOST3410PublicKeyParameters)param;
        }
    }

    public BigInteger[] generateSignature(byte message[])
    {
        byte mRev[] = new byte[message.length];
        for(int i = 0; i != mRev.length; i++)
            mRev[i] = message[mRev.length - 1 - i];

        BigInteger m = new BigInteger(1, mRev);
        GOST3410Parameters params = key.getParameters();
        BigInteger k;
        do
            k = new BigInteger(params.getQ().bitLength(), random);
        while(k.compareTo(params.getQ()) >= 0);
        BigInteger r = params.getA().modPow(k, params.getP()).mod(params.getQ());
        BigInteger s = k.multiply(m).add(((GOST3410PrivateKeyParameters)key).getX().multiply(r)).mod(params.getQ());
        BigInteger res[] = new BigInteger[2];
        res[0] = r;
        res[1] = s;
        return res;
    }

    public boolean verifySignature(byte message[], BigInteger r, BigInteger s)
    {
        byte mRev[] = new byte[message.length];
        for(int i = 0; i != mRev.length; i++)
            mRev[i] = message[mRev.length - 1 - i];

        BigInteger m = new BigInteger(1, mRev);
        GOST3410Parameters params = key.getParameters();
        BigInteger zero = BigInteger.valueOf(0L);
        if(zero.compareTo(r) >= 0 || params.getQ().compareTo(r) <= 0)
            return false;
        if(zero.compareTo(s) >= 0 || params.getQ().compareTo(s) <= 0)
        {
            return false;
        } else
        {
            BigInteger v = m.modPow(params.getQ().subtract(new BigInteger("2")), params.getQ());
            BigInteger z1 = s.multiply(v).mod(params.getQ());
            BigInteger z2 = params.getQ().subtract(r).multiply(v).mod(params.getQ());
            z1 = params.getA().modPow(z1, params.getP());
            z2 = ((GOST3410PublicKeyParameters)key).getY().modPow(z2, params.getP());
            BigInteger u = z1.multiply(z2).mod(params.getP()).mod(params.getQ());
            return u.equals(r);
        }
    }

    GOST3410KeyParameters key;
    SecureRandom random;
}
