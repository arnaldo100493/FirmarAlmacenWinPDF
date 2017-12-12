// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignerId.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.cert.selector.X509CertificateHolderSelector;
import co.org.bouncy.util.Selector;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.cms:
//            SignerInformation

public class SignerId
    implements Selector
{

    private SignerId(X509CertificateHolderSelector baseSelector)
    {
        this.baseSelector = baseSelector;
    }

    public SignerId(byte subjectKeyId[])
    {
        this(null, null, subjectKeyId);
    }

    public SignerId(X500Name issuer, BigInteger serialNumber)
    {
        this(issuer, serialNumber, null);
    }

    public SignerId(X500Name issuer, BigInteger serialNumber, byte subjectKeyId[])
    {
        this(new X509CertificateHolderSelector(issuer, serialNumber, subjectKeyId));
    }

    public X500Name getIssuer()
    {
        return baseSelector.getIssuer();
    }

    public BigInteger getSerialNumber()
    {
        return baseSelector.getSerialNumber();
    }

    public byte[] getSubjectKeyIdentifier()
    {
        return baseSelector.getSubjectKeyIdentifier();
    }

    public int hashCode()
    {
        return baseSelector.hashCode();
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof SignerId))
        {
            return false;
        } else
        {
            SignerId id = (SignerId)o;
            return baseSelector.equals(id.baseSelector);
        }
    }

    public boolean match(Object obj)
    {
        if(obj instanceof SignerInformation)
            return ((SignerInformation)obj).getSID().equals(this);
        else
            return baseSelector.match(obj);
    }

    public Object clone()
    {
        return new SignerId(baseSelector);
    }

    private X509CertificateHolderSelector baseSelector;
}
