// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PasswordRecipientInfo.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

public class PasswordRecipientInfo extends ASN1Object
{

    public PasswordRecipientInfo(AlgorithmIdentifier keyEncryptionAlgorithm, ASN1OctetString encryptedKey)
    {
        version = new ASN1Integer(0L);
        this.keyEncryptionAlgorithm = keyEncryptionAlgorithm;
        this.encryptedKey = encryptedKey;
    }

    public PasswordRecipientInfo(AlgorithmIdentifier keyDerivationAlgorithm, AlgorithmIdentifier keyEncryptionAlgorithm, ASN1OctetString encryptedKey)
    {
        version = new ASN1Integer(0L);
        this.keyDerivationAlgorithm = keyDerivationAlgorithm;
        this.keyEncryptionAlgorithm = keyEncryptionAlgorithm;
        this.encryptedKey = encryptedKey;
    }

    public PasswordRecipientInfo(ASN1Sequence seq)
    {
        version = (ASN1Integer)seq.getObjectAt(0);
        if(seq.getObjectAt(1) instanceof ASN1TaggedObject)
        {
            keyDerivationAlgorithm = AlgorithmIdentifier.getInstance((ASN1TaggedObject)seq.getObjectAt(1), false);
            keyEncryptionAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(2));
            encryptedKey = (ASN1OctetString)seq.getObjectAt(3);
        } else
        {
            keyEncryptionAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(1));
            encryptedKey = (ASN1OctetString)seq.getObjectAt(2);
        }
    }

    public static PasswordRecipientInfo getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static PasswordRecipientInfo getInstance(Object obj)
    {
        if(obj == null || (obj instanceof PasswordRecipientInfo))
            return (PasswordRecipientInfo)obj;
        if(obj instanceof ASN1Sequence)
            return new PasswordRecipientInfo((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid PasswordRecipientInfo: ").append(obj.getClass().getName()).toString());
    }

    public ASN1Integer getVersion()
    {
        return version;
    }

    public AlgorithmIdentifier getKeyDerivationAlgorithm()
    {
        return keyDerivationAlgorithm;
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
        if(keyDerivationAlgorithm != null)
            v.add(new DERTaggedObject(false, 0, keyDerivationAlgorithm));
        v.add(keyEncryptionAlgorithm);
        v.add(encryptedKey);
        return new DERSequence(v);
    }

    private ASN1Integer version;
    private AlgorithmIdentifier keyDerivationAlgorithm;
    private AlgorithmIdentifier keyEncryptionAlgorithm;
    private ASN1OctetString encryptedKey;
}
