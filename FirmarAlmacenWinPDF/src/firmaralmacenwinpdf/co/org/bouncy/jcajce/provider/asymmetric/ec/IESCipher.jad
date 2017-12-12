// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IESCipher.java

package co.org.bouncy.jcajce.provider.asymmetric.ec;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.agreement.ECDHBasicAgreement;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.crypto.engines.*;
import co.org.bouncy.crypto.generators.*;
import co.org.bouncy.crypto.macs.HMac;
import co.org.bouncy.crypto.paddings.PaddedBufferedBlockCipher;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.crypto.parsers.ECIESPublicKeyParser;
import co.org.bouncy.jcajce.provider.asymmetric.util.ECUtil;
import co.org.bouncy.jcajce.provider.asymmetric.util.IESUtil;
import co.org.bouncy.jce.interfaces.*;
import co.org.bouncy.jce.spec.ECParameterSpec;
import co.org.bouncy.jce.spec.IESParameterSpec;
import co.org.bouncy.math.ec.ECCurve;
import co.org.bouncy.math.ec.ECPoint;
import co.org.bouncy.util.Strings;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.*;

public class IESCipher extends CipherSpi
{
    public static class ECIESwithAES extends IESCipher
    {

        public ECIESwithAES()
        {
            super(new IESEngine(new ECDHBasicAgreement(), new KDF2BytesGenerator(new SHA1Digest()), new HMac(new SHA1Digest()), new PaddedBufferedBlockCipher(new AESEngine())));
        }
    }

    public static class ECIESwithDESede extends IESCipher
    {

        public ECIESwithDESede()
        {
            super(new IESEngine(new ECDHBasicAgreement(), new KDF2BytesGenerator(new SHA1Digest()), new HMac(new SHA1Digest()), new PaddedBufferedBlockCipher(new DESedeEngine())));
        }
    }

    public static class ECIES extends IESCipher
    {

        public ECIES()
        {
            super(new IESEngine(new ECDHBasicAgreement(), new KDF2BytesGenerator(new SHA1Digest()), new HMac(new SHA1Digest())));
        }
    }


    public IESCipher(IESEngine engine)
    {
        state = -1;
        buffer = new ByteArrayOutputStream();
        engineParam = null;
        engineSpec = null;
        dhaesMode = false;
        otherKeyParameter = null;
        this.engine = engine;
    }

    public int engineGetBlockSize()
    {
        if(engine.getCipher() != null)
            return engine.getCipher().getBlockSize();
        else
            return 0;
    }

    public int engineGetKeySize(Key key)
    {
        if(key instanceof ECKey)
            return ((ECKey)key).getParameters().getCurve().getFieldSize();
        else
            throw new IllegalArgumentException("not an EC key");
    }

    public byte[] engineGetIV()
    {
        return null;
    }

    public AlgorithmParameters engineGetParameters()
    {
        if(engineParam == null && engineSpec != null)
            try
            {
                engineParam = AlgorithmParameters.getInstance("IES", "BC");
                engineParam.init(engineSpec);
            }
            catch(Exception e)
            {
                throw new RuntimeException(e.toString());
            }
        return engineParam;
    }

    public void engineSetMode(String mode)
        throws NoSuchAlgorithmException
    {
        String modeName = Strings.toUpperCase(mode);
        if(modeName.equals("NONE"))
            dhaesMode = false;
        else
        if(modeName.equals("DHAES"))
            dhaesMode = true;
        else
            throw new IllegalArgumentException((new StringBuilder()).append("can't support mode ").append(mode).toString());
    }

    public int engineGetOutputSize(int inputLen)
    {
        int len1 = engine.getMac().getMacSize();
        int len2;
        if(key != null)
            len2 = 1 + (2 * (((ECKey)key).getParameters().getCurve().getFieldSize() + 7)) / 8;
        else
            throw new IllegalStateException("cipher not initialised");
        int len3;
        if(engine.getCipher() == null)
            len3 = inputLen;
        else
        if(state == 1 || state == 3)
            len3 = engine.getCipher().getOutputSize(inputLen);
        else
        if(state == 2 || state == 4)
            len3 = engine.getCipher().getOutputSize(inputLen - len1 - len2);
        else
            throw new IllegalStateException("cipher not initialised");
        if(state == 1 || state == 3)
            return buffer.size() + len1 + len2 + len3;
        if(state == 2 || state == 4)
            return (buffer.size() - len1 - len2) + len3;
        else
            throw new IllegalStateException("cipher not initialised");
    }

    public void engineSetPadding(String padding)
        throws NoSuchPaddingException
    {
        String paddingName = Strings.toUpperCase(padding);
        if(!paddingName.equals("NOPADDING") && !paddingName.equals("PKCS5PADDING") && !paddingName.equals("PKCS7PADDING"))
            throw new NoSuchPaddingException("padding not available with IESCipher");
        else
            return;
    }

    public void engineInit(int opmode, Key key, AlgorithmParameters params, SecureRandom random)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        AlgorithmParameterSpec paramSpec = null;
        if(params != null)
            try
            {
                paramSpec = params.getParameterSpec(co/org/bouncy/jce/spec/IESParameterSpec);
            }
            catch(Exception e)
            {
                throw new InvalidAlgorithmParameterException((new StringBuilder()).append("cannot recognise parameters: ").append(e.toString()).toString());
            }
        engineParam = params;
        engineInit(opmode, key, paramSpec, random);
    }

    public void engineInit(int opmode, Key key, AlgorithmParameterSpec engineSpec, SecureRandom random)
        throws InvalidAlgorithmParameterException, InvalidKeyException
    {
        otherKeyParameter = null;
        if(engineSpec == null)
            this.engineSpec = IESUtil.guessParameterSpec(engine);
        else
        if(engineSpec instanceof IESParameterSpec)
            this.engineSpec = (IESParameterSpec)engineSpec;
        else
            throw new InvalidAlgorithmParameterException("must be passed IES parameters");
        if(opmode == 1 || opmode == 3)
        {
            if(key instanceof ECPublicKey)
                this.key = ECUtil.generatePublicKeyParameter((PublicKey)key);
            else
            if(key instanceof IESKey)
            {
                IESKey ieKey = (IESKey)key;
                this.key = ECUtil.generatePublicKeyParameter(ieKey.getPublic());
                otherKeyParameter = ECUtil.generatePrivateKeyParameter(ieKey.getPrivate());
            } else
            {
                throw new InvalidKeyException("must be passed recipient's public EC key for encryption");
            }
        } else
        if(opmode == 2 || opmode == 4)
        {
            if(key instanceof ECPrivateKey)
                this.key = ECUtil.generatePrivateKeyParameter((PrivateKey)key);
            else
            if(key instanceof IESKey)
            {
                IESKey ieKey = (IESKey)key;
                otherKeyParameter = ECUtil.generatePublicKeyParameter(ieKey.getPublic());
                this.key = ECUtil.generatePrivateKeyParameter(ieKey.getPrivate());
            } else
            {
                throw new InvalidKeyException("must be passed recipient's private EC key for decryption");
            }
        } else
        {
            throw new InvalidKeyException("must be passed EC key");
        }
        this.random = random;
        state = opmode;
        buffer.reset();
    }

    public void engineInit(int opmode, Key key, SecureRandom random)
        throws InvalidKeyException
    {
        try
        {
            engineInit(opmode, key, (AlgorithmParameterSpec)null, random);
        }
        catch(InvalidAlgorithmParameterException e)
        {
            throw new IllegalArgumentException("can't handle supplied parameter spec");
        }
    }

    public byte[] engineUpdate(byte input[], int inputOffset, int inputLen)
    {
        buffer.write(input, inputOffset, inputLen);
        return null;
    }

    public int engineUpdate(byte input[], int inputOffset, int inputLen, byte output[], int outputOffset)
    {
        buffer.write(input, inputOffset, inputLen);
        return 0;
    }

    public byte[] engineDoFinal(byte input[], int inputOffset, int inputLen)
        throws IllegalBlockSizeException, BadPaddingException
    {
        if(inputLen != 0)
            buffer.write(input, inputOffset, inputLen);
        byte in[] = buffer.toByteArray();
        buffer.reset();
        IESParameters params = new IESWithCipherParameters(engineSpec.getDerivationV(), engineSpec.getEncodingV(), engineSpec.getMacKeySize(), engineSpec.getCipherKeySize());
        ECDomainParameters ecParams = ((ECKeyParameters)key).getParameters();
        if(otherKeyParameter != null)
            try
            {
                if(state == 1 || state == 3)
                    engine.init(true, otherKeyParameter, key, params);
                else
                    engine.init(false, key, otherKeyParameter, params);
                return engine.processBlock(in, 0, in.length);
            }
            catch(Exception e)
            {
                throw new BadPaddingException(e.getMessage());
            }
        if(state == 1 || state == 3)
        {
            ECKeyPairGenerator gen = new ECKeyPairGenerator();
            gen.init(new ECKeyGenerationParameters(ecParams, random));
            EphemeralKeyPairGenerator kGen = new EphemeralKeyPairGenerator(gen, new KeyEncoder() {

                public byte[] getEncoded(AsymmetricKeyParameter keyParameter)
                {
                    return ((ECPublicKeyParameters)keyParameter).getQ().getEncoded();
                }

                final IESCipher this$0;

            
            {
                this$0 = IESCipher.this;
                super();
            }
            }
);
            try
            {
                engine.init(key, params, kGen);
                return engine.processBlock(in, 0, in.length);
            }
            catch(Exception e)
            {
                throw new BadPaddingException(e.getMessage());
            }
        }
        if(state == 2 || state == 4)
            try
            {
                engine.init(key, params, new ECIESPublicKeyParser(ecParams));
                return engine.processBlock(in, 0, in.length);
            }
            catch(InvalidCipherTextException e)
            {
                throw new BadPaddingException(e.getMessage());
            }
        else
            throw new IllegalStateException("cipher not initialised");
    }

    public int engineDoFinal(byte input[], int inputOffset, int inputLength, byte output[], int outputOffset)
        throws ShortBufferException, IllegalBlockSizeException, BadPaddingException
    {
        byte buf[] = engineDoFinal(input, inputOffset, inputLength);
        System.arraycopy(buf, 0, output, outputOffset, buf.length);
        return buf.length;
    }

    private IESEngine engine;
    private int state;
    private ByteArrayOutputStream buffer;
    private AlgorithmParameters engineParam;
    private IESParameterSpec engineSpec;
    private AsymmetricKeyParameter key;
    private SecureRandom random;
    private boolean dhaesMode;
    private AsymmetricKeyParameter otherKeyParameter;
}
