// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProviderDigest.java

package co.com.pdf.text.pdf.security;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;

// Referenced classes of package co.com.pdf.text.pdf.security:
//            ExternalDigest, DigestAlgorithms

public class ProviderDigest
    implements ExternalDigest
{

    public ProviderDigest(String provider)
    {
        this.provider = provider;
    }

    public MessageDigest getMessageDigest(String hashAlgorithm)
        throws GeneralSecurityException
    {
        return DigestAlgorithms.getMessageDigest(hashAlgorithm, provider);
    }

    private String provider;
}
