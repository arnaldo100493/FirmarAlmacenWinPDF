// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyAgreementSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.ec;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.DERObjectIdentifier;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x9.X9IntegerConverter;
import co.org.bouncy.crypto.BasicAgreement;
import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.DerivationFunction;
import co.org.bouncy.crypto.agreement.ECDHBasicAgreement;
import co.org.bouncy.crypto.agreement.ECDHCBasicAgreement;
import co.org.bouncy.crypto.agreement.ECMQVBasicAgreement;
import co.org.bouncy.crypto.agreement.kdf.DHKDFParameters;
import co.org.bouncy.crypto.agreement.kdf.ECDHKEKGenerator;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.crypto.params.ECDomainParameters;
import co.org.bouncy.crypto.params.ECPrivateKeyParameters;
import co.org.bouncy.crypto.params.ECPublicKeyParameters;
import co.org.bouncy.crypto.params.MQVPrivateParameters;
import co.org.bouncy.crypto.params.MQVPublicParameters;
import co.org.bouncy.jcajce.provider.asymmetric.util.ECUtil;
import co.org.bouncy.jce.interfaces.ECPrivateKey;
import co.org.bouncy.jce.interfaces.ECPublicKey;
import co.org.bouncy.jce.interfaces.MQVPrivateKey;
import co.org.bouncy.jce.interfaces.MQVPublicKey;
import co.org.bouncy.math.ec.ECPoint;
import co.org.bouncy.util.Integers;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Hashtable;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

public class KeyAgreementSpi extends javax.crypto.KeyAgreementSpi
{
    public static class MQVwithSHA1KDF extends KeyAgreementSpi
    {

        public MQVwithSHA1KDF()
        {
            super("ECMQVwithSHA1KDF", new ECMQVBasicAgreement(), new ECDHKEKGenerator(new SHA1Digest()));
        }
    }

    public static class DHwithSHA1KDF extends KeyAgreementSpi
    {

        public DHwithSHA1KDF()
        {
            super("ECDHwithSHA1KDF", new ECDHBasicAgreement(), new ECDHKEKGenerator(new SHA1Digest()));
        }
    }

    public static class MQV extends KeyAgreementSpi
    {

        public MQV()
        {
            super("ECMQV", new ECMQVBasicAgreement(), null);
        }
    }

    public static class DHC extends KeyAgreementSpi
    {

        public DHC()
        {
            super("ECDHC", new ECDHCBasicAgreement(), null);
        }
    }

    public static class DH extends KeyAgreementSpi
    {

        public DH()
        {
            super("ECDH", new ECDHBasicAgreement(), null);
        }
    }


    private byte[] bigIntToBytes(BigInteger r)
    {
        return converter.integerToBytes(r, converter.getByteLength(parameters.getG().getX()));
    }

    protected KeyAgreementSpi(String kaAlgorithm, BasicAgreement agreement, DerivationFunction kdf)
    {
        this.kaAlgorithm = kaAlgorithm;
        this.agreement = agreement;
        this.kdf = kdf;
    }

    protected Key engineDoPhase(Key key, boolean lastPhase)
        throws InvalidKeyException, IllegalStateException
    {
        if(parameters == null)
            throw new IllegalStateException((new StringBuilder()).append(kaAlgorithm).append(" not initialised.").toString());
        if(!lastPhase)
            throw new IllegalStateException((new StringBuilder()).append(kaAlgorithm).append(" can only be between two parties.").toString());
        CipherParameters pubKey;
        if(agreement instanceof ECMQVBasicAgreement)
        {
            if(!(key instanceof MQVPublicKey))
                throw new InvalidKeyException((new StringBuilder()).append(kaAlgorithm).append(" key agreement requires ").append(getSimpleName(co/org/bouncy/jce/interfaces/MQVPublicKey)).append(" for doPhase").toString());
            MQVPublicKey mqvPubKey = (MQVPublicKey)key;
            ECPublicKeyParameters staticKey = (ECPublicKeyParameters)ECUtil.generatePublicKeyParameter(mqvPubKey.getStaticKey());
            ECPublicKeyParameters ephemKey = (ECPublicKeyParameters)ECUtil.generatePublicKeyParameter(mqvPubKey.getEphemeralKey());
            pubKey = new MQVPublicParameters(staticKey, ephemKey);
        } else
        {
            if(!(key instanceof PublicKey))
                throw new InvalidKeyException((new StringBuilder()).append(kaAlgorithm).append(" key agreement requires ").append(getSimpleName(co/org/bouncy/jce/interfaces/ECPublicKey)).append(" for doPhase").toString());
            pubKey = ECUtil.generatePublicKeyParameter((PublicKey)key);
        }
        result = agreement.calculateAgreement(pubKey);
        return null;
    }

    protected byte[] engineGenerateSecret()
        throws IllegalStateException
    {
        if(kdf != null)
            throw new UnsupportedOperationException("KDF can only be used when algorithm is known");
        else
            return bigIntToBytes(result);
    }

    protected int engineGenerateSecret(byte sharedSecret[], int offset)
        throws IllegalStateException, ShortBufferException
    {
        byte secret[] = engineGenerateSecret();
        if(sharedSecret.length - offset < secret.length)
        {
            throw new ShortBufferException((new StringBuilder()).append(kaAlgorithm).append(" key agreement: need ").append(secret.length).append(" bytes").toString());
        } else
        {
            System.arraycopy(secret, 0, sharedSecret, offset, secret.length);
            return secret.length;
        }
    }

    protected SecretKey engineGenerateSecret(String algorithm)
        throws NoSuchAlgorithmException
    {
        byte secret[] = bigIntToBytes(result);
        if(kdf != null)
        {
            if(!algorithms.containsKey(algorithm))
                throw new NoSuchAlgorithmException((new StringBuilder()).append("unknown algorithm encountered: ").append(algorithm).toString());
            int keySize = ((Integer)algorithms.get(algorithm)).intValue();
            DHKDFParameters params = new DHKDFParameters(new DERObjectIdentifier(algorithm), keySize, secret);
            byte keyBytes[] = new byte[keySize / 8];
            kdf.init(params);
            kdf.generateBytes(keyBytes, 0, keyBytes.length);
            secret = keyBytes;
        }
        return new SecretKeySpec(secret, algorithm);
    }

    protected void engineInit(Key key, AlgorithmParameterSpec params, SecureRandom random)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        initFromKey(key);
    }

    protected void engineInit(Key key, SecureRandom random)
        throws InvalidKeyException
    {
        initFromKey(key);
    }

    private void initFromKey(Key key)
        throws InvalidKeyException
    {
        if(agreement instanceof ECMQVBasicAgreement)
        {
            if(!(key instanceof MQVPrivateKey))
                throw new InvalidKeyException((new StringBuilder()).append(kaAlgorithm).append(" key agreement requires ").append(getSimpleName(co/org/bouncy/jce/interfaces/MQVPrivateKey)).append(" for initialisation").toString());
            MQVPrivateKey mqvPrivKey = (MQVPrivateKey)key;
            ECPrivateKeyParameters staticPrivKey = (ECPrivateKeyParameters)ECUtil.generatePrivateKeyParameter(mqvPrivKey.getStaticPrivateKey());
            ECPrivateKeyParameters ephemPrivKey = (ECPrivateKeyParameters)ECUtil.generatePrivateKeyParameter(mqvPrivKey.getEphemeralPrivateKey());
            ECPublicKeyParameters ephemPubKey = null;
            if(mqvPrivKey.getEphemeralPublicKey() != null)
                ephemPubKey = (ECPublicKeyParameters)ECUtil.generatePublicKeyParameter(mqvPrivKey.getEphemeralPublicKey());
            MQVPrivateParameters localParams = new MQVPrivateParameters(staticPrivKey, ephemPrivKey, ephemPubKey);
            parameters = staticPrivKey.getParameters();
            agreement.init(localParams);
        } else
        {
            if(!(key instanceof PrivateKey))
                throw new InvalidKeyException((new StringBuilder()).append(kaAlgorithm).append(" key agreement requires ").append(getSimpleName(co/org/bouncy/jce/interfaces/ECPrivateKey)).append(" for initialisation").toString());
            ECPrivateKeyParameters privKey = (ECPrivateKeyParameters)ECUtil.generatePrivateKeyParameter((PrivateKey)key);
            parameters = privKey.getParameters();
            agreement.init(privKey);
        }
    }

    private static String getSimpleName(Class clazz)
    {
        String fullName = clazz.getName();
        return fullName.substring(fullName.lastIndexOf('.') + 1);
    }

    private static final X9IntegerConverter converter = new X9IntegerConverter();
    private static final Hashtable algorithms;
    private String kaAlgorithm;
    private BigInteger result;
    private ECDomainParameters parameters;
    private BasicAgreement agreement;
    private DerivationFunction kdf;

    static 
    {
        algorithms = new Hashtable();
        Integer i128 = Integers.valueOf(128);
        Integer i192 = Integers.valueOf(192);
        Integer i256 = Integers.valueOf(256);
        algorithms.put(NISTObjectIdentifiers.id_aes128_CBC.getId(), i128);
        algorithms.put(NISTObjectIdentifiers.id_aes192_CBC.getId(), i192);
        algorithms.put(NISTObjectIdentifiers.id_aes256_CBC.getId(), i256);
        algorithms.put(NISTObjectIdentifiers.id_aes128_wrap.getId(), i128);
        algorithms.put(NISTObjectIdentifiers.id_aes192_wrap.getId(), i192);
        algorithms.put(NISTObjectIdentifiers.id_aes256_wrap.getId(), i256);
        algorithms.put(PKCSObjectIdentifiers.id_alg_CMS3DESwrap.getId(), i192);
    }
}
