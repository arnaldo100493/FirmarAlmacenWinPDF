// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   VerificationOK.java

package co.com.pdf.text.pdf.security;

import java.security.Principal;
import java.security.cert.X509Certificate;

public class VerificationOK
{

    public VerificationOK(X509Certificate certificate, Class verifierClass, String message)
    {
        this.certificate = certificate;
        this.verifierClass = verifierClass;
        this.message = message;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        if(certificate != null)
        {
            sb.append(certificate.getSubjectDN().getName());
            sb.append(" verified with ");
        }
        sb.append(verifierClass.getName());
        sb.append(": ");
        sb.append(message);
        return sb.toString();
    }

    protected X509Certificate certificate;
    protected Class verifierClass;
    protected String message;
}
