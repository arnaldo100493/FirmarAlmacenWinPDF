// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509StoreLDAPCertPairs.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.jce.X509LDAPCertStoreParameters;
import co.org.bouncy.util.Selector;
import co.org.bouncy.util.StoreException;
import co.org.bouncy.x509.util.LDAPStoreHelper;
import co.org.bouncy.x509_.*;
import java.util.*;

public class X509StoreLDAPCertPairs extends X509StoreSpi
{

    public X509StoreLDAPCertPairs()
    {
    }

    public void engineInit(X509StoreParameters parameters)
    {
        if(!(parameters instanceof X509LDAPCertStoreParameters))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Initialization parameters must be an instance of ").append(co/org/bouncy/jce/X509LDAPCertStoreParameters.getName()).append(".").toString());
        } else
        {
            helper = new LDAPStoreHelper((X509LDAPCertStoreParameters)parameters);
            return;
        }
    }

    public Collection engineGetMatches(Selector selector)
        throws StoreException
    {
        if(!(selector instanceof X509CertPairStoreSelector))
        {
            return Collections.EMPTY_SET;
        } else
        {
            X509CertPairStoreSelector xselector = (X509CertPairStoreSelector)selector;
            Set set = new HashSet();
            set.addAll(helper.getCrossCertificatePairs(xselector));
            return set;
        }
    }

    private LDAPStoreHelper helper;
}
