// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NotationData.java

package co.org.bouncy.bcpg.sig;

import co.org.bouncy.bcpg.SignatureSubpacket;
import co.org.bouncy.util.Strings;
import java.io.ByteArrayOutputStream;

public class NotationData extends SignatureSubpacket
{

    public NotationData(boolean critical, byte data[])
    {
        super(20, critical, data);
    }

    public NotationData(boolean critical, boolean humanReadable, String notationName, String notationValue)
    {
        super(20, critical, createData(humanReadable, notationName, notationValue));
    }

    private static byte[] createData(boolean humanReadable, String notationName, String notationValue)
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(humanReadable ? 128 : 0);
        out.write(0);
        out.write(0);
        out.write(0);
        byte valueData[] = null;
        byte nameData[] = Strings.toUTF8ByteArray(notationName);
        int nameLength = Math.min(nameData.length, 255);
        valueData = Strings.toUTF8ByteArray(notationValue);
        int valueLength = Math.min(valueData.length, 255);
        out.write(nameLength >>> 8 & 0xff);
        out.write(nameLength >>> 0 & 0xff);
        out.write(valueLength >>> 8 & 0xff);
        out.write(valueLength >>> 0 & 0xff);
        out.write(nameData, 0, nameLength);
        out.write(valueData, 0, valueLength);
        return out.toByteArray();
    }

    public boolean isHumanReadable()
    {
        return data[0] == -128;
    }

    public String getNotationName()
    {
        int nameLength = (data[4] << 8) + (data[5] << 0);
        byte bName[] = new byte[nameLength];
        System.arraycopy(data, 8, bName, 0, nameLength);
        return Strings.fromUTF8ByteArray(bName);
    }

    public String getNotationValue()
    {
        return Strings.fromUTF8ByteArray(getNotationValueBytes());
    }

    public byte[] getNotationValueBytes()
    {
        int nameLength = (data[4] << 8) + (data[5] << 0);
        int valueLength = (data[6] << 8) + (data[7] << 0);
        byte bValue[] = new byte[valueLength];
        System.arraycopy(data, 8 + nameLength, bValue, 0, valueLength);
        return bValue;
    }

    public static final int HEADER_FLAG_LENGTH = 4;
    public static final int HEADER_NAME_LENGTH = 2;
    public static final int HEADER_VALUE_LENGTH = 2;
}
