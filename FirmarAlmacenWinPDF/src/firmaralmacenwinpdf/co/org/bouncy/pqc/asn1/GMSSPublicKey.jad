// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GMSSPublicKey.java

package co.org.bouncy.pqc.asn1;

import co.org.bouncy.asn1.*;
import co.org.bouncy.util.Arrays;

public class GMSSPublicKey extends ASN1Object
{

    private GMSSPublicKey(ASN1Sequence seq)
    {
        if(seq.size() != 2)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("size of seq = ").append(seq.size()).toString());
        } else
        {
            version = ASN1Integer.getInstance(seq.getObjectAt(0));
            publicKey = ASN1OctetString.getInstance(seq.getObjectAt(1)).getOctets();
            return;
        }
    }

    public GMSSPublicKey(byte publicKeyBytes[])
    {
        version = new ASN1Integer(0L);
        publicKey = publicKeyBytes;
    }

    public static GMSSPublicKey getInstance(Object o)
    {
        if(o instanceof GMSSPublicKey)
            return (GMSSPublicKey)o;
        if(o != null)
            return new GMSSPublicKey(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public byte[] getPublicKey()
    {
        return Arrays.clone(publicKey);
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(version);
        v.add(new DEROctetString(publicKey));
        return new DERSequence(v);
    }

    private ASN1Integer version;
    private byte publicKey[];
}
