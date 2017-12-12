// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignedData.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            ContentInfo, CMSObjectIdentifiers, SignerInfo

public class SignedData extends ASN1Object
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

    public SignedData(ASN1Set digestAlgorithms, ContentInfo contentInfo, ASN1Set certificates, ASN1Set crls, ASN1Set signerInfos)
    {
        version = calculateVersion(contentInfo.getContentType(), certificates, crls, signerInfos);
        this.digestAlgorithms = digestAlgorithms;
        this.contentInfo = contentInfo;
        this.certificates = certificates;
        this.crls = crls;
        this.signerInfos = signerInfos;
        crlsBer = crls instanceof BERSet;
        certsBer = certificates instanceof BERSet;
    }

    private ASN1Integer calculateVersion(ASN1ObjectIdentifier contentOid, ASN1Set certs, ASN1Set crls, ASN1Set signerInfs)
    {
        boolean otherCert = false;
        boolean otherCrl = false;
        boolean attrCertV1Found = false;
        boolean attrCertV2Found = false;
        if(certs != null)
        {
            Enumeration en = certs.getObjects();
            do
            {
                if(!en.hasMoreElements())
                    break;
                Object obj = en.nextElement();
                if(obj instanceof ASN1TaggedObject)
                {
                    ASN1TaggedObject tagged = ASN1TaggedObject.getInstance(obj);
                    if(tagged.getTagNo() == 1)
                        attrCertV1Found = true;
                    else
                    if(tagged.getTagNo() == 2)
                        attrCertV2Found = true;
                    else
                    if(tagged.getTagNo() == 3)
                        otherCert = true;
                }
            } while(true);
        }
        if(otherCert)
            return new ASN1Integer(5L);
        if(crls != null)
        {
            Enumeration en = crls.getObjects();
            do
            {
                if(!en.hasMoreElements())
                    break;
                Object obj = en.nextElement();
                if(obj instanceof ASN1TaggedObject)
                    otherCrl = true;
            } while(true);
        }
        if(otherCrl)
            return VERSION_5;
        if(attrCertV2Found)
            return VERSION_4;
        if(attrCertV1Found)
            return VERSION_3;
        if(checkForVersion3(signerInfs))
            return VERSION_3;
        if(!CMSObjectIdentifiers.data.equals(contentOid))
            return VERSION_3;
        else
            return VERSION_1;
    }

    private boolean checkForVersion3(ASN1Set signerInfs)
    {
        for(Enumeration e = signerInfs.getObjects(); e.hasMoreElements();)
        {
            SignerInfo s = SignerInfo.getInstance(e.nextElement());
            if(s.getVersion().getValue().intValue() == 3)
                return true;
        }

        return false;
    }

    private SignedData(ASN1Sequence seq)
    {
        Enumeration e = seq.getObjects();
        version = ASN1Integer.getInstance(e.nextElement());
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
                        certsBer = tagged instanceof BERTaggedObject;
                        certificates = ASN1Set.getInstance(tagged, false);
                        break;

                    case 1: // '\001'
                        crlsBer = tagged instanceof BERTaggedObject;
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

    public ContentInfo getEncapContentInfo()
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
            if(certsBer)
                v.add(new BERTaggedObject(false, 0, certificates));
            else
                v.add(new DERTaggedObject(false, 0, certificates));
        if(crls != null)
            if(crlsBer)
                v.add(new BERTaggedObject(false, 1, crls));
            else
                v.add(new DERTaggedObject(false, 1, crls));
        v.add(signerInfos);
        return new BERSequence(v);
    }

    private static final ASN1Integer VERSION_1 = new ASN1Integer(1L);
    private static final ASN1Integer VERSION_3 = new ASN1Integer(3L);
    private static final ASN1Integer VERSION_4 = new ASN1Integer(4L);
    private static final ASN1Integer VERSION_5 = new ASN1Integer(5L);
    private ASN1Integer version;
    private ASN1Set digestAlgorithms;
    private ContentInfo contentInfo;
    private ASN1Set certificates;
    private ASN1Set crls;
    private ASN1Set signerInfos;
    private boolean certsBer;
    private boolean crlsBer;

}
