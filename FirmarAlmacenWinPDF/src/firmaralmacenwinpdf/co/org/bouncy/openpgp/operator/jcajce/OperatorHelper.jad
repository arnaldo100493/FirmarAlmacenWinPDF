// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorHelper.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.jcajce.JcaJceHelper;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.PGPDataDecryptor;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import java.io.InputStream;
import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            PGPUtil, SHA1PGPDigestCalculator

class OperatorHelper
{

    OperatorHelper(JcaJceHelper helper)
    {
        this.helper = helper;
    }

    MessageDigest createDigest(int algorithm)
        throws GeneralSecurityException, PGPException
    {
        MessageDigest dig = helper.createDigest(PGPUtil.getDigestName(algorithm));
        return dig;
    }

    KeyFactory createKeyFactory(String algorithm)
        throws GeneralSecurityException, PGPException
    {
        return helper.createKeyFactory(algorithm);
    }

    PGPDataDecryptor createDataDecryptor(boolean withIntegrityPacket, int encAlgorithm, byte key[])
        throws PGPException
    {
        try
        {
            javax.crypto.SecretKey secretKey = new SecretKeySpec(key, PGPUtil.getSymmetricCipherName(encAlgorithm));
            final Cipher c = createStreamCipher(encAlgorithm, withIntegrityPacket);
            byte iv[] = new byte[c.getBlockSize()];
            c.init(2, secretKey, new IvParameterSpec(iv));
            return new PGPDataDecryptor() {

                public InputStream getInputStream(InputStream in)
                {
                    return new CipherInputStream(in, c);
                }

                public int getBlockSize()
                {
                    return c.getBlockSize();
                }

                public PGPDigestCalculator getIntegrityCalculator()
                {
                    return new SHA1PGPDigestCalculator();
                }

                final Cipher val$c;
                final OperatorHelper this$0;

            
            {
                this$0 = OperatorHelper.this;
                c = cipher;
                super();
            }
            }
;
        }
        catch(PGPException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new PGPException("Exception creating cipher", e);
        }
    }

    Cipher createStreamCipher(int encAlgorithm, boolean withIntegrityPacket)
        throws PGPException
    {
        String mode = withIntegrityPacket ? "CFB" : "OpenPGPCFB";
        String cName = (new StringBuilder()).append(PGPUtil.getSymmetricCipherName(encAlgorithm)).append("/").append(mode).append("/NoPadding").toString();
        return createCipher(cName);
    }

    Cipher createCipher(String cipherName)
        throws PGPException
    {
        try
        {
            return helper.createCipher(cipherName);
        }
        catch(GeneralSecurityException e)
        {
            throw new PGPException((new StringBuilder()).append("cannot create cipher: ").append(e.getMessage()).toString(), e);
        }
    }

    Cipher createPublicKeyCipher(int encAlgorithm)
        throws PGPException
    {
        switch(encAlgorithm)
        {
        case 1: // '\001'
        case 2: // '\002'
            return createCipher("RSA/ECB/PKCS1Padding");

        case 16: // '\020'
        case 20: // '\024'
            return createCipher("ElGamal/ECB/PKCS1Padding");

        case 17: // '\021'
            throw new PGPException("Can't use DSA for encryption.");

        case 19: // '\023'
            throw new PGPException("Can't use ECDSA for encryption.");

        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 18: // '\022'
        default:
            throw new PGPException((new StringBuilder()).append("unknown asymmetric algorithm: ").append(encAlgorithm).toString());
        }
    }

    private Signature createSignature(String cipherName)
        throws PGPException
    {
        try
        {
            return helper.createSignature(cipherName);
        }
        catch(GeneralSecurityException e)
        {
            throw new PGPException((new StringBuilder()).append("cannot create signature: ").append(e.getMessage()).toString(), e);
        }
    }

    public Signature createSignature(int keyAlgorithm, int hashAlgorithm)
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
        return createSignature((new StringBuilder()).append(PGPUtil.getDigestName(hashAlgorithm)).append("with").append(encAlg).toString());
    }

    private JcaJceHelper helper;
}
