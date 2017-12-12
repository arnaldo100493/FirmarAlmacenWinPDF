// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSAParameter.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;
import java.util.Enumeration;

public class DSAParameter extends ASN1Object
{

    public static DSAParameter getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static DSAParameter getInstance(Object obj)
    {
        if(obj instanceof DSAParameter)
            return (DSAParameter)obj;
        if(obj != null)
            return new DSAParameter(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public DSAParameter(BigInteger p, BigInteger q, BigInteger g)
    {
        this.p = new ASN1Integer(p);
        this.q = new ASN1Integer(q);
        this.g = new ASN1Integer(g);
    }

    private DSAParameter(ASN1Sequence seq)
    {
        if(seq.size() != 3)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        } else
        {
            Enumeration e = seq.getObjects();
            p = ASN1Integer.getInstance(e.nextElement());
            q = ASN1Integer.getInstance(e.nextElement());
            g = ASN1Integer.getInstance(e.nextElement());
            return;
        }
    }

    public BigInteger getP()
    {
        return p.getPositiveValue();
    }

    public BigInteger getQ()
    {
        return q.getPositiveValue();
    }

    public BigInteger getG()
    {
        return g.getPositiveValue();
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(p);
        v.add(q);
        v.add(g);
        return new DERSequence(v);
    }

    ASN1Integer p;
    ASN1Integer q;
    ASN1Integer g;
}
