// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertificateVerification.java

package co.com.pdf.text.pdf.security;

import co.org.bouncy.cert.ocsp.BasicOCSPResp;
import co.org.bouncy.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import co.org.bouncy.operator.jcajce.JcaContentVerifierProviderBuilder;
import co.org.bouncy.tsp.TimeStampToken;
import java.security.KeyStore;
import java.security.cert.*;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.security:
//            VerificationException

public class CertificateVerification
{

    public CertificateVerification()
    {
    }

    public static String verifyCertificate(X509Certificate cert, Collection crls, Calendar calendar)
    {
        Iterator i$;
        if(calendar == null)
            calendar = new GregorianCalendar();
        if(!cert.hasUnsupportedCriticalExtension())
            break MISSING_BLOCK_LABEL_106;
        i$ = cert.getCriticalExtensionOIDs().iterator();
_L1:
        String oid;
        do
        {
            if(!i$.hasNext())
                break MISSING_BLOCK_LABEL_106;
            oid = (String)i$.next();
        } while("2.5.29.15".equals(oid) && cert.getKeyUsage()[0]);
        if(!"2.5.29.37".equals(oid) || !cert.getExtendedKeyUsage().contains("1.3.6.1.5.5.7.3.8")) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_103;
        CertificateParsingException e;
        e;
        return "Has unsupported critical extension";
label0:
        {
            try
            {
                cert.checkValidity(calendar.getTime());
            }
            catch(Exception e)
            {
                return e.getMessage();
            }
            if(crls == null)
                break label0;
            e = crls.iterator();
            CRL crl;
            do
            {
                if(!e.hasNext())
                    break label0;
                crl = (CRL)e.next();
            } while(!crl.isRevoked(cert));
            return "Certificate revoked";
        }
        return null;
    }

    public static List verifyCertificates(Certificate certs[], KeyStore keystore, Collection crls, Calendar calendar)
    {
        List result;
        int k;
        result = new ArrayList();
        if(calendar == null)
            calendar = new GregorianCalendar();
        k = 0;
_L5:
        if(k >= certs.length) goto _L2; else goto _L1
_L1:
        X509Certificate cert;
        cert = (X509Certificate)certs[k];
        String err = verifyCertificate(cert, crls, calendar);
        if(err != null)
            result.add(new VerificationException(cert, err));
        Enumeration aliases = keystore.aliases();
_L3:
        if(!aliases.hasMoreElements())
            break MISSING_BLOCK_LABEL_165;
        String alias = (String)aliases.nextElement();
        if(keystore.isCertificateEntry(alias)) goto _L4; else goto _L3
_L4:
        X509Certificate certStoreX509 = (X509Certificate)keystore.getCertificate(alias);
        if(verifyCertificate(certStoreX509, crls, calendar) == null)
            try
            {
                cert.verify(certStoreX509.getPublicKey());
                return result;
            }
            catch(Exception e) { }
            catch(Exception ex) { }
          goto _L3
        Exception e;
        e;
        int j;
        for(j = 0; j < certs.length;)
        {
            if(j == k)
                continue;
            X509Certificate certNext = (X509Certificate)certs[j];
            try
            {
                cert.verify(certNext.getPublicKey());
                break;
            }
            catch(Exception e)
            {
                j++;
            }
        }

        if(j == certs.length)
            result.add(new VerificationException(cert, "Cannot be verified against the KeyStore or the certificate chain"));
        k++;
          goto _L5
_L2:
        if(result.size() == 0)
            result.add(new VerificationException(null, "Invalid state. Possible circular certificate chain"));
        return result;
    }

    public static List verifyCertificates(Certificate certs[], KeyStore keystore, Calendar calendar)
    {
        return verifyCertificates(certs, keystore, null, calendar);
    }

    public static boolean verifyOcspCertificates(BasicOCSPResp ocsp, KeyStore keystore, String provider)
    {
        if(provider == null)
            provider = "BC";
        Enumeration aliases = keystore.aliases();
_L1:
        if(!aliases.hasMoreElements())
            break MISSING_BLOCK_LABEL_95;
        String alias = (String)aliases.nextElement();
        if(keystore.isCertificateEntry(alias))
            try
            {
                X509Certificate certStoreX509 = (X509Certificate)keystore.getCertificate(alias);
                if(ocsp.isSignatureValid((new JcaContentVerifierProviderBuilder()).setProvider(provider).build(certStoreX509.getPublicKey())))
                    return true;
            }
            catch(Exception ex) { }
          goto _L1
        Exception e;
        e;
        return false;
    }

    public static boolean verifyTimestampCertificates(TimeStampToken ts, KeyStore keystore, String provider)
    {
        if(provider == null)
            provider = "BC";
        Enumeration aliases = keystore.aliases();
_L1:
        if(!aliases.hasMoreElements())
            break MISSING_BLOCK_LABEL_87;
        String alias = (String)aliases.nextElement();
        if(keystore.isCertificateEntry(alias))
            try
            {
                X509Certificate certStoreX509 = (X509Certificate)keystore.getCertificate(alias);
                ts.isSignatureValid((new JcaSimpleSignerInfoVerifierBuilder()).setProvider(provider).build(certStoreX509));
                return true;
            }
            catch(Exception ex) { }
          goto _L1
        Exception e;
        e;
        return false;
    }
}
