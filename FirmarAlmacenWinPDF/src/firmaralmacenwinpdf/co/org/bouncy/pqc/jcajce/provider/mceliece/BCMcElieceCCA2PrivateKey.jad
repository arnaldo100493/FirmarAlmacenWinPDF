// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCMcElieceCCA2PrivateKey.java

package co.org.bouncy.pqc.jcajce.provider.mceliece;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.pqc.asn1.McElieceCCA2PrivateKey;
import co.org.bouncy.pqc.crypto.mceliece.McElieceCCA2Parameters;
import co.org.bouncy.pqc.crypto.mceliece.McElieceCCA2PrivateKeyParameters;
import co.org.bouncy.pqc.jcajce.spec.McElieceCCA2PrivateKeySpec;
import co.org.bouncy.pqc.math.linearalgebra.*;
import java.io.IOException;
import java.security.PrivateKey;

public class BCMcElieceCCA2PrivateKey
    implements CipherParameters, PrivateKey
{

    public BCMcElieceCCA2PrivateKey(String oid, int n, int k, GF2mField field, PolynomialGF2mSmallM gp, Permutation p, GF2Matrix h, 
            PolynomialGF2mSmallM qInv[])
    {
        this.oid = oid;
        this.n = n;
        this.k = k;
        this.field = field;
        goppaPoly = gp;
        this.p = p;
        this.h = h;
        this.qInv = qInv;
    }

    public BCMcElieceCCA2PrivateKey(McElieceCCA2PrivateKeySpec keySpec)
    {
        this(keySpec.getOIDString(), keySpec.getN(), keySpec.getK(), keySpec.getField(), keySpec.getGoppaPoly(), keySpec.getP(), keySpec.getH(), keySpec.getQInv());
    }

    public BCMcElieceCCA2PrivateKey(McElieceCCA2PrivateKeyParameters params)
    {
        this(params.getOIDString(), params.getN(), params.getK(), params.getField(), params.getGoppaPoly(), params.getP(), params.getH(), params.getQInv());
        mcElieceCCA2Params = params.getParameters();
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
        return k;
    }

    public int getT()
    {
        return goppaPoly.getDegree();
    }

    public GF2mField getField()
    {
        return field;
    }

    public PolynomialGF2mSmallM getGoppaPoly()
    {
        return goppaPoly;
    }

    public Permutation getP()
    {
        return p;
    }

    public GF2Matrix getH()
    {
        return h;
    }

    public PolynomialGF2mSmallM[] getQInv()
    {
        return qInv;
    }

    public String toString()
    {
        String result = "";
        result = (new StringBuilder()).append(result).append(" extension degree of the field      : ").append(n).append("\n").toString();
        result = (new StringBuilder()).append(result).append(" dimension of the code              : ").append(k).append("\n").toString();
        result = (new StringBuilder()).append(result).append(" irreducible Goppa polynomial       : ").append(goppaPoly).append("\n").toString();
        return result;
    }

    public boolean equals(Object other)
    {
        if(other == null || !(other instanceof BCMcElieceCCA2PrivateKey))
        {
            return false;
        } else
        {
            BCMcElieceCCA2PrivateKey otherKey = (BCMcElieceCCA2PrivateKey)other;
            return n == otherKey.n && k == otherKey.k && field.equals(otherKey.field) && goppaPoly.equals(otherKey.goppaPoly) && p.equals(otherKey.p) && h.equals(otherKey.h);
        }
    }

    public int hashCode()
    {
        return k + n + field.hashCode() + goppaPoly.hashCode() + p.hashCode() + h.hashCode();
    }

    public String getOIDString()
    {
        return oid;
    }

    protected ASN1ObjectIdentifier getOID()
    {
        return new ASN1ObjectIdentifier("1.3.6.1.4.1.8301.3.1.3.4.2");
    }

    protected ASN1Primitive getAlgParams()
    {
        return null;
    }

    public byte[] getEncoded()
    {
        McElieceCCA2PrivateKey privateKey = new McElieceCCA2PrivateKey(new ASN1ObjectIdentifier(oid), n, k, field, goppaPoly, p, h, qInv);
        PrivateKeyInfo pki;
        try
        {
            AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(getOID(), DERNull.INSTANCE);
            pki = new PrivateKeyInfo(algorithmIdentifier, privateKey);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return null;
        }
        try
        {
            byte encoded[] = pki.getEncoded();
            return encoded;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public String getFormat()
    {
        return null;
    }

    public McElieceCCA2Parameters getMcElieceCCA2Parameters()
    {
        return mcElieceCCA2Params;
    }

    private static final long serialVersionUID = 1L;
    private String oid;
    private int n;
    private int k;
    private GF2mField field;
    private PolynomialGF2mSmallM goppaPoly;
    private Permutation p;
    private GF2Matrix h;
    private PolynomialGF2mSmallM qInv[];
    private McElieceCCA2Parameters mcElieceCCA2Params;
}
