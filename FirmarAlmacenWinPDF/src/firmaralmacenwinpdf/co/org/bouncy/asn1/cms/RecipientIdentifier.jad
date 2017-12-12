// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RecipientIdentifier.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            IssuerAndSerialNumber

public class RecipientIdentifier extends ASN1Object
    implements ASN1Choice
{

    public RecipientIdentifier(IssuerAndSerialNumber id)
    {
        this.id = id;
    }

    public RecipientIdentifier(ASN1OctetString id)
    {
        this.id = new DERTaggedObject(false, 0, id);
    }

    public RecipientIdentifier(ASN1Primitive id)
    {
        this.id = id;
    }

    public static RecipientIdentifier getInstance(Object o)
    {
        if(o == null || (o instanceof RecipientIdentifier))
            return (RecipientIdentifier)o;
        if(o instanceof IssuerAndSerialNumber)
            return new RecipientIdentifier((IssuerAndSerialNumber)o);
        if(o instanceof ASN1OctetString)
            return new RecipientIdentifier((ASN1OctetString)o);
        if(o instanceof ASN1Primitive)
            return new RecipientIdentifier((ASN1Primitive)o);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Illegal object in RecipientIdentifier: ").append(o.getClass().getName()).toString());
    }

    public boolean isTagged()
    {
        return id instanceof ASN1TaggedObject;
    }

    public ASN1Encodable getId()
    {
        if(id instanceof ASN1TaggedObject)
            return ASN1OctetString.getInstance((ASN1TaggedObject)id, false);
        else
            return IssuerAndSerialNumber.getInstance(id);
    }

    public ASN1Primitive toASN1Primitive()
    {
        return id.toASN1Primitive();
    }

    private ASN1Encodable id;
}
