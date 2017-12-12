// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignedData.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.pkcs:
//            PKCSObjectIdentifiers, ContentInfo

public class SignedData extends ASN1Object
    implements PKCSObjectIdentifiers
{

    public static SignedData getInstance(Object o)
    {
        if(o instanceof SignedData)
            return (SignedData)o;
        if(o != null)
            return new SignedData(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public SignedData(ASN1Integer _version, ASN1Set _digestAlgorithms, ContentInfo _contentInfo, ASN1Set _certificates, ASN1Set _crls, ASN1Set _signerInfos)
    {
        version = _version;
        digestAlgorithms = _digestAlgorithms;
        contentInfo = _contentInfo;
        certificates = _certificates;
        crls = _crls;
        signerInfos = _signerInfos;
    }

    public SignedData(ASN1Sequence seq)
    {
        Enumeration e = seq.getObjects();
        version = (ASN1Integer)e.nextElement();
        digestAlgorithms = (ASN1Set)e.nextElement();
        contentInfo = ContentInfo.getInstance(e.nextElement());
        do
        {
            while(e.hasMoreElements()) 
            {
                ASN1Primitive o = (ASN1Primitive)e.nextElement();
                if(o instanceof ASN1TaggedObject)
                {
                    ASN1TaggedObject tagged = (ASN1TaggedObject)o;
                    switch(tagged.getTagNo())
                    {
                    case 0: // '\0'
                        certificates = ASN1Set.getInstance(tagged, false);
                        break;

                    case 1: // '\001'
                        crls = ASN1Set.getInstance(tagged, false);
                        break;

                    default:
                        throw new IllegalArgumentException((new StringBuilder()).append("unknown tag value ").append(tagged.getTagNo()).toString());
                    }
                } else
                {
                    signerInfos = (ASN1Set)o;
                }
            }
            return;
        } while(true);
    }

    public ASN1Integer getVersion()
    {
        return version;
    }

    public ASN1Set getDigestAlgorithms()
    {
        return digestAlgorithms;
    }

    public ContentInfo getContentInfo()
    {
        return contentInfo;
    }

    public ASN1Set getCertificates()
    {
        return certificates;
    }

    public ASN1Set getCRLs()
    {
        return crls;
    }

    public ASN1Set getSignerInfos()
    {
        return signerInfos;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(version);
        v.add(digestAlgorithms);
        v.add(contentInfo);
        if(certificates != null)
            v.add(new DERTaggedObject(false, 0, certificates));
        if(crls != null)
            v.add(new DERTaggedObject(false, 1, crls));
        v.add(signerInfos);
        return new BERSequence(v);
    }

    private ASN1Integer version;
    private ASN1Set digestAlgorithms;
    private ContentInfo contentInfo;
    private ASN1Set certificates;
    private ASN1Set crls;
    private ASN1Set signerInfos;
}
