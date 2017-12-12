// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EncryptedData.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            EncryptedContentInfo

public class EncryptedData extends ASN1Object
{

    public static EncryptedData getInstance(Object o)
    {
        if(o instanceof EncryptedData)
            return (EncryptedData)o;
        if(o != null)
            return new EncryptedData(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public EncryptedData(EncryptedContentInfo encInfo)
    {
        this(encInfo, null);
    }

    public EncryptedData(EncryptedContentInfo encInfo, ASN1Set unprotectedAttrs)
    {
        version = new ASN1Integer(unprotectedAttrs != null ? 2L : 0L);
        encryptedContentInfo = encInfo;
        this.unprotectedAttrs = unprotectedAttrs;
    }

    private EncryptedData(ASN1Sequence seq)
    {
        version = ASN1Integer.getInstance(seq.getObjectAt(0));
        encryptedContentInfo = EncryptedContentInfo.getInstance(seq.getObjectAt(1));
        if(seq.size() == 3)
            unprotectedAttrs = ASN1Set.getInstance(seq.getObjectAt(2));
    }

    public ASN1Integer getVersion()
    {
        return version;
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
        v.add(encryptedContentInfo);
        if(unprotectedAttrs != null)
            v.add(new BERTaggedObject(false, 1, unprotectedAttrs));
        return new BERSequence(v);
    }

    private ASN1Integer version;
    private EncryptedContentInfo encryptedContentInfo;
    private ASN1Set unprotectedAttrs;
}
