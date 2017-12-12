// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RC2Parameters.java

package co.org.bouncy.crypto.params;

import co.org.bouncy.crypto.CipherParameters;

public class RC2Parameters
    implements CipherParameters
{

    public RC2Parameters(byte key[])
    {
        this(key, key.length <= 128 ? key.length * 8 : 1024);
    }

    public RC2Parameters(byte key[], int bits)
    {
        this.key = new byte[key.length];
        this.bits = bits;
        System.arraycopy(key, 0, this.key, 0, key.length);
    }

    public byte[] getKey()
    {
        return key;
    }

    public int getEffectiveKeyBits()
    {
        return bits;
    }

    private byte key[];
    private int bits;
}
