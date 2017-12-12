// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LtvVerifier.java

package co.com.pdf.text.pdf.security;

import co.com.pdf.text.log.Logger;
import co.com.pdf.text.log.LoggerFactory;
import co.com.pdf.text.pdf.*;
import co.org.bouncy.cert.ocsp.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.security.cert.*;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.security:
//            RootStoreVerifier, VerificationException, VerificationOK, CRLVerifier, 
//            OCSPVerifier, PdfPKCS7, LtvVerification, CertificateVerifier

public class LtvVerifier extends RootStoreVerifier
{

    public LtvVerifier(PdfReader reader)
        throws GeneralSecurityException
    {
        super(null);
        option = LtvVerification.CertificateOption.SIGNING_CERTIFICATE;
        verifyRootCertificate = true;
        latestRevision = true;
        this.reader = reader;
        fields = reader.getAcroFields();
        List names = fields.getSignatureNames();
        signatureName = (String)names.get(names.size() - 1);
        signDate = new Date();
        pkcs7 = coversWholeDocument();
        LOGGER.info(String.format("Checking %ssignature %s", new Object[] {
            pkcs7.isTsp() ? "document-level timestamp " : "", signatureName
        }));
    }

    public void setVerifier(CertificateVerifier verifier)
    {
        this.verifier = verifier;
    }

    public void setCertificateOption(LtvVerification.CertificateOption option)
    {
        this.option = option;
    }

    public void setVerifyRootCertificate(boolean verifyRootCertificate)
    {
        this.verifyRootCertificate = verifyRootCertificate;
    }

    protected PdfPKCS7 coversWholeDocument()
        throws GeneralSecurityException
    {
        PdfPKCS7 pkcs7 = fields.verifySignature(signatureName);
        if(fields.signatureCoversWholeDocument(signatureName))
            LOGGER.info("The timestamp covers whole document.");
        else
            throw new VerificationException(null, "Signature doesn't cover whole document.");
        if(pkcs7.verify())
        {
            LOGGER.info("The signed document has not been modified.");
            return pkcs7;
        } else
        {
            throw new VerificationException(null, "The document was altered after the final signature was applied.");
        }
    }

    public List verify(List result)
        throws IOException, GeneralSecurityException
    {
        if(result == null)
            result = new ArrayList();
        while(pkcs7 != null) 
            result.addAll(verifySignature());
        return result;
    }

    public List verifySignature()
        throws GeneralSecurityException, IOException
    {
        LOGGER.info("Verifying signature.");
        List result = new ArrayList();
        Certificate chain[] = pkcs7.getSignCertificateChain();
        verifyChain(chain);
        int total = 1;
        if(LtvVerification.CertificateOption.WHOLE_CHAIN.equals(option))
            total = chain.length;
        List list;
        for(int i = 0; i < total; result.addAll(list))
        {
            X509Certificate signCert = (X509Certificate)chain[i++];
            X509Certificate issuerCert = null;
            if(i < chain.length)
                issuerCert = (X509Certificate)chain[i];
            LOGGER.info(signCert.getSubjectDN().getName());
            list = verify(signCert, issuerCert, signDate);
            if(list.size() != 0)
                continue;
            try
            {
                signCert.verify(signCert.getPublicKey());
                if(latestRevision && chain.length > 1)
                    list.add(new VerificationOK(signCert, getClass(), "Root certificate in final revision"));
                if(list.size() == 0 && verifyRootCertificate)
                    throw new GeneralSecurityException();
                if(chain.length > 1)
                    list.add(new VerificationOK(signCert, getClass(), "Root certificate passed without checking"));
            }
            catch(GeneralSecurityException e)
            {
                throw new VerificationException(signCert, "Couldn't verify with CRL or OCSP or trusted anchor");
            }
        }

        switchToPreviousRevision();
        return result;
    }

    public void verifyChain(Certificate chain[])
        throws GeneralSecurityException
    {
        for(int i = 0; i < chain.length; i++)
        {
            X509Certificate cert = (X509Certificate)chain[i];
            cert.checkValidity(signDate);
            if(i > 0)
                chain[i - 1].verify(chain[i].getPublicKey());
        }

        LOGGER.info((new StringBuilder()).append("All certificates are valid on ").append(signDate.toString()).toString());
    }

    public List verify(X509Certificate signCert, X509Certificate issuerCert, Date signDate)
        throws GeneralSecurityException, IOException
    {
        RootStoreVerifier rootStoreVerifier = new RootStoreVerifier(verifier);
        rootStoreVerifier.setRootStore(rootStore);
        CRLVerifier crlVerifier = new CRLVerifier(rootStoreVerifier, getCRLsFromDSS());
        crlVerifier.setRootStore(rootStore);
        crlVerifier.setOnlineCheckingAllowed(latestRevision || onlineCheckingAllowed);
        OCSPVerifier ocspVerifier = new OCSPVerifier(crlVerifier, getOCSPResponsesFromDSS());
        ocspVerifier.setRootStore(rootStore);
        ocspVerifier.setOnlineCheckingAllowed(latestRevision || onlineCheckingAllowed);
        return ocspVerifier.verify(signCert, issuerCert, signDate);
    }

    public void switchToPreviousRevision()
        throws IOException, GeneralSecurityException
    {
        LOGGER.info("Switching to previous revision.");
        latestRevision = false;
        dss = reader.getCatalog().getAsDict(PdfName.DSS);
        Calendar cal = pkcs7.getTimeStampDate();
        if(cal == null)
            cal = pkcs7.getSignDate();
        signDate = cal.getTime();
        List names = fields.getSignatureNames();
        if(names.size() > 1)
        {
            signatureName = (String)names.get(names.size() - 2);
            reader = new PdfReader(fields.extractRevision(signatureName));
            fields = reader.getAcroFields();
            names = fields.getSignatureNames();
            signatureName = (String)names.get(names.size() - 1);
            pkcs7 = coversWholeDocument();
            LOGGER.info(String.format("Checking %ssignature %s", new Object[] {
                pkcs7.isTsp() ? "document-level timestamp " : "", signatureName
            }));
        } else
        {
            LOGGER.info("No signatures in revision");
            pkcs7 = null;
        }
    }

    public List getCRLsFromDSS()
        throws GeneralSecurityException, IOException
    {
        List crls = new ArrayList();
        if(dss == null)
            return crls;
        PdfArray crlarray = dss.getAsArray(PdfName.CRLS);
        if(crlarray == null)
            return crls;
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        for(int i = 0; i < crlarray.size(); i++)
        {
            PRStream stream = (PRStream)crlarray.getAsStream(i);
            X509CRL crl = (X509CRL)cf.generateCRL(new ByteArrayInputStream(PdfReader.getStreamBytes(stream)));
            crls.add(crl);
        }

        return crls;
    }

    public List getOCSPResponsesFromDSS()
        throws IOException, GeneralSecurityException
    {
        List ocsps = new ArrayList();
        if(dss == null)
            return ocsps;
        PdfArray ocsparray = dss.getAsArray(PdfName.OCSPS);
        if(ocsparray == null)
            return ocsps;
        for(int i = 0; i < ocsparray.size(); i++)
        {
            PRStream stream = (PRStream)ocsparray.getAsStream(i);
            OCSPResp ocspResponse = new OCSPResp(PdfReader.getStreamBytes(stream));
            if(ocspResponse.getStatus() != 0)
                continue;
            try
            {
                ocsps.add((BasicOCSPResp)ocspResponse.getResponseObject());
            }
            catch(OCSPException e)
            {
                throw new GeneralSecurityException(e);
            }
        }

        return ocsps;
    }

    protected static final Logger LOGGER = LoggerFactory.getLogger(co/com/pdf/text/pdf/security/LtvVerifier);
    protected LtvVerification.CertificateOption option;
    protected boolean verifyRootCertificate;
    protected PdfReader reader;
    protected AcroFields fields;
    protected Date signDate;
    protected String signatureName;
    protected PdfPKCS7 pkcs7;
    protected boolean latestRevision;
    protected PdfDictionary dss;

}
