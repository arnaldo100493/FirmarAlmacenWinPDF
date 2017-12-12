// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DigestInfo.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            AlgorithmIdentifier

public class DigestInfo extends ASN1Object
{

    public static DigestInfo getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static DigestInfo getInstance(Object obj)
    {
        if(obj instanceof DigestInfo)
            return (DigestInfo)obj;
        if(obj != null)
            return new DigestInfo(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public DigestInfo(AlgorithmIdentifier algId, byte digest[])
    {
        this.digest = digest;
        this.algId = algId;
    }

    public DigestInfo(ASN1Sequence obj)
    {
        Enumeration e = obj.getObjects();
        algId = AlgorithmIdentifier.getInstance(e.nextElement());
        digest = ASN1OctetString.getInstance(e.nextElement()).getOctets();
    }

    public AlgorithmIdentifier getAlgorithmId()
    {
        return algId;
    }

    public byte[] getDigest()
    {
        return digest;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(algId);
        v.add(new DEROctetString(digest));
        return new DERSequence(v);
    }

    private byte digest[];
    private AlgorithmIdentifier algId;
}
