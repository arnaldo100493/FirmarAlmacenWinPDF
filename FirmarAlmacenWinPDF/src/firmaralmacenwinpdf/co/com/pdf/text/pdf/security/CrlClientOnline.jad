// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CrlClientOnline.java

package co.com.pdf.text.pdf.security;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.log.Logger;
import co.com.pdf.text.log.LoggerFactory;
import java.io.*;
import java.net.*;
import java.security.cert.*;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.security:
//            CrlClient, CertificateUtil

public class CrlClientOnline
    implements CrlClient
{

    public CrlClientOnline()
    {
        urls = new ArrayList();
    }

    public transient CrlClientOnline(String crls[])
    {
        urls = new ArrayList();
        String arr$[] = crls;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            String url = arr$[i$];
            addUrl(url);
        }

    }

    public transient CrlClientOnline(URL crls[])
    {
        urls = new ArrayList();
        URL url;
        for(Iterator i$ = urls.iterator(); i$.hasNext(); addUrl(url))
            url = (URL)i$.next();

    }

    public CrlClientOnline(Certificate chain[])
    {
        urls = new ArrayList();
        for(int i = 0; i < chain.length; i++)
        {
            X509Certificate cert = (X509Certificate)chain[i];
            LOGGER.info((new StringBuilder()).append("Checking certificate: ").append(cert.getSubjectDN()).toString());
            try
            {
                addUrl(CertificateUtil.getCRLURL(cert));
            }
            catch(CertificateParsingException e)
            {
                LOGGER.info("Skipped CRL url (certificate could not be parsed)");
            }
        }

    }

    protected void addUrl(String url)
    {
        try
        {
            addUrl(new URL(url));
        }
        catch(MalformedURLException e)
        {
            LOGGER.info((new StringBuilder()).append("Skipped CRL url (malformed): ").append(url).toString());
        }
    }

    protected void addUrl(URL url)
    {
        if(urls.contains(url))
        {
            LOGGER.info((new StringBuilder()).append("Skipped CRL url (duplicate): ").append(url).toString());
            return;
        } else
        {
            urls.add(url);
            LOGGER.info((new StringBuilder()).append("Added CRL url: ").append(url).toString());
            return;
        }
    }

    public Collection getEncoded(X509Certificate checkCert, String url)
    {
        if(checkCert == null)
            return null;
        List urllist = new ArrayList(urls);
        if(urllist.size() == 0)
        {
            LOGGER.info((new StringBuilder()).append("Looking for CRL for certificate ").append(checkCert.getSubjectDN()).toString());
            try
            {
                if(url == null)
                    url = CertificateUtil.getCRLURL(checkCert);
                if(url == null)
                    throw new NullPointerException();
                urllist.add(new URL(url));
                LOGGER.info((new StringBuilder()).append("Found CRL url: ").append(url).toString());
            }
            catch(Exception e)
            {
                LOGGER.info((new StringBuilder()).append("Skipped CRL url: ").append(e.getMessage()).toString());
            }
        }
        ArrayList ar = new ArrayList();
        for(Iterator i$ = urllist.iterator(); i$.hasNext();)
        {
            URL urlt = (URL)i$.next();
            try
            {
                LOGGER.info((new StringBuilder()).append("Checking CRL: ").append(urlt).toString());
                HttpURLConnection con = (HttpURLConnection)urlt.openConnection();
                if(con.getResponseCode() / 100 != 2)
                    throw new IOException(MessageLocalization.getComposedMessage("invalid.http.response.1", con.getResponseCode()));
                InputStream inp = (InputStream)con.getContent();
                byte buf[] = new byte[1024];
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                do
                {
                    int n = inp.read(buf, 0, buf.length);
                    if(n <= 0)
                        break;
                    bout.write(buf, 0, n);
                } while(true);
                inp.close();
                ar.add(bout.toByteArray());
                LOGGER.info((new StringBuilder()).append("Added CRL found at: ").append(urlt).toString());
            }
            catch(Exception e)
            {
                LOGGER.info((new StringBuilder()).append("Skipped CRL: ").append(e.getMessage()).append(" for ").append(urlt).toString());
            }
        }

        return ar;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(co/com/pdf/text/pdf/security/CrlClientOnline);
    protected List urls;

}
