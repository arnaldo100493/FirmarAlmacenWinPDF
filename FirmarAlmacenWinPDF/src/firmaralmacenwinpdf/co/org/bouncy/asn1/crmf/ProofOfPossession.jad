// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProofOfPossession.java

package co.org.bouncy.asn1.crmf;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.crmf:
//            POPOSigningKey, POPOPrivKey

public class ProofOfPossession extends ASN1Object
    implements ASN1Choice
{

    private ProofOfPossession(ASN1TaggedObject tagged)
    {
        tagNo = tagged.getTagNo();
        switch(tagNo)
        {
        case 0: // '\0'
            obj = DERNull.INSTANCE;
            break;

        case 1: // '\001'
            obj = POPOSigningKey.getInstance(tagged, false);
            break;

        case 2: // '\002'
        case 3: // '\003'
            obj = POPOPrivKey.getInstance(tagged, true);
            break;

        default:
            throw new IllegalArgumentException((new StringBuilder()).append("unknown tag: ").append(tagNo).toString());
        }
    }

    public static ProofOfPossession getInstance(Object o)
    {
        if(o == null || (o instanceof ProofOfPossession))
            return (ProofOfPossession)o;
        if(o instanceof ASN1TaggedObject)
            return new ProofOfPossession((ASN1TaggedObject)o);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid object: ").append(o.getClass().getName()).toString());
    }

    public ProofOfPossession()
    {
        tagNo = 0;
        obj = DERNull.INSTANCE;
    }

    public ProofOfPossession(POPOSigningKey poposk)
    {
        tagNo = 1;
        obj = poposk;
    }

    public ProofOfPossession(int type, POPOPrivKey privkey)
    {
        tagNo = type;
        obj = privkey;
    }

    public int getType()
    {
        return tagNo;
    }

    public ASN1Encodable getObject()
    {
        return obj;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return new DERTaggedObject(false, tagNo, obj);
    }

    public static final int TYPE_RA_VERIFIED = 0;
    public static final int TYPE_SIGNING_KEY = 1;
    public static final int TYPE_KEY_ENCIPHERMENT = 2;
    public static final int TYPE_KEY_AGREEMENT = 3;
    private int tagNo;
    private ASN1Encodable obj;
}
