// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKIPublicationInfo.java

package co.org.bouncy.asn1.crmf;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.crmf:
//            SinglePubInfo

public class PKIPublicationInfo extends ASN1Object
{

    private PKIPublicationInfo(ASN1Sequence seq)
    {
        action = ASN1Integer.getInstance(seq.getObjectAt(0));
        pubInfos = ASN1Sequence.getInstance(seq.getObjectAt(1));
    }

    public static PKIPublicationInfo getInstance(Object o)
    {
        if(o instanceof PKIPublicationInfo)
            return (PKIPublicationInfo)o;
        if(o != null)
            return new PKIPublicationInfo(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public ASN1Integer getAction()
    {
        return action;
    }

    public SinglePubInfo[] getPubInfos()
    {
        if(pubInfos == null)
            return null;
        SinglePubInfo results[] = new SinglePubInfo[pubInfos.size()];
        for(int i = 0; i != results.length; i++)
            results[i] = SinglePubInfo.getInstance(pubInfos.getObjectAt(i));

        return results;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(action);
        v.add(pubInfos);
        return new DERSequence(v);
    }

    private ASN1Integer action;
    private ASN1Sequence pubInfos;
}
