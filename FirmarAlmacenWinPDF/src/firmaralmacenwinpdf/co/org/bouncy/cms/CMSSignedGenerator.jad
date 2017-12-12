// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSSignedGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.cryptopro.CryptoProObjectIdentifiers;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.teletrust.TeleTrusTObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.AttributeCertificate;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.cert.*;
import co.org.bouncy.jce.interfaces.GOST3410PrivateKey;
import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.Store;
import co.org.bouncy.x509_.X509AttributeCertificate;
import co.org.bouncy.x509_.X509Store;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.*;

// Referenced classes of package co.org.bouncy.cms:
//            CMSException, CMSUtils, SignerInformationStore, SignerInfoGenerator

public class CMSSignedGenerator
{

    protected CMSSignedGenerator()
    {
        this(new SecureRandom());
    }

    protected CMSSignedGenerator(SecureRandom rand)
    {
        certs = new ArrayList();
        crls = new ArrayList();
        _signers = new ArrayList();
        signerGens = new ArrayList();
        digests = new HashMap();
        this.rand = rand;
    }

    protected String getEncOID(PrivateKey key, String digestOID)
    {
        String encOID = null;
        if((key instanceof RSAPrivateKey) || "RSA".equalsIgnoreCase(key.getAlgorithm()))
            encOID = ENCRYPTION_RSA;
        else
        if((key instanceof DSAPrivateKey) || "DSA".equalsIgnoreCase(key.getAlgorithm()))
        {
            encOID = ENCRYPTION_DSA;
            if(!digestOID.equals(DIGEST_SHA1))
                throw new IllegalArgumentException("can't mix DSA with anything but SHA1");
        } else
        if("ECDSA".equalsIgnoreCase(key.getAlgorithm()) || "EC".equalsIgnoreCase(key.getAlgorithm()))
        {
            encOID = (String)EC_ALGORITHMS.get(digestOID);
            if(encOID == null)
                throw new IllegalArgumentException("can't mix ECDSA with anything but SHA family digests");
        } else
        if((key instanceof GOST3410PrivateKey) || "GOST3410".equalsIgnoreCase(key.getAlgorithm()))
            encOID = ENCRYPTION_GOST3410;
        else
        if("ECGOST3410".equalsIgnoreCase(key.getAlgorithm()))
            encOID = ENCRYPTION_ECGOST3410;
        return encOID;
    }

    protected Map getBaseParameters(ASN1ObjectIdentifier contentType, AlgorithmIdentifier digAlgId, byte hash[])
    {
        Map param = new HashMap();
        param.put("contentType", contentType);
        param.put("digestAlgID", digAlgId);
        param.put("digest", Arrays.clone(hash));
        return param;
    }

    protected ASN1Set getAttributeSet(AttributeTable attr)
    {
        if(attr != null)
            return new DERSet(attr.toASN1EncodableVector());
        else
            return null;
    }

    /**
     * @deprecated Method addCertificatesAndCRLs is deprecated
     */

    public void addCertificatesAndCRLs(CertStore certStore)
        throws CertStoreException, CMSException
    {
        certs.addAll(CMSUtils.getCertificatesFromStore(certStore));
        crls.addAll(CMSUtils.getCRLsFromStore(certStore));
    }

    public void addCertificate(X509CertificateHolder certificate)
        throws CMSException
    {
        certs.add(certificate.toASN1Structure());
    }

    public void addCertificates(Store certStore)
        throws CMSException
    {
        certs.addAll(CMSUtils.getCertificatesFromStore(certStore));
    }

    public void addCRL(X509CRLHolder crl)
    {
        crls.add(crl.toASN1Structure());
    }

    public void addCRLs(Store crlStore)
        throws CMSException
    {
        crls.addAll(CMSUtils.getCRLsFromStore(crlStore));
    }

    public void addAttributeCertificate(X509AttributeCertificateHolder attrCert)
        throws CMSException
    {
        certs.add(new DERTaggedObject(false, 2, attrCert.toASN1Structure()));
    }

    public void addAttributeCertificates(Store attrStore)
        throws CMSException
    {
        certs.addAll(CMSUtils.getAttributeCertificatesFromStore(attrStore));
    }

    public void addOtherRevocationInfo(ASN1ObjectIdentifier otherRevocationInfoFormat, ASN1Encodable otherRevocationInfo)
    {
        crls.add(new DERTaggedObject(false, 1, new OtherRevocationInfoFormat(otherRevocationInfoFormat, otherRevocationInfo)));
    }

    public void addOtherRevocationInfo(ASN1ObjectIdentifier otherRevocationInfoFormat, Store otherRevocationInfos)
    {
        crls.addAll(CMSUtils.getOthersFromStore(otherRevocationInfoFormat, otherRevocationInfos));
    }

    /**
     * @deprecated Method addAttributeCertificates is deprecated
     */

    public void addAttributeCertificates(X509Store store)
        throws CMSException
    {
        try
        {
            X509AttributeCertificate attrCert;
            for(Iterator it = store.getMatches(null).iterator(); it.hasNext(); certs.add(new DERTaggedObject(false, 2, AttributeCertificate.getInstance(ASN1Primitive.fromByteArray(attrCert.getEncoded())))))
                attrCert = (X509AttributeCertificate)it.next();

        }
        catch(IllegalArgumentException e)
        {
            throw new CMSException("error processing attribute certs", e);
        }
        catch(IOException e)
        {
            throw new CMSException("error processing attribute certs", e);
        }
    }

    public void addSigners(SignerInformationStore signerStore)
    {
        for(Iterator it = signerStore.getSigners().iterator(); it.hasNext(); _signers.add(it.next()));
    }

    public void addSignerInfoGenerator(SignerInfoGenerator infoGen)
    {
        signerGens.add(infoGen);
    }

    public Map getGeneratedDigests()
    {
        return new HashMap(digests);
    }

    public static final String DATA;
    public static final String DIGEST_SHA1;
    public static final String DIGEST_SHA224;
    public static final String DIGEST_SHA256;
    public static final String DIGEST_SHA384;
    public static final String DIGEST_SHA512;
    public static final String DIGEST_MD5;
    public static final String DIGEST_GOST3411;
    public static final String DIGEST_RIPEMD128;
    public static final String DIGEST_RIPEMD160;
    public static final String DIGEST_RIPEMD256;
    public static final String ENCRYPTION_RSA;
    public static final String ENCRYPTION_DSA;
    public static final String ENCRYPTION_ECDSA;
    public static final String ENCRYPTION_RSA_PSS;
    public static final String ENCRYPTION_GOST3410;
    public static final String ENCRYPTION_ECGOST3410;
    private static final String ENCRYPTION_ECDSA_WITH_SHA1;
    private static final String ENCRYPTION_ECDSA_WITH_SHA224;
    private static final String ENCRYPTION_ECDSA_WITH_SHA256;
    private static final String ENCRYPTION_ECDSA_WITH_SHA384;
    private static final String ENCRYPTION_ECDSA_WITH_SHA512;
    private static final Set NO_PARAMS;
    private static final Map EC_ALGORITHMS;
    protected List certs;
    protected List crls;
    protected List _signers;
    protected List signerGens;
    protected Map digests;
    protected final SecureRandom rand;

    static 
    {
        DATA = CMSObjectIdentifiers.data.getId();
        DIGEST_SHA1 = OIWObjectIdentifiers.idSHA1.getId();
        DIGEST_SHA224 = NISTObjectIdentifiers.id_sha224.getId();
        DIGEST_SHA256 = NISTObjectIdentifiers.id_sha256.getId();
        DIGEST_SHA384 = NISTObjectIdentifiers.id_sha384.getId();
        DIGEST_SHA512 = NISTObjectIdentifiers.id_sha512.getId();
        DIGEST_MD5 = PKCSObjectIdentifiers.md5.getId();
        DIGEST_GOST3411 = CryptoProObjectIdentifiers.gostR3411.getId();
        DIGEST_RIPEMD128 = TeleTrusTObjectIdentifiers.ripemd128.getId();
        DIGEST_RIPEMD160 = TeleTrusTObjectIdentifiers.ripemd160.getId();
        DIGEST_RIPEMD256 = TeleTrusTObjectIdentifiers.ripemd256.getId();
        ENCRYPTION_RSA = PKCSObjectIdentifiers.rsaEncryption.getId();
        ENCRYPTION_DSA = X9ObjectIdentifiers.id_dsa_with_sha1.getId();
        ENCRYPTION_ECDSA = X9ObjectIdentifiers.ecdsa_with_SHA1.getId();
        ENCRYPTION_RSA_PSS = PKCSObjectIdentifiers.id_RSASSA_PSS.getId();
        ENCRYPTION_GOST3410 = CryptoProObjectIdentifiers.gostR3410_94.getId();
        ENCRYPTION_ECGOST3410 = CryptoProObjectIdentifiers.gostR3410_2001.getId();
        ENCRYPTION_ECDSA_WITH_SHA1 = X9ObjectIdentifiers.ecdsa_with_SHA1.getId();
        ENCRYPTION_ECDSA_WITH_SHA224 = X9ObjectIdentifiers.ecdsa_with_SHA224.getId();
        ENCRYPTION_ECDSA_WITH_SHA256 = X9ObjectIdentifiers.ecdsa_with_SHA256.getId();
        ENCRYPTION_ECDSA_WITH_SHA384 = X9ObjectIdentifiers.ecdsa_with_SHA384.getId();
        ENCRYPTION_ECDSA_WITH_SHA512 = X9ObjectIdentifiers.ecdsa_with_SHA512.getId();
        NO_PARAMS = new HashSet();
        EC_ALGORITHMS = new HashMap();
        NO_PARAMS.add(ENCRYPTION_DSA);
        NO_PARAMS.add(ENCRYPTION_ECDSA);
        NO_PARAMS.add(ENCRYPTION_ECDSA_WITH_SHA1);
        NO_PARAMS.add(ENCRYPTION_ECDSA_WITH_SHA224);
        NO_PARAMS.add(ENCRYPTION_ECDSA_WITH_SHA256);
        NO_PARAMS.add(ENCRYPTION_ECDSA_WITH_SHA384);
        NO_PARAMS.add(ENCRYPTION_ECDSA_WITH_SHA512);
        EC_ALGORITHMS.put(DIGEST_SHA1, ENCRYPTION_ECDSA_WITH_SHA1);
        EC_ALGORITHMS.put(DIGEST_SHA224, ENCRYPTION_ECDSA_WITH_SHA224);
        EC_ALGORITHMS.put(DIGEST_SHA256, ENCRYPTION_ECDSA_WITH_SHA256);
        EC_ALGORITHMS.put(DIGEST_SHA384, ENCRYPTION_ECDSA_WITH_SHA384);
        EC_ALGORITHMS.put(DIGEST_SHA512, ENCRYPTION_ECDSA_WITH_SHA512);
    }
}
