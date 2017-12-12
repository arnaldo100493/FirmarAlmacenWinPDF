// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseBlockCipher.java

package co.org.bouncy.jcajce.provider.symmetric.util;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.modes.*;
import co.org.bouncy.crypto.paddings.*;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.jce.spec.GOST28147ParameterSpec;
import co.org.bouncy.jce.spec.RepeatedSecretKeySpec;
import co.org.bouncy.util.Strings;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.*;
import javax.crypto.spec.*;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric.util:
//            BaseWrapCipher, BCPBEKey, PBE, BlockCipherProvider

public class BaseBlockCipher extends BaseWrapCipher
    implements PBE
{
    private static class AEADGenericBlockCipher
        implements GenericBlockCipher
    {

        public void init(boolean forEncryption, CipherParameters params)
            throws IllegalArgumentException
        {
            cipher.init(forEncryption, params);
        }

        public String getAlgorithmName()
        {
            return cipher.getUnderlyingCipher().getAlgorithmName();
        }

        public boolean wrapOnNoPadding()
        {
            return false;
        }

        public BlockCipher getUnderlyingCipher()
        {
            return cipher.getUnderlyingCipher();
        }

        public int getOutputSize(int len)
        {
            return cipher.getOutputSize(len);
        }

        public int getUpdateOutputSize(int len)
        {
            return cipher.getUpdateOutputSize(len);
        }

        public int processByte(byte in, byte out[], int outOff)
            throws DataLengthException
        {
            return cipher.processByte(in, out, outOff);
        }

        public int processBytes(byte in[], int inOff, int len, byte out[], int outOff)
            throws DataLengthException
        {
            return cipher.processBytes(in, inOff, len, out, outOff);
        }

        public int doFinal(byte out[], int outOff)
            throws IllegalStateException, InvalidCipherTextException
        {
            return cipher.doFinal(out, outOff);
        }

        private AEADBlockCipher cipher;

        AEADGenericBlockCipher(AEADBlockCipher cipher)
        {
            this.cipher = cipher;
        }
    }

    private static class BufferedGenericBlockCipher
        implements GenericBlockCipher
    {

        public void init(boolean forEncryption, CipherParameters params)
            throws IllegalArgumentException
        {
            cipher.init(forEncryption, params);
        }

        public boolean wrapOnNoPadding()
        {
            return !(cipher instanceof CTSBlockCipher);
        }

        public String getAlgorithmName()
        {
            return cipher.getUnderlyingCipher().getAlgorithmName();
        }

        public BlockCipher getUnderlyingCipher()
        {
            return cipher.getUnderlyingCipher();
        }

        public int getOutputSize(int len)
        {
            return cipher.getOutputSize(len);
        }

        public int getUpdateOutputSize(int len)
        {
            return cipher.getUpdateOutputSize(len);
        }

        public int processByte(byte in, byte out[], int outOff)
            throws DataLengthException
        {
            return cipher.processByte(in, out, outOff);
        }

        public int processBytes(byte in[], int inOff, int len, byte out[], int outOff)
            throws DataLengthException
        {
            return cipher.processBytes(in, inOff, len, out, outOff);
        }

        public int doFinal(byte out[], int outOff)
            throws IllegalStateException, InvalidCipherTextException
        {
            return cipher.doFinal(out, outOff);
        }

        private BufferedBlockCipher cipher;

        BufferedGenericBlockCipher(BufferedBlockCipher cipher)
        {
            this.cipher = cipher;
        }

        BufferedGenericBlockCipher(BlockCipher cipher)
        {
            this.cipher = new PaddedBufferedBlockCipher(cipher);
        }

        BufferedGenericBlockCipher(BlockCipher cipher, BlockCipherPadding padding)
        {
            this.cipher = new PaddedBufferedBlockCipher(cipher, padding);
        }
    }

    private static interface GenericBlockCipher
    {

        public abstract void init(boolean flag, CipherParameters cipherparameters)
            throws IllegalArgumentException;

        public abstract boolean wrapOnNoPadding();

        public abstract String getAlgorithmName();

        public abstract BlockCipher getUnderlyingCipher();

        public abstract int getOutputSize(int i);

        public abstract int getUpdateOutputSize(int i);

        public abstract int processByte(byte byte0, byte abyte0[], int i)
            throws DataLengthException;

        public abstract int processBytes(byte abyte0[], int i, int j, byte abyte1[], int k)
            throws DataLengthException;

        public abstract int doFinal(byte abyte0[], int i)
            throws IllegalStateException, InvalidCipherTextException;
    }


    protected BaseBlockCipher(BlockCipher engine)
    {
        ivLength = 0;
        pbeSpec = null;
        pbeAlgorithm = null;
        modeName = null;
        baseEngine = engine;
        cipher = new BufferedGenericBlockCipher(engine);
    }

    protected BaseBlockCipher(BlockCipherProvider provider)
    {
        ivLength = 0;
        pbeSpec = null;
        pbeAlgorithm = null;
        modeName = null;
        baseEngine = provider.get();
        engineProvider = provider;
        cipher = new BufferedGenericBlockCipher(provider.get());
    }

    protected BaseBlockCipher(BlockCipher engine, int ivLength)
    {
        this.ivLength = 0;
        pbeSpec = null;
        pbeAlgorithm = null;
        modeName = null;
        baseEngine = engine;
        cipher = new BufferedGenericBlockCipher(engine);
        this.ivLength = ivLength / 8;
    }

    protected BaseBlockCipher(BufferedBlockCipher engine, int ivLength)
    {
        this.ivLength = 0;
        pbeSpec = null;
        pbeAlgorithm = null;
        modeName = null;
        baseEngine = engine.getUnderlyingCipher();
        cipher = new BufferedGenericBlockCipher(engine);
        this.ivLength = ivLength / 8;
    }

    protected int engineGetBlockSize()
    {
        return baseEngine.getBlockSize();
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
        return cipher.getOutputSize(inputLen);
    }

    protected AlgorithmParameters engineGetParameters()
    {
        if(engineParams == null)
            if(pbeSpec != null)
                try
                {
                    engineParams = AlgorithmParameters.getInstance(pbeAlgorithm, "BC");
                    engineParams.init(pbeSpec);
                }
                catch(Exception e)
                {
                    return null;
                }
            else
            if(ivParam != null)
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
        throws NoSuchAlgorithmException
    {
        modeName = Strings.toUpperCase(mode);
        if(modeName.equals("ECB"))
        {
            ivLength = 0;
            cipher = new BufferedGenericBlockCipher(baseEngine);
        } else
        if(modeName.equals("CBC"))
        {
            ivLength = baseEngine.getBlockSize();
            cipher = new BufferedGenericBlockCipher(new CBCBlockCipher(baseEngine));
        } else
        if(modeName.startsWith("OFB"))
        {
            ivLength = baseEngine.getBlockSize();
            if(modeName.length() != 3)
            {
                int wordSize = Integer.parseInt(modeName.substring(3));
                cipher = new BufferedGenericBlockCipher(new OFBBlockCipher(baseEngine, wordSize));
            } else
            {
                cipher = new BufferedGenericBlockCipher(new OFBBlockCipher(baseEngine, 8 * baseEngine.getBlockSize()));
            }
        } else
        if(modeName.startsWith("CFB"))
        {
            ivLength = baseEngine.getBlockSize();
            if(modeName.length() != 3)
            {
                int wordSize = Integer.parseInt(modeName.substring(3));
                cipher = new BufferedGenericBlockCipher(new CFBBlockCipher(baseEngine, wordSize));
            } else
            {
                cipher = new BufferedGenericBlockCipher(new CFBBlockCipher(baseEngine, 8 * baseEngine.getBlockSize()));
            }
        } else
        if(modeName.startsWith("PGP"))
        {
            boolean inlineIV = modeName.equalsIgnoreCase("PGPCFBwithIV");
            ivLength = baseEngine.getBlockSize();
            cipher = new BufferedGenericBlockCipher(new PGPCFBBlockCipher(baseEngine, inlineIV));
        } else
        if(modeName.equalsIgnoreCase("OpenPGPCFB"))
        {
            ivLength = 0;
            cipher = new BufferedGenericBlockCipher(new OpenPGPCFBBlockCipher(baseEngine));
        } else
        if(modeName.startsWith("SIC"))
        {
            ivLength = baseEngine.getBlockSize();
            if(ivLength < 16)
                throw new IllegalArgumentException("Warning: SIC-Mode can become a twotime-pad if the blocksize of the cipher is too small. Use a cipher with a block size of at least 128 bits (e.g. AES)");
            cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(new SICBlockCipher(baseEngine)));
        } else
        if(modeName.startsWith("CTR"))
        {
            ivLength = baseEngine.getBlockSize();
            cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(new SICBlockCipher(baseEngine)));
        } else
        if(modeName.startsWith("GOFB"))
        {
            ivLength = baseEngine.getBlockSize();
            cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(new GOFBBlockCipher(baseEngine)));
        } else
        if(modeName.startsWith("CTS"))
        {
            ivLength = baseEngine.getBlockSize();
            cipher = new BufferedGenericBlockCipher(new CTSBlockCipher(new CBCBlockCipher(baseEngine)));
        } else
        if(modeName.startsWith("CCM"))
        {
            ivLength = baseEngine.getBlockSize();
            cipher = new AEADGenericBlockCipher(new CCMBlockCipher(baseEngine));
        } else
        if(modeName.startsWith("OCB"))
        {
            if(engineProvider != null)
            {
                ivLength = baseEngine.getBlockSize();
                cipher = new AEADGenericBlockCipher(new OCBBlockCipher(baseEngine, engineProvider.get()));
            } else
            {
                throw new NoSuchAlgorithmException((new StringBuilder()).append("can't support mode ").append(mode).toString());
            }
        } else
        if(modeName.startsWith("EAX"))
        {
            ivLength = baseEngine.getBlockSize();
            cipher = new AEADGenericBlockCipher(new EAXBlockCipher(baseEngine));
        } else
        if(modeName.startsWith("GCM"))
        {
            ivLength = baseEngine.getBlockSize();
            cipher = new AEADGenericBlockCipher(new GCMBlockCipher(baseEngine));
        } else
        {
            throw new NoSuchAlgorithmException((new StringBuilder()).append("can't support mode ").append(mode).toString());
        }
    }

    protected void engineSetPadding(String padding)
        throws NoSuchPaddingException
    {
        String paddingName = Strings.toUpperCase(padding);
        if(paddingName.equals("NOPADDING"))
        {
            if(cipher.wrapOnNoPadding())
                cipher = new BufferedGenericBlockCipher(new BufferedBlockCipher(cipher.getUnderlyingCipher()));
        } else
        if(paddingName.equals("WITHCTS"))
        {
            cipher = new BufferedGenericBlockCipher(new CTSBlockCipher(cipher.getUnderlyingCipher()));
        } else
        {
            padded = true;
            if(isAEADModeName(modeName))
                throw new NoSuchPaddingException("Only NoPadding can be used with AEAD modes.");
            if(paddingName.equals("PKCS5PADDING") || paddingName.equals("PKCS7PADDING"))
                cipher = new BufferedGenericBlockCipher(cipher.getUnderlyingCipher());
            else
            if(paddingName.equals("ZEROBYTEPADDING"))
                cipher = new BufferedGenericBlockCipher(cipher.getUnderlyingCipher(), new ZeroBytePadding());
            else
            if(paddingName.equals("ISO10126PADDING") || paddingName.equals("ISO10126-2PADDING"))
                cipher = new BufferedGenericBlockCipher(cipher.getUnderlyingCipher(), new ISO10126d2Padding());
            else
            if(paddingName.equals("X9.23PADDING") || paddingName.equals("X923PADDING"))
                cipher = new BufferedGenericBlockCipher(cipher.getUnderlyingCipher(), new X923Padding());
            else
            if(paddingName.equals("ISO7816-4PADDING") || paddingName.equals("ISO9797-1PADDING"))
                cipher = new BufferedGenericBlockCipher(cipher.getUnderlyingCipher(), new ISO7816d4Padding());
            else
            if(paddingName.equals("TBCPADDING"))
                cipher = new BufferedGenericBlockCipher(cipher.getUnderlyingCipher(), new TBCPadding());
            else
                throw new NoSuchPaddingException((new StringBuilder()).append("Padding ").append(padding).append(" unknown.").toString());
        }
    }

    protected void engineInit(int opmode, Key key, AlgorithmParameterSpec params, SecureRandom random)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        pbeSpec = null;
        pbeAlgorithm = null;
        engineParams = null;
        if(!(key instanceof SecretKey))
            throw new InvalidKeyException((new StringBuilder()).append("Key for algorithm ").append(key.getAlgorithm()).append(" not suitable for symmetric enryption.").toString());
        if(params == null && baseEngine.getAlgorithmName().startsWith("RC5-64"))
            throw new InvalidAlgorithmParameterException("RC5 requires an RC5ParametersSpec to be passed in.");
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
                if(params instanceof IvParameterSpec)
                {
                    IvParameterSpec iv = (IvParameterSpec)params;
                    param = new ParametersWithIV(param, iv.getIV());
                }
            } else
            if(params instanceof PBEParameterSpec)
            {
                pbeSpec = (PBEParameterSpec)params;
                param = PBE.Util.makePBEParameters(k, params, cipher.getUnderlyingCipher().getAlgorithmName());
            } else
            {
                throw new InvalidAlgorithmParameterException("PBE requires PBE parameters to be set.");
            }
            if(param instanceof ParametersWithIV)
                ivParam = (ParametersWithIV)param;
        } else
        if(params == null)
            param = new KeyParameter(key.getEncoded());
        else
        if(params instanceof IvParameterSpec)
        {
            if(ivLength != 0)
            {
                IvParameterSpec p = (IvParameterSpec)params;
                if(p.getIV().length != ivLength && !isAEADModeName(modeName))
                    throw new InvalidAlgorithmParameterException((new StringBuilder()).append("IV must be ").append(ivLength).append(" bytes long.").toString());
                if(key instanceof RepeatedSecretKeySpec)
                {
                    param = new ParametersWithIV(null, p.getIV());
                    ivParam = (ParametersWithIV)param;
                } else
                {
                    param = new ParametersWithIV(new KeyParameter(key.getEncoded()), p.getIV());
                    ivParam = (ParametersWithIV)param;
                }
            } else
            {
                if(modeName != null && modeName.equals("ECB"))
                    throw new InvalidAlgorithmParameterException("ECB mode does not use an IV");
                param = new KeyParameter(key.getEncoded());
            }
        } else
        if(params instanceof GOST28147ParameterSpec)
        {
            GOST28147ParameterSpec gost28147Param = (GOST28147ParameterSpec)params;
            param = new ParametersWithSBox(new KeyParameter(key.getEncoded()), ((GOST28147ParameterSpec)params).getSbox());
            if(gost28147Param.getIV() != null && ivLength != 0)
            {
                param = new ParametersWithIV(param, gost28147Param.getIV());
                ivParam = (ParametersWithIV)param;
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
            if(baseEngine.getAlgorithmName().startsWith("RC5"))
            {
                if(baseEngine.getAlgorithmName().equals("RC5-32"))
                {
                    if(rc5Param.getWordSize() != 32)
                        throw new InvalidAlgorithmParameterException((new StringBuilder()).append("RC5 already set up for a word size of 32 not ").append(rc5Param.getWordSize()).append(".").toString());
                } else
                if(baseEngine.getAlgorithmName().equals("RC5-64") && rc5Param.getWordSize() != 64)
                    throw new InvalidAlgorithmParameterException((new StringBuilder()).append("RC5 already set up for a word size of 64 not ").append(rc5Param.getWordSize()).append(".").toString());
            } else
            {
                throw new InvalidAlgorithmParameterException("RC5 parameters passed to a cipher that is not RC5.");
            }
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
            if(cipher.getUnderlyingCipher().getAlgorithmName().indexOf("PGPCFB") < 0)
                throw new InvalidAlgorithmParameterException("no IV set when one expected");
        }
        if(random != null && padded)
            param = new ParametersWithRandom(param, random);
        try
        {
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
                throw new InvalidParameterException((new StringBuilder()).append("unknown opmode ").append(opmode).append(" passed").toString());
            }
        }
        catch(Exception e)
        {
            throw new InvalidKeyException(e.getMessage());
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
        int length = cipher.getUpdateOutputSize(inputLen);
        if(length > 0)
        {
            byte out[] = new byte[length];
            int len = cipher.processBytes(input, inputOffset, inputLen, out, 0);
            if(len == 0)
                return null;
            if(len != out.length)
            {
                byte tmp[] = new byte[len];
                System.arraycopy(out, 0, tmp, 0, len);
                return tmp;
            } else
            {
                return out;
            }
        } else
        {
            cipher.processBytes(input, inputOffset, inputLen, null, 0);
            return null;
        }
    }

    protected int engineUpdate(byte input[], int inputOffset, int inputLen, byte output[], int outputOffset)
        throws ShortBufferException
    {
        try
        {
            return cipher.processBytes(input, inputOffset, inputLen, output, outputOffset);
        }
        catch(DataLengthException e)
        {
            throw new ShortBufferException(e.getMessage());
        }
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
        if(len == tmp.length)
        {
            return tmp;
        } else
        {
            byte out[] = new byte[len];
            System.arraycopy(tmp, 0, out, 0, len);
            return out;
        }
    }

    protected int engineDoFinal(byte input[], int inputOffset, int inputLen, byte output[], int outputOffset)
        throws IllegalBlockSizeException, BadPaddingException, ShortBufferException
    {
        try
        {
            int len = 0;
            if(inputLen != 0)
                len = cipher.processBytes(input, inputOffset, inputLen, output, outputOffset);
            return len + cipher.doFinal(output, outputOffset + len);
        }
        catch(OutputLengthException e)
        {
            throw new ShortBufferException(e.getMessage());
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

    private boolean isAEADModeName(String modeName)
    {
        return "CCM".equals(modeName) || "EAX".equals(modeName) || "GCM".equals(modeName) || "OCB".equals(modeName);
    }

    private Class availableSpecs[] = {
        javax/crypto/spec/RC2ParameterSpec, javax/crypto/spec/RC5ParameterSpec, javax/crypto/spec/IvParameterSpec, javax/crypto/spec/PBEParameterSpec, co/org/bouncy/jce/spec/GOST28147ParameterSpec
    };
    private BlockCipher baseEngine;
    private BlockCipherProvider engineProvider;
    private GenericBlockCipher cipher;
    private ParametersWithIV ivParam;
    private int ivLength;
    private boolean padded;
    private PBEParameterSpec pbeSpec;
    private String pbeAlgorithm;
    private String modeName;
}
