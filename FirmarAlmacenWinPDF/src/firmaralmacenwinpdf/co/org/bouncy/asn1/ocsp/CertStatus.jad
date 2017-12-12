// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertStatus.java

package co.org.bouncy.asn1.ocsp;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.ocsp:
//            RevokedInfo

public class CertStatus extends ASN1Object
    implements ASN1Choice
{

    public CertStatus()
    {
        tagNo = 0;
        value = DERNull.INSTANCE;
    }

    public CertStatus(RevokedInfo info)
    {
        tagNo = 1;
        value = info;
    }

    public CertStatus(int tagNo, ASN1Encodable value)
    {
        this.tagNo = tagNo;
        this.value = value;
    }

    public CertStatus(ASN1TaggedObject choice)
    {
        tagNo = choice.getTagNo();
        switch(choice.getTagNo())
        {
        case 0: // '\0'
            value = DERNull.INSTANCE;
            break;

        case 1: // '\001'
            value = RevokedInfo.getInstance(choice, false);
            break;

        case 2: // '\002'
            value = DERNull.INSTANCE;
            break;
        }
    }

    public static CertStatus getInstance(Object obj)
    {
        if(obj == null || (obj instanceof CertStatus))
            return (CertStatus)obj;
        if(obj instanceof ASN1TaggedObject)
            return new CertStatus((ASN1TaggedObject)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("unknown object in factory: ").append(obj.getClass().getName()).toString());
    }

    public static CertStatus getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(obj.getObject());
    }

    public int getTagNo()
    {
        return tagNo;
    }

    public ASN1Encodable getStatus()
    {
        return value;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return new DERTaggedObject(false, tagNo, value);
    }

    private int tagNo;
    private ASN1Encodable value;
}
