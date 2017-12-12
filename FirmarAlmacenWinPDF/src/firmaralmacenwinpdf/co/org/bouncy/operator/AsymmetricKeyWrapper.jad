// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AsymmetricKeyWrapper.java

package co.org.bouncy.operator;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

// Referenced classes of package co.org.bouncy.operator:
//            KeyWrapper

public abstract class AsymmetricKeyWrapper
    implements KeyWrapper
{

    protected AsymmetricKeyWrapper(AlgorithmIdentifier algorithmId)
    {
        this.algorithmId = algorithmId;
    }

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return algorithmId;
    }

    private AlgorithmIdentifier algorithmId;
}
