// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EC.java

package co.org.bouncy.jcajce.provider.asymmetric;

import co.org.bouncy.asn1.eac.EACObjectIdentifiers;
import co.org.bouncy.asn1.teletrust.TeleTrusTObjectIdentifiers;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.jcajce.provider.asymmetric.ec.KeyFactorySpi;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AsymmetricAlgorithmProvider;

public class EC
{
    public static class Mappings extends AsymmetricAlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("KeyAgreement.ECDH", "co.org.bouncy.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DH");
            provider.addAlgorithm("KeyAgreement.ECDHC", "co.org.bouncy.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHC");
            provider.addAlgorithm("KeyAgreement.ECMQV", "co.org.bouncy.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQV");
            provider.addAlgorithm((new StringBuilder()).append("KeyAgreement.").append(X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme).toString(), "co.org.bouncy.jcajce.provider.asymmetric.ec.KeyAgreementSpi$DHwithSHA1KDF");
            provider.addAlgorithm((new StringBuilder()).append("KeyAgreement.").append(X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme).toString(), "co.org.bouncy.jcajce.provider.asymmetric.ec.KeyAgreementSpi$MQVwithSHA1KDF");
            registerOid(provider, X9ObjectIdentifiers.id_ecPublicKey, "EC", new co.org.bouncy.jcajce.provider.asymmetric.ec.KeyFactorySpi.EC());
            registerOid(provider, X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme, "EC", new co.org.bouncy.jcajce.provider.asymmetric.ec.KeyFactorySpi.EC());
            registerOid(provider, X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme, "ECMQV", new co.org.bouncy.jcajce.provider.asymmetric.ec.KeyFactorySpi.ECMQV());
            registerOidAlgorithmParameters(provider, X9ObjectIdentifiers.id_ecPublicKey, "EC");
            registerOidAlgorithmParameters(provider, X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme, "EC");
            registerOidAlgorithmParameters(provider, X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme, "EC");
            provider.addAlgorithm("KeyFactory.EC", "co.org.bouncy.jcajce.provider.asymmetric.ec.KeyFactorySpi$EC");
            provider.addAlgorithm("KeyFactory.ECDSA", "co.org.bouncy.jcajce.provider.asymmetric.ec.KeyFactorySpi$ECDSA");
            provider.addAlgorithm("KeyFactory.ECDH", "co.org.bouncy.jcajce.provider.asymmetric.ec.KeyFactorySpi$ECDH");
            provider.addAlgorithm("KeyFactory.ECDHC", "co.org.bouncy.jcajce.provider.asymmetric.ec.KeyFactorySpi$ECDHC");
            provider.addAlgorithm("KeyFactory.ECMQV", "co.org.bouncy.jcajce.provider.asymmetric.ec.KeyFactorySpi$ECMQV");
            provider.addAlgorithm("KeyPairGenerator.EC", "co.org.bouncy.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$EC");
            provider.addAlgorithm("KeyPairGenerator.ECDSA", "co.org.bouncy.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECDSA");
            provider.addAlgorithm("KeyPairGenerator.ECDH", "co.org.bouncy.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECDH");
            provider.addAlgorithm("KeyPairGenerator.ECDHC", "co.org.bouncy.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECDHC");
            provider.addAlgorithm("KeyPairGenerator.ECIES", "co.org.bouncy.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECDH");
            provider.addAlgorithm("KeyPairGenerator.ECMQV", "co.org.bouncy.jcajce.provider.asymmetric.ec.KeyPairGeneratorSpi$ECMQV");
            provider.addAlgorithm("Cipher.ECIES", "co.org.bouncy.jcajce.provider.asymmetric.ec.IESCipher$ECIES");
            provider.addAlgorithm("Cipher.ECIESwithAES", "co.org.bouncy.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithAES");
            provider.addAlgorithm("Cipher.ECIESWITHAES", "co.org.bouncy.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithAES");
            provider.addAlgorithm("Cipher.ECIESwithDESEDE", "co.org.bouncy.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithDESede");
            provider.addAlgorithm("Cipher.ECIESWITHDESEDE", "co.org.bouncy.jcajce.provider.asymmetric.ec.IESCipher$ECIESwithDESede");
            provider.addAlgorithm("Signature.ECDSA", "co.org.bouncy.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA");
            provider.addAlgorithm("Signature.NONEwithECDSA", "co.org.bouncy.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSAnone");
            provider.addAlgorithm("Alg.Alias.Signature.SHA1withECDSA", "ECDSA");
            provider.addAlgorithm("Alg.Alias.Signature.ECDSAwithSHA1", "ECDSA");
            provider.addAlgorithm("Alg.Alias.Signature.SHA1WITHECDSA", "ECDSA");
            provider.addAlgorithm("Alg.Alias.Signature.ECDSAWITHSHA1", "ECDSA");
            provider.addAlgorithm("Alg.Alias.Signature.SHA1WithECDSA", "ECDSA");
            provider.addAlgorithm("Alg.Alias.Signature.ECDSAWithSHA1", "ECDSA");
            provider.addAlgorithm("Alg.Alias.Signature.1.2.840.10045.4.1", "ECDSA");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Signature.").append(TeleTrusTObjectIdentifiers.ecSignWithSha1).toString(), "ECDSA");
            addSignatureAlgorithm(provider, "SHA224", "ECDSA", "co.org.bouncy.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA224", X9ObjectIdentifiers.ecdsa_with_SHA224);
            addSignatureAlgorithm(provider, "SHA256", "ECDSA", "co.org.bouncy.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA256", X9ObjectIdentifiers.ecdsa_with_SHA256);
            addSignatureAlgorithm(provider, "SHA384", "ECDSA", "co.org.bouncy.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA384", X9ObjectIdentifiers.ecdsa_with_SHA384);
            addSignatureAlgorithm(provider, "SHA512", "ECDSA", "co.org.bouncy.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSA512", X9ObjectIdentifiers.ecdsa_with_SHA512);
            addSignatureAlgorithm(provider, "RIPEMD160", "ECDSA", "co.org.bouncy.jcajce.provider.asymmetric.ec.SignatureSpi$ecDSARipeMD160", TeleTrusTObjectIdentifiers.ecSignWithRipemd160);
            provider.addAlgorithm("Signature.SHA1WITHECNR", "co.org.bouncy.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR");
            provider.addAlgorithm("Signature.SHA224WITHECNR", "co.org.bouncy.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR224");
            provider.addAlgorithm("Signature.SHA256WITHECNR", "co.org.bouncy.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR256");
            provider.addAlgorithm("Signature.SHA384WITHECNR", "co.org.bouncy.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR384");
            provider.addAlgorithm("Signature.SHA512WITHECNR", "co.org.bouncy.jcajce.provider.asymmetric.ec.SignatureSpi$ecNR512");
            addSignatureAlgorithm(provider, "SHA1", "CVC-ECDSA", "co.org.bouncy.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_1);
            addSignatureAlgorithm(provider, "SHA224", "CVC-ECDSA", "co.org.bouncy.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA224", EACObjectIdentifiers.id_TA_ECDSA_SHA_224);
            addSignatureAlgorithm(provider, "SHA256", "CVC-ECDSA", "co.org.bouncy.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA256", EACObjectIdentifiers.id_TA_ECDSA_SHA_256);
            addSignatureAlgorithm(provider, "SHA384", "CVC-ECDSA", "co.org.bouncy.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA384", EACObjectIdentifiers.id_TA_ECDSA_SHA_384);
            addSignatureAlgorithm(provider, "SHA512", "CVC-ECDSA", "co.org.bouncy.jcajce.provider.asymmetric.ec.SignatureSpi$ecCVCDSA512", EACObjectIdentifiers.id_TA_ECDSA_SHA_512);
        }

        public Mappings()
        {
        }
    }


    public EC()
    {
    }

    private static final String PREFIX = "co.org.bouncy.jcajce.provider.asymmetric.ec.";
}
