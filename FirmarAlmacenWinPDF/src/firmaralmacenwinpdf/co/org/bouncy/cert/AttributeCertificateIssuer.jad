// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AttributeCertificateIssuer.java

package co.org.bouncy.cert;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1Integer;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.util.Selector;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package co.org.bouncy.cert:
//            X509CertificateHolder

public class AttributeCertificateIssuer
    implements Selector
{

    public AttributeCertificateIssuer(AttCertIssuer issuer)
    {
        form = issuer.getIssuer();
    }

    public AttributeCertificateIssuer(X500Name principal)
    {
        form = new V2Form(new GeneralNames(new GeneralName(principal)));
    }

    public X500Name[] getNames()
    {
        GeneralNames name;
        if(form instanceof V2Form)
            name = ((V2Form)form).getIssuerName();
        else
            name = (GeneralNames)form;
        GeneralName names[] = name.getNames();
        List l = new ArrayList(names.length);
        for(int i = 0; i != names.length; i++)
            if(names[i].getTagNo() == 4)
                l.add(X500Name.getInstance(names[i].getName()));

        return (X500Name[])(X500Name[])l.toArray(new X500Name[l.size()]);
    }

    private boolean matchesDN(X500Name subject, GeneralNames targets)
    {
        GeneralName names[] = targets.getNames();
        for(int i = 0; i != names.length; i++)
        {
            GeneralName gn = names[i];
            if(gn.getTagNo() == 4 && X500Name.getInstance(gn.getName()).equals(subject))
                return true;
        }

        return false;
    }

    public Object clone()
    {
        return new AttributeCertificateIssuer(AttCertIssuer.getInstance(form));
    }

    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;
        if(!(obj instanceof AttributeCertificateIssuer))
        {
            return false;
        } else
        {
            AttributeCertificateIssuer other = (AttributeCertificateIssuer)obj;
            return form.equals(other.form);
        }
    }

    public int hashCode()
    {
        return form.hashCode();
    }

    public boolean match(Object obj)
    {
        if(!(obj instanceof X509CertificateHolder))
            return false;
        X509CertificateHolder x509Cert = (X509CertificateHolder)obj;
        if(form instanceof V2Form)
        {
            V2Form issuer = (V2Form)form;
            if(issuer.getBaseCertificateID() != null)
                return issuer.getBaseCertificateID().getSerial().getValue().equals(x509Cert.getSerialNumber()) && matchesDN(x509Cert.getIssuer(), issuer.getBaseCertificateID().getIssuer());
            GeneralNames name = issuer.getIssuerName();
            if(matchesDN(x509Cert.getSubject(), name))
                return true;
        } else
        {
            GeneralNames name = (GeneralNames)form;
            if(matchesDN(x509Cert.getSubject(), name))
                return true;
        }
        return false;
    }

    final ASN1Encodable form;
}
