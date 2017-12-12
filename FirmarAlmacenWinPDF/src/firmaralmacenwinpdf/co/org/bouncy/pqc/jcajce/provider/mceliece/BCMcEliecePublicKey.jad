// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCMcEliecePublicKey.java

package co.org.bouncy.pqc.jcajce.provider.mceliece;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.pqc.asn1.McEliecePublicKey;
import co.org.bouncy.pqc.crypto.mceliece.McElieceParameters;
import co.org.bouncy.pqc.crypto.mceliece.McEliecePublicKeyParameters;
import co.org.bouncy.pqc.jcajce.spec.McEliecePublicKeySpec;
import co.org.bouncy.pqc.math.linearalgebra.GF2Matrix;
import java.io.IOException;
import java.security.PublicKey;

public class BCMcEliecePublicKey
    implements CipherParameters, PublicKey
{

    public BCMcEliecePublicKey(String oid, int n, int t, GF2Matrix g)
    {
        this.oid = oid;
        this.n = n;
        this.t = t;
        this.g = g;
    }

    public BCMcEliecePublicKey(McEliecePublicKeySpec keySpec)
    {
        this(keySpec.getOIDString(), keySpec.getN(), keySpec.getT(), keySpec.getG());
    }

    public BCMcEliecePublicKey(McEliecePublicKeyParameters params)
    {
        this(params.getOIDString(), params.getN(), params.getT(), params.getG());
        McElieceParams = params.getParameters();
    }

    public String getAlgorithm()
    {
        return "McEliece";
    }

    public int getN()
    {
        return n;
    }

    public int getK()
    {
        return g.getNumRows();
    }

    public int getT()
    {
        return t;
    }

    public GF2Matrix getG()
    {
        return g;
    }

    public String toString()
    {
        String result = "McEliecePublicKey:\n";
        result = (new StringBuilder()).append(result).append(" length of the code         : ").append(n).append("\n").toString();
        result = (new StringBuilder()).append(result).append(" error correction capability: ").append(t).append("\n").toString();
        result = (new StringBuilder()).append(result).append(" generator matrix           : ").append(g.toString()).toString();
        return result;
    }

    public boolean equals(Object other)
    {
        if(!(other instanceof BCMcEliecePublicKey))
        {
            return false;
        } else
        {
            BCMcEliecePublicKey otherKey = (BCMcEliecePublicKey)other;
            return n == otherKey.n && t == otherKey.t && g.equals(otherKey.g);
        }
    }

    public int hashCode()
    {
        return n + t + g.hashCode();
    }

    public String getOIDString()
    {
        return oid;
    }

    protected ASN1ObjectIdentifier getOID()
    {
        return new ASN1ObjectIdentifier("1.3.6.1.4.1.8301.3.1.3.4.1");
    }

    protected ASN1Primitive getAlgParams()
    {
        return null;
    }

    public byte[] getEncoded()
    {
        McEliecePublicKey key = new McEliecePublicKey(new ASN1ObjectIdentifier(oid), n, t, g);
        AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(getOID(), DERNull.INSTANCE);
        try
        {
            SubjectPublicKeyInfo subjectPublicKeyInfo = new SubjectPublicKeyInfo(algorithmIdentifier, key);
            return subjectPublicKeyInfo.getEncoded();
        }
        catch(IOException e)
        {
            return null;
        }
    }

    public String getFormat()
    {
        return null;
    }

    public McElieceParameters getMcElieceParameters()
    {
        return McElieceParams;
    }

    private static final long serialVersionUID = 1L;
    private String oid;
    private int n;
    private int t;
    private GF2Matrix g;
    private McElieceParameters McElieceParams;
}
