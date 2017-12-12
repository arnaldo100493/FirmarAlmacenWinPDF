// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaAttrCertStore.java

package co.org.bouncy.cert.jcajce;

import co.org.bouncy.util.CollectionStore;
import co.org.bouncy.x509_.X509AttributeCertificate;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.org.bouncy.cert.jcajce:
//            JcaX509AttributeCertificateHolder

public class JcaAttrCertStore extends CollectionStore
{

    public JcaAttrCertStore(Collection collection)
        throws IOException
    {
        super(convertCerts(collection));
    }

    public JcaAttrCertStore(X509AttributeCertificate attrCert)
        throws IOException
    {
        this(((Collection) (Collections.singletonList(attrCert))));
    }

    private static Collection convertCerts(Collection collection)
        throws IOException
    {
        List list = new ArrayList(collection.size());
        for(Iterator it = collection.iterator(); it.hasNext();)
        {
            Object o = it.next();
            if(o instanceof X509AttributeCertificate)
            {
                X509AttributeCertificate cert = (X509AttributeCertificate)o;
                list.add(new JcaX509AttributeCertificateHolder(cert));
            } else
            {
                list.add(o);
            }
        }

        return list;
    }
}
