// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RFC3281CertPathUtilities.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.jce.exception.ExtCertPathValidatorException;
import co.org.bouncy.x509_.*;
import java.io.IOException;
import java.security.*;
import java.security.cert.*;
import java.util.*;
import javax.security.auth.x500.X500Principal;

// Referenced classes of package co.org.bouncy.jce.provider:
//            AnnotatedException, CertStatus, ReasonsMask, CertPathValidatorUtilities, 
//            RFC3280CertPathUtilities

class RFC3281CertPathUtilities
{

    RFC3281CertPathUtilities()
    {
    }

    protected static void processAttrCert7(X509AttributeCertificate attrCert, CertPath certPath, CertPath holderCertPath, ExtendedPKIXParameters pkixParams)
        throws CertPathValidatorException
    {
        Set set = attrCert.getCriticalExtensionOIDs();
        if(set.contains(TARGET_INFORMATION))
            try
            {
                TargetInformation.getInstance(CertPathValidatorUtilities.getExtensionValue(attrCert, TARGET_INFORMATION));
            }
            catch(AnnotatedException e)
            {
                throw new ExtCertPathValidatorException("Target information extension could not be read.", e);
            }
            catch(IllegalArgumentException e)
            {
                throw new ExtCertPathValidatorException("Target information extension could not be read.", e);
            }
        set.remove(TARGET_INFORMATION);
        for(Iterator it = pkixParams.getAttrCertCheckers().iterator(); it.hasNext(); ((PKIXAttrCertChecker)it.next()).check(attrCert, certPath, holderCertPath, set));
        if(!set.isEmpty())
            throw new CertPathValidatorException((new StringBuilder()).append("Attribute certificate contains unsupported critical extensions: ").append(set).toString());
        else
            return;
    }

    protected static void checkCRLs(X509AttributeCertificate attrCert, ExtendedPKIXParameters paramsPKIX, X509Certificate issuerCert, Date validDate, List certPathCerts)
        throws CertPathValidatorException
    {
        if(paramsPKIX.isRevocationEnabled())
            if(attrCert.getExtensionValue(NO_REV_AVAIL) == null)
            {
                CRLDistPoint crldp = null;
                try
                {
                    crldp = CRLDistPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(attrCert, CRL_DISTRIBUTION_POINTS));
                }
                catch(AnnotatedException e)
                {
                    throw new CertPathValidatorException("CRL distribution point extension could not be read.", e);
                }
                try
                {
                    CertPathValidatorUtilities.addAdditionalStoresFromCRLDistributionPoint(crldp, paramsPKIX);
                }
                catch(AnnotatedException e)
                {
                    throw new CertPathValidatorException("No additional CRL locations could be decoded from CRL distribution point extension.", e);
                }
                CertStatus certStatus = new CertStatus();
                ReasonsMask reasonsMask = new ReasonsMask();
                AnnotatedException lastException = null;
                boolean validCrlFound = false;
                if(crldp != null)
                {
                    DistributionPoint dps[] = null;
                    try
                    {
                        dps = crldp.getDistributionPoints();
                    }
                    catch(Exception e)
                    {
                        throw new ExtCertPathValidatorException("Distribution points could not be read.", e);
                    }
                    try
                    {
                        for(int i = 0; i < dps.length && certStatus.getCertStatus() == 11 && !reasonsMask.isAllReasons(); i++)
                        {
                            ExtendedPKIXParameters paramsPKIXClone = (ExtendedPKIXParameters)paramsPKIX.clone();
                            checkCRL(dps[i], attrCert, paramsPKIXClone, validDate, issuerCert, certStatus, reasonsMask, certPathCerts);
                            validCrlFound = true;
                        }

                    }
                    catch(AnnotatedException e)
                    {
                        lastException = new AnnotatedException("No valid CRL for distribution point found.", e);
                    }
                }
                if(certStatus.getCertStatus() == 11 && !reasonsMask.isAllReasons())
                    try
                    {
                        ASN1Primitive issuer = null;
                        try
                        {
                            issuer = (new ASN1InputStream(((X500Principal)attrCert.getIssuer().getPrincipals()[0]).getEncoded())).readObject();
                        }
                        catch(Exception e)
                        {
                            throw new AnnotatedException("Issuer from certificate for CRL could not be reencoded.", e);
                        }
                        DistributionPoint dp = new DistributionPoint(new DistributionPointName(0, new GeneralNames(new GeneralName(4, issuer))), null, null);
                        ExtendedPKIXParameters paramsPKIXClone = (ExtendedPKIXParameters)paramsPKIX.clone();
                        checkCRL(dp, attrCert, paramsPKIXClone, validDate, issuerCert, certStatus, reasonsMask, certPathCerts);
                        validCrlFound = true;
                    }
                    catch(AnnotatedException e)
                    {
                        lastException = new AnnotatedException("No valid CRL for distribution point found.", e);
                    }
                if(!validCrlFound)
                    throw new ExtCertPathValidatorException("No valid CRL found.", lastException);
                if(certStatus.getCertStatus() != 11)
                {
                    String message = (new StringBuilder()).append("Attribute certificate revocation after ").append(certStatus.getRevocationDate()).toString();
                    message = (new StringBuilder()).append(message).append(", reason: ").append(RFC3280CertPathUtilities.crlReasons[certStatus.getCertStatus()]).toString();
                    throw new CertPathValidatorException(message);
                }
                if(!reasonsMask.isAllReasons() && certStatus.getCertStatus() == 11)
                    certStatus.setCertStatus(12);
                if(certStatus.getCertStatus() == 12)
                    throw new CertPathValidatorException("Attribute certificate status could not be determined.");
            } else
            if(attrCert.getExtensionValue(CRL_DISTRIBUTION_POINTS) != null || attrCert.getExtensionValue(AUTHORITY_INFO_ACCESS) != null)
                throw new CertPathValidatorException("No rev avail extension is set, but also an AC revocation pointer.");
    }

    protected static void additionalChecks(X509AttributeCertificate attrCert, ExtendedPKIXParameters pkixParams)
        throws CertPathValidatorException
    {
        for(Iterator it = pkixParams.getProhibitedACAttributes().iterator(); it.hasNext();)
        {
            String oid = (String)it.next();
            if(attrCert.getAttributes(oid) != null)
                throw new CertPathValidatorException((new StringBuilder()).append("Attribute certificate contains prohibited attribute: ").append(oid).append(".").toString());
        }

        for(Iterator it = pkixParams.getNecessaryACAttributes().iterator(); it.hasNext();)
        {
            String oid = (String)it.next();
            if(attrCert.getAttributes(oid) == null)
                throw new CertPathValidatorException((new StringBuilder()).append("Attribute certificate does not contain necessary attribute: ").append(oid).append(".").toString());
        }

    }

    protected static void processAttrCert5(X509AttributeCertificate attrCert, ExtendedPKIXParameters pkixParams)
        throws CertPathValidatorException
    {
        try
        {
            attrCert.checkValidity(CertPathValidatorUtilities.getValidDate(pkixParams));
        }
        catch(CertificateExpiredException e)
        {
            throw new ExtCertPathValidatorException("Attribute certificate is not valid.", e);
        }
        catch(CertificateNotYetValidException e)
        {
            throw new ExtCertPathValidatorException("Attribute certificate is not valid.", e);
        }
    }

    protected static void processAttrCert4(X509Certificate acIssuerCert, ExtendedPKIXParameters pkixParams)
        throws CertPathValidatorException
    {
        Set set = pkixParams.getTrustedACIssuers();
        boolean trusted = false;
        Iterator it = set.iterator();
        do
        {
            if(!it.hasNext())
                break;
            TrustAnchor anchor = (TrustAnchor)it.next();
            if(acIssuerCert.getSubjectX500Principal().getName("RFC2253").equals(anchor.getCAName()) || acIssuerCert.equals(anchor.getTrustedCert()))
                trusted = true;
        } while(true);
        if(!trusted)
            throw new CertPathValidatorException("Attribute certificate issuer is not directly trusted.");
        else
            return;
    }

    protected static void processAttrCert3(X509Certificate acIssuerCert, ExtendedPKIXParameters pkixParams)
        throws CertPathValidatorException
    {
        if(acIssuerCert.getKeyUsage() != null && !acIssuerCert.getKeyUsage()[0] && !acIssuerCert.getKeyUsage()[1])
            throw new CertPathValidatorException("Attribute certificate issuer public key cannot be used to validate digital signatures.");
        if(acIssuerCert.getBasicConstraints() != -1)
            throw new CertPathValidatorException("Attribute certificate issuer is also a public key certificate issuer.");
        else
            return;
    }

    protected static CertPathValidatorResult processAttrCert2(CertPath certPath, ExtendedPKIXParameters pkixParams)
        throws CertPathValidatorException
    {
        CertPathValidator validator = null;
        try
        {
            validator = CertPathValidator.getInstance("PKIX", "BC");
        }
        catch(NoSuchProviderException e)
        {
            throw new ExtCertPathValidatorException("Support class could not be created.", e);
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new ExtCertPathValidatorException("Support class could not be created.", e);
        }
        try
        {
            return validator.validate(certPath, pkixParams);
        }
        catch(CertPathValidatorException e)
        {
            throw new ExtCertPathValidatorException("Certification path for issuer certificate of attribute certificate could not be validated.", e);
        }
        catch(InvalidAlgorithmParameterException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected static CertPath processAttrCert1(X509AttributeCertificate attrCert, ExtendedPKIXParameters pkixParams)
        throws CertPathValidatorException
    {
        CertPathBuilderResult result = null;
        Set holderPKCs = new HashSet();
        if(attrCert.getHolder().getIssuer() != null)
        {
            X509CertStoreSelector selector = new X509CertStoreSelector();
            selector.setSerialNumber(attrCert.getHolder().getSerialNumber());
            java.security.Principal principals[] = attrCert.getHolder().getIssuer();
            for(int i = 0; i < principals.length; i++)
                try
                {
                    if(principals[i] instanceof X500Principal)
                        selector.setIssuer(((X500Principal)principals[i]).getEncoded());
                    holderPKCs.addAll(CertPathValidatorUtilities.findCertificates(selector, pkixParams.getStores()));
                }
                catch(AnnotatedException e)
                {
                    throw new ExtCertPathValidatorException("Public key certificate for attribute certificate cannot be searched.", e);
                }
                catch(IOException e)
                {
                    throw new ExtCertPathValidatorException("Unable to encode X500 principal.", e);
                }

            if(holderPKCs.isEmpty())
                throw new CertPathValidatorException("Public key certificate specified in base certificate ID for attribute certificate cannot be found.");
        }
        if(attrCert.getHolder().getEntityNames() != null)
        {
            X509CertStoreSelector selector = new X509CertStoreSelector();
            java.security.Principal principals[] = attrCert.getHolder().getEntityNames();
            for(int i = 0; i < principals.length; i++)
                try
                {
                    if(principals[i] instanceof X500Principal)
                        selector.setIssuer(((X500Principal)principals[i]).getEncoded());
                    holderPKCs.addAll(CertPathValidatorUtilities.findCertificates(selector, pkixParams.getStores()));
                }
                catch(AnnotatedException e)
                {
                    throw new ExtCertPathValidatorException("Public key certificate for attribute certificate cannot be searched.", e);
                }
                catch(IOException e)
                {
                    throw new ExtCertPathValidatorException("Unable to encode X500 principal.", e);
                }

            if(holderPKCs.isEmpty())
                throw new CertPathValidatorException("Public key certificate specified in entity name for attribute certificate cannot be found.");
        }
        ExtendedPKIXBuilderParameters params = (ExtendedPKIXBuilderParameters)ExtendedPKIXBuilderParameters.getInstance(pkixParams);
        CertPathValidatorException lastException = null;
        for(Iterator it = holderPKCs.iterator(); it.hasNext();)
        {
            X509CertStoreSelector selector = new X509CertStoreSelector();
            selector.setCertificate((X509Certificate)it.next());
            params.setTargetConstraints(selector);
            CertPathBuilder builder = null;
            try
            {
                builder = CertPathBuilder.getInstance("PKIX", "BC");
            }
            catch(NoSuchProviderException e)
            {
                throw new ExtCertPathValidatorException("Support class could not be created.", e);
            }
            catch(NoSuchAlgorithmException e)
            {
                throw new ExtCertPathValidatorException("Support class could not be created.", e);
            }
            try
            {
                result = builder.build(ExtendedPKIXBuilderParameters.getInstance(params));
            }
            catch(CertPathBuilderException e)
            {
                lastException = new ExtCertPathValidatorException("Certification path for public key certificate of attribute certificate could not be build.", e);
            }
            catch(InvalidAlgorithmParameterException e)
            {
                throw new RuntimeException(e.getMessage());
            }
        }

        if(lastException != null)
            throw lastException;
        else
            return result.getCertPath();
    }

    private static void checkCRL(DistributionPoint dp, X509AttributeCertificate attrCert, ExtendedPKIXParameters paramsPKIX, Date validDate, X509Certificate issuerCert, CertStatus certStatus, ReasonsMask reasonMask, List certPathCerts)
        throws AnnotatedException
    {
        Date currentDate;
        boolean validCrlFound;
        AnnotatedException lastException;
        Iterator crl_iter;
        if(attrCert.getExtensionValue(X509Extensions.NoRevAvail.getId()) != null)
            return;
        currentDate = new Date(System.currentTimeMillis());
        if(validDate.getTime() > currentDate.getTime())
            throw new AnnotatedException("Validation time is in future.");
        Set crls = CertPathValidatorUtilities.getCompleteCRLs(dp, attrCert, currentDate, paramsPKIX);
        validCrlFound = false;
        lastException = null;
        crl_iter = crls.iterator();
_L2:
        if(!crl_iter.hasNext() || certStatus.getCertStatus() != 11 || reasonMask.isAllReasons())
            break; /* Loop/switch isn't completed */
        X509CRL crl;
        ReasonsMask interimReasonsMask;
        crl = (X509CRL)crl_iter.next();
        interimReasonsMask = RFC3280CertPathUtilities.processCRLD(crl, dp);
        if(interimReasonsMask.hasNewReasons(reasonMask))
            try
            {
                Set keys = RFC3280CertPathUtilities.processCRLF(crl, attrCert, null, null, paramsPKIX, certPathCerts);
                PublicKey key = RFC3280CertPathUtilities.processCRLG(crl, keys);
                X509CRL deltaCRL = null;
                if(paramsPKIX.isUseDeltasEnabled())
                {
                    Set deltaCRLs = CertPathValidatorUtilities.getDeltaCRLs(currentDate, paramsPKIX, crl);
                    deltaCRL = RFC3280CertPathUtilities.processCRLH(deltaCRLs, key);
                }
                if(paramsPKIX.getValidityModel() != 1 && attrCert.getNotAfter().getTime() < crl.getThisUpdate().getTime())
                    throw new AnnotatedException("No valid CRL for current time found.");
                RFC3280CertPathUtilities.processCRLB1(dp, attrCert, crl);
                RFC3280CertPathUtilities.processCRLB2(dp, attrCert, crl);
                RFC3280CertPathUtilities.processCRLC(deltaCRL, crl, paramsPKIX);
                RFC3280CertPathUtilities.processCRLI(validDate, deltaCRL, attrCert, certStatus, paramsPKIX);
                RFC3280CertPathUtilities.processCRLJ(validDate, crl, attrCert, certStatus);
                if(certStatus.getCertStatus() == 8)
                    certStatus.setCertStatus(11);
                reasonMask.addReasons(interimReasonsMask);
                validCrlFound = true;
            }
            catch(AnnotatedException e)
            {
                lastException = e;
            }
        if(true) goto _L2; else goto _L1
_L1:
        if(!validCrlFound)
            throw lastException;
        else
            return;
    }

    private static final String TARGET_INFORMATION;
    private static final String NO_REV_AVAIL;
    private static final String CRL_DISTRIBUTION_POINTS;
    private static final String AUTHORITY_INFO_ACCESS;

    static 
    {
        TARGET_INFORMATION = X509Extensions.TargetInformation.getId();
        NO_REV_AVAIL = X509Extensions.NoRevAvail.getId();
        CRL_DISTRIBUTION_POINTS = X509Extensions.CRLDistributionPoints.getId();
        AUTHORITY_INFO_ACCESS = X509Extensions.AuthorityInfoAccess.getId();
    }
}
