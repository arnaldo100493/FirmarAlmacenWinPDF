// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OtherInfo.java

package co.org.bouncy.asn1.x9;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.x9:
//            KeySpecificInfo

public class OtherInfo extends ASN1Object
{

    public OtherInfo(KeySpecificInfo keyInfo, ASN1OctetString partyAInfo, ASN1OctetString suppPubInfo)
    {
        this.keyInfo = keyInfo;
        this.partyAInfo = partyAInfo;
        this.suppPubInfo = suppPubInfo;
    }

    public OtherInfo(ASN1Sequence seq)
    {
        Enumeration e = seq.getObjects();
        keyInfo = new KeySpecificInfo((ASN1Sequence)e.nextElement());
        do
        {
            if(!e.hasMoreElements())
                break;
            DERTaggedObject o = (DERTaggedObject)e.nextElement();
            if(o.getTagNo() == 0)
                partyAInfo = (ASN1OctetString)o.getObject();
            else
            if(o.getTagNo() == 2)
                suppPubInfo = (ASN1OctetString)o.getObject();
        } while(true);
    }

    public KeySpecificInfo getKeyInfo()
    {
        return keyInfo;
    }

    public ASN1OctetString getPartyAInfo()
    {
        return partyAInfo;
    }

    public ASN1OctetString getSuppPubInfo()
    {
        return suppPubInfo;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(keyInfo);
        if(partyAInfo != null)
            v.add(new DERTaggedObject(0, partyAInfo));
        v.add(new DERTaggedObject(2, suppPubInfo));
        return new DERSequence(v);
    }

    private KeySpecificInfo keyInfo;
    private ASN1OctetString partyAInfo;
    private ASN1OctetString suppPubInfo;
}
