// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CrlClientOffline.java

package co.com.pdf.text.pdf.security;

import co.com.pdf.text.ExceptionConverter;
import java.security.cert.*;
import java.util.ArrayList;
import java.util.Collection;

// Referenced classes of package co.com.pdf.text.pdf.security:
//            CrlClient

public class CrlClientOffline
    implements CrlClient
{

    public CrlClientOffline(byte crlEncoded[])
    {
        crls = new ArrayList();
        crls.add(crlEncoded);
    }

    public CrlClientOffline(CRL crl)
    {
        crls = new ArrayList();
        try
        {
            crls.add(((X509CRL)crl).getEncoded());
        }
        catch(Exception ex)
        {
            throw new ExceptionConverter(ex);
        }
    }

    public Collection getEncoded(X509Certificate checkCert, String url)
    {
        return crls;
    }

    private ArrayList crls;
}
