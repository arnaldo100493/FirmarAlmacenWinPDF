// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignerUserID.java

package co.org.bouncy.bcpg.sig;

import co.org.bouncy.bcpg.SignatureSubpacket;

public class SignerUserID extends SignatureSubpacket
{

    private static byte[] userIDToBytes(String id)
    {
        byte idData[] = new byte[id.length()];
        for(int i = 0; i != id.length(); i++)
            idData[i] = (byte)id.charAt(i);

        return idData;
    }

    public SignerUserID(boolean critical, byte data[])
    {
        super(28, critical, data);
    }

    public SignerUserID(boolean critical, String userID)
    {
        super(28, critical, userIDToBytes(userID));
    }

    public String getID()
    {
        char chars[] = new char[data.length];
        for(int i = 0; i != chars.length; i++)
            chars[i] = (char)(data[i] & 0xff);

        return new String(chars);
    }
}
