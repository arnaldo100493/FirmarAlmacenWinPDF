// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECGOST3410ParamSetParameters.java

package co.org.bouncy.asn1.cryptopro;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;
import java.util.Enumeration;

public class ECGOST3410ParamSetParameters extends ASN1Object
{

    public static ECGOST3410ParamSetParameters getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static ECGOST3410ParamSetParameters getInstance(Object obj)
    {
        if(obj == null || (obj instanceof ECGOST3410ParamSetParameters))
            return (ECGOST3410ParamSetParameters)obj;
        if(obj instanceof ASN1Sequence)
            return new ECGOST3410ParamSetParameters((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid GOST3410Parameter: ").append(obj.getClass().getName()).toString());
    }

    public ECGOST3410ParamSetParameters(BigInteger a, BigInteger b, BigInteger p, BigInteger q, int x, BigInteger y)
    {
        this.a = new ASN1Integer(a);
        this.b = new ASN1Integer(b);
        this.p = new ASN1Integer(p);
        this.q = new ASN1Integer(q);
        this.x = new ASN1Integer(x);
        this.y = new ASN1Integer(y);
    }

    public ECGOST3410ParamSetParameters(ASN1Sequence seq)
    {
        Enumeration e = seq.getObjects();
        a = (ASN1Integer)e.nextElement();
        b = (ASN1Integer)e.nextElement();
        p = (ASN1Integer)e.nextElement();
        q = (ASN1Integer)e.nextElement();
        x = (ASN1Integer)e.nextElement();
        y = (ASN1Integer)e.nextElement();
    }

    public BigInteger getP()
    {
        return p.getPositiveValue();
    }

    public BigInteger getQ()
    {
        return q.getPositiveValue();
    }

    public BigInteger getA()
    {
        return a.getPositiveValue();
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(a);
        v.add(b);
        v.add(p);
        v.add(q);
        v.add(x);
        v.add(y);
        return new DERSequence(v);
    }

    ASN1Integer p;
    ASN1Integer q;
    ASN1Integer a;
    ASN1Integer b;
    ASN1Integer x;
    ASN1Integer y;
}
