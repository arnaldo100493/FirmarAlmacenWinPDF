// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceFujisakiCipherSpi.java

package co.org.bouncy.pqc.jcajce.provider.mceliece;

import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x509.X509ObjectIdentifiers;
import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.*;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.pqc.crypto.mceliece.McElieceCCA2KeyParameters;
import co.org.bouncy.pqc.crypto.mceliece.McElieceFujisakiCipher;
import co.org.bouncy.pqc.jcajce.provider.util.AsymmetricHybridCipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

// Referenced classes of package co.org.bouncy.pqc.jcajce.provider.mceliece:
//            McElieceCCA2KeysToParams

public class McElieceFujisakiCipherSpi extends AsymmetricHybridCipher
    implements PKCSObjectIdentifiers, X509ObjectIdentifiers
{
    public static class McElieceFujisaki512 extends McElieceFujisakiCipherSpi
    {

        public McElieceFujisaki512()
        {
            super(new SHA512Digest(), new McElieceFujisakiCipher());
        }
    }

    public static class McElieceFujisaki384 extends McElieceFujisakiCipherSpi
    {

        public McElieceFujisaki384()
        {
            super(new SHA384Digest(), new McElieceFujisakiCipher());
        }
    }

    public static class McElieceFujisaki256 extends McElieceFujisakiCipherSpi
    {

        public McElieceFujisaki256()
        {
            super(new SHA256Digest(), new McElieceFujisakiCipher());
        }
    }

    public static class McElieceFujisaki224 extends McElieceFujisakiCipherSpi
    {

        public McElieceFujisaki224()
        {
            super(new SHA224Digest(), new McElieceFujisakiCipher());
        }
    }

    public static class McElieceFujisaki extends McElieceFujisakiCipherSpi
    {

        public McElieceFujisaki()
        {
            super(new SHA1Digest(), new McElieceFujisakiCipher());
        }
    }


    protected McElieceFujisakiCipherSpi(Digest digest, McElieceFujisakiCipher cipher)
    {
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
        byte data[] = buf.toByteArray();
        buf.reset();
        if(opMode == 1)
            try
            {
                return cipher.messageEncrypt(data);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        else
        if(opMode == 2)
            try
            {
                return cipher.messageDecrypt(data);
            }
            catch(Exception e)
            {
                e.printStackTrace();
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
        co.org.bouncy.crypto.CipherParameters param = McElieceCCA2KeysToParams.generatePublicKeyParameter((PublicKey)key);
        param = new ParametersWithRandom(param, sr);
        digest.reset();
        cipher.init(true, param);
    }

    protected void initCipherDecrypt(Key key, AlgorithmParameterSpec params)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        co.org.bouncy.crypto.CipherParameters param = McElieceCCA2KeysToParams.generatePrivateKeyParameter((PrivateKey)key);
        digest.reset();
        cipher.init(false, param);
    }

    public String getName()
    {
        return "McElieceFujisakiCipher";
    }

    public int getKeySize(Key key)
        throws InvalidKeyException
    {
        McElieceCCA2KeyParameters mcElieceCCA2KeyParameters;
        if(key instanceof PublicKey)
            mcElieceCCA2KeyParameters = (McElieceCCA2KeyParameters)McElieceCCA2KeysToParams.generatePublicKeyParameter((PublicKey)key);
        else
            mcElieceCCA2KeyParameters = (McElieceCCA2KeyParameters)McElieceCCA2KeysToParams.generatePrivateKeyParameter((PrivateKey)key);
        return cipher.getKeySize(mcElieceCCA2KeyParameters);
    }

    public byte[] messageEncrypt(byte input[])
        throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException
    {
        byte output[] = null;
        try
        {
            output = cipher.messageEncrypt(input);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return output;
    }

    public byte[] messageDecrypt(byte input[])
        throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException
    {
        byte output[] = null;
        try
        {
            output = cipher.messageDecrypt(input);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return output;
    }

    private Digest digest;
    private McElieceFujisakiCipher cipher;
    private ByteArrayOutputStream buf;
}
