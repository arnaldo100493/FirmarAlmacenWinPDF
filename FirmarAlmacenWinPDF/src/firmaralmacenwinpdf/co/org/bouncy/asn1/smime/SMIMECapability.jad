// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMECapability.java

package co.org.bouncy.asn1.smime;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;

public class SMIMECapability extends ASN1Object
{

    public SMIMECapability(ASN1Sequence seq)
    {
        capabilityID = (ASN1ObjectIdentifier)seq.getObjectAt(0);
        if(seq.size() > 1)
            parameters = (ASN1Primitive)seq.getObjectAt(1);
    }

    public SMIMECapability(ASN1ObjectIdentifier capabilityID, ASN1Encodable parameters)
    {
        this.capabilityID = capabilityID;
        this.parameters = parameters;
    }

    public static SMIMECapability getInstance(Object obj)
    {
        if(obj == null || (obj instanceof SMIMECapability))
            return (SMIMECapability)obj;
        if(obj instanceof ASN1Sequence)
            return new SMIMECapability((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException("Invalid SMIMECapability");
    }

    public ASN1ObjectIdentifier getCapabilityID()
    {
        return capabilityID;
    }

    public ASN1Encodable getParameters()
    {
        return parameters;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(capabilityID);
        if(parameters != null)
            v.add(parameters);
        return new DERSequence(v);
    }

    public static final ASN1ObjectIdentifier preferSignedData;
    public static final ASN1ObjectIdentifier canNotDecryptAny;
    public static final ASN1ObjectIdentifier sMIMECapabilitiesVersions;
    public static final ASN1ObjectIdentifier dES_CBC = new ASN1ObjectIdentifier("1.3.14.3.2.7");
    public static final ASN1ObjectIdentifier dES_EDE3_CBC;
    public static final ASN1ObjectIdentifier rC2_CBC;
    public static final ASN1ObjectIdentifier aES128_CBC;
    public static final ASN1ObjectIdentifier aES192_CBC;
    public static final ASN1ObjectIdentifier aES256_CBC;
    private ASN1ObjectIdentifier capabilityID;
    private ASN1Encodable parameters;

    static 
    {
        preferSignedData = PKCSObjectIdentifiers.preferSignedData;
        canNotDecryptAny = PKCSObjectIdentifiers.canNotDecryptAny;
        sMIMECapabilitiesVersions = PKCSObjectIdentifiers.sMIMECapabilitiesVersions;
        dES_EDE3_CBC = PKCSObjectIdentifiers.des_EDE3_CBC;
        rC2_CBC = PKCSObjectIdentifiers.RC2_CBC;
        aES128_CBC = NISTObjectIdentifiers.id_aes128_CBC;
        aES192_CBC = NISTObjectIdentifiers.id_aes192_CBC;
        aES256_CBC = NISTObjectIdentifiers.id_aes256_CBC;
    }
}
