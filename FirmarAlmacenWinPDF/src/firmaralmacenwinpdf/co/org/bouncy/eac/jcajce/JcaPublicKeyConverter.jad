// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaPublicKeyConverter.java

package co.org.bouncy.eac.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.eac.EACObjectIdentifiers;
import co.org.bouncy.asn1.eac.ECDSAPublicKey;
import co.org.bouncy.asn1.eac.PublicKeyDataObject;
import co.org.bouncy.asn1.eac.RSAPublicKey;
import co.org.bouncy.eac.EACException;
import co.org.bouncy.jce.spec.ECParameterSpec;
import co.org.bouncy.jce.spec.ECPublicKeySpec;
import co.org.bouncy.math.ec.ECCurve;
import co.org.bouncy.math.ec.ECPoint;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECField;
import java.security.spec.ECFieldFp;
import java.security.spec.EllipticCurve;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

// Referenced classes of package co.org.bouncy.eac.jcajce:
//            DefaultEACHelper, NamedEACHelper, ProviderEACHelper, EACHelper

public class JcaPublicKeyConverter
{

    public JcaPublicKeyConverter()
    {
        helper = new DefaultEACHelper();
    }

    public JcaPublicKeyConverter setProvider(String providerName)
    {
        helper = new NamedEACHelper(providerName);
        return this;
    }

    public JcaPublicKeyConverter setProvider(Provider provider)
    {
        helper = new ProviderEACHelper(provider);
        return this;
    }

    public PublicKey getKey(PublicKeyDataObject publicKeyDataObject)
        throws EACException, InvalidKeySpecException
    {
        if(publicKeyDataObject.getUsage().on(EACObjectIdentifiers.id_TA_ECDSA))
            return getECPublicKeyPublicKey((ECDSAPublicKey)publicKeyDataObject);
        RSAPublicKey pubKey = (RSAPublicKey)publicKeyDataObject;
        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(pubKey.getModulus(), pubKey.getPublicExponent());
        try
        {
            KeyFactory factk = helper.createKeyFactory("RSA");
            return factk.generatePublic(pubKeySpec);
        }
        catch(NoSuchProviderException e)
        {
            throw new EACException((new StringBuilder()).append("cannot find provider: ").append(e.getMessage()).toString(), e);
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new EACException((new StringBuilder()).append("cannot find algorithm ECDSA: ").append(e.getMessage()).toString(), e);
        }
    }

    private PublicKey getECPublicKeyPublicKey(ECDSAPublicKey key)
        throws EACException, InvalidKeySpecException
    {
        ECParameterSpec spec = getParams(key);
        ECCurve curve = spec.getCurve();
        ECPoint point = curve.decodePoint(key.getPublicPointY());
        ECPublicKeySpec pubKeySpec = new ECPublicKeySpec(point, spec);
        KeyFactory factk;
        try
        {
            factk = helper.createKeyFactory("ECDSA");
        }
        catch(NoSuchProviderException e)
        {
            throw new EACException((new StringBuilder()).append("cannot find provider: ").append(e.getMessage()).toString(), e);
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new EACException((new StringBuilder()).append("cannot find algorithm ECDSA: ").append(e.getMessage()).toString(), e);
        }
        return factk.generatePublic(pubKeySpec);
    }

    private ECParameterSpec getParams(ECDSAPublicKey key)
    {
        if(!key.hasParameters())
        {
            throw new IllegalArgumentException("Public key does not contains EC Params");
        } else
        {
            BigInteger p = key.getPrimeModulusP();
            co.org.bouncy.math.ec.ECCurve.Fp curve = new co.org.bouncy.math.ec.ECCurve.Fp(p, key.getFirstCoefA(), key.getSecondCoefB());
            ECPoint G = curve.decodePoint(key.getBasePointG());
            BigInteger order = key.getOrderOfBasePointR();
            BigInteger coFactor = key.getCofactorF();
            ECParameterSpec ecspec = new ECParameterSpec(curve, G, order, coFactor);
            return ecspec;
        }
    }

    public PublicKeyDataObject getPublicKeyDataObject(ASN1ObjectIdentifier usage, PublicKey publicKey)
    {
        if(publicKey instanceof java.security.interfaces.RSAPublicKey)
        {
            java.security.interfaces.RSAPublicKey pubKey = (java.security.interfaces.RSAPublicKey)publicKey;
            return new RSAPublicKey(usage, pubKey.getModulus(), pubKey.getPublicExponent());
        } else
        {
            ECPublicKey pubKey = (ECPublicKey)publicKey;
            java.security.spec.ECParameterSpec params = pubKey.getParams();
            return new ECDSAPublicKey(usage, ((ECFieldFp)params.getCurve().getField()).getP(), params.getCurve().getA(), params.getCurve().getB(), convertPoint(convertCurve(params.getCurve()), params.getGenerator(), false).getEncoded(), params.getOrder(), convertPoint(convertCurve(params.getCurve()), pubKey.getW(), false).getEncoded(), params.getCofactor());
        }
    }

    private static ECPoint convertPoint(ECCurve curve, java.security.spec.ECPoint point, boolean withCompression)
    {
        return curve.createPoint(point.getAffineX(), point.getAffineY(), withCompression);
    }

    private static ECCurve convertCurve(EllipticCurve ec)
    {
        ECField field = ec.getField();
        BigInteger a = ec.getA();
        BigInteger b = ec.getB();
        if(field instanceof ECFieldFp)
            return new co.org.bouncy.math.ec.ECCurve.Fp(((ECFieldFp)field).getP(), a, b);
        else
            throw new IllegalStateException("not implemented yet!!!");
    }

    private EACHelper helper;
}
