// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyTransRecipientInfo.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            RecipientIdentifier

public class KeyTransRecipientInfo extends ASN1Object
{

    public KeyTransRecipientInfo(RecipientIdentifier rid, AlgorithmIdentifier keyEncryptionAlgorithm, ASN1OctetString encryptedKey)
    {
        if(rid.toASN1Primitive() instanceof ASN1TaggedObject)
            version = new ASN1Integer(2L);
        else
            version = new ASN1Integer(0L);
        this.rid = rid;
        this.keyEncryptionAlgorithm = keyEncryptionAlgorithm;
        this.encryptedKey = encryptedKey;
    }

    public KeyTransRecipientInfo(ASN1Sequence seq)
    {
        version = (ASN1Integer)seq.getObjectAt(0);
        rid = RecipientIdentifier.getInstance(seq.getObjectAt(1));
        keyEncryptionAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(2));
        encryptedKey = (ASN1OctetString)seq.getObjectAt(3);
    }

    public static KeyTransRecipientInfo getInstance(Object obj)
    {
        if(obj == null || (obj instanceof KeyTransRecipientInfo))
            return (KeyTransRecipientInfo)obj;
        if(obj instanceof ASN1Sequence)
            return new KeyTransRecipientInfo((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Illegal object in KeyTransRecipientInfo: ").append(obj.getClass().getName()).toString());
    }

    public ASN1Integer getVersion()
    {
        return version;
    }

    public RecipientIdentifier getRecipientIdentifier()
    {
        return rid;
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
        v.add(rid);
        v.add(keyEncryptionAlgorithm);
        v.add(encryptedKey);
        return new DERSequence(v);
    }

    private ASN1Integer version;
    private RecipientIdentifier rid;
    private AlgorithmIdentifier keyEncryptionAlgorithm;
    private ASN1OctetString encryptedKey;
}
