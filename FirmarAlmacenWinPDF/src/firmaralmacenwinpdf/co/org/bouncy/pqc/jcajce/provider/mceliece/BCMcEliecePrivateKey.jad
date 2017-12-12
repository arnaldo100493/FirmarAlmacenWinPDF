// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCMcEliecePrivateKey.java

package co.org.bouncy.pqc.jcajce.provider.mceliece;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.pqc.asn1.McEliecePrivateKey;
import co.org.bouncy.pqc.crypto.mceliece.McElieceParameters;
import co.org.bouncy.pqc.crypto.mceliece.McEliecePrivateKeyParameters;
import co.org.bouncy.pqc.jcajce.spec.McEliecePrivateKeySpec;
import co.org.bouncy.pqc.math.linearalgebra.*;
import java.io.IOException;
import java.security.PrivateKey;

public class BCMcEliecePrivateKey
    implements CipherParameters, PrivateKey
{

    public BCMcEliecePrivateKey(String oid, int n, int k, GF2mField field, PolynomialGF2mSmallM goppaPoly, GF2Matrix sInv, Permutation p1, 
            Permutation p2, GF2Matrix h, PolynomialGF2mSmallM qInv[])
    {
        this.oid = oid;
        this.n = n;
        this.k = k;
        this.field = field;
        this.goppaPoly = goppaPoly;
        this.sInv = sInv;
        this.p1 = p1;
        this.p2 = p2;
        this.h = h;
        this.qInv = qInv;
    }

    public BCMcEliecePrivateKey(McEliecePrivateKeySpec keySpec)
    {
        this(keySpec.getOIDString(), keySpec.getN(), keySpec.getK(), keySpec.getField(), keySpec.getGoppaPoly(), keySpec.getSInv(), keySpec.getP1(), keySpec.getP2(), keySpec.getH(), keySpec.getQInv());
    }

    public BCMcEliecePrivateKey(McEliecePrivateKeyParameters params)
    {
        this(params.getOIDString(), params.getN(), params.getK(), params.getField(), params.getGoppaPoly(), params.getSInv(), params.getP1(), params.getP2(), params.getH(), params.getQInv());
        mcElieceParams = params.getParameters();
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

    public GF2mField getField()
    {
        return field;
    }

    public PolynomialGF2mSmallM getGoppaPoly()
    {
        return goppaPoly;
    }

    public GF2Matrix getSInv()
    {
        return sInv;
    }

    public Permutation getP1()
    {
        return p1;
    }

    public Permutation getP2()
    {
        return p2;
    }

    public GF2Matrix getH()
    {
        return h;
    }

    public PolynomialGF2mSmallM[] getQInv()
    {
        return qInv;
    }

    public String getOIDString()
    {
        return oid;
    }

    public String toString()
    {
        String result = (new StringBuilder()).append(" length of the code          : ").append(n).append("\n").toString();
        result = (new StringBuilder()).append(result).append(" dimension of the code       : ").append(k).append("\n").toString();
        result = (new StringBuilder()).append(result).append(" irreducible Goppa polynomial: ").append(goppaPoly).append("\n").toString();
        result = (new StringBuilder()).append(result).append(" (k x k)-matrix S^-1         : ").append(sInv).append("\n").toString();
        result = (new StringBuilder()).append(result).append(" permutation P1              : ").append(p1).append("\n").toString();
        result = (new StringBuilder()).append(result).append(" permutation P2              : ").append(p2).toString();
        return result;
    }

    public boolean equals(Object other)
    {
        if(!(other instanceof BCMcEliecePrivateKey))
        {
            return false;
        } else
        {
            BCMcEliecePrivateKey otherKey = (BCMcEliecePrivateKey)other;
            return n == otherKey.n && k == otherKey.k && field.equals(otherKey.field) && goppaPoly.equals(otherKey.goppaPoly) && sInv.equals(otherKey.sInv) && p1.equals(otherKey.p1) && p2.equals(otherKey.p2) && h.equals(otherKey.h);
        }
    }

    public int hashCode()
    {
        return k + n + field.hashCode() + goppaPoly.hashCode() + sInv.hashCode() + p1.hashCode() + p2.hashCode() + h.hashCode();
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
        McEliecePrivateKey privateKey = new McEliecePrivateKey(new ASN1ObjectIdentifier(oid), n, k, field, goppaPoly, sInv, p1, p2, h, qInv);
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

    public McElieceParameters getMcElieceParameters()
    {
        return mcElieceParams;
    }

    private static final long serialVersionUID = 1L;
    private String oid;
    private int n;
    private int k;
    private GF2mField field;
    private PolynomialGF2mSmallM goppaPoly;
    private GF2Matrix sInv;
    private Permutation p1;
    private Permutation p2;
    private GF2Matrix h;
    private PolynomialGF2mSmallM qInv[];
    private McElieceParameters mcElieceParams;
}
