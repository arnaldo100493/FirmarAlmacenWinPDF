// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VerificationException.java

package co.com.pdf.text.pdf.security;

import java.security.GeneralSecurityException;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public class VerificationException extends GeneralSecurityException
{

    public VerificationException(Certificate cert, String message)
    {
        super(String.format("Certificate %s failed: %s", new Object[] {
            cert != null ? ((X509Certificate)cert).getSubjectDN().getName() : "Unknown", message
        }));
    }

    private static final long serialVersionUID = 0x295621062af5dd70L;
}
