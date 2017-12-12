// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CBCBlockCipher.java

package co.org.bouncy.crypto.modes;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.ParametersWithIV;
import co.org.bouncy.util.Arrays;

public class CBCBlockCipher
    implements BlockCipher
{

    public CBCBlockCipher(BlockCipher cipher)
    {
        this.cipher = null;
        this.cipher = cipher;
        blockSize = cipher.getBlockSize();
        IV = new byte[blockSize];
        cbcV = new byte[blockSize];
        cbcNextV = new byte[blockSize];
    }

    public BlockCipher getUnderlyingCipher()
    {
        return cipher;
    }

    public void init(boolean encrypting, CipherParameters params)
        throws IllegalArgumentException
    {
        boolean oldEncrypting = this.encrypting;
        this.encrypting = encrypting;
        if(params instanceof ParametersWithIV)
        {
            ParametersWithIV ivParam = (ParametersWithIV)params;
            byte iv[] = ivParam.getIV();
            if(iv.length != blockSize)
                throw new IllegalArgumentException("initialisation vector must be the same length as block size");
            System.arraycopy(iv, 0, IV, 0, iv.length);
            reset();
            if(ivParam.getParameters() != null)
                cipher.init(encrypting, ivParam.getParameters());
            else
            if(oldEncrypting != encrypting)
                throw new IllegalArgumentException("cannot change encrypting state without providing key.");
        } else
        {
            reset();
            if(params != null)
                cipher.init(encrypting, params);
            else
            if(oldEncrypting != encrypting)
                throw new IllegalArgumentException("cannot change encrypting state without providing key.");
        }
    }

    public String getAlgorithmName()
    {
        return (new StringBuilder()).append(cipher.getAlgorithmName()).append("/CBC").toString();
    }

    public int getBlockSize()
    {
        return cipher.getBlockSize();
    }

    public int processBlock(byte in[], int inOff, byte out[], int outOff)
        throws DataLengthException, IllegalStateException
    {
        return encrypting ? encryptBlock(in, inOff, out, outOff) : decryptBlock(in, inOff, out, outOff);
    }

    public void reset()
    {
        System.arraycopy(IV, 0, cbcV, 0, IV.length);
        Arrays.fill(cbcNextV, (byte)0);
        cipher.reset();
    }

    private int encryptBlock(byte in[], int inOff, byte out[], int outOff)
        throws DataLengthException, IllegalStateException
    {
        if(inOff + blockSize > in.length)
            throw new DataLengthException("input buffer too short");
        for(int i = 0; i < blockSize; i++)
            cbcV[i] ^= in[inOff + i];

        int length = cipher.processBlock(cbcV, 0, out, outOff);
        System.arraycopy(out, outOff, cbcV, 0, cbcV.length);
        return length;
    }

    private int decryptBlock(byte in[], int inOff, byte out[], int outOff)
        throws DataLengthException, IllegalStateException
    {
        if(inOff + blockSize > in.length)
            throw new DataLengthException("input buffer too short");
        System.arraycopy(in, inOff, cbcNextV, 0, blockSize);
        int length = cipher.processBlock(in, inOff, out, outOff);
        for(int i = 0; i < blockSize; i++)
            out[outOff + i] ^= cbcV[i];

        byte tmp[] = cbcV;
        cbcV = cbcNextV;
        cbcNextV = tmp;
        return length;
    }

    private byte IV[];
    private byte cbcV[];
    private byte cbcNextV[];
    private int blockSize;
    private BlockCipher cipher;
    private boolean encrypting;
}
