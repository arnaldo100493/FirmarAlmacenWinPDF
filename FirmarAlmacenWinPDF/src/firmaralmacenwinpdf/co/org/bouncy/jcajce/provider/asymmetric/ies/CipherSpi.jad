// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CipherSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.ies;

import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.InvalidCipherTextException;
import co.org.bouncy.crypto.agreement.DHBasicAgreement;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.crypto.engines.IESEngine;
import co.org.bouncy.crypto.generators.KDF2BytesGenerator;
import co.org.bouncy.crypto.macs.HMac;
import co.org.bouncy.crypto.params.IESParameters;
import co.org.bouncy.jcajce.provider.asymmetric.util.DHUtil;
import co.org.bouncy.jcajce.provider.asymmetric.util.ECUtil;
import co.org.bouncy.jce.interfaces.ECPrivateKey;
import co.org.bouncy.jce.interfaces.ECPublicKey;
import co.org.bouncy.jce.interfaces.IESKey;
import co.org.bouncy.jce.spec.IESParameterSpec;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.interfaces.DHPrivateKey;

public class CipherSpi extends javax.crypto.CipherSpi
{
    public static class IES extends CipherSpi
    {

        public IES()
        {
            super(new IESEngine(new DHBasicAgreement(), new KDF2BytesGenerator(new SHA1Digest()), new HMac(new SHA1Digest())));
        }
    }


    public CipherSpi(IESEngine engine)
    {
        state = -1;
        buffer = new ByteArrayOutputStream();
        engineParam = null;
        engineParams = null;
        cipher = engine;
    }

    protected int engineGetBlockSize()
    {
        return 0;
    }

    protected byte[] engineGetIV()
    {
        return null;
    }

    protected int engineGetKeySize(Key key)
    {
        if(!(key instanceof IESKey))
            throw new IllegalArgumentException("must be passed IE key");
        IESKey ieKey = (IESKey)key;
        if(ieKey.getPrivate() instanceof DHPrivateKey)
        {
            DHPrivateKey k = (DHPrivateKey)ieKey.getPrivate();
            return k.getX().bitLength();
        }
        if(ieKey.getPrivate() instanceof ECPrivateKey)
        {
            ECPrivateKey k = (ECPrivateKey)ieKey.getPrivate();
            return k.getD().bitLength();
        } else
        {
            throw new IllegalArgumentException("not an IE key!");
        }
    }

    protected int engineGetOutputSize(int inputLen)
    {
        if(state == 1 || state == 3)
            return buffer.size() + inputLen + 20;
        if(state == 2 || state == 4)
            return (buffer.size() + inputLen) - 20;
        else
            throw new IllegalStateException("cipher not initialised");
    }

    protected AlgorithmParameters engineGetParameters()
    {
        if(engineParam == null && engineParams != null)
        {
            String name = "IES";
            try
            {
                engineParam = AlgorithmParameters.getInstance(name, "BC");
                engineParam.init(engineParams);
            }
            catch(Exception e)
            {
                throw new RuntimeException(e.toString());
            }
        }
        return engineParam;
    }

    protected void engineSetMode(String mode)
    {
        throw new IllegalArgumentException((new StringBuilder()).append("can't support mode ").append(mode).toString());
    }

    protected void engineSetPadding(String padding)
        throws NoSuchPaddingException
    {
        throw new NoSuchPaddingException((new StringBuilder()).append(padding).append(" unavailable with RSA.").toString());
    }

    protected void engineInit(int opmode, Key key, AlgorithmParameterSpec params, SecureRandom random)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        if(!(key instanceof IESKey))
            throw new InvalidKeyException("must be passed IES key");
        if(params == null && (opmode == 1 || opmode == 3))
        {
            byte d[] = new byte[16];
            byte e[] = new byte[16];
            if(random == null)
                random = new SecureRandom();
            random.nextBytes(d);
            random.nextBytes(e);
            params = new IESParameterSpec(d, e, 128);
        } else
        if(!(params instanceof IESParameterSpec))
            throw new InvalidAlgorithmParameterException("must be passed IES parameters");
        IESKey ieKey = (IESKey)key;
        CipherParameters pubKey;
        CipherParameters privKey;
        if(ieKey.getPublic() instanceof ECPublicKey)
        {
            pubKey = ECUtil.generatePublicKeyParameter(ieKey.getPublic());
            privKey = ECUtil.generatePrivateKeyParameter(ieKey.getPrivate());
        } else
        {
            pubKey = DHUtil.generatePublicKeyParameter(ieKey.getPublic());
            privKey = DHUtil.generatePrivateKeyParameter(ieKey.getPrivate());
        }
        engineParams = (IESParameterSpec)params;
        IESParameters p = new IESParameters(engineParams.getDerivationV(), engineParams.getEncodingV(), engineParams.getMacKeySize());
        state = opmode;
        buffer.reset();
        switch(opmode)
        {
        case 1: // '\001'
        case 3: // '\003'
            cipher.init(true, privKey, pubKey, p);
            break;

        case 2: // '\002'
        case 4: // '\004'
            cipher.init(false, privKey, pubKey, p);
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
        engineParam = params;
        engineInit(opmode, key, paramSpec, random);
    }

    protected void engineInit(int opmode, Key key, SecureRandom random)
        throws InvalidKeyException
    {
        if(opmode == 1 || opmode == 3)
            try
            {
                engineInit(opmode, key, (AlgorithmParameterSpec)null, random);
                return;
            }
            catch(InvalidAlgorithmParameterException e) { }
        throw new IllegalArgumentException("can't handle null parameter spec in IES");
    }

    protected byte[] engineUpdate(byte input[], int inputOffset, int inputLen)
    {
        buffer.write(input, inputOffset, inputLen);
        return null;
    }

    protected int engineUpdate(byte input[], int inputOffset, int inputLen, byte output[], int outputOffset)
    {
        buffer.write(input, inputOffset, inputLen);
        return 0;
    }

    protected byte[] engineDoFinal(byte input[], int inputOffset, int inputLen)
        throws IllegalBlockSizeException, BadPaddingException
    {
        if(inputLen != 0)
            buffer.write(input, inputOffset, inputLen);
        try
        {
            byte buf[] = buffer.toByteArray();
            buffer.reset();
            return cipher.processBlock(buf, 0, buf.length);
        }
        catch(InvalidCipherTextException e)
        {
            throw new BadPaddingException(e.getMessage());
        }
    }

    protected int engineDoFinal(byte input[], int inputOffset, int inputLen, byte output[], int outputOffset)
        throws IllegalBlockSizeException, BadPaddingException
    {
        if(inputLen != 0)
            buffer.write(input, inputOffset, inputLen);
        try
        {
            byte buf[] = buffer.toByteArray();
            buffer.reset();
            buf = cipher.processBlock(buf, 0, buf.length);
            System.arraycopy(buf, 0, output, outputOffset, buf.length);
            return buf.length;
        }
        catch(InvalidCipherTextException e)
        {
            throw new BadPaddingException(e.getMessage());
        }
    }

    private IESEngine cipher;
    private int state;
    private ByteArrayOutputStream buffer;
    private AlgorithmParameters engineParam;
    private IESParameterSpec engineParams;
    private Class availableSpecs[] = {
        co/org/bouncy/jce/spec/IESParameterSpec
    };
}
