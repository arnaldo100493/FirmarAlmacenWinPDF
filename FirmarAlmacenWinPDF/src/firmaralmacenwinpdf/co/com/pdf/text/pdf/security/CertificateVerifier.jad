// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertificateVerifier.java

package co.com.pdf.text.pdf.security;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.*;

public class CertificateVerifier
{

    public CertificateVerifier(CertificateVerifier verifier)
    {
        onlineCheckingAllowed = true;
        this.verifier = verifier;
    }

    public void setOnlineCheckingAllowed(boolean onlineCheckingAllowed)
    {
        this.onlineCheckingAllowed = onlineCheckingAllowed;
    }

    public List verify(X509Certificate signCert, X509Certificate issuerCert, Date signDate)
        throws GeneralSecurityException, IOException
    {
        if(signDate != null)
            signCert.checkValidity(signDate);
        if(issuerCert != null)
            signCert.verify(issuerCert.getPublicKey());
        else
            signCert.verify(signCert.getPublicKey());
        List result = new ArrayList();
        if(verifier != null)
            result.addAll(verifier.verify(signCert, issuerCert, signDate));
        return result;
    }

    protected CertificateVerifier verifier;
    protected boolean onlineCheckingAllowed;
}
