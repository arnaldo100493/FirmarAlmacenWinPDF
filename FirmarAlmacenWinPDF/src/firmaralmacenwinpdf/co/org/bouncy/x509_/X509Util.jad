// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509Util.java

package co.org.bouncy.x509_;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cryptopro.CryptoProObjectIdentifiers;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.RSASSAPSSparams;
import co.org.bouncy.asn1.teletrust.TeleTrusTObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.jce.X509Principal;
import co.org.bouncy.util.Strings;
import java.io.IOException;
import java.security.*;
import java.util.*;
import javax.security.auth.x500.X500Principal;

class X509Util
{
    static class Implementation
    {

        Object getEngine()
        {
            return engine;
        }

        Provider getProvider()
        {
            return provider;
        }

        Object engine;
        Provider provider;

        Implementation(Object engine, Provider provider)
        {
            this.engine = engine;
            this.provider = provider;
        }
    }


    X509Util()
    {
    }

    private static RSASSAPSSparams creatPSSParams(AlgorithmIdentifier hashAlgId, int saltSize)
    {
        return new RSASSAPSSparams(hashAlgId, new AlgorithmIdentifier(PKCSObjectIdentifiers.id_mgf1, hashAlgId), new ASN1Integer(saltSize), new ASN1Integer(1L));
    }

    static DERObjectIdentifier getAlgorithmOID(String algorithmName)
    {
        algorithmName = Strings.toUpperCase(algorithmName);
        if(algorithms.containsKey(algorithmName))
            return (DERObjectIdentifier)algorithms.get(algorithmName);
        else
            return new DERObjectIdentifier(algorithmName);
    }

    static AlgorithmIdentifier getSigAlgID(DERObjectIdentifier sigOid, String algorithmName)
    {
        if(noParams.contains(sigOid))
            return new AlgorithmIdentifier(sigOid);
        algorithmName = Strings.toUpperCase(algorithmName);
        if(params.containsKey(algorithmName))
            return new AlgorithmIdentifier(sigOid, (ASN1Encodable)params.get(algorithmName));
        else
            return new AlgorithmIdentifier(sigOid, DERNull.INSTANCE);
    }

    static Iterator getAlgNames()
    {
        Enumeration e = algorithms.keys();
        List l = new ArrayList();
        for(; e.hasMoreElements(); l.add(e.nextElement()));
        return l.iterator();
    }

    static Signature getSignatureInstance(String algorithm)
        throws NoSuchAlgorithmException
    {
        return Signature.getInstance(algorithm);
    }

    static Signature getSignatureInstance(String algorithm, String provider)
        throws NoSuchProviderException, NoSuchAlgorithmException
    {
        if(provider != null)
            return Signature.getInstance(algorithm, provider);
        else
            return Signature.getInstance(algorithm);
    }

    static byte[] calculateSignature(DERObjectIdentifier sigOid, String sigName, PrivateKey key, SecureRandom random, ASN1Encodable object)
        throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException
    {
        if(sigOid == null)
            throw new IllegalStateException("no signature algorithm specified");
        Signature sig = getSignatureInstance(sigName);
        if(random != null)
            sig.initSign(key, random);
        else
            sig.initSign(key);
        sig.update(object.toASN1Primitive().getEncoded("DER"));
        return sig.sign();
    }

    static byte[] calculateSignature(DERObjectIdentifier sigOid, String sigName, String provider, PrivateKey key, SecureRandom random, ASN1Encodable object)
        throws IOException, NoSuchProviderException, NoSuchAlgorithmException, InvalidKeyException, SignatureException
    {
        if(sigOid == null)
            throw new IllegalStateException("no signature algorithm specified");
        Signature sig = getSignatureInstance(sigName, provider);
        if(random != null)
            sig.initSign(key, random);
        else
            sig.initSign(key);
        sig.update(object.toASN1Primitive().getEncoded("DER"));
        return sig.sign();
    }

    static X509Principal convertPrincipal(X500Principal principal)
    {
        try
        {
            return new X509Principal(principal.getEncoded());
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException("cannot convert principal");
        }
    }

    static Implementation getImplementation(String baseName, String algorithm, Provider prov)
        throws NoSuchAlgorithmException
    {
        String alias;
        for(algorithm = Strings.toUpperCase(algorithm); (alias = prov.getProperty((new StringBuilder()).append("Alg.Alias.").append(baseName).append(".").append(algorithm).toString())) != null; algorithm = alias);
        String className = prov.getProperty((new StringBuilder()).append(baseName).append(".").append(algorithm).toString());
        if(className != null)
            try
            {
                ClassLoader clsLoader = prov.getClass().getClassLoader();
                Class cls;
                if(clsLoader != null)
                    cls = clsLoader.loadClass(className);
                else
                    cls = Class.forName(className);
                return new Implementation(cls.newInstance(), prov);
            }
            catch(ClassNotFoundException e)
            {
                throw new IllegalStateException((new StringBuilder()).append("algorithm ").append(algorithm).append(" in provider ").append(prov.getName()).append(" but no class \"").append(className).append("\" found!").toString());
            }
            catch(Exception e)
            {
                throw new IllegalStateException((new StringBuilder()).append("algorithm ").append(algorithm).append(" in provider ").append(prov.getName()).append(" but class \"").append(className).append("\" inaccessible!").toString());
            }
        else
            throw new NoSuchAlgorithmException((new StringBuilder()).append("cannot find implementation ").append(algorithm).append(" for provider ").append(prov.getName()).toString());
    }

    static Implementation getImplementation(String baseName, String algorithm)
        throws NoSuchAlgorithmException
    {
        Provider prov[] = Security.getProviders();
        for(int i = 0; i != prov.length; i++)
        {
            Implementation imp = getImplementation(baseName, Strings.toUpperCase(algorithm), prov[i]);
            if(imp != null)
                return imp;
            try
            {
                imp = getImplementation(baseName, algorithm, prov[i]);
            }
            catch(NoSuchAlgorithmException e) { }
        }

        throw new NoSuchAlgorithmException((new StringBuilder()).append("cannot find implementation ").append(algorithm).toString());
    }

    static Provider getProvider(String provider)
        throws NoSuchProviderException
    {
        Provider prov = Security.getProvider(provider);
        if(prov == null)
            throw new NoSuchProviderException((new StringBuilder()).append("Provider ").append(provider).append(" not found").toString());
        else
            return prov;
    }

    private static Hashtable algorithms;
    private static Hashtable params;
    private static Set noParams;

    static 
    {
        algorithms = new Hashtable();
        params = new Hashtable();
        noParams = new HashSet();
        algorithms.put("MD2WITHRSAENCRYPTION", PKCSObjectIdentifiers.md2WithRSAEncryption);
        algorithms.put("MD2WITHRSA", PKCSObjectIdentifiers.md2WithRSAEncryption);
        algorithms.put("MD5WITHRSAENCRYPTION", PKCSObjectIdentifiers.md5WithRSAEncryption);
        algorithms.put("MD5WITHRSA", PKCSObjectIdentifiers.md5WithRSAEncryption);
        algorithms.put("SHA1WITHRSAENCRYPTION", PKCSObjectIdentifiers.sha1WithRSAEncryption);
        algorithms.put("SHA1WITHRSA", PKCSObjectIdentifiers.sha1WithRSAEncryption);
        algorithms.put("SHA224WITHRSAENCRYPTION", PKCSObjectIdentifiers.sha224WithRSAEncryption);
        algorithms.put("SHA224WITHRSA", PKCSObjectIdentifiers.sha224WithRSAEncryption);
        algorithms.put("SHA256WITHRSAENCRYPTION", PKCSObjectIdentifiers.sha256WithRSAEncryption);
        algorithms.put("SHA256WITHRSA", PKCSObjectIdentifiers.sha256WithRSAEncryption);
        algorithms.put("SHA384WITHRSAENCRYPTION", PKCSObjectIdentifiers.sha384WithRSAEncryption);
        algorithms.put("SHA384WITHRSA", PKCSObjectIdentifiers.sha384WithRSAEncryption);
        algorithms.put("SHA512WITHRSAENCRYPTION", PKCSObjectIdentifiers.sha512WithRSAEncryption);
        algorithms.put("SHA512WITHRSA", PKCSObjectIdentifiers.sha512WithRSAEncryption);
        algorithms.put("SHA1WITHRSAANDMGF1", PKCSObjectIdentifiers.id_RSASSA_PSS);
        algorithms.put("SHA224WITHRSAANDMGF1", PKCSObjectIdentifiers.id_RSASSA_PSS);
        algorithms.put("SHA256WITHRSAANDMGF1", PKCSObjectIdentifiers.id_RSASSA_PSS);
        algorithms.put("SHA384WITHRSAANDMGF1", PKCSObjectIdentifiers.id_RSASSA_PSS);
        algorithms.put("SHA512WITHRSAANDMGF1", PKCSObjectIdentifiers.id_RSASSA_PSS);
        algorithms.put("RIPEMD160WITHRSAENCRYPTION", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd160);
        algorithms.put("RIPEMD160WITHRSA", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd160);
        algorithms.put("RIPEMD128WITHRSAENCRYPTION", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd128);
        algorithms.put("RIPEMD128WITHRSA", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd128);
        algorithms.put("RIPEMD256WITHRSAENCRYPTION", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd256);
        algorithms.put("RIPEMD256WITHRSA", TeleTrusTObjectIdentifiers.rsaSignatureWithripemd256);
        algorithms.put("SHA1WITHDSA", X9ObjectIdentifiers.id_dsa_with_sha1);
        algorithms.put("DSAWITHSHA1", X9ObjectIdentifiers.id_dsa_with_sha1);
        algorithms.put("SHA224WITHDSA", NISTObjectIdentifiers.dsa_with_sha224);
        algorithms.put("SHA256WITHDSA", NISTObjectIdentifiers.dsa_with_sha256);
        algorithms.put("SHA384WITHDSA", NISTObjectIdentifiers.dsa_with_sha384);
        algorithms.put("SHA512WITHDSA", NISTObjectIdentifiers.dsa_with_sha512);
        algorithms.put("SHA1WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA1);
        algorithms.put("ECDSAWITHSHA1", X9ObjectIdentifiers.ecdsa_with_SHA1);
        algorithms.put("SHA224WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA224);
        algorithms.put("SHA256WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA256);
        algorithms.put("SHA384WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA384);
        algorithms.put("SHA512WITHECDSA", X9ObjectIdentifiers.ecdsa_with_SHA512);
        algorithms.put("GOST3411WITHGOST3410", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94);
        algorithms.put("GOST3411WITHGOST3410-94", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94);
        algorithms.put("GOST3411WITHECGOST3410", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001);
        algorithms.put("GOST3411WITHECGOST3410-2001", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001);
        algorithms.put("GOST3411WITHGOST3410-2001", CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA1);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA224);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA256);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA384);
        noParams.add(X9ObjectIdentifiers.ecdsa_with_SHA512);
        noParams.add(X9ObjectIdentifiers.id_dsa_with_sha1);
        noParams.add(NISTObjectIdentifiers.dsa_with_sha224);
        noParams.add(NISTObjectIdentifiers.dsa_with_sha256);
        noParams.add(NISTObjectIdentifiers.dsa_with_sha384);
        noParams.add(NISTObjectIdentifiers.dsa_with_sha512);
        noParams.add(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94);
        noParams.add(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001);
        AlgorithmIdentifier sha1AlgId = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
        params.put("SHA1WITHRSAANDMGF1", creatPSSParams(sha1AlgId, 20));
        AlgorithmIdentifier sha224AlgId = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha224, DERNull.INSTANCE);
        params.put("SHA224WITHRSAANDMGF1", creatPSSParams(sha224AlgId, 28));
        AlgorithmIdentifier sha256AlgId = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256, DERNull.INSTANCE);
        params.put("SHA256WITHRSAANDMGF1", creatPSSParams(sha256AlgId, 32));
        AlgorithmIdentifier sha384AlgId = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha384, DERNull.INSTANCE);
        params.put("SHA384WITHRSAANDMGF1", creatPSSParams(sha384AlgId, 48));
        AlgorithmIdentifier sha512AlgId = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512, DERNull.INSTANCE);
        params.put("SHA512WITHRSAANDMGF1", creatPSSParams(sha512AlgId, 64));
    }
}
