// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CrlID.java

package co.org.bouncy.asn1.ocsp;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

public class CrlID extends ASN1Object
{

    private CrlID(ASN1Sequence seq)
    {
        Enumeration e = seq.getObjects();
        do
        {
            if(!e.hasMoreElements())
                break;
            ASN1TaggedObject o = (ASN1TaggedObject)e.nextElement();
            switch(o.getTagNo())
            {
            case 0: // '\0'
                crlUrl = DERIA5String.getInstance(o, true);
                break;

            case 1: // '\001'
                crlNum = ASN1Integer.getInstance(o, true);
                break;

            case 2: // '\002'
                crlTime = DERGeneralizedTime.getInstance(o, true);
                break;

            default:
                throw new IllegalArgumentException((new StringBuilder()).append("unknown tag number: ").append(o.getTagNo()).toString());
            }
        } while(true);
    }

    public static CrlID getInstance(Object obj)
    {
        if(obj instanceof CrlID)
            return (CrlID)obj;
        if(obj != null)
            return new CrlID(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public DERIA5String getCrlUrl()
    {
        return crlUrl;
    }

    public ASN1Integer getCrlNum()
    {
        return crlNum;
    }

    public ASN1GeneralizedTime getCrlTime()
    {
        return crlTime;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(crlUrl != null)
            v.add(new DERTaggedObject(true, 0, crlUrl));
        if(crlNum != null)
            v.add(new DERTaggedObject(true, 1, crlNum));
        if(crlTime != null)
            v.add(new DERTaggedObject(true, 2, crlTime));
        return new DERSequence(v);
    }

    private DERIA5String crlUrl;
    private ASN1Integer crlNum;
    private ASN1GeneralizedTime crlTime;
}
