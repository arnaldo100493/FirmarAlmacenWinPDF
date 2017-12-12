// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaCertStore.java

package co.org.bouncy.cert.jcajce;

import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.util.CollectionStore;
import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.*;

public class JcaCertStore extends CollectionStore
{

    public JcaCertStore(Collection collection)
        throws CertificateEncodingException
    {
        super(convertCerts(collection));
    }

    private static Collection convertCerts(Collection collection)
        throws CertificateEncodingException
    {
        List list = new ArrayList(collection.size());
        for(Iterator it = collection.iterator(); it.hasNext();)
        {
            Object o = it.next();
            if(o instanceof X509Certificate)
            {
                X509Certificate cert = (X509Certificate)o;
                try
                {
                    list.add(new X509CertificateHolder(cert.getEncoded()));
                }
                catch(IOException e)
                {
                    throw new CertificateEncodingException((new StringBuilder()).append("unable to read encoding: ").append(e.getMessage()).toString());
                }
            } else
            {
                list.add((X509CertificateHolder)o);
            }
        }

        return list;
    }
}
