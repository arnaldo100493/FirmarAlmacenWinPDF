// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaCertStoreBuilder.java

package co.org.bouncy.cert.jcajce;

import co.org.bouncy.cert.X509CRLHolder;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.util.Store;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.cert.*;
import java.util.*;

// Referenced classes of package co.org.bouncy.cert.jcajce:
//            JcaX509CertificateConverter, JcaX509CRLConverter

public class JcaCertStoreBuilder
{

    public JcaCertStoreBuilder()
    {
        certs = new ArrayList();
        crls = new ArrayList();
        certificateConverter = new JcaX509CertificateConverter();
        crlConverter = new JcaX509CRLConverter();
        type = "Collection";
    }

    public JcaCertStoreBuilder addCertificates(Store certStore)
    {
        certs.addAll(certStore.getMatches(null));
        return this;
    }

    public JcaCertStoreBuilder addCertificate(X509CertificateHolder cert)
    {
        certs.add(cert);
        return this;
    }

    public JcaCertStoreBuilder addCRLs(Store crlStore)
    {
        crls.addAll(crlStore.getMatches(null));
        return this;
    }

    public JcaCertStoreBuilder addCRL(X509CRLHolder crl)
    {
        crls.add(crl);
        return this;
    }

    public JcaCertStoreBuilder setProvider(String providerName)
    {
        certificateConverter.setProvider(providerName);
        crlConverter.setProvider(providerName);
        provider = providerName;
        return this;
    }

    public JcaCertStoreBuilder setProvider(Provider provider)
    {
        certificateConverter.setProvider(provider);
        crlConverter.setProvider(provider);
        this.provider = provider;
        return this;
    }

    public JcaCertStoreBuilder setType(String type)
    {
        this.type = type;
        return this;
    }

    public CertStore build()
        throws GeneralSecurityException
    {
        CollectionCertStoreParameters params = convertHolders(certificateConverter, crlConverter);
        if(provider instanceof String)
            return CertStore.getInstance(type, params, (String)provider);
        if(provider instanceof Provider)
            return CertStore.getInstance(type, params, (Provider)provider);
        else
            return CertStore.getInstance(type, params);
    }

    private CollectionCertStoreParameters convertHolders(JcaX509CertificateConverter certificateConverter, JcaX509CRLConverter crlConverter)
        throws CertificateException, CRLException
    {
        List jcaObjs = new ArrayList(certs.size() + crls.size());
        for(Iterator it = certs.iterator(); it.hasNext(); jcaObjs.add(certificateConverter.getCertificate((X509CertificateHolder)it.next())));
        for(Iterator it = crls.iterator(); it.hasNext(); jcaObjs.add(crlConverter.getCRL((X509CRLHolder)it.next())));
        return new CollectionCertStoreParameters(jcaObjs);
    }

    private List certs;
    private List crls;
    private Object provider;
    private JcaX509CertificateConverter certificateConverter;
    private JcaX509CRLConverter crlConverter;
    private String type;
}
