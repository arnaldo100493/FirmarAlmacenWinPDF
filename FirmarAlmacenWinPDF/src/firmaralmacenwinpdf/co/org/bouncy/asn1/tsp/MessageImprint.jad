// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MessageImprint.java

package co.org.bouncy.asn1.tsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

public class MessageImprint extends ASN1Object
{

    public static MessageImprint getInstance(Object o)
    {
        if(o instanceof MessageImprint)
            return (MessageImprint)o;
        if(o != null)
            return new MessageImprint(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    private MessageImprint(ASN1Sequence seq)
    {
        hashAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(0));
        hashedMessage = ASN1OctetString.getInstance(seq.getObjectAt(1)).getOctets();
    }

    public MessageImprint(AlgorithmIdentifier hashAlgorithm, byte hashedMessage[])
    {
        this.hashAlgorithm = hashAlgorithm;
        this.hashedMessage = hashedMessage;
    }

    public AlgorithmIdentifier getHashAlgorithm()
    {
        return hashAlgorithm;
    }

    public byte[] getHashedMessage()
    {
        return hashedMessage;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(hashAlgorithm);
        v.add(new DEROctetString(hashedMessage));
        return new DERSequence(v);
    }

    AlgorithmIdentifier hashAlgorithm;
    byte hashedMessage[];
}
