// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EncryptionScheme.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

public class EncryptionScheme extends ASN1Object
{

    public EncryptionScheme(ASN1ObjectIdentifier objectId, ASN1Encodable parameters)
    {
        algId = new AlgorithmIdentifier(objectId, parameters);
    }

    private EncryptionScheme(ASN1Sequence seq)
    {
        algId = AlgorithmIdentifier.getInstance(seq);
    }

    public static final EncryptionScheme getInstance(Object obj)
    {
        if(obj instanceof EncryptionScheme)
            return (EncryptionScheme)obj;
        if(obj != null)
            return new EncryptionScheme(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public ASN1ObjectIdentifier getAlgorithm()
    {
        return algId.getAlgorithm();
    }

    public ASN1Encodable getParameters()
    {
        return algId.getParameters();
    }

    public ASN1Primitive toASN1Primitive()
    {
        return algId.toASN1Primitive();
    }

    private AlgorithmIdentifier algId;
}
