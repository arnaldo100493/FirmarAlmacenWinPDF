// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECPrivateKeyStructure.java

package co.org.bouncy.asn1.sec;

import co.org.bouncy.asn1.*;
import co.org.bouncy.util.BigIntegers;
import java.math.BigInteger;
import java.util.Enumeration;

/**
 * @deprecated Class ECPrivateKeyStructure is deprecated
 */

public class ECPrivateKeyStructure extends ASN1Object
{

    public ECPrivateKeyStructure(ASN1Sequence seq)
    {
        this.seq = seq;
    }

    public ECPrivateKeyStructure(BigInteger key)
    {
        byte bytes[] = BigIntegers.asUnsignedByteArray(key);
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(1L));
        v.add(new DEROctetString(bytes));
        seq = new DERSequence(v);
    }

    public ECPrivateKeyStructure(BigInteger key, ASN1Encodable parameters)
    {
        this(key, null, parameters);
    }

    public ECPrivateKeyStructure(BigInteger key, DERBitString publicKey, ASN1Encodable parameters)
    {
        byte bytes[] = BigIntegers.asUnsignedByteArray(key);
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(1L));
        v.add(new DEROctetString(bytes));
        if(parameters != null)
            v.add(new DERTaggedObject(true, 0, parameters));
        if(publicKey != null)
            v.add(new DERTaggedObject(true, 1, publicKey));
        seq = new DERSequence(v);
    }

    public BigInteger getKey()
    {
        ASN1OctetString octs = (ASN1OctetString)seq.getObjectAt(1);
        return new BigInteger(1, octs.getOctets());
    }

    public DERBitString getPublicKey()
    {
        return (DERBitString)getObjectInTag(1);
    }

    public ASN1Primitive getParameters()
    {
        return getObjectInTag(0);
    }

    private ASN1Primitive getObjectInTag(int tagNo)
    {
        for(Enumeration e = seq.getObjects(); e.hasMoreElements();)
        {
            ASN1Encodable obj = (ASN1Encodable)e.nextElement();
            if(obj instanceof ASN1TaggedObject)
            {
                ASN1TaggedObject tag = (ASN1TaggedObject)obj;
                if(tag.getTagNo() == tagNo)
                    return tag.getObject().toASN1Primitive();
            }
        }

        return null;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return seq;
    }

    private ASN1Sequence seq;
}
