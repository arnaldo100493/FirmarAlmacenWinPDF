// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ResponderID.java

package co.org.bouncy.asn1.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.X500Name;

public class ResponderID extends ASN1Object
    implements ASN1Choice
{

    public ResponderID(ASN1OctetString value)
    {
        this.value = value;
    }

    public ResponderID(X500Name value)
    {
        this.value = value;
    }

    public static ResponderID getInstance(Object obj)
    {
        if(obj instanceof ResponderID)
            return (ResponderID)obj;
        if(obj instanceof DEROctetString)
            return new ResponderID((DEROctetString)obj);
        if(obj instanceof ASN1TaggedObject)
        {
            ASN1TaggedObject o = (ASN1TaggedObject)obj;
            if(o.getTagNo() == 1)
                return new ResponderID(X500Name.getInstance(o, true));
            else
                return new ResponderID(ASN1OctetString.getInstance(o, true));
        } else
        {
            return new ResponderID(X500Name.getInstance(obj));
        }
    }

    public static ResponderID getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(obj.getObject());
    }

    public byte[] getKeyHash()
    {
        if(value instanceof ASN1OctetString)
        {
            ASN1OctetString octetString = (ASN1OctetString)value;
            return octetString.getOctets();
        } else
        {
            return null;
        }
    }

    public X500Name getName()
    {
        if(value instanceof ASN1OctetString)
            return null;
        else
            return X500Name.getInstance(value);
    }

    public ASN1Primitive toASN1Primitive()
    {
        if(value instanceof ASN1OctetString)
            return new DERTaggedObject(true, 2, value);
        else
            return new DERTaggedObject(true, 1, value);
    }

    private ASN1Encodable value;
}
