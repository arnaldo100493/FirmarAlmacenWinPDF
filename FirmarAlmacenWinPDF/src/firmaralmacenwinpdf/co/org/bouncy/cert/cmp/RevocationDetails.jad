// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RevocationDetails.java

package co.org.bouncy.cert.cmp;

import co.org.bouncy.asn1.ASN1Integer;
import co.org.bouncy.asn1.cmp.RevDetails;
import co.org.bouncy.asn1.crmf.CertTemplate;
import co.org.bouncy.asn1.x500.X500Name;
import java.math.BigInteger;

public class RevocationDetails
{

    public RevocationDetails(RevDetails revDetails)
    {
        this.revDetails = revDetails;
    }

    public X500Name getSubject()
    {
        return revDetails.getCertDetails().getSubject();
    }

    public X500Name getIssuer()
    {
        return revDetails.getCertDetails().getIssuer();
    }

    public BigInteger getSerialNumber()
    {
        return revDetails.getCertDetails().getSerialNumber().getValue();
    }

    public RevDetails toASN1Structure()
    {
        return revDetails;
    }

    private RevDetails revDetails;
}
