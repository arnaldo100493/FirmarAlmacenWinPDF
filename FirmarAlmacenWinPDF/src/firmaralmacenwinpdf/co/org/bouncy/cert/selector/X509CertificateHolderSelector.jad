// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509CertificateHolderSelector.java

package co.org.bouncy.cert.selector;

import co.org.bouncy.asn1.ASN1Integer;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.cms.IssuerAndSerialNumber;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.Extension;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Selector;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.cert.selector:
//            MSOutlookKeyIdCalculator

public class X509CertificateHolderSelector
    implements Selector
{

    public X509CertificateHolderSelector(byte subjectKeyId[])
    {
        this(null, null, subjectKeyId);
    }

    public X509CertificateHolderSelector(X500Name issuer, BigInteger serialNumber)
    {
        this(issuer, serialNumber, null);
    }

    public X509CertificateHolderSelector(X500Name issuer, BigInteger serialNumber, byte subjectKeyId[])
    {
        this.issuer = issuer;
        this.serialNumber = serialNumber;
        this.subjectKeyId = subjectKeyId;
    }

    public X500Name getIssuer()
    {
        return issuer;
    }

    public BigInteger getSerialNumber()
    {
        return serialNumber;
    }

    public byte[] getSubjectKeyIdentifier()
    {
        return Arrays.clone(subjectKeyId);
    }

    public int hashCode()
    {
        int code = Arrays.hashCode(subjectKeyId);
        if(serialNumber != null)
            code ^= serialNumber.hashCode();
        if(issuer != null)
            code ^= issuer.hashCode();
        return code;
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof X509CertificateHolderSelector))
        {
            return false;
        } else
        {
            X509CertificateHolderSelector id = (X509CertificateHolderSelector)o;
            return Arrays.areEqual(subjectKeyId, id.subjectKeyId) && equalsObj(serialNumber, id.serialNumber) && equalsObj(issuer, id.issuer);
        }
    }

    private boolean equalsObj(Object a, Object b)
    {
        return a == null ? b == null : a.equals(b);
    }

    public boolean match(Object obj)
    {
        if(obj instanceof X509CertificateHolder)
        {
            X509CertificateHolder certHldr = (X509CertificateHolder)obj;
            if(getSerialNumber() != null)
            {
                IssuerAndSerialNumber iAndS = new IssuerAndSerialNumber(certHldr.toASN1Structure());
                return iAndS.getName().equals(issuer) && iAndS.getSerialNumber().getValue().equals(serialNumber);
            }
            if(subjectKeyId != null)
            {
                Extension ext = certHldr.getExtension(Extension.subjectKeyIdentifier);
                if(ext == null)
                {
                    return Arrays.areEqual(subjectKeyId, MSOutlookKeyIdCalculator.calculateKeyId(certHldr.getSubjectPublicKeyInfo()));
                } else
                {
                    byte subKeyID[] = ASN1OctetString.getInstance(ext.getParsedValue()).getOctets();
                    return Arrays.areEqual(subjectKeyId, subKeyID);
                }
            }
        } else
        if(obj instanceof byte[])
            return Arrays.areEqual(subjectKeyId, (byte[])(byte[])obj);
        return false;
    }

    public Object clone()
    {
        return new X509CertificateHolderSelector(issuer, serialNumber, subjectKeyId);
    }

    private byte subjectKeyId[];
    private X500Name issuer;
    private BigInteger serialNumber;
}
