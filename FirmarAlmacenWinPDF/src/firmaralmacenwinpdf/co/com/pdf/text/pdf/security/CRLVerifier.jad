// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CRLVerifier.java

package co.com.pdf.text.pdf.security;

import co.com.pdf.text.log.Logger;
import co.com.pdf.text.log.LoggerFactory;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.*;
import java.util.*;
import javax.security.auth.x500.X500Principal;

// Referenced classes of package co.com.pdf.text.pdf.security:
//            RootStoreVerifier, VerificationOK, VerificationException, CertificateVerifier, 
//            CertificateUtil

public class CRLVerifier extends RootStoreVerifier
{

    public CRLVerifier(CertificateVerifier verifier, List crls)
    {
        super(verifier);
        this.crls = crls;
    }

    public List verify(X509Certificate signCert, X509Certificate issuerCert, Date signDate)
        throws GeneralSecurityException, IOException
    {
        List result = new ArrayList();
        int validCrlsFound = 0;
        if(crls != null)
        {
            Iterator i$ = crls.iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                X509CRL crl = (X509CRL)i$.next();
                if(verify(crl, signCert, issuerCert, signDate))
                    validCrlsFound++;
            } while(true);
        }
        boolean online = false;
        if(onlineCheckingAllowed && validCrlsFound == 0 && verify(getCRL(signCert, issuerCert), signCert, issuerCert, signDate))
        {
            validCrlsFound++;
            online = true;
        }
        LOGGER.info((new StringBuilder()).append("Valid CRLs found: ").append(validCrlsFound).toString());
        if(validCrlsFound > 0)
            result.add(new VerificationOK(signCert, getClass(), (new StringBuilder()).append("Valid CRLs found: ").append(validCrlsFound).append(online ? " (online)" : "").toString()));
        if(verifier != null)
            result.addAll(verifier.verify(signCert, issuerCert, signDate));
        return result;
    }

    public boolean verify(X509CRL crl, X509Certificate signCert, X509Certificate issuerCert, Date signDate)
        throws GeneralSecurityException
    {
        if(crl == null || signDate == null)
            return false;
        if(crl.getIssuerX500Principal().equals(signCert.getIssuerX500Principal()) && signDate.after(crl.getThisUpdate()) && signDate.before(crl.getNextUpdate()))
        {
            if(isSignatureValid(crl, issuerCert) && crl.isRevoked(signCert))
                throw new VerificationException(signCert, "The certificate has been revoked.");
            else
                return true;
        } else
        {
            return false;
        }
    }

    public X509CRL getCRL(X509Certificate signCert, X509Certificate issuerCert)
    {
        if(issuerCert == null)
            issuerCert = signCert;
        String crlurl;
        CertificateFactory cf;
        try
        {
            crlurl = CertificateUtil.getCRLURL(signCert);
            if(crlurl == null)
                return null;
        }
        catch(IOException e)
        {
            return null;
        }
        catch(GeneralSecurityException e)
        {
            return null;
        }
        LOGGER.info((new StringBuilder()).append("Getting CRL from ").append(crlurl).toString());
        cf = CertificateFactory.getInstance("X.509");
        return (X509CRL)cf.generateCRL((new URL(crlurl)).openStream());
    }

    public boolean isSignatureValid(X509CRL crl, X509Certificate crlIssuer)
    {
        GeneralSecurityException e;
        if(crlIssuer != null)
            try
            {
                crl.verify(crlIssuer.getPublicKey());
                return true;
            }
            // Misplaced declaration of an exception variable
            catch(GeneralSecurityException e)
            {
                LOGGER.warn("CRL not issued by the same authority as the certificate that is being checked");
            }
        if(rootStore == null)
            return false;
        Enumeration aliases = rootStore.aliases();
_L1:
        String alias;
        if(!aliases.hasMoreElements())
            break MISSING_BLOCK_LABEL_113;
        alias = (String)aliases.nextElement();
        if(rootStore.isCertificateEntry(alias)) goto _L2; else goto _L1
_L2:
        try
        {
            X509Certificate anchor = (X509Certificate)rootStore.getCertificate(alias);
            crl.verify(anchor.getPublicKey());
            return true;
        }
        catch(GeneralSecurityException e) { }
          goto _L1
        aliases;
        return false;
        return false;
    }

    protected static final Logger LOGGER = LoggerFactory.getLogger(co/com/pdf/text/pdf/security/CRLVerifier);
    List crls;

}
