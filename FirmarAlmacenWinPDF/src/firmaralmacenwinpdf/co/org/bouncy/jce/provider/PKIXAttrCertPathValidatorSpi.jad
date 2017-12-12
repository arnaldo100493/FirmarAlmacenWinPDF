// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKIXAttrCertPathValidatorSpi.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.jce.exception.ExtCertPathValidatorException;
import co.org.bouncy.util.Selector;
import co.org.bouncy.x509_.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.cert.*;
import java.util.Date;
import java.util.List;

// Referenced classes of package co.org.bouncy.jce.provider:
//            AnnotatedException, RFC3281CertPathUtilities, CertPathValidatorUtilities

public class PKIXAttrCertPathValidatorSpi extends CertPathValidatorSpi
{

    public PKIXAttrCertPathValidatorSpi()
    {
    }

    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters params)
        throws CertPathValidatorException, InvalidAlgorithmParameterException
    {
        if(!(params instanceof ExtendedPKIXParameters))
            throw new InvalidAlgorithmParameterException((new StringBuilder()).append("Parameters must be a ").append(co/org/bouncy/x509_/ExtendedPKIXParameters.getName()).append(" instance.").toString());
        ExtendedPKIXParameters pkixParams = (ExtendedPKIXParameters)params;
        Selector certSelect = pkixParams.getTargetConstraints();
        if(!(certSelect instanceof X509AttributeCertStoreSelector))
            throw new InvalidAlgorithmParameterException((new StringBuilder()).append("TargetConstraints must be an instance of ").append(co/org/bouncy/x509_/X509AttributeCertStoreSelector.getName()).append(" for ").append(getClass().getName()).append(" class.").toString());
        X509AttributeCertificate attrCert = ((X509AttributeCertStoreSelector)certSelect).getAttributeCert();
        CertPath holderCertPath = RFC3281CertPathUtilities.processAttrCert1(attrCert, pkixParams);
        CertPathValidatorResult result = RFC3281CertPathUtilities.processAttrCert2(certPath, pkixParams);
        X509Certificate issuerCert = (X509Certificate)certPath.getCertificates().get(0);
        RFC3281CertPathUtilities.processAttrCert3(issuerCert, pkixParams);
        RFC3281CertPathUtilities.processAttrCert4(issuerCert, pkixParams);
        RFC3281CertPathUtilities.processAttrCert5(attrCert, pkixParams);
        RFC3281CertPathUtilities.processAttrCert7(attrCert, certPath, holderCertPath, pkixParams);
        RFC3281CertPathUtilities.additionalChecks(attrCert, pkixParams);
        Date date = null;
        try
        {
            date = CertPathValidatorUtilities.getValidCertDateFromValidityModel(pkixParams, null, -1);
        }
        catch(AnnotatedException e)
        {
            throw new ExtCertPathValidatorException("Could not get validity date from attribute certificate.", e);
        }
        RFC3281CertPathUtilities.checkCRLs(attrCert, pkixParams, issuerCert, date, certPath.getCertificates());
        return result;
    }
}
