// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RevocationDetailsBuilder.java

package co.org.bouncy.cert.cmp;

import co.org.bouncy.asn1.ASN1Integer;
import co.org.bouncy.asn1.cmp.RevDetails;
import co.org.bouncy.asn1.crmf.CertTemplateBuilder;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.cert.cmp:
//            RevocationDetails

public class RevocationDetailsBuilder
{

    public RevocationDetailsBuilder()
    {
        templateBuilder = new CertTemplateBuilder();
    }

    public RevocationDetailsBuilder setPublicKey(SubjectPublicKeyInfo publicKey)
    {
        if(publicKey != null)
            templateBuilder.setPublicKey(publicKey);
        return this;
    }

    public RevocationDetailsBuilder setIssuer(X500Name issuer)
    {
        if(issuer != null)
            templateBuilder.setIssuer(issuer);
        return this;
    }

    public RevocationDetailsBuilder setSerialNumber(BigInteger serialNumber)
    {
        if(serialNumber != null)
            templateBuilder.setSerialNumber(new ASN1Integer(serialNumber));
        return this;
    }

    public RevocationDetailsBuilder setSubject(X500Name subject)
    {
        if(subject != null)
            templateBuilder.setSubject(subject);
        return this;
    }

    public RevocationDetails build()
    {
        return new RevocationDetails(new RevDetails(templateBuilder.build()));
    }

    private CertTemplateBuilder templateBuilder;
}
