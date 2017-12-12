// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PreferredAlgorithms.java

package co.org.bouncy.bcpg.sig;

import co.org.bouncy.bcpg.SignatureSubpacket;

public class PreferredAlgorithms extends SignatureSubpacket
{

    private static byte[] intToByteArray(int v[])
    {
        byte data[] = new byte[v.length];
        for(int i = 0; i != v.length; i++)
            data[i] = (byte)v[i];

        return data;
    }

    public PreferredAlgorithms(int type, boolean critical, byte data[])
    {
        super(type, critical, data);
    }

    public PreferredAlgorithms(int type, boolean critical, int preferrences[])
    {
        super(type, critical, intToByteArray(preferrences));
    }

    /**
     * @deprecated Method getPreferrences is deprecated
     */

    public int[] getPreferrences()
    {
        return getPreferences();
    }

    public int[] getPreferences()
    {
        int v[] = new int[data.length];
        for(int i = 0; i != v.length; i++)
            v[i] = data[i] & 0xff;

        return v;
    }
}
