// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsECCUtils.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.asn1.sec.SECNamedCurves;
import co.org.bouncy.asn1.x9.X9ECParameters;
import co.org.bouncy.crypto.AsymmetricCipherKeyPair;
import co.org.bouncy.crypto.agreement.ECDHBasicAgreement;
import co.org.bouncy.crypto.generators.ECKeyPairGenerator;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.math.ec.*;
import co.org.bouncy.util.BigIntegers;
import co.org.bouncy.util.Integers;
import java.io.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Hashtable;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsFatalAlert, TlsUtils, TlsProtocol, NamedCurve

public class TlsECCUtils
{

    public TlsECCUtils()
    {
    }

    public static void addSupportedEllipticCurvesExtension(Hashtable extensions, int namedCurves[])
        throws IOException
    {
        extensions.put(EXT_elliptic_curves, createSupportedEllipticCurvesExtension(namedCurves));
    }

    public static void addSupportedPointFormatsExtension(Hashtable extensions, short ecPointFormats[])
        throws IOException
    {
        extensions.put(EXT_ec_point_formats, createSupportedPointFormatsExtension(ecPointFormats));
    }

    public static int[] getSupportedEllipticCurvesExtension(Hashtable extensions)
        throws IOException
    {
        if(extensions == null)
            return null;
        byte extensionValue[] = (byte[])(byte[])extensions.get(EXT_elliptic_curves);
        if(extensionValue == null)
            return null;
        else
            return readSupportedEllipticCurvesExtension(extensionValue);
    }

    public static short[] getSupportedPointFormatsExtension(Hashtable extensions)
        throws IOException
    {
        if(extensions == null)
            return null;
        byte extensionValue[] = (byte[])(byte[])extensions.get(EXT_ec_point_formats);
        if(extensionValue == null)
            return null;
        else
            return readSupportedPointFormatsExtension(extensionValue);
    }

    public static byte[] createSupportedEllipticCurvesExtension(int namedCurves[])
        throws IOException
    {
        if(namedCurves == null || namedCurves.length < 1)
        {
            throw new TlsFatalAlert((short)80);
        } else
        {
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            TlsUtils.writeUint16(2 * namedCurves.length, buf);
            TlsUtils.writeUint16Array(namedCurves, buf);
            return buf.toByteArray();
        }
    }

    public static byte[] createSupportedPointFormatsExtension(short ecPointFormats[])
        throws IOException
    {
        if(ecPointFormats == null)
            ecPointFormats = (new short[] {
                0
            });
        else
        if(!TlsProtocol.arrayContains(ecPointFormats, (short)0))
        {
            short tmp[] = new short[ecPointFormats.length + 1];
            System.arraycopy(ecPointFormats, 0, tmp, 0, ecPointFormats.length);
            tmp[ecPointFormats.length] = 0;
            ecPointFormats = tmp;
        }
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        TlsUtils.writeUint8((short)ecPointFormats.length, buf);
        TlsUtils.writeUint8Array(ecPointFormats, buf);
        return buf.toByteArray();
    }

    public static int[] readSupportedEllipticCurvesExtension(byte extensionValue[])
        throws IOException
    {
        if(extensionValue == null)
            throw new IllegalArgumentException("'extensionValue' cannot be null");
        ByteArrayInputStream buf = new ByteArrayInputStream(extensionValue);
        int length = TlsUtils.readUint16(buf);
        if(length < 2 || (length & 1) != 0)
        {
            throw new TlsFatalAlert((short)50);
        } else
        {
            int namedCurves[] = TlsUtils.readUint16Array(length / 2, buf);
            TlsProtocol.assertEmpty(buf);
            return namedCurves;
        }
    }

    public static short[] readSupportedPointFormatsExtension(byte extensionValue[])
        throws IOException
    {
        if(extensionValue == null)
            throw new IllegalArgumentException("'extensionValue' cannot be null");
        ByteArrayInputStream buf = new ByteArrayInputStream(extensionValue);
        short length = TlsUtils.readUint8(buf);
        if(length < 1)
            throw new TlsFatalAlert((short)50);
        short ecPointFormats[] = TlsUtils.readUint8Array(length, buf);
        TlsProtocol.assertEmpty(buf);
        if(!TlsProtocol.arrayContains(ecPointFormats, (short)0))
            throw new TlsFatalAlert((short)47);
        else
            return ecPointFormats;
    }

    public static String getNameOfNamedCurve(int namedCurve)
    {
        return isSupportedNamedCurve(namedCurve) ? curveNames[namedCurve - 1] : null;
    }

    public static ECDomainParameters getParametersForNamedCurve(int namedCurve)
    {
        String curveName = getNameOfNamedCurve(namedCurve);
        if(curveName == null)
            return null;
        X9ECParameters ecP = SECNamedCurves.getByName(curveName);
        if(ecP == null)
            return null;
        else
            return new ECDomainParameters(ecP.getCurve(), ecP.getG(), ecP.getN(), ecP.getH(), ecP.getSeed());
    }

    public static boolean hasAnySupportedNamedCurves()
    {
        return curveNames.length > 0;
    }

    public static boolean containsECCCipherSuites(int cipherSuites[])
    {
        for(int i = 0; i < cipherSuites.length; i++)
            if(isECCCipherSuite(cipherSuites[i]))
                return true;

        return false;
    }

    public static boolean isECCCipherSuite(int cipherSuite)
    {
        switch(cipherSuite)
        {
        case 49153: 
        case 49154: 
        case 49155: 
        case 49156: 
        case 49157: 
        case 49158: 
        case 49159: 
        case 49160: 
        case 49161: 
        case 49162: 
        case 49163: 
        case 49164: 
        case 49165: 
        case 49166: 
        case 49167: 
        case 49168: 
        case 49169: 
        case 49170: 
        case 49171: 
        case 49172: 
        case 49173: 
        case 49174: 
        case 49175: 
        case 49176: 
        case 49177: 
        case 49187: 
        case 49188: 
        case 49189: 
        case 49190: 
        case 49191: 
        case 49192: 
        case 49193: 
        case 49194: 
        case 49195: 
        case 49196: 
        case 49197: 
        case 49198: 
        case 49199: 
        case 49200: 
        case 49201: 
        case 49202: 
            return true;

        case 49178: 
        case 49179: 
        case 49180: 
        case 49181: 
        case 49182: 
        case 49183: 
        case 49184: 
        case 49185: 
        case 49186: 
        default:
            return false;
        }
    }

    public static boolean areOnSameCurve(ECDomainParameters a, ECDomainParameters b)
    {
        return a.getCurve().equals(b.getCurve()) && a.getG().equals(b.getG()) && a.getN().equals(b.getN()) && a.getH().equals(b.getH());
    }

    public static boolean isSupportedNamedCurve(int namedCurve)
    {
        return namedCurve > 0 && namedCurve <= curveNames.length;
    }

    public static boolean isCompressionPreferred(short ecPointFormats[], short compressionFormat)
    {
        if(ecPointFormats == null)
            return false;
        for(int i = 0; i < ecPointFormats.length; i++)
        {
            short ecPointFormat = ecPointFormats[i];
            if(ecPointFormat == 0)
                return false;
            if(ecPointFormat == compressionFormat)
                return true;
        }

        return false;
    }

    public static byte[] serializeECFieldElement(int fieldSize, BigInteger x)
        throws IOException
    {
        int requiredLength = (fieldSize + 7) / 8;
        return BigIntegers.asUnsignedByteArray(requiredLength, x);
    }

    public static byte[] serializeECPoint(short ecPointFormats[], ECPoint point)
        throws IOException
    {
        ECCurve curve = point.getCurve();
        boolean compressed = false;
        if(curve instanceof co.org.bouncy.math.ec.ECCurve.F2m)
            compressed = isCompressionPreferred(ecPointFormats, (short)2);
        else
        if(curve instanceof co.org.bouncy.math.ec.ECCurve.Fp)
            compressed = isCompressionPreferred(ecPointFormats, (short)1);
        return point.getEncoded(compressed);
    }

    public static byte[] serializeECPublicKey(short ecPointFormats[], ECPublicKeyParameters keyParameters)
        throws IOException
    {
        return serializeECPoint(ecPointFormats, keyParameters.getQ());
    }

    public static BigInteger deserializeECFieldElement(int fieldSize, byte encoding[])
        throws IOException
    {
        int requiredLength = (fieldSize + 7) / 8;
        if(encoding.length != requiredLength)
            throw new TlsFatalAlert((short)50);
        else
            return new BigInteger(1, encoding);
    }

    public static ECPoint deserializeECPoint(short ecPointFormats[], ECCurve curve, byte encoding[])
        throws IOException
    {
        return curve.decodePoint(encoding);
    }

    public static ECPublicKeyParameters deserializeECPublicKey(short ecPointFormats[], ECDomainParameters curve_params, byte encoding[])
        throws IOException
    {
        try
        {
            ECPoint Y = deserializeECPoint(ecPointFormats, curve_params.getCurve(), encoding);
            return new ECPublicKeyParameters(Y, curve_params);
        }
        catch(RuntimeException e)
        {
            throw new TlsFatalAlert((short)47);
        }
    }

    public static byte[] calculateECDHBasicAgreement(ECPublicKeyParameters publicKey, ECPrivateKeyParameters privateKey)
    {
        ECDHBasicAgreement basicAgreement = new ECDHBasicAgreement();
        basicAgreement.init(privateKey);
        BigInteger agreementValue = basicAgreement.calculateAgreement(publicKey);
        return BigIntegers.asUnsignedByteArray(basicAgreement.getFieldSize(), agreementValue);
    }

    public static AsymmetricCipherKeyPair generateECKeyPair(SecureRandom random, ECDomainParameters ecParams)
    {
        ECKeyPairGenerator keyPairGenerator = new ECKeyPairGenerator();
        ECKeyGenerationParameters keyGenerationParameters = new ECKeyGenerationParameters(ecParams, random);
        keyPairGenerator.init(keyGenerationParameters);
        return keyPairGenerator.generateKeyPair();
    }

    public static ECPublicKeyParameters validateECPublicKey(ECPublicKeyParameters key)
        throws IOException
    {
        return key;
    }

    public static int readECExponent(int fieldSize, InputStream input)
        throws IOException
    {
        BigInteger K = readECParameter(input);
        if(K.bitLength() < 32)
        {
            int k = K.intValue();
            if(k > 0 && k < fieldSize)
                return k;
        }
        throw new TlsFatalAlert((short)47);
    }

    public static BigInteger readECFieldElement(int fieldSize, InputStream input)
        throws IOException
    {
        return deserializeECFieldElement(fieldSize, TlsUtils.readOpaque8(input));
    }

    public static BigInteger readECParameter(InputStream input)
        throws IOException
    {
        return new BigInteger(1, TlsUtils.readOpaque8(input));
    }

    public static ECDomainParameters readECParameters(int namedCurves[], short ecPointFormats[], InputStream input)
        throws IOException
    {
        short curveType = TlsUtils.readUint8(input);
        curveType;
        JVM INSTR tableswitch 1 3: default 363
    //                   1 32
    //                   2 115
    //                   3 314;
           goto _L1 _L2 _L3 _L4
_L2:
        BigInteger prime_p = readECParameter(input);
        BigInteger a = readECFieldElement(prime_p.bitLength(), input);
        BigInteger b = readECFieldElement(prime_p.bitLength(), input);
        ECCurve curve = new co.org.bouncy.math.ec.ECCurve.Fp(prime_p, a, b);
        ECPoint base = deserializeECPoint(ecPointFormats, curve, TlsUtils.readOpaque8(input));
        BigInteger order = readECParameter(input);
        BigInteger cofactor = readECParameter(input);
        return new ECDomainParameters(curve, base, order, cofactor);
_L3:
        int namedCurve;
        try
        {
            int m = TlsUtils.readUint16(input);
            short basis = TlsUtils.readUint8(input);
            ECCurve curve;
            switch(basis)
            {
            case 1: // '\001'
            {
                int k = readECExponent(m, input);
                BigInteger a = readECFieldElement(m, input);
                BigInteger b = readECFieldElement(m, input);
                curve = new co.org.bouncy.math.ec.ECCurve.F2m(m, k, a, b);
                break;
            }

            case 2: // '\002'
            {
                int k1 = readECExponent(m, input);
                int k2 = readECExponent(m, input);
                int k3 = readECExponent(m, input);
                BigInteger a = readECFieldElement(m, input);
                BigInteger b = readECFieldElement(m, input);
                curve = new co.org.bouncy.math.ec.ECCurve.F2m(m, k1, k2, k3, a, b);
                break;
            }

            default:
            {
                throw new TlsFatalAlert((short)47);
            }
            }
            ECPoint base = deserializeECPoint(ecPointFormats, curve, TlsUtils.readOpaque8(input));
            BigInteger order = readECParameter(input);
            BigInteger cofactor = readECParameter(input);
            return new ECDomainParameters(curve, base, order, cofactor);
        }
        catch(RuntimeException e)
        {
            throw new TlsFatalAlert((short)47);
        }
_L4:
        namedCurve = TlsUtils.readUint16(input);
        if(!NamedCurve.refersToASpecificNamedCurve(namedCurve))
            throw new TlsFatalAlert((short)47);
        if(!TlsProtocol.arrayContains(namedCurves, namedCurve))
            throw new TlsFatalAlert((short)47);
        else
            return getParametersForNamedCurve(namedCurve);
_L1:
        throw new TlsFatalAlert((short)47);
    }

    public static void writeECExponent(int k, OutputStream output)
        throws IOException
    {
        BigInteger K = BigInteger.valueOf(k);
        writeECParameter(K, output);
    }

    public static void writeECFieldElement(int fieldSize, BigInteger x, OutputStream output)
        throws IOException
    {
        TlsUtils.writeOpaque8(serializeECFieldElement(fieldSize, x), output);
    }

    public static void writeECParameter(BigInteger x, OutputStream output)
        throws IOException
    {
        TlsUtils.writeOpaque8(BigIntegers.asUnsignedByteArray(x), output);
    }

    public static void writeExplicitECParameters(short ecPointFormats[], ECDomainParameters ecParameters, OutputStream output)
        throws IOException
    {
        ECCurve curve = ecParameters.getCurve();
        if(curve instanceof co.org.bouncy.math.ec.ECCurve.Fp)
        {
            TlsUtils.writeUint8((short)1, output);
            co.org.bouncy.math.ec.ECCurve.Fp fp = (co.org.bouncy.math.ec.ECCurve.Fp)curve;
            writeECParameter(fp.getQ(), output);
        } else
        if(curve instanceof co.org.bouncy.math.ec.ECCurve.F2m)
        {
            TlsUtils.writeUint8((short)2, output);
            co.org.bouncy.math.ec.ECCurve.F2m f2m = (co.org.bouncy.math.ec.ECCurve.F2m)curve;
            TlsUtils.writeUint16(f2m.getM(), output);
            if(f2m.isTrinomial())
            {
                TlsUtils.writeUint8((short)1, output);
                writeECExponent(f2m.getK1(), output);
            } else
            {
                TlsUtils.writeUint8((short)2, output);
                writeECExponent(f2m.getK1(), output);
                writeECExponent(f2m.getK2(), output);
                writeECExponent(f2m.getK3(), output);
            }
        } else
        {
            throw new IllegalArgumentException("'ecParameters' not a known curve type");
        }
        writeECFieldElement(curve.getFieldSize(), curve.getA().toBigInteger(), output);
        writeECFieldElement(curve.getFieldSize(), curve.getB().toBigInteger(), output);
        TlsUtils.writeOpaque8(serializeECPoint(ecPointFormats, ecParameters.getG()), output);
        writeECParameter(ecParameters.getN(), output);
        writeECParameter(ecParameters.getH(), output);
    }

    public static void writeNamedECParameters(int namedCurve, OutputStream output)
        throws IOException
    {
        if(!NamedCurve.refersToASpecificNamedCurve(namedCurve))
        {
            throw new TlsFatalAlert((short)80);
        } else
        {
            TlsUtils.writeUint8((short)3, output);
            TlsUtils.writeUint16(namedCurve, output);
            return;
        }
    }

    public static final Integer EXT_elliptic_curves = Integers.valueOf(10);
    public static final Integer EXT_ec_point_formats = Integers.valueOf(11);
    private static final String curveNames[] = {
        "sect163k1", "sect163r1", "sect163r2", "sect193r1", "sect193r2", "sect233k1", "sect233r1", "sect239k1", "sect283k1", "sect283r1", 
        "sect409k1", "sect409r1", "sect571k1", "sect571r1", "secp160k1", "secp160r1", "secp160r2", "secp192k1", "secp192r1", "secp224k1", 
        "secp224r1", "secp256k1", "secp256r1", "secp384r1", "secp521r1"
    };

}
