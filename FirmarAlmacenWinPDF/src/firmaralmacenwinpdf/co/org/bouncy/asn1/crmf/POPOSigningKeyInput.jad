// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   POPOSigningKeyInput.java

package co.org.bouncy.asn1.crmf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.GeneralName;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;

// Referenced classes of package co.org.bouncy.asn1.crmf:
//            PKMACValue

public class POPOSigningKeyInput extends ASN1Object
{

    private POPOSigningKeyInput(ASN1Sequence seq)
    {
        ASN1Encodable authInfo = seq.getObjectAt(0);
        if(authInfo instanceof ASN1TaggedObject)
        {
            ASN1TaggedObject tagObj = (ASN1TaggedObject)authInfo;
            if(tagObj.getTagNo() != 0)
                throw new IllegalArgumentException((new StringBuilder()).append("Unknown authInfo tag: ").append(tagObj.getTagNo()).toString());
            sender = GeneralName.getInstance(tagObj.getObject());
        } else
        {
            publicKeyMAC = PKMACValue.getInstance(authInfo);
        }
        publicKey = SubjectPublicKeyInfo.getInstance(seq.getObjectAt(1));
    }

    public static POPOSigningKeyInput getInstance(Object o)
    {
        if(o instanceof POPOSigningKeyInput)
            return (POPOSigningKeyInput)o;
        if(o != null)
            return new POPOSigningKeyInput(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public POPOSigningKeyInput(GeneralName sender, SubjectPublicKeyInfo spki)
    {
        this.sender = sender;
        publicKey = spki;
    }

    public POPOSigningKeyInput(PKMACValue pkmac, SubjectPublicKeyInfo spki)
    {
        publicKeyMAC = pkmac;
        publicKey = spki;
    }

    public GeneralName getSender()
    {
        return sender;
    }

    public PKMACValue getPublicKeyMAC()
    {
        return publicKeyMAC;
    }

    public SubjectPublicKeyInfo getPublicKey()
    {
        return publicKey;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(sender != null)
            v.add(new DERTaggedObject(false, 0, sender));
        else
            v.add(publicKeyMAC);
        v.add(publicKey);
        return new DERSequence(v);
    }

    private GeneralName sender;
    private PKMACValue publicKeyMAC;
    private SubjectPublicKeyInfo publicKey;
}
