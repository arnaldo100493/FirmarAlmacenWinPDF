// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSASigner.java

package co.org.bouncy.crypto.signers;

import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.DSA;
import co.org.bouncy.crypto.params.*;
import java.math.BigInteger;
import java.security.SecureRandom;

public class DSASigner
    implements DSA
{

    public DSASigner()
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
                key = (DSAPrivateKeyParameters)rParam.getParameters();
            } else
            {
                random = new SecureRandom();
                key = (DSAPrivateKeyParameters)param;
            }
        } else
        {
            key = (DSAPublicKeyParameters)param;
        }
    }

    public BigInteger[] generateSignature(byte message[])
    {
        DSAParameters params = key.getParameters();
        BigInteger m = calculateE(params.getQ(), message);
        int qBitLength = params.getQ().bitLength();
        BigInteger k;
        do
            k = new BigInteger(qBitLength, random);
        while(k.compareTo(params.getQ()) >= 0);
        BigInteger r = params.getG().modPow(k, params.getP()).mod(params.getQ());
        k = k.modInverse(params.getQ()).multiply(m.add(((DSAPrivateKeyParameters)key).getX().multiply(r)));
        BigInteger s = k.mod(params.getQ());
        BigInteger res[] = new BigInteger[2];
        res[0] = r;
        res[1] = s;
        return res;
    }

    public boolean verifySignature(byte message[], BigInteger r, BigInteger s)
    {
        DSAParameters params = key.getParameters();
        BigInteger m = calculateE(params.getQ(), message);
        BigInteger zero = BigInteger.valueOf(0L);
        if(zero.compareTo(r) >= 0 || params.getQ().compareTo(r) <= 0)
            return false;
        if(zero.compareTo(s) >= 0 || params.getQ().compareTo(s) <= 0)
        {
            return false;
        } else
        {
            BigInteger w = s.modInverse(params.getQ());
            BigInteger u1 = m.multiply(w).mod(params.getQ());
            BigInteger u2 = r.multiply(w).mod(params.getQ());
            u1 = params.getG().modPow(u1, params.getP());
            u2 = ((DSAPublicKeyParameters)key).getY().modPow(u2, params.getP());
            BigInteger v = u1.multiply(u2).mod(params.getP()).mod(params.getQ());
            return v.equals(r);
        }
    }

    private BigInteger calculateE(BigInteger n, byte message[])
    {
        if(n.bitLength() >= message.length * 8)
        {
            return new BigInteger(1, message);
        } else
        {
            byte trunc[] = new byte[n.bitLength() / 8];
            System.arraycopy(message, 0, trunc, 0, trunc.length);
            return new BigInteger(1, trunc);
        }
    }

    DSAKeyParameters key;
    SecureRandom random;
}
