// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECParameterSpec.java

package co.org.bouncy.jce.spec;

import co.org.bouncy.math.ec.ECCurve;
import co.org.bouncy.math.ec.ECPoint;
import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

public class ECParameterSpec
    implements AlgorithmParameterSpec
{

    public ECParameterSpec(ECCurve curve, ECPoint G, BigInteger n)
    {
        this.curve = curve;
        this.G = G;
        this.n = n;
        h = BigInteger.valueOf(1L);
        seed = null;
    }

    public ECParameterSpec(ECCurve curve, ECPoint G, BigInteger n, BigInteger h)
    {
        this.curve = curve;
        this.G = G;
        this.n = n;
        this.h = h;
        seed = null;
    }

    public ECParameterSpec(ECCurve curve, ECPoint G, BigInteger n, BigInteger h, byte seed[])
    {
        this.curve = curve;
        this.G = G;
        this.n = n;
        this.h = h;
        this.seed = seed;
    }

    public ECCurve getCurve()
    {
        return curve;
    }

    public ECPoint getG()
    {
        return G;
    }

    public BigInteger getN()
    {
        return n;
    }

    public BigInteger getH()
    {
        return h;
    }

    public byte[] getSeed()
    {
        return seed;
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof ECParameterSpec))
        {
            return false;
        } else
        {
            ECParameterSpec other = (ECParameterSpec)o;
            return getCurve().equals(other.getCurve()) && getG().equals(other.getG());
        }
    }

    public int hashCode()
    {
        return getCurve().hashCode() ^ getG().hashCode();
    }

    private ECCurve curve;
    private byte seed[];
    private ECPoint G;
    private BigInteger n;
    private BigInteger h;
}
