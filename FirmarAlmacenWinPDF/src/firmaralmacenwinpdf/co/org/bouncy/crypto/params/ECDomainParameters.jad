// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECDomainParameters.java

package co.org.bouncy.crypto.params;

import co.org.bouncy.math.ec.*;
import co.org.bouncy.util.Arrays;
import java.math.BigInteger;

public class ECDomainParameters
    implements ECConstants
{

    public ECDomainParameters(ECCurve curve, ECPoint G, BigInteger n)
    {
        this(curve, G, n, ONE, null);
    }

    public ECDomainParameters(ECCurve curve, ECPoint G, BigInteger n, BigInteger h)
    {
        this(curve, G, n, h, null);
    }

    public ECDomainParameters(ECCurve curve, ECPoint G, BigInteger n, BigInteger h, byte seed[])
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
        return Arrays.clone(seed);
    }

    private ECCurve curve;
    private byte seed[];
    private ECPoint G;
    private BigInteger n;
    private BigInteger h;
}
