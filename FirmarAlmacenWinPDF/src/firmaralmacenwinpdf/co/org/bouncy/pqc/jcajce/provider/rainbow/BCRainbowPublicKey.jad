// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCRainbowPublicKey.java

package co.org.bouncy.pqc.jcajce.provider.rainbow;

import co.org.bouncy.asn1.DERNull;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.pqc.asn1.PQCObjectIdentifiers;
import co.org.bouncy.pqc.asn1.RainbowPublicKey;
import co.org.bouncy.pqc.crypto.rainbow.RainbowParameters;
import co.org.bouncy.pqc.crypto.rainbow.RainbowPublicKeyParameters;
import co.org.bouncy.pqc.crypto.rainbow.util.RainbowUtil;
import co.org.bouncy.pqc.jcajce.provider.util.KeyUtil;
import co.org.bouncy.pqc.jcajce.spec.RainbowPublicKeySpec;
import co.org.bouncy.util.Arrays;
import java.security.PublicKey;

public class BCRainbowPublicKey
    implements PublicKey
{

    public BCRainbowPublicKey(int docLength, short coeffQuadratic[][], short coeffSingular[][], short coeffScalar[])
    {
        this.docLength = docLength;
        coeffquadratic = coeffQuadratic;
        coeffsingular = coeffSingular;
        coeffscalar = coeffScalar;
    }

    public BCRainbowPublicKey(RainbowPublicKeySpec keySpec)
    {
        this(keySpec.getDocLength(), keySpec.getCoeffQuadratic(), keySpec.getCoeffSingular(), keySpec.getCoeffScalar());
    }

    public BCRainbowPublicKey(RainbowPublicKeyParameters params)
    {
        this(params.getDocLength(), params.getCoeffQuadratic(), params.getCoeffSingular(), params.getCoeffScalar());
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
        short copy[][] = new short[coeffsingular.length][];
        for(int i = 0; i != coeffsingular.length; i++)
            copy[i] = Arrays.clone(coeffsingular[i]);

        return copy;
    }

    public short[] getCoeffScalar()
    {
        return Arrays.clone(coeffscalar);
    }

    public boolean equals(Object other)
    {
        if(other == null || !(other instanceof BCRainbowPublicKey))
        {
            return false;
        } else
        {
            BCRainbowPublicKey otherKey = (BCRainbowPublicKey)other;
            return docLength == otherKey.getDocLength() && RainbowUtil.equals(coeffquadratic, otherKey.getCoeffQuadratic()) && RainbowUtil.equals(coeffsingular, otherKey.getCoeffSingular()) && RainbowUtil.equals(coeffscalar, otherKey.getCoeffScalar());
        }
    }

    public int hashCode()
    {
        int hash = docLength;
        hash = hash * 37 + Arrays.hashCode(coeffquadratic);
        hash = hash * 37 + Arrays.hashCode(coeffsingular);
        hash = hash * 37 + Arrays.hashCode(coeffscalar);
        return hash;
    }

    public final String getAlgorithm()
    {
        return "Rainbow";
    }

    public String getFormat()
    {
        return "X.509";
    }

    public byte[] getEncoded()
    {
        RainbowPublicKey key = new RainbowPublicKey(docLength, coeffquadratic, coeffsingular, coeffscalar);
        AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(PQCObjectIdentifiers.rainbow, DERNull.INSTANCE);
        return KeyUtil.getEncodedSubjectPublicKeyInfo(algorithmIdentifier, key);
    }

    private static final long serialVersionUID = 1L;
    private short coeffquadratic[][];
    private short coeffsingular[][];
    private short coeffscalar[];
    private int docLength;
    private RainbowParameters rainbowParams;
}
