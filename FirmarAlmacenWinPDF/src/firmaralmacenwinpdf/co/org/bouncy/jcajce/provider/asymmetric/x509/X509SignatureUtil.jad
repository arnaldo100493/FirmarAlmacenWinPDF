// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509SignatureUtil.java

package co.org.bouncy.jcajce.provider.asymmetric.x509;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cryptopro.CryptoProObjectIdentifiers;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.RSASSAPSSparams;
import co.org.bouncy.asn1.teletrust.TeleTrusTObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x9.X9ObjectIdentifiers;
import java.io.IOException;
import java.security.*;
import java.security.spec.PSSParameterSpec;

class X509SignatureUtil
{

    X509SignatureUtil()
    {
    }

    static void setSignatureParameters(Signature signature, ASN1Encodable params)
        throws NoSuchAlgorithmException, SignatureException, InvalidKeyException
    {
        if(params != null && !derNull.equals(params))
        {
            AlgorithmParameters sigParams = AlgorithmParameters.getInstance(signature.getAlgorithm(), signature.getProvider());
            try
            {
                sigParams.init(params.toASN1Primitive().getEncoded());
            }
            catch(IOException e)
            {
                throw new SignatureException((new StringBuilder()).append("IOException decoding parameters: ").append(e.getMessage()).toString());
            }
            if(signature.getAlgorithm().endsWith("MGF1"))
                try
                {
                    signature.setParameter(sigParams.getParameterSpec(java/security/spec/PSSParameterSpec));
                }
                catch(GeneralSecurityException e)
                {
                    throw new SignatureException((new StringBuilder()).append("Exception extracting parameters: ").append(e.getMessage()).toString());
                }
        }
    }

    static String getSignatureName(AlgorithmIdentifier sigAlgId)
    {
        ASN1Encodable params = sigAlgId.getParameters();
        if(params != null && !derNull.equals(params))
        {
            if(sigAlgId.getAlgorithm().equals(PKCSObjectIdentifiers.id_RSASSA_PSS))
            {
                RSASSAPSSparams rsaParams = RSASSAPSSparams.getInstance(params);
                return (new StringBuilder()).append(getDigestAlgName(rsaParams.getHashAlgorithm().getAlgorithm())).append("withRSAandMGF1").toString();
            }
            if(sigAlgId.getAlgorithm().equals(X9ObjectIdentifiers.ecdsa_with_SHA2))
            {
                ASN1Sequence ecDsaParams = ASN1Sequence.getInstance(params);
                return (new StringBuilder()).append(getDigestAlgName((DERObjectIdentifier)ecDsaParams.getObjectAt(0))).append("withECDSA").toString();
            }
        }
        return sigAlgId.getAlgorithm().getId();
    }

    private static String getDigestAlgName(DERObjectIdentifier digestAlgOID)
    {
        if(PKCSObjectIdentifiers.md5.equals(digestAlgOID))
            return "MD5";
        if(OIWObjectIdentifiers.idSHA1.equals(digestAlgOID))
            return "SHA1";
        if(NISTObjectIdentifiers.id_sha224.equals(digestAlgOID))
            return "SHA224";
        if(NISTObjectIdentifiers.id_sha256.equals(digestAlgOID))
            return "SHA256";
        if(NISTObjectIdentifiers.id_sha384.equals(digestAlgOID))
            return "SHA384";
        if(NISTObjectIdentifiers.id_sha512.equals(digestAlgOID))
            return "SHA512";
        if(TeleTrusTObjectIdentifiers.ripemd128.equals(digestAlgOID))
            return "RIPEMD128";
        if(TeleTrusTObjectIdentifiers.ripemd160.equals(digestAlgOID))
            return "RIPEMD160";
        if(TeleTrusTObjectIdentifiers.ripemd256.equals(digestAlgOID))
            return "RIPEMD256";
        if(CryptoProObjectIdentifiers.gostR3411.equals(digestAlgOID))
            return "GOST3411";
        else
            return digestAlgOID.getId();
    }

    private static final ASN1Null derNull;

    static 
    {
        derNull = DERNull.INSTANCE;
    }
}
