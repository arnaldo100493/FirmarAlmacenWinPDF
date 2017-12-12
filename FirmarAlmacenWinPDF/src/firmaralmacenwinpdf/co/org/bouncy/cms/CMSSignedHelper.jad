// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSSignedHelper.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.OtherRevocationInfoFormat;
import co.org.bouncy.asn1.cryptopro.CryptoProObjectIdentifiers;
import co.org.bouncy.asn1.eac.EACObjectIdentifiers;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.teletrust.TeleTrusTObjectIdentifiers;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.cert.*;
import co.org.bouncy.cert.jcajce.JcaX509CRLConverter;
import co.org.bouncy.cert.jcajce.JcaX509CertificateConverter;
import co.org.bouncy.util.CollectionStore;
import co.org.bouncy.util.Store;
import co.org.bouncy.x509_.*;
import java.io.IOException;
import java.security.Provider;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.util.*;

// Referenced classes of package co.org.bouncy.cms:
//            CMSException, CMSSignedDataGenerator

class CMSSignedHelper
{

    CMSSignedHelper()
    {
    }

    private static void addEntries(ASN1ObjectIdentifier alias, String digest, String encryption)
    {
        digestAlgs.put(alias.getId(), digest);
        encryptionAlgs.put(alias.getId(), encryption);
    }

    String getDigestAlgName(String digestAlgOID)
    {
        String algName = (String)digestAlgs.get(digestAlgOID);
        if(algName != null)
            return algName;
        else
            return digestAlgOID;
    }

    String getEncryptionAlgName(String encryptionAlgOID)
    {
        String algName = (String)encryptionAlgs.get(encryptionAlgOID);
        if(algName != null)
            return algName;
        else
            return encryptionAlgOID;
    }

    X509Store createAttributeStore(String type, Provider provider, Store certStore)
        throws NoSuchStoreException, CMSException
    {
        try
        {
            Collection certHldrs = certStore.getMatches(null);
            List certs = new ArrayList(certHldrs.size());
            for(Iterator it = certHldrs.iterator(); it.hasNext(); certs.add(new X509V2AttributeCertificate(((X509AttributeCertificateHolder)it.next()).getEncoded())));
            return X509Store.getInstance((new StringBuilder()).append("AttributeCertificate/").append(type).toString(), new X509CollectionStoreParameters(certs), provider);
        }
        catch(IllegalArgumentException e)
        {
            throw new CMSException("can't setup the X509Store", e);
        }
        catch(IOException e)
        {
            throw new CMSException("can't setup the X509Store", e);
        }
    }

    X509Store createCertificateStore(String type, Provider provider, Store certStore)
        throws NoSuchStoreException, CMSException
    {
        try
        {
            JcaX509CertificateConverter converter = (new JcaX509CertificateConverter()).setProvider(provider);
            Collection certHldrs = certStore.getMatches(null);
            List certs = new ArrayList(certHldrs.size());
            for(Iterator it = certHldrs.iterator(); it.hasNext(); certs.add(converter.getCertificate((X509CertificateHolder)it.next())));
            return X509Store.getInstance((new StringBuilder()).append("Certificate/").append(type).toString(), new X509CollectionStoreParameters(certs), provider);
        }
        catch(IllegalArgumentException e)
        {
            throw new CMSException("can't setup the X509Store", e);
        }
        catch(CertificateException e)
        {
            throw new CMSException("can't setup the X509Store", e);
        }
    }

    X509Store createCRLsStore(String type, Provider provider, Store crlStore)
        throws NoSuchStoreException, CMSException
    {
        try
        {
            JcaX509CRLConverter converter = (new JcaX509CRLConverter()).setProvider(provider);
            Collection crlHldrs = crlStore.getMatches(null);
            List crls = new ArrayList(crlHldrs.size());
            for(Iterator it = crlHldrs.iterator(); it.hasNext(); crls.add(converter.getCRL((X509CRLHolder)it.next())));
            return X509Store.getInstance((new StringBuilder()).append("CRL/").append(type).toString(), new X509CollectionStoreParameters(crls), provider);
        }
        catch(IllegalArgumentException e)
        {
            throw new CMSException("can't setup the X509Store", e);
        }
        catch(CRLException e)
        {
            throw new CMSException("can't setup the X509Store", e);
        }
    }

    AlgorithmIdentifier fixAlgID(AlgorithmIdentifier algId)
    {
        if(algId.getParameters() == null)
            return new AlgorithmIdentifier(algId.getAlgorithm(), DERNull.INSTANCE);
        else
            return algId;
    }

    void setSigningEncryptionAlgorithmMapping(ASN1ObjectIdentifier oid, String algorithmName)
    {
        encryptionAlgs.put(oid.getId(), algorithmName);
    }

    void setSigningDigestAlgorithmMapping(ASN1ObjectIdentifier oid, String algorithmName)
    {
        digestAlgs.put(oid.getId(), algorithmName);
    }

    Store getCertificates(ASN1Set certSet)
    {
        if(certSet != null)
        {
            List certList = new ArrayList(certSet.size());
            Enumeration en = certSet.getObjects();
            do
            {
                if(!en.hasMoreElements())
                    break;
                co.org.bouncy.asn1.ASN1Primitive obj = ((ASN1Encodable)en.nextElement()).toASN1Primitive();
                if(obj instanceof ASN1Sequence)
                    certList.add(new X509CertificateHolder(Certificate.getInstance(obj)));
            } while(true);
            return new CollectionStore(certList);
        } else
        {
            return new CollectionStore(new ArrayList());
        }
    }

    Store getAttributeCertificates(ASN1Set certSet)
    {
        if(certSet != null)
        {
            List certList = new ArrayList(certSet.size());
            Enumeration en = certSet.getObjects();
            do
            {
                if(!en.hasMoreElements())
                    break;
                co.org.bouncy.asn1.ASN1Primitive obj = ((ASN1Encodable)en.nextElement()).toASN1Primitive();
                if(obj instanceof ASN1TaggedObject)
                    certList.add(new X509AttributeCertificateHolder(AttributeCertificate.getInstance(((ASN1TaggedObject)obj).getObject())));
            } while(true);
            return new CollectionStore(certList);
        } else
        {
            return new CollectionStore(new ArrayList());
        }
    }

    Store getCRLs(ASN1Set crlSet)
    {
        if(crlSet != null)
        {
            List crlList = new ArrayList(crlSet.size());
            Enumeration en = crlSet.getObjects();
            do
            {
                if(!en.hasMoreElements())
                    break;
                co.org.bouncy.asn1.ASN1Primitive obj = ((ASN1Encodable)en.nextElement()).toASN1Primitive();
                if(obj instanceof ASN1Sequence)
                    crlList.add(new X509CRLHolder(CertificateList.getInstance(obj)));
            } while(true);
            return new CollectionStore(crlList);
        } else
        {
            return new CollectionStore(new ArrayList());
        }
    }

    Store getOtherRevocationInfo(ASN1ObjectIdentifier otherRevocationInfoFormat, ASN1Set crlSet)
    {
        if(crlSet != null)
        {
            List crlList = new ArrayList(crlSet.size());
            Enumeration en = crlSet.getObjects();
            do
            {
                if(!en.hasMoreElements())
                    break;
                co.org.bouncy.asn1.ASN1Primitive obj = ((ASN1Encodable)en.nextElement()).toASN1Primitive();
                if(obj instanceof ASN1TaggedObject)
                {
                    ASN1TaggedObject tObj = ASN1TaggedObject.getInstance(obj);
                    if(tObj.getTagNo() == 1)
                    {
                        OtherRevocationInfoFormat other = OtherRevocationInfoFormat.getInstance(tObj, false);
                        if(otherRevocationInfoFormat.equals(other.getInfoFormat()))
                            crlList.add(other.getInfo());
                    }
                }
            } while(true);
            return new CollectionStore(crlList);
        } else
        {
            return new CollectionStore(new ArrayList());
        }
    }

    static final CMSSignedHelper INSTANCE = new CMSSignedHelper();
    private static final Map encryptionAlgs;
    private static final Map digestAlgs;
    private static final Map digestAliases;

    static 
    {
        encryptionAlgs = new HashMap();
        digestAlgs = new HashMap();
        digestAliases = new HashMap();
        addEntries(NISTObjectIdentifiers.dsa_with_sha224, "SHA224", "DSA");
        addEntries(NISTObjectIdentifiers.dsa_with_sha256, "SHA256", "DSA");
        addEntries(NISTObjectIdentifiers.dsa_with_sha384, "SHA384", "DSA");
        addEntries(NISTObjectIdentifiers.dsa_with_sha512, "SHA512", "DSA");
        addEntries(OIWObjectIdentifiers.dsaWithSHA1, "SHA1", "DSA");
        addEntries(OIWObjectIdentifiers.md4WithRSA, "MD4", "RSA");
        addEntries(OIWObjectIdentifiers.md4WithRSAEncryption, "MD4", "RSA");
        addEntries(OIWObjectIdentifiers.md5WithRSA, "MD5", "RSA");
        addEntries(OIWObjectIdentifiers.sha1WithRSA, "SHA1", "RSA");
        addEntries(PKCSObjectIdentifiers.md2WithRSAEncryption, "MD2", "RSA");
        addEntries(PKCSObjectIdentifiers.md4WithRSAEncryption, "MD4", "RSA");
        addEntries(PKCSObjectIdentifiers.md5WithRSAEncryption, "MD5", "RSA");
        addEntries(PKCSObjectIdentifiers.sha1WithRSAEncryption, "SHA1", "RSA");
        addEntries(PKCSObjectIdentifiers.sha224WithRSAEncryption, "SHA224", "RSA");
        addEntries(PKCSObjectIdentifiers.sha256WithRSAEncryption, "SHA256", "RSA");
        addEntries(PKCSObjectIdentifiers.sha384WithRSAEncryption, "SHA384", "RSA");
        addEntries(PKCSObjectIdentifiers.sha512WithRSAEncryption, "SHA512", "RSA");
        addEntries(X9ObjectIdentifiers.ecdsa_with_SHA1, "SHA1", "ECDSA");
        addEntries(X9ObjectIdentifiers.ecdsa_with_SHA224, "SHA224", "ECDSA");
        addEntries(X9ObjectIdentifiers.ecdsa_with_SHA256, "SHA256", "ECDSA");
        addEntries(X9ObjectIdentifiers.ecdsa_with_SHA384, "SHA384", "ECDSA");
        addEntries(X9ObjectIdentifiers.ecdsa_with_SHA512, "SHA512", "ECDSA");
        addEntries(X9ObjectIdentifiers.id_dsa_with_sha1, "SHA1", "DSA");
        addEntries(EACObjectIdentifiers.id_TA_ECDSA_SHA_1, "SHA1", "ECDSA");
        addEntries(EACObjectIdentifiers.id_TA_ECDSA_SHA_224, "SHA224", "ECDSA");
        addEntries(EACObjectIdentifiers.id_TA_ECDSA_SHA_256, "SHA256", "ECDSA");
        addEntries(EACObjectIdentifiers.id_TA_ECDSA_SHA_384, "SHA384", "ECDSA");
        addEntries(EACObjectIdentifiers.id_TA_ECDSA_SHA_512, "SHA512", "ECDSA");
        addEntries(EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_1, "SHA1", "RSA");
        addEntries(EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_256, "SHA256", "RSA");
        addEntries(EACObjectIdentifiers.id_TA_RSA_PSS_SHA_1, "SHA1", "RSAandMGF1");
        addEntries(EACObjectIdentifiers.id_TA_RSA_PSS_SHA_256, "SHA256", "RSAandMGF1");
        encryptionAlgs.put(X9ObjectIdentifiers.id_dsa.getId(), "DSA");
        encryptionAlgs.put(PKCSObjectIdentifiers.rsaEncryption.getId(), "RSA");
        encryptionAlgs.put(TeleTrusTObjectIdentifiers.teleTrusTRSAsignatureAlgorithm, "RSA");
        encryptionAlgs.put(X509ObjectIdentifiers.id_ea_rsa.getId(), "RSA");
        encryptionAlgs.put(CMSSignedDataGenerator.ENCRYPTION_RSA_PSS, "RSAandMGF1");
        encryptionAlgs.put(CryptoProObjectIdentifiers.gostR3410_94.getId(), "GOST3410");
        encryptionAlgs.put(CryptoProObjectIdentifiers.gostR3410_2001.getId(), "ECGOST3410");
        encryptionAlgs.put("1.3.6.1.4.1.5849.1.6.2", "ECGOST3410");
        encryptionAlgs.put("1.3.6.1.4.1.5849.1.1.5", "GOST3410");
        encryptionAlgs.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001.getId(), "ECGOST3410");
        encryptionAlgs.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94.getId(), "GOST3410");
        digestAlgs.put(PKCSObjectIdentifiers.md2.getId(), "MD2");
        digestAlgs.put(PKCSObjectIdentifiers.md4.getId(), "MD4");
        digestAlgs.put(PKCSObjectIdentifiers.md5.getId(), "MD5");
        digestAlgs.put(OIWObjectIdentifiers.idSHA1.getId(), "SHA1");
        digestAlgs.put(NISTObjectIdentifiers.id_sha224.getId(), "SHA224");
        digestAlgs.put(NISTObjectIdentifiers.id_sha256.getId(), "SHA256");
        digestAlgs.put(NISTObjectIdentifiers.id_sha384.getId(), "SHA384");
        digestAlgs.put(NISTObjectIdentifiers.id_sha512.getId(), "SHA512");
        digestAlgs.put(TeleTrusTObjectIdentifiers.ripemd128.getId(), "RIPEMD128");
        digestAlgs.put(TeleTrusTObjectIdentifiers.ripemd160.getId(), "RIPEMD160");
        digestAlgs.put(TeleTrusTObjectIdentifiers.ripemd256.getId(), "RIPEMD256");
        digestAlgs.put(CryptoProObjectIdentifiers.gostR3411.getId(), "GOST3411");
        digestAlgs.put("1.3.6.1.4.1.5849.1.2.1", "GOST3411");
        digestAliases.put("SHA1", new String[] {
            "SHA-1"
        });
        digestAliases.put("SHA224", new String[] {
            "SHA-224"
        });
        digestAliases.put("SHA256", new String[] {
            "SHA-256"
        });
        digestAliases.put("SHA384", new String[] {
            "SHA-384"
        });
        digestAliases.put("SHA512", new String[] {
            "SHA-512"
        });
    }
}
