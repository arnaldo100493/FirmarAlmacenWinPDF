// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaX509CertSelectorConverter.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.cms.KeyTransRecipientId;
import co.org.bouncy.cms.SignerId;
import java.security.cert.X509CertSelector;

public class JcaX509CertSelectorConverter extends co.org.bouncy.cert.selector.jcajce.JcaX509CertSelectorConverter
{

    public JcaX509CertSelectorConverter()
    {
    }

    public X509CertSelector getCertSelector(KeyTransRecipientId recipientId)
    {
        return doConversion(recipientId.getIssuer(), recipientId.getSerialNumber(), recipientId.getSubjectKeyIdentifier());
    }

    public X509CertSelector getCertSelector(SignerId signerId)
    {
        return doConversion(signerId.getIssuer(), signerId.getSerialNumber(), signerId.getSubjectKeyIdentifier());
    }
}
