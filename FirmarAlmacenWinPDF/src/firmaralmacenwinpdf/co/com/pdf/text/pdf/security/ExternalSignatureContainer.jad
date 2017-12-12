// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExternalSignatureContainer.java

package co.com.pdf.text.pdf.security;

import co.com.pdf.text.pdf.PdfDictionary;
import java.io.InputStream;
import java.security.GeneralSecurityException;

public interface ExternalSignatureContainer
{

    public abstract byte[] sign(InputStream inputstream)
        throws GeneralSecurityException;

    public abstract void modifySigningDictionary(PdfDictionary pdfdictionary);
}
