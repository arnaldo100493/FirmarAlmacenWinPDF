// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RecipientKeyIdentifier.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            OtherKeyAttribute

public class RecipientKeyIdentifier extends ASN1Object
{

    public RecipientKeyIdentifier(ASN1OctetString subjectKeyIdentifier, DERGeneralizedTime date, OtherKeyAttribute other)
    {
        this.subjectKeyIdentifier = subjectKeyIdentifier;
        this.date = date;
        this.other = other;
    }

    public RecipientKeyIdentifier(byte subjectKeyIdentifier[], DERGeneralizedTime date, OtherKeyAttribute other)
    {
        this.subjectKeyIdentifier = new DEROctetString(subjectKeyIdentifier);
        this.date = date;
        this.other = other;
    }

    public RecipientKeyIdentifier(byte subjectKeyIdentifier[])
    {
        this(subjectKeyIdentifier, null, null);
    }

    public RecipientKeyIdentifier(ASN1Sequence seq)
    {
        subjectKeyIdentifier = ASN1OctetString.getInstance(seq.getObjectAt(0));
        switch(seq.size())
        {
        case 1: // '\001'
            break;

        case 2: // '\002'
            if(seq.getObjectAt(1) instanceof DERGeneralizedTime)
                date = (DERGeneralizedTime)seq.getObjectAt(1);
            else
                other = OtherKeyAttribute.getInstance(seq.getObjectAt(2));
            break;

        case 3: // '\003'
            date = (DERGeneralizedTime)seq.getObjectAt(1);
            other = OtherKeyAttribute.getInstance(seq.getObjectAt(2));
            break;

        default:
            throw new IllegalArgumentException("Invalid RecipientKeyIdentifier");
        }
    }

    public static RecipientKeyIdentifier getInstance(ASN1TaggedObject _ato, boolean _explicit)
    {
        return getInstance(ASN1Sequence.getInstance(_ato, _explicit));
    }

    public static RecipientKeyIdentifier getInstance(Object _obj)
    {
        if(_obj == null || (_obj instanceof RecipientKeyIdentifier))
            return (RecipientKeyIdentifier)_obj;
        if(_obj instanceof ASN1Sequence)
            return new RecipientKeyIdentifier((ASN1Sequence)_obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid RecipientKeyIdentifier: ").append(_obj.getClass().getName()).toString());
    }

    public ASN1OctetString getSubjectKeyIdentifier()
    {
        return subjectKeyIdentifier;
    }

    public DERGeneralizedTime getDate()
    {
        return date;
    }

    public OtherKeyAttribute getOtherKeyAttribute()
    {
        return other;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(subjectKeyIdentifier);
        if(date != null)
            v.add(date);
        if(other != null)
            v.add(other);
        return new DERSequence(v);
    }

    private ASN1OctetString subjectKeyIdentifier;
    private DERGeneralizedTime date;
    private OtherKeyAttribute other;
}
