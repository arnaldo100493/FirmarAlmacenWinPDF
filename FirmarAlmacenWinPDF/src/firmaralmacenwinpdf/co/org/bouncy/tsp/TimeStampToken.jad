// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimeStampToken.java

package co.org.bouncy.tsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.ess.*;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.tsp.TSTInfo;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.cms.*;
import co.org.bouncy.jce.PrincipalUtil;
import co.org.bouncy.jce.X509Principal;
import co.org.bouncy.operator.DigestCalculator;
import co.org.bouncy.operator.OperatorCreationException;
import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Store;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.util.*;

// Referenced classes of package co.org.bouncy.tsp:
//            TSPException, TSPValidationException, TimeStampTokenInfo, TSPUtil

public class TimeStampToken
{
    private class CertID
    {

        public String getHashAlgorithmName()
        {
            if(certID != null)
                return "SHA-1";
            if(NISTObjectIdentifiers.id_sha256.equals(certIDv2.getHashAlgorithm().getAlgorithm()))
                return "SHA-256";
            else
                return certIDv2.getHashAlgorithm().getAlgorithm().getId();
        }

        public AlgorithmIdentifier getHashAlgorithm()
        {
            if(certID != null)
                return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1);
            else
                return certIDv2.getHashAlgorithm();
        }

        public byte[] getCertHash()
        {
            if(certID != null)
                return certID.getCertHash();
            else
                return certIDv2.getCertHash();
        }

        public IssuerSerial getIssuerSerial()
        {
            if(certID != null)
                return certID.getIssuerSerial();
            else
                return certIDv2.getIssuerSerial();
        }

        private ESSCertID certID;
        private ESSCertIDv2 certIDv2;
        final TimeStampToken this$0;

        CertID(ESSCertID certID)
        {
            this$0 = TimeStampToken.this;
            super();
            this.certID = certID;
            certIDv2 = null;
        }

        CertID(ESSCertIDv2 certID)
        {
            this$0 = TimeStampToken.this;
            super();
            certIDv2 = certID;
            this.certID = null;
        }
    }


    public TimeStampToken(ContentInfo contentInfo)
        throws TSPException, IOException
    {
        this(getSignedData(contentInfo));
    }

    private static CMSSignedData getSignedData(ContentInfo contentInfo)
        throws TSPException
    {
        try
        {
            return new CMSSignedData(contentInfo);
        }
        catch(CMSException e)
        {
            throw new TSPException((new StringBuilder()).append("TSP parsing error: ").append(e.getMessage()).toString(), e.getCause());
        }
    }

    public TimeStampToken(CMSSignedData signedData)
        throws TSPException, IOException
    {
        tsToken = signedData;
        if(!tsToken.getSignedContentTypeOID().equals(PKCSObjectIdentifiers.id_ct_TSTInfo.getId()))
            throw new TSPValidationException("ContentInfo object not for a time stamp.");
        Collection signers = tsToken.getSignerInfos().getSigners();
        if(signers.size() != 1)
            throw new IllegalArgumentException((new StringBuilder()).append("Time-stamp token signed by ").append(signers.size()).append(" signers, but it must contain just the TSA signature.").toString());
        tsaSignerInfo = (SignerInformation)signers.iterator().next();
        try
        {
            CMSProcessable content = tsToken.getSignedContent();
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            content.write(bOut);
            ASN1InputStream aIn = new ASN1InputStream(new ByteArrayInputStream(bOut.toByteArray()));
            tstInfo = new TimeStampTokenInfo(TSTInfo.getInstance(aIn.readObject()));
            Attribute attr = tsaSignerInfo.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificate);
            if(attr != null)
            {
                SigningCertificate signCert = SigningCertificate.getInstance(attr.getAttrValues().getObjectAt(0));
                certID = new CertID(ESSCertID.getInstance(signCert.getCerts()[0]));
            } else
            {
                attr = tsaSignerInfo.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificateV2);
                if(attr == null)
                    throw new TSPValidationException("no signing certificate attribute found, time stamp invalid.");
                SigningCertificateV2 signCertV2 = SigningCertificateV2.getInstance(attr.getAttrValues().getObjectAt(0));
                certID = new CertID(ESSCertIDv2.getInstance(signCertV2.getCerts()[0]));
            }
        }
        catch(CMSException e)
        {
            throw new TSPException(e.getMessage(), e.getUnderlyingException());
        }
    }

    public TimeStampTokenInfo getTimeStampInfo()
    {
        return tstInfo;
    }

    public SignerId getSID()
    {
        return tsaSignerInfo.getSID();
    }

    public AttributeTable getSignedAttributes()
    {
        return tsaSignerInfo.getSignedAttributes();
    }

    public AttributeTable getUnsignedAttributes()
    {
        return tsaSignerInfo.getUnsignedAttributes();
    }

    /**
     * @deprecated Method getCertificatesAndCRLs is deprecated
     */

    public CertStore getCertificatesAndCRLs(String type, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, CMSException
    {
        return tsToken.getCertificatesAndCRLs(type, provider);
    }

    public Store getCertificates()
    {
        return tsToken.getCertificates();
    }

    public Store getCRLs()
    {
        return tsToken.getCRLs();
    }

    public Store getAttributeCertificates()
    {
        return tsToken.getAttributeCertificates();
    }

    /**
     * @deprecated Method validate is deprecated
     */

    public void validate(X509Certificate cert, String provider)
        throws TSPException, TSPValidationException, CertificateExpiredException, CertificateNotYetValidException, NoSuchProviderException
    {
        try
        {
            if(!Arrays.constantTimeAreEqual(certID.getCertHash(), MessageDigest.getInstance(certID.getHashAlgorithmName()).digest(cert.getEncoded())))
                throw new TSPValidationException("certificate hash does not match certID hash.");
            if(certID.getIssuerSerial() != null)
            {
                if(!certID.getIssuerSerial().getSerial().getValue().equals(cert.getSerialNumber()))
                    throw new TSPValidationException("certificate serial number does not match certID for signature.");
                GeneralName names[] = certID.getIssuerSerial().getIssuer().getNames();
                X509Principal principal = PrincipalUtil.getIssuerX509Principal(cert);
                boolean found = false;
                int i = 0;
                do
                {
                    if(i == names.length)
                        break;
                    if(names[i].getTagNo() == 4 && (new X509Principal(X509Name.getInstance(names[i].getName()))).equals(principal))
                    {
                        found = true;
                        break;
                    }
                    i++;
                } while(true);
                if(!found)
                    throw new TSPValidationException("certificate name does not match certID for signature. ");
            }
            TSPUtil.validateCertificate(cert);
            cert.checkValidity(tstInfo.getGenTime());
            if(!tsaSignerInfo.verify(cert, provider))
                throw new TSPValidationException("signature not created by certificate.");
        }
        catch(CMSException e)
        {
            if(e.getUnderlyingException() != null)
                throw new TSPException(e.getMessage(), e.getUnderlyingException());
            else
                throw new TSPException((new StringBuilder()).append("CMS exception: ").append(e).toString(), e);
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new TSPException((new StringBuilder()).append("cannot find algorithm: ").append(e).toString(), e);
        }
        catch(CertificateEncodingException e)
        {
            throw new TSPException((new StringBuilder()).append("problem processing certificate: ").append(e).toString(), e);
        }
    }

    public void validate(SignerInformationVerifier sigVerifier)
        throws TSPException, TSPValidationException
    {
        if(!sigVerifier.hasAssociatedCertificate())
            throw new IllegalArgumentException("verifier provider needs an associated certificate");
        try
        {
            X509CertificateHolder certHolder = sigVerifier.getAssociatedCertificate();
            DigestCalculator calc = sigVerifier.getDigestCalculator(certID.getHashAlgorithm());
            OutputStream cOut = calc.getOutputStream();
            cOut.write(certHolder.getEncoded());
            cOut.close();
            if(!Arrays.constantTimeAreEqual(certID.getCertHash(), calc.getDigest()))
                throw new TSPValidationException("certificate hash does not match certID hash.");
            if(certID.getIssuerSerial() != null)
            {
                IssuerAndSerialNumber issuerSerial = new IssuerAndSerialNumber(certHolder.toASN1Structure());
                if(!certID.getIssuerSerial().getSerial().equals(issuerSerial.getSerialNumber()))
                    throw new TSPValidationException("certificate serial number does not match certID for signature.");
                GeneralName names[] = certID.getIssuerSerial().getIssuer().getNames();
                boolean found = false;
                int i = 0;
                do
                {
                    if(i == names.length)
                        break;
                    if(names[i].getTagNo() == 4 && X500Name.getInstance(names[i].getName()).equals(X500Name.getInstance(issuerSerial.getName())))
                    {
                        found = true;
                        break;
                    }
                    i++;
                } while(true);
                if(!found)
                    throw new TSPValidationException("certificate name does not match certID for signature. ");
            }
            TSPUtil.validateCertificate(certHolder);
            if(!certHolder.isValidOn(tstInfo.getGenTime()))
                throw new TSPValidationException("certificate not valid when time stamp created.");
            if(!tsaSignerInfo.verify(sigVerifier))
                throw new TSPValidationException("signature not created by certificate.");
        }
        catch(CMSException e)
        {
            if(e.getUnderlyingException() != null)
                throw new TSPException(e.getMessage(), e.getUnderlyingException());
            else
                throw new TSPException((new StringBuilder()).append("CMS exception: ").append(e).toString(), e);
        }
        catch(IOException e)
        {
            throw new TSPException((new StringBuilder()).append("problem processing certificate: ").append(e).toString(), e);
        }
        catch(OperatorCreationException e)
        {
            throw new TSPException((new StringBuilder()).append("unable to create digest: ").append(e.getMessage()).toString(), e);
        }
    }

    public boolean isSignatureValid(SignerInformationVerifier sigVerifier)
        throws TSPException
    {
        try
        {
            return tsaSignerInfo.verify(sigVerifier);
        }
        catch(CMSException e)
        {
            if(e.getUnderlyingException() != null)
                throw new TSPException(e.getMessage(), e.getUnderlyingException());
            else
                throw new TSPException((new StringBuilder()).append("CMS exception: ").append(e).toString(), e);
        }
    }

    public CMSSignedData toCMSSignedData()
    {
        return tsToken;
    }

    public byte[] getEncoded()
        throws IOException
    {
        return tsToken.getEncoded();
    }

    CMSSignedData tsToken;
    SignerInformation tsaSignerInfo;
    Date genTime;
    TimeStampTokenInfo tstInfo;
    CertID certID;
}
