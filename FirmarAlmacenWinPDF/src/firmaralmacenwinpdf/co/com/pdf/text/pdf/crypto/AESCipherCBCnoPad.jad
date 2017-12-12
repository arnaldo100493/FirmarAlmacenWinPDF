// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AESCipherCBCnoPad.java

package co.com.pdf.text.pdf.crypto;

import co.org.bouncy.crypto.BlockCipher;
import co.org.bouncy.crypto.engines.AESFastEngine;
import co.org.bouncy.crypto.modes.CBCBlockCipher;
import co.org.bouncy.crypto.params.KeyParameter;

public class AESCipherCBCnoPad
{

    public AESCipherCBCnoPad(boolean forEncryption, byte key[])
    {
        BlockCipher aes = new AESFastEngine();
        cbc = new CBCBlockCipher(aes);
        KeyParameter kp = new KeyParameter(key);
        cbc.init(forEncryption, kp);
    }

    public byte[] processBlock(byte inp[], int inpOff, int inpLen)
    {
        if(inpLen % cbc.getBlockSize() != 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Not multiple of block: ").append(inpLen).toString());
        byte outp[] = new byte[inpLen];
        int baseOffset = 0;
        while(inpLen > 0) 
        {
            cbc.processBlock(inp, inpOff, outp, baseOffset);
            inpLen -= cbc.getBlockSize();
            baseOffset += cbc.getBlockSize();
            inpOff += cbc.getBlockSize();
        }
        return outp;
    }

    private BlockCipher cbc;
}
