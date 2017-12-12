// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKIHeaderBuilder.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.GeneralName;

// Referenced classes of package co.org.bouncy.asn1.cmp:
//            PKIHeader, PKIFreeText, InfoTypeAndValue

public class PKIHeaderBuilder
{

    public PKIHeaderBuilder(int pvno, GeneralName sender, GeneralName recipient)
    {
        this(new ASN1Integer(pvno), sender, recipient);
    }

    private PKIHeaderBuilder(ASN1Integer pvno, GeneralName sender, GeneralName recipient)
    {
        this.pvno = pvno;
        this.sender = sender;
        this.recipient = recipient;
    }

    /**
     * @deprecated Method setMessageTime is deprecated
     */

    public PKIHeaderBuilder setMessageTime(DERGeneralizedTime time)
    {
        messageTime = ASN1GeneralizedTime.getInstance(time);
        return this;
    }

    public PKIHeaderBuilder setMessageTime(ASN1GeneralizedTime time)
    {
        messageTime = time;
        return this;
    }

    public PKIHeaderBuilder setProtectionAlg(AlgorithmIdentifier aid)
    {
        protectionAlg = aid;
        return this;
    }

    public PKIHeaderBuilder setSenderKID(byte kid[])
    {
        return setSenderKID(((ASN1OctetString) (kid != null ? ((ASN1OctetString) (new DEROctetString(kid))) : null)));
    }

    public PKIHeaderBuilder setSenderKID(ASN1OctetString kid)
    {
        senderKID = kid;
        return this;
    }

    public PKIHeaderBuilder setRecipKID(byte kid[])
    {
        return setRecipKID(kid != null ? new DEROctetString(kid) : null);
    }

    public PKIHeaderBuilder setRecipKID(DEROctetString kid)
    {
        recipKID = kid;
        return this;
    }

    public PKIHeaderBuilder setTransactionID(byte tid[])
    {
        return setTransactionID(((ASN1OctetString) (tid != null ? ((ASN1OctetString) (new DEROctetString(tid))) : null)));
    }

    public PKIHeaderBuilder setTransactionID(ASN1OctetString tid)
    {
        transactionID = tid;
        return this;
    }

    public PKIHeaderBuilder setSenderNonce(byte nonce[])
    {
        return setSenderNonce(((ASN1OctetString) (nonce != null ? ((ASN1OctetString) (new DEROctetString(nonce))) : null)));
    }

    public PKIHeaderBuilder setSenderNonce(ASN1OctetString nonce)
    {
        senderNonce = nonce;
        return this;
    }

    public PKIHeaderBuilder setRecipNonce(byte nonce[])
    {
        return setRecipNonce(((ASN1OctetString) (nonce != null ? ((ASN1OctetString) (new DEROctetString(nonce))) : null)));
    }

    public PKIHeaderBuilder setRecipNonce(ASN1OctetString nonce)
    {
        recipNonce = nonce;
        return this;
    }

    public PKIHeaderBuilder setFreeText(PKIFreeText text)
    {
        freeText = text;
        return this;
    }

    public PKIHeaderBuilder setGeneralInfo(InfoTypeAndValue genInfo)
    {
        return setGeneralInfo(makeGeneralInfoSeq(genInfo));
    }

    public PKIHeaderBuilder setGeneralInfo(InfoTypeAndValue genInfos[])
    {
        return setGeneralInfo(makeGeneralInfoSeq(genInfos));
    }

    public PKIHeaderBuilder setGeneralInfo(ASN1Sequence seqOfInfoTypeAndValue)
    {
        generalInfo = seqOfInfoTypeAndValue;
        return this;
    }

    private static ASN1Sequence makeGeneralInfoSeq(InfoTypeAndValue generalInfo)
    {
        return new DERSequence(generalInfo);
    }

    private static ASN1Sequence makeGeneralInfoSeq(InfoTypeAndValue generalInfos[])
    {
        ASN1Sequence genInfoSeq = null;
        if(generalInfos != null)
        {
            ASN1EncodableVector v = new ASN1EncodableVector();
            for(int i = 0; i < generalInfos.length; i++)
                v.add(generalInfos[i]);

            genInfoSeq = new DERSequence(v);
        }
        return genInfoSeq;
    }

    public PKIHeader build()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(pvno);
        v.add(sender);
        v.add(recipient);
        addOptional(v, 0, messageTime);
        addOptional(v, 1, protectionAlg);
        addOptional(v, 2, senderKID);
        addOptional(v, 3, recipKID);
        addOptional(v, 4, transactionID);
        addOptional(v, 5, senderNonce);
        addOptional(v, 6, recipNonce);
        addOptional(v, 7, freeText);
        addOptional(v, 8, generalInfo);
        messageTime = null;
        protectionAlg = null;
        senderKID = null;
        recipKID = null;
        transactionID = null;
        senderNonce = null;
        recipNonce = null;
        freeText = null;
        generalInfo = null;
        return PKIHeader.getInstance(new DERSequence(v));
    }

    private void addOptional(ASN1EncodableVector v, int tagNo, ASN1Encodable obj)
    {
        if(obj != null)
            v.add(new DERTaggedObject(true, tagNo, obj));
    }

    private ASN1Integer pvno;
    private GeneralName sender;
    private GeneralName recipient;
    private ASN1GeneralizedTime messageTime;
    private AlgorithmIdentifier protectionAlg;
    private ASN1OctetString senderKID;
    private ASN1OctetString recipKID;
    private ASN1OctetString transactionID;
    private ASN1OctetString senderNonce;
    private ASN1OctetString recipNonce;
    private PKIFreeText freeText;
    private ASN1Sequence generalInfo;
}
