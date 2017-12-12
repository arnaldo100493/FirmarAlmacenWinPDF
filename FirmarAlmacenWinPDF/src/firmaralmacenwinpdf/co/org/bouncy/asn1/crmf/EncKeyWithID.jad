// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EncKeyWithID.java

package co.org.bouncy.asn1.crmf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.GeneralName;

public class EncKeyWithID extends ASN1Object
{

    public static EncKeyWithID getInstance(Object o)
    {
        if(o instanceof EncKeyWithID)
            return (EncKeyWithID)o;
        if(o != null)
            return new EncKeyWithID(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    private EncKeyWithID(ASN1Sequence seq)
    {
        privKeyInfo = PrivateKeyInfo.getInstance(seq.getObjectAt(0));
        if(seq.size() > 1)
        {
            if(!(seq.getObjectAt(1) instanceof DERUTF8String))
                identifier = GeneralName.getInstance(seq.getObjectAt(1));
            else
                identifier = seq.getObjectAt(1);
        } else
        {
            identifier = null;
        }
    }

    public EncKeyWithID(PrivateKeyInfo privKeyInfo)
    {
        this.privKeyInfo = privKeyInfo;
        identifier = null;
    }

    public EncKeyWithID(PrivateKeyInfo privKeyInfo, DERUTF8String str)
    {
        this.privKeyInfo = privKeyInfo;
        identifier = str;
    }

    public EncKeyWithID(PrivateKeyInfo privKeyInfo, GeneralName generalName)
    {
        this.privKeyInfo = privKeyInfo;
        identifier = generalName;
    }

    public PrivateKeyInfo getPrivateKey()
    {
        return privKeyInfo;
    }

    public boolean hasIdentifier()
    {
        return identifier != null;
    }

    public boolean isIdentifierUTF8String()
    {
        return identifier instanceof DERUTF8String;
    }

    public ASN1Encodable getIdentifier()
    {
        return identifier;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(privKeyInfo);
        if(identifier != null)
            v.add(identifier);
        return new DERSequence(v);
    }

    private final PrivateKeyInfo privKeyInfo;
    private final ASN1Encodable identifier;
}
