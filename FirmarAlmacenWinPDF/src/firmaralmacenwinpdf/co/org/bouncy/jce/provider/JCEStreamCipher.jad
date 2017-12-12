// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JCEStreamCipher.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.engines.*;
import co.org.bouncy.crypto.modes.CFBBlockCipher;
import co.org.bouncy.crypto.modes.OFBBlockCipher;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.params.ParametersWithIV;
import co.org.bouncy.jcajce.provider.symmetric.util.BCPBEKey;
import co.org.bouncy.jcajce.provider.symmetric.util.PBE;
import java.io.PrintStream;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;

// Referenced classes of package co.org.bouncy.jce.provider:
//            BouncyCastleProvider

public class JCEStreamCipher extends CipherSpi
    implements PBE
{
    public static class Twofish_OFB8 extends JCEStreamCipher
    {

        public Twofish_OFB8()
        {
            super(new OFBBlockCipher(new TwofishEngine(), 8), 128);
        }
    }

    public static class Blowfish_OFB8 extends JCEStreamCipher
    {

        public Blowfish_OFB8()
        {
            super(new OFBBlockCipher(new BlowfishEngine(), 8), 64);
        }
    }

    public static class Skipjack_OFB8 extends JCEStreamCipher
    {

        public Skipjack_OFB8()
        {
            super(new OFBBlockCipher(new SkipjackEngine(), 8), 64);
        }
    }

    public static class DESede_OFB8 extends JCEStreamCipher
    {

        public DESede_OFB8()
        {
            super(new OFBBlockCipher(new DESedeEngine(), 8), 64);
        }
    }

    public static class DES_OFB8 extends JCEStreamCipher
    {

        public DES_OFB8()
        {
            super(new OFBBlockCipher(new DESEngine(), 8), 64);
        }
    }

    public static class Twofish_CFB8 extends JCEStreamCipher
    {

        public Twofish_CFB8()
        {
            super(new CFBBlockCipher(new TwofishEngine(), 8), 128);
        }
    }

    public static class Blowfish_CFB8 extends JCEStreamCipher
    {

        public Blowfish_CFB8()
        {
            super(new CFBBlockCipher(new BlowfishEngine(), 8), 64);
        }
    }

    public static class Skipjack_CFB8 extends JCEStreamCipher
    {

        public Skipjack_CFB8()
        {
            super(new CFBBlockCipher(new SkipjackEngine(), 8), 64);
        }
    }

    public static class DESede_CFB8 extends JCEStreamCipher
    {

        public DESede_CFB8()
        {
            super(new CFBBlockCipher(new DESedeEngine(), 8), 64);
        }
    }

    public static class DES_CFB8 extends JCEStreamCipher
    {

        public DES_CFB8()
        {
            super(new CFBBlockCipher(new DESEngine(), 8), 64);
        }
    }


    protected JCEStreamCipher(StreamCipher engine, int ivLength)
    {
        this.ivLength = 0;
        pbeSpec = null;
        pbeAlgorithm = null;
        cipher = engine;
        this.ivLength = ivLength;
    }

    protected JCEStreamCipher(BlockCipher engine, int ivLength)
    {
        this.ivLength = 0;
        pbeSpec = null;
        pbeAlgorithm = null;
        this.ivLength = ivLength;
        cipher = new StreamBlockCipher(engine);
    }

    protected int engineGetBlockSize()
    {
        return 0;
    }

    protected byte[] engineGetIV()
    {
        return ivParam == null ? null : ivParam.getIV();
    }

    protected int engineGetKeySize(Key key)
    {
        return key.getEncoded().length * 8;
    }

    protected int engineGetOutputSize(int inputLen)
    {
        return inputLen;
    }

    protected AlgorithmParameters engineGetParameters()
    {
        if(this.engineParams == null && pbeSpec != null)
            try
            {
                AlgorithmParameters engineParams = AlgorithmParameters.getInstance(pbeAlgorithm, "BC");
                engineParams.init(pbeSpec);
                return engineParams;
            }
            catch(Exception e)
            {
                return null;
            }
        else
            return this.engineParams;
    }

    protected void engineSetMode(String mode)
    {
        if(!mode.equalsIgnoreCase("ECB"))
            throw new IllegalArgumentException((new StringBuilder()).append("can't support mode ").append(mode).toString());
        else
            return;
    }

    protected void engineSetPadding(String padding)
        throws NoSuchPaddingException
    {
        if(!padding.equalsIgnoreCase("NoPadding"))
            throw new NoSuchPaddingException((new StringBuilder()).append("Padding ").append(padding).append(" unknown.").toString());
        else
            return;
    }

    protected void engineInit(int opmode, Key key, AlgorithmParameterSpec params, SecureRandom random)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        pbeSpec = null;
        pbeAlgorithm = null;
        engineParams = null;
        if(!(key instanceof SecretKey))
            throw new InvalidKeyException((new StringBuilder()).append("Key for algorithm ").append(key.getAlgorithm()).append(" not suitable for symmetric enryption.").toString());
        CipherParameters param;
        if(key instanceof BCPBEKey)
        {
            BCPBEKey k = (BCPBEKey)key;
            if(k.getOID() != null)
                pbeAlgorithm = k.getOID().getId();
            else
                pbeAlgorithm = k.getAlgorithm();
            if(k.getParam() != null)
            {
                param = k.getParam();
                pbeSpec = new PBEParameterSpec(k.getSalt(), k.getIterationCount());
            } else
            if(params instanceof PBEParameterSpec)
            {
                param = co.org.bouncy.jcajce.provider.symmetric.util.PBE.Util.makePBEParameters(k, params, cipher.getAlgorithmName());
                pbeSpec = (PBEParameterSpec)params;
            } else
            {
                throw new InvalidAlgorithmParameterException("PBE requires PBE parameters to be set.");
            }
            if(k.getIvSize() != 0)
                ivParam = (ParametersWithIV)param;
        } else
        if(params == null)
            param = new KeyParameter(key.getEncoded());
        else
        if(params instanceof IvParameterSpec)
        {
            param = new ParametersWithIV(new KeyParameter(key.getEncoded()), ((IvParameterSpec)params).getIV());
            ivParam = (ParametersWithIV)param;
        } else
        {
            throw new IllegalArgumentException("unknown parameter type.");
        }
        if(ivLength != 0 && !(param instanceof ParametersWithIV))
        {
            SecureRandom ivRandom = random;
            if(ivRandom == null)
                ivRandom = new SecureRandom();
            if(opmode == 1 || opmode == 3)
            {
                byte iv[] = new byte[ivLength];
                ivRandom.nextBytes(iv);
                param = new ParametersWithIV(param, iv);
                ivParam = (ParametersWithIV)param;
            } else
            {
                throw new InvalidAlgorithmParameterException("no IV set when one expected");
            }
        }
        switch(opmode)
        {
        case 1: // '\001'
        case 3: // '\003'
            cipher.init(true, param);
            break;

        case 2: // '\002'
        case 4: // '\004'
            cipher.init(false, param);
            break;

        default:
            System.out.println("eeek!");
            break;
        }
    }

    protected void engineInit(int opmode, Key key, AlgorithmParameters params, SecureRandom random)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        AlgorithmParameterSpec paramSpec = null;
        if(params != null)
        {
            int i = 0;
            do
            {
                if(i == availableSpecs.length)
                    break;
                try
                {
                    paramSpec = params.getParameterSpec(availableSpecs[i]);
                    break;
                }
                catch(Exception e)
                {
                    i++;
                }
            } while(true);
            if(paramSpec == null)
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("can't handle parameter ").append(params.toString()).toString());
        }
        engineInit(opmode, key, paramSpec, random);
        engineParams = params;
    }

    protected void engineInit(int opmode, Key key, SecureRandom random)
        throws InvalidKeyException
    {
        try
        {
            engineInit(opmode, key, (AlgorithmParameterSpec)null, random);
        }
        catch(InvalidAlgorithmParameterException e)
        {
            throw new InvalidKeyException(e.getMessage());
        }
    }

    protected byte[] engineUpdate(byte input[], int inputOffset, int inputLen)
    {
        byte out[] = new byte[inputLen];
        cipher.processBytes(input, inputOffset, inputLen, out, 0);
        return out;
    }

    protected int engineUpdate(byte input[], int inputOffset, int inputLen, byte output[], int outputOffset)
        throws ShortBufferException
    {
        try
        {
            cipher.processBytes(input, inputOffset, inputLen, output, outputOffset);
            return inputLen;
        }
        catch(DataLengthException e)
        {
            throw new ShortBufferException(e.getMessage());
        }
    }

    protected byte[] engineDoFinal(byte input[], int inputOffset, int inputLen)
        throws BadPaddingException, IllegalBlockSizeException
    {
        if(inputLen != 0)
        {
            byte out[] = engineUpdate(input, inputOffset, inputLen);
            cipher.reset();
            return out;
        } else
        {
            cipher.reset();
            return new byte[0];
        }
    }

    protected int engineDoFinal(byte input[], int inputOffset, int inputLen, byte output[], int outputOffset)
        throws BadPaddingException
    {
        if(inputLen != 0)
            cipher.processBytes(input, inputOffset, inputLen, output, outputOffset);
        cipher.reset();
        return inputLen;
    }

    protected byte[] engineWrap(Key key)
        throws IllegalBlockSizeException, InvalidKeyException
    {
        byte encoded[] = key.getEncoded();
        if(encoded == null)
            throw new InvalidKeyException("Cannot wrap key, null encoding.");
        try
        {
            return engineDoFinal(encoded, 0, encoded.length);
        }
        catch(BadPaddingException e)
        {
            throw new IllegalBlockSizeException(e.getMessage());
        }
    }

    protected Key engineUnwrap(byte wrappedKey[], String wrappedKeyAlgorithm, int wrappedKeyType)
        throws InvalidKeyException
    {
        byte encoded[];
        try
        {
            encoded = engineDoFinal(wrappedKey, 0, wrappedKey.length);
        }
        catch(BadPaddingException e)
        {
            throw new InvalidKeyException(e.getMessage());
        }
        catch(IllegalBlockSizeException e2)
        {
            throw new InvalidKeyException(e2.getMessage());
        }
        if(wrappedKeyType == 3)
            return new SecretKeySpec(encoded, wrappedKeyAlgorithm);
        if(!wrappedKeyAlgorithm.equals("") || wrappedKeyType != 2)
            break MISSING_BLOCK_LABEL_146;
        PrivateKeyInfo in;
        try
        {
            in = PrivateKeyInfo.getInstance(encoded);
            PrivateKey privKey = BouncyCastleProvider.getPrivateKey(in);
            if(privKey != null)
                return privKey;
        }
        catch(Exception e)
        {
            throw new InvalidKeyException("Invalid key encoding.");
        }
        throw new InvalidKeyException((new StringBuilder()).append("algorithm ").append(in.getPrivateKeyAlgorithm().getAlgorithm()).append(" not supported").toString());
        KeyFactory kf;
        try
        {
            kf = KeyFactory.getInstance(wrappedKeyAlgorithm, "BC");
            if(wrappedKeyType == 1)
                return kf.generatePublic(new X509EncodedKeySpec(encoded));
        }
        catch(NoSuchProviderException e)
        {
            throw new InvalidKeyException((new StringBuilder()).append("Unknown key type ").append(e.getMessage()).toString());
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new InvalidKeyException((new StringBuilder()).append("Unknown key type ").append(e.getMessage()).toString());
        }
        catch(InvalidKeySpecException e2)
        {
            throw new InvalidKeyException((new StringBuilder()).append("Unknown key type ").append(e2.getMessage()).toString());
        }
        if(wrappedKeyType == 2)
            return kf.generatePrivate(new PKCS8EncodedKeySpec(encoded));
        throw new InvalidKeyException((new StringBuilder()).append("Unknown key type ").append(wrappedKeyType).toString());
    }

    private Class availableSpecs[] = {
        javax/crypto/spec/RC2ParameterSpec, javax/crypto/spec/RC5ParameterSpec, javax/crypto/spec/IvParameterSpec, javax/crypto/spec/PBEParameterSpec
    };
    private StreamCipher cipher;
    private ParametersWithIV ivParam;
    private int ivLength;
    private PBEParameterSpec pbeSpec;
    private String pbeAlgorithm;
    private AlgorithmParameters engineParams;
}
