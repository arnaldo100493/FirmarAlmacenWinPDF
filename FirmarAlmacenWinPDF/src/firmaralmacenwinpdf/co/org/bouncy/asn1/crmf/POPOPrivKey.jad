// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   POPOPrivKey.java

package co.org.bouncy.asn1.crmf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.EnvelopedData;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.crmf:
//            SubsequentMessage, PKMACValue

public class POPOPrivKey extends ASN1Object
    implements ASN1Choice
{

    private POPOPrivKey(ASN1TaggedObject obj)
    {
        tagNo = obj.getTagNo();
        switch(tagNo)
        {
        case 0: // '\0'
            this.obj = DERBitString.getInstance(obj, false);
            break;

        case 1: // '\001'
            this.obj = SubsequentMessage.valueOf(ASN1Integer.getInstance(obj, false).getValue().intValue());
            break;

        case 2: // '\002'
            this.obj = DERBitString.getInstance(obj, false);
            break;

        case 3: // '\003'
            this.obj = PKMACValue.getInstance(obj, false);
            break;

        case 4: // '\004'
            this.obj = EnvelopedData.getInstance(obj, false);
            break;

        default:
            throw new IllegalArgumentException("unknown tag in POPOPrivKey");
        }
    }

    public static POPOPrivKey getInstance(Object obj)
    {
        if(obj instanceof POPOPrivKey)
            return (POPOPrivKey)obj;
        if(obj != null)
            return new POPOPrivKey(ASN1TaggedObject.getInstance(obj));
        else
            return null;
    }

    public static POPOPrivKey getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1TaggedObject.getInstance(obj, explicit));
    }

    public POPOPrivKey(SubsequentMessage msg)
    {
        tagNo = 1;
        obj = msg;
    }

    public int getType()
    {
        return tagNo;
    }

    public ASN1Encodable getValue()
    {
        return obj;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return new DERTaggedObject(false, tagNo, obj);
    }

    public static final int thisMessage = 0;
    public static final int subsequentMessage = 1;
    public static final int dhMAC = 2;
    public static final int agreeMAC = 3;
    public static final int encryptedKey = 4;
    private int tagNo;
    private ASN1Encodable obj;
}
