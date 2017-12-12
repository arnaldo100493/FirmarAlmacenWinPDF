// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertRequest.java

package co.org.bouncy.asn1.crmf;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.crmf:
//            CertTemplate, Controls

public class CertRequest extends ASN1Object
{

    private CertRequest(ASN1Sequence seq)
    {
        certReqId = new ASN1Integer(ASN1Integer.getInstance(seq.getObjectAt(0)).getValue());
        certTemplate = CertTemplate.getInstance(seq.getObjectAt(1));
        if(seq.size() > 2)
            controls = Controls.getInstance(seq.getObjectAt(2));
    }

    public static CertRequest getInstance(Object o)
    {
        if(o instanceof CertRequest)
            return (CertRequest)o;
        if(o != null)
            return new CertRequest(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public CertRequest(int certReqId, CertTemplate certTemplate, Controls controls)
    {
        this(new ASN1Integer(certReqId), certTemplate, controls);
    }

    public CertRequest(ASN1Integer certReqId, CertTemplate certTemplate, Controls controls)
    {
        this.certReqId = certReqId;
        this.certTemplate = certTemplate;
        this.controls = controls;
    }

    public ASN1Integer getCertReqId()
    {
        return certReqId;
    }

    public CertTemplate getCertTemplate()
    {
        return certTemplate;
    }

    public Controls getControls()
    {
        return controls;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(certReqId);
        v.add(certTemplate);
        if(controls != null)
            v.add(controls);
        return new DERSequence(v);
    }

    private ASN1Integer certReqId;
    private CertTemplate certTemplate;
    private Controls controls;
}
