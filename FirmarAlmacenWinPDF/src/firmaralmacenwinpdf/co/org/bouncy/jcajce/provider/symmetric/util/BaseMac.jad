// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BaseMac.java

package co.org.bouncy.jcajce.provider.symmetric.util;

import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.Mac;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.params.ParametersWithIV;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.MacSpi;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric.util:
//            BCPBEKey, PBE

public class BaseMac extends MacSpi
    implements PBE
{

    protected BaseMac(Mac macEngine)
    {
        pbeType = 2;
        pbeHash = 1;
        keySize = 160;
        this.macEngine = macEngine;
    }

    protected BaseMac(Mac macEngine, int pbeType, int pbeHash, int keySize)
    {
        this.pbeType = 2;
        this.pbeHash = 1;
        this.keySize = 160;
        this.macEngine = macEngine;
        this.pbeType = pbeType;
        this.pbeHash = pbeHash;
        this.keySize = keySize;
    }

    protected void engineInit(Key key, AlgorithmParameterSpec params)
        throws InvalidKeyException, InvalidAlgorithmParameterException
    {
        if(key == null)
            throw new InvalidKeyException("key is null");
        CipherParameters param;
        if(key instanceof BCPBEKey)
        {
            BCPBEKey k = (BCPBEKey)key;
            if(k.getParam() != null)
                param = k.getParam();
            else
            if(params instanceof PBEParameterSpec)
                param = PBE.Util.makePBEMacParameters(k, params);
            else
                throw new InvalidAlgorithmParameterException("PBE requires PBE parameters to be set.");
        } else
        if(params instanceof IvParameterSpec)
            param = new ParametersWithIV(new KeyParameter(key.getEncoded()), ((IvParameterSpec)params).getIV());
        else
        if(params == null)
            param = new KeyParameter(key.getEncoded());
        else
            throw new InvalidAlgorithmParameterException("unknown parameter type.");
        macEngine.init(param);
    }

    protected int engineGetMacLength()
    {
        return macEngine.getMacSize();
    }

    protected void engineReset()
    {
        macEngine.reset();
    }

    protected void engineUpdate(byte input)
    {
        macEngine.update(input);
    }

    protected void engineUpdate(byte input[], int offset, int len)
    {
        macEngine.update(input, offset, len);
    }

    protected byte[] engineDoFinal()
    {
        byte out[] = new byte[engineGetMacLength()];
        macEngine.doFinal(out, 0);
        return out;
    }

    private Mac macEngine;
    private int pbeType;
    private int pbeHash;
    private int keySize;
}
