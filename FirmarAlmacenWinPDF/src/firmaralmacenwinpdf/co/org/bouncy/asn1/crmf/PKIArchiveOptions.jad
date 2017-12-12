// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKIArchiveOptions.java

package co.org.bouncy.asn1.crmf;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.crmf:
//            EncryptedKey

public class PKIArchiveOptions extends ASN1Object
    implements ASN1Choice
{

    public static PKIArchiveOptions getInstance(Object o)
    {
        if(o == null || (o instanceof PKIArchiveOptions))
            return (PKIArchiveOptions)o;
        if(o instanceof ASN1TaggedObject)
            return new PKIArchiveOptions((ASN1TaggedObject)o);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("unknown object: ").append(o).toString());
    }

    private PKIArchiveOptions(ASN1TaggedObject tagged)
    {
        switch(tagged.getTagNo())
        {
        case 0: // '\0'
            value = EncryptedKey.getInstance(tagged.getObject());
            break;

        case 1: // '\001'
            value = ASN1OctetString.getInstance(tagged, false);
            break;

        case 2: // '\002'
            value = ASN1Boolean.getInstance(tagged, false);
            break;

        default:
            throw new IllegalArgumentException((new StringBuilder()).append("unknown tag number: ").append(tagged.getTagNo()).toString());
        }
    }

    public PKIArchiveOptions(EncryptedKey encKey)
    {
        value = encKey;
    }

    public PKIArchiveOptions(ASN1OctetString keyGenParameters)
    {
        value = keyGenParameters;
    }

    public PKIArchiveOptions(boolean archiveRemGenPrivKey)
    {
        value = ASN1Boolean.getInstance(archiveRemGenPrivKey);
    }

    public int getType()
    {
        if(value instanceof EncryptedKey)
            return 0;
        return !(value instanceof ASN1OctetString) ? 2 : 1;
    }

    public ASN1Encodable getValue()
    {
        return value;
    }

    public ASN1Primitive toASN1Primitive()
    {
        if(value instanceof EncryptedKey)
            return new DERTaggedObject(true, 0, value);
        if(value instanceof ASN1OctetString)
            return new DERTaggedObject(false, 1, value);
        else
            return new DERTaggedObject(false, 2, value);
    }

    public static final int encryptedPrivKey = 0;
    public static final int keyGenParameters = 1;
    public static final int archiveRemGenPrivKey = 2;
    private ASN1Encodable value;
}
