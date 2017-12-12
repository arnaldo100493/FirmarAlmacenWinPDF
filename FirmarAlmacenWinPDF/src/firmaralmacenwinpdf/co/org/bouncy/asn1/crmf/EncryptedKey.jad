// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EncryptedKey.java

package co.org.bouncy.asn1.crmf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.EnvelopedData;

// Referenced classes of package co.org.bouncy.asn1.crmf:
//            EncryptedValue

public class EncryptedKey extends ASN1Object
    implements ASN1Choice
{

    public static EncryptedKey getInstance(Object o)
    {
        if(o instanceof EncryptedKey)
            return (EncryptedKey)o;
        if(o instanceof ASN1TaggedObject)
            return new EncryptedKey(EnvelopedData.getInstance((ASN1TaggedObject)o, false));
        if(o instanceof EncryptedValue)
            return new EncryptedKey((EncryptedValue)o);
        else
            return new EncryptedKey(EncryptedValue.getInstance(o));
    }

    public EncryptedKey(EnvelopedData envelopedData)
    {
        this.envelopedData = envelopedData;
    }

    public EncryptedKey(EncryptedValue encryptedValue)
    {
        this.encryptedValue = encryptedValue;
    }

    public boolean isEncryptedValue()
    {
        return encryptedValue != null;
    }

    public ASN1Encodable getValue()
    {
        if(encryptedValue != null)
            return encryptedValue;
        else
            return envelopedData;
    }

    public ASN1Primitive toASN1Primitive()
    {
        if(encryptedValue != null)
            return encryptedValue.toASN1Primitive();
        else
            return new DERTaggedObject(false, 0, envelopedData);
    }

    private EnvelopedData envelopedData;
    private EncryptedValue encryptedValue;
}
