// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyFlags.java

package co.org.bouncy.bcpg.sig;

import co.org.bouncy.bcpg.SignatureSubpacket;

public class KeyFlags extends SignatureSubpacket
{

    private static byte[] intToByteArray(int v)
    {
        byte tmp[] = new byte[4];
        int size = 0;
        for(int i = 0; i != 4; i++)
        {
            tmp[i] = (byte)(v >> i * 8);
            if(tmp[i] != 0)
                size = i;
        }

        byte data[] = new byte[size + 1];
        System.arraycopy(tmp, 0, data, 0, data.length);
        return data;
    }

    public KeyFlags(boolean critical, byte data[])
    {
        super(27, critical, data);
    }

    public KeyFlags(boolean critical, int flags)
    {
        super(27, critical, intToByteArray(flags));
    }

    public int getFlags()
    {
        int flags = 0;
        for(int i = 0; i != data.length; i++)
            flags |= (data[i] & 0xff) << i * 8;

        return flags;
    }

    public static final int CERTIFY_OTHER = 1;
    public static final int SIGN_DATA = 2;
    public static final int ENCRYPT_COMMS = 4;
    public static final int ENCRYPT_STORAGE = 8;
    public static final int SPLIT = 16;
    public static final int AUTHENTICATION = 32;
    public static final int SHARED = 128;
}
