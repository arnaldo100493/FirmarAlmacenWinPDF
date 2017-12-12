// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IssuerKeyID.java

package co.org.bouncy.bcpg.sig;

import co.org.bouncy.bcpg.SignatureSubpacket;

public class IssuerKeyID extends SignatureSubpacket
{

    protected static byte[] keyIDToBytes(long keyId)
    {
        byte data[] = new byte[8];
        data[0] = (byte)(int)(keyId >> 56);
        data[1] = (byte)(int)(keyId >> 48);
        data[2] = (byte)(int)(keyId >> 40);
        data[3] = (byte)(int)(keyId >> 32);
        data[4] = (byte)(int)(keyId >> 24);
        data[5] = (byte)(int)(keyId >> 16);
        data[6] = (byte)(int)(keyId >> 8);
        data[7] = (byte)(int)keyId;
        return data;
    }

    public IssuerKeyID(boolean critical, byte data[])
    {
        super(16, critical, data);
    }

    public IssuerKeyID(boolean critical, long keyID)
    {
        super(16, critical, keyIDToBytes(keyID));
    }

    public long getKeyID()
    {
        long keyID = (long)(data[0] & 0xff) << 56 | (long)(data[1] & 0xff) << 48 | (long)(data[2] & 0xff) << 40 | (long)(data[3] & 0xff) << 32 | (long)(data[4] & 0xff) << 24 | (long)((data[5] & 0xff) << 16) | (long)((data[6] & 0xff) << 8) | (long)(data[7] & 0xff);
        return keyID;
    }
}
