// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RecipientInfo.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            KeyAgreeRecipientInfo, KEKRecipientInfo, PasswordRecipientInfo, KeyTransRecipientInfo, 
//            OtherRecipientInfo

public class RecipientInfo extends ASN1Object
    implements ASN1Choice
{

    public RecipientInfo(KeyTransRecipientInfo info)
    {
        this.info = info;
    }

    public RecipientInfo(KeyAgreeRecipientInfo info)
    {
        this.info = new DERTaggedObject(false, 1, info);
    }

    public RecipientInfo(KEKRecipientInfo info)
    {
        this.info = new DERTaggedObject(false, 2, info);
    }

    public RecipientInfo(PasswordRecipientInfo info)
    {
        this.info = new DERTaggedObject(false, 3, info);
    }

    public RecipientInfo(OtherRecipientInfo info)
    {
        this.info = new DERTaggedObject(false, 4, info);
    }

    public RecipientInfo(ASN1Primitive info)
    {
        this.info = info;
    }

    public static RecipientInfo getInstance(Object o)
    {
        if(o == null || (o instanceof RecipientInfo))
            return (RecipientInfo)o;
        if(o instanceof ASN1Sequence)
            return new RecipientInfo((ASN1Sequence)o);
        if(o instanceof ASN1TaggedObject)
            return new RecipientInfo((ASN1TaggedObject)o);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("unknown object in factory: ").append(o.getClass().getName()).toString());
    }

    public ASN1Integer getVersion()
    {
        if(info instanceof ASN1TaggedObject)
        {
            ASN1TaggedObject o = (ASN1TaggedObject)info;
            switch(o.getTagNo())
            {
            case 1: // '\001'
                return KeyAgreeRecipientInfo.getInstance(o, false).getVersion();

            case 2: // '\002'
                return getKEKInfo(o).getVersion();

            case 3: // '\003'
                return PasswordRecipientInfo.getInstance(o, false).getVersion();

            case 4: // '\004'
                return new ASN1Integer(0L);
            }
            throw new IllegalStateException("unknown tag");
        } else
        {
            return KeyTransRecipientInfo.getInstance(info).getVersion();
        }
    }

    public boolean isTagged()
    {
        return info instanceof ASN1TaggedObject;
    }

    public ASN1Encodable getInfo()
    {
        if(info instanceof ASN1TaggedObject)
        {
            ASN1TaggedObject o = (ASN1TaggedObject)info;
            switch(o.getTagNo())
            {
            case 1: // '\001'
                return KeyAgreeRecipientInfo.getInstance(o, false);

            case 2: // '\002'
                return getKEKInfo(o);

            case 3: // '\003'
                return PasswordRecipientInfo.getInstance(o, false);

            case 4: // '\004'
                return OtherRecipientInfo.getInstance(o, false);
            }
            throw new IllegalStateException("unknown tag");
        } else
        {
            return KeyTransRecipientInfo.getInstance(info);
        }
    }

    private KEKRecipientInfo getKEKInfo(ASN1TaggedObject o)
    {
        if(o.isExplicit())
            return KEKRecipientInfo.getInstance(o, true);
        else
            return KEKRecipientInfo.getInstance(o, false);
    }

    public ASN1Primitive toASN1Primitive()
    {
        return info.toASN1Primitive();
    }

    ASN1Encodable info;
}
