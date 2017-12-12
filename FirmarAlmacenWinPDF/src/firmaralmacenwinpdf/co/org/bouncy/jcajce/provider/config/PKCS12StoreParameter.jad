// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS12StoreParameter.java

package co.org.bouncy.jcajce.provider.config;

import java.io.OutputStream;
import java.security.KeyStore;

public class PKCS12StoreParameter
    implements java.security.KeyStore.LoadStoreParameter
{

    public PKCS12StoreParameter(OutputStream out, char password[])
    {
        this(out, password, false);
    }

    public PKCS12StoreParameter(OutputStream out, java.security.KeyStore.ProtectionParameter protectionParameter)
    {
        this(out, protectionParameter, false);
    }

    public PKCS12StoreParameter(OutputStream out, char password[], boolean forDEREncoding)
    {
        this(out, ((java.security.KeyStore.ProtectionParameter) (new java.security.KeyStore.PasswordProtection(password))), forDEREncoding);
    }

    public PKCS12StoreParameter(OutputStream out, java.security.KeyStore.ProtectionParameter protectionParameter, boolean forDEREncoding)
    {
        this.out = out;
        this.protectionParameter = protectionParameter;
        this.forDEREncoding = forDEREncoding;
    }

    public OutputStream getOutputStream()
    {
        return out;
    }

    public java.security.KeyStore.ProtectionParameter getProtectionParameter()
    {
        return protectionParameter;
    }

    public boolean isForDEREncoding()
    {
        return forDEREncoding;
    }

    private final OutputStream out;
    private final java.security.KeyStore.ProtectionParameter protectionParameter;
    private final boolean forDEREncoding;
}
