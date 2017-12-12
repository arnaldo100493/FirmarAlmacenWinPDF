// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509StoreLDAPCRLs.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.jce.X509LDAPCertStoreParameters;
import co.org.bouncy.util.Selector;
import co.org.bouncy.util.StoreException;
import co.org.bouncy.x509.util.LDAPStoreHelper;
import co.org.bouncy.x509_.*;
import java.util.*;

public class X509StoreLDAPCRLs extends X509StoreSpi
{

    public X509StoreLDAPCRLs()
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
        if(!(selector instanceof X509CRLStoreSelector))
            return Collections.EMPTY_SET;
        X509CRLStoreSelector xselector = (X509CRLStoreSelector)selector;
        Set set = new HashSet();
        if(xselector.isDeltaCRLIndicatorEnabled())
        {
            set.addAll(helper.getDeltaCertificateRevocationLists(xselector));
        } else
        {
            set.addAll(helper.getDeltaCertificateRevocationLists(xselector));
            set.addAll(helper.getAttributeAuthorityRevocationLists(xselector));
            set.addAll(helper.getAttributeCertificateRevocationLists(xselector));
            set.addAll(helper.getAuthorityRevocationLists(xselector));
            set.addAll(helper.getCertificateRevocationLists(xselector));
        }
        return set;
    }

    private LDAPStoreHelper helper;
}
