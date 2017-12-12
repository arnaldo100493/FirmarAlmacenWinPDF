// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OtherHash.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;

// Referenced classes of package co.org.bouncy.asn1.esf:
//            OtherHashAlgAndValue

public class OtherHash extends ASN1Object
    implements ASN1Choice
{

    public static OtherHash getInstance(Object obj)
    {
        if(obj instanceof OtherHash)
            return (OtherHash)obj;
        if(obj instanceof ASN1OctetString)
            return new OtherHash((ASN1OctetString)obj);
        else
            return new OtherHash(OtherHashAlgAndValue.getInstance(obj));
    }

    private OtherHash(ASN1OctetString sha1Hash)
    {
        this.sha1Hash = sha1Hash;
    }

    public OtherHash(OtherHashAlgAndValue otherHash)
    {
        this.otherHash = otherHash;
    }

    public OtherHash(byte sha1Hash[])
    {
        this.sha1Hash = new DEROctetString(sha1Hash);
    }

    public AlgorithmIdentifier getHashAlgorithm()
    {
        if(null == otherHash)
            return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1);
        else
            return otherHash.getHashAlgorithm();
    }

    public byte[] getHashValue()
    {
        if(null == otherHash)
            return sha1Hash.getOctets();
        else
            return otherHash.getHashValue().getOctets();
    }

    public ASN1Primitive toASN1Primitive()
    {
        if(null == otherHash)
            return sha1Hash;
        else
            return otherHash.toASN1Primitive();
    }

    private ASN1OctetString sha1Hash;
    private OtherHashAlgAndValue otherHash;
}
