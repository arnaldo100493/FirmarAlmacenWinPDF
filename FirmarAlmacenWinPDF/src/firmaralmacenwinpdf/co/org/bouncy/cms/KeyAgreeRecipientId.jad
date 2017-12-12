// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyAgreeRecipientId.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.cert.selector.X509CertificateHolderSelector;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.cms:
//            RecipientId, KeyAgreeRecipientInformation

public class KeyAgreeRecipientId extends RecipientId
{

    private KeyAgreeRecipientId(X509CertificateHolderSelector baseSelector)
    {
        super(2);
        this.baseSelector = baseSelector;
    }

    public KeyAgreeRecipientId(byte subjectKeyId[])
    {
        this(null, null, subjectKeyId);
    }

    public KeyAgreeRecipientId(X500Name issuer, BigInteger serialNumber)
    {
        this(issuer, serialNumber, null);
    }

    public KeyAgreeRecipientId(X500Name issuer, BigInteger serialNumber, byte subjectKeyId[])
    {
        this(new X509CertificateHolderSelector(issuer, serialNumber, subjectKeyId));
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
        if(!(o instanceof KeyAgreeRecipientId))
        {
            return false;
        } else
        {
            KeyAgreeRecipientId id = (KeyAgreeRecipientId)o;
            return baseSelector.equals(id.baseSelector);
        }
    }

    public Object clone()
    {
        return new KeyAgreeRecipientId(baseSelector);
    }

    public boolean match(Object obj)
    {
        if(obj instanceof KeyAgreeRecipientInformation)
            return ((KeyAgreeRecipientInformation)obj).getRID().equals(this);
        else
            return baseSelector.match(obj);
    }

    private X509CertificateHolderSelector baseSelector;
}
