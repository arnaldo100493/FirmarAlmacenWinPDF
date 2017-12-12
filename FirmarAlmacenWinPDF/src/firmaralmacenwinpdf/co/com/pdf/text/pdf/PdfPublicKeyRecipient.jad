// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfPublicKeyRecipient.java

package co.com.pdf.text.pdf;

import java.security.cert.Certificate;

public class PdfPublicKeyRecipient
{

    public PdfPublicKeyRecipient(Certificate certificate, int permission)
    {
        this.certificate = null;
        this.permission = 0;
        cms = null;
        this.certificate = certificate;
        this.permission = permission;
    }

    public Certificate getCertificate()
    {
        return certificate;
    }

    public int getPermission()
    {
        return permission;
    }

    protected void setCms(byte cms[])
    {
        this.cms = cms;
    }

    protected byte[] getCms()
    {
        return cms;
    }

    private Certificate certificate;
    private int permission;
    protected byte cms[];
}
