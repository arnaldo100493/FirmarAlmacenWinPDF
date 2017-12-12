// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NaccacheSternKeyParameters.java

package co.org.bouncy.crypto.params;

import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.crypto.params:
//            AsymmetricKeyParameter

public class NaccacheSternKeyParameters extends AsymmetricKeyParameter
{

    public NaccacheSternKeyParameters(boolean privateKey, BigInteger g, BigInteger n, int lowerSigmaBound)
    {
        super(privateKey);
        this.g = g;
        this.n = n;
        this.lowerSigmaBound = lowerSigmaBound;
    }

    public BigInteger getG()
    {
        return g;
    }

    public int getLowerSigmaBound()
    {
        return lowerSigmaBound;
    }

    public BigInteger getModulus()
    {
        return n;
    }

    private BigInteger g;
    private BigInteger n;
    int lowerSigmaBound;
}
