// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKIXCRLUtil.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.util.StoreException;
import co.org.bouncy.x509_.*;
import java.security.cert.*;
import java.util.*;

// Referenced classes of package co.org.bouncy.jce.provider:
//            AnnotatedException

public class PKIXCRLUtil
{

    public PKIXCRLUtil()
    {
    }

    public Set findCRLs(X509CRLStoreSelector crlselect, ExtendedPKIXParameters paramsPKIX, Date currentDate)
        throws AnnotatedException
    {
        Set initialSet = new HashSet();
        try
        {
            initialSet.addAll(findCRLs(crlselect, paramsPKIX.getAdditionalStores()));
            initialSet.addAll(findCRLs(crlselect, paramsPKIX.getStores()));
            initialSet.addAll(findCRLs(crlselect, paramsPKIX.getCertStores()));
        }
        catch(AnnotatedException e)
        {
            throw new AnnotatedException("Exception obtaining complete CRLs.", e);
        }
        Set finalSet = new HashSet();
        Date validityDate = currentDate;
        if(paramsPKIX.getDate() != null)
            validityDate = paramsPKIX.getDate();
        Iterator it = initialSet.iterator();
        do
        {
            if(!it.hasNext())
                break;
            X509CRL crl = (X509CRL)it.next();
            if(crl.getNextUpdate().after(validityDate))
            {
                X509Certificate cert = crlselect.getCertificateChecking();
                if(cert != null)
                {
                    if(crl.getThisUpdate().before(cert.getNotAfter()))
                        finalSet.add(crl);
                } else
                {
                    finalSet.add(crl);
                }
            }
        } while(true);
        return finalSet;
    }

    public Set findCRLs(X509CRLStoreSelector crlselect, PKIXParameters paramsPKIX)
        throws AnnotatedException
    {
        Set completeSet = new HashSet();
        try
        {
            completeSet.addAll(findCRLs(crlselect, paramsPKIX.getCertStores()));
        }
        catch(AnnotatedException e)
        {
            throw new AnnotatedException("Exception obtaining complete CRLs.", e);
        }
        return completeSet;
    }

    private final Collection findCRLs(X509CRLStoreSelector crlSelect, List crlStores)
        throws AnnotatedException
    {
        Set crls = new HashSet();
        Iterator iter = crlStores.iterator();
        AnnotatedException lastException = null;
        boolean foundValidStore = false;
        while(iter.hasNext()) 
        {
            Object obj = iter.next();
            if(obj instanceof X509Store)
            {
                X509Store store = (X509Store)obj;
                try
                {
                    crls.addAll(store.getMatches(crlSelect));
                    foundValidStore = true;
                }
                catch(StoreException e)
                {
                    lastException = new AnnotatedException("Exception searching in X.509 CRL store.", e);
                }
            } else
            {
                CertStore store = (CertStore)obj;
                try
                {
                    crls.addAll(store.getCRLs(crlSelect));
                    foundValidStore = true;
                }
                catch(CertStoreException e)
                {
                    lastException = new AnnotatedException("Exception searching in X.509 CRL store.", e);
                }
            }
        }
        if(!foundValidStore && lastException != null)
            throw lastException;
        else
            return crls;
    }
}
