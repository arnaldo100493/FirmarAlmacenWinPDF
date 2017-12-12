// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPUtil.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.openpgp.PGPException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class PGPUtil
{

    PGPUtil()
    {
    }

    static String getDigestName(int hashAlgorithm)
        throws PGPException
    {
        switch(hashAlgorithm)
        {
        case 2: // '\002'
            return "SHA1";

        case 5: // '\005'
            return "MD2";

        case 1: // '\001'
            return "MD5";

        case 3: // '\003'
            return "RIPEMD160";

        case 8: // '\b'
            return "SHA256";

        case 9: // '\t'
            return "SHA384";

        case 10: // '\n'
            return "SHA512";

        case 11: // '\013'
            return "SHA224";

        case 6: // '\006'
            return "TIGER";

        case 4: // '\004'
        case 7: // '\007'
        default:
            throw new PGPException((new StringBuilder()).append("unknown hash algorithm tag in getDigestName: ").append(hashAlgorithm).toString());
        }
    }

    static String getSignatureName(int keyAlgorithm, int hashAlgorithm)
        throws PGPException
    {
        String encAlg;
        switch(keyAlgorithm)
        {
        case 1: // '\001'
        case 3: // '\003'
            encAlg = "RSA";
            break;

        case 17: // '\021'
            encAlg = "DSA";
            break;

        case 16: // '\020'
        case 20: // '\024'
            encAlg = "ElGamal";
            break;

        default:
            throw new PGPException((new StringBuilder()).append("unknown algorithm tag in signature:").append(keyAlgorithm).toString());
        }
        return (new StringBuilder()).append(getDigestName(hashAlgorithm)).append("with").append(encAlg).toString();
    }

    static String getSymmetricCipherName(int algorithm)
    {
        switch(algorithm)
        {
        case 0: // '\0'
            return null;

        case 2: // '\002'
            return "DESEDE";

        case 1: // '\001'
            return "IDEA";

        case 3: // '\003'
            return "CAST5";

        case 4: // '\004'
            return "Blowfish";

        case 5: // '\005'
            return "SAFER";

        case 6: // '\006'
            return "DES";

        case 7: // '\007'
            return "AES";

        case 8: // '\b'
            return "AES";

        case 9: // '\t'
            return "AES";

        case 10: // '\n'
            return "Twofish";
        }
        throw new IllegalArgumentException((new StringBuilder()).append("unknown symmetric algorithm: ").append(algorithm).toString());
    }

    public static SecretKey makeSymmetricKey(int algorithm, byte keyBytes[])
        throws PGPException
    {
        String algName;
        switch(algorithm)
        {
        case 2: // '\002'
            algName = "DES_EDE";
            break;

        case 1: // '\001'
            algName = "IDEA";
            break;

        case 3: // '\003'
            algName = "CAST5";
            break;

        case 4: // '\004'
            algName = "Blowfish";
            break;

        case 5: // '\005'
            algName = "SAFER";
            break;

        case 6: // '\006'
            algName = "DES";
            break;

        case 7: // '\007'
            algName = "AES";
            break;

        case 8: // '\b'
            algName = "AES";
            break;

        case 9: // '\t'
            algName = "AES";
            break;

        case 10: // '\n'
            algName = "Twofish";
            break;

        default:
            throw new PGPException((new StringBuilder()).append("unknown symmetric algorithm: ").append(algorithm).toString());
        }
        return new SecretKeySpec(keyBytes, algName);
    }
}
