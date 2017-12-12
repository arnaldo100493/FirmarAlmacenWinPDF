// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimeStampTokenGenerator.java

package co.org.bouncy.tsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.Attribute;
import co.org.bouncy.asn1.cms.AttributeTable;
import co.org.bouncy.asn1.ess.*;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.tsp.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.GeneralName;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.cert.jcajce.JcaX509CRLHolder;
import co.org.bouncy.cert.jcajce.JcaX509CertificateHolder;
import co.org.bouncy.cms.*;
import co.org.bouncy.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import co.org.bouncy.jce.interfaces.GOST3410PrivateKey;
import co.org.bouncy.operator.DigestCalculator;
import co.org.bouncy.operator.OperatorCreationException;
import co.org.bouncy.operator.jcajce.JcaContentSignerBuilder;
import co.org.bouncy.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import co.org.bouncy.util.CollectionStore;
import co.org.bouncy.util.Store;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.*;

// Referenced classes of package co.org.bouncy.tsp:
//            TSPException, TimeStampToken, TimeStampRequest, TSPUtil

public class TimeStampTokenGenerator
{

    public TimeStampTokenGenerator(final SignerInfoGenerator signerInfoGen, DigestCalculator digestCalculator, ASN1ObjectIdentifier tsaPolicy)
        throws IllegalArgumentException, TSPException
    {
        accuracySeconds = -1;
        accuracyMillis = -1;
        accuracyMicros = -1;
        ordering = false;
        tsa = null;
        certs = new ArrayList();
        crls = new ArrayList();
        attrCerts = new ArrayList();
        this.signerInfoGen = signerInfoGen;
        tsaPolicyOID = tsaPolicy;
        if(!signerInfoGen.hasAssociatedCertificate())
            throw new IllegalArgumentException("SignerInfoGenerator must have an associated certificate");
        TSPUtil.validateCertificate(signerInfoGen.getAssociatedCertificate());
        try
        {
            OutputStream dOut = digestCalculator.getOutputStream();
            dOut.write(signerInfoGen.getAssociatedCertificate().getEncoded());
            dOut.close();
            if(digestCalculator.getAlgorithmIdentifier().getAlgorithm().equals(OIWObjectIdentifiers.idSHA1))
            {
                final ESSCertID essCertid = new ESSCertID(digestCalculator.getDigest());
                this.signerInfoGen = new SignerInfoGenerator(signerInfoGen, new CMSAttributeTableGenerator() {

                    public AttributeTable getAttributes(Map parameters)
                        throws CMSAttributeTableGenerationException
                    {
                        AttributeTable table = signerInfoGen.getSignedAttributeTableGenerator().getAttributes(parameters);
                        if(table.get(PKCSObjectIdentifiers.id_aa_signingCertificate) == null)
                            return table.add(PKCSObjectIdentifiers.id_aa_signingCertificate, new SigningCertificate(essCertid));
                        else
                            return table;
                    }

                    final SignerInfoGenerator val$signerInfoGen;
                    final ESSCertID val$essCertid;
                    final TimeStampTokenGenerator this$0;

            
            {
                this$0 = TimeStampTokenGenerator.this;
                signerInfoGen = signerinfogenerator;
                essCertid = esscertid;
                super();
            }
                }
, signerInfoGen.getUnsignedAttributeTableGenerator());
            } else
            {
                AlgorithmIdentifier digAlgID = new AlgorithmIdentifier(digestCalculator.getAlgorithmIdentifier().getAlgorithm());
                final ESSCertIDv2 essCertid = new ESSCertIDv2(digAlgID, digestCalculator.getDigest());
                this.signerInfoGen = new SignerInfoGenerator(signerInfoGen, new CMSAttributeTableGenerator() {

                    public AttributeTable getAttributes(Map parameters)
                        throws CMSAttributeTableGenerationException
                    {
                        AttributeTable table = signerInfoGen.getSignedAttributeTableGenerator().getAttributes(parameters);
                        if(table.get(PKCSObjectIdentifiers.id_aa_signingCertificateV2) == null)
                            return table.add(PKCSObjectIdentifiers.id_aa_signingCertificateV2, new SigningCertificateV2(essCertid));
                        else
                            return table;
                    }

                    final SignerInfoGenerator val$signerInfoGen;
                    final ESSCertIDv2 val$essCertid;
                    final TimeStampTokenGenerator this$0;

            
            {
                this$0 = TimeStampTokenGenerator.this;
                signerInfoGen = signerinfogenerator;
                essCertid = esscertidv2;
                super();
            }
                }
, signerInfoGen.getUnsignedAttributeTableGenerator());
            }
        }
        catch(IOException e)
        {
            throw new TSPException("Exception processing certificate.", e);
        }
    }

    /**
     * @deprecated Method TimeStampTokenGenerator is deprecated
     */

    public TimeStampTokenGenerator(DigestCalculator sha1DigestCalculator, SignerInfoGenerator signerInfoGen, ASN1ObjectIdentifier tsaPolicy)
        throws IllegalArgumentException, TSPException
    {
        this(signerInfoGen, sha1DigestCalculator, tsaPolicy);
    }

    /**
     * @deprecated Method TimeStampTokenGenerator is deprecated
     */

    public TimeStampTokenGenerator(SignerInfoGenerator signerInfoGen, ASN1ObjectIdentifier tsaPolicy)
        throws IllegalArgumentException, TSPException
    {
        this(new DigestCalculator() {

            public AlgorithmIdentifier getAlgorithmIdentifier()
            {
                return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
            }

            public OutputStream getOutputStream()
            {
                return bOut;
            }

            public byte[] getDigest()
            {
                try
                {
                    return MessageDigest.getInstance("SHA-1").digest(bOut.toByteArray());
                }
                catch(NoSuchAlgorithmException e)
                {
                    throw new IllegalStateException((new StringBuilder()).append("cannot find sha-1: ").append(e.getMessage()).toString());
                }
            }

            private ByteArrayOutputStream bOut;

            
            {
                bOut = new ByteArrayOutputStream();
            }
        }
, signerInfoGen, tsaPolicy);
    }

    /**
     * @deprecated Method TimeStampTokenGenerator is deprecated
     */

    public TimeStampTokenGenerator(PrivateKey key, X509Certificate cert, String digestOID, String tsaPolicyOID)
        throws IllegalArgumentException, TSPException
    {
        this(key, cert, digestOID, tsaPolicyOID, null, null);
    }

    /**
     * @deprecated Method TimeStampTokenGenerator is deprecated
     */

    public TimeStampTokenGenerator(PrivateKey key, X509Certificate cert, ASN1ObjectIdentifier digestOID, String tsaPolicyOID)
        throws IllegalArgumentException, TSPException
    {
        this(key, cert, digestOID.getId(), tsaPolicyOID, null, null);
    }

    /**
     * @deprecated Method TimeStampTokenGenerator is deprecated
     */

    public TimeStampTokenGenerator(PrivateKey key, X509Certificate cert, String digestOID, String tsaPolicyOID, AttributeTable signedAttr, AttributeTable unsignedAttr)
        throws IllegalArgumentException, TSPException
    {
        accuracySeconds = -1;
        accuracyMillis = -1;
        accuracyMicros = -1;
        ordering = false;
        tsa = null;
        certs = new ArrayList();
        crls = new ArrayList();
        attrCerts = new ArrayList();
        this.key = key;
        this.cert = cert;
        this.digestOID = digestOID;
        this.tsaPolicyOID = new ASN1ObjectIdentifier(tsaPolicyOID);
        this.unsignedAttr = unsignedAttr;
        Hashtable signedAttrs = null;
        if(signedAttr != null)
            signedAttrs = signedAttr.toHashtable();
        else
            signedAttrs = new Hashtable();
        TSPUtil.validateCertificate(cert);
        try
        {
            ESSCertID essCertid = new ESSCertID(MessageDigest.getInstance("SHA-1").digest(cert.getEncoded()));
            signedAttrs.put(PKCSObjectIdentifiers.id_aa_signingCertificate, new Attribute(PKCSObjectIdentifiers.id_aa_signingCertificate, new DERSet(new SigningCertificate(essCertid))));
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new TSPException("Can't find a SHA-1 implementation.", e);
        }
        catch(CertificateEncodingException e)
        {
            throw new TSPException("Exception processing certificate.", e);
        }
        this.signedAttr = new AttributeTable(signedAttrs);
    }

    /**
     * @deprecated Method setCertificatesAndCRLs is deprecated
     */

    public void setCertificatesAndCRLs(CertStore certificates)
        throws CertStoreException, TSPException
    {
        Collection c1 = certificates.getCertificates(null);
        for(Iterator it = c1.iterator(); it.hasNext();)
            try
            {
                certs.add(new JcaX509CertificateHolder((X509Certificate)it.next()));
            }
            catch(CertificateEncodingException e)
            {
                throw new TSPException((new StringBuilder()).append("cannot encode certificate: ").append(e.getMessage()).toString(), e);
            }

        c1 = certificates.getCRLs(null);
        for(Iterator it = c1.iterator(); it.hasNext();)
            try
            {
                crls.add(new JcaX509CRLHolder((X509CRL)it.next()));
            }
            catch(CRLException e)
            {
                throw new TSPException((new StringBuilder()).append("cannot encode CRL: ").append(e.getMessage()).toString(), e);
            }

    }

    public void addCertificates(Store certStore)
    {
        certs.addAll(certStore.getMatches(null));
    }

    public void addCRLs(Store crlStore)
    {
        crls.addAll(crlStore.getMatches(null));
    }

    public void addAttributeCertificates(Store attrStore)
    {
        attrCerts.addAll(attrStore.getMatches(null));
    }

    public void setAccuracySeconds(int accuracySeconds)
    {
        this.accuracySeconds = accuracySeconds;
    }

    public void setAccuracyMillis(int accuracyMillis)
    {
        this.accuracyMillis = accuracyMillis;
    }

    public void setAccuracyMicros(int accuracyMicros)
    {
        this.accuracyMicros = accuracyMicros;
    }

    public void setOrdering(boolean ordering)
    {
        this.ordering = ordering;
    }

    public void setTSA(GeneralName tsa)
    {
        this.tsa = tsa;
    }

    public TimeStampToken generate(TimeStampRequest request, BigInteger serialNumber, Date genTime, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, TSPException
    {
        if(signerInfoGen == null)
            try
            {
                JcaSignerInfoGeneratorBuilder sigBuilder = new JcaSignerInfoGeneratorBuilder((new JcaDigestCalculatorProviderBuilder()).setProvider(provider).build());
                sigBuilder.setSignedAttributeGenerator(new DefaultSignedAttributeTableGenerator(signedAttr));
                if(unsignedAttr != null)
                    sigBuilder.setUnsignedAttributeGenerator(new SimpleAttributeTableGenerator(unsignedAttr));
                signerInfoGen = sigBuilder.build((new JcaContentSignerBuilder(getSigAlgorithm(key, digestOID))).setProvider(provider).build(key), cert);
            }
            catch(OperatorCreationException e)
            {
                throw new TSPException("Error generating signing operator", e);
            }
            catch(CertificateEncodingException e)
            {
                throw new TSPException("Error encoding certificate", e);
            }
        return generate(request, serialNumber, genTime);
    }

    public TimeStampToken generate(TimeStampRequest request, BigInteger serialNumber, Date genTime)
        throws TSPException
    {
        if(signerInfoGen == null)
            throw new IllegalStateException("can only use this method with SignerInfoGenerator constructor");
        ASN1ObjectIdentifier digestAlgOID = request.getMessageImprintAlgOID();
        AlgorithmIdentifier algID = new AlgorithmIdentifier(digestAlgOID, DERNull.INSTANCE);
        MessageImprint messageImprint = new MessageImprint(algID, request.getMessageImprintDigest());
        Accuracy accuracy = null;
        if(accuracySeconds > 0 || accuracyMillis > 0 || accuracyMicros > 0)
        {
            ASN1Integer seconds = null;
            if(accuracySeconds > 0)
                seconds = new ASN1Integer(accuracySeconds);
            ASN1Integer millis = null;
            if(accuracyMillis > 0)
                millis = new ASN1Integer(accuracyMillis);
            ASN1Integer micros = null;
            if(accuracyMicros > 0)
                micros = new ASN1Integer(accuracyMicros);
            accuracy = new Accuracy(seconds, millis, micros);
        }
        ASN1Boolean derOrdering = null;
        if(ordering)
            derOrdering = new ASN1Boolean(ordering);
        ASN1Integer nonce = null;
        if(request.getNonce() != null)
            nonce = new ASN1Integer(request.getNonce());
        ASN1ObjectIdentifier tsaPolicy = tsaPolicyOID;
        if(request.getReqPolicy() != null)
            tsaPolicy = request.getReqPolicy();
        TSTInfo tstInfo = new TSTInfo(tsaPolicy, messageImprint, new ASN1Integer(serialNumber), new ASN1GeneralizedTime(genTime), accuracy, derOrdering, nonce, tsa, request.getExtensions());
        try
        {
            CMSSignedDataGenerator signedDataGenerator = new CMSSignedDataGenerator();
            if(request.getCertReq())
            {
                signedDataGenerator.addCertificates(new CollectionStore(certs));
                signedDataGenerator.addCRLs(new CollectionStore(crls));
                signedDataGenerator.addAttributeCertificates(new CollectionStore(attrCerts));
            } else
            {
                signedDataGenerator.addCRLs(new CollectionStore(crls));
            }
            signedDataGenerator.addSignerInfoGenerator(signerInfoGen);
            byte derEncodedTSTInfo[] = tstInfo.getEncoded("DER");
            co.org.bouncy.cms.CMSSignedData signedData = signedDataGenerator.generate(new CMSProcessableByteArray(PKCSObjectIdentifiers.id_ct_TSTInfo, derEncodedTSTInfo), true);
            return new TimeStampToken(signedData);
        }
        catch(CMSException cmsEx)
        {
            throw new TSPException("Error generating time-stamp token", cmsEx);
        }
        catch(IOException e)
        {
            throw new TSPException("Exception encoding info", e);
        }
    }

    private String getSigAlgorithm(PrivateKey key, String digestOID)
    {
        String enc = null;
        if((key instanceof RSAPrivateKey) || "RSA".equalsIgnoreCase(key.getAlgorithm()))
            enc = "RSA";
        else
        if((key instanceof DSAPrivateKey) || "DSA".equalsIgnoreCase(key.getAlgorithm()))
            enc = "DSA";
        else
        if("ECDSA".equalsIgnoreCase(key.getAlgorithm()) || "EC".equalsIgnoreCase(key.getAlgorithm()))
            enc = "ECDSA";
        else
        if((key instanceof GOST3410PrivateKey) || "GOST3410".equalsIgnoreCase(key.getAlgorithm()))
            enc = "GOST3410";
        else
        if("ECGOST3410".equalsIgnoreCase(key.getAlgorithm()))
            enc = CMSSignedGenerator.ENCRYPTION_ECGOST3410;
        return (new StringBuilder()).append(TSPUtil.getDigestAlgName(digestOID)).append("with").append(enc).toString();
    }

    int accuracySeconds;
    int accuracyMillis;
    int accuracyMicros;
    boolean ordering;
    GeneralName tsa;
    private ASN1ObjectIdentifier tsaPolicyOID;
    PrivateKey key;
    X509Certificate cert;
    String digestOID;
    AttributeTable signedAttr;
    AttributeTable unsignedAttr;
    private List certs;
    private List crls;
    private List attrCerts;
    private SignerInfoGenerator signerInfoGen;
}
