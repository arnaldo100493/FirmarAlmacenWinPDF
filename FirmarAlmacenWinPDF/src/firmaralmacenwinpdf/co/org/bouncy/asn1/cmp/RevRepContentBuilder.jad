// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RevRepContentBuilder.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.crmf.CertId;
import co.org.bouncy.asn1.x509.CertificateList;

// Referenced classes of package co.org.bouncy.asn1.cmp:
//            RevRepContent, PKIStatusInfo

public class RevRepContentBuilder
{

    public RevRepContentBuilder()
    {
        status = new ASN1EncodableVector();
        revCerts = new ASN1EncodableVector();
        crls = new ASN1EncodableVector();
    }

    public RevRepContentBuilder add(PKIStatusInfo status)
    {
        this.status.add(status);
        return this;
    }

    public RevRepContentBuilder add(PKIStatusInfo status, CertId certId)
    {
        if(this.status.size() != revCerts.size())
        {
            throw new IllegalStateException("status and revCerts sequence must be in common order");
        } else
        {
            this.status.add(status);
            revCerts.add(certId);
            return this;
        }
    }

    public RevRepContentBuilder addCrl(CertificateList crl)
    {
        crls.add(crl);
        return this;
    }

    public RevRepContent build()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new DERSequence(status));
        if(revCerts.size() != 0)
            v.add(new DERTaggedObject(true, 0, new DERSequence(revCerts)));
        if(crls.size() != 0)
            v.add(new DERTaggedObject(true, 1, new DERSequence(crls)));
        return RevRepContent.getInstance(new DERSequence(v));
    }

    private ASN1EncodableVector status;
    private ASN1EncodableVector revCerts;
    private ASN1EncodableVector crls;
}
