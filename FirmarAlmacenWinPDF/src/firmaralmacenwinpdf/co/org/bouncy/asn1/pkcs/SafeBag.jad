// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SafeBag.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;

public class SafeBag extends ASN1Object
{

    public SafeBag(ASN1ObjectIdentifier oid, ASN1Encodable obj)
    {
        bagId = oid;
        bagValue = obj;
        bagAttributes = null;
    }

    public SafeBag(ASN1ObjectIdentifier oid, ASN1Encodable obj, ASN1Set bagAttributes)
    {
        bagId = oid;
        bagValue = obj;
        this.bagAttributes = bagAttributes;
    }

    public static SafeBag getInstance(Object obj)
    {
        if(obj instanceof SafeBag)
            return (SafeBag)obj;
        if(obj != null)
            return new SafeBag(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private SafeBag(ASN1Sequence seq)
    {
        bagId = (ASN1ObjectIdentifier)seq.getObjectAt(0);
        bagValue = ((ASN1TaggedObject)seq.getObjectAt(1)).getObject();
        if(seq.size() == 3)
            bagAttributes = (ASN1Set)seq.getObjectAt(2);
    }

    public ASN1ObjectIdentifier getBagId()
    {
        return bagId;
    }

    public ASN1Encodable getBagValue()
    {
        return bagValue;
    }

    public ASN1Set getBagAttributes()
    {
        return bagAttributes;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(bagId);
        v.add(new DLTaggedObject(true, 0, bagValue));
        if(bagAttributes != null)
            v.add(bagAttributes);
        return new DLSequence(v);
    }

    private ASN1ObjectIdentifier bagId;
    private ASN1Encodable bagValue;
    private ASN1Set bagAttributes;
}
