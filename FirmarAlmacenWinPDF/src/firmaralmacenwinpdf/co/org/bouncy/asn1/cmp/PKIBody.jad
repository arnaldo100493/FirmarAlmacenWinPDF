// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKIBody.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.crmf.CertReqMessages;
import co.org.bouncy.asn1.pkcs.CertificationRequest;

// Referenced classes of package co.org.bouncy.asn1.cmp:
//            CertRepMessage, POPODecKeyChallContent, POPODecKeyRespContent, KeyRecRepContent, 
//            RevReqContent, RevRepContent, CAKeyUpdAnnContent, CMPCertificate, 
//            RevAnnContent, CRLAnnContent, PKIConfirmContent, PKIMessages, 
//            GenMsgContent, GenRepContent, ErrorMsgContent, CertConfirmContent, 
//            PollReqContent, PollRepContent

public class PKIBody extends ASN1Object
    implements ASN1Choice
{

    public static PKIBody getInstance(Object o)
    {
        if(o == null || (o instanceof PKIBody))
            return (PKIBody)o;
        if(o instanceof ASN1TaggedObject)
            return new PKIBody((ASN1TaggedObject)o);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid object: ").append(o.getClass().getName()).toString());
    }

    private PKIBody(ASN1TaggedObject tagged)
    {
        tagNo = tagged.getTagNo();
        body = getBodyForType(tagNo, tagged.getObject());
    }

    public PKIBody(int type, ASN1Encodable content)
    {
        tagNo = type;
        body = getBodyForType(type, content);
    }

    private static ASN1Encodable getBodyForType(int type, ASN1Encodable o)
    {
        switch(type)
        {
        case 0: // '\0'
            return CertReqMessages.getInstance(o);

        case 1: // '\001'
            return CertRepMessage.getInstance(o);

        case 2: // '\002'
            return CertReqMessages.getInstance(o);

        case 3: // '\003'
            return CertRepMessage.getInstance(o);

        case 4: // '\004'
            return CertificationRequest.getInstance(o);

        case 5: // '\005'
            return POPODecKeyChallContent.getInstance(o);

        case 6: // '\006'
            return POPODecKeyRespContent.getInstance(o);

        case 7: // '\007'
            return CertReqMessages.getInstance(o);

        case 8: // '\b'
            return CertRepMessage.getInstance(o);

        case 9: // '\t'
            return CertReqMessages.getInstance(o);

        case 10: // '\n'
            return KeyRecRepContent.getInstance(o);

        case 11: // '\013'
            return RevReqContent.getInstance(o);

        case 12: // '\f'
            return RevRepContent.getInstance(o);

        case 13: // '\r'
            return CertReqMessages.getInstance(o);

        case 14: // '\016'
            return CertRepMessage.getInstance(o);

        case 15: // '\017'
            return CAKeyUpdAnnContent.getInstance(o);

        case 16: // '\020'
            return CMPCertificate.getInstance(o);

        case 17: // '\021'
            return RevAnnContent.getInstance(o);

        case 18: // '\022'
            return CRLAnnContent.getInstance(o);

        case 19: // '\023'
            return PKIConfirmContent.getInstance(o);

        case 20: // '\024'
            return PKIMessages.getInstance(o);

        case 21: // '\025'
            return GenMsgContent.getInstance(o);

        case 22: // '\026'
            return GenRepContent.getInstance(o);

        case 23: // '\027'
            return ErrorMsgContent.getInstance(o);

        case 24: // '\030'
            return CertConfirmContent.getInstance(o);

        case 25: // '\031'
            return PollReqContent.getInstance(o);

        case 26: // '\032'
            return PollRepContent.getInstance(o);
        }
        throw new IllegalArgumentException((new StringBuilder()).append("unknown tag number: ").append(type).toString());
    }

    public int getType()
    {
        return tagNo;
    }

    public ASN1Encodable getContent()
    {
        return body;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return new DERTaggedObject(true, tagNo, body);
    }

    public static final int TYPE_INIT_REQ = 0;
    public static final int TYPE_INIT_REP = 1;
    public static final int TYPE_CERT_REQ = 2;
    public static final int TYPE_CERT_REP = 3;
    public static final int TYPE_P10_CERT_REQ = 4;
    public static final int TYPE_POPO_CHALL = 5;
    public static final int TYPE_POPO_REP = 6;
    public static final int TYPE_KEY_UPDATE_REQ = 7;
    public static final int TYPE_KEY_UPDATE_REP = 8;
    public static final int TYPE_KEY_RECOVERY_REQ = 9;
    public static final int TYPE_KEY_RECOVERY_REP = 10;
    public static final int TYPE_REVOCATION_REQ = 11;
    public static final int TYPE_REVOCATION_REP = 12;
    public static final int TYPE_CROSS_CERT_REQ = 13;
    public static final int TYPE_CROSS_CERT_REP = 14;
    public static final int TYPE_CA_KEY_UPDATE_ANN = 15;
    public static final int TYPE_CERT_ANN = 16;
    public static final int TYPE_REVOCATION_ANN = 17;
    public static final int TYPE_CRL_ANN = 18;
    public static final int TYPE_CONFIRM = 19;
    public static final int TYPE_NESTED = 20;
    public static final int TYPE_GEN_MSG = 21;
    public static final int TYPE_GEN_REP = 22;
    public static final int TYPE_ERROR = 23;
    public static final int TYPE_CERT_CONFIRM = 24;
    public static final int TYPE_POLL_REQ = 25;
    public static final int TYPE_POLL_REP = 26;
    private int tagNo;
    private ASN1Encodable body;
}
