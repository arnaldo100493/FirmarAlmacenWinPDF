// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceCCA2PublicKey.java

package co.org.bouncy.pqc.asn1;

import co.org.bouncy.asn1.*;
import co.org.bouncy.pqc.math.linearalgebra.GF2Matrix;
import java.math.BigInteger;

public class McElieceCCA2PublicKey extends ASN1Object
{

    public McElieceCCA2PublicKey(ASN1ObjectIdentifier oid, int n, int t, GF2Matrix g)
    {
        this.oid = oid;
        this.n = n;
        this.t = t;
        matrixG = g.getEncoded();
    }

    private McElieceCCA2PublicKey(ASN1Sequence seq)
    {
        oid = (ASN1ObjectIdentifier)seq.getObjectAt(0);
        BigInteger bigN = ((ASN1Integer)seq.getObjectAt(1)).getValue();
        n = bigN.intValue();
        BigInteger bigT = ((ASN1Integer)seq.getObjectAt(2)).getValue();
        t = bigT.intValue();
        matrixG = ((ASN1OctetString)seq.getObjectAt(3)).getOctets();
    }

    public ASN1ObjectIdentifier getOID()
    {
        return oid;
    }

    public int getN()
    {
        return n;
    }

    public int getT()
    {
        return t;
    }

    public GF2Matrix getG()
    {
        return new GF2Matrix(matrixG);
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(oid);
        v.add(new ASN1Integer(n));
        v.add(new ASN1Integer(t));
        v.add(new DEROctetString(matrixG));
        return new DERSequence(v);
    }

    public static McElieceCCA2PublicKey getInstance(Object o)
    {
        if(o instanceof McElieceCCA2PublicKey)
            return (McElieceCCA2PublicKey)o;
        if(o != null)
            return new McElieceCCA2PublicKey(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    private ASN1ObjectIdentifier oid;
    private int n;
    private int t;
    private byte matrixG[];
}
