// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Challenge.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

public class Challenge extends ASN1Object
{

    private Challenge(ASN1Sequence seq)
    {
        int index = 0;
        if(seq.size() == 3)
            owf = AlgorithmIdentifier.getInstance(seq.getObjectAt(index++));
        witness = ASN1OctetString.getInstance(seq.getObjectAt(index++));
        challenge = ASN1OctetString.getInstance(seq.getObjectAt(index));
    }

    public static Challenge getInstance(Object o)
    {
        if(o instanceof Challenge)
            return (Challenge)o;
        if(o != null)
            return new Challenge(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public Challenge(byte witness[], byte challenge[])
    {
        this(null, witness, challenge);
    }

    public Challenge(AlgorithmIdentifier owf, byte witness[], byte challenge[])
    {
        this.owf = owf;
        this.witness = new DEROctetString(witness);
        this.challenge = new DEROctetString(challenge);
    }

    public AlgorithmIdentifier getOwf()
    {
        return owf;
    }

    public byte[] getWitness()
    {
        return witness.getOctets();
    }

    public byte[] getChallenge()
    {
        return challenge.getOctets();
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        addOptional(v, owf);
        v.add(witness);
        v.add(challenge);
        return new DERSequence(v);
    }

    private void addOptional(ASN1EncodableVector v, ASN1Encodable obj)
    {
        if(obj != null)
            v.add(obj);
    }

    private AlgorithmIdentifier owf;
    private ASN1OctetString witness;
    private ASN1OctetString challenge;
}
