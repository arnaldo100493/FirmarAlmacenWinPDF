// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RainbowKeyFactorySpi.java

package co.org.bouncy.pqc.jcajce.provider.rainbow;

import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.jcajce.provider.util.AsymmetricKeyInfoConverter;
import co.org.bouncy.pqc.asn1.RainbowPrivateKey;
import co.org.bouncy.pqc.asn1.RainbowPublicKey;
import co.org.bouncy.pqc.jcajce.spec.RainbowPrivateKeySpec;
import co.org.bouncy.pqc.jcajce.spec.RainbowPublicKeySpec;
import java.io.IOException;
import java.security.*;
import java.security.spec.*;

// Referenced classes of package co.org.bouncy.pqc.jcajce.provider.rainbow:
//            BCRainbowPrivateKey, BCRainbowPublicKey

public class RainbowKeyFactorySpi extends KeyFactorySpi
    implements AsymmetricKeyInfoConverter
{

    public RainbowKeyFactorySpi()
    {
    }

    public PrivateKey engineGeneratePrivate(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof RainbowPrivateKeySpec)
            return new BCRainbowPrivateKey((RainbowPrivateKeySpec)keySpec);
        if(keySpec instanceof PKCS8EncodedKeySpec)
        {
            byte encKey[] = ((PKCS8EncodedKeySpec)keySpec).getEncoded();
            try
            {
                return generatePrivate(PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(encKey)));
            }
            catch(Exception e)
            {
                throw new InvalidKeySpecException(e.toString());
            }
        } else
        {
            throw new InvalidKeySpecException((new StringBuilder()).append("Unsupported key specification: ").append(keySpec.getClass()).append(".").toString());
        }
    }

    public PublicKey engineGeneratePublic(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof RainbowPublicKeySpec)
            return new BCRainbowPublicKey((RainbowPublicKeySpec)keySpec);
        if(keySpec instanceof X509EncodedKeySpec)
        {
            byte encKey[] = ((X509EncodedKeySpec)keySpec).getEncoded();
            try
            {
                return generatePublic(SubjectPublicKeyInfo.getInstance(encKey));
            }
            catch(Exception e)
            {
                throw new InvalidKeySpecException(e.toString());
            }
        } else
        {
            throw new InvalidKeySpecException((new StringBuilder()).append("Unknown key specification: ").append(keySpec).append(".").toString());
        }
    }

    public final KeySpec engineGetKeySpec(Key key, Class keySpec)
        throws InvalidKeySpecException
    {
        if(key instanceof BCRainbowPrivateKey)
        {
            if(java/security/spec/PKCS8EncodedKeySpec.isAssignableFrom(keySpec))
                return new PKCS8EncodedKeySpec(key.getEncoded());
            if(co/org/bouncy/pqc/jcajce/spec/RainbowPrivateKeySpec.isAssignableFrom(keySpec))
            {
                BCRainbowPrivateKey privKey = (BCRainbowPrivateKey)key;
                return new RainbowPrivateKeySpec(privKey.getInvA1(), privKey.getB1(), privKey.getInvA2(), privKey.getB2(), privKey.getVi(), privKey.getLayers());
            }
        } else
        if(key instanceof BCRainbowPublicKey)
        {
            if(java/security/spec/X509EncodedKeySpec.isAssignableFrom(keySpec))
                return new X509EncodedKeySpec(key.getEncoded());
            if(co/org/bouncy/pqc/jcajce/spec/RainbowPublicKeySpec.isAssignableFrom(keySpec))
            {
                BCRainbowPublicKey pubKey = (BCRainbowPublicKey)key;
                return new RainbowPublicKeySpec(pubKey.getDocLength(), pubKey.getCoeffQuadratic(), pubKey.getCoeffSingular(), pubKey.getCoeffScalar());
            }
        } else
        {
            throw new InvalidKeySpecException((new StringBuilder()).append("Unsupported key type: ").append(key.getClass()).append(".").toString());
        }
        throw new InvalidKeySpecException((new StringBuilder()).append("Unknown key specification: ").append(keySpec).append(".").toString());
    }

    public final Key engineTranslateKey(Key key)
        throws InvalidKeyException
    {
        if((key instanceof BCRainbowPrivateKey) || (key instanceof BCRainbowPublicKey))
            return key;
        else
            throw new InvalidKeyException("Unsupported key type");
    }

    public PrivateKey generatePrivate(PrivateKeyInfo keyInfo)
        throws IOException
    {
        RainbowPrivateKey pKey = RainbowPrivateKey.getInstance(keyInfo.parsePrivateKey());
        return new BCRainbowPrivateKey(pKey.getInvA1(), pKey.getB1(), pKey.getInvA2(), pKey.getB2(), pKey.getVi(), pKey.getLayers());
    }

    public PublicKey generatePublic(SubjectPublicKeyInfo keyInfo)
        throws IOException
    {
        RainbowPublicKey pKey = RainbowPublicKey.getInstance(keyInfo.parsePublicKey());
        return new BCRainbowPublicKey(pKey.getDocLength(), pKey.getCoeffQuadratic(), pKey.getCoeffSingular(), pKey.getCoeffScalar());
    }
}
