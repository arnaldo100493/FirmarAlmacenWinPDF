// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RootStoreVerifier.java

package co.com.pdf.text.pdf.security;

import co.com.pdf.text.log.Logger;
import co.com.pdf.text.log.LoggerFactory;
import java.io.IOException;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.security:
//            CertificateVerifier, VerificationOK

public class RootStoreVerifier extends CertificateVerifier
{

    public RootStoreVerifier(CertificateVerifier verifier)
    {
        super(verifier);
        rootStore = null;
    }

    public void setRootStore(KeyStore keyStore)
    {
        rootStore = keyStore;
    }

    public List verify(X509Certificate signCert, X509Certificate issuerCert, Date signDate)
        throws GeneralSecurityException, IOException
    {
        LOGGER.info((new StringBuilder()).append("Root store verification: ").append(signCert.getSubjectDN().getName()).toString());
        if(rootStore == null)
            return super.verify(signCert, issuerCert, signDate);
        List result;
        Enumeration aliases;
        result = new ArrayList();
        aliases = rootStore.aliases();
_L1:
        String alias;
        if(!aliases.hasMoreElements())
            break MISSING_BLOCK_LABEL_183;
        alias = (String)aliases.nextElement();
        if(rootStore.isCertificateEntry(alias)) goto _L2; else goto _L1
_L2:
        try
        {
            X509Certificate anchor = (X509Certificate)rootStore.getCertificate(alias);
            signCert.verify(anchor.getPublicKey());
            LOGGER.info("Certificate verified against root store");
            result.add(new VerificationOK(signCert, getClass(), "Certificate verified against root store."));
            result.addAll(super.verify(signCert, issuerCert, signDate));
            return result;
        }
        catch(GeneralSecurityException e) { }
          goto _L1
        result.addAll(super.verify(signCert, issuerCert, signDate));
        return result;
        GeneralSecurityException e;
        e;
        return super.verify(signCert, issuerCert, signDate);
    }

    protected static final Logger LOGGER = LoggerFactory.getLogger(co/com/pdf/text/pdf/security/RootStoreVerifier);
    protected KeyStore rootStore;

}
