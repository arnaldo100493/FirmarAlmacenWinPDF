// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OptionalValidity.java

package co.org.bouncy.asn1.crmf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.Time;
import java.util.Enumeration;

public class OptionalValidity extends ASN1Object
{

    private OptionalValidity(ASN1Sequence seq)
    {
        for(Enumeration en = seq.getObjects(); en.hasMoreElements();)
        {
            ASN1TaggedObject tObj = (ASN1TaggedObject)en.nextElement();
            if(tObj.getTagNo() == 0)
                notBefore = Time.getInstance(tObj, true);
            else
                notAfter = Time.getInstance(tObj, true);
        }

    }

    public static OptionalValidity getInstance(Object o)
    {
        if(o instanceof OptionalValidity)
            return (OptionalValidity)o;
        if(o != null)
            return new OptionalValidity(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public OptionalValidity(Time notBefore, Time notAfter)
    {
        if(notBefore == null && notAfter == null)
        {
            throw new IllegalArgumentException("at least one of notBefore/notAfter must not be null.");
        } else
        {
            this.notBefore = notBefore;
            this.notAfter = notAfter;
            return;
        }
    }

    public Time getNotBefore()
    {
        return notBefore;
    }

    public Time getNotAfter()
    {
        return notAfter;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(notBefore != null)
            v.add(new DERTaggedObject(true, 0, notBefore));
        if(notAfter != null)
            v.add(new DERTaggedObject(true, 1, notAfter));
        return new DERSequence(v);
    }

    private Time notBefore;
    private Time notAfter;
}
