// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKIXAttrCertPathBuilderSpi.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.jce.exception.ExtCertPathBuilderException;
import co.org.bouncy.util.Selector;
import co.org.bouncy.x509_.*;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.cert.*;
import java.util.*;
import javax.security.auth.x500.X500Principal;

// Referenced classes of package co.org.bouncy.jce.provider:
//            AnnotatedException, CertPathValidatorUtilities

public class PKIXAttrCertPathBuilderSpi extends CertPathBuilderSpi
{

    public PKIXAttrCertPathBuilderSpi()
    {
    }

    public CertPathBuilderResult engineBuild(CertPathParameters params)
        throws CertPathBuilderException, InvalidAlgorithmParameterException
    {
        if(!(params instanceof PKIXBuilderParameters) && !(params instanceof ExtendedPKIXBuilderParameters))
            throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Parameters must be an instance of ").append(java/security/cert/PKIXBuilderParameters.getName()).append(" or ").append(co/org/bouncy/x509_/ExtendedPKIXBuilderParameters.getName()).append(".").toString());
        ExtendedPKIXBuilderParameters pkixParams;
        if(params instanceof ExtendedPKIXBuilderParameters)
            pkixParams = (ExtendedPKIXBuilderParameters)params;
        else
            pkixParams = (ExtendedPKIXBuilderParameters)ExtendedPKIXBuilderParameters.getInstance((PKIXBuilderParameters)params);
        List certPathList = new ArrayList();
        Selector certSelect = pkixParams.getTargetConstraints();
        if(!(certSelect instanceof X509AttributeCertStoreSelector))
            throw new CertPathBuilderException((new StringBuilder()).append("TargetConstraints must be an instance of ").append(co/org/bouncy/x509_/X509AttributeCertStoreSelector.getName()).append(" for ").append(getClass().getName()).append(" class.").toString());
        Collection targets;
        try
        {
            targets = CertPathValidatorUtilities.findCertificates((X509AttributeCertStoreSelector)certSelect, pkixParams.getStores());
        }
        catch(AnnotatedException e)
        {
            throw new ExtCertPathBuilderException("Error finding target attribute certificate.", e);
        }
        if(targets.isEmpty())
            throw new CertPathBuilderException("No attribute certificate found matching targetContraints.");
        CertPathBuilderResult result = null;
        for(Iterator targetIter = targets.iterator(); targetIter.hasNext() && result == null;)
        {
            X509AttributeCertificate cert = (X509AttributeCertificate)targetIter.next();
            X509CertStoreSelector selector = new X509CertStoreSelector();
            java.security.Principal principals[] = cert.getIssuer().getPrincipals();
            Set issuers = new HashSet();
            for(int i = 0; i < principals.length; i++)
                try
                {
                    if(principals[i] instanceof X500Principal)
                        selector.setSubject(((X500Principal)principals[i]).getEncoded());
                    issuers.addAll(CertPathValidatorUtilities.findCertificates(selector, pkixParams.getStores()));
                    issuers.addAll(CertPathValidatorUtilities.findCertificates(selector, pkixParams.getCertStores()));
                }
                catch(AnnotatedException e)
                {
                    throw new ExtCertPathBuilderException("Public key certificate for attribute certificate cannot be searched.", e);
                }
                catch(IOException e)
                {
                    throw new ExtCertPathBuilderException("cannot encode X500Principal.", e);
                }

            if(issuers.isEmpty())
                throw new CertPathBuilderException("Public key certificate for attribute certificate cannot be found.");
            Iterator it = issuers.iterator();
            while(it.hasNext() && result == null) 
                result = build(cert, (X509Certificate)it.next(), pkixParams, certPathList);
        }

        if(result == null && certPathException != null)
            throw new ExtCertPathBuilderException("Possible certificate chain could not be validated.", certPathException);
        if(result == null && certPathException == null)
            throw new CertPathBuilderException("Unable to find certificate chain.");
        else
            return result;
    }

    private CertPathBuilderResult build(X509AttributeCertificate attrCert, X509Certificate tbvCert, ExtendedPKIXBuilderParameters pkixParams, List tbvPath)
    {
        CertificateFactory cFact;
        CertPathValidator validator;
        CertPathBuilderResult builderResult;
        if(tbvPath.contains(tbvCert))
            return null;
        if(pkixParams.getExcludedCerts().contains(tbvCert))
            return null;
        if(pkixParams.getMaxPathLength() != -1 && tbvPath.size() - 1 > pkixParams.getMaxPathLength())
            return null;
        tbvPath.add(tbvCert);
        builderResult = null;
        try
        {
            cFact = CertificateFactory.getInstance("X.509", "BC");
            validator = CertPathValidator.getInstance("RFC3281", "BC");
        }
        catch(Exception e)
        {
            throw new RuntimeException("Exception creating support classes.");
        }
        if(CertPathValidatorUtilities.findTrustAnchor(tbvCert, pkixParams.getTrustAnchors(), pkixParams.getSigProvider()) != null)
        {
            CertPath certPath;
            try
            {
                certPath = cFact.generateCertPath(tbvPath);
            }
            catch(Exception e)
            {
                throw new AnnotatedException("Certification path could not be constructed from certificate list.", e);
            }
            PKIXCertPathValidatorResult result;
            try
            {
                result = (PKIXCertPathValidatorResult)validator.validate(certPath, pkixParams);
            }
            catch(Exception e)
            {
                throw new AnnotatedException("Certification path could not be validated.", e);
            }
            return new PKIXCertPathBuilderResult(certPath, result.getTrustAnchor(), result.getPolicyTree(), result.getPublicKey());
        }
        try
        {
            try
            {
                CertPathValidatorUtilities.addAdditionalStoresFromAltNames(tbvCert, pkixParams);
            }
            catch(CertificateParsingException e)
            {
                throw new AnnotatedException("No additional X.509 stores can be added from certificate locations.", e);
            }
            Collection issuers = new HashSet();
            try
            {
                issuers.addAll(CertPathValidatorUtilities.findIssuerCerts(tbvCert, pkixParams));
            }
            catch(AnnotatedException e)
            {
                throw new AnnotatedException("Cannot find issuer certificate for certificate in certification path.", e);
            }
            if(issuers.isEmpty())
                throw new AnnotatedException("No issuer certificate for certificate in certification path found.");
            Iterator it = issuers.iterator();
            do
            {
                if(!it.hasNext() || builderResult != null)
                    break;
                X509Certificate issuer = (X509Certificate)it.next();
                if(!issuer.getIssuerX500Principal().equals(issuer.getSubjectX500Principal()))
                    builderResult = build(attrCert, issuer, pkixParams, tbvPath);
            } while(true);
        }
        catch(AnnotatedException e)
        {
            certPathException = new AnnotatedException("No valid certification path could be build.", e);
        }
        if(builderResult == null)
            tbvPath.remove(tbvCert);
        return builderResult;
    }

    private Exception certPathException;
}
