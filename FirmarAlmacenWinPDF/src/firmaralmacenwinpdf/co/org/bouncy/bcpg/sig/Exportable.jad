// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Exportable.java

package co.org.bouncy.bcpg.sig;

import co.org.bouncy.bcpg.SignatureSubpacket;

public class Exportable extends SignatureSubpacket
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

    public Exportable(boolean critical, byte data[])
    {
        super(4, critical, data);
    }

    public Exportable(boolean critical, boolean isExportable)
    {
        super(4, critical, booleanToByteArray(isExportable));
    }

    public boolean isExportable()
    {
        return data[0] != 0;
    }
}
