// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BrokenJCEBlockCipher.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.engines.*;
import co.org.bouncy.crypto.modes.*;
import co.org.bouncy.crypto.paddings.PaddedBufferedBlockCipher;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.jcajce.provider.symmetric.util.BCPBEKey;
import co.org.bouncy.util.Strings;
import java.io.PrintStream;
import java.security.*;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;

// Referenced classes of package co.org.bouncy.jce.provider:
//            BrokenPBE

public class BrokenJCEBlockCipher
    implements BrokenPBE
{
    public static class OldPBEWithSHAAndTwofish extends BrokenJCEBlockCipher
    {

        public OldPBEWithSHAAndTwofish()
        {
            super(new CBCBlockCipher(new TwofishEngine()), 3, 1, 256, 128);
        }
    }

    public static class BrokePBEWithSHAAndDES2Key extends BrokenJCEBlockCipher
    {

        public BrokePBEWithSHAAndDES2Key()
        {
            super(new CBCBlockCipher(new DESedeEngine()), 2, 1, 128, 64);
        }
    }

    public static class OldPBEWithSHAAndDES3Key extends BrokenJCEBlockCipher
    {

        public OldPBEWithSHAAndDES3Key()
        {
            super(new CBCBlockCipher(new DESedeEngine()), 3, 1, 192, 64);
        }
    }

    public static class BrokePBEWithSHAAndDES3Key extends BrokenJCEBlockCipher
    {

        public BrokePBEWithSHAAndDES3Key()
        {
            super(new CBCBlockCipher(new DESedeEngine()), 2, 1, 192, 64);
        }
    }

    public static class BrokePBEWithSHA1AndDES extends BrokenJCEBlockCipher
    {

        public BrokePBEWithSHA1AndDES()
        {
            super(new CBCBlockCipher(new DESEngine()), 0, 1, 64, 64);
        }
    }

    public static class BrokePBEWithMD5AndDES extends BrokenJCEBlockCipher
    {

        public BrokePBEWithMD5AndDES()
        {
            super(new CBCBlockCipher(new DESEngine()), 0, 0, 64, 64);
        }
    }


    protected BrokenJCEBlockCipher(BlockCipher engine)
    {
        pbeType = 2;
        pbeHash = 1;
        ivLength = 0;
        engineParams = null;
        cipher = new PaddedBufferedBlockCipher(engine);
    }

    protected BrokenJCEBlockCipher(BlockCipher engine, int pbeType, int pbeHash, int pbeKeySize, int pbeIvSize)
    {
        this.pbeType = 2;
        this.pbeHash = 1;
        ivLength = 0;
        engineParams = null;
        cipher = new PaddedBufferedBlockCipher(engine);
        this.pbeType = pbeType;
        this.pbeHash = pbeHash;
        this.pbeKeySize = pbeKeySize;
        this.pbeIvSize = pbeIvSize;
    }

    protected int engineGetBlockSize()
    {
        return cipher.getBlockSize();
    }

    protected byte[] engineGetIV()
    {
        return ivParam == null ? null : ivParam.getIV();
    }

    protected int engineGetKeySize(Key key)
    {
        return key.getEncoded().length;
    }

    protected int engineGetOutputSize(int inputLen)
    {
        return cipher.getOutputSize(inputLen);
    }

    protected AlgorithmParameters engineGetParameters()
    {
        if(engineParams == null && ivParam != null)
        {
            String name = cipher.getUnderlyingCipher().getAlgorithmName();
            if(name.indexOf('/') >= 0)
                name = name.substring(0, name.indexOf('/'));
            try
            {
                engineParams = AlgorithmParameters.getInstance(name, "BC");
                engineParams.init(ivParam.getIV());
            }
            catch(Exception e)
            {
                throw new RuntimeException(e.toString());
            }
        }
        return engineParams;
    }

    protected void engineSetMode(String mode)
    {
        String modeName = Strings.toUpperCase(mode);
        if(modeName.equals("ECB"))
        {
            ivLength = 0;
            cipher = new PaddedBufferedBlockCipher(cipher.getUnderlyingCipher());
        } else
        if(modeName.equals("CBC"))
        {
            ivLength = cipher.getUnderlyingCipher().getBlockSize();
            cipher = new PaddedBufferedBlockCipher(new CBCBlockCipher(cipher.getUnderlyingCipher()));
        } else
        if(modeName.startsWith("OFB"))
        {
            ivLength = cipher.getUnderlyingCipher().getBlockSize();
            if(modeName.length() != 3)
            {
                int wordSize = Integer.parseInt(modeName.substring(3));
                cipher = new PaddedBufferedBlockCipher(new OFBBlockCipher(cipher.getUnderlyingCipher(), wordSize));
            } else
            {
                cipher = new PaddedBufferedBlockCipher(new OFBBlockCipher(cipher.getUnderlyingCipher(), 8 * cipher.getBlockSize()));
            }
        } else
        if(modeName.startsWith("CFB"))
        {
            ivLength = cipher.getUnderlyingCipher().getBlockSize();
            if(modeName.length() != 3)
            {
                int wordSize = Integer.parseInt(modeName.substring(3));
                cipher = new PaddedBufferedBlockCipher(new CFBBlockCipher(cipher.getUnderlyingCipher(), wordSize));
            } else
            {
                cipher = new PaddedBufferedBlockCipher(new CFBBlockCipher(cipher.getUnderlyingCipher(), 8 * cipher.getBlockSize()));
            }
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("can't support mode ").append(mode).toString());
        }
    }

    protected void engineSetPadding(String padding)
        throws NoSuchPaddingException
    {
        String paddingName = Strings.toUpperCase(padding);
        if(paddingName.equals("NOPADDING"))
            cipher = new BufferedBlockCipher(cipher.getUnderlyingCipher());
        else
        if(paddingName.equals("PKCS5PADDING") || paddingName.equals("PKCS7PADDING") || paddingName.equals("ISO10126PADDING"))
            cipher = new PaddedBufferedBlockCipher(cipher.getUnderlyingCipher());
        else
        if(paddingName.equals("WITHCTS"))
            cipher = new CTSBlockCipher(cipher.getUnderlyingCipher());
        else
            throw new NoSuchPaddingException((new StringBuilder()).append("Padding ").append(padding).append(" unknown.").toString());
    }

    protected void engineInit(int opmode, Key key, AlgorithmParameterSpec params, SecureRandom random)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        CipherParameters param;
        if(key instanceof BCPBEKey)
        {
            param = BrokenPBE.Util.makePBEParameters((BCPBEKey)key, params, pbeType, pbeHash, cipher.getUnderlyingCipher().getAlgorithmName(), pbeKeySize, pbeIvSize);
            if(pbeIvSize != 0)
                ivParam = (ParametersWithIV)param;
        } else
        if(params == null)
            param = new KeyParameter(key.getEncoded());
        else
        if(params instanceof IvParameterSpec)
        {
            if(ivLength != 0)
            {
                param = new ParametersWithIV(new KeyParameter(key.getEncoded()), ((IvParameterSpec)params).getIV());
                ivParam = (ParametersWithIV)param;
            } else
            {
                param = new KeyParameter(key.getEncoded());
            }
        } else
        if(params instanceof RC2ParameterSpec)
        {
            RC2ParameterSpec rc2Param = (RC2ParameterSpec)params;
            param = new RC2Parameters(key.getEncoded(), ((RC2ParameterSpec)params).getEffectiveKeyBits());
            if(rc2Param.getIV() != null && ivLength != 0)
            {
                param = new ParametersWithIV(param, rc2Param.getIV());
                ivParam = (ParametersWithIV)param;
            }
        } else
        if(params instanceof RC5ParameterSpec)
        {
            RC5ParameterSpec rc5Param = (RC5ParameterSpec)params;
            param = new RC5Parameters(key.getEncoded(), ((RC5ParameterSpec)params).getRounds());
            if(rc5Param.getWordSize() != 32)
                throw new IllegalArgumentException("can only accept RC5 word size 32 (at the moment...)");
            if(rc5Param.getIV() != null && ivLength != 0)
            {
                param = new ParametersWithIV(param, rc5Param.getIV());
                ivParam = (ParametersWithIV)param;
            }
        } else
        {
            throw new InvalidAlgorithmParameterException("unknown parameter type.");
        }
        if(ivLength != 0 && !(param instanceof ParametersWithIV))
        {
            if(random == null)
                random = new SecureRandom();
            if(opmode == 1 || opmode == 3)
            {
                byte iv[] = new byte[ivLength];
                random.nextBytes(iv);
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
        engineParams = params;
        engineInit(opmode, key, paramSpec, random);
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
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    protected byte[] engineUpdate(byte input[], int inputOffset, int inputLen)
    {
        int length = cipher.getUpdateOutputSize(inputLen);
        if(length > 0)
        {
            byte out[] = new byte[length];
            cipher.processBytes(input, inputOffset, inputLen, out, 0);
            return out;
        } else
        {
            cipher.processBytes(input, inputOffset, inputLen, null, 0);
            return null;
        }
    }

    protected int engineUpdate(byte input[], int inputOffset, int inputLen, byte output[], int outputOffset)
    {
        return cipher.processBytes(input, inputOffset, inputLen, output, outputOffset);
    }

    protected byte[] engineDoFinal(byte input[], int inputOffset, int inputLen)
        throws IllegalBlockSizeException, BadPaddingException
    {
        int len = 0;
        byte tmp[] = new byte[engineGetOutputSize(inputLen)];
        if(inputLen != 0)
            len = cipher.processBytes(input, inputOffset, inputLen, tmp, 0);
        try
        {
            len += cipher.doFinal(tmp, len);
        }
        catch(DataLengthException e)
        {
            throw new IllegalBlockSizeException(e.getMessage());
        }
        catch(InvalidCipherTextException e)
        {
            throw new BadPaddingException(e.getMessage());
        }
        byte out[] = new byte[len];
        System.arraycopy(tmp, 0, out, 0, len);
        return out;
    }

    protected int engineDoFinal(byte input[], int inputOffset, int inputLen, byte output[], int outputOffset)
        throws IllegalBlockSizeException, BadPaddingException
    {
        int len = 0;
        if(inputLen != 0)
            len = cipher.processBytes(input, inputOffset, inputLen, output, outputOffset);
        try
        {
            return len + cipher.doFinal(output, outputOffset + len);
        }
        catch(DataLengthException e)
        {
            throw new IllegalBlockSizeException(e.getMessage());
        }
        catch(InvalidCipherTextException e)
        {
            throw new BadPaddingException(e.getMessage());
        }
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
        byte encoded[] = null;
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
        javax/crypto/spec/IvParameterSpec, javax/crypto/spec/PBEParameterSpec, javax/crypto/spec/RC2ParameterSpec, javax/crypto/spec/RC5ParameterSpec
    };
    private BufferedBlockCipher cipher;
    private ParametersWithIV ivParam;
    private int pbeType;
    private int pbeHash;
    private int pbeKeySize;
    private int pbeIvSize;
    private int ivLength;
    private AlgorithmParameters engineParams;
}
