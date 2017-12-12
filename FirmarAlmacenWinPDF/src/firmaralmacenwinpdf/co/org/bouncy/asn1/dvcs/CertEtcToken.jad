// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertEtcToken.java

package co.org.bouncy.asn1.dvcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cmp.PKIStatusInfo;
import co.org.bouncy.asn1.cms.ContentInfo;
import co.org.bouncy.asn1.ess.ESSCertID;
import co.org.bouncy.asn1.ocsp.*;
import co.org.bouncy.asn1.smime.SMIMECapabilities;
import co.org.bouncy.asn1.x509.*;

public class CertEtcToken extends ASN1Object
    implements ASN1Choice
{

    public CertEtcToken(int tagNo, ASN1Encodable value)
    {
        this.tagNo = tagNo;
        this.value = value;
    }

    public CertEtcToken(Extension extension)
    {
        tagNo = -1;
        this.extension = extension;
    }

    private CertEtcToken(ASN1TaggedObject choice)
    {
        tagNo = choice.getTagNo();
        switch(tagNo)
        {
        case 0: // '\0'
            value = Certificate.getInstance(choice, false);
            break;

        case 1: // '\001'
            value = ESSCertID.getInstance(choice.getObject());
            break;

        case 2: // '\002'
            value = PKIStatusInfo.getInstance(choice, false);
            break;

        case 3: // '\003'
            value = ContentInfo.getInstance(choice.getObject());
            break;

        case 4: // '\004'
            value = CertificateList.getInstance(choice, false);
            break;

        case 5: // '\005'
            value = CertStatus.getInstance(choice.getObject());
            break;

        case 6: // '\006'
            value = CertID.getInstance(choice, false);
            break;

        case 7: // '\007'
            value = OCSPResponse.getInstance(choice, false);
            break;

        case 8: // '\b'
            value = SMIMECapabilities.getInstance(choice.getObject());
            break;

        default:
            throw new IllegalArgumentException((new StringBuilder()).append("Unknown tag: ").append(tagNo).toString());
        }
    }

    public static CertEtcToken getInstance(Object obj)
    {
        if(obj instanceof CertEtcToken)
            return (CertEtcToken)obj;
        if(obj instanceof ASN1TaggedObject)
            return new CertEtcToken((ASN1TaggedObject)obj);
        if(obj != null)
            return new CertEtcToken(Extension.getInstance(obj));
        else
            return null;
    }

    public ASN1Primitive toASN1Primitive()
    {
        if(extension == null)
            return new DERTaggedObject(explicit[tagNo], tagNo, value);
        else
            return extension.toASN1Primitive();
    }

    public int getTagNo()
    {
        return tagNo;
    }

    public ASN1Encodable getValue()
    {
        return value;
    }

    public Extension getExtension()
    {
        return extension;
    }

    public String toString()
    {
        return (new StringBuilder()).append("CertEtcToken {\n").append(value).append("}\n").toString();
    }

    public static CertEtcToken[] arrayFromSequence(ASN1Sequence seq)
    {
        CertEtcToken tmp[] = new CertEtcToken[seq.size()];
        for(int i = 0; i != tmp.length; i++)
            tmp[i] = getInstance(seq.getObjectAt(i));

        return tmp;
    }

    public static final int TAG_CERTIFICATE = 0;
    public static final int TAG_ESSCERTID = 1;
    public static final int TAG_PKISTATUS = 2;
    public static final int TAG_ASSERTION = 3;
    public static final int TAG_CRL = 4;
    public static final int TAG_OCSPCERTSTATUS = 5;
    public static final int TAG_OCSPCERTID = 6;
    public static final int TAG_OCSPRESPONSE = 7;
    public static final int TAG_CAPABILITIES = 8;
    private static final boolean explicit[] = {
        false, true, false, true, false, true, false, false, true
    };
    private int tagNo;
    private ASN1Encodable value;
    private Extension extension;

}
