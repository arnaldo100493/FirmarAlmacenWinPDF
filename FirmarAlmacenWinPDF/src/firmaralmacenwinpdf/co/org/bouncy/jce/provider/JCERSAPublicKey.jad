// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCERSAPublicKey.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.asn1.DERNull;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.crypto.params.RSAKeyParameters;
import co.org.bouncy.jcajce.provider.asymmetric.util.KeyUtil;
import java.io.IOException;
import java.math.BigInteger;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

public class JCERSAPublicKey
    implements RSAPublicKey
{

    JCERSAPublicKey(RSAKeyParameters key)
    {
        modulus = key.getModulus();
        publicExponent = key.getExponent();
    }

    JCERSAPublicKey(RSAPublicKeySpec spec)
    {
        modulus = spec.getModulus();
        publicExponent = spec.getPublicExponent();
    }

    JCERSAPublicKey(RSAPublicKey key)
    {
        modulus = key.getModulus();
        publicExponent = key.getPublicExponent();
    }

    JCERSAPublicKey(SubjectPublicKeyInfo info)
    {
        try
        {
            RSAPublicKeyStructure pubKey = new RSAPublicKeyStructure((ASN1Sequence)info.parsePublicKey());
            modulus = pubKey.getModulus();
            publicExponent = pubKey.getPublicExponent();
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException("invalid info structure in RSA public key");
        }
    }

    public BigInteger getModulus()
    {
        return modulus;
    }

    public BigInteger getPublicExponent()
    {
        return publicExponent;
    }

    public String getAlgorithm()
    {
        return "RSA";
    }

    public String getFormat()
    {
        return "X.509";
    }

    public byte[] getEncoded()
    {
        return KeyUtil.getEncodedSubjectPublicKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE), new RSAPublicKeyStructure(getModulus(), getPublicExponent()));
    }

    public int hashCode()
    {
        return getModulus().hashCode() ^ getPublicExponent().hashCode();
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(!(o instanceof RSAPublicKey))
        {
            return false;
        } else
        {
            RSAPublicKey key = (RSAPublicKey)o;
            return getModulus().equals(key.getModulus()) && getPublicExponent().equals(key.getPublicExponent());
        }
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        String nl = System.getProperty("line.separator");
        buf.append("RSA Public Key").append(nl);
        buf.append("            modulus: ").append(getModulus().toString(16)).append(nl);
        buf.append("    public exponent: ").append(getPublicExponent().toString(16)).append(nl);
        return buf.toString();
    }

    static final long serialVersionUID = 0x25226a0e5bfa6c84L;
    private BigInteger modulus;
    private BigInteger publicExponent;
}
