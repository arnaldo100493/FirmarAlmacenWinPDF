// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseStreamCipher.java

package co.org.bouncy.jcajce.provider.symmetric.util;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.params.ParametersWithIV;
import java.io.PrintStream;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.*;
import javax.crypto.spec.*;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric.util:
//            BaseWrapCipher, BCPBEKey, PBE

public class BaseStreamCipher extends BaseWrapCipher
    implements PBE
{

    protected BaseStreamCipher(StreamCipher engine, int ivLength)
    {
        this.ivLength = 0;
        pbeSpec = null;
        pbeAlgorithm = null;
        cipher = engine;
        this.ivLength = ivLength;
    }

    protected BaseStreamCipher(BlockCipher engine, int ivLength)
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
                param = PBE.Util.makePBEParameters(k, params, cipher.getAlgorithmName());
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
    {
        if(inputLen != 0)
            cipher.processBytes(input, inputOffset, inputLen, output, outputOffset);
        cipher.reset();
        return inputLen;
    }

    private Class availableSpecs[] = {
        javax/crypto/spec/RC2ParameterSpec, javax/crypto/spec/RC5ParameterSpec, javax/crypto/spec/IvParameterSpec, javax/crypto/spec/PBEParameterSpec
    };
    private StreamCipher cipher;
    private ParametersWithIV ivParam;
    private int ivLength;
    private PBEParameterSpec pbeSpec;
    private String pbeAlgorithm;
}
