// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509AttributeCertificateHolderSelectorBuilder.java

package co.org.bouncy.cert.selector;

import co.org.bouncy.asn1.x509.GeneralName;
import co.org.bouncy.cert.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

// Referenced classes of package co.org.bouncy.cert.selector:
//            X509AttributeCertificateHolderSelector

public class X509AttributeCertificateHolderSelectorBuilder
{

    public X509AttributeCertificateHolderSelectorBuilder()
    {
        targetNames = new HashSet();
        targetGroups = new HashSet();
    }

    public void setAttributeCert(X509AttributeCertificateHolder attributeCert)
    {
        this.attributeCert = attributeCert;
    }

    public void setAttributeCertificateValid(Date attributeCertificateValid)
    {
        if(attributeCertificateValid != null)
            this.attributeCertificateValid = new Date(attributeCertificateValid.getTime());
        else
            this.attributeCertificateValid = null;
    }

    public void setHolder(AttributeCertificateHolder holder)
    {
        this.holder = holder;
    }

    public void setIssuer(AttributeCertificateIssuer issuer)
    {
        this.issuer = issuer;
    }

    public void setSerialNumber(BigInteger serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public void addTargetName(GeneralName name)
    {
        targetNames.add(name);
    }

    public void setTargetNames(Collection names)
        throws IOException
    {
        targetNames = extractGeneralNames(names);
    }

    public void addTargetGroup(GeneralName group)
    {
        targetGroups.add(group);
    }

    public void setTargetGroups(Collection names)
        throws IOException
    {
        targetGroups = extractGeneralNames(names);
    }

    private Set extractGeneralNames(Collection names)
        throws IOException
    {
        if(names == null || names.isEmpty())
            return new HashSet();
        Set temp = new HashSet();
        for(Iterator it = names.iterator(); it.hasNext(); temp.add(GeneralName.getInstance(it.next())));
        return temp;
    }

    public X509AttributeCertificateHolderSelector build()
    {
        X509AttributeCertificateHolderSelector sel = new X509AttributeCertificateHolderSelector(holder, issuer, serialNumber, attributeCertificateValid, attributeCert, Collections.unmodifiableCollection(new HashSet(targetNames)), Collections.unmodifiableCollection(new HashSet(targetGroups)));
        return sel;
    }

    private AttributeCertificateHolder holder;
    private AttributeCertificateIssuer issuer;
    private BigInteger serialNumber;
    private Date attributeCertificateValid;
    private X509AttributeCertificateHolder attributeCert;
    private Collection targetNames;
    private Collection targetGroups;
}
