// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PublicKeyAndChallenge.java

package co.org.bouncy.asn1.mozilla;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;

public class PublicKeyAndChallenge extends ASN1Object
{

    public static PublicKeyAndChallenge getInstance(Object obj)
    {
        if(obj instanceof PublicKeyAndChallenge)
            return (PublicKeyAndChallenge)obj;
        if(obj != null)
            return new PublicKeyAndChallenge(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private PublicKeyAndChallenge(ASN1Sequence seq)
    {
        pkacSeq = seq;
        spki = SubjectPublicKeyInfo.getInstance(seq.getObjectAt(0));
        challenge = DERIA5String.getInstance(seq.getObjectAt(1));
    }

    public ASN1Primitive toASN1Primitive()
    {
        return pkacSeq;
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo()
    {
        return spki;
    }

    public DERIA5String getChallenge()
    {
        return challenge;
    }

    private ASN1Sequence pkacSeq;
    private SubjectPublicKeyInfo spki;
    private DERIA5String challenge;
}
