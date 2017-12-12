// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyTransRecipientId.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.cert.selector.X509CertificateHolderSelector;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.cms:
//            RecipientId, KeyTransRecipientInformation

public class KeyTransRecipientId extends RecipientId
{

    private KeyTransRecipientId(X509CertificateHolderSelector baseSelector)
    {
        super(0);
        this.baseSelector = baseSelector;
    }

    public KeyTransRecipientId(byte subjectKeyId[])
    {
        this(null, null, subjectKeyId);
    }

    public KeyTransRecipientId(X500Name issuer, BigInteger serialNumber)
    {
        this(issuer, serialNumber, null);
    }

    public KeyTransRecipientId(X500Name issuer, BigInteger serialNumber, byte subjectKeyId[])
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
        if(!(o instanceof KeyTransRecipientId))
        {
            return false;
        } else
        {
            KeyTransRecipientId id = (KeyTransRecipientId)o;
            return baseSelector.equals(id.baseSelector);
        }
    }

    public Object clone()
    {
        return new KeyTransRecipientId(baseSelector);
    }

    public boolean match(Object obj)
    {
        if(obj instanceof KeyTransRecipientInformation)
            return ((KeyTransRecipientInformation)obj).getRID().equals(this);
        else
            return baseSelector.match(obj);
    }

    private X509CertificateHolderSelector baseSelector;
}
