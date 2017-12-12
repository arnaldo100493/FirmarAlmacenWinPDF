// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PrimaryUserID.java

package co.org.bouncy.bcpg.sig;

import co.org.bouncy.bcpg.SignatureSubpacket;

public class PrimaryUserID extends SignatureSubpacket
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

    public PrimaryUserID(boolean critical, byte data[])
    {
        super(25, critical, data);
    }

    public PrimaryUserID(boolean critical, boolean isPrimaryUserID)
    {
        super(25, critical, booleanToByteArray(isPrimaryUserID));
    }

    public boolean isPrimaryUserID()
    {
        return data[0] != 0;
    }
}
