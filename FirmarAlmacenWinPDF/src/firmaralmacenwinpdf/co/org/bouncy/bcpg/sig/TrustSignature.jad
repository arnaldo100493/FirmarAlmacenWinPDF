// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TrustSignature.java

package co.org.bouncy.bcpg.sig;

import co.org.bouncy.bcpg.SignatureSubpacket;

public class TrustSignature extends SignatureSubpacket
{

    private static byte[] intToByteArray(int v1, int v2)
    {
        byte data[] = new byte[2];
        data[0] = (byte)v1;
        data[1] = (byte)v2;
        return data;
    }

    public TrustSignature(boolean critical, byte data[])
    {
        super(5, critical, data);
    }

    public TrustSignature(boolean critical, int depth, int trustAmount)
    {
        super(5, critical, intToByteArray(depth, trustAmount));
    }

    public int getDepth()
    {
        return data[0] & 0xff;
    }

    public int getTrustAmount()
    {
        return data[1] & 0xff;
    }
}
