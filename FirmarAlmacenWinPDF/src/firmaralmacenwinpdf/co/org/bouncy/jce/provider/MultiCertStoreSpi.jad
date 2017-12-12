// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MultiCertStoreSpi.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.jce.MultiCertStoreParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.cert.*;
import java.util.*;

public class MultiCertStoreSpi extends CertStoreSpi
{

    public MultiCertStoreSpi(CertStoreParameters params)
        throws InvalidAlgorithmParameterException
    {
        super(params);
        if(!(params instanceof MultiCertStoreParameters))
        {
            throw new InvalidAlgorithmParameterException((new StringBuilder()).append("co.org.bouncy.jce.provider.MultiCertStoreSpi: parameter must be a MultiCertStoreParameters object\n").append(params.toString()).toString());
        } else
        {
            this.params = (MultiCertStoreParameters)params;
            return;
        }
    }

    public Collection engineGetCertificates(CertSelector certSelector)
        throws CertStoreException
    {
        List allCerts;
label0:
        {
            boolean searchAllStores = params.getSearchAllStores();
            Iterator iter = params.getCertStores().iterator();
            allCerts = ((List) (searchAllStores ? ((List) (new ArrayList())) : Collections.EMPTY_LIST));
            Collection certs;
label1:
            do
            {
                for(; iter.hasNext(); allCerts.addAll(certs))
                {
                    CertStore store = (CertStore)iter.next();
                    certs = store.getCertificates(certSelector);
                    if(!searchAllStores)
                        continue label1;
                }

                break label0;
            } while(certs.isEmpty());
            return certs;
        }
        return allCerts;
    }

    public Collection engineGetCRLs(CRLSelector crlSelector)
        throws CertStoreException
    {
        List allCRLs;
label0:
        {
            boolean searchAllStores = params.getSearchAllStores();
            Iterator iter = params.getCertStores().iterator();
            allCRLs = ((List) (searchAllStores ? ((List) (new ArrayList())) : Collections.EMPTY_LIST));
            Collection crls;
label1:
            do
            {
                for(; iter.hasNext(); allCRLs.addAll(crls))
                {
                    CertStore store = (CertStore)iter.next();
                    crls = store.getCRLs(crlSelector);
                    if(!searchAllStores)
                        continue label1;
                }

                break label0;
            } while(crls.isEmpty());
            return crls;
        }
        return allCRLs;
    }

    private MultiCertStoreParameters params;
}
