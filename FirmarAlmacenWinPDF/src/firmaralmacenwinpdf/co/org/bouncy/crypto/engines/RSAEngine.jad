// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RSAEngine.java

package co.org.bouncy.crypto.engines;

import co.org.bouncy.crypto.AsymmetricBlockCipher;
import co.org.bouncy.crypto.CipherParameters;

// Referenced classes of package co.org.bouncy.crypto.engines:
//            RSACoreEngine

public class RSAEngine
    implements AsymmetricBlockCipher
{

    public RSAEngine()
    {
    }

    public void init(boolean forEncryption, CipherParameters param)
    {
        if(core == null)
            core = new RSACoreEngine();
        core.init(forEncryption, param);
    }

    public int getInputBlockSize()
    {
        return core.getInputBlockSize();
    }

    public int getOutputBlockSize()
    {
        return core.getOutputBlockSize();
    }

    public byte[] processBlock(byte in[], int inOff, int inLen)
    {
        if(core == null)
            throw new IllegalStateException("RSA engine not initialised");
        else
            return core.convertOutput(core.processBlock(core.convertInput(in, inOff, inLen)));
    }

    private RSACoreEngine core;
}
