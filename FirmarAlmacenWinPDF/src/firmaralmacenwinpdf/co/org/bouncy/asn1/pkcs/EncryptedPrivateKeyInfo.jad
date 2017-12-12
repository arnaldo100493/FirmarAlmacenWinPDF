// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EncryptedPrivateKeyInfo.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import java.util.Enumeration;

public class EncryptedPrivateKeyInfo extends ASN1Object
{

    private EncryptedPrivateKeyInfo(ASN1Sequence seq)
    {
        Enumeration e = seq.getObjects();
        algId = AlgorithmIdentifier.getInstance(e.nextElement());
        data = ASN1OctetString.getInstance(e.nextElement());
    }

    public EncryptedPrivateKeyInfo(AlgorithmIdentifier algId, byte encoding[])
    {
        this.algId = algId;
        data = new DEROctetString(encoding);
    }

    public static EncryptedPrivateKeyInfo getInstance(Object obj)
    {
        if(obj instanceof EncryptedPrivateKeyInfo)
            return (EncryptedPrivateKeyInfo)obj;
        if(obj != null)
            return new EncryptedPrivateKeyInfo(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public AlgorithmIdentifier getEncryptionAlgorithm()
    {
        return algId;
    }

    public byte[] getEncryptedData()
    {
        return data.getOctets();
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(algId);
        v.add(data);
        return new DERSequence(v);
    }

    private AlgorithmIdentifier algId;
    private ASN1OctetString data;
}
