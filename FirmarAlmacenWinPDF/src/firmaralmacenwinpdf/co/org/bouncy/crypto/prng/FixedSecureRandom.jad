// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FixedSecureRandom.java

package co.org.bouncy.crypto.prng;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

public class FixedSecureRandom extends SecureRandom
{

    public FixedSecureRandom(byte value[])
    {
        this(false, new byte[][] {
            value
        });
    }

    public FixedSecureRandom(byte values[][])
    {
        this(false, values);
    }

    public FixedSecureRandom(boolean intPad, byte value[])
    {
        this(intPad, new byte[][] {
            value
        });
    }

    public FixedSecureRandom(boolean intPad, byte values[][])
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        for(int i = 0; i != values.length; i++)
            try
            {
                bOut.write(values[i]);
            }
            catch(IOException e)
            {
                throw new IllegalArgumentException("can't save value array.");
            }

        _data = bOut.toByteArray();
        if(intPad)
            _intPad = _data.length % 4;
    }

    public void nextBytes(byte bytes[])
    {
        System.arraycopy(_data, _index, bytes, 0, bytes.length);
        _index += bytes.length;
    }

    public int nextInt()
    {
        int val = 0;
        val |= nextValue() << 24;
        val |= nextValue() << 16;
        if(_intPad == 2)
            _intPad--;
        else
            val |= nextValue() << 8;
        if(_intPad == 1)
            _intPad--;
        else
            val |= nextValue();
        return val;
    }

    public long nextLong()
    {
        long val = 0L;
        val |= (long)nextValue() << 56;
        val |= (long)nextValue() << 48;
        val |= (long)nextValue() << 40;
        val |= (long)nextValue() << 32;
        val |= (long)nextValue() << 24;
        val |= (long)nextValue() << 16;
        val |= (long)nextValue() << 8;
        val |= nextValue();
        return val;
    }

    public boolean isExhausted()
    {
        return _index == _data.length;
    }

    private int nextValue()
    {
        return _data[_index++] & 0xff;
    }

    private byte _data[];
    private int _index;
    private int _intPad;
}
