// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RSAPrivateCrtKeyParameters.java

package co.org.bouncy.crypto.params;

import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.crypto.params:
//            RSAKeyParameters

public class RSAPrivateCrtKeyParameters extends RSAKeyParameters
{

    public RSAPrivateCrtKeyParameters(BigInteger modulus, BigInteger publicExponent, BigInteger privateExponent, BigInteger p, BigInteger q, BigInteger dP, BigInteger dQ, 
            BigInteger qInv)
    {
        super(true, modulus, privateExponent);
        e = publicExponent;
        this.p = p;
        this.q = q;
        this.dP = dP;
        this.dQ = dQ;
        this.qInv = qInv;
    }

    public BigInteger getPublicExponent()
    {
        return e;
    }

    public BigInteger getP()
    {
        return p;
    }

    public BigInteger getQ()
    {
        return q;
    }

    public BigInteger getDP()
    {
        return dP;
    }

    public BigInteger getDQ()
    {
        return dQ;
    }

    public BigInteger getQInv()
    {
        return qInv;
    }

    private BigInteger e;
    private BigInteger p;
    private BigInteger q;
    private BigInteger dP;
    private BigInteger dQ;
    private BigInteger qInv;
}
