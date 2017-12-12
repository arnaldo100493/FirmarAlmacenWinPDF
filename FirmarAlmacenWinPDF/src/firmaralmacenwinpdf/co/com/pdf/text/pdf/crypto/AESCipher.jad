// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AESCipher.java

package co.com.pdf.text.pdf.crypto;

import co.org.bouncy.crypto.engines.AESFastEngine;
import co.org.bouncy.crypto.modes.CBCBlockCipher;
import co.org.bouncy.crypto.paddings.PaddedBufferedBlockCipher;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.crypto.params.ParametersWithIV;

public class AESCipher
{

    public AESCipher(boolean forEncryption, byte key[], byte iv[])
    {
        co.org.bouncy.crypto.BlockCipher aes = new AESFastEngine();
        co.org.bouncy.crypto.BlockCipher cbc = new CBCBlockCipher(aes);
        bp = new PaddedBufferedBlockCipher(cbc);
        KeyParameter kp = new KeyParameter(key);
        ParametersWithIV piv = new ParametersWithIV(kp, iv);
        bp.init(forEncryption, piv);
    }

    public byte[] update(byte inp[], int inpOff, int inpLen)
    {
        int neededLen = bp.getUpdateOutputSize(inpLen);
        byte outp[] = null;
        if(neededLen > 0)
            outp = new byte[neededLen];
        else
            neededLen = 0;
        bp.processBytes(inp, inpOff, inpLen, outp, 0);
        return outp;
    }

    public byte[] doFinal()
    {
        int neededLen = bp.getOutputSize(0);
        byte outp[] = new byte[neededLen];
        int n = 0;
        try
        {
            n = bp.doFinal(outp, 0);
        }
        catch(Exception ex)
        {
            return outp;
        }
        if(n != outp.length)
        {
            byte outp2[] = new byte[n];
            System.arraycopy(outp, 0, outp2, 0, n);
            return outp2;
        } else
        {
            return outp;
        }
    }

    private PaddedBufferedBlockCipher bp;
}
