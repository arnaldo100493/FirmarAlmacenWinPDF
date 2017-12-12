// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSSignedDataParser.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cert.jcajce.JcaCertStoreBuilder;
import co.org.bouncy.operator.*;
import co.org.bouncy.operator.bc.BcDigestCalculatorProvider;
import co.org.bouncy.util.Store;
import co.org.bouncy.util.io.Streams;
import co.org.bouncy.x509_.NoSuchStoreException;
import co.org.bouncy.x509_.X509Store;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.util.*;

// Referenced classes of package co.org.bouncy.cms:
//            CMSContentInfoParser, CMSTypedStream, CMSException, SignerInformation, 
//            SignerInformationStore, CMSUtils, CMSSignedHelper

public class CMSSignedDataParser extends CMSContentInfoParser
{

    /**
     * @deprecated Method CMSSignedDataParser is deprecated
     */

    public CMSSignedDataParser(byte sigBlock[])
        throws CMSException
    {
        this(createDefaultDigestProvider(), ((InputStream) (new ByteArrayInputStream(sigBlock))));
    }

    public CMSSignedDataParser(DigestCalculatorProvider digestCalculatorProvider, byte sigBlock[])
        throws CMSException
    {
        this(digestCalculatorProvider, ((InputStream) (new ByteArrayInputStream(sigBlock))));
    }

    /**
     * @deprecated Method CMSSignedDataParser is deprecated
     */

    public CMSSignedDataParser(CMSTypedStream signedContent, byte sigBlock[])
        throws CMSException
    {
        this(createDefaultDigestProvider(), signedContent, ((InputStream) (new ByteArrayInputStream(sigBlock))));
    }

    public CMSSignedDataParser(DigestCalculatorProvider digestCalculatorProvider, CMSTypedStream signedContent, byte sigBlock[])
        throws CMSException
    {
        this(digestCalculatorProvider, signedContent, ((InputStream) (new ByteArrayInputStream(sigBlock))));
    }

    private static DigestCalculatorProvider createDefaultDigestProvider()
        throws CMSException
    {
        return new BcDigestCalculatorProvider();
    }

    /**
     * @deprecated Method CMSSignedDataParser is deprecated
     */

    public CMSSignedDataParser(InputStream sigData)
        throws CMSException
    {
        this(createDefaultDigestProvider(), null, sigData);
    }

    public CMSSignedDataParser(DigestCalculatorProvider digestCalculatorProvider, InputStream sigData)
        throws CMSException
    {
        this(digestCalculatorProvider, null, sigData);
    }

    /**
     * @deprecated Method CMSSignedDataParser is deprecated
     */

    public CMSSignedDataParser(CMSTypedStream signedContent, InputStream sigData)
        throws CMSException
    {
        this(createDefaultDigestProvider(), signedContent, sigData);
    }

    public CMSSignedDataParser(DigestCalculatorProvider digestCalculatorProvider, CMSTypedStream signedContent, InputStream sigData)
        throws CMSException
    {
        super(sigData);
        try
        {
            _signedContent = signedContent;
            _signedData = SignedDataParser.getInstance(_contentInfo.getContent(16));
            digests = new HashMap();
            ASN1SetParser digAlgs = _signedData.getDigestAlgorithms();
            do
            {
                ASN1Encodable o;
                if((o = digAlgs.readObject()) == null)
                    break;
                AlgorithmIdentifier algId = AlgorithmIdentifier.getInstance(o);
                try
                {
                    DigestCalculator calculator = digestCalculatorProvider.get(algId);
                    if(calculator != null)
                        digests.put(algId.getAlgorithm(), calculator);
                }
                catch(OperatorCreationException e) { }
            } while(true);
            ContentInfoParser cont = _signedData.getEncapContentInfo();
            ASN1OctetStringParser octs = (ASN1OctetStringParser)cont.getContent(4);
            if(octs != null)
            {
                CMSTypedStream ctStr = new CMSTypedStream(cont.getContentType().getId(), octs.getOctetStream());
                if(_signedContent == null)
                    _signedContent = ctStr;
                else
                    ctStr.drain();
            }
            if(signedContent == null)
                _signedContentType = cont.getContentType();
            else
                _signedContentType = _signedContent.getContentType();
        }
        catch(IOException e)
        {
            throw new CMSException((new StringBuilder()).append("io exception: ").append(e.getMessage()).toString(), e);
        }
        if(digests.isEmpty())
            throw new CMSException("no digests could be created for message.");
        else
            return;
    }

    public int getVersion()
    {
        return _signedData.getVersion().getValue().intValue();
    }

    public SignerInformationStore getSignerInfos()
        throws CMSException
    {
        if(_signerInfoStore == null)
        {
            populateCertCrlSets();
            List signerInfos = new ArrayList();
            Map hashes = new HashMap();
            Object digestKey;
            for(Iterator it = digests.keySet().iterator(); it.hasNext(); hashes.put(digestKey, ((DigestCalculator)digests.get(digestKey)).getDigest()))
                digestKey = it.next();

            try
            {
                ASN1SetParser s = _signedData.getSignerInfos();
                ASN1Encodable o;
                while((o = s.readObject()) != null) 
                {
                    SignerInfo info = SignerInfo.getInstance(o.toASN1Primitive());
                    byte hash[] = (byte[])(byte[])hashes.get(info.getDigestAlgorithm().getAlgorithm());
                    signerInfos.add(new SignerInformation(info, _signedContentType, null, hash));
                }
            }
            catch(IOException e)
            {
                throw new CMSException((new StringBuilder()).append("io exception: ").append(e.getMessage()).toString(), e);
            }
            _signerInfoStore = new SignerInformationStore(signerInfos);
        }
        return _signerInfoStore;
    }

    /**
     * @deprecated Method getAttributeCertificates is deprecated
     */

    public X509Store getAttributeCertificates(String type, String provider)
        throws NoSuchStoreException, NoSuchProviderException, CMSException
    {
        return getAttributeCertificates(type, CMSUtils.getProvider(provider));
    }

    /**
     * @deprecated Method getAttributeCertificates is deprecated
     */

    public X509Store getAttributeCertificates(String type, Provider provider)
        throws NoSuchStoreException, CMSException
    {
        if(_attributeStore == null)
        {
            populateCertCrlSets();
            _attributeStore = HELPER.createAttributeStore(type, provider, getAttributeCertificates());
        }
        return _attributeStore;
    }

    /**
     * @deprecated Method getCertificates is deprecated
     */

    public X509Store getCertificates(String type, String provider)
        throws NoSuchStoreException, NoSuchProviderException, CMSException
    {
        return getCertificates(type, CMSUtils.getProvider(provider));
    }

    /**
     * @deprecated Method getCertificates is deprecated
     */

    public X509Store getCertificates(String type, Provider provider)
        throws NoSuchStoreException, CMSException
    {
        if(_certificateStore == null)
        {
            populateCertCrlSets();
            _certificateStore = HELPER.createCertificateStore(type, provider, getCertificates());
        }
        return _certificateStore;
    }

    /**
     * @deprecated Method getCRLs is deprecated
     */

    public X509Store getCRLs(String type, String provider)
        throws NoSuchStoreException, NoSuchProviderException, CMSException
    {
        return getCRLs(type, CMSUtils.getProvider(provider));
    }

    /**
     * @deprecated Method getCRLs is deprecated
     */

    public X509Store getCRLs(String type, Provider provider)
        throws NoSuchStoreException, CMSException
    {
        if(_crlStore == null)
        {
            populateCertCrlSets();
            _crlStore = HELPER.createCRLsStore(type, provider, getCRLs());
        }
        return _crlStore;
    }

    /**
     * @deprecated Method getCertificatesAndCRLs is deprecated
     */

    public CertStore getCertificatesAndCRLs(String type, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, CMSException
    {
        return getCertificatesAndCRLs(type, CMSUtils.getProvider(provider));
    }

    /**
     * @deprecated Method getCertificatesAndCRLs is deprecated
     */

    public CertStore getCertificatesAndCRLs(String type, Provider provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, CMSException
    {
        populateCertCrlSets();
        try
        {
            JcaCertStoreBuilder certStoreBuilder = (new JcaCertStoreBuilder()).setType(type);
            if(provider != null)
                certStoreBuilder.setProvider(provider);
            certStoreBuilder.addCertificates(getCertificates());
            certStoreBuilder.addCRLs(getCRLs());
            return certStoreBuilder.build();
        }
        catch(NoSuchAlgorithmException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new CMSException((new StringBuilder()).append("exception creating CertStore: ").append(e.getMessage()).toString(), e);
        }
    }

    public Store getCertificates()
        throws CMSException
    {
        populateCertCrlSets();
        return HELPER.getCertificates(_certSet);
    }

    public Store getCRLs()
        throws CMSException
    {
        populateCertCrlSets();
        return HELPER.getCRLs(_crlSet);
    }

    public Store getAttributeCertificates()
        throws CMSException
    {
        populateCertCrlSets();
        return HELPER.getAttributeCertificates(_certSet);
    }

    public Store getOtherRevocationInfo(ASN1ObjectIdentifier otherRevocationInfoFormat)
        throws CMSException
    {
        populateCertCrlSets();
        return HELPER.getOtherRevocationInfo(otherRevocationInfoFormat, _crlSet);
    }

    private void populateCertCrlSets()
        throws CMSException
    {
        if(_isCertCrlParsed)
            return;
        _isCertCrlParsed = true;
        try
        {
            _certSet = getASN1Set(_signedData.getCertificates());
            _crlSet = getASN1Set(_signedData.getCrls());
        }
        catch(IOException e)
        {
            throw new CMSException("problem parsing cert/crl sets", e);
        }
    }

    public String getSignedContentTypeOID()
    {
        return _signedContentType.getId();
    }

    public CMSTypedStream getSignedContent()
    {
        if(_signedContent == null)
        {
            return null;
        } else
        {
            InputStream digStream = CMSUtils.attachDigestsToInputStream(digests.values(), _signedContent.getContentStream());
            return new CMSTypedStream(_signedContent.getContentType(), digStream);
        }
    }

    public static OutputStream replaceSigners(InputStream original, SignerInformationStore signerInformationStore, OutputStream out)
        throws CMSException, IOException
    {
        ASN1StreamParser in = new ASN1StreamParser(original);
        ContentInfoParser contentInfo = new ContentInfoParser((ASN1SequenceParser)in.readObject());
        SignedDataParser signedData = SignedDataParser.getInstance(contentInfo.getContent(16));
        BERSequenceGenerator sGen = new BERSequenceGenerator(out);
        sGen.addObject(CMSObjectIdentifiers.signedData);
        BERSequenceGenerator sigGen = new BERSequenceGenerator(sGen.getRawOutputStream(), 0, true);
        sigGen.addObject(signedData.getVersion());
        signedData.getDigestAlgorithms().toASN1Primitive();
        ASN1EncodableVector digestAlgs = new ASN1EncodableVector();
        SignerInformation signer;
        for(Iterator it = signerInformationStore.getSigners().iterator(); it.hasNext(); digestAlgs.add(CMSSignedHelper.INSTANCE.fixAlgID(signer.getDigestAlgorithmID())))
            signer = (SignerInformation)it.next();

        sigGen.getRawOutputStream().write((new DERSet(digestAlgs)).getEncoded());
        ContentInfoParser encapContentInfo = signedData.getEncapContentInfo();
        BERSequenceGenerator eiGen = new BERSequenceGenerator(sigGen.getRawOutputStream());
        eiGen.addObject(encapContentInfo.getContentType());
        pipeEncapsulatedOctetString(encapContentInfo, eiGen.getRawOutputStream());
        eiGen.close();
        writeSetToGeneratorTagged(sigGen, signedData.getCertificates(), 0);
        writeSetToGeneratorTagged(sigGen, signedData.getCrls(), 1);
        ASN1EncodableVector signerInfos = new ASN1EncodableVector();
        SignerInformation signer;
        for(Iterator it = signerInformationStore.getSigners().iterator(); it.hasNext(); signerInfos.add(signer.toASN1Structure()))
            signer = (SignerInformation)it.next();

        sigGen.getRawOutputStream().write((new DERSet(signerInfos)).getEncoded());
        sigGen.close();
        sGen.close();
        return out;
    }

    /**
     * @deprecated Method replaceCertificatesAndCRLs is deprecated
     */

    public static OutputStream replaceCertificatesAndCRLs(InputStream original, CertStore certsAndCrls, OutputStream out)
        throws CMSException, IOException
    {
        ASN1StreamParser in = new ASN1StreamParser(original);
        ContentInfoParser contentInfo = new ContentInfoParser((ASN1SequenceParser)in.readObject());
        SignedDataParser signedData = SignedDataParser.getInstance(contentInfo.getContent(16));
        BERSequenceGenerator sGen = new BERSequenceGenerator(out);
        sGen.addObject(CMSObjectIdentifiers.signedData);
        BERSequenceGenerator sigGen = new BERSequenceGenerator(sGen.getRawOutputStream(), 0, true);
        sigGen.addObject(signedData.getVersion());
        sigGen.getRawOutputStream().write(signedData.getDigestAlgorithms().toASN1Primitive().getEncoded());
        ContentInfoParser encapContentInfo = signedData.getEncapContentInfo();
        BERSequenceGenerator eiGen = new BERSequenceGenerator(sigGen.getRawOutputStream());
        eiGen.addObject(encapContentInfo.getContentType());
        pipeEncapsulatedOctetString(encapContentInfo, eiGen.getRawOutputStream());
        eiGen.close();
        getASN1Set(signedData.getCertificates());
        getASN1Set(signedData.getCrls());
        ASN1Set certs;
        try
        {
            certs = CMSUtils.createBerSetFromList(CMSUtils.getCertificatesFromStore(certsAndCrls));
        }
        catch(CertStoreException e)
        {
            throw new CMSException("error getting certs from certStore", e);
        }
        if(certs.size() > 0)
            sigGen.getRawOutputStream().write((new DERTaggedObject(false, 0, certs)).getEncoded());
        ASN1Set crls;
        try
        {
            crls = CMSUtils.createBerSetFromList(CMSUtils.getCRLsFromStore(certsAndCrls));
        }
        catch(CertStoreException e)
        {
            throw new CMSException("error getting crls from certStore", e);
        }
        if(crls.size() > 0)
            sigGen.getRawOutputStream().write((new DERTaggedObject(false, 1, crls)).getEncoded());
        sigGen.getRawOutputStream().write(signedData.getSignerInfos().toASN1Primitive().getEncoded());
        sigGen.close();
        sGen.close();
        return out;
    }

    public static OutputStream replaceCertificatesAndCRLs(InputStream original, Store certs, Store crls, Store attrCerts, OutputStream out)
        throws CMSException, IOException
    {
        ASN1StreamParser in = new ASN1StreamParser(original);
        ContentInfoParser contentInfo = new ContentInfoParser((ASN1SequenceParser)in.readObject());
        SignedDataParser signedData = SignedDataParser.getInstance(contentInfo.getContent(16));
        BERSequenceGenerator sGen = new BERSequenceGenerator(out);
        sGen.addObject(CMSObjectIdentifiers.signedData);
        BERSequenceGenerator sigGen = new BERSequenceGenerator(sGen.getRawOutputStream(), 0, true);
        sigGen.addObject(signedData.getVersion());
        sigGen.getRawOutputStream().write(signedData.getDigestAlgorithms().toASN1Primitive().getEncoded());
        ContentInfoParser encapContentInfo = signedData.getEncapContentInfo();
        BERSequenceGenerator eiGen = new BERSequenceGenerator(sigGen.getRawOutputStream());
        eiGen.addObject(encapContentInfo.getContentType());
        pipeEncapsulatedOctetString(encapContentInfo, eiGen.getRawOutputStream());
        eiGen.close();
        getASN1Set(signedData.getCertificates());
        getASN1Set(signedData.getCrls());
        if(certs != null || attrCerts != null)
        {
            List certificates = new ArrayList();
            if(certs != null)
                certificates.addAll(CMSUtils.getCertificatesFromStore(certs));
            if(attrCerts != null)
                certificates.addAll(CMSUtils.getAttributeCertificatesFromStore(attrCerts));
            ASN1Set asn1Certs = CMSUtils.createBerSetFromList(certificates);
            if(asn1Certs.size() > 0)
                sigGen.getRawOutputStream().write((new DERTaggedObject(false, 0, asn1Certs)).getEncoded());
        }
        if(crls != null)
        {
            ASN1Set asn1Crls = CMSUtils.createBerSetFromList(CMSUtils.getCRLsFromStore(crls));
            if(asn1Crls.size() > 0)
                sigGen.getRawOutputStream().write((new DERTaggedObject(false, 1, asn1Crls)).getEncoded());
        }
        sigGen.getRawOutputStream().write(signedData.getSignerInfos().toASN1Primitive().getEncoded());
        sigGen.close();
        sGen.close();
        return out;
    }

    private static void writeSetToGeneratorTagged(ASN1Generator asn1Gen, ASN1SetParser asn1SetParser, int tagNo)
        throws IOException
    {
        ASN1Set asn1Set = getASN1Set(asn1SetParser);
        if(asn1Set != null)
            if(asn1SetParser instanceof BERSetParser)
                asn1Gen.getRawOutputStream().write((new BERTaggedObject(false, tagNo, asn1Set)).getEncoded());
            else
                asn1Gen.getRawOutputStream().write((new DERTaggedObject(false, tagNo, asn1Set)).getEncoded());
    }

    private static ASN1Set getASN1Set(ASN1SetParser asn1SetParser)
    {
        return asn1SetParser != null ? ASN1Set.getInstance(asn1SetParser.toASN1Primitive()) : null;
    }

    private static void pipeEncapsulatedOctetString(ContentInfoParser encapContentInfo, OutputStream rawOutputStream)
        throws IOException
    {
        ASN1OctetStringParser octs = (ASN1OctetStringParser)encapContentInfo.getContent(4);
        if(octs != null)
            pipeOctetString(octs, rawOutputStream);
    }

    private static void pipeOctetString(ASN1OctetStringParser octs, OutputStream output)
        throws IOException
    {
        OutputStream outOctets = CMSUtils.createBEROctetOutputStream(output, 0, true, 0);
        Streams.pipeAll(octs.getOctetStream(), outOctets);
        outOctets.close();
    }

    private static final CMSSignedHelper HELPER;
    private SignedDataParser _signedData;
    private ASN1ObjectIdentifier _signedContentType;
    private CMSTypedStream _signedContent;
    private Map digests;
    private SignerInformationStore _signerInfoStore;
    private X509Store _attributeStore;
    private ASN1Set _certSet;
    private ASN1Set _crlSet;
    private boolean _isCertCrlParsed;
    private X509Store _certificateStore;
    private X509Store _crlStore;

    static 
    {
        HELPER = CMSSignedHelper.INSTANCE;
    }
}
