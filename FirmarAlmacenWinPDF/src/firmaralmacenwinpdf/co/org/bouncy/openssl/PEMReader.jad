// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMReader.java

package co.org.bouncy.openssl;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.ContentInfo;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.sec.ECPrivateKey;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import co.org.bouncy.crypto.PBEParametersGenerator;
import co.org.bouncy.crypto.generators.OpenSSLPBEParametersGenerator;
import co.org.bouncy.crypto.generators.PKCS5S2ParametersGenerator;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.jce.ECNamedCurveTable;
import co.org.bouncy.jce.PKCS10CertificationRequest;
import co.org.bouncy.util.encoders.Hex;
import co.org.bouncy.util.io.pem.*;
import co.org.bouncy.x509_.X509V2AttributeCertificate;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.spec.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;

// Referenced classes of package co.org.bouncy.openssl:
//            EncryptionException, PEMUtilities, PasswordFinder, PEMException, 
//            PasswordException

/**
 * @deprecated Class PEMReader is deprecated
 */

public class PEMReader extends PemReader
{
    private class PrivateKeyParser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            try
            {
                PrivateKeyInfo info = PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(obj.getContent()));
                PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(obj.getContent());
                KeyFactory keyFact = KeyFactory.getInstance(info.getPrivateKeyAlgorithm().getAlgorithm().getId(), provider);
                return keyFact.generatePrivate(keySpec);
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("problem parsing PRIVATE KEY: ").append(e.toString()).toString(), e);
            }
        }

        private String provider;
        final PEMReader this$0;

        public PrivateKeyParser(String provider)
        {
            this$0 = PEMReader.this;
            super();
            this.provider = provider;
        }
    }

    private class EncryptedPrivateKeyParser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            EncryptedPrivateKeyInfo info;
            AlgorithmIdentifier algId;
            try
            {
                info = EncryptedPrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(obj.getContent()));
                algId = info.getEncryptionAlgorithm();
                if(pFinder == null)
                    throw new PEMException("no PasswordFinder specified");
                if(PEMUtilities.isPKCS5Scheme2(algId.getAlgorithm()))
                {
                    PBES2Parameters params = PBES2Parameters.getInstance(algId.getParameters());
                    KeyDerivationFunc func = params.getKeyDerivationFunc();
                    EncryptionScheme scheme = params.getEncryptionScheme();
                    PBKDF2Params defParams = (PBKDF2Params)func.getParameters();
                    int iterationCount = defParams.getIterationCount().intValue();
                    byte salt[] = defParams.getSalt();
                    String algorithm = scheme.getAlgorithm().getId();
                    SecretKey key = PEMReader.generateSecretKeyForPKCS5Scheme2(algorithm, pFinder.getPassword(), salt, iterationCount);
                    Cipher cipher = Cipher.getInstance(algorithm, symProvider);
                    AlgorithmParameters algParams = AlgorithmParameters.getInstance(algorithm, symProvider);
                    algParams.init(scheme.getParameters().toASN1Primitive().getEncoded());
                    cipher.init(2, key, algParams);
                    PrivateKeyInfo pInfo = PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(cipher.doFinal(info.getEncryptedData())));
                    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pInfo.getEncoded());
                    KeyFactory keyFact = KeyFactory.getInstance(pInfo.getPrivateKeyAlgorithm().getAlgorithm().getId(), asymProvider);
                    return keyFact.generatePrivate(keySpec);
                }
            }
            catch(IOException e)
            {
                throw e;
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("problem parsing ENCRYPTED PRIVATE KEY: ").append(e.toString()).toString(), e);
            }
            if(PEMUtilities.isPKCS12(algId.getAlgorithm()))
            {
                PKCS12PBEParams params = PKCS12PBEParams.getInstance(algId.getParameters());
                String algorithm = algId.getAlgorithm().getId();
                PBEKeySpec pbeSpec = new PBEKeySpec(pFinder.getPassword());
                SecretKeyFactory secKeyFact = SecretKeyFactory.getInstance(algorithm, symProvider);
                PBEParameterSpec defParams = new PBEParameterSpec(params.getIV(), params.getIterations().intValue());
                Cipher cipher = Cipher.getInstance(algorithm, symProvider);
                cipher.init(2, secKeyFact.generateSecret(pbeSpec), defParams);
                PrivateKeyInfo pInfo = PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(cipher.doFinal(info.getEncryptedData())));
                PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pInfo.getEncoded());
                KeyFactory keyFact = KeyFactory.getInstance(pInfo.getPrivateKeyAlgorithm().getAlgorithm().getId(), asymProvider);
                return keyFact.generatePrivate(keySpec);
            }
            if(PEMUtilities.isPKCS5Scheme1(algId.getAlgorithm()))
            {
                PBEParameter params = PBEParameter.getInstance(algId.getParameters());
                String algorithm = algId.getAlgorithm().getId();
                PBEKeySpec pbeSpec = new PBEKeySpec(pFinder.getPassword());
                SecretKeyFactory secKeyFact = SecretKeyFactory.getInstance(algorithm, symProvider);
                PBEParameterSpec defParams = new PBEParameterSpec(params.getSalt(), params.getIterationCount().intValue());
                Cipher cipher = Cipher.getInstance(algorithm, symProvider);
                cipher.init(2, secKeyFact.generateSecret(pbeSpec), defParams);
                PrivateKeyInfo pInfo = PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(cipher.doFinal(info.getEncryptedData())));
                PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pInfo.getEncoded());
                KeyFactory keyFact = KeyFactory.getInstance(pInfo.getPrivateKeyAlgorithm().getAlgorithm().getId(), asymProvider);
                return keyFact.generatePrivate(keySpec);
            }
            throw new PEMException((new StringBuilder()).append("Unknown algorithm: ").append(algId.getAlgorithm()).toString());
        }

        private String symProvider;
        private String asymProvider;
        final PEMReader this$0;

        public EncryptedPrivateKeyParser(String symProvider, String asymProvider)
        {
            this$0 = PEMReader.this;
            super();
            this.symProvider = symProvider;
            this.asymProvider = asymProvider;
        }
    }

    private class ECNamedCurveSpecParser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            try
            {
                DERObjectIdentifier oid = (DERObjectIdentifier)ASN1Primitive.fromByteArray(obj.getContent());
                Object params = ECNamedCurveTable.getParameterSpec(oid.getId());
                if(params == null)
                    throw new IOException("object ID not found in EC curve table");
                else
                    return params;
            }
            catch(IOException e)
            {
                throw e;
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("exception extracting EC named curve: ").append(e.toString()).toString());
            }
        }

        final PEMReader this$0;

        private ECNamedCurveSpecParser()
        {
            this$0 = PEMReader.this;
            super();
        }

    }

    private class X509AttributeCertificateParser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            return new X509V2AttributeCertificate(obj.getContent());
        }

        final PEMReader this$0;

        private X509AttributeCertificateParser()
        {
            this$0 = PEMReader.this;
            super();
        }

    }

    private class PKCS7Parser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            try
            {
                ASN1InputStream aIn = new ASN1InputStream(obj.getContent());
                return ContentInfo.getInstance(aIn.readObject());
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("problem parsing PKCS7 object: ").append(e.toString()).toString(), e);
            }
        }

        final PEMReader this$0;

        private PKCS7Parser()
        {
            this$0 = PEMReader.this;
            super();
        }

    }

    private class PKCS10CertificationRequestParser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            try
            {
                return new PKCS10CertificationRequest(obj.getContent());
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("problem parsing certrequest: ").append(e.toString()).toString(), e);
            }
        }

        final PEMReader this$0;

        private PKCS10CertificationRequestParser()
        {
            this$0 = PEMReader.this;
            super();
        }

    }

    private class X509CRLParser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            ByteArrayInputStream bIn = new ByteArrayInputStream(obj.getContent());
            try
            {
                CertificateFactory certFact = CertificateFactory.getInstance("X.509", provider);
                return certFact.generateCRL(bIn);
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("problem parsing cert: ").append(e.toString()).toString(), e);
            }
        }

        private String provider;
        final PEMReader this$0;

        public X509CRLParser(String provider)
        {
            this$0 = PEMReader.this;
            super();
            this.provider = provider;
        }
    }

    private class X509CertificateParser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            ByteArrayInputStream bIn = new ByteArrayInputStream(obj.getContent());
            try
            {
                CertificateFactory certFact = CertificateFactory.getInstance("X.509", provider);
                return certFact.generateCertificate(bIn);
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("problem parsing cert: ").append(e.toString()).toString(), e);
            }
        }

        private String provider;
        final PEMReader this$0;

        public X509CertificateParser(String provider)
        {
            this$0 = PEMReader.this;
            super();
            this.provider = provider;
        }
    }

    private class RSAPublicKeyParser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            try
            {
                ASN1InputStream ais = new ASN1InputStream(obj.getContent());
                Object asnObject = ais.readObject();
                ASN1Sequence sequence = (ASN1Sequence)asnObject;
                RSAPublicKey rsaPubStructure = RSAPublicKey.getInstance(sequence);
                RSAPublicKeySpec keySpec = new RSAPublicKeySpec(rsaPubStructure.getModulus(), rsaPubStructure.getPublicExponent());
                KeyFactory keyFact = KeyFactory.getInstance("RSA", provider);
                return keyFact.generatePublic(keySpec);
            }
            catch(IOException e)
            {
                throw e;
            }
            catch(NoSuchProviderException e)
            {
                throw new IOException((new StringBuilder()).append("can't find provider ").append(provider).toString());
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("problem extracting key: ").append(e.toString()).toString(), e);
            }
        }

        private String provider;
        final PEMReader this$0;

        public RSAPublicKeyParser(String provider)
        {
            this$0 = PEMReader.this;
            super();
            this.provider = provider;
        }
    }

    private class PublicKeyParser
        implements PemObjectParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            KeySpec keySpec = new X509EncodedKeySpec(obj.getContent());
            String algorithms[] = {
                "DSA", "RSA"
            };
            for(int i = 0; i < algorithms.length; i++)
                try
                {
                    KeyFactory keyFact = KeyFactory.getInstance(algorithms[i], provider);
                    java.security.PublicKey pubKey = keyFact.generatePublic(keySpec);
                    return pubKey;
                }
                catch(NoSuchAlgorithmException e) { }
                catch(InvalidKeySpecException e) { }
                catch(NoSuchProviderException e)
                {
                    throw new RuntimeException((new StringBuilder()).append("can't find provider ").append(provider).toString());
                }

            return null;
        }

        private String provider;
        final PEMReader this$0;

        public PublicKeyParser(String provider)
        {
            this$0 = PEMReader.this;
            super();
            this.provider = provider;
        }
    }

    private class RSAKeyPairParser extends KeyPairParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            try
            {
                ASN1Sequence seq = readKeyPair(obj);
                if(seq.size() != 9)
                {
                    throw new PEMException("malformed sequence in RSA private key");
                } else
                {
                    RSAPrivateKey keyStruct = RSAPrivateKey.getInstance(seq);
                    RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(keyStruct.getModulus(), keyStruct.getPublicExponent());
                    RSAPrivateCrtKeySpec privSpec = new RSAPrivateCrtKeySpec(keyStruct.getModulus(), keyStruct.getPublicExponent(), keyStruct.getPrivateExponent(), keyStruct.getPrime1(), keyStruct.getPrime2(), keyStruct.getExponent1(), keyStruct.getExponent2(), keyStruct.getCoefficient());
                    KeyFactory fact = KeyFactory.getInstance("RSA", asymProvider);
                    return new KeyPair(fact.generatePublic(pubSpec), fact.generatePrivate(privSpec));
                }
            }
            catch(IOException e)
            {
                throw e;
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("problem creating RSA private key: ").append(e.toString()).toString(), e);
            }
        }

        private String asymProvider;
        final PEMReader this$0;

        public RSAKeyPairParser(String symProvider, String asymProvider)
        {
            this$0 = PEMReader.this;
            super(symProvider);
            this.asymProvider = asymProvider;
        }
    }

    private class ECDSAKeyPairParser extends KeyPairParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            try
            {
                ASN1Sequence seq = readKeyPair(obj);
                ECPrivateKey pKey = ECPrivateKey.getInstance(seq);
                AlgorithmIdentifier algId = new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, pKey.getParameters());
                PrivateKeyInfo privInfo = new PrivateKeyInfo(algId, pKey);
                SubjectPublicKeyInfo pubInfo = new SubjectPublicKeyInfo(algId, pKey.getPublicKey().getBytes());
                PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(privInfo.getEncoded());
                X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(pubInfo.getEncoded());
                KeyFactory fact = KeyFactory.getInstance("ECDSA", asymProvider);
                return new KeyPair(fact.generatePublic(pubSpec), fact.generatePrivate(privSpec));
            }
            catch(IOException e)
            {
                throw e;
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("problem creating EC private key: ").append(e.toString()).toString(), e);
            }
        }

        private String asymProvider;
        final PEMReader this$0;

        public ECDSAKeyPairParser(String symProvider, String asymProvider)
        {
            this$0 = PEMReader.this;
            super(symProvider);
            this.asymProvider = asymProvider;
        }
    }

    private class DSAKeyPairParser extends KeyPairParser
    {

        public Object parseObject(PemObject obj)
            throws IOException
        {
            try
            {
                ASN1Sequence seq = readKeyPair(obj);
                if(seq.size() != 6)
                {
                    throw new PEMException("malformed sequence in DSA private key");
                } else
                {
                    DERInteger p = (DERInteger)seq.getObjectAt(1);
                    DERInteger q = (DERInteger)seq.getObjectAt(2);
                    DERInteger g = (DERInteger)seq.getObjectAt(3);
                    DERInteger y = (DERInteger)seq.getObjectAt(4);
                    DERInteger x = (DERInteger)seq.getObjectAt(5);
                    DSAPrivateKeySpec privSpec = new DSAPrivateKeySpec(x.getValue(), p.getValue(), q.getValue(), g.getValue());
                    DSAPublicKeySpec pubSpec = new DSAPublicKeySpec(y.getValue(), p.getValue(), q.getValue(), g.getValue());
                    KeyFactory fact = KeyFactory.getInstance("DSA", asymProvider);
                    return new KeyPair(fact.generatePublic(pubSpec), fact.generatePrivate(privSpec));
                }
            }
            catch(IOException e)
            {
                throw e;
            }
            catch(Exception e)
            {
                throw new PEMException((new StringBuilder()).append("problem creating DSA private key: ").append(e.toString()).toString(), e);
            }
        }

        private String asymProvider;
        final PEMReader this$0;

        public DSAKeyPairParser(String symProvider, String asymProvider)
        {
            this$0 = PEMReader.this;
            super(symProvider);
            this.asymProvider = asymProvider;
        }
    }

    private abstract class KeyPairParser
        implements PemObjectParser
    {

        protected ASN1Sequence readKeyPair(PemObject obj)
            throws IOException
        {
            boolean isEncrypted = false;
            String dekInfo = null;
            List headers = obj.getHeaders();
            Iterator it = headers.iterator();
            do
            {
                if(!it.hasNext())
                    break;
                PemHeader hdr = (PemHeader)it.next();
                if(hdr.getName().equals("Proc-Type") && hdr.getValue().equals("4,ENCRYPTED"))
                    isEncrypted = true;
                else
                if(hdr.getName().equals("DEK-Info"))
                    dekInfo = hdr.getValue();
            } while(true);
            byte keyBytes[] = obj.getContent();
            if(isEncrypted)
            {
                if(pFinder == null)
                    throw new PasswordException("No password finder specified, but a password is required");
                char password[] = pFinder.getPassword();
                if(password == null)
                    throw new PasswordException("Password is null, but a password is required");
                StringTokenizer tknz = new StringTokenizer(dekInfo, ",");
                String dekAlgName = tknz.nextToken();
                byte iv[] = Hex.decode(tknz.nextToken());
                keyBytes = PEMReader.crypt(false, symProvider, keyBytes, password, dekAlgName, iv);
            }
            try
            {
                return ASN1Sequence.getInstance(ASN1Primitive.fromByteArray(keyBytes));
            }
            catch(IOException e)
            {
                if(isEncrypted)
                    throw new PEMException("exception decoding - please check password and data.", e);
                else
                    throw new PEMException(e.getMessage(), e);
            }
            catch(IllegalArgumentException e)
            {
                if(isEncrypted)
                    throw new PEMException("exception decoding - please check password and data.", e);
                else
                    throw new PEMException(e.getMessage(), e);
            }
        }

        protected String symProvider;
        final PEMReader this$0;

        public KeyPairParser(String symProvider)
        {
            this$0 = PEMReader.this;
            super();
            this.symProvider = symProvider;
        }
    }


    /**
     * @deprecated Method PEMReader is deprecated
     */

    public PEMReader(Reader reader)
    {
        this(reader, null, "BC");
    }

    /**
     * @deprecated Method PEMReader is deprecated
     */

    public PEMReader(Reader reader, PasswordFinder pFinder)
    {
        this(reader, pFinder, "BC");
    }

    /**
     * @deprecated Method PEMReader is deprecated
     */

    public PEMReader(Reader reader, PasswordFinder pFinder, String provider)
    {
        this(reader, pFinder, provider, provider);
    }

    /**
     * @deprecated Method PEMReader is deprecated
     */

    public PEMReader(Reader reader, PasswordFinder pFinder, String symProvider, String asymProvider)
    {
        super(reader);
        parsers = new HashMap();
        this.pFinder = pFinder;
        parsers.put("CERTIFICATE REQUEST", new PKCS10CertificationRequestParser());
        parsers.put("NEW CERTIFICATE REQUEST", new PKCS10CertificationRequestParser());
        parsers.put("CERTIFICATE", new X509CertificateParser(asymProvider));
        parsers.put("X509 CERTIFICATE", new X509CertificateParser(asymProvider));
        parsers.put("X509 CRL", new X509CRLParser(asymProvider));
        parsers.put("PKCS7", new PKCS7Parser());
        parsers.put("ATTRIBUTE CERTIFICATE", new X509AttributeCertificateParser());
        parsers.put("EC PARAMETERS", new ECNamedCurveSpecParser());
        parsers.put("PUBLIC KEY", new PublicKeyParser(asymProvider));
        parsers.put("RSA PUBLIC KEY", new RSAPublicKeyParser(asymProvider));
        parsers.put("RSA PRIVATE KEY", new RSAKeyPairParser(symProvider, asymProvider));
        parsers.put("DSA PRIVATE KEY", new DSAKeyPairParser(symProvider, asymProvider));
        parsers.put("EC PRIVATE KEY", new ECDSAKeyPairParser(symProvider, asymProvider));
        parsers.put("ENCRYPTED PRIVATE KEY", new EncryptedPrivateKeyParser(symProvider, asymProvider));
        parsers.put("PRIVATE KEY", new PrivateKeyParser(asymProvider));
    }

    public Object readObject()
        throws IOException
    {
        PemObject obj = readPemObject();
        if(obj != null)
        {
            String type = obj.getType();
            if(parsers.containsKey(type))
                return ((PemObjectParser)parsers.get(type)).parseObject(obj);
            else
                throw new IOException((new StringBuilder()).append("unrecognised object: ").append(type).toString());
        } else
        {
            return null;
        }
    }

    static byte[] crypt(boolean encrypt, String provider, byte bytes[], char password[], String dekAlgName, byte iv[])
        throws IOException
    {
        Provider prov = null;
        if(provider != null)
        {
            prov = Security.getProvider(provider);
            if(prov == null)
                throw new EncryptionException((new StringBuilder()).append("cannot find provider: ").append(provider).toString());
        }
        return crypt(encrypt, prov, bytes, password, dekAlgName, iv);
    }

    static byte[] crypt(boolean encrypt, Provider provider, byte bytes[], char password[], String dekAlgName, byte iv[])
        throws IOException
    {
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
        String blockMode = "CBC";
        String padding = "PKCS5Padding";
        if(dekAlgName.endsWith("-CFB"))
        {
            blockMode = "CFB";
            padding = "NoPadding";
        }
        if(dekAlgName.endsWith("-ECB") || "DES-EDE".equals(dekAlgName) || "DES-EDE3".equals(dekAlgName))
        {
            blockMode = "ECB";
            paramSpec = null;
        }
        if(dekAlgName.endsWith("-OFB"))
        {
            blockMode = "OFB";
            padding = "NoPadding";
        }
        String alg;
        Key sKey;
        if(dekAlgName.startsWith("DES-EDE"))
        {
            alg = "DESede";
            boolean des2 = !dekAlgName.startsWith("DES-EDE3");
            sKey = getKey(password, alg, 24, iv, des2);
        } else
        if(dekAlgName.startsWith("DES-"))
        {
            alg = "DES";
            sKey = getKey(password, alg, 8, iv);
        } else
        if(dekAlgName.startsWith("BF-"))
        {
            alg = "Blowfish";
            sKey = getKey(password, alg, 16, iv);
        } else
        if(dekAlgName.startsWith("RC2-"))
        {
            alg = "RC2";
            int keyBits = 128;
            if(dekAlgName.startsWith("RC2-40-"))
                keyBits = 40;
            else
            if(dekAlgName.startsWith("RC2-64-"))
                keyBits = 64;
            sKey = getKey(password, alg, keyBits / 8, iv);
            if(paramSpec == null)
                paramSpec = new RC2ParameterSpec(keyBits);
            else
                paramSpec = new RC2ParameterSpec(keyBits, iv);
        } else
        if(dekAlgName.startsWith("AES-"))
        {
            alg = "AES";
            byte salt[] = iv;
            if(salt.length > 8)
            {
                salt = new byte[8];
                System.arraycopy(iv, 0, salt, 0, 8);
            }
            int keyBits;
            if(dekAlgName.startsWith("AES-128-"))
                keyBits = 128;
            else
            if(dekAlgName.startsWith("AES-192-"))
                keyBits = 192;
            else
            if(dekAlgName.startsWith("AES-256-"))
                keyBits = 256;
            else
                throw new EncryptionException("unknown AES encryption with private key");
            sKey = getKey(password, "AES", keyBits / 8, salt);
        } else
        {
            throw new EncryptionException("unknown encryption with private key");
        }
        String transformation = (new StringBuilder()).append(alg).append("/").append(blockMode).append("/").append(padding).toString();
        try
        {
            Cipher c = Cipher.getInstance(transformation, provider);
            int mode = encrypt ? 1 : 2;
            if(paramSpec == null)
                c.init(mode, sKey);
            else
                c.init(mode, sKey, paramSpec);
            return c.doFinal(bytes);
        }
        catch(Exception e)
        {
            throw new EncryptionException("exception using cipher - please check password and data.", e);
        }
    }

    private static SecretKey getKey(char password[], String algorithm, int keyLength, byte salt[])
    {
        return getKey(password, algorithm, keyLength, salt, false);
    }

    private static SecretKey getKey(char password[], String algorithm, int keyLength, byte salt[], boolean des2)
    {
        OpenSSLPBEParametersGenerator pGen = new OpenSSLPBEParametersGenerator();
        pGen.init(PBEParametersGenerator.PKCS5PasswordToBytes(password), salt);
        KeyParameter keyParam = (KeyParameter)pGen.generateDerivedParameters(keyLength * 8);
        byte key[] = keyParam.getKey();
        if(des2 && key.length >= 24)
            System.arraycopy(key, 0, key, 16, 8);
        return new SecretKeySpec(key, algorithm);
    }

    public static SecretKey generateSecretKeyForPKCS5Scheme2(String algorithm, char password[], byte salt[], int iterationCount)
    {
        PBEParametersGenerator generator = new PKCS5S2ParametersGenerator();
        generator.init(PBEParametersGenerator.PKCS5PasswordToBytes(password), salt, iterationCount);
        return new SecretKeySpec(((KeyParameter)generator.generateDerivedParameters(PEMUtilities.getKeySize(algorithm))).getKey(), algorithm);
    }

    private final Map parsers;
    private PasswordFinder pFinder;

}
