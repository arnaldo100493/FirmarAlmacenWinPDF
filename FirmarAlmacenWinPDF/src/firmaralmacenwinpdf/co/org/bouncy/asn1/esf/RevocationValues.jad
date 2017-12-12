// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RevocationValues.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.ocsp.BasicOCSPResponse;
import co.org.bouncy.asn1.x509.CertificateList;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.esf:
//            OtherRevVals

public class RevocationValues extends ASN1Object
{

    public static RevocationValues getInstance(Object obj)
    {
        if(obj instanceof RevocationValues)
            return (RevocationValues)obj;
        if(obj != null)
            return new RevocationValues(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private RevocationValues(ASN1Sequence seq)
    {
        if(seq.size() > 3)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        Enumeration e = seq.getObjects();
        do
        {
            if(!e.hasMoreElements())
                break;
            DERTaggedObject o = (DERTaggedObject)e.nextElement();
            switch(o.getTagNo())
            {
            case 0: // '\0'
                ASN1Sequence crlValsSeq = (ASN1Sequence)o.getObject();
                for(Enumeration crlValsEnum = crlValsSeq.getObjects(); crlValsEnum.hasMoreElements(); CertificateList.getInstance(crlValsEnum.nextElement()));
                crlVals = crlValsSeq;
                break;

            case 1: // '\001'
                ASN1Sequence ocspValsSeq = (ASN1Sequence)o.getObject();
                for(Enumeration ocspValsEnum = ocspValsSeq.getObjects(); ocspValsEnum.hasMoreElements(); BasicOCSPResponse.getInstance(ocspValsEnum.nextElement()));
                ocspVals = ocspValsSeq;
                break;

            case 2: // '\002'
                otherRevVals = OtherRevVals.getInstance(o.getObject());
                break;

            default:
                throw new IllegalArgumentException((new StringBuilder()).append("invalid tag: ").append(o.getTagNo()).toString());
            }
        } while(true);
    }

    public RevocationValues(CertificateList crlVals[], BasicOCSPResponse ocspVals[], OtherRevVals otherRevVals)
    {
        if(null != crlVals)
            this.crlVals = new DERSequence(crlVals);
        if(null != ocspVals)
            this.ocspVals = new DERSequence(ocspVals);
        this.otherRevVals = otherRevVals;
    }

    public CertificateList[] getCrlVals()
    {
        if(null == crlVals)
            return new CertificateList[0];
        CertificateList result[] = new CertificateList[crlVals.size()];
        for(int idx = 0; idx < result.length; idx++)
            result[idx] = CertificateList.getInstance(crlVals.getObjectAt(idx));

        return result;
    }

    public BasicOCSPResponse[] getOcspVals()
    {
        if(null == ocspVals)
            return new BasicOCSPResponse[0];
        BasicOCSPResponse result[] = new BasicOCSPResponse[ocspVals.size()];
        for(int idx = 0; idx < result.length; idx++)
            result[idx] = BasicOCSPResponse.getInstance(ocspVals.getObjectAt(idx));

        return result;
    }

    public OtherRevVals getOtherRevVals()
    {
        return otherRevVals;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(null != crlVals)
            v.add(new DERTaggedObject(true, 0, crlVals));
        if(null != ocspVals)
            v.add(new DERTaggedObject(true, 1, ocspVals));
        if(null != otherRevVals)
            v.add(new DERTaggedObject(true, 2, otherRevVals.toASN1Primitive()));
        return new DERSequence(v);
    }

    private ASN1Sequence crlVals;
    private ASN1Sequence ocspVals;
    private OtherRevVals otherRevVals;
}
