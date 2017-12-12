// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DHDomainParameters.java

package co.org.bouncy.asn1.x9;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.x9:
//            DHValidationParms

public class DHDomainParameters extends ASN1Object
{

    public static DHDomainParameters getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static DHDomainParameters getInstance(Object obj)
    {
        if(obj == null || (obj instanceof DHDomainParameters))
            return (DHDomainParameters)obj;
        if(obj instanceof ASN1Sequence)
            return new DHDomainParameters((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid DHDomainParameters: ").append(obj.getClass().getName()).toString());
    }

    public DHDomainParameters(ASN1Integer p, ASN1Integer g, ASN1Integer q, ASN1Integer j, DHValidationParms validationParms)
    {
        if(p == null)
            throw new IllegalArgumentException("'p' cannot be null");
        if(g == null)
            throw new IllegalArgumentException("'g' cannot be null");
        if(q == null)
        {
            throw new IllegalArgumentException("'q' cannot be null");
        } else
        {
            this.p = p;
            this.g = g;
            this.q = q;
            this.j = j;
            this.validationParms = validationParms;
            return;
        }
    }

    private DHDomainParameters(ASN1Sequence seq)
    {
        if(seq.size() < 3 || seq.size() > 5)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        Enumeration e = seq.getObjects();
        p = ASN1Integer.getInstance(e.nextElement());
        g = ASN1Integer.getInstance(e.nextElement());
        q = ASN1Integer.getInstance(e.nextElement());
        ASN1Encodable next = getNext(e);
        if(next != null && (next instanceof ASN1Integer))
        {
            j = ASN1Integer.getInstance(next);
            next = getNext(e);
        }
        if(next != null)
            validationParms = DHValidationParms.getInstance(next.toASN1Primitive());
    }

    private static ASN1Encodable getNext(Enumeration e)
    {
        return e.hasMoreElements() ? (ASN1Encodable)e.nextElement() : null;
    }

    public ASN1Integer getP()
    {
        return p;
    }

    public ASN1Integer getG()
    {
        return g;
    }

    public ASN1Integer getQ()
    {
        return q;
    }

    public ASN1Integer getJ()
    {
        return j;
    }

    public DHValidationParms getValidationParms()
    {
        return validationParms;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(p);
        v.add(g);
        v.add(q);
        if(j != null)
            v.add(j);
        if(validationParms != null)
            v.add(validationParms);
        return new DERSequence(v);
    }

    private ASN1Integer p;
    private ASN1Integer g;
    private ASN1Integer q;
    private ASN1Integer j;
    private DHValidationParms validationParms;
}
