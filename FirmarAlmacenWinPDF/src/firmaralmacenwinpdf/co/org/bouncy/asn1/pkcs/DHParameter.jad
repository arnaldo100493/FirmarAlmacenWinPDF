// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DHParameter.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;
import java.util.Enumeration;

public class DHParameter extends ASN1Object
{

    public DHParameter(BigInteger p, BigInteger g, int l)
    {
        this.p = new ASN1Integer(p);
        this.g = new ASN1Integer(g);
        if(l != 0)
            this.l = new ASN1Integer(l);
        else
            this.l = null;
    }

    public static DHParameter getInstance(Object obj)
    {
        if(obj instanceof DHParameter)
            return (DHParameter)obj;
        if(obj != null)
            return new DHParameter(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private DHParameter(ASN1Sequence seq)
    {
        Enumeration e = seq.getObjects();
        p = ASN1Integer.getInstance(e.nextElement());
        g = ASN1Integer.getInstance(e.nextElement());
        if(e.hasMoreElements())
            l = (ASN1Integer)e.nextElement();
        else
            l = null;
    }

    public BigInteger getP()
    {
        return p.getPositiveValue();
    }

    public BigInteger getG()
    {
        return g.getPositiveValue();
    }

    public BigInteger getL()
    {
        if(l == null)
            return null;
        else
            return l.getPositiveValue();
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(p);
        v.add(g);
        if(getL() != null)
            v.add(l);
        return new DERSequence(v);
    }

    ASN1Integer p;
    ASN1Integer g;
    ASN1Integer l;
}
