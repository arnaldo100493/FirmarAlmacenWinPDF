// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RainbowPublicKeySpec.java

package co.org.bouncy.pqc.jcajce.spec;

import java.security.spec.KeySpec;

public class RainbowPublicKeySpec
    implements KeySpec
{

    public RainbowPublicKeySpec(int docLength, short coeffquadratic[][], short coeffSingular[][], short coeffScalar[])
    {
        this.docLength = docLength;
        this.coeffquadratic = coeffquadratic;
        coeffsingular = coeffSingular;
        coeffscalar = coeffScalar;
    }

    public int getDocLength()
    {
        return docLength;
    }

    public short[][] getCoeffQuadratic()
    {
        return coeffquadratic;
    }

    public short[][] getCoeffSingular()
    {
        return coeffsingular;
    }

    public short[] getCoeffScalar()
    {
        return coeffscalar;
    }

    private short coeffquadratic[][];
    private short coeffsingular[][];
    private short coeffscalar[];
    private int docLength;
}
