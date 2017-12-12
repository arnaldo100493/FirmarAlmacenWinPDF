// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsDHUtils.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.AsymmetricCipherKeyPair;
import co.org.bouncy.crypto.agreement.DHBasicAgreement;
import co.org.bouncy.crypto.generators.DHBasicKeyPairGenerator;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.util.BigIntegers;
import java.io.*;
import java.math.BigInteger;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsFatalAlert, TlsUtils

public class TlsDHUtils
{

    public TlsDHUtils()
    {
    }

    public static byte[] calculateDHBasicAgreement(DHPublicKeyParameters publicKey, DHPrivateKeyParameters privateKey)
    {
        DHBasicAgreement basicAgreement = new DHBasicAgreement();
        basicAgreement.init(privateKey);
        BigInteger agreementValue = basicAgreement.calculateAgreement(publicKey);
        return BigIntegers.asUnsignedByteArray(agreementValue);
    }

    public static AsymmetricCipherKeyPair generateDHKeyPair(SecureRandom random, DHParameters dhParams)
    {
        DHBasicKeyPairGenerator dhGen = new DHBasicKeyPairGenerator();
        dhGen.init(new DHKeyGenerationParameters(random, dhParams));
        return dhGen.generateKeyPair();
    }

    public static DHPrivateKeyParameters generateEphemeralClientKeyExchange(SecureRandom random, DHParameters dhParams, OutputStream output)
        throws IOException
    {
        AsymmetricCipherKeyPair dhAgreeClientKeyPair = generateDHKeyPair(random, dhParams);
        DHPrivateKeyParameters dhAgreeClientPrivateKey = (DHPrivateKeyParameters)dhAgreeClientKeyPair.getPrivate();
        BigInteger Yc = ((DHPublicKeyParameters)dhAgreeClientKeyPair.getPublic()).getY();
        byte keData[] = BigIntegers.asUnsignedByteArray(Yc);
        TlsUtils.writeOpaque16(keData, output);
        return dhAgreeClientPrivateKey;
    }

    public static DHPublicKeyParameters validateDHPublicKey(DHPublicKeyParameters key)
        throws IOException
    {
        BigInteger Y = key.getY();
        DHParameters params = key.getParameters();
        BigInteger p = params.getP();
        BigInteger g = params.getG();
        if(!p.isProbablePrime(2))
            throw new TlsFatalAlert((short)47);
        if(g.compareTo(TWO) < 0 || g.compareTo(p.subtract(TWO)) > 0)
            throw new TlsFatalAlert((short)47);
        if(Y.compareTo(TWO) < 0 || Y.compareTo(p.subtract(ONE)) > 0)
            throw new TlsFatalAlert((short)47);
        else
            return key;
    }

    public static BigInteger readDHParameter(InputStream input)
        throws IOException
    {
        return new BigInteger(1, TlsUtils.readOpaque16(input));
    }

    public static void writeDHParameter(BigInteger x, OutputStream output)
        throws IOException
    {
        TlsUtils.writeOpaque16(BigIntegers.asUnsignedByteArray(x), output);
    }

    static final BigInteger ONE = BigInteger.valueOf(1L);
    static final BigInteger TWO = BigInteger.valueOf(2L);

}
