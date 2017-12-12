// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GOST3410PublicKeyAlgParameters.java

package co.org.bouncy.asn1.cryptopro;

import co.org.bouncy.asn1.*;

public class GOST3410PublicKeyAlgParameters extends ASN1Object
{

    public static GOST3410PublicKeyAlgParameters getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static GOST3410PublicKeyAlgParameters getInstance(Object obj)
    {
        if(obj instanceof GOST3410PublicKeyAlgParameters)
            return (GOST3410PublicKeyAlgParameters)obj;
        if(obj != null)
            return new GOST3410PublicKeyAlgParameters(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public GOST3410PublicKeyAlgParameters(ASN1ObjectIdentifier publicKeyParamSet, ASN1ObjectIdentifier digestParamSet)
    {
        this.publicKeyParamSet = publicKeyParamSet;
        this.digestParamSet = digestParamSet;
        encryptionParamSet = null;
    }

    public GOST3410PublicKeyAlgParameters(ASN1ObjectIdentifier publicKeyParamSet, ASN1ObjectIdentifier digestParamSet, ASN1ObjectIdentifier encryptionParamSet)
    {
        this.publicKeyParamSet = publicKeyParamSet;
        this.digestParamSet = digestParamSet;
        this.encryptionParamSet = encryptionParamSet;
    }

    public GOST3410PublicKeyAlgParameters(ASN1Sequence seq)
    {
        publicKeyParamSet = (ASN1ObjectIdentifier)seq.getObjectAt(0);
        digestParamSet = (ASN1ObjectIdentifier)seq.getObjectAt(1);
        if(seq.size() > 2)
            encryptionParamSet = (ASN1ObjectIdentifier)seq.getObjectAt(2);
    }

    public ASN1ObjectIdentifier getPublicKeyParamSet()
    {
        return publicKeyParamSet;
    }

    public ASN1ObjectIdentifier getDigestParamSet()
    {
        return digestParamSet;
    }

    public ASN1ObjectIdentifier getEncryptionParamSet()
    {
        return encryptionParamSet;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(publicKeyParamSet);
        v.add(digestParamSet);
        if(encryptionParamSet != null)
            v.add(encryptionParamSet);
        return new DERSequence(v);
    }

    private ASN1ObjectIdentifier publicKeyParamSet;
    private ASN1ObjectIdentifier digestParamSet;
    private ASN1ObjectIdentifier encryptionParamSet;
}
