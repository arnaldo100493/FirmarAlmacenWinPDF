// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509Attribute.java

package co.org.bouncy.x509_;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.Attribute;

public class X509Attribute extends ASN1Object
{

    X509Attribute(ASN1Encodable at)
    {
        attr = Attribute.getInstance(at);
    }

    public X509Attribute(String oid, ASN1Encodable value)
    {
        attr = new Attribute(new ASN1ObjectIdentifier(oid), new DERSet(value));
    }

    public X509Attribute(String oid, ASN1EncodableVector value)
    {
        attr = new Attribute(new ASN1ObjectIdentifier(oid), new DERSet(value));
    }

    public String getOID()
    {
        return attr.getAttrType().getId();
    }

    public ASN1Encodable[] getValues()
    {
        ASN1Set s = attr.getAttrValues();
        ASN1Encodable values[] = new ASN1Encodable[s.size()];
        for(int i = 0; i != s.size(); i++)
            values[i] = s.getObjectAt(i);

        return values;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return attr.toASN1Primitive();
    }

    Attribute attr;
}
