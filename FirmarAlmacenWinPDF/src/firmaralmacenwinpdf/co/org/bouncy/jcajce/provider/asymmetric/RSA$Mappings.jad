// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RSA.java

package co.org.bouncy.jcajce.provider.asymmetric;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.teletrust.TeleTrusTObjectIdentifiers;
import co.org.bouncy.asn1.x509.X509ObjectIdentifiers;
import co.org.bouncy.jcajce.provider.asymmetric.rsa.KeyFactorySpi;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.util.AsymmetricAlgorithmProvider;
import co.org.bouncy.jcajce.provider.util.AsymmetricKeyInfoConverter;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric:
//            RSA

public static class RSA$Mappings extends AsymmetricAlgorithmProvider
{

    public void configure(ConfigurableProvider provider)
    {
        provider.addAlgorithm("AlgorithmParameters.OAEP", "co.org.bouncy.jcajce.provider.asymmetric.rsa.AlgorithmParametersSpi$OAEP");
        provider.addAlgorithm("AlgorithmParameters.PSS", "co.org.bouncy.jcajce.provider.asymmetric.rsa.AlgorithmParametersSpi$PSS");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.RSAPSS", "PSS");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.RSASSA-PSS", "PSS");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.SHA224withRSA/PSS", "PSS");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.SHA256withRSA/PSS", "PSS");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.SHA384withRSA/PSS", "PSS");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.SHA512withRSA/PSS", "PSS");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.SHA224WITHRSAANDMGF1", "PSS");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.SHA256WITHRSAANDMGF1", "PSS");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.SHA384WITHRSAANDMGF1", "PSS");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.SHA512WITHRSAANDMGF1", "PSS");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.RAWRSAPSS", "PSS");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.NONEWITHRSAPSS", "PSS");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.NONEWITHRSASSA-PSS", "PSS");
        provider.addAlgorithm("Alg.Alias.AlgorithmParameters.NONEWITHRSAANDMGF1", "PSS");
        provider.addAlgorithm("Cipher.RSA", "co.org.bouncy.jcajce.provider.asymmetric.rsa.CipherSpi$NoPadding");
        provider.addAlgorithm("Cipher.RSA/RAW", "co.org.bouncy.jcajce.provider.asymmetric.rsa.CipherSpi$NoPadding");
        provider.addAlgorithm("Cipher.RSA/PKCS1", "co.org.bouncy.jcajce.provider.asymmetric.rsa.CipherSpi$PKCS1v1_5Padding");
        provider.addAlgorithm("Cipher.1.2.840.113549.1.1.1", "co.org.bouncy.jcajce.provider.asymmetric.rsa.CipherSpi$PKCS1v1_5Padding");
        provider.addAlgorithm("Cipher.2.5.8.1.1", "co.org.bouncy.jcajce.provider.asymmetric.rsa.CipherSpi$PKCS1v1_5Padding");
        provider.addAlgorithm("Cipher.RSA/1", "co.org.bouncy.jcajce.provider.asymmetric.rsa.CipherSpi$PKCS1v1_5Padding_PrivateOnly");
        provider.addAlgorithm("Cipher.RSA/2", "co.org.bouncy.jcajce.provider.asymmetric.rsa.CipherSpi$PKCS1v1_5Padding_PublicOnly");
        provider.addAlgorithm("Cipher.RSA/OAEP", "co.org.bouncy.jcajce.provider.asymmetric.rsa.CipherSpi$OAEPPadding");
        provider.addAlgorithm((new StringBuilder()).append("Cipher.").append(PKCSObjectIdentifiers.id_RSAES_OAEP).toString(), "co.org.bouncy.jcajce.provider.asymmetric.rsa.CipherSpi$OAEPPadding");
        provider.addAlgorithm("Cipher.RSA/ISO9796-1", "co.org.bouncy.jcajce.provider.asymmetric.rsa.CipherSpi$ISO9796d1Padding");
        provider.addAlgorithm("Alg.Alias.Cipher.RSA//RAW", "RSA");
        provider.addAlgorithm("Alg.Alias.Cipher.RSA//NOPADDING", "RSA");
        provider.addAlgorithm("Alg.Alias.Cipher.RSA//PKCS1PADDING", "RSA/PKCS1");
        provider.addAlgorithm("Alg.Alias.Cipher.RSA//OAEPPADDING", "RSA/OAEP");
        provider.addAlgorithm("Alg.Alias.Cipher.RSA//ISO9796-1PADDING", "RSA/ISO9796-1");
        provider.addAlgorithm("KeyFactory.RSA", "co.org.bouncy.jcajce.provider.asymmetric.rsa.KeyFactorySpi");
        provider.addAlgorithm("KeyPairGenerator.RSA", "co.org.bouncy.jcajce.provider.asymmetric.rsa.KeyPairGeneratorSpi");
        AsymmetricKeyInfoConverter keyFact = new KeyFactorySpi();
        registerOid(provider, PKCSObjectIdentifiers.rsaEncryption, "RSA", keyFact);
        registerOid(provider, X509ObjectIdentifiers.id_ea_rsa, "RSA", keyFact);
        registerOid(provider, PKCSObjectIdentifiers.id_RSAES_OAEP, "RSA", keyFact);
        registerOid(provider, PKCSObjectIdentifiers.id_RSASSA_PSS, "RSA", keyFact);
        registerOidAlgorithmParameters(provider, PKCSObjectIdentifiers.rsaEncryption, "RSA");
        registerOidAlgorithmParameters(provider, X509ObjectIdentifiers.id_ea_rsa, "RSA");
        registerOidAlgorithmParameters(provider, PKCSObjectIdentifiers.id_RSAES_OAEP, "OAEP");
        registerOidAlgorithmParameters(provider, PKCSObjectIdentifiers.id_RSASSA_PSS, "PSS");
        provider.addAlgorithm("Signature.RSASSA-PSS", "co.org.bouncy.jcajce.provider.asymmetric.rsa.PSSSignatureSpi$PSSwithRSA");
        provider.addAlgorithm((new StringBuilder()).append("Signature.").append(PKCSObjectIdentifiers.id_RSASSA_PSS).toString(), "co.org.bouncy.jcajce.provider.asymmetric.rsa.PSSSignatureSpi$PSSwithRSA");
        provider.addAlgorithm((new StringBuilder()).append("Signature.OID.").append(PKCSObjectIdentifiers.id_RSASSA_PSS).toString(), "co.org.bouncy.jcajce.provider.asymmetric.rsa.PSSSignatureSpi$PSSwithRSA");
        provider.addAlgorithm("Signature.SHA224withRSA/PSS", "co.org.bouncy.jcajce.provider.asymmetric.rsa.PSSSignatureSpi$SHA224withRSA");
        provider.addAlgorithm("Signature.SHA256withRSA/PSS", "co.org.bouncy.jcajce.provider.asymmetric.rsa.PSSSignatureSpi$SHA256withRSA");
        provider.addAlgorithm("Signature.SHA384withRSA/PSS", "co.org.bouncy.jcajce.provider.asymmetric.rsa.PSSSignatureSpi$SHA384withRSA");
        provider.addAlgorithm("Signature.SHA512withRSA/PSS", "co.org.bouncy.jcajce.provider.asymmetric.rsa.PSSSignatureSpi$SHA512withRSA");
        provider.addAlgorithm("Signature.RSA", "co.org.bouncy.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$noneRSA");
        provider.addAlgorithm("Signature.RAWRSASSA-PSS", "co.org.bouncy.jcajce.provider.asymmetric.rsa.PSSSignatureSpi$nonePSS");
        provider.addAlgorithm("Alg.Alias.Signature.RAWRSA", "RSA");
        provider.addAlgorithm("Alg.Alias.Signature.NONEWITHRSA", "RSA");
        provider.addAlgorithm("Alg.Alias.Signature.RAWRSAPSS", "RAWRSASSA-PSS");
        provider.addAlgorithm("Alg.Alias.Signature.NONEWITHRSAPSS", "RAWRSASSA-PSS");
        provider.addAlgorithm("Alg.Alias.Signature.NONEWITHRSASSA-PSS", "RAWRSASSA-PSS");
        provider.addAlgorithm("Alg.Alias.Signature.NONEWITHRSAANDMGF1", "RAWRSASSA-PSS");
        provider.addAlgorithm("Alg.Alias.Signature.RSAPSS", "RSASSA-PSS");
        provider.addAlgorithm("Alg.Alias.Signature.SHA224withRSAandMGF1", "SHA224withRSA/PSS");
        provider.addAlgorithm("Alg.Alias.Signature.SHA256withRSAandMGF1", "SHA256withRSA/PSS");
        provider.addAlgorithm("Alg.Alias.Signature.SHA384withRSAandMGF1", "SHA384withRSA/PSS");
        provider.addAlgorithm("Alg.Alias.Signature.SHA512withRSAandMGF1", "SHA512withRSA/PSS");
        provider.addAlgorithm("Alg.Alias.Signature.SHA224WITHRSAANDMGF1", "SHA224withRSA/PSS");
        provider.addAlgorithm("Alg.Alias.Signature.SHA256WITHRSAANDMGF1", "SHA256withRSA/PSS");
        provider.addAlgorithm("Alg.Alias.Signature.SHA384WITHRSAANDMGF1", "SHA384withRSA/PSS");
        provider.addAlgorithm("Alg.Alias.Signature.SHA512WITHRSAANDMGF1", "SHA512withRSA/PSS");
        if(provider.hasAlgorithm("MessageDigest", "MD2"))
            addDigestSignature(provider, "MD2", "co.org.bouncy.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$MD2", PKCSObjectIdentifiers.md2WithRSAEncryption);
        if(provider.hasAlgorithm("MessageDigest", "MD4"))
            addDigestSignature(provider, "MD4", "co.org.bouncy.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$MD4", PKCSObjectIdentifiers.md4WithRSAEncryption);
        if(provider.hasAlgorithm("MessageDigest", "MD5"))
        {
            addDigestSignature(provider, "MD5", "co.org.bouncy.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$MD5", PKCSObjectIdentifiers.md5WithRSAEncryption);
            provider.addAlgorithm("Signature.MD5withRSA/ISO9796-2", "co.org.bouncy.jcajce.provider.asymmetric.rsa.ISOSignatureSpi$MD5WithRSAEncryption");
            provider.addAlgorithm("Alg.Alias.Signature.MD5WithRSA/ISO9796-2", "MD5withRSA/ISO9796-2");
        }
        if(provider.hasAlgorithm("MessageDigest", "SHA1"))
        {
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.SHA1withRSA/PSS", "PSS");
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.SHA1WITHRSAANDMGF1", "PSS");
            provider.addAlgorithm("Signature.SHA1withRSA/PSS", "co.org.bouncy.jcajce.provider.asymmetric.rsa.PSSSignatureSpi$SHA1withRSA");
            provider.addAlgorithm("Alg.Alias.Signature.SHA1withRSAandMGF1", "SHA1withRSA/PSS");
            provider.addAlgorithm("Alg.Alias.Signature.SHA1WITHRSAANDMGF1", "SHA1withRSA/PSS");
            addDigestSignature(provider, "SHA1", "co.org.bouncy.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$SHA1", PKCSObjectIdentifiers.sha1WithRSAEncryption);
            provider.addAlgorithm("Alg.Alias.Signature.SHA1WithRSA/ISO9796-2", "SHA1withRSA/ISO9796-2");
            provider.addAlgorithm("Signature.SHA1withRSA/ISO9796-2", "co.org.bouncy.jcajce.provider.asymmetric.rsa.ISOSignatureSpi$SHA1WithRSAEncryption");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Signature.").append(OIWObjectIdentifiers.sha1WithRSA).toString(), "SHA1WITHRSA");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Signature.OID.").append(OIWObjectIdentifiers.sha1WithRSA).toString(), "SHA1WITHRSA");
        }
        addDigestSignature(provider, "SHA224", "co.org.bouncy.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$SHA224", PKCSObjectIdentifiers.sha224WithRSAEncryption);
        addDigestSignature(provider, "SHA256", "co.org.bouncy.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$SHA256", PKCSObjectIdentifiers.sha256WithRSAEncryption);
        addDigestSignature(provider, "SHA384", "co.org.bouncy.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$SHA384", PKCSObjectIdentifiers.sha384WithRSAEncryption);
        addDigestSignature(provider, "SHA512", "co.org.bouncy.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$SHA512", PKCSObjectIdentifiers.sha512WithRSAEncryption);
        if(provider.hasAlgorithm("MessageDigest", "RIPEMD128"))
        {
            addDigestSignature(provider, "RIPEMD128", "co.org.bouncy.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$RIPEMD128", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd128);
            addDigestSignature(provider, "RMD128", "co.org.bouncy.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$RIPEMD128", null);
        }
        if(provider.hasAlgorithm("MessageDigest", "RIPEMD160"))
        {
            addDigestSignature(provider, "RIPEMD160", "co.org.bouncy.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$RIPEMD160", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd160);
            addDigestSignature(provider, "RMD160", "co.org.bouncy.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$RIPEMD160", null);
            provider.addAlgorithm("Alg.Alias.Signature.RIPEMD160WithRSA/ISO9796-2", "RIPEMD160withRSA/ISO9796-2");
            provider.addAlgorithm("Signature.RIPEMD160withRSA/ISO9796-2", "co.org.bouncy.jcajce.provider.asymmetric.rsa.ISOSignatureSpi$RIPEMD160WithRSAEncryption");
        }
        if(provider.hasAlgorithm("MessageDigest", "RIPEMD256"))
        {
            addDigestSignature(provider, "RIPEMD256", "co.org.bouncy.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$RIPEMD256", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd256);
            addDigestSignature(provider, "RMD256", "co.org.bouncy.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$RIPEMD256", null);
        }
    }

    private void addDigestSignature(ConfigurableProvider provider, String digest, String className, ASN1ObjectIdentifier oid)
    {
        String mainName = (new StringBuilder()).append(digest).append("WITHRSA").toString();
        String jdk11Variation1 = (new StringBuilder()).append(digest).append("withRSA").toString();
        String jdk11Variation2 = (new StringBuilder()).append(digest).append("WithRSA").toString();
        String alias = (new StringBuilder()).append(digest).append("/").append("RSA").toString();
        String longName = (new StringBuilder()).append(digest).append("WITHRSAENCRYPTION").toString();
        String longJdk11Variation1 = (new StringBuilder()).append(digest).append("withRSAEncryption").toString();
        String longJdk11Variation2 = (new StringBuilder()).append(digest).append("WithRSAEncryption").toString();
        provider.addAlgorithm((new StringBuilder()).append("Signature.").append(mainName).toString(), className);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Signature.").append(jdk11Variation1).toString(), mainName);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Signature.").append(jdk11Variation2).toString(), mainName);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Signature.").append(longName).toString(), mainName);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Signature.").append(longJdk11Variation1).toString(), mainName);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Signature.").append(longJdk11Variation2).toString(), mainName);
        provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Signature.").append(alias).toString(), mainName);
        if(oid != null)
        {
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Signature.").append(oid).toString(), mainName);
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Signature.OID.").append(oid).toString(), mainName);
        }
    }

    public RSA$Mappings()
    {
    }
}
