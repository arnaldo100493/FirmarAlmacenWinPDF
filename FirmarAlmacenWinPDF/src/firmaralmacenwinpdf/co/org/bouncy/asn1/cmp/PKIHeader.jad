// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKIHeader.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.GeneralName;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.cmp:
//            InfoTypeAndValue, PKIFreeText

public class PKIHeader extends ASN1Object
{

    private PKIHeader(ASN1Sequence seq)
    {
        Enumeration en = seq.getObjects();
        pvno = ASN1Integer.getInstance(en.nextElement());
        sender = GeneralName.getInstance(en.nextElement());
        recipient = GeneralName.getInstance(en.nextElement());
        do
        {
            if(!en.hasMoreElements())
                break;
            ASN1TaggedObject tObj = (ASN1TaggedObject)en.nextElement();
            switch(tObj.getTagNo())
            {
            case 0: // '\0'
                messageTime = DERGeneralizedTime.getInstance(tObj, true);
                break;

            case 1: // '\001'
                protectionAlg = AlgorithmIdentifier.getInstance(tObj, true);
                break;

            case 2: // '\002'
                senderKID = ASN1OctetString.getInstance(tObj, true);
                break;

            case 3: // '\003'
                recipKID = ASN1OctetString.getInstance(tObj, true);
                break;

            case 4: // '\004'
                transactionID = ASN1OctetString.getInstance(tObj, true);
                break;

            case 5: // '\005'
                senderNonce = ASN1OctetString.getInstance(tObj, true);
                break;

            case 6: // '\006'
                recipNonce = ASN1OctetString.getInstance(tObj, true);
                break;

            case 7: // '\007'
                freeText = PKIFreeText.getInstance(tObj, true);
                break;

            case 8: // '\b'
                generalInfo = ASN1Sequence.getInstance(tObj, true);
                break;

            default:
                throw new IllegalArgumentException((new StringBuilder()).append("unknown tag number: ").append(tObj.getTagNo()).toString());
            }
        } while(true);
    }

    public static PKIHeader getInstance(Object o)
    {
        if(o instanceof PKIHeader)
            return (PKIHeader)o;
        if(o != null)
            return new PKIHeader(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public PKIHeader(int pvno, GeneralName sender, GeneralName recipient)
    {
        this(new ASN1Integer(pvno), sender, recipient);
    }

    private PKIHeader(ASN1Integer pvno, GeneralName sender, GeneralName recipient)
    {
        this.pvno = pvno;
        this.sender = sender;
        this.recipient = recipient;
    }

    public ASN1Integer getPvno()
    {
        return pvno;
    }

    public GeneralName getSender()
    {
        return sender;
    }

    public GeneralName getRecipient()
    {
        return recipient;
    }

    public DERGeneralizedTime getMessageTime()
    {
        return messageTime;
    }

    public AlgorithmIdentifier getProtectionAlg()
    {
        return protectionAlg;
    }

    public ASN1OctetString getSenderKID()
    {
        return senderKID;
    }

    public ASN1OctetString getRecipKID()
    {
        return recipKID;
    }

    public ASN1OctetString getTransactionID()
    {
        return transactionID;
    }

    public ASN1OctetString getSenderNonce()
    {
        return senderNonce;
    }

    public ASN1OctetString getRecipNonce()
    {
        return recipNonce;
    }

    public PKIFreeText getFreeText()
    {
        return freeText;
    }

    public InfoTypeAndValue[] getGeneralInfo()
    {
        if(generalInfo == null)
            return null;
        InfoTypeAndValue results[] = new InfoTypeAndValue[generalInfo.size()];
        for(int i = 0; i < results.length; i++)
            results[i] = InfoTypeAndValue.getInstance(generalInfo.getObjectAt(i));

        return results;
    }

    public ASN1Primitive toASN1Primitive()
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
        return new DERSequence(v);
    }

    private void addOptional(ASN1EncodableVector v, int tagNo, ASN1Encodable obj)
    {
        if(obj != null)
            v.add(new DERTaggedObject(true, tagNo, obj));
    }

    public static final GeneralName NULL_NAME = new GeneralName(X500Name.getInstance(new DERSequence()));
    public static final int CMP_1999 = 1;
    public static final int CMP_2000 = 2;
    private ASN1Integer pvno;
    private GeneralName sender;
    private GeneralName recipient;
    private DERGeneralizedTime messageTime;
    private AlgorithmIdentifier protectionAlg;
    private ASN1OctetString senderKID;
    private ASN1OctetString recipKID;
    private ASN1OctetString transactionID;
    private ASN1OctetString senderNonce;
    private ASN1OctetString recipNonce;
    private PKIFreeText freeText;
    private ASN1Sequence generalInfo;

}
