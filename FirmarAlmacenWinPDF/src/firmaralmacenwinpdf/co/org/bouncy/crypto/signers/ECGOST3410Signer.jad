// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECGOST3410Signer.java

package co.org.bouncy.crypto.signers;

import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.DSA;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.math.ec.*;
import java.math.BigInteger;
import java.security.SecureRandom;

public class ECGOST3410Signer
    implements DSA
{

    public ECGOST3410Signer()
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
                key = (ECPrivateKeyParameters)rParam.getParameters();
            } else
            {
                random = new SecureRandom();
                key = (ECPrivateKeyParameters)param;
            }
        } else
        {
            key = (ECPublicKeyParameters)param;
        }
    }

    public BigInteger[] generateSignature(byte message[])
    {
        byte mRev[] = new byte[message.length];
        for(int i = 0; i != mRev.length; i++)
            mRev[i] = message[mRev.length - 1 - i];

        BigInteger e = new BigInteger(1, mRev);
        BigInteger n = key.getParameters().getN();
        BigInteger r = null;
        BigInteger s = null;
        do
        {
            BigInteger k = null;
            do
            {
                do
                    k = new BigInteger(n.bitLength(), random);
                while(k.equals(ECConstants.ZERO));
                ECPoint p = key.getParameters().getG().multiply(k);
                BigInteger x = p.getX().toBigInteger();
                r = x.mod(n);
            } while(r.equals(ECConstants.ZERO));
            BigInteger d = ((ECPrivateKeyParameters)key).getD();
            s = k.multiply(e).add(d.multiply(r)).mod(n);
        } while(s.equals(ECConstants.ZERO));
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

        BigInteger e = new BigInteger(1, mRev);
        BigInteger n = key.getParameters().getN();
        if(r.compareTo(ECConstants.ONE) < 0 || r.compareTo(n) >= 0)
            return false;
        if(s.compareTo(ECConstants.ONE) < 0 || s.compareTo(n) >= 0)
            return false;
        BigInteger v = e.modInverse(n);
        BigInteger z1 = s.multiply(v).mod(n);
        BigInteger z2 = n.subtract(r).multiply(v).mod(n);
        ECPoint G = key.getParameters().getG();
        ECPoint Q = ((ECPublicKeyParameters)key).getQ();
        ECPoint point = ECAlgorithms.sumOfTwoMultiplies(G, z1, Q, z2);
        if(point.isInfinity())
        {
            return false;
        } else
        {
            BigInteger R = point.getX().toBigInteger().mod(n);
            return R.equals(r);
        }
    }

    ECKeyParameters key;
    SecureRandom random;
}
