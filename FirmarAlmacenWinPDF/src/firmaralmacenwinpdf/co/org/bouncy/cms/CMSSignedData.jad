// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSSignedData.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cert.jcajce.JcaCertStoreBuilder;
import co.org.bouncy.operator.*;
import co.org.bouncy.util.Store;
import co.org.bouncy.x509_.NoSuchStoreException;
import co.org.bouncy.x509_.X509Store;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.util.*;

// Referenced classes of package co.org.bouncy.cms:
//            CMSTypedData, CMSProcessableByteArray, CMSException, SignerInformation, 
//            SignerInformationStore, CMSProcessable, SignerInformationVerifier, CMSUtils, 
//            CMSSignedHelper, SignerInformationVerifierProvider

public class CMSSignedData
{

    private CMSSignedData(CMSSignedData c)
    {
        signedData = c.signedData;
        contentInfo = c.contentInfo;
        signedContent = c.signedContent;
        signerInfoStore = c.signerInfoStore;
    }

    public CMSSignedData(byte sigBlock[])
        throws CMSException
    {
        this(CMSUtils.readContentInfo(sigBlock));
    }

    public CMSSignedData(CMSProcessable signedContent, byte sigBlock[])
        throws CMSException
    {
        this(signedContent, CMSUtils.readContentInfo(sigBlock));
    }

    public CMSSignedData(Map hashes, byte sigBlock[])
        throws CMSException
    {
        this(hashes, CMSUtils.readContentInfo(sigBlock));
    }

    public CMSSignedData(CMSProcessable signedContent, InputStream sigData)
        throws CMSException
    {
        this(signedContent, CMSUtils.readContentInfo(new ASN1InputStream(sigData)));
    }

    public CMSSignedData(InputStream sigData)
        throws CMSException
    {
        this(CMSUtils.readContentInfo(sigData));
    }

    public CMSSignedData(final CMSProcessable signedContent, ContentInfo sigData)
        throws CMSException
    {
        if(signedContent instanceof CMSTypedData)
            this.signedContent = (CMSTypedData)signedContent;
        else
            this.signedContent = new CMSTypedData() {

                public ASN1ObjectIdentifier getContentType()
                {
                    return signedData.getEncapContentInfo().getContentType();
                }

                public void write(OutputStream out)
                    throws IOException, CMSException
                {
                    signedContent.write(out);
                }

                public Object getContent()
                {
                    return signedContent.getContent();
                }

                final CMSProcessable val$signedContent;
                final CMSSignedData this$0;

            
            {
                this$0 = CMSSignedData.this;
                signedContent = cmsprocessable;
                super();
            }
            }
;
        contentInfo = sigData;
        signedData = getSignedData();
    }

    public CMSSignedData(Map hashes, ContentInfo sigData)
        throws CMSException
    {
        this.hashes = hashes;
        contentInfo = sigData;
        signedData = getSignedData();
    }

    public CMSSignedData(ContentInfo sigData)
        throws CMSException
    {
        contentInfo = sigData;
        signedData = getSignedData();
        if(signedData.getEncapContentInfo().getContent() != null)
            signedContent = new CMSProcessableByteArray(signedData.getEncapContentInfo().getContentType(), ((ASN1OctetString)(ASN1OctetString)signedData.getEncapContentInfo().getContent()).getOctets());
        else
            signedContent = null;
    }

    private SignedData getSignedData()
        throws CMSException
    {
        try
        {
            return SignedData.getInstance(contentInfo.getContent());
        }
        catch(ClassCastException e)
        {
            throw new CMSException("Malformed content.", e);
        }
        catch(IllegalArgumentException e)
        {
            throw new CMSException("Malformed content.", e);
        }
    }

    public int getVersion()
    {
        return signedData.getVersion().getValue().intValue();
    }

    public SignerInformationStore getSignerInfos()
    {
        if(signerInfoStore == null)
        {
            ASN1Set s = signedData.getSignerInfos();
            List signerInfos = new ArrayList();
            SignatureAlgorithmIdentifierFinder sigAlgFinder = new DefaultSignatureAlgorithmIdentifierFinder();
            for(int i = 0; i != s.size(); i++)
            {
                SignerInfo info = SignerInfo.getInstance(s.getObjectAt(i));
                ASN1ObjectIdentifier contentType = signedData.getEncapContentInfo().getContentType();
                if(hashes == null)
                {
                    signerInfos.add(new SignerInformation(info, contentType, signedContent, null));
                } else
                {
                    Object obj = hashes.keySet().iterator().next();
                    byte hash[] = (obj instanceof String) ? (byte[])(byte[])hashes.get(info.getDigestAlgorithm().getAlgorithm().getId()) : (byte[])(byte[])hashes.get(info.getDigestAlgorithm().getAlgorithm());
                    signerInfos.add(new SignerInformation(info, contentType, null, hash));
                }
            }

            signerInfoStore = new SignerInformationStore(signerInfos);
        }
        return signerInfoStore;
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
        if(attributeStore == null)
            attributeStore = HELPER.createAttributeStore(type, provider, getAttributeCertificates());
        return attributeStore;
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
        if(certificateStore == null)
            certificateStore = HELPER.createCertificateStore(type, provider, getCertificates());
        return certificateStore;
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
        if(crlStore == null)
            crlStore = HELPER.createCRLsStore(type, provider, getCRLs());
        return crlStore;
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
        throws NoSuchAlgorithmException, CMSException
    {
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
    {
        return HELPER.getCertificates(signedData.getCertificates());
    }

    public Store getCRLs()
    {
        return HELPER.getCRLs(signedData.getCRLs());
    }

    public Store getAttributeCertificates()
    {
        return HELPER.getAttributeCertificates(signedData.getCertificates());
    }

    public Store getOtherRevocationInfo(ASN1ObjectIdentifier otherRevocationInfoFormat)
    {
        return HELPER.getOtherRevocationInfo(otherRevocationInfoFormat, signedData.getCRLs());
    }

    public String getSignedContentTypeOID()
    {
        return signedData.getEncapContentInfo().getContentType().getId();
    }

    public CMSTypedData getSignedContent()
    {
        return signedContent;
    }

    /**
     * @deprecated Method getContentInfo is deprecated
     */

    public ContentInfo getContentInfo()
    {
        return contentInfo;
    }

    public ContentInfo toASN1Structure()
    {
        return contentInfo;
    }

    public byte[] getEncoded()
        throws IOException
    {
        return contentInfo.getEncoded();
    }

    public boolean verifySignatures(SignerInformationVerifierProvider verifierProvider)
        throws CMSException
    {
        return verifySignatures(verifierProvider, false);
    }

    public boolean verifySignatures(SignerInformationVerifierProvider verifierProvider, boolean ignoreCounterSignatures)
        throws CMSException
    {
        Iterator it;
        Collection signers = getSignerInfos().getSigners();
        it = signers.iterator();
_L2:
        if(!it.hasNext())
            break MISSING_BLOCK_LABEL_177;
        SignerInformation signer = (SignerInformation)it.next();
        Collection counterSigners;
        Iterator cIt;
        SignerInformation counterSigner;
        SignerInformationVerifier counterVerifier;
        try
        {
            SignerInformationVerifier verifier = verifierProvider.get(signer.getSID());
            if(!signer.verify(verifier))
                return false;
        }
        catch(OperatorCreationException e)
        {
            throw new CMSException((new StringBuilder()).append("failure in verifier provider: ").append(e.getMessage()).toString(), e);
        }
        if(ignoreCounterSignatures) goto _L2; else goto _L1
_L1:
        counterSigners = signer.getCounterSignatures().getSigners();
        cIt = counterSigners.iterator();
_L5:
        if(!cIt.hasNext()) goto _L2; else goto _L3
_L3:
        counterSigner = (SignerInformation)cIt.next();
        counterVerifier = verifierProvider.get(signer.getSID());
        if(counterSigner.verify(counterVerifier)) goto _L5; else goto _L4
_L4:
        return false;
        return true;
    }

    public static CMSSignedData replaceSigners(CMSSignedData signedData, SignerInformationStore signerInformationStore)
    {
        CMSSignedData cms = new CMSSignedData(signedData);
        cms.signerInfoStore = signerInformationStore;
        ASN1EncodableVector digestAlgs = new ASN1EncodableVector();
        ASN1EncodableVector vec = new ASN1EncodableVector();
        SignerInformation signer;
        for(Iterator it = signerInformationStore.getSigners().iterator(); it.hasNext(); vec.add(signer.toASN1Structure()))
        {
            signer = (SignerInformation)it.next();
            digestAlgs.add(CMSSignedHelper.INSTANCE.fixAlgID(signer.getDigestAlgorithmID()));
        }

        ASN1Set digests = new DERSet(digestAlgs);
        ASN1Set signers = new DERSet(vec);
        ASN1Sequence sD = (ASN1Sequence)signedData.signedData.toASN1Primitive();
        vec = new ASN1EncodableVector();
        vec.add(sD.getObjectAt(0));
        vec.add(digests);
        for(int i = 2; i != sD.size() - 1; i++)
            vec.add(sD.getObjectAt(i));

        vec.add(signers);
        cms.signedData = SignedData.getInstance(new BERSequence(vec));
        cms.contentInfo = new ContentInfo(cms.contentInfo.getContentType(), cms.signedData);
        return cms;
    }

    /**
     * @deprecated Method replaceCertificatesAndCRLs is deprecated
     */

    public static CMSSignedData replaceCertificatesAndCRLs(CMSSignedData signedData, CertStore certsAndCrls)
        throws CMSException
    {
        CMSSignedData cms = new CMSSignedData(signedData);
        ASN1Set certs = null;
        ASN1Set crls = null;
        try
        {
            ASN1Set set = CMSUtils.createBerSetFromList(CMSUtils.getCertificatesFromStore(certsAndCrls));
            if(set.size() != 0)
                certs = set;
        }
        catch(CertStoreException e)
        {
            throw new CMSException("error getting certs from certStore", e);
        }
        try
        {
            ASN1Set set = CMSUtils.createBerSetFromList(CMSUtils.getCRLsFromStore(certsAndCrls));
            if(set.size() != 0)
                crls = set;
        }
        catch(CertStoreException e)
        {
            throw new CMSException("error getting crls from certStore", e);
        }
        cms.signedData = new SignedData(signedData.signedData.getDigestAlgorithms(), signedData.signedData.getEncapContentInfo(), certs, crls, signedData.signedData.getSignerInfos());
        cms.contentInfo = new ContentInfo(cms.contentInfo.getContentType(), cms.signedData);
        return cms;
    }

    public static CMSSignedData replaceCertificatesAndCRLs(CMSSignedData signedData, Store certificates, Store attrCerts, Store crls)
        throws CMSException
    {
        CMSSignedData cms = new CMSSignedData(signedData);
        ASN1Set certSet = null;
        ASN1Set crlSet = null;
        if(certificates != null || attrCerts != null)
        {
            List certs = new ArrayList();
            if(certificates != null)
                certs.addAll(CMSUtils.getCertificatesFromStore(certificates));
            if(attrCerts != null)
                certs.addAll(CMSUtils.getAttributeCertificatesFromStore(attrCerts));
            ASN1Set set = CMSUtils.createBerSetFromList(certs);
            if(set.size() != 0)
                certSet = set;
        }
        if(crls != null)
        {
            ASN1Set set = CMSUtils.createBerSetFromList(CMSUtils.getCRLsFromStore(crls));
            if(set.size() != 0)
                crlSet = set;
        }
        cms.signedData = new SignedData(signedData.signedData.getDigestAlgorithms(), signedData.signedData.getEncapContentInfo(), certSet, crlSet, signedData.signedData.getSignerInfos());
        cms.contentInfo = new ContentInfo(cms.contentInfo.getContentType(), cms.signedData);
        return cms;
    }

    private static final CMSSignedHelper HELPER;
    SignedData signedData;
    ContentInfo contentInfo;
    CMSTypedData signedContent;
    SignerInformationStore signerInfoStore;
    X509Store attributeStore;
    X509Store certificateStore;
    X509Store crlStore;
    private Map hashes;

    static 
    {
        HELPER = CMSSignedHelper.INSTANCE;
    }
}
