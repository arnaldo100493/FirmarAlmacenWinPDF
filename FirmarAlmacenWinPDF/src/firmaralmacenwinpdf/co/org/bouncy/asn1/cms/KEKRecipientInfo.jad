// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KEKRecipientInfo.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            KEKIdentifier

public class KEKRecipientInfo extends ASN1Object
{

    public KEKRecipientInfo(KEKIdentifier kekid, AlgorithmIdentifier keyEncryptionAlgorithm, ASN1OctetString encryptedKey)
    {
        version = new ASN1Integer(4L);
        this.kekid = kekid;
        this.keyEncryptionAlgorithm = keyEncryptionAlgorithm;
        this.encryptedKey = encryptedKey;
    }

    public KEKRecipientInfo(ASN1Sequence seq)
    {
        version = (ASN1Integer)seq.getObjectAt(0);
        kekid = KEKIdentifier.getInstance(seq.getObjectAt(1));
        keyEncryptionAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(2));
        encryptedKey = (ASN1OctetString)seq.getObjectAt(3);
    }

    public static KEKRecipientInfo getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static KEKRecipientInfo getInstance(Object obj)
    {
        if(obj == null || (obj instanceof KEKRecipientInfo))
            return (KEKRecipientInfo)obj;
        if(obj instanceof ASN1Sequence)
            return new KEKRecipientInfo((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid KEKRecipientInfo: ").append(obj.getClass().getName()).toString());
    }

    public ASN1Integer getVersion()
    {
        return version;
    }

    public KEKIdentifier getKekid()
    {
        return kekid;
    }

    public AlgorithmIdentifier getKeyEncryptionAlgorithm()
    {
        return keyEncryptionAlgorithm;
    }

    public ASN1OctetString getEncryptedKey()
    {
        return encryptedKey;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(version);
        v.add(kekid);
        v.add(keyEncryptionAlgorithm);
        v.add(encryptedKey);
        return new DERSequence(v);
    }

    private ASN1Integer version;
    private KEKIdentifier kekid;
    private AlgorithmIdentifier keyEncryptionAlgorithm;
    private ASN1OctetString encryptedKey;
}
