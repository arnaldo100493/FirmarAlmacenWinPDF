// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Signer.java

package co.org.bouncy.crypto;


// Referenced classes of package co.org.bouncy.crypto:
//            CryptoException, DataLengthException, CipherParameters

public interface Signer
{

    public abstract void init(boolean flag, CipherParameters cipherparameters);

    public abstract void update(byte byte0);

    public abstract void update(byte abyte0[], int i, int j);

    public abstract byte[] generateSignature()
        throws CryptoException, DataLengthException;

    public abstract boolean verifySignature(byte abyte0[]);

    public abstract void reset();
}
