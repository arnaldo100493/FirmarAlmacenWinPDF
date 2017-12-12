// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertificatePair.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            Certificate

public class CertificatePair extends ASN1Object
{

    public static CertificatePair getInstance(Object obj)
    {
        if(obj == null || (obj instanceof CertificatePair))
            return (CertificatePair)obj;
        if(obj instanceof ASN1Sequence)
            return new CertificatePair((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    private CertificatePair(ASN1Sequence seq)
    {
        if(seq.size() != 1 && seq.size() != 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        for(Enumeration e = seq.getObjects(); e.hasMoreElements();)
        {
            ASN1TaggedObject o = ASN1TaggedObject.getInstance(e.nextElement());
            if(o.getTagNo() == 0)
                forward = Certificate.getInstance(o, true);
            else
            if(o.getTagNo() == 1)
                reverse = Certificate.getInstance(o, true);
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Bad tag number: ").append(o.getTagNo()).toString());
        }

    }

    public CertificatePair(Certificate forward, Certificate reverse)
    {
        this.forward = forward;
        this.reverse = reverse;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector vec = new ASN1EncodableVector();
        if(forward != null)
            vec.add(new DERTaggedObject(0, forward));
        if(reverse != null)
            vec.add(new DERTaggedObject(1, reverse));
        return new DERSequence(vec);
    }

    public Certificate getForward()
    {
        return forward;
    }

    public Certificate getReverse()
    {
        return reverse;
    }

    private Certificate forward;
    private Certificate reverse;
}
