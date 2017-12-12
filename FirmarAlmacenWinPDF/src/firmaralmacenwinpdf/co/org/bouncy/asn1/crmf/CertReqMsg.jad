// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertReqMsg.java

package co.org.bouncy.asn1.crmf;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.crmf:
//            ProofOfPossession, AttributeTypeAndValue, CertRequest

public class CertReqMsg extends ASN1Object
{

    private CertReqMsg(ASN1Sequence seq)
    {
        Enumeration en = seq.getObjects();
        certReq = CertRequest.getInstance(en.nextElement());
        while(en.hasMoreElements()) 
        {
            Object o = en.nextElement();
            if((o instanceof ASN1TaggedObject) || (o instanceof ProofOfPossession))
                pop = ProofOfPossession.getInstance(o);
            else
                regInfo = ASN1Sequence.getInstance(o);
        }
    }

    public static CertReqMsg getInstance(Object o)
    {
        if(o instanceof CertReqMsg)
            return (CertReqMsg)o;
        if(o != null)
            return new CertReqMsg(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public CertReqMsg(CertRequest certReq, ProofOfPossession pop, AttributeTypeAndValue regInfo[])
    {
        if(certReq == null)
            throw new IllegalArgumentException("'certReq' cannot be null");
        this.certReq = certReq;
        this.pop = pop;
        if(regInfo != null)
            this.regInfo = new DERSequence(regInfo);
    }

    public CertRequest getCertReq()
    {
        return certReq;
    }

    /**
     * @deprecated Method getPop is deprecated
     */

    public ProofOfPossession getPop()
    {
        return pop;
    }

    public ProofOfPossession getPopo()
    {
        return pop;
    }

    public AttributeTypeAndValue[] getRegInfo()
    {
        if(regInfo == null)
            return null;
        AttributeTypeAndValue results[] = new AttributeTypeAndValue[regInfo.size()];
        for(int i = 0; i != results.length; i++)
            results[i] = AttributeTypeAndValue.getInstance(regInfo.getObjectAt(i));

        return results;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(certReq);
        addOptional(v, pop);
        addOptional(v, regInfo);
        return new DERSequence(v);
    }

    private void addOptional(ASN1EncodableVector v, ASN1Encodable obj)
    {
        if(obj != null)
            v.add(obj);
    }

    private CertRequest certReq;
    private ProofOfPossession pop;
    private ASN1Sequence regInfo;
}
