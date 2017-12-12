// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceKobaraImaiCipherSpi.java

package co.org.bouncy.pqc.jcajce.provider.mceliece;

import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x509.X509ObjectIdentifiers;
import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.*;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.pqc.crypto.mceliece.McElieceCCA2KeyParameters;
import co.org.bouncy.pqc.crypto.mceliece.McElieceKobaraImaiCipher;
import co.org.bouncy.pqc.jcajce.provider.util.AsymmetricHybridCipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

// Referenced classes of package co.org.bouncy.pqc.jcajce.provider.mceliece:
//            McElieceCCA2KeysToParams

public class McElieceKobaraImaiCipherSpi extends AsymmetricHybridCipher
    implements PKCSObjectIdentifiers, X509ObjectIdentifiers
{
    public static class McElieceKobaraImai512 extends McElieceKobaraImaiCipherSpi
    {

        public McElieceKobaraImai512()
        {
            super(new SHA512Digest(), new McElieceKobaraImaiCipher());
        }
    }

    public static class McElieceKobaraImai384 extends McElieceKobaraImaiCipherSpi
    {

        public McElieceKobaraImai384()
        {
            super(new SHA384Digest(), new McElieceKobaraImaiCipher());
        }
    }

    public static class McElieceKobaraImai256 extends McElieceKobaraImaiCipherSpi
    {

        public McElieceKobaraImai256()
        {
            super(new SHA256Digest(), new McElieceKobaraImaiCipher());
        }
    }

    public static class McElieceKobaraImai224 extends McElieceKobaraImaiCipherSpi
    {

        public McElieceKobaraImai224()
        {
            super(new SHA224Digest(), new McElieceKobaraImaiCipher());
        }
    }

    public static class McElieceKobaraImai extends McElieceKobaraImaiCipherSpi
    {

        public McElieceKobaraImai()
        {
            super(new SHA1Digest(), new McElieceKobaraImaiCipher());
        }
    }


    public McElieceKobaraImaiCipherSpi()
    {
        buf = new ByteArrayOutputStream();
        buf = new ByteArrayOutputStream();
    }

    protected McElieceKobaraImaiCipherSpi(Digest digest, McElieceKobaraImaiCipher cipher)
    {
        buf = new ByteArrayOutputStream();
        this.digest = digest;
        this.cipher = cipher;
        buf = new ByteArrayOutputStream();
    }

    public byte[] update(byte input[], int inOff, int inLen)
    {
        buf.write(input, inOff, inLen);
        return new byte[0];
    }

    public byte[] doFinal(byte input[], int inOff, int inLen)
        throws BadPaddingException
    {
        update(input, inOff, inLen);
        if(opMode == 1)
            try
            {
                return cipher.messageEncrypt(pad());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        else
        if(opMode == 2)
        {
            byte inputOfDecr[] = buf.toByteArray();
            buf.reset();
            try
            {
                return unpad(cipher.messageDecrypt(inputOfDecr));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected int encryptOutputSize(int inLen)
    {
        return 0;
    }

    protected int decryptOutputSize(int inLen)
    {
        return 0;
    }

    protected void initCipherEncrypt(Key key, AlgorithmParameterSpec params, SecureRandom sr)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        buf.reset();
        co.org.bouncy.crypto.CipherParameters param = McElieceCCA2KeysToParams.generatePublicKeyParameter((PublicKey)key);
        param = new ParametersWithRandom(param, sr);
        digest.reset();
        cipher.init(true, param);
    }

    protected void initCipherDecrypt(Key key, AlgorithmParameterSpec params)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        buf.reset();
        co.org.bouncy.crypto.CipherParameters param = McElieceCCA2KeysToParams.generatePrivateKeyParameter((PrivateKey)key);
        digest.reset();
        cipher.init(false, param);
    }

    public String getName()
    {
        return "McElieceKobaraImaiCipher";
    }

    public int getKeySize(Key key)
        throws InvalidKeyException
    {
        if(key instanceof PublicKey)
        {
            McElieceCCA2KeyParameters mcElieceCCA2KeyParameters = (McElieceCCA2KeyParameters)McElieceCCA2KeysToParams.generatePublicKeyParameter((PublicKey)key);
            return cipher.getKeySize(mcElieceCCA2KeyParameters);
        }
        if(key instanceof PrivateKey)
        {
            McElieceCCA2KeyParameters mcElieceCCA2KeyParameters = (McElieceCCA2KeyParameters)McElieceCCA2KeysToParams.generatePrivateKeyParameter((PrivateKey)key);
            return cipher.getKeySize(mcElieceCCA2KeyParameters);
        } else
        {
            throw new InvalidKeyException();
        }
    }

    private byte[] pad()
    {
        buf.write(1);
        byte result[] = buf.toByteArray();
        buf.reset();
        return result;
    }

    private byte[] unpad(byte pmBytes[])
        throws BadPaddingException
    {
        int index;
        for(index = pmBytes.length - 1; index >= 0 && pmBytes[index] == 0; index--);
        if(pmBytes[index] != 1)
        {
            throw new BadPaddingException("invalid ciphertext");
        } else
        {
            byte mBytes[] = new byte[index];
            System.arraycopy(pmBytes, 0, mBytes, 0, index);
            return mBytes;
        }
    }

    public byte[] messageEncrypt()
        throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException
    {
        byte output[] = null;
        try
        {
            output = cipher.messageEncrypt(pad());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return output;
    }

    public byte[] messageDecrypt()
        throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException
    {
        byte output[] = null;
        byte inputOfDecr[] = buf.toByteArray();
        buf.reset();
        try
        {
            output = unpad(cipher.messageDecrypt(inputOfDecr));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return output;
    }

    private Digest digest;
    private McElieceKobaraImaiCipher cipher;
    private ByteArrayOutputStream buf;
}
