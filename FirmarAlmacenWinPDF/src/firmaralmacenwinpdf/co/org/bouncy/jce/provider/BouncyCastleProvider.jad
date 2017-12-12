// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BouncyCastleProvider.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.config.ProviderConfiguration;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;
import co.org.bouncy.jcajce.provider.util.AsymmetricKeyInfoConverter;
import java.io.IOException;
import java.security.*;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package co.org.bouncy.jce.provider:
//            BouncyCastleProviderConfiguration

public final class BouncyCastleProvider extends Provider
    implements ConfigurableProvider
{

    public BouncyCastleProvider()
    {
        super("BC", 1.49D, info);
        AccessController.doPrivileged(new PrivilegedAction() {

            public Object run()
            {
                setup();
                return null;
            }

            final BouncyCastleProvider this$0;

            
            {
                this$0 = BouncyCastleProvider.this;
                super();
            }
        }
);
    }

    private void setup()
    {
        loadAlgorithms("co.org.bouncy.jcajce.provider.digest.", DIGESTS);
        loadAlgorithms("co.org.bouncy.jcajce.provider.symmetric.", SYMMETRIC_GENERIC);
        loadAlgorithms("co.org.bouncy.jcajce.provider.symmetric.", SYMMETRIC_MACS);
        loadAlgorithms("co.org.bouncy.jcajce.provider.symmetric.", SYMMETRIC_CIPHERS);
        loadAlgorithms("co.org.bouncy.jcajce.provider.asymmetric.", ASYMMETRIC_GENERIC);
        loadAlgorithms("co.org.bouncy.jcajce.provider.asymmetric.", ASYMMETRIC_CIPHERS);
        loadAlgorithms("co.org.bouncy.jcajce.provider.keystore.", KEYSTORES);
        put("X509Store.CERTIFICATE/COLLECTION", "co.org.bouncy.jce.provider.X509StoreCertCollection");
        put("X509Store.ATTRIBUTECERTIFICATE/COLLECTION", "co.org.bouncy.jce.provider.X509StoreAttrCertCollection");
        put("X509Store.CRL/COLLECTION", "co.org.bouncy.jce.provider.X509StoreCRLCollection");
        put("X509Store.CERTIFICATEPAIR/COLLECTION", "co.org.bouncy.jce.provider.X509StoreCertPairCollection");
        put("X509Store.CERTIFICATE/LDAP", "co.org.bouncy.jce.provider.X509StoreLDAPCerts");
        put("X509Store.CRL/LDAP", "co.org.bouncy.jce.provider.X509StoreLDAPCRLs");
        put("X509Store.ATTRIBUTECERTIFICATE/LDAP", "co.org.bouncy.jce.provider.X509StoreLDAPAttrCerts");
        put("X509Store.CERTIFICATEPAIR/LDAP", "co.org.bouncy.jce.provider.X509StoreLDAPCertPairs");
        put("X509StreamParser.CERTIFICATE", "co.org.bouncy.jce.provider.X509CertParser");
        put("X509StreamParser.ATTRIBUTECERTIFICATE", "co.org.bouncy.jce.provider.X509AttrCertParser");
        put("X509StreamParser.CRL", "co.org.bouncy.jce.provider.X509CRLParser");
        put("X509StreamParser.CERTIFICATEPAIR", "co.org.bouncy.jce.provider.X509CertPairParser");
        put("Cipher.BROKENPBEWITHMD5ANDDES", "co.org.bouncy.jce.provider.BrokenJCEBlockCipher$BrokePBEWithMD5AndDES");
        put("Cipher.BROKENPBEWITHSHA1ANDDES", "co.org.bouncy.jce.provider.BrokenJCEBlockCipher$BrokePBEWithSHA1AndDES");
        put("Cipher.OLDPBEWITHSHAANDTWOFISH-CBC", "co.org.bouncy.jce.provider.BrokenJCEBlockCipher$OldPBEWithSHAAndTwofish");
        put("CertPathValidator.RFC3281", "co.org.bouncy.jce.provider.PKIXAttrCertPathValidatorSpi");
        put("CertPathBuilder.RFC3281", "co.org.bouncy.jce.provider.PKIXAttrCertPathBuilderSpi");
        put("CertPathValidator.RFC3280", "co.org.bouncy.jce.provider.PKIXCertPathValidatorSpi");
        put("CertPathBuilder.RFC3280", "co.org.bouncy.jce.provider.PKIXCertPathBuilderSpi");
        put("CertPathValidator.PKIX", "co.org.bouncy.jce.provider.PKIXCertPathValidatorSpi");
        put("CertPathBuilder.PKIX", "co.org.bouncy.jce.provider.PKIXCertPathBuilderSpi");
        put("CertStore.Collection", "co.org.bouncy.jce.provider.CertStoreCollectionSpi");
        put("CertStore.LDAP", "co.org.bouncy.jce.provider.X509LDAPCertStoreSpi");
        put("CertStore.Multi", "co.org.bouncy.jce.provider.MultiCertStoreSpi");
        put("Alg.Alias.CertStore.X509LDAP", "LDAP");
    }

    private void loadAlgorithms(String packageName, String names[])
    {
        for(int i = 0; i != names.length; i++)
        {
            Class clazz = null;
            try
            {
                ClassLoader loader = getClass().getClassLoader();
                if(loader != null)
                    clazz = loader.loadClass((new StringBuilder()).append(packageName).append(names[i]).append("$Mappings").toString());
                else
                    clazz = Class.forName((new StringBuilder()).append(packageName).append(names[i]).append("$Mappings").toString());
            }
            catch(ClassNotFoundException e) { }
            if(clazz == null)
                continue;
            try
            {
                ((AlgorithmProvider)clazz.newInstance()).configure(this);
            }
            catch(Exception e)
            {
                throw new InternalError((new StringBuilder()).append("cannot create instance of ").append(packageName).append(names[i]).append("$Mappings : ").append(e).toString());
            }
        }

    }

    public void setParameter(String parameterName, Object parameter)
    {
        synchronized(CONFIGURATION)
        {
            ((BouncyCastleProviderConfiguration)CONFIGURATION).setParameter(parameterName, parameter);
        }
    }

    public boolean hasAlgorithm(String type, String name)
    {
        return containsKey((new StringBuilder()).append(type).append(".").append(name).toString()) || containsKey((new StringBuilder()).append("Alg.Alias.").append(type).append(".").append(name).toString());
    }

    public void addAlgorithm(String key, String value)
    {
        if(containsKey(key))
        {
            throw new IllegalStateException((new StringBuilder()).append("duplicate provider key (").append(key).append(") found").toString());
        } else
        {
            put(key, value);
            return;
        }
    }

    public void addKeyInfoConverter(ASN1ObjectIdentifier oid, AsymmetricKeyInfoConverter keyInfoConverter)
    {
        keyInfoConverters.put(oid, keyInfoConverter);
    }

    public static PublicKey getPublicKey(SubjectPublicKeyInfo publicKeyInfo)
        throws IOException
    {
        AsymmetricKeyInfoConverter converter = (AsymmetricKeyInfoConverter)keyInfoConverters.get(publicKeyInfo.getAlgorithm().getAlgorithm());
        if(converter == null)
            return null;
        else
            return converter.generatePublic(publicKeyInfo);
    }

    public static PrivateKey getPrivateKey(PrivateKeyInfo privateKeyInfo)
        throws IOException
    {
        AsymmetricKeyInfoConverter converter = (AsymmetricKeyInfoConverter)keyInfoConverters.get(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm());
        if(converter == null)
            return null;
        else
            return converter.generatePrivate(privateKeyInfo);
    }

    private static String info = "BouncyCastle Security Provider v1.49";
    public static final String PROVIDER_NAME = "BC";
    public static final ProviderConfiguration CONFIGURATION = new BouncyCastleProviderConfiguration();
    private static final Map keyInfoConverters = new HashMap();
    private static final String SYMMETRIC_PACKAGE = "co.org.bouncy.jcajce.provider.symmetric.";
    private static final String SYMMETRIC_GENERIC[] = {
        "PBEPBKDF2", "PBEPKCS12"
    };
    private static final String SYMMETRIC_MACS[] = {
        "SipHash"
    };
    private static final String SYMMETRIC_CIPHERS[] = {
        "AES", "ARC4", "Blowfish", "Camellia", "CAST5", "CAST6", "DES", "DESede", "GOST28147", "Grainv1", 
        "Grain128", "HC128", "HC256", "IDEA", "Noekeon", "RC2", "RC5", "RC6", "Rijndael", "Salsa20", 
        "SEED", "Serpent", "Skipjack", "TEA", "Twofish", "VMPC", "VMPCKSA3", "XTEA"
    };
    private static final String ASYMMETRIC_PACKAGE = "co.org.bouncy.jcajce.provider.asymmetric.";
    private static final String ASYMMETRIC_GENERIC[] = {
        "X509", "IES"
    };
    private static final String ASYMMETRIC_CIPHERS[] = {
        "DSA", "DH", "EC", "RSA", "GOST", "ECGOST", "ElGamal", "DSTU4145"
    };
    private static final String DIGEST_PACKAGE = "co.org.bouncy.jcajce.provider.digest.";
    private static final String DIGESTS[] = {
        "GOST3411", "MD2", "MD4", "MD5", "SHA1", "RIPEMD128", "RIPEMD160", "RIPEMD256", "RIPEMD320", "SHA224", 
        "SHA256", "SHA384", "SHA512", "SHA3", "Tiger", "Whirlpool"
    };
    private static final String KEYSTORE_PACKAGE = "co.org.bouncy.jcajce.provider.keystore.";
    private static final String KEYSTORES[] = {
        "BC", "PKCS12"
    };


}
