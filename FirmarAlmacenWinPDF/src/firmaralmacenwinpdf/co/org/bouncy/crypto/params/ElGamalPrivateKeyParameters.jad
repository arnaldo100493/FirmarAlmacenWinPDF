// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ElGamalPrivateKeyParameters.java

package co.org.bouncy.crypto.params;

import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.crypto.params:
//            ElGamalKeyParameters, ElGamalParameters

public class ElGamalPrivateKeyParameters extends ElGamalKeyParameters
{

    public ElGamalPrivateKeyParameters(BigInteger x, ElGamalParameters params)
    {
        super(true, params);
        this.x = x;
    }

    public BigInteger getX()
    {
        return x;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof ElGamalPrivateKeyParameters))
            return false;
        ElGamalPrivateKeyParameters pKey = (ElGamalPrivateKeyParameters)obj;
        if(!pKey.getX().equals(x))
            return false;
        else
            return super.equals(obj);
    }

    public int hashCode()
    {
        return getX().hashCode();
    }

    private BigInteger x;
}
