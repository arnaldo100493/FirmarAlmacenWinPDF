// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BlockCipherPadding.java

package co.org.bouncy.crypto.paddings;

import co.org.bouncy.crypto.InvalidCipherTextException;
import java.security.SecureRandom;

public interface BlockCipherPadding
{

    public abstract void init(SecureRandom securerandom)
        throws IllegalArgumentException;

    public abstract String getPaddingName();

    public abstract int addPadding(byte abyte0[], int i);

    public abstract int padCount(byte abyte0[])
        throws InvalidCipherTextException;
}
