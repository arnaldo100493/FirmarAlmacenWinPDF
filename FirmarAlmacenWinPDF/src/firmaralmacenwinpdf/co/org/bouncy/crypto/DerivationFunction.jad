// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DerivationFunction.java

package co.org.bouncy.crypto;


// Referenced classes of package co.org.bouncy.crypto:
//            DataLengthException, DerivationParameters, Digest

public interface DerivationFunction
{

    public abstract void init(DerivationParameters derivationparameters);

    public abstract Digest getDigest();

    public abstract int generateBytes(byte abyte0[], int i, int j)
        throws DataLengthException, IllegalArgumentException;
}
