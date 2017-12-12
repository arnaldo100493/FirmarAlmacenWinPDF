// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AEADBlockCipher.java

package co.org.bouncy.crypto.modes;

import co.org.bouncy.crypto.*;

public interface AEADBlockCipher
{

    public abstract void init(boolean flag, CipherParameters cipherparameters)
        throws IllegalArgumentException;

    public abstract String getAlgorithmName();

    public abstract BlockCipher getUnderlyingCipher();

    public abstract void processAADByte(byte byte0);

    public abstract void processAADBytes(byte abyte0[], int i, int j);

    public abstract int processByte(byte byte0, byte abyte0[], int i)
        throws DataLengthException;

    public abstract int processBytes(byte abyte0[], int i, int j, byte abyte1[], int k)
        throws DataLengthException;

    public abstract int doFinal(byte abyte0[], int i)
        throws IllegalStateException, InvalidCipherTextException;

    public abstract byte[] getMac();

    public abstract int getUpdateOutputSize(int i);

    public abstract int getOutputSize(int i);

    public abstract void reset();
}
