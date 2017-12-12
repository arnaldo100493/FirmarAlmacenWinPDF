// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GenericKey.java

package co.org.bouncy.operator;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

public class GenericKey
{

    /**
     * @deprecated Method GenericKey is deprecated
     */

    public GenericKey(Object representation)
    {
        algorithmIdentifier = null;
        this.representation = representation;
    }

    public GenericKey(AlgorithmIdentifier algorithmIdentifier, byte representation[])
    {
        this.algorithmIdentifier = algorithmIdentifier;
        this.representation = representation;
    }

    protected GenericKey(AlgorithmIdentifier algorithmIdentifier, Object representation)
    {
        this.algorithmIdentifier = algorithmIdentifier;
        this.representation = representation;
    }

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return algorithmIdentifier;
    }

    public Object getRepresentation()
    {
        return representation;
    }

    private AlgorithmIdentifier algorithmIdentifier;
    private Object representation;
}
