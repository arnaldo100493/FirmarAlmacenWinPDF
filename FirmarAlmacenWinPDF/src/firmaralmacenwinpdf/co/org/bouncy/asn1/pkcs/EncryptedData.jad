// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EncryptedData.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import java.math.BigInteger;

public class EncryptedData extends ASN1Object
{

    public static EncryptedData getInstance(Object obj)
    {
        if(obj instanceof EncryptedData)
            return (EncryptedData)obj;
        if(obj != null)
            return new EncryptedData(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private EncryptedData(ASN1Sequence seq)
    {
        int version = ((ASN1Integer)seq.getObjectAt(0)).getValue().intValue();
        if(version != 0)
        {
            throw new IllegalArgumentException("sequence not version 0");
        } else
        {
            data = ASN1Sequence.getInstance(seq.getObjectAt(1));
            return;
        }
    }

    public EncryptedData(ASN1ObjectIdentifier contentType, AlgorithmIdentifier encryptionAlgorithm, ASN1Encodable content)
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(contentType);
        v.add(encryptionAlgorithm.toASN1Primitive());
        v.add(new BERTaggedObject(false, 0, content));
        data = new BERSequence(v);
    }

    public ASN1ObjectIdentifier getContentType()
    {
        return ASN1ObjectIdentifier.getInstance(data.getObjectAt(0));
    }

    public AlgorithmIdentifier getEncryptionAlgorithm()
    {
        return AlgorithmIdentifier.getInstance(data.getObjectAt(1));
    }

    public ASN1OctetString getContent()
    {
        if(data.size() == 3)
        {
            ASN1TaggedObject o = ASN1TaggedObject.getInstance(data.getObjectAt(2));
            return ASN1OctetString.getInstance(o, false);
        } else
        {
            return null;
        }
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(0L));
        v.add(data);
        return new BERSequence(v);
    }

    ASN1Sequence data;
    ASN1ObjectIdentifier bagId;
    ASN1Primitive bagValue;
}
