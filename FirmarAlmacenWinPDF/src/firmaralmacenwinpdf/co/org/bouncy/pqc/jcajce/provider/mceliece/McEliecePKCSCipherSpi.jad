// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McEliecePKCSCipherSpi.java

package co.org.bouncy.pqc.jcajce.provider.mceliece;

import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x509.X509ObjectIdentifiers;
import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.*;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.pqc.crypto.mceliece.McElieceKeyParameters;
import co.org.bouncy.pqc.crypto.mceliece.McEliecePKCSCipher;
import co.org.bouncy.pqc.jcajce.provider.util.AsymmetricBlockCipher;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

// Referenced classes of package co.org.bouncy.pqc.jcajce.provider.mceliece:
//            McElieceKeysToParams

public class McEliecePKCSCipherSpi extends AsymmetricBlockCipher
    implements PKCSObjectIdentifiers, X509ObjectIdentifiers
{
    public static class McEliecePKCS512 extends McEliecePKCSCipherSpi
    {

        public McEliecePKCS512()
        {
            super(new SHA512Digest(), new McEliecePKCSCipher());
        }
    }

    public static class McEliecePKCS384 extends McEliecePKCSCipherSpi
    {

        public McEliecePKCS384()
        {
            super(new SHA384Digest(), new McEliecePKCSCipher());
        }
    }

    public static class McEliecePKCS256 extends McEliecePKCSCipherSpi
    {

        public McEliecePKCS256()
        {
            super(new SHA256Digest(), new McEliecePKCSCipher());
        }
    }

    public static class McEliecePKCS224 extends McEliecePKCSCipherSpi
    {

        public McEliecePKCS224()
        {
            super(new SHA224Digest(), new McEliecePKCSCipher());
        }
    }

    public static class McEliecePKCS extends McEliecePKCSCipherSpi
    {

        public McEliecePKCS()
        {
            super(new SHA1Digest(), new McEliecePKCSCipher());
        }
    }


    public McEliecePKCSCipherSpi(Digest digest, McEliecePKCSCipher cipher)
    {
        this.digest = digest;
        this.cipher = cipher;
    }

    protected void initCipherEncrypt(Key key, AlgorithmParameterSpec params, SecureRandom sr)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        co.org.bouncy.crypto.CipherParameters param = McElieceKeysToParams.generatePublicKeyParameter((PublicKey)key);
        param = new ParametersWithRandom(param, sr);
        digest.reset();
        cipher.init(true, param);
        maxPlainTextSize = cipher.maxPlainTextSize;
        cipherTextSize = cipher.cipherTextSize;
    }

    protected void initCipherDecrypt(Key key, AlgorithmParameterSpec params)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        co.org.bouncy.crypto.CipherParameters param = McElieceKeysToParams.generatePrivateKeyParameter((PrivateKey)key);
        digest.reset();
        cipher.init(false, param);
        maxPlainTextSize = cipher.maxPlainTextSize;
        cipherTextSize = cipher.cipherTextSize;
    }

    protected byte[] messageEncrypt(byte input[])
        throws IllegalBlockSizeException, BadPaddingException
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

    protected byte[] messageDecrypt(byte input[])
        throws IllegalBlockSizeException, BadPaddingException
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

    public String getName()
    {
        return "McEliecePKCS";
    }

    public int getKeySize(Key key)
        throws InvalidKeyException
    {
        McElieceKeyParameters mcElieceKeyParameters;
        if(key instanceof PublicKey)
            mcElieceKeyParameters = (McElieceKeyParameters)McElieceKeysToParams.generatePublicKeyParameter((PublicKey)key);
        else
            mcElieceKeyParameters = (McElieceKeyParameters)McElieceKeysToParams.generatePrivateKeyParameter((PrivateKey)key);
        return cipher.getKeySize(mcElieceKeyParameters);
    }

    private Digest digest;
    private McEliecePKCSCipher cipher;
}
