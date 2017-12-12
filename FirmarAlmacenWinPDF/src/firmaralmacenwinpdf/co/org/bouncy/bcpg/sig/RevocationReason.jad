// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RevocationReason.java

package co.org.bouncy.bcpg.sig;

import co.org.bouncy.bcpg.SignatureSubpacket;
import co.org.bouncy.util.Strings;

public class RevocationReason extends SignatureSubpacket
{

    public RevocationReason(boolean isCritical, byte data[])
    {
        super(29, isCritical, data);
    }

    public RevocationReason(boolean isCritical, byte reason, String description)
    {
        super(29, isCritical, createData(reason, description));
    }

    private static byte[] createData(byte reason, String description)
    {
        byte descriptionBytes[] = Strings.toUTF8ByteArray(description);
        byte data[] = new byte[1 + descriptionBytes.length];
        data[0] = reason;
        System.arraycopy(descriptionBytes, 0, data, 1, descriptionBytes.length);
        return data;
    }

    public byte getRevocationReason()
    {
        return getData()[0];
    }

    public String getRevocationDescription()
    {
        byte data[] = getData();
        if(data.length == 1)
        {
            return "";
        } else
        {
            byte description[] = new byte[data.length - 1];
            System.arraycopy(data, 1, description, 0, description.length);
            return Strings.fromUTF8ByteArray(description);
        }
    }
}
