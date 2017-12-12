// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PublicKeyDataObject.java

package co.org.bouncy.asn1.eac;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.eac:
//            ECDSAPublicKey, RSAPublicKey, EACObjectIdentifiers

public abstract class PublicKeyDataObject extends ASN1Object
{

    public PublicKeyDataObject()
    {
    }

    public static PublicKeyDataObject getInstance(Object obj)
    {
        if(obj instanceof PublicKeyDataObject)
            return (PublicKeyDataObject)obj;
        if(obj != null)
        {
            ASN1Sequence seq = ASN1Sequence.getInstance(obj);
            ASN1ObjectIdentifier usage = ASN1ObjectIdentifier.getInstance(seq.getObjectAt(0));
            if(usage.on(EACObjectIdentifiers.id_TA_ECDSA))
                return new ECDSAPublicKey(seq);
            else
                return new RSAPublicKey(seq);
        } else
        {
            return null;
        }
    }

    public abstract ASN1ObjectIdentifier getUsage();
}
