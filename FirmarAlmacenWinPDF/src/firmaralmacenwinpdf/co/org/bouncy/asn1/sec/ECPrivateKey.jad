// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECPrivateKey.java

package co.org.bouncy.asn1.sec;

import co.org.bouncy.asn1.*;
import co.org.bouncy.util.BigIntegers;
import java.math.BigInteger;
import java.util.Enumeration;

public class ECPrivateKey extends ASN1Object
{

    private ECPrivateKey(ASN1Sequence seq)
    {
        this.seq = seq;
    }

    public static ECPrivateKey getInstance(Object obj)
    {
        if(obj instanceof ECPrivateKey)
            return (ECPrivateKey)obj;
        if(obj != null)
            return new ECPrivateKey(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public ECPrivateKey(BigInteger key)
    {
        byte bytes[] = BigIntegers.asUnsignedByteArray(key);
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(1L));
        v.add(new DEROctetString(bytes));
        seq = new DERSequence(v);
    }

    public ECPrivateKey(BigInteger key, ASN1Object parameters)
    {
        this(key, null, parameters);
    }

    public ECPrivateKey(BigInteger key, DERBitString publicKey, ASN1Object parameters)
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
