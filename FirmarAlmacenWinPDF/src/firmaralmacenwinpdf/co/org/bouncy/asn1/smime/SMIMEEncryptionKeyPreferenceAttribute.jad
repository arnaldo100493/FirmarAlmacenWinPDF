// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMEEncryptionKeyPreferenceAttribute.java

package co.org.bouncy.asn1.smime;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;

// Referenced classes of package co.org.bouncy.asn1.smime:
//            SMIMEAttributes

public class SMIMEEncryptionKeyPreferenceAttribute extends Attribute
{

    public SMIMEEncryptionKeyPreferenceAttribute(IssuerAndSerialNumber issAndSer)
    {
        super(SMIMEAttributes.encrypKeyPref, new DERSet(new DERTaggedObject(false, 0, issAndSer)));
    }

    public SMIMEEncryptionKeyPreferenceAttribute(RecipientKeyIdentifier rKeyId)
    {
        super(SMIMEAttributes.encrypKeyPref, new DERSet(new DERTaggedObject(false, 1, rKeyId)));
    }

    public SMIMEEncryptionKeyPreferenceAttribute(ASN1OctetString sKeyId)
    {
        super(SMIMEAttributes.encrypKeyPref, new DERSet(new DERTaggedObject(false, 2, sKeyId)));
    }
}
