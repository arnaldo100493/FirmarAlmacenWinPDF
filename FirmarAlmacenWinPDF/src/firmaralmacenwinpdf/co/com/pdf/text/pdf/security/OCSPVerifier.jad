// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OCSPVerifier.java

package co.com.pdf.text.pdf.security;

import co.com.pdf.text.log.Logger;
import co.com.pdf.text.log.LoggerFactory;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.cert.jcajce.JcaX509CertificateConverter;
import co.org.bouncy.cert.ocsp.*;
import co.org.bouncy.operator.OperatorCreationException;
import co.org.bouncy.operator.bc.BcDigestCalculatorProvider;
import co.org.bouncy.operator.jcajce.JcaContentVerifierProviderBuilder;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.security:
//            RootStoreVerifier, VerificationOK, VerificationException, OcspClientBouncyCastle, 
//            CertificateVerifier

public class OCSPVerifier extends RootStoreVerifier
{

    public OCSPVerifier(CertificateVerifier verifier, List ocsps)
    {
        super(verifier);
        this.ocsps = ocsps;
    }

    public List verify(X509Certificate signCert, X509Certificate issuerCert, Date signDate)
        throws GeneralSecurityException, IOException
    {
        List result = new ArrayList();
        int validOCSPsFound = 0;
        if(ocsps != null)
        {
            Iterator i$ = ocsps.iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                BasicOCSPResp ocspResp = (BasicOCSPResp)i$.next();
                if(verify(ocspResp, signCert, issuerCert, signDate))
                    validOCSPsFound++;
            } while(true);
        }
        boolean online = false;
        if(onlineCheckingAllowed && validOCSPsFound == 0 && verify(getOcspResponse(signCert, issuerCert), signCert, issuerCert, signDate))
        {
            validOCSPsFound++;
            online = true;
        }
        LOGGER.info((new StringBuilder()).append("Valid OCSPs found: ").append(validOCSPsFound).toString());
        if(validOCSPsFound > 0)
            result.add(new VerificationOK(signCert, getClass(), (new StringBuilder()).append("Valid OCSPs Found: ").append(validOCSPsFound).append(online ? " (online)" : "").toString()));
        if(verifier != null)
            result.addAll(verifier.verify(signCert, issuerCert, signDate));
        return result;
    }

    public boolean verify(BasicOCSPResp ocspResp, X509Certificate signCert, X509Certificate issuerCert, Date signDate)
        throws GeneralSecurityException, IOException
    {
        if(ocspResp == null)
            return false;
        SingleResp resp[] = ocspResp.getResponses();
        for(int i = 0; i < resp.length; i++)
        {
            if(!signCert.getSerialNumber().equals(resp[i].getCertID().getSerialNumber()))
                continue;
            try
            {
                if(issuerCert == null)
                    issuerCert = signCert;
                if(!resp[i].getCertID().matchesIssuer(new X509CertificateHolder(issuerCert.getEncoded()), new BcDigestCalculatorProvider()))
                {
                    LOGGER.info("OCSP: Issuers doesn't match.");
                    continue;
                }
            }
            catch(OCSPException e)
            {
                continue;
            }
            Date nextUpdate = resp[i].getNextUpdate();
            if(nextUpdate == null)
            {
                nextUpdate = new Date(resp[i].getThisUpdate().getTime() + 0x2bf20L);
                LOGGER.info(String.format("No 'next update' for OCSP Response; assuming %s", new Object[] {
                    nextUpdate
                }));
            }
            if(signDate.after(nextUpdate))
            {
                LOGGER.info(String.format("OCSP no longer valid: %s after %s", new Object[] {
                    signDate, nextUpdate
                }));
                continue;
            }
            Object status = resp[i].getCertStatus();
            if(status == CertificateStatus.GOOD)
            {
                isValidResponse(ocspResp, issuerCert);
                return true;
            }
        }

        return false;
    }

    public void isValidResponse(BasicOCSPResp ocspResp, X509Certificate issuerCert)
        throws GeneralSecurityException, IOException
    {
        X509Certificate responderCert = issuerCert;
        X509CertificateHolder certHolders[] = ocspResp.getCerts();
        if(certHolders.length > 0)
        {
            responderCert = (new JcaX509CertificateConverter()).setProvider("BC").getCertificate(certHolders[0]);
            try
            {
                responderCert.verify(issuerCert.getPublicKey());
            }
            catch(GeneralSecurityException e)
            {
                if(super.verify(responderCert, issuerCert, null).size() == 0)
                    throw new VerificationException(responderCert, "Responder certificate couldn't be verified");
            }
        }
        if(!verifyResponse(ocspResp, responderCert))
            throw new VerificationException(responderCert, "OCSP response could not be verified");
        else
            return;
    }

    public boolean verifyResponse(BasicOCSPResp ocspResp, X509Certificate responderCert)
    {
        if(isSignatureValid(ocspResp, responderCert))
            return true;
        if(rootStore == null)
            return false;
        Enumeration aliases = rootStore.aliases();
_L1:
        String alias;
        if(!aliases.hasMoreElements())
            break MISSING_BLOCK_LABEL_106;
        alias = (String)aliases.nextElement();
        if(rootStore.isCertificateEntry(alias)) goto _L2; else goto _L1
_L2:
        try
        {
            X509Certificate anchor = (X509Certificate)rootStore.getCertificate(alias);
            if(isSignatureValid(ocspResp, anchor))
                return true;
        }
        catch(GeneralSecurityException e) { }
          goto _L1
        GeneralSecurityException e;
        e;
        return false;
        return false;
    }

    public boolean isSignatureValid(BasicOCSPResp ocspResp, Certificate responderCert)
    {
        try
        {
            co.org.bouncy.operator.ContentVerifierProvider verifierProvider = (new JcaContentVerifierProviderBuilder()).setProvider("BC").build(responderCert.getPublicKey());
            return ocspResp.isSignatureValid(verifierProvider);
        }
        catch(OperatorCreationException e)
        {
            return false;
        }
        catch(OCSPException e)
        {
            return false;
        }
    }

    public BasicOCSPResp getOcspResponse(X509Certificate signCert, X509Certificate issuerCert)
    {
        if(signCert == null && issuerCert == null)
            return null;
        OcspClientBouncyCastle ocsp = new OcspClientBouncyCastle();
        BasicOCSPResp ocspResp = ocsp.getBasicOCSPResp(signCert, issuerCert, null);
        if(ocspResp == null)
            return null;
        SingleResp resp[] = ocspResp.getResponses();
        for(int i = 0; i < resp.length; i++)
        {
            Object status = resp[i].getCertStatus();
            if(status == CertificateStatus.GOOD)
                return ocspResp;
        }

        return null;
    }

    protected static final Logger LOGGER = LoggerFactory.getLogger(co/com/pdf/text/pdf/security/OCSPVerifier);
    protected List ocsps;

}
