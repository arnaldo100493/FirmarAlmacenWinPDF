// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509StoreLDAPCerts.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.jce.X509LDAPCertStoreParameters;
import co.org.bouncy.util.Selector;
import co.org.bouncy.util.StoreException;
import co.org.bouncy.x509.util.LDAPStoreHelper;
import co.org.bouncy.x509_.*;
import java.util.*;

public class X509StoreLDAPCerts extends X509StoreSpi
{

    public X509StoreLDAPCerts()
    {
    }

    public void engineInit(X509StoreParameters params)
    {
        if(!(params instanceof X509LDAPCertStoreParameters))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Initialization parameters must be an instance of ").append(co/org/bouncy/jce/X509LDAPCertStoreParameters.getName()).append(".").toString());
        } else
        {
            helper = new LDAPStoreHelper((X509LDAPCertStoreParameters)params);
            return;
        }
    }

    public Collection engineGetMatches(Selector selector)
        throws StoreException
    {
        if(!(selector instanceof X509CertStoreSelector))
            return Collections.EMPTY_SET;
        X509CertStoreSelector xselector = (X509CertStoreSelector)selector;
        Set set = new HashSet();
        if(xselector.getBasicConstraints() > 0)
        {
            set.addAll(helper.getCACertificates(xselector));
            set.addAll(getCertificatesFromCrossCertificatePairs(xselector));
        } else
        if(xselector.getBasicConstraints() == -2)
        {
            set.addAll(helper.getUserCertificates(xselector));
        } else
        {
            set.addAll(helper.getUserCertificates(xselector));
            set.addAll(helper.getCACertificates(xselector));
            set.addAll(getCertificatesFromCrossCertificatePairs(xselector));
        }
        return set;
    }

    private Collection getCertificatesFromCrossCertificatePairs(X509CertStoreSelector xselector)
        throws StoreException
    {
        Set set = new HashSet();
        X509CertPairStoreSelector ps = new X509CertPairStoreSelector();
        ps.setForwardSelector(xselector);
        ps.setReverseSelector(new X509CertStoreSelector());
        Set crossCerts = new HashSet(helper.getCrossCertificatePairs(ps));
        Set forward = new HashSet();
        Set reverse = new HashSet();
        Iterator it = crossCerts.iterator();
        do
        {
            if(!it.hasNext())
                break;
            X509CertificatePair pair = (X509CertificatePair)it.next();
            if(pair.getForward() != null)
                forward.add(pair.getForward());
            if(pair.getReverse() != null)
                reverse.add(pair.getReverse());
        } while(true);
        set.addAll(forward);
        set.addAll(reverse);
        return set;
    }

    private LDAPStoreHelper helper;
}
