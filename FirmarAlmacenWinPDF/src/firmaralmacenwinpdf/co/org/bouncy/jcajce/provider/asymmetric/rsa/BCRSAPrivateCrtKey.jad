// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCRSAPrivateCrtKey.java

package co.org.bouncy.jcajce.provider.asymmetric.rsa;

import co.org.bouncy.asn1.DERNull;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.params.RSAPrivateCrtKeyParameters;
import co.org.bouncy.jcajce.provider.asymmetric.util.KeyUtil;
import java.io.IOException;
import java.math.BigInteger;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.RSAPrivateCrtKeySpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.rsa:
//            BCRSAPrivateKey

public class BCRSAPrivateCrtKey extends BCRSAPrivateKey
    implements RSAPrivateCrtKey
{

    BCRSAPrivateCrtKey(RSAPrivateCrtKeyParameters key)
    {
        super(key);
        publicExponent = key.getPublicExponent();
        primeP = key.getP();
        primeQ = key.getQ();
        primeExponentP = key.getDP();
        primeExponentQ = key.getDQ();
        crtCoefficient = key.getQInv();
    }

    BCRSAPrivateCrtKey(RSAPrivateCrtKeySpec spec)
    {
        modulus = spec.getModulus();
        publicExponent = spec.getPublicExponent();
        privateExponent = spec.getPrivateExponent();
        primeP = spec.getPrimeP();
        primeQ = spec.getPrimeQ();
        primeExponentP = spec.getPrimeExponentP();
        primeExponentQ = spec.getPrimeExponentQ();
        crtCoefficient = spec.getCrtCoefficient();
    }

    BCRSAPrivateCrtKey(RSAPrivateCrtKey key)
    {
        modulus = key.getModulus();
        publicExponent = key.getPublicExponent();
        privateExponent = key.getPrivateExponent();
        primeP = key.getPrimeP();
        primeQ = key.getPrimeQ();
        primeExponentP = key.getPrimeExponentP();
        primeExponentQ = key.getPrimeExponentQ();
        crtCoefficient = key.getCrtCoefficient();
    }

    BCRSAPrivateCrtKey(PrivateKeyInfo info)
        throws IOException
    {
        this(RSAPrivateKey.getInstance(info.parsePrivateKey()));
    }

    BCRSAPrivateCrtKey(RSAPrivateKey key)
    {
        modulus = key.getModulus();
        publicExponent = key.getPublicExponent();
        privateExponent = key.getPrivateExponent();
        primeP = key.getPrime1();
        primeQ = key.getPrime2();
        primeExponentP = key.getExponent1();
        primeExponentQ = key.getExponent2();
        crtCoefficient = key.getCoefficient();
    }

    public String getFormat()
    {
        return "PKCS#8";
    }

    public byte[] getEncoded()
    {
        return KeyUtil.getEncodedPrivateKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.rsaEncryption, DERNull.INSTANCE), new RSAPrivateKey(getModulus(), getPublicExponent(), getPrivateExponent(), getPrimeP(), getPrimeQ(), getPrimeExponentP(), getPrimeExponentQ(), getCrtCoefficient()));
    }

    public BigInteger getPublicExponent()
    {
        return publicExponent;
    }

    public BigInteger getPrimeP()
    {
        return primeP;
    }

    public BigInteger getPrimeQ()
    {
        return primeQ;
    }

    public BigInteger getPrimeExponentP()
    {
        return primeExponentP;
    }

    public BigInteger getPrimeExponentQ()
    {
        return primeExponentQ;
    }

    public BigInteger getCrtCoefficient()
    {
        return crtCoefficient;
    }

    public int hashCode()
    {
        return getModulus().hashCode() ^ getPublicExponent().hashCode() ^ getPrivateExponent().hashCode();
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(!(o instanceof RSAPrivateCrtKey))
        {
            return false;
        } else
        {
            RSAPrivateCrtKey key = (RSAPrivateCrtKey)o;
            return getModulus().equals(key.getModulus()) && getPublicExponent().equals(key.getPublicExponent()) && getPrivateExponent().equals(key.getPrivateExponent()) && getPrimeP().equals(key.getPrimeP()) && getPrimeQ().equals(key.getPrimeQ()) && getPrimeExponentP().equals(key.getPrimeExponentP()) && getPrimeExponentQ().equals(key.getPrimeExponentQ()) && getCrtCoefficient().equals(key.getCrtCoefficient());
        }
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        String nl = System.getProperty("line.separator");
        buf.append("RSA Private CRT Key").append(nl);
        buf.append("            modulus: ").append(getModulus().toString(16)).append(nl);
        buf.append("    public exponent: ").append(getPublicExponent().toString(16)).append(nl);
        buf.append("   private exponent: ").append(getPrivateExponent().toString(16)).append(nl);
        buf.append("             primeP: ").append(getPrimeP().toString(16)).append(nl);
        buf.append("             primeQ: ").append(getPrimeQ().toString(16)).append(nl);
        buf.append("     primeExponentP: ").append(getPrimeExponentP().toString(16)).append(nl);
        buf.append("     primeExponentQ: ").append(getPrimeExponentQ().toString(16)).append(nl);
        buf.append("     crtCoefficient: ").append(getCrtCoefficient().toString(16)).append(nl);
        return buf.toString();
    }

    static final long serialVersionUID = 0x6cba87ce0273552eL;
    private BigInteger publicExponent;
    private BigInteger primeP;
    private BigInteger primeQ;
    private BigInteger primeExponentP;
    private BigInteger primeExponentQ;
    private BigInteger crtCoefficient;
}
