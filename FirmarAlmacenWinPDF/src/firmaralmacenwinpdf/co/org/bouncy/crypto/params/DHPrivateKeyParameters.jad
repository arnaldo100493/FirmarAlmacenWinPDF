// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DHPrivateKeyParameters.java

package co.org.bouncy.crypto.params;

import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.crypto.params:
//            DHKeyParameters, DHParameters

public class DHPrivateKeyParameters extends DHKeyParameters
{

    public DHPrivateKeyParameters(BigInteger x, DHParameters params)
    {
        super(true, params);
        this.x = x;
    }

    public BigInteger getX()
    {
        return x;
    }

    public int hashCode()
    {
        return x.hashCode() ^ super.hashCode();
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof DHPrivateKeyParameters))
        {
            return false;
        } else
        {
            DHPrivateKeyParameters other = (DHPrivateKeyParameters)obj;
            return other.getX().equals(x) && super.equals(obj);
        }
    }

    private BigInteger x;
}
