// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KEKIdentifier.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            OtherKeyAttribute

public class KEKIdentifier extends ASN1Object
{

    public KEKIdentifier(byte keyIdentifier[], ASN1GeneralizedTime date, OtherKeyAttribute other)
    {
        this.keyIdentifier = new DEROctetString(keyIdentifier);
        this.date = date;
        this.other = other;
    }

    private KEKIdentifier(ASN1Sequence seq)
    {
        keyIdentifier = (ASN1OctetString)seq.getObjectAt(0);
        switch(seq.size())
        {
        case 1: // '\001'
            break;

        case 2: // '\002'
            if(seq.getObjectAt(1) instanceof ASN1GeneralizedTime)
                date = (ASN1GeneralizedTime)seq.getObjectAt(1);
            else
                other = OtherKeyAttribute.getInstance(seq.getObjectAt(1));
            break;

        case 3: // '\003'
            date = (ASN1GeneralizedTime)seq.getObjectAt(1);
            other = OtherKeyAttribute.getInstance(seq.getObjectAt(2));
            break;

        default:
            throw new IllegalArgumentException("Invalid KEKIdentifier");
        }
    }

    public static KEKIdentifier getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static KEKIdentifier getInstance(Object obj)
    {
        if(obj == null || (obj instanceof KEKIdentifier))
            return (KEKIdentifier)obj;
        if(obj instanceof ASN1Sequence)
            return new KEKIdentifier((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid KEKIdentifier: ").append(obj.getClass().getName()).toString());
    }

    public ASN1OctetString getKeyIdentifier()
    {
        return keyIdentifier;
    }

    public ASN1GeneralizedTime getDate()
    {
        return date;
    }

    public OtherKeyAttribute getOther()
    {
        return other;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(keyIdentifier);
        if(date != null)
            v.add(date);
        if(other != null)
            v.add(other);
        return new DERSequence(v);
    }

    private ASN1OctetString keyIdentifier;
    private ASN1GeneralizedTime date;
    private OtherKeyAttribute other;
}
