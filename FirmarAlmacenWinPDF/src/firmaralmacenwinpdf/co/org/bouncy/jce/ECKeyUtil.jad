// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECKeyUtil.java

package co.org.bouncy.jce;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cryptopro.CryptoProObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.asn1.x9.*;
import co.org.bouncy.jcajce.provider.asymmetric.util.ECUtil;
import co.org.bouncy.jcajce.provider.config.ProviderConfiguration;
import co.org.bouncy.jce.provider.BouncyCastleProvider;
import co.org.bouncy.jce.spec.ECParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class ECKeyUtil
{
    private static class UnexpectedException extends RuntimeException
    {

        public Throwable getCause()
        {
            return cause;
        }

        private Throwable cause;

        UnexpectedException(Throwable cause)
        {
            super(cause.toString());
            this.cause = cause;
        }
    }


    public ECKeyUtil()
    {
    }

    public static PublicKey publicToExplicitParameters(PublicKey key, String providerName)
        throws IllegalArgumentException, NoSuchAlgorithmException, NoSuchProviderException
    {
        Provider provider = Security.getProvider(providerName);
        if(provider == null)
            throw new NoSuchProviderException((new StringBuilder()).append("cannot find provider: ").append(providerName).toString());
        else
            return publicToExplicitParameters(key, provider);
    }

    public static PublicKey publicToExplicitParameters(PublicKey key, Provider provider)
        throws IllegalArgumentException, NoSuchAlgorithmException
    {
        SubjectPublicKeyInfo info;
        X962Parameters params;
        X9ECParameters curveParams;
        KeyFactory keyFact;
        try
        {
            info = SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(key.getEncoded()));
            if(info.getAlgorithmId().getObjectId().equals(CryptoProObjectIdentifiers.gostR3410_2001))
                throw new IllegalArgumentException("cannot convert GOST key to explicit parameters.");
            params = X962Parameters.getInstance(info.getAlgorithmId().getParameters());
            if(params.isNamedCurve())
            {
                ASN1ObjectIdentifier oid = ASN1ObjectIdentifier.getInstance(params.getParameters());
                curveParams = ECUtil.getNamedCurveByOid(oid);
                curveParams = new X9ECParameters(curveParams.getCurve(), curveParams.getG(), curveParams.getN(), curveParams.getH());
            } else
            if(params.isImplicitlyCA())
                curveParams = new X9ECParameters(BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa().getCurve(), BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa().getG(), BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa().getN(), BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa().getH());
            else
                return key;
        }
        catch(IllegalArgumentException e)
        {
            throw e;
        }
        catch(NoSuchAlgorithmException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new UnexpectedException(e);
        }
        params = new X962Parameters(curveParams);
        info = new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, params), info.getPublicKeyData().getBytes());
        keyFact = KeyFactory.getInstance(key.getAlgorithm(), provider);
        return keyFact.generatePublic(new X509EncodedKeySpec(info.getEncoded()));
    }

    public static PrivateKey privateToExplicitParameters(PrivateKey key, String providerName)
        throws IllegalArgumentException, NoSuchAlgorithmException, NoSuchProviderException
    {
        Provider provider = Security.getProvider(providerName);
        if(provider == null)
            throw new NoSuchProviderException((new StringBuilder()).append("cannot find provider: ").append(providerName).toString());
        else
            return privateToExplicitParameters(key, provider);
    }

    public static PrivateKey privateToExplicitParameters(PrivateKey key, Provider provider)
        throws IllegalArgumentException, NoSuchAlgorithmException
    {
        PrivateKeyInfo info;
        X962Parameters params;
        X9ECParameters curveParams;
        KeyFactory keyFact;
        try
        {
            info = PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(key.getEncoded()));
            if(info.getAlgorithmId().getObjectId().equals(CryptoProObjectIdentifiers.gostR3410_2001))
                throw new UnsupportedEncodingException("cannot convert GOST key to explicit parameters.");
            params = X962Parameters.getInstance(info.getAlgorithmId().getParameters());
            if(params.isNamedCurve())
            {
                ASN1ObjectIdentifier oid = ASN1ObjectIdentifier.getInstance(params.getParameters());
                curveParams = ECUtil.getNamedCurveByOid(oid);
                curveParams = new X9ECParameters(curveParams.getCurve(), curveParams.getG(), curveParams.getN(), curveParams.getH());
            } else
            if(params.isImplicitlyCA())
                curveParams = new X9ECParameters(BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa().getCurve(), BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa().getG(), BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa().getN(), BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa().getH());
            else
                return key;
        }
        catch(IllegalArgumentException e)
        {
            throw e;
        }
        catch(NoSuchAlgorithmException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new UnexpectedException(e);
        }
        params = new X962Parameters(curveParams);
        info = new PrivateKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.id_ecPublicKey, params), info.parsePrivateKey());
        keyFact = KeyFactory.getInstance(key.getAlgorithm(), provider);
        return keyFact.generatePrivate(new PKCS8EncodedKeySpec(info.getEncoded()));
    }
}
