// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AuthEnvelopedData.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            OriginatorInfo, EncryptedContentInfo

public class AuthEnvelopedData extends ASN1Object
{

    public AuthEnvelopedData(OriginatorInfo originatorInfo, ASN1Set recipientInfos, EncryptedContentInfo authEncryptedContentInfo, ASN1Set authAttrs, ASN1OctetString mac, ASN1Set unauthAttrs)
    {
        version = new ASN1Integer(0L);
        this.originatorInfo = originatorInfo;
        this.recipientInfos = recipientInfos;
        this.authEncryptedContentInfo = authEncryptedContentInfo;
        this.authAttrs = authAttrs;
        this.mac = mac;
        this.unauthAttrs = unauthAttrs;
    }

    public AuthEnvelopedData(ASN1Sequence seq)
    {
        int index = 0;
        ASN1Primitive tmp = seq.getObjectAt(index++).toASN1Primitive();
        version = (ASN1Integer)tmp;
        tmp = seq.getObjectAt(index++).toASN1Primitive();
        if(tmp instanceof ASN1TaggedObject)
        {
            originatorInfo = OriginatorInfo.getInstance((ASN1TaggedObject)tmp, false);
            tmp = seq.getObjectAt(index++).toASN1Primitive();
        }
        recipientInfos = ASN1Set.getInstance(tmp);
        tmp = seq.getObjectAt(index++).toASN1Primitive();
        authEncryptedContentInfo = EncryptedContentInfo.getInstance(tmp);
        tmp = seq.getObjectAt(index++).toASN1Primitive();
        if(tmp instanceof ASN1TaggedObject)
        {
            authAttrs = ASN1Set.getInstance((ASN1TaggedObject)tmp, false);
            tmp = seq.getObjectAt(index++).toASN1Primitive();
        }
        mac = ASN1OctetString.getInstance(tmp);
        if(seq.size() > index)
        {
            tmp = seq.getObjectAt(index++).toASN1Primitive();
            unauthAttrs = ASN1Set.getInstance((ASN1TaggedObject)tmp, false);
        }
    }

    public static AuthEnvelopedData getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static AuthEnvelopedData getInstance(Object obj)
    {
        if(obj == null || (obj instanceof AuthEnvelopedData))
            return (AuthEnvelopedData)obj;
        if(obj instanceof ASN1Sequence)
            return new AuthEnvelopedData((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid AuthEnvelopedData: ").append(obj.getClass().getName()).toString());
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

    public EncryptedContentInfo getAuthEncryptedContentInfo()
    {
        return authEncryptedContentInfo;
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
        v.add(authEncryptedContentInfo);
        if(authAttrs != null)
            v.add(new DERTaggedObject(false, 1, authAttrs));
        v.add(mac);
        if(unauthAttrs != null)
            v.add(new DERTaggedObject(false, 2, unauthAttrs));
        return new BERSequence(v);
    }

    private ASN1Integer version;
    private OriginatorInfo originatorInfo;
    private ASN1Set recipientInfos;
    private EncryptedContentInfo authEncryptedContentInfo;
    private ASN1Set authAttrs;
    private ASN1OctetString mac;
    private ASN1Set unauthAttrs;
}
