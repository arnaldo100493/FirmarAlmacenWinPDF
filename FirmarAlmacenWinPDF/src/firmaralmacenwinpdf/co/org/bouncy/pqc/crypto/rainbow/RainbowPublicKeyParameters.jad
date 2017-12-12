// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RainbowPublicKeyParameters.java

package co.org.bouncy.pqc.crypto.rainbow;


// Referenced classes of package co.org.bouncy.pqc.crypto.rainbow:
//            RainbowKeyParameters

public class RainbowPublicKeyParameters extends RainbowKeyParameters
{

    public RainbowPublicKeyParameters(int docLength, short coeffQuadratic[][], short coeffSingular[][], short coeffScalar[])
    {
        super(false, docLength);
        coeffquadratic = coeffQuadratic;
        coeffsingular = coeffSingular;
        coeffscalar = coeffScalar;
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
}
