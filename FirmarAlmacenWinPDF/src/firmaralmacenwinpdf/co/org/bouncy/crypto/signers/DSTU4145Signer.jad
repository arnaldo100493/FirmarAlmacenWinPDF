// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSTU4145Signer.java

package co.org.bouncy.crypto.signers;

import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.DSA;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.math.ec.*;
import co.org.bouncy.util.Arrays;
import java.math.BigInteger;
import java.security.SecureRandom;

public class DSTU4145Signer
    implements DSA
{

    public DSTU4145Signer()
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
                param = rParam.getParameters();
            } else
            {
                random = new SecureRandom();
            }
            key = (ECPrivateKeyParameters)param;
        } else
        {
            key = (ECPublicKeyParameters)param;
        }
    }

    public BigInteger[] generateSignature(byte message[])
    {
        ECFieldElement h = hash2FieldElement(key.getParameters().getCurve(), message);
        if(h.toBigInteger().signum() == 0)
            h = key.getParameters().getCurve().fromBigInteger(ONE);
        BigInteger r;
        BigInteger s;
        do
        {
            BigInteger e;
            do
            {
                ECFieldElement Fe;
                do
                {
                    e = generateRandomInteger(key.getParameters().getN(), random);
                    Fe = key.getParameters().getG().multiply(e).getX();
                } while(Fe.toBigInteger().signum() == 0);
                ECFieldElement y = h.multiply(Fe);
                r = fieldElement2Integer(key.getParameters().getN(), y);
            } while(r.signum() == 0);
            s = r.multiply(((ECPrivateKeyParameters)key).getD()).add(e).mod(key.getParameters().getN());
        } while(s.signum() == 0);
        return (new BigInteger[] {
            r, s
        });
    }

    public boolean verifySignature(byte message[], BigInteger r, BigInteger s)
    {
        if(r.signum() == 0 || s.signum() == 0)
            return false;
        if(r.compareTo(key.getParameters().getN()) >= 0 || s.compareTo(key.getParameters().getN()) >= 0)
            return false;
        ECFieldElement h = hash2FieldElement(key.getParameters().getCurve(), message);
        if(h.toBigInteger().signum() == 0)
            h = key.getParameters().getCurve().fromBigInteger(ONE);
        ECPoint R = ECAlgorithms.sumOfTwoMultiplies(key.getParameters().getG(), s, ((ECPublicKeyParameters)key).getQ(), r);
        if(R.isInfinity())
        {
            return false;
        } else
        {
            ECFieldElement y = h.multiply(R.getX());
            return fieldElement2Integer(key.getParameters().getN(), y).compareTo(r) == 0;
        }
    }

    private static BigInteger generateRandomInteger(BigInteger n, SecureRandom random)
    {
        return new BigInteger(n.bitLength() - 1, random);
    }

    private static void reverseBytes(byte bytes[])
    {
        for(int i = 0; i < bytes.length / 2; i++)
        {
            byte tmp = bytes[i];
            bytes[i] = bytes[bytes.length - 1 - i];
            bytes[bytes.length - 1 - i] = tmp;
        }

    }

    private static ECFieldElement hash2FieldElement(ECCurve curve, byte hash[])
    {
        byte data[] = Arrays.clone(hash);
        reverseBytes(data);
        BigInteger num;
        for(num = new BigInteger(1, data); num.bitLength() >= curve.getFieldSize(); num = num.clearBit(num.bitLength() - 1));
        return curve.fromBigInteger(num);
    }

    private static BigInteger fieldElement2Integer(BigInteger n, ECFieldElement fieldElement)
    {
        BigInteger num;
        for(num = fieldElement.toBigInteger(); num.bitLength() >= n.bitLength(); num = num.clearBit(num.bitLength() - 1));
        return num;
    }

    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private ECKeyParameters key;
    private SecureRandom random;

}
