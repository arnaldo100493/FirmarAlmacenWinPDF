// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OriginatorInfo.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;

public class OriginatorInfo extends ASN1Object
{

    public OriginatorInfo(ASN1Set certs, ASN1Set crls)
    {
        this.certs = certs;
        this.crls = crls;
    }

    private OriginatorInfo(ASN1Sequence seq)
    {
        switch(seq.size())
        {
        case 0: // '\0'
            break;

        case 1: // '\001'
            ASN1TaggedObject o = (ASN1TaggedObject)seq.getObjectAt(0);
            switch(o.getTagNo())
            {
            case 0: // '\0'
                certs = ASN1Set.getInstance(o, false);
                break;

            case 1: // '\001'
                crls = ASN1Set.getInstance(o, false);
                break;

            default:
                throw new IllegalArgumentException((new StringBuilder()).append("Bad tag in OriginatorInfo: ").append(o.getTagNo()).toString());
            }
            break;

        case 2: // '\002'
            certs = ASN1Set.getInstance((ASN1TaggedObject)seq.getObjectAt(0), false);
            crls = ASN1Set.getInstance((ASN1TaggedObject)seq.getObjectAt(1), false);
            break;

        default:
            throw new IllegalArgumentException("OriginatorInfo too big");
        }
    }

    public static OriginatorInfo getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static OriginatorInfo getInstance(Object obj)
    {
        if(obj instanceof OriginatorInfo)
            return (OriginatorInfo)obj;
        if(obj != null)
            return new OriginatorInfo(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public ASN1Set getCertificates()
    {
        return certs;
    }

    public ASN1Set getCRLs()
    {
        return crls;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(certs != null)
            v.add(new DERTaggedObject(false, 0, certs));
        if(crls != null)
            v.add(new DERTaggedObject(false, 1, crls));
        return new DERSequence(v);
    }

    private ASN1Set certs;
    private ASN1Set crls;
}
