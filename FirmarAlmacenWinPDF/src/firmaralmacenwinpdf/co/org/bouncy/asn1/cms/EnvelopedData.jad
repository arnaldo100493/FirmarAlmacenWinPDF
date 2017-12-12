// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EnvelopedData.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            OriginatorInfo, EncryptedContentInfo, RecipientInfo, Attributes

public class EnvelopedData extends ASN1Object
{

    public EnvelopedData(OriginatorInfo originatorInfo, ASN1Set recipientInfos, EncryptedContentInfo encryptedContentInfo, ASN1Set unprotectedAttrs)
    {
        version = new ASN1Integer(calculateVersion(originatorInfo, recipientInfos, unprotectedAttrs));
        this.originatorInfo = originatorInfo;
        this.recipientInfos = recipientInfos;
        this.encryptedContentInfo = encryptedContentInfo;
        this.unprotectedAttrs = unprotectedAttrs;
    }

    public EnvelopedData(OriginatorInfo originatorInfo, ASN1Set recipientInfos, EncryptedContentInfo encryptedContentInfo, Attributes unprotectedAttrs)
    {
        version = new ASN1Integer(calculateVersion(originatorInfo, recipientInfos, ASN1Set.getInstance(unprotectedAttrs)));
        this.originatorInfo = originatorInfo;
        this.recipientInfos = recipientInfos;
        this.encryptedContentInfo = encryptedContentInfo;
        this.unprotectedAttrs = ASN1Set.getInstance(unprotectedAttrs);
    }

    /**
     * @deprecated Method EnvelopedData is deprecated
     */

    public EnvelopedData(ASN1Sequence seq)
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
        encryptedContentInfo = EncryptedContentInfo.getInstance(seq.getObjectAt(index++));
        if(seq.size() > index)
            unprotectedAttrs = ASN1Set.getInstance((ASN1TaggedObject)seq.getObjectAt(index), false);
    }

    public static EnvelopedData getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static EnvelopedData getInstance(Object obj)
    {
        if(obj instanceof EnvelopedData)
            return (EnvelopedData)obj;
        if(obj != null)
            return new EnvelopedData(ASN1Sequence.getInstance(obj));
        else
            return null;
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

    public EncryptedContentInfo getEncryptedContentInfo()
    {
        return encryptedContentInfo;
    }

    public ASN1Set getUnprotectedAttrs()
    {
        return unprotectedAttrs;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(version);
        if(originatorInfo != null)
            v.add(new DERTaggedObject(false, 0, originatorInfo));
        v.add(recipientInfos);
        v.add(encryptedContentInfo);
        if(unprotectedAttrs != null)
            v.add(new DERTaggedObject(false, 1, unprotectedAttrs));
        return new BERSequence(v);
    }

    public static int calculateVersion(OriginatorInfo originatorInfo, ASN1Set recipientInfos, ASN1Set unprotectedAttrs)
    {
        int version;
label0:
        {
            if(originatorInfo != null || unprotectedAttrs != null)
            {
                version = 2;
                break label0;
            }
            version = 0;
            Enumeration e = recipientInfos.getObjects();
            RecipientInfo ri;
            do
            {
                if(!e.hasMoreElements())
                    break label0;
                ri = RecipientInfo.getInstance(e.nextElement());
            } while(ri.getVersion().getValue().intValue() == version);
            version = 2;
        }
        return version;
    }

    private ASN1Integer version;
    private OriginatorInfo originatorInfo;
    private ASN1Set recipientInfos;
    private EncryptedContentInfo encryptedContentInfo;
    private ASN1Set unprotectedAttrs;
}
