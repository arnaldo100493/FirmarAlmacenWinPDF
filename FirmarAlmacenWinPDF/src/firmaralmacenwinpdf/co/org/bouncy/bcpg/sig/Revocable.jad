// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Revocable.java

package co.org.bouncy.bcpg.sig;

import co.org.bouncy.bcpg.SignatureSubpacket;

public class Revocable extends SignatureSubpacket
{

    private static byte[] booleanToByteArray(boolean value)
    {
        byte data[] = new byte[1];
        if(value)
        {
            data[0] = 1;
            return data;
        } else
        {
            return data;
        }
    }

    public Revocable(boolean critical, byte data[])
    {
        super(7, critical, data);
    }

    public Revocable(boolean critical, boolean isRevocable)
    {
        super(7, critical, booleanToByteArray(isRevocable));
    }

    public boolean isRevocable()
    {
        return data[0] != 0;
    }
}
