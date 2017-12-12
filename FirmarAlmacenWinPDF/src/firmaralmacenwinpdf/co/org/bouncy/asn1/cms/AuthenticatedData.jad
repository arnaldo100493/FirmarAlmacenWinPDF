// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AuthenticatedData.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            OriginatorInfo, ContentInfo

public class AuthenticatedData extends ASN1Object
{

    public AuthenticatedData(OriginatorInfo originatorInfo, ASN1Set recipientInfos, AlgorithmIdentifier macAlgorithm, AlgorithmIdentifier digestAlgorithm, ContentInfo encapsulatedContent, ASN1Set authAttrs, ASN1OctetString mac, 
            ASN1Set unauthAttrs)
    {
        if((digestAlgorithm != null || authAttrs != null) && (digestAlgorithm == null || authAttrs == null))
        {
            throw new IllegalArgumentException("digestAlgorithm and authAttrs must be set together");
        } else
        {
            version = new ASN1Integer(calculateVersion(originatorInfo));
            this.originatorInfo = originatorInfo;
            this.macAlgorithm = macAlgorithm;
            this.digestAlgorithm = digestAlgorithm;
            this.recipientInfos = recipientInfos;
            encapsulatedContentInfo = encapsulatedContent;
            this.authAttrs = authAttrs;
            this.mac = mac;
            this.unauthAttrs = unauthAttrs;
            return;
        }
    }

    public AuthenticatedData(ASN1Sequence seq)
    {
        int index = 0;
        version = (ASN1Integer)seq.getObjectAt(index++);
        Object tmp = seq.getObjectAt(index++);
        if(tmp instanceof ASN1TaggedObject)
        {
            originatorInfo = OriginatorInfo.getInstance((ASN1TaggedObject)tmp, false);
            tmp = seq.getObjectAt(index++);
        }
        recipientInfos = ASN1Set.getInstance(tmp);
        macAlgorithm = AlgorithmIdentifier.getInstance(seq.getObjectAt(index++));
        tmp = seq.getObjectAt(index++);
        if(tmp instanceof ASN1TaggedObject)
        {
            digestAlgorithm = AlgorithmIdentifier.getInstance((ASN1TaggedObject)tmp, false);
            tmp = seq.getObjectAt(index++);
        }
        encapsulatedContentInfo = ContentInfo.getInstance(tmp);
        tmp = seq.getObjectAt(index++);
        if(tmp instanceof ASN1TaggedObject)
        {
            authAttrs = ASN1Set.getInstance((ASN1TaggedObject)tmp, false);
            tmp = seq.getObjectAt(index++);
        }
        mac = ASN1OctetString.getInstance(tmp);
        if(seq.size() > index)
            unauthAttrs = ASN1Set.getInstance((ASN1TaggedObject)seq.getObjectAt(index), false);
    }

    public static AuthenticatedData getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static AuthenticatedData getInstance(Object obj)
    {
        if(obj == null || (obj instanceof AuthenticatedData))
            return (AuthenticatedData)obj;
        if(obj instanceof ASN1Sequence)
            return new AuthenticatedData((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid AuthenticatedData: ").append(obj.getClass().getName()).toString());
    }

    public ASN1Integer getVersion()
    {
        return version;
    }

    public OriginatorInfo getOriginatorInfo()
    {
        return originatorInfo;
    }

    public ASN1Set getRecipientInfos()
    {
        return recipientInfos;
    }

    public AlgorithmIdentifier getMacAlgorithm()
    {
        return macAlgorithm;
    }

    public AlgorithmIdentifier getDigestAlgorithm()
    {
        return digestAlgorithm;
    }

    public ContentInfo getEncapsulatedContentInfo()
    {
        return encapsulatedContentInfo;
    }

    public ASN1Set getAuthAttrs()
    {
        return authAttrs;
    }

    public ASN1OctetString getMac()
    {
        return mac;
    }

    public ASN1Set getUnauthAttrs()
    {
        return unauthAttrs;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(version);
        if(originatorInfo != null)
            v.add(new DERTaggedObject(false, 0, originatorInfo));
        v.add(recipientInfos);
        v.add(macAlgorithm);
        if(digestAlgorithm != null)
            v.add(new DERTaggedObject(false, 1, digestAlgorithm));
        v.add(encapsulatedContentInfo);
        if(authAttrs != null)
            v.add(new DERTaggedObject(false, 2, authAttrs));
        v.add(mac);
        if(unauthAttrs != null)
            v.add(new DERTaggedObject(false, 3, unauthAttrs));
        return new BERSequence(v);
    }

    public static int calculateVersion(OriginatorInfo origInfo)
    {
        int ver;
label0:
        {
            if(origInfo == null)
                return 0;
            ver = 0;
            ASN1TaggedObject tag;
label1:
            do
            {
                for(Enumeration e = origInfo.getCertificates().getObjects(); e.hasMoreElements();)
                {
                    Object obj = e.nextElement();
                    if(obj instanceof ASN1TaggedObject)
                    {
                        tag = (ASN1TaggedObject)obj;
                        if(tag.getTagNo() != 2)
                            continue label1;
                        ver = 1;
                    }
                }

                break label0;
            } while(tag.getTagNo() != 3);
            ver = 3;
        }
label2:
        {
            if(origInfo.getCRLs() == null)
                break label2;
            Enumeration e = origInfo.getCRLs().getObjects();
            ASN1TaggedObject tag;
            do
            {
                Object obj;
                do
                {
                    if(!e.hasMoreElements())
                        break label2;
                    obj = e.nextElement();
                } while(!(obj instanceof ASN1TaggedObject));
                tag = (ASN1TaggedObject)obj;
            } while(tag.getTagNo() != 1);
            ver = 3;
        }
        return ver;
    }

    private ASN1Integer version;
    private OriginatorInfo originatorInfo;
    private ASN1Set recipientInfos;
    private AlgorithmIdentifier macAlgorithm;
    private AlgorithmIdentifier digestAlgorithm;
    private ContentInfo encapsulatedContentInfo;
    private ASN1Set authAttrs;
    private ASN1OctetString mac;
    private ASN1Set unauthAttrs;
}
